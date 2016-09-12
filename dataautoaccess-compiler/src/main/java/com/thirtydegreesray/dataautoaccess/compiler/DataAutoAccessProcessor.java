package com.thirtydegreesray.dataautoaccess.compiler;

import com.google.auto.service.AutoService;
import com.thirtydegreesray.dataautoaccess.annotation.AutoAccess;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.STATIC;

/**
 * Created by ThirtyDegreesRay on 2016/9/5 10:43
 */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@AutoService(Processor.class)
public class DataAutoAccessProcessor extends AbstractProcessor {

    public static final String SUFFIX = "$$DataAccessor";

    private static final List<String> SUPPORTED_INTERFACE_TYPE = Arrays.asList(
            "android.os.Parcelable", "java.io.Serializable", "java.lang.CharSequence"
    );

    static final List<String> SUPPORTED_FIELD_TYPE = Arrays.asList(
            "java.lang.String", "int", "boolean", "double", "float", "long",
            "byte", "char", "short", "android.os.Parcelable", "java.io.Serializable", "android.os.Bundle", "java.lang.CharSequence",
            "java.util.ArrayList",
            "java.lang.String[]", "int[]", "boolean[]", "double[]", "float[]", "long[]",
            "byte[]", "char[]", "short[]", "android.os.Parcelable[]", "java.lang.CharSequence[]"
    );

    static final List<String> PUT_DATA_PRE_CODE = Arrays.asList(
            "putString", "putInt", "putBoolean", "putDouble", "putFloat", "putLong",
            "putByte", "putChar", "putShort", "putParcelable", "putSerializable", "putBundle", "putCharSequence",
            "putSerializable",
            "putStringArray", "putIntArray", "putBooleanArray", "putDoubleArray", "putFloatArray", "putLongArray",
            "putByteArray", "putCharArray", "putShortArray", "putParcelableArray", "putCharSequenceArray"
    );

    static final LinkedHashMap<String, String> PUT_DATA_PRE_CODE_MAP = new LinkedHashMap<String, String>();

    static {
        for (int i = 0; i < SUPPORTED_FIELD_TYPE.size(); i++) {
            PUT_DATA_PRE_CODE_MAP.put(SUPPORTED_FIELD_TYPE.get(i), PUT_DATA_PRE_CODE.get(i));
        }
    }

    private Elements elementUtils;
    private Types typeUtils;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);

        elementUtils = env.getElementUtils();
        typeUtils = env.getTypeUtils();
        filer = env.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<String>();
        types.add(AutoAccess.class.getCanonicalName());
        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {

        Map<TypeElement, AutoAccessClass> targetClassMap = findAndParseTargets(env);

        for (Map.Entry<TypeElement, AutoAccessClass> entry : targetClassMap.entrySet()) {
            TypeElement typeElement = entry.getKey();
            AutoAccessClass bindingClass = entry.getValue();

            try {
                JavaFileObject jfo = filer.createSourceFile(bindingClass.getFqcn(), typeElement);
                Writer writer = jfo.openWriter();
                writer.write(bindingClass.brewJava());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                error(typeElement, "Unable to write data accessor for type %s: %s", typeElement,
                        e.getMessage());
            }
//            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
//                    bindingClass.brewJava(), typeElement);
        }

        return true;
    }

    private Map<TypeElement, AutoAccessClass> findAndParseTargets(RoundEnvironment env) {
        Map<TypeElement, AutoAccessClass> targetClassMap = new LinkedHashMap<TypeElement, AutoAccessClass>();

        for (Element element : env.getElementsAnnotatedWith(AutoAccess.class)) {
            parseElement(element, targetClassMap);
        }

        return targetClassMap;
    }

    private void parseElement(Element element, Map<TypeElement, AutoAccessClass> targetClassMap) {
        if (!isAccessibleField(element)) {
            return;
        }

        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        AutoAccessClass autoAccessClass = getOrCreateTargetClass(typeElement, targetClassMap);

        String filedName = element.getSimpleName().toString();
        String dataName = element.getAnnotation(AutoAccess.class).dataName();
        String filedType = getProcessedFiledType(element);
        FieldAutoAccess fieldAutoAccess = new FieldAutoAccess(filedName, dataName, filedType);

        autoAccessClass.addField(fieldAutoAccess);

    }

    private AutoAccessClass getOrCreateTargetClass(TypeElement typeElement
            , Map<TypeElement, AutoAccessClass> targetClassMap) {

        AutoAccessClass autoAccessClass = targetClassMap.get(typeElement);
        if (autoAccessClass == null) {
            String targetType = typeElement.getQualifiedName().toString();
            String classPackage = getPackageName(typeElement);
            String className = getClassName(typeElement, classPackage) + SUFFIX;
            autoAccessClass = new AutoAccessClass(classPackage, className, targetType);
            targetClassMap.put(typeElement, autoAccessClass);
        }

        return autoAccessClass;
    }

    private void error(Element element, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message, element);
    }

    private String getPackageName(TypeElement type) {
        return elementUtils.getPackageOf(type).getQualifiedName().toString();
    }

    private static String getClassName(TypeElement type, String packageName) {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen).replace('.', '$');
    }

    private String getRealFieldType(TypeMirror elementType) {
        String name = typeUtils.erasure(elementType).toString();
        int typeParamStart = name.indexOf('<');
        if (typeParamStart != -1) {
            name = name.substring(0, typeParamStart);
        }
        return name;
    }

    /**
     * especially process field type who interface Parcelable/Serializable/CharSequence
     * @param element
     * @return
     */
    private String getProcessedFiledType(Element element) {
        String filedType = getRealFieldType(element.asType());
        TypeMirror typeMirror = element.asType();
        if (!SUPPORTED_FIELD_TYPE.contains(filedType)) {
            for (String interfaceType : SUPPORTED_INTERFACE_TYPE) {
                //if filed is array type, use component type to compare
                if(typeMirror instanceof ArrayType){
                    TypeMirror componentTypeMirror = ((ArrayType)typeMirror).getComponentType();
                    if (isSubtypeOfType(componentTypeMirror, interfaceType)) {
                        filedType = interfaceType + "[]";
                        break;
                    }
                }else if (isSubtypeOfType(typeMirror, interfaceType)) {
                    filedType = interfaceType;
                    break;
                }
            }
        }
        return filedType;
    }

    private boolean isAccessibleField(Element element) {
        boolean enable = true;
        TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();

        //Verify is filed
        if (!element.getKind().equals(ElementKind.FIELD)) {
            error(element, "@ %s must be field. (%s)",
                    enclosingElement.getQualifiedName(),
                    element.getSimpleName());
            enable = false;
        }

        // Verify field modifiers.
        Set<Modifier> modifiers = element.getModifiers();
        if (modifiers.contains(PRIVATE) || modifiers.contains(STATIC)) {
            error(element, "@ %s must not be private or static. (%s)",
                    enclosingElement.getQualifiedName(),
                    element.getSimpleName());
            enable = false;
        }

        String filedType = getProcessedFiledType(element);
        if (!SUPPORTED_FIELD_TYPE.contains(filedType)) {
            error(element, "@ %s %s not supported. (%s)",
                    enclosingElement.getQualifiedName(),
                    filedType,
                    element.getSimpleName());
            enable = false;
        }

        return enable;
    }

    private boolean isSubtypeOfType(TypeMirror typeMirror, String otherType) {
        if (otherType.equals(typeMirror.toString())) {
            return true;
        }
        if (typeMirror.getKind() != TypeKind.DECLARED) {
            return false;
        }
        DeclaredType declaredType = (DeclaredType) typeMirror;
        List<? extends TypeMirror> typeArguments = declaredType.getTypeArguments();
        if (typeArguments.size() > 0) {
            StringBuilder typeString = new StringBuilder(declaredType.asElement().toString());
            typeString.append('<');
            for (int i = 0; i < typeArguments.size(); i++) {
                if (i > 0) {
                    typeString.append(',');
                }
                typeString.append('?');
            }
            typeString.append('>');
            if (typeString.toString().equals(otherType)) {
                return true;
            }
        }
        Element element = declaredType.asElement();
        if (!(element instanceof TypeElement)) {
            return false;
        }
        TypeElement typeElement = (TypeElement) element;
        TypeMirror superType = typeElement.getSuperclass();
        if (isSubtypeOfType(superType, otherType)) {
            return true;
        }
        for (TypeMirror interfaceType : typeElement.getInterfaces()) {
            if (isSubtypeOfType(interfaceType, otherType)) {
                return true;
            }
        }
        return false;
    }

}

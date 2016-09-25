package com.thirtydegreesray.dataautoaccess.compiler;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2016/9/6 16:49
 */

public class AutoAccessClass {

    private String classPackage;
    private String className;
    private String targetClass;

    private ArrayList<FieldAutoAccess> fields = new ArrayList<FieldAutoAccess>();

    public AutoAccessClass(String classPackage, String className, String targetClass) {
        this.classPackage = classPackage;
        this.className = className;
        this.targetClass = targetClass;
    }

    public void addField(FieldAutoAccess fieldAutoAccess){
        fields.add(fieldAutoAccess);
    }

    public ArrayList<FieldAutoAccess> getFields() {
        return fields;
    }

    /**
     * get fully qualified class name
     * @return fully qualified class name
     */
    String getFqcn() {
        return classPackage + "." + className;
    }

    /**
     * generate java code
     * @return java code
     */
    String brewJava() {
        StringBuilder java = new StringBuilder();

        java.append("package ").append(classPackage).append(";").append("\n");
        java.append("\n");
        java.append("import android.os.Bundle;").append("\n");
        java.append("import android.os.Parcelable;").append("\n");
        java.append("import java.io.Serializable;").append("\n");
        java.append("import java.util.ArrayList;").append("\n");
        java.append("\n");
        java.append("import com.thirtydegreesray.dataautoaccess.DataAutoAccess;").append("\n");
        java.append("import com.thirtydegreesray.dataautoaccess.DataAutoAccess.DataAccessor;").append("\n");
        java.append("\n");

        java.append("public class ").append(className)
                .append("<T extends ").append(targetClass).append(">")
                .append(" implements").append(" DataAccessor<T>")
                .append("{\n");
        generateGetDataCode(java);
        generateSaveDataCode(java);
        java.append("}\n");
        return java.toString();
    }

    private void generateGetDataCode(StringBuilder java){
        java.append("\t@Override ").append("public void getData(T target, Bundle dataStore){\n");
        for(FieldAutoAccess field : fields){
            java.append("\t\t").append("if(dataStore.containsKey(\"").append(field.getFiledKey()).append("\"))\n");
            java.append("\t\t\t").append("target.").append(field.getFieldName())
                    .append(" = ")
                    .append("DataAutoAccess.getCastData(").append("\"").append(field.getFiledKey()).append("\"")
                    .append(", ").append("dataStore").append(");\n");
        }
        java.append("\t}\n");
    }

    private void generateSaveDataCode(StringBuilder java){
        java.append("\t@Override ").append("public void saveData(T target, Bundle dataStore){\n");
        for(FieldAutoAccess field : fields){
            String putPreCode = DataAutoAccessProcessor.PUT_DATA_PRE_CODE_MAP.get(field.getFieldType());
            //type ArrayList
//            if(putPreCode.equals("")){
//                java.append("\t").append("DataAutoAccess.saveArrayList(")
//                        .append("\"").append(field.getFiledKey()).append("\"").append(", ")
//                        .append("target.").append(field.getFieldName()).append(", ")
//                        .append("dataStore").append(");\n");
//            }else{
                java.append("\t\t").append("dataStore.").append(putPreCode)
                        .append("(").append("\"").append(field.getFiledKey()).append("\"").append(", ")
                        .append("target.").append(field.getFieldName()).append(");\n");
//            }
        }
        java.append("\t}\n");
    }

}

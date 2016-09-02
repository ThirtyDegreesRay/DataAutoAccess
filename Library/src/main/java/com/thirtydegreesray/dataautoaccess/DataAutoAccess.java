package com.thirtydegreesray.dataautoaccess;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * data auto access<br>
 * set this inject, the object will save automatic when onSaveInstanceState, and get data when onCreate<br>
 * if you want transfer data with intent, you must set dataName value<br>
 * field type we supported，@see com.thirtydegreesray.dataautoaccess.DataAutoAccessTool#saveData
 * @author ThirtyDegreesRay
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataAutoAccess {
	/**
	 * if you want transfer data with intent, you must set this value<br>
	 * onSaveInstanceState don't use this value, use filed name directly
	 * @return dataName
	 */
	String dataName() default "";
	/**
	 * if the field is the type of ArrayList，you need declare the type of ArrayList
	 * @return arrayListType
	 */
	Class<?> arrayListType() default String.class;
}

package com.thirtydegreesray.dataautoaccess;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据自动存储
 * 添加此注解的对象，在onSaveInstanceState时会自动被存储，然后onCreate时读取数据<br>
 * 如果想在初始化activity时，自动获取intent中的数据，请设置dataName属性<br>
 * 支持的数据类型，@see com.thirtydegreesray.dataautoaccess.DataAutoAccessTool#saveData 方法
 * @author ThirtyDegreesRay
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataAutoAccess {
	/**
	 * 如果想通过intent传递数据，则设置此值</br>
	 * onSaveInstanceState存储不会使用到该值，直接使用Filed名称
	 * @return
	 */
	String dataName() default "";
	/**
	 * 如果是ArrayList<?>，需要说明?类型
	 * @return
	 */
	Class<?> arrayListType() default String.class;
}

package com.thirtydegreesray.dataautoaccess.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * data auto access<br>
 * set this inject, the object will save automatic save when onSaveInstanceState, and get data when onCreate<br>
 *
 * Created by ThirtyDegreesRay on 2016/9/6 09:35
 */
@Target(FIELD)
@Retention(CLASS)
public @interface AutoAccess {
    /**
     * the access key of the filed, must be unique in one class<br>
     * if don't set this value, use field name as default key
     * @return dataName
     */
    String dataName() default "";
}

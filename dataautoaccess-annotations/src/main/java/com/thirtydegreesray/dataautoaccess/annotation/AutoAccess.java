package com.thirtydegreesray.dataautoaccess.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * data auto access<br>
 * set this inject, the object will save automatic save when onSaveInstanceState, and get data when onCreate<br>
 *
 * Created by ThirtyDegreesRay on 2016/9/6 09:35
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoAccess {
    /**
     * the access key of the filed, must be unique in one class
     * @return dataName
     */
    String dataName() ;
}

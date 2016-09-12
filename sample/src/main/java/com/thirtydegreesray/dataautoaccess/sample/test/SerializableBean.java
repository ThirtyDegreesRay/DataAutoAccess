package com.thirtydegreesray.dataautoaccess.sample.test;

import java.io.Serializable;

/**
 * Created by ThirtyDegreesRay on 2016/9/12 11:10
 */

public class SerializableBean implements Serializable {

    int size ;
    String name ;

    public SerializableBean initData(){
        size = 2;
        name = "ParcelableClass";
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SerializableBean)){
            return false;
        }
        SerializableBean serializableBean = (SerializableBean) obj;
        if(size != serializableBean.size){
            return false;
        }
        return name.equals(serializableBean.name);
    }
}

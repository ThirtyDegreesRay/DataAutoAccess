package com.thirtydegreesray.dataautoaccess.compiler;

/**
 * Created by ThirtyDegreesRay on 2016/9/6 17:07
 */

public class FieldAutoAccess {

    private String fieldName;
    private String dataName;
    private String fieldType;

    public FieldAutoAccess(String fieldName, String dataName, String fieldType) {
        this.fieldName = fieldName;
        this.dataName = dataName;
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getDataName() {
        return dataName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public String getFiledKey(){
        return dataName== null || dataName.equals("") ? fieldName : dataName;
    }
}

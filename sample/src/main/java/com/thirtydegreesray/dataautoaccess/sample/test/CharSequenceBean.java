package com.thirtydegreesray.dataautoaccess.sample.test;

/**
 * Created by ThirtyDegreesRay on 2016/9/12 11:21
 */

public class CharSequenceBean implements CharSequence{

    private String charString;

    public CharSequenceBean(String charString){
        this.charString = charString;
    }

    @Override
    public int length() {
        return charString.length();
    }

    @Override
    public char charAt(int index) {
        return charString.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return charString.substring(start, end);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CharSequenceBean)){
            return false;
        }
        CharSequenceBean charSequenceBean = (CharSequenceBean) obj;
        return charString.equals(charSequenceBean.charString);
    }
}

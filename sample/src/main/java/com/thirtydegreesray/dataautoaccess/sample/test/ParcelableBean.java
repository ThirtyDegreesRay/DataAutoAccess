package com.thirtydegreesray.dataautoaccess.sample.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ThirtyDegreesRay on 2016/9/12 11:04
 */

public class ParcelableBean implements Parcelable{

    int size ;
    String name ;

    protected ParcelableBean(Parcel in) {
        size = in.readInt();
        name = in.readString();
    }

    public ParcelableBean(){

    }

    public ParcelableBean initData(){
        size = 2;
        name = "ParcelableClass";
        return this;
    }

    public static final Creator<ParcelableBean> CREATOR = new Creator<ParcelableBean>() {
        @Override
        public ParcelableBean createFromParcel(Parcel in) {
            return new ParcelableBean(in);
        }

        @Override
        public ParcelableBean[] newArray(int size) {
            return new ParcelableBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(size);
        dest.writeString(name);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ParcelableBean)){
            return false;
        }
        ParcelableBean parcelableBean = (ParcelableBean) obj;
        if(size != parcelableBean.size){
            return false;
        }
        return name.equals(parcelableBean.name);
    }
}

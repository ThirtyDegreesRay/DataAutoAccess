package com.thirtydegreesray.sample;

import android.os.Bundle;
import android.os.Parcelable;

import com.thirtydegreesray.dataautoaccess.AutoAccess;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by YuYunHao on 2016/9/5 17:45
 */

public class TestClass {

    @AutoAccess(dataName = "field1")
    String field1 ;
    @AutoAccess(dataName = "field2")
    int field2 ;
    @AutoAccess(dataName = "field3")
    boolean field3 ;
    @AutoAccess(dataName = "field4")
    double field4 ;
    @AutoAccess(dataName = "field5")
    float field5 ;
    @AutoAccess(dataName = "field6")
    long field6 ;
    @AutoAccess(dataName = "field7")
    byte field7 ;
    @AutoAccess(dataName = "field8")
    char field8 ;
    @AutoAccess(dataName = "field9")
    short field9 ;
    @AutoAccess(dataName = "field1")
    Parcelable field10 ;
    @AutoAccess(dataName = "field11")
    Serializable field11 ;
    @AutoAccess(dataName = "field12")
    Bundle field12 ;
    @AutoAccess(dataName = "field28")
    CharSequence field28 ;


    @AutoAccess(dataName = "field13")
    ArrayList<String> field13 = new ArrayList<>();
    @AutoAccess(dataName = "field14")
    ArrayList<Integer> field14 = new ArrayList<>();
    @AutoAccess(dataName = "field15")
    ArrayList<? extends Parcelable> field15 = new ArrayList<>();
    @AutoAccess(dataName = "field26")
    ArrayList<? extends CharSequence> field26 = new ArrayList<>();

    @AutoAccess(dataName = "field16")
    String[] field16 ;
    @AutoAccess(dataName = "field17")
    int[] field17 ;
    @AutoAccess(dataName = "field18")
    boolean[] field18 ;
    @AutoAccess(dataName = "field19")
    double[] field19 ;
    @AutoAccess(dataName = "field20")
    float[] field20 ;
    @AutoAccess(dataName = "field21")
    long[] field21 ;
    @AutoAccess(dataName = "field22")
    byte[] field22 ;
    @AutoAccess(dataName = "field23")
    char[] field23 ;
    @AutoAccess(dataName = "field24")
    short[] field24 ;
    @AutoAccess(dataName = "field25")
    Parcelable[] field25 ;
    @AutoAccess(dataName = "field27")
    CharSequence[] field27 ;

}

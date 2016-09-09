package com.thirtydegreesray.dataautoaccess.sample;

import android.os.Bundle;
import android.os.Parcelable;

import com.thirtydegreesray.dataautoaccess.annotation.AutoAccess;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2016/9/5 17:45
 */

public class TestClass {

    @AutoAccess String field1 ;
    @AutoAccess int field2 ;
    @AutoAccess boolean field3 ;
    @AutoAccess double field4 ;
    @AutoAccess float field5 ;
    @AutoAccess long field6 ;
    @AutoAccess byte field7 ;
    @AutoAccess char field8 ;
    @AutoAccess short field9 ;
    @AutoAccess Parcelable field10 ;
    @AutoAccess Serializable field11 ;
    @AutoAccess Bundle field12 ;
    @AutoAccess CharSequence field28 ;

    @AutoAccess ArrayList<String> field13 = new ArrayList<>();
    @AutoAccess ArrayList<Integer> field14 = new ArrayList<>();
    @AutoAccess ArrayList<? extends Parcelable> field15 = new ArrayList<>();
    @AutoAccess ArrayList<? extends CharSequence> field26 = new ArrayList<>();

    @AutoAccess String[] field16 ;
    @AutoAccess int[] field17 ;
    @AutoAccess boolean[] field18 ;
    @AutoAccess double[] field19 ;
    @AutoAccess float[] field20 ;
    @AutoAccess long[] field21 ;
    @AutoAccess byte[] field22 ;
    @AutoAccess char[] field23 ;
    @AutoAccess short[] field24 ;
    @AutoAccess Parcelable[] field25 ;
    @AutoAccess CharSequence[] field27 ;

}

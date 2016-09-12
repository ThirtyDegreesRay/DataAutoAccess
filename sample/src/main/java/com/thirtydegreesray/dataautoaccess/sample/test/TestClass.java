package com.thirtydegreesray.dataautoaccess.sample.test;

import android.os.Bundle;
import android.os.Parcelable;

import com.thirtydegreesray.dataautoaccess.annotation.AutoAccess;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2016/9/5 17:45
 */

public class TestClass {

    @AutoAccess
    String field1;
    @AutoAccess
    int field2;
    @AutoAccess
    boolean field3;
    @AutoAccess
    double field4;
    @AutoAccess
    float field5;
    @AutoAccess
    long field6;
    @AutoAccess
    byte field7;
    @AutoAccess
    char field8;
    @AutoAccess
    short field9;
    @AutoAccess
    ParcelableBean field10;
    @AutoAccess
    SerializableBean field11;
    @AutoAccess
    Bundle field12;
    @AutoAccess
    CharSequenceBean field28;

    @AutoAccess
    ArrayList<String> field13;
    @AutoAccess
    ArrayList<Integer> field14;
    @AutoAccess
    ArrayList<Parcelable> field15;
    @AutoAccess
    ArrayList<CharSequence> field26;

    @AutoAccess
    String[] field16;
    @AutoAccess
    int[] field17;
    @AutoAccess
    boolean[] field18;
    @AutoAccess
    double[] field19;
    @AutoAccess
    float[] field20;
    @AutoAccess
    long[] field21;
    @AutoAccess
    byte[] field22;
    @AutoAccess
    char[] field23;
    @AutoAccess
    short[] field24;
    @AutoAccess
    ParcelableBean[] field25;
    @AutoAccess
    CharSequenceBean[] field27;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TestClass)) {
            return false;
        }

        TestClass testClass = (TestClass) obj;
        if (!field1.equals(testClass.field1)) {
            return false;
        }
        if (field2 != testClass.field2) {
            return false;
        }
        if (field3 != testClass.field3) {
            return false;
        }
        if (field4 != testClass.field4) {
            return false;
        }
        if (field5 != testClass.field5) {
            return false;
        }
        if (field6 != testClass.field6) {
            return false;
        }
        if (field7 != testClass.field7) {
            return false;
        }
        if (field8 != testClass.field8) {
            return false;
        }
        if (field9 != testClass.field9) {
            return false;
        }
        if (!field10.equals(testClass.field10)) {
            return false;
        }
        if (!field11.equals(testClass.field11)) {
            return false;
        }
        if (!field12.get("name").equals(testClass.field12.get("name"))) {
            return false;
        }
        if (!field28.equals(testClass.field28)) {
            return false;
        }

        if (!field13.equals(testClass.field13)) {
            return false;
        }
        if (!field14.equals(testClass.field14)) {
            return false;
        }
        if (!field15.equals(testClass.field15)) {
            return false;
        }
        if (!field26.equals(testClass.field26)) {
            return false;
        }

        if (!field16.equals(testClass.field16)) {
            return false;
        }
        if (!field17.equals(testClass.field17)) {
            return false;
        }
        if (!field18.equals(testClass.field18)) {
            return false;
        }
        if (!field19.equals(testClass.field19)) {
            return false;
        }
        if (!field20.equals(testClass.field20)) {
            return false;
        }
        if (!field21.equals(testClass.field21)) {
            return false;
        }
        if (!field22.equals(testClass.field22)) {
            return false;
        }
        if (!field23.equals(testClass.field23)) {
            return false;
        }
        if (!field24.equals(testClass.field24)) {
            return false;
        }
        if (!field25.equals(testClass.field25)) {
            return false;
        }
        return field27.equals(testClass.field27);
    }
}

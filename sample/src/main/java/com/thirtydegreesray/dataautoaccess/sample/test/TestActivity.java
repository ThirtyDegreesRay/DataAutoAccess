package com.thirtydegreesray.dataautoaccess.sample.test;

import android.os.Bundle;
import android.widget.TextView;

import com.thirtydegreesray.dataautoaccess.DataAutoAccess;
import com.thirtydegreesray.dataautoaccess.sample.BaseActivity;
import com.thirtydegreesray.dataautoaccess.sample.R;

import java.util.ArrayList;

/**
 * Created by ThirtyDegreesRay on 2016/9/12 10:10
 */

public class TestActivity extends BaseActivity {

    private TextView testResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testResult = (TextView) findViewById(R.id.test_result);
        testResult.setText(test() ? "test ok" : "test error");
    }

    private boolean test() {
        TestClass testClass1 = getTestClass();
        Bundle bundle = new Bundle();
        DataAutoAccess.saveData(testClass1, bundle);
        TestClass testClass2 = new TestClass();
        DataAutoAccess.getData(testClass2, bundle);
        return testClass1.equals(testClass2);
    }

    private TestClass getTestClass() {
        TestClass testClass = new TestClass();

        testClass.field1 = "field";
        testClass.field2 = 1;
        testClass.field3 = true;
        testClass.field4 = 1.2;
        testClass.field5 = 1.2f;
        testClass.field6 = 1;
        testClass.field7 = 1;
        testClass.field8 = 'a';
        testClass.field9 = 1;
        testClass.field10 = new ParcelableBean();
        testClass.field10.initData();
        testClass.field11 = new SerializableBean();
        testClass.field11.initData();
        testClass.field12 = new Bundle();
        testClass.field12.putString("name", "DataAutoAccess");
        testClass.field28 = new CharSequenceBean("field");

        testClass.field13 = new ArrayList<>();
        testClass.field13.add("field");
        testClass.field14 = new ArrayList<>();
        testClass.field14.add(1);
        testClass.field15 = new ArrayList<>();
        testClass.field15.add(new ParcelableBean().initData());
        testClass.field26 = new ArrayList<>();
        testClass.field26.add(new CharSequenceBean("field"));

        testClass.field16 = new String[]{"field"};
        testClass.field17 = new int[]{1};
        testClass.field18 = new boolean[]{true};
        testClass.field19 = new double[]{1.2};
        testClass.field20 = new float[]{1.2f};
        testClass.field21 = new long[]{1};
        testClass.field22 = new byte[]{1};
        testClass.field23 = new char[]{'a'};
        testClass.field24 = new short[]{1};
        testClass.field25 = new ParcelableBean[]{new ParcelableBean().initData()};
        testClass.field27 = new CharSequenceBean[]{new CharSequenceBean("field")};

        return testClass;
    }
}

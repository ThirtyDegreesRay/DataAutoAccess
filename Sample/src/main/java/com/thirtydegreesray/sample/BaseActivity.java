package com.thirtydegreesray.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.thirtydegreesray.dataautoaccess.DataAutoAccessTool;

/**
 * Created by ThirtyDegreesRay on 2016/9/1 09:58
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //DataAutoAccessTool 取数据
        Bundle data;
        boolean isFromIntent = true;
        data = getIntent().getExtras();
        //判断数据源
        if (data == null) {
            data = savedInstanceState;
            isFromIntent = false;
        }
        DataAutoAccessTool.getData(this, data, isFromIntent);

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //系统由于内存不足而杀死activity，此时保存数据
        DataAutoAccessTool.saveData(this, outState);
    }
}

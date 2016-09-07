package com.thirtydegreesray.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.thirtydegreesray.dataautoaccess.DataAutoAccess;

/**
 * Created by ThirtyDegreesRay on 2016/9/1 09:58
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get data
        Bundle data;
        data = getIntent().getExtras();
        //judge data source
        if (data == null) {
            data = savedInstanceState;
        }
        DataAutoAccess.getData(this, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save data
        DataAutoAccess.saveData(this, outState);
    }
}

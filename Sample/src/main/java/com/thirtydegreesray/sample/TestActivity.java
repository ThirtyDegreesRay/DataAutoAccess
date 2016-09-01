package com.thirtydegreesray.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.thirtydegreesray.dataautoaccess.DataAutoAccess;

/**
 * Created by ThirtyDegreesRay on 2016/9/1 10:09
 */

public class TestActivity extends BaseActivity {

    @DataAutoAccess(dataName = "name")
    private String name;
    @DataAutoAccess(dataName = "description")
    private String description;

    private TextView tvName;
    private TextView tvDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvName.setText(name);
        tvDescription.setText(description);
    }
}

package com.thirtydegreesray.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.thirtydegreesray.dataautoaccess.DataAutoAccess;
import com.thirtydegreesray.dataautoaccess.annotation.AutoAccess;

/**
 * Created by ThirtyDegreesRay on 2016/9/1 10:09
 */

public class ExampleActivity extends BaseActivity {

    @AutoAccess(dataName = "name")
    String name;
    @AutoAccess(dataName = "description")
    String description;

    private TextView tvName;
    private TextView tvDescription;

    private Bundle testBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvName.setText(name);
        tvDescription.setText(description);

        testBundle = new Bundle();
        DataAutoAccess.saveData(this, testBundle);
    }

    public void onSaveDataClick(View view){
        DataAutoAccess.saveData(this, testBundle);
    }

    public void onChangeDataClick(View view){
        name += (int)(Math.random() * 10);
        description += (int)(Math.random() * 10);
        tvName.setText(name);
        tvDescription.setText(description);
    }

    public void onGetDataClick(View view){
        DataAutoAccess.getData(this, testBundle);
        tvName.setText(name);
        tvDescription.setText(description);
    }
}

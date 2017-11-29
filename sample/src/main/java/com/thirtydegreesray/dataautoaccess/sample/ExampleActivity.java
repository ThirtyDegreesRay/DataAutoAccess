package com.thirtydegreesray.dataautoaccess.sample;

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

    @AutoAccess String name;
    @AutoAccess String description;

    private TextView tvName;
    private TextView tvDescription;
    private TextView tvStarted;

    private Bundle testBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvStarted = (TextView) findViewById(R.id.tv_started);
        tvName.setText(name);
        tvDescription.setText(description);
        tvStarted.setText(started ? "started" : "starting");

        testBundle = new Bundle();
        DataAutoAccess.saveData(this, testBundle);
        started = true;
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

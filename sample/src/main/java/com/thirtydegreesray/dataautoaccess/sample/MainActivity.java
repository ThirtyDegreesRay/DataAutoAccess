package com.thirtydegreesray.dataautoaccess.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thirtydegreesray.dataautoaccess.sample.test.TestActivity;

/**
 * Created by ThirtyDegreesRay on 2016/9/1 09:58
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onDataAutoAccessClick(View view){
        Intent intent = new Intent(this, ExampleActivity.class);
        intent.putExtra("name", "DataAutoAccess");
        intent.putExtra("description", "Android bundle data auto access.");
        startActivity(intent);
    }

    public void onTestClick(View view){
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
}

#DataAutoAccess
Android bundle data auto access
##Usages
1. activity启动时取出intent中的数据；
2. activity由于系统内存不足时被杀死，在onSaveInstanceState方法里存储数据，onCreate时取出数据。

##Use
BaseActivity中添加取出数据和存储数据代码：

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

启动Activity时传入参数：
    
    Intent intent = new Intent(this, TestActivity.class);
    intent.putExtra("name", "DataAutoAccess");
    intent.putExtra("description", "Android bundle data auto access.");
    startActivity(intent);
        
给ExampleActivity中需要自动存储的变量添加注解：

    @AutoAccess(dataName = "name")
    private String name;
    @AutoAccess(dataName = "description")
    private String description;
    
经过以上配置之后，DataAutoAccess会自动从intent中取出数据，给name和description变量赋值，而且当activity由于系统内存不足被杀死时，也会自动保存变量值，在onCreate时取出进行赋值。


##Download
Configure your project-level build.gradle to include the 'android-apt' plugin:

    buildscript {
        repositories {
            mavenCentral()
        }
        dependencies {
            classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
        }
    }
    
Then, apply the 'android-apt' plugin in your module-level build.gradle and add the Data Auto Access dependencies:

    apply plugin: 'android-apt'
    
    android {
        ...
    }
    
    dependencies {
        compile 'com.thirtydegreesray:dataautoaccess:1.2.1'
        apt 'com.thirtydegreesray:dataautoaccess-compiler:1.2.1'
    }

##License
    Copyright 2016 ThirtyDegreesRay
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




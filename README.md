# DataAutoAccess
[![Releases](https://img.shields.io/github/release/ThirtyDegreesRay/DataAutoAccess.svg)](https://github.com/ThirtyDegreesRay/DataAutoAccess/releases/latest)

自动存取Android Bundle中数据——给需要自动存取的变量添加注解，编译时会通过注解处理自动生成存取的代码

* Activity或Service启动时自动取出Intent中的数据，并赋值给相应的field
* Activity或Fragment由于系统内存不足将要被杀死时，在onSaveInstanceState方法里存储数据，重启时在onCreate中取出数据并赋值给相应的field

```java
public class ExampleActivity extends Activity{
    @AutoAccess String name;
    @AutoAccess String description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get data
        DataAutoAccess.getData(this, savedInstanceState);
        //TODO use fields...
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save data
        DataAutoAccess.saveData(this, outState);
    }
}
```

Intent传参形式：
```java    
Intent intent = new Intent(this, ExampleActivity.class);
intent.putExtra("name", "DataAutoAccess");
intent.putExtra("description", "Android bundle data auto access.");
startActivity(intent);
```    
经过以上配置之后，DataAutoAccess会自动从intent中取出数据，给name和description变量赋值，而且当activity由于系统内存不足将要被杀死时，也会自动保存变量值，在onCreate时取出进行赋值。
### Supported field type
Support all type which bundle supported:<br>
String, int, boolean, double, float, long, byte, char, short, Parcelable, Serializable, Bundle, CharSequence,ArrayList<String>, ArrayList<Integer>, ArrayList<Parcelable>, ArrayList<CharSequence>, String[], int[], boolean[], double[], float[], long[], byte[], char[], short[], ParcelableBean[], CharSequenceBean[].

## Download
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
        compile 'com.thirtydegreesray:dataautoaccess:latestVersion'
        apt 'com.thirtydegreesray:dataautoaccess-compiler:latestVersion'
    }
    
## Proguard
    -keep class com.thirtydegreesray.dataautoaccess.** { *; }
    -keep class **$$DataAccessor { *; }
    -keepclasseswithmembernames class * { @com.thirtydegreesray.dataautoaccess.annotation.AutoAccess <fields>;}

## License
    Copyright 2016 ThirtyDegreesRay
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governin8g permissions and
    limitations under the License.




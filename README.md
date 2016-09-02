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

启动Activity时传入参数：
    
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra("name", "DataAutoAccess");
        intent.putExtra("description", "Android bundle data auto access.");
        startActivity(intent);
        
给TestActivity中需要自动存储的变量添加注解：

    @DataAutoAccess(dataName = "name")
    private String name;
    @DataAutoAccess(dataName = "description")
    private String description;
    
经过以上配置之后，DataAutoAccessTool会自动从intent中取出数据，给name和description变量赋值，而且当activity由于系统内存不足被杀死时，也会自动保存变量值，在onCreate时取出进行自动赋值。


##Download

    dependencies {
        compile 'com.thirtydegreesray.dataautoaccess:Library:1.0.5'
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




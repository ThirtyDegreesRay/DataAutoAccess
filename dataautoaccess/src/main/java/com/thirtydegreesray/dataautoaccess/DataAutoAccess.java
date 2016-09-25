package com.thirtydegreesray.dataautoaccess;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Data auto access<br>
 * Android bundle data auto access.<br>
 * USE<br>
 * <pre><code>
 *       public class ExampleActivity extends Activity {
 *          {@literal @}AutoAccess String name;
 *          {@literal @}AutoAccess String description;
 *
 *          {@literal @}Override
 *           protected void onCreate(@Nullable Bundle savedInstanceState) {
 *               super.onCreate(savedInstanceState);
 *               DataAutoAccess.getData(this, data);
 *               //TODO use fields...
 *           }
 *
 *          {@literal @}Override
 *           protected void onSaveInstanceState(Bundle outState) {
 *               super.onSaveInstanceState(outState);
 *               //save data
 *               DataAutoAccess.saveData(this, outState);
 *           }
 *      }
 * </code></pre>
 * <p>
 * <pre><code>
 *      Intent intent = new Intent(this, ExampleActivity.class);
 *      intent.putExtra("name", "DataAutoAccess");
 *      intent.putExtra("description", "Android bundle data auto access.");
 *      startActivity(intent);
 * </code></pre>
 * <p>
 * Created by ThirtyDegreesRay on 2016/9/6 09:35
 */
public class DataAutoAccess {

    private static final String SUFFIX = "$$DataAccessor";

    private static LinkedHashMap<String, DataAccessor<Object>> dataAccessorMap = new LinkedHashMap<String, DataAccessor<Object>>();

    /**
     * get data from bundle
     *
     * @param targetObject the object need to save data
     * @param dataStore    the bundle to get data
     */
    public static void getData(Object targetObject, Bundle dataStore) {

        if(targetObject == null){
            return ;
        }

        //data from savedInstanceState or intent
        if(targetObject instanceof Activity && dataStore == null){
            dataStore = ((Activity)targetObject).getIntent().getExtras();
        }

        if (dataStore == null) {
            return;
        }

        DataAccessor<Object> dataAccessor = getDataAccessor(targetObject, dataStore);
        if (dataAccessor != null) {
            dataAccessor.getData(targetObject, dataStore);
        }
    }

    /**
     * save data to bundle
     *
     * @param targetObject the object need to save data
     * @param dataStore    the bundle to save data
     */
    public static void saveData(Object targetObject, Bundle dataStore) {
        if (targetObject == null || dataStore == null) {
            return;
        }

        DataAccessor<Object> dataAccessor = getDataAccessor(targetObject, dataStore);
        if (dataAccessor != null) {
            dataAccessor.saveData(targetObject, dataStore);
        }
    }

    private static DataAccessor<Object> getDataAccessor(Object targetObject, Bundle dataStore) {
        Class<?> targetClass = targetObject.getClass();
        String className = targetClass.getName() + SUFFIX;
        return getDataAccessor(className);
    }

    private static DataAccessor<Object> getDataAccessor(String className) {
        if (!dataAccessorMap.containsKey(className)) {
            try {
                Class<?> dataAccessorClass = Class.forName(className);
                DataAccessor<Object> dataAccessor = (DataAccessor<Object>) dataAccessorClass.newInstance();
                dataAccessorMap.put(className, dataAccessor);
            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return dataAccessorMap.get(className);
    }

    /**
     * DO NOT USE: Exposed for generated code.
     */
    public interface DataAccessor<T> {
        void getData(T target, Bundle dataStore);

        void saveData(T target, Bundle dataStore);
    }

    /**
     * Exposed for generated code.
     */
    public static <T extends Object> T getCastData(String key, Bundle dataStore) {
        return (T) dataStore.get(key);
    }

    /**
     * Exposed for generated code.
     */
    public static void saveArrayList(String key, ArrayList list, Bundle dataStore) {
        if (list == null) {
            return;
        }
        Object[] listArray = list.toArray();
        if (listArray instanceof String[]) {
            dataStore.putStringArrayList(key, list);
        } else if (listArray instanceof Integer[]) {
            dataStore.putIntegerArrayList(key, list);
        } else if (listArray instanceof Parcelable[]) {
            dataStore.putParcelableArrayList(key, list);
        } else if (listArray instanceof CharSequence[]) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                dataStore.putCharSequenceArrayList(key, list);
            }
        } else {
            throw new NullPointerException("");
        }
    }

}

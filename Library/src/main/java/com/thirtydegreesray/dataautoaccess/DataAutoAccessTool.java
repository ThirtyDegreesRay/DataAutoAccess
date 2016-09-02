package com.thirtydegreesray.dataautoaccess;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * data auto access tool
 * @author ThirtyDegreesRay
 *
 */
public class DataAutoAccessTool {

	private final static String TAG = "DataAutoAccessTool";
	
	/**
	 * save data
	 * @param injectedSource the object need to save data
	 * @param outState the bundle to save data
	 */
	@SuppressWarnings("unchecked")
	public static void saveData(Object injectedSource, Bundle outState){
		if(injectedSource == null || outState == null)
			return ;
		
		Field[] fields = injectedSource.getClass().getDeclaredFields();
		if(fields!=null && fields.length>0){
			for(Field field : fields){
				try {
					field.setAccessible(true);
				
					DataAutoAccess dataAutoAccess = field.getAnnotation(DataAutoAccess.class);
					if(dataAutoAccess != null){
						Class<?> type = field.getType();
						Object value = field.get(injectedSource);
						String key = field.getName();
						
						if(type.equals(String.class)){
							outState.putString(key, (String) value);
						}else if(type.equals(int.class)){
							outState.putInt(key, (Integer) value);
						}else if(type.equals(boolean.class)){
							outState.putBoolean(key, (Boolean) value);
						}else if(type.equals(double.class)){
							outState.putDouble(key, (Double) value);
						}else if(type.equals(float.class)){
							outState.putFloat(key, (Float) value);
						}else if(type.equals(long.class)){
							outState.putLong(key, (Long) value);
						}else if(type.equals(byte.class)){
							outState.putByte(key, (Byte) value);
						}else if(type.equals(char.class)){
							outState.putChar(key, (Character) value);
						}else if(type.equals(short.class)){
							outState.putShort(key, (Short) value);
						}else if(type.equals(Parcelable.class)){
							outState.putParcelable(key, (Parcelable) value);
						}else if(type.equals(Serializable.class)){
							outState.putSerializable(key, (Serializable) value);
						}else if(type.equals(Bundle.class)){
							outState.putBundle(key, (Bundle) value);
						}
						
						else if(type.equals(ArrayList.class)){
							Class<?> arrayListType = dataAutoAccess.arrayListType();
							if(arrayListType.equals(String.class)){
								outState.putStringArrayList(key, (ArrayList<String>) value);
							}else if(arrayListType.equals(Integer.class)){
								outState.putIntegerArrayList(key, (ArrayList<Integer>) value);
							}else if(arrayListType.equals(Parcelable.class)){
								outState.putParcelableArrayList(key, (ArrayList<? extends Parcelable>) value);
							}
						}
						
						else if(type.equals(String[].class)){
							outState.putStringArray(key, (String[]) value);
						}else if(type.equals(int[].class)){
							outState.putIntArray(key, (int[]) value);
						}else if(type.equals(boolean[].class)){
							outState.putBooleanArray(key, (boolean[]) value);
						}else if(type.equals(double[].class)){
							outState.putDoubleArray(key, (double[]) value);
						}else if(type.equals(float[].class)){
							outState.putFloatArray(key, (float[]) value);
						}else if(type.equals(long[].class)){
							outState.putLongArray(key, (long[]) value);
						}else if(type.equals(byte[].class)){
							outState.putByteArray(key, (byte[]) value);
						}else if(type.equals(char[].class)){
							outState.putCharArray(key, (char[]) value);
						}else if(type.equals(short[].class)){
							outState.putShortArray(key, (short[]) value);
						}else if(type.equals(Parcelable[].class)){
							outState.putParcelableArray(key, (Parcelable[]) value);
						}
//						Log.i("Save", "save success：filed " + field.getName() );
					}
				} catch (Exception e) {
					e.printStackTrace();
					Log.w("Save", "save exception：filed " + field.getName() + "  " + e.getMessage().toString());
				} 
			}
		}
	}
	
	/**
	 * get data from bundle, and init filed value
	 * @param injectedSource the object need init
	 * @param data bundle data
	 * @param isFromIntent if the data from intent, set true, otherwise set false
	 */
	public static void getData(Object injectedSource, Bundle data, boolean isFromIntent){
		if(injectedSource == null || data == null)
			return ;
		
		Field[] fields = injectedSource.getClass().getDeclaredFields();
		if(fields!=null && fields.length>0){
			for(Field field : fields){
				String error = null;
				try {
					field.setAccessible(true);
				
					DataAutoAccess dataAutoAccess = field.getAnnotation(DataAutoAccess.class);
					if(dataAutoAccess != null){
						String key ;
						//because the field name will changed when proguard, so we need to set ‘dataName' as key
						if(isFromIntent){
							key = dataAutoAccess.dataName();
						}else{
							//when onSaveInstanceState, we save field name as key, so get use filed name too
							key = field.getName();
						}

						if(key.equals(""))
							continue;
						
						if(data.containsKey(key)){
							Object value = data.get(key);
							field.set(injectedSource, value);
//							Log.i("Save", "get success：filed " + field.getName() );
						}else{
							error = key + "don't exits";
						}
				
					}
				} catch (Exception e) {
					error = field.getName() + " get Exception:" + e.getMessage().toString();
				} 
				
				if(error != null){
					if(isFromIntent){
						Log.w("GetIntentDataError", injectedSource.getClass().getName() + " " + error);
					}else{
						Log.w("GetSaveInstanceError",
								injectedSource.getClass().getName() + " " + error);
					}
				}
			}
		}
	}
}

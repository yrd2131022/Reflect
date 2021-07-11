package com.yrd.reflect02;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 3. 获取成员方法们： Method[] getMethods() Method getMethod(String name, 类<?>...
 * parameterTypes)
 * 
 * Method[] getDeclaredMethods() Method getDeclaredMethod(String name, 类<?>...
 * parameterTypes)
 * 
 * * Method：方法对象
		* 执行方法：
			* Object invoke(Object obj, Object... args)  

		* 获取方法名称：
			* String getName:获取方法名
 * 
 * @ClassName:ReflectDemo03
 * @Description:
 *
 * @author:Yrd
 * @date:2021-6-14 12:07:29
 *
 */
public class ReflectDemo03 {

	public static void main(String[] args) throws Exception {
		// 获取Class对象
		Class<?> c1 = Class.forName("com.yrd.reflect02.Student");

		// 获取无参构造方法创建对象
		Constructor<?> constructor = c1.getConstructor();
		Object object = constructor.newInstance();
		
		Method[] declaredMethods = c1.getDeclaredMethods();
		for (Method method : declaredMethods) {
			System.out.println(method);
		}
		
		
		Method method02 = c1.getDeclaredMethod("method2", String.class);
		 method02.invoke(object, "ni hao world!");
		
		Method method01 = c1.getDeclaredMethod("method1");
		method01.invoke(object);
		
//		System.out.println(object);

	}

}

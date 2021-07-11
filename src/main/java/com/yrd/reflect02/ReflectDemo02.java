package com.yrd.reflect02;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 1. 获取成员变量们 Field[] getFields() ：获取所有public修饰的成员变量 
 * Field getField(String name) : 获取指定名称的 public修饰的成员变量
 * 
 * Field[] getDeclaredFields() 获取所有的成员变量，不考虑修饰符 
 * Field getDeclaredField(String name) 
 * Field：成员变量 操作： 
 * 1. 设置值 void set(Object obj, Object value) 
 * 2. 获取值 get(Object obj)
 * 
 * 3. 忽略访问权限修饰符的安全检查 setAccessible(true):暴力反射
 * 
 * @ClassName:ReflectDemo02
 * @Description:
 *
 * @author:Yrd
 * @date:2021-6-14 11:32:50
 *
 */
public class ReflectDemo02 {

	public static void main(String[] args)
			throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
		// 获取Class对象
		Class<?> c1 = Class.forName("com.yrd.reflect02.Student");

		// 获取无参构造方法创建对象
		Constructor<?> constructor = c1.getConstructor();
		Object object = constructor.newInstance();

		// 获取成员变量
		Field[] fields = c1.getFields();
		for (Field field : fields) {
			System.out.println(field);
		}
		System.out.println("=======================");

		Field[] declaredFields = c1.getDeclaredFields();
		for (Field field : declaredFields) {
			System.out.println(field);
		}
		System.out.println("=======================");

		// 获取单个变量
		Field addressField = c1.getDeclaredField("address");
		addressField.set(object, "音头");
		System.out.println(object);

		Field nameField = c1.getDeclaredField("name");
		;
		System.out.println(nameField.getGenericType().getTypeName().toString());
		nameField.setAccessible(true);
		nameField.set(object, "xiaoming");
		System.out.println(object);

	}

}

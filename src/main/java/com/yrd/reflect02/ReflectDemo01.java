package com.yrd.reflect02;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * .获取构造方法们 Constructor<?>[] getConstructors() Constructor<T>
 * getConstructor(类<?>... parameterTypes)
 * 
 * Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)
 * Constructor<?>[] getDeclaredConstructors()
 * 
 * Constructor:构造方法， 创建对象： T newInstance(Object... initargs)
 * 
 * .如果使用空参数构造方法创建对象，操作可以简化：Class对象的newInstance方法
 * 
 * @ClassName:ReflectDemo01
 * @Description:
 *
 * @author:Yrd
 * @date:2021-6-14 10:51:02
 *
 */
public class ReflectDemo01 {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 1. Class.forName("全类名")：将字节码文件加载进内存，返回Class对象
		// 获取Class对象
		Class<?> c1 = Class.forName("com.yrd.reflect02.Student");
		System.out.println(c1);
		System.out.println("==========================");

		// 获取所有public修饰的
		Constructor<?>[] constructors = c1.getConstructors();
		for (Constructor<?> constructor : constructors) {
			System.out.println(constructor);
		}
		System.out.println("==========================");

		// 获取所有的，不考虑修饰符
		Constructor<?>[] declaredConstructors = c1.getDeclaredConstructors();
		for (Constructor<?> constructor : declaredConstructors) {
			System.out.println(constructor);
		}
		System.out.println("==========================");

		// 单个构造方法
		Constructor<?> constructor1 = c1.getDeclaredConstructor(String.class);
		System.out.println(constructor1);

		// 暴力反射 ,可以反射私有构造方法
		constructor1.setAccessible(true);

		Object object = constructor1.newInstance("xiaoming");
		System.out.println(object);

	}

}

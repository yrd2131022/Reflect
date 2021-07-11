package com.yrd.reflect02;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * .通过配置文件运行类中的方法
 * @ClassName:ReflectDemo04
 * @Description:
 *
 * @author:Yrd
 * @date:2021-6-14 12:40:01
 *
 */
public class ReflectDemo04 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//加载数据
		Properties properties = new Properties();
		FileReader fr = new FileReader("./MyClass.txt");
		properties.load(fr);
		fr.close();
		
		String className = properties.getProperty("className");
		String methodName = properties.getProperty("methodName");
		
		//通过反射使用
		Class<?> c1 = Class.forName(className);
		
		Constructor<?> constructor = c1.getConstructor();
		Object object = constructor.newInstance();
		
		Method declaredMethod = c1.getDeclaredMethod(methodName, String.class);
		Object invoke = declaredMethod.invoke(object, "tiantian");
		System.out.println(invoke);

	}

}

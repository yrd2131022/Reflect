package com.yrd.reflect02;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * * 好处： 1. 可以在程序运行过程中，操作这些对象。 2. 可以解耦，提高程序的可扩展性。 获取Class对象的方式： 1.
 * Class.forName("全类名")：将字节码文件加载进内存，返回Class对象 多用于配置文件，将类名定义在配置文件中。读取文件，加载类 2.
 * 类名.class：通过类名的属性class获取 多用于参数的传递 3. 对象.getClass()：getClass()方法在Object类中定义着。
 * 多用于对象的获取字节码的方式
 * 
 * 结论： 同一个字节码文件(*.class)在一次程序运行过程中，只会被加载一次，不论通过哪一种方式获取的Class对象都是同一个。
 * 
 * @ClassName:ReflectDemo
 * @Description:
 *
 * @author:Yrd
 * @date:2021-6-14 10:22:29
 *
 */

public class ReflectDemo {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 1. Class.forName("全类名")：将字节码文件加载进内存，返回Class对象
		Class<?> c3 = Class.forName("com.yrd.reflect02.Student");
//		System.out.println(c3);

		// 2. 类名.class：通过类名的属性class获取
		Class<Student> c1 = Student.class;
//		System.out.println(c1);

		// 3. 对象.getClass()：getClass()方法在Object类中定义着
		Student s = new Student();
		Class<? extends Student> c2 = s.getClass();
//		System.out.println(c2);

//		System.out.println(c1 == c2);
//		System.out.println(c1 == c3);
//		System.out.println(c2 == c3);
//		System.out.println(c1.getSimpleName());

		ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<String, Object>();
		concurrentHashMap.put("name", "小明");
		concurrentHashMap.put("age", 13);
		concurrentHashMap.put("money", 78.5);
		System.out.println(concurrentHashMap);
//		Class<? extends Object> class1 = concurrentHashMap.get("age").getClass();
//		System.out.println(class1.getSimpleName());
//		Class<? extends Object> class2 = concurrentHashMap.get("name").getClass();
//		System.out.println(class2.getSimpleName());

//		Class<? extends Object> class3 = concurrentHashMap.get("money").getClass();
//		System.out.println(class3.getSimpleName());

//		Class<?> class4 = Integer.TYPE;
//		System.out.println(class4);
//		System.out.println(Integer.TYPE.getClass());
//		
		Person person = new Person();
		
		try {
			mapPopulateObject(person, concurrentHashMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(person);

	}

	// 包装类型class转换为基本类型class
//	Integer.class  ===> int.class

	public static Class<?> packingTypeToBasicType(Class<?> packingType) {

		String packingTypeName = packingType.getName();

		switch (packingTypeName) {
		case "java.lang.Integer":
			return Integer.TYPE;
		case "java.lang.Double":
			return Double.TYPE;
		case "java.lang.Float":
			return Float.TYPE;
		case "java.lang.Character":
			return Character.TYPE;
		case "java.lang.Boolean":
			return Boolean.TYPE;
		case "java.lang.Long":
			return Long.TYPE;
		case "java.lang.Byte":
			return Byte.TYPE;
		case "java.lang.Short":
			return Short.TYPE;

		}

		try {
			return Class.forName(packingTypeName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return packingType;
	}
	
	
	public static String toUpperString(String varString) {
		
		byte[] bytes = varString.getBytes();
		bytes[0] = (byte) ((char)bytes[0] - 'a'+'A');
		return new String(bytes);
	}

	// map 转换 类：将map赋值给person类
	public static void mapPopulateObject(Object bean, Map<String, Object> map) throws Exception {
		
		Class<? extends Object> cls = bean.getClass();
		
		Set<String> keySet = map.keySet();
		
		Class<?> basicType = null;

		for (String key : keySet) {
			
			String newKey = key.substring(0, 1).toUpperCase() + key.substring(1);
			
			if(cls.getDeclaredField(key).getGenericType().getTypeName() != 
					map.get(key).getClass().getName()	) {
				 basicType = packingTypeToBasicType(map.get(key).getClass());
			}else {
				 basicType=map.get(key).getClass();
			}
			
			Method declaredMethod = cls.getDeclaredMethod("set" + newKey, basicType);
			declaredMethod.invoke(bean, map.get(key));
		}

	}

}

/*
 * 反射中使用方法的反射，需要注意参数的class类型（封装类型class，基础类型class）
 */

class Person {
	private String name;
	private Integer age;
	private double money;

	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public Double getMoney() {
		return money;
	}



	public void setMoney(double money) {
		this.money = money;
	}



	@Override
	public String toString() {
		return "Person {\"name\"=\"" + name + "\", \"aget\"=\"" + age + "\", \"money\"=\"" + money + "\"}";
	}

}

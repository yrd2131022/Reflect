package com.yrd.reflect02;
/**
 * .//成员变量：一个私有，一个默认，一个公共
 * .//构造方法：一个私有，一个默认，两个公共
 * .//成员方法：一个私有，四个公共
 * @ClassName:Student
 * @Description:
 *
 * @author:Yrd
 * @date:2021-6-14 10:56:28
 *
 */
public class Student {
	//成员变量：一个私有，一个默认，一个公共
	private String name;
	int age;
	public String address;
	//构造方法：一个私有，一个默认，两个公共
	public Student() {
		
	}
	
	@SuppressWarnings("unused")
	private Student(String name) {
		this.name=name;
	}
	
	Student(String name,int age){
		this.name=name;
		this.age=age;
	}
	
	public Student(String name,int age,String address) {
		this.name=name;
		this.age=age;
		this.address=address;
	}
	
	//成员方法：一个私有，四个公共
	@SuppressWarnings("unused")
	private void function() {
		System.out.println("function");
	}
	
	public void method1() {
		System.out.println("show method1");
	}
	
	public int method2(String string) {
		System.out.println("method:"+string);
		return 22;
	}
	
	public String method3(String s,int i) {
		return s+",,"+i;
	}

	@Override
	public String toString() {
		return "Student {\"name\"=\"" + name + "\", \"age\"=\"" + age + "\", \"address\"=\"" + address + "\"}";
	}
	
	

}

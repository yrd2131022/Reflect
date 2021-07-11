package com.yrd.reflect01;

/**
 * ClassLoader 中的两个方法:
 *  1.static ClassLoader getSystemClassLoader():返回用于委派的系统类加载器
 *  2.ClassLoader getParent():返回父类加载器进行 委派
 * @ClassName:ClassLoaderDemo
 * @Description:
 *
 * @author:Yrd
 * @date:2021-6-14 10:10:12
 *
 */

public class ClassLoaderDemo {

	public static void main(String[] args) {
		// 1.static ClassLoader getSystemClassLoader():返回用于委派的系统类加载器
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println(systemClassLoader);//AppClassLoader
		
		//2.ClassLoader getParent():返回父类加载器进行 委派
		ClassLoader parent = systemClassLoader.getParent();
		System.out.println(parent);//ExtClassLoader
		//Bootstrap class loader
		ClassLoader parent2 = parent.getParent();
		System.out.println(parent2);//null
		
		

	}

}

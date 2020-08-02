package com.sincosmos.thinkjava.classloader;

class MyClassLoader extends ClassLoader {
	private String root;
	public MyClassLoader(String root){
		this.root = root;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException{
		return null;
	}
	
	private byte[] getClassData(String clazz) {
		return null;
	}
}

public class ParentClassLoader {
	public static void main(String[] args) {
		MyClassLoader myClassLoader = new MyClassLoader("");
		System.out.println(MyClassLoader.class.getClassLoader());
		System.out.println(myClassLoader.getParent());
		System.out.println(MyClassLoader.getSystemClassLoader());
		System.out.println(MyClassLoader.getSystemClassLoader().getParent());
		System.out.println(MyClassLoader.getSystemClassLoader().getParent().getParent());
	}
	
}

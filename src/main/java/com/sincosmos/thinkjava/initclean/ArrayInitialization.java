package com.sincosmos.thinkjava.initclean;

public class ArrayInitialization {
	//private String[] strArr = {"This", "is", "a", "test"};//Method 1
	//private String[] strArr = new String[]{"This", "is", "a", "test"};//Method 2
	
	private String[] strArr = null;//Method 3
	
	{
		strArr = new String[]{"This", "is", "a", "test"};//Method 3
	}
	
	
	ArrayInitialization(String arg){
		
		System.out.println("Constructor argument: "+  arg);
	}

	public String[] getStrArr() {
		return strArr;
	}	
	
	public void setStrArr(String[] strArr) {
		this.strArr = strArr;
	}
}

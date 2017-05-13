package com.sincosmos.thinkjava.initclean;

public class StaticInitilization {
	public static String defInit= "A string filed that is initialized at the point of definition!";
	public static String staticBlkInit;
	
	static {
		staticBlkInit = "A string filed that is initialized by the static block!";
	}
	
	public String instanceInit;
	
	{
		instanceInit = "A string filed that is initialized usding instance initialization!";
	}
	
	public static void staticStrInitedDemo(){
		System.out.println("defInit: " + defInit);
		System.out.println("staticBlkInit: " + staticBlkInit);
	}
	
	public void instanceInitDemo(){
		System.out.println("instanceInit: " + instanceInit);
	}
}

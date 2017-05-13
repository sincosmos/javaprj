package com.sincosmos.thinkjava.polymorphism;

public class SubClass extends Base{
	@Override
	public void baseMethod(){
		System.out.println("Override base method");
	}
	
	public void subMethod(){
		System.out.println("Sub method");
	}
}

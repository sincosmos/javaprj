package com.sincosmos.thinkjava.generics;

import java.lang.reflect.Method;

class Mime {
	public void sit(){
		System.out.println("Mime is pretending to sit");
	}
	
	@Override
	public String toString(){
		return "Mime";
	}
}

class SmartDog {
	public void speak(){
		System.out.println("Smart dog is speaking");
		sit();
	}
	
	private void sit(){
		System.out.println("Smart dog is sitting");
	}
	
	public SmartDog reproduce(){
		return new SmartDog();
	}
	
	@Override
	public String toString(){
		return "SmartDog";
	}
}

public class CommunicateReflectively {
	public static void perform(Object speaker){
		Class<?> spkr = speaker.getClass();
		try{
			try{
				Method speak = spkr.getMethod("speak");
				speak.invoke(speaker);
			}catch(NoSuchMethodException e){
				System.out.println(speaker + " can't speak");
			}
			try{
				Method sit = spkr.getMethod("sit");
				sit.invoke(speaker);
			}catch(NoSuchMethodException e){
				System.out.println(speaker + " can't sit");
			}
		}catch(Exception e){
			throw new RuntimeException(speaker.toString(), e);
		}
	}
}

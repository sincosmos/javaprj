package com.sincosmos.thinkjava.generics;

import java.util.ArrayList;

class Shape{
	public void rotate(){
		System.out.println("rotating");
	}
	
	public void resize(int size){
		System.out.println("resize " + size);
	}
}

class Square extends Shape {}


public class SeqMethodToApply<T> extends ArrayList<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeqMethodToApply(Class<? extends T> type, int size){
		try{
			for(int i=0; i<size; i++){
				add(type.newInstance());
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}

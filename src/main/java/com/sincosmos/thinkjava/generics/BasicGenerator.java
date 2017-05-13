package com.sincosmos.thinkjava.generics;

public class BasicGenerator<T> implements Generator<T> {
	private Class<T> type;
	
	public BasicGenerator(Class<T> type){
		this.type = type;
	}
	
	@Override
	public T next() {
		try{
			//Assume type is a public type
			return type.newInstance();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	//Produce a default generator given a type token:
	public static <T> Generator<T> create(Class<T> type){
		return new BasicGenerator<T>(type);
	}

}

package com.sincosmos.thinkjava.generics;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

class SimpleQueue<T> implements Iterable<T>{
	private LinkedList<T> storge = new LinkedList<T>();
	public void add(T t) {
		storge.offer(t);
	}
	public T get(){
		return storge.poll();
	}
	@Override
	public Iterator<T> iterator() {
		return storge.iterator();
	}
}

public class ApplyMethods {
	public static <T, S extends Iterable<? extends T>> void
	apply(S seq, Method func, Object... args){
		try{
			for(T t : seq){
				func.invoke(t, args);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static <T> void fill(Collection<T> collection, Class<? extends T> type, int size){
		for(int i=0; i<size; i++){
			try{
				collection.add(type.newInstance());
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
	}
}

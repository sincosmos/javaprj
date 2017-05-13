package com.sincosmos.thinkjava.arrays;

import com.sincosmos.thinkjava.generics.*;

public class CountingGenerator {
	public static class Boolean implements Generator<java.lang.Boolean> {
		private boolean value = false;
		
		@Override
		public java.lang.Boolean next(){
			value = !value;
			return value;
		}
	}
	
	public static class Integer implements Generator<java.lang.Integer>{
		private int value = 0;
		
		@Override
		public java.lang.Integer next() {
			// TODO Auto-generated method stub
			return value++;
		}
	}
	
	public static void printClasses(Class<?> surroundingClass) {
		for(Class<?> type : surroundingClass.getClasses()){
			System.out.print(type.getName() + ": ");
			System.out.print(type.getSimpleName() + ": ");
			try{
				Generator<?> g = (Generator<?>)type.newInstance();
				for(int i=0; i<10; ++i){
					System.out.printf(g.next() + " ");
				}
				System.out.println();
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
	}
}

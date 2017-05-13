package com.sincosmos.thinkjava.initclean;

public class ThisCallConstructor {
	private int cnt = 0;
	private String str = "initial value";
	
	ThisCallConstructor(int cnt){
		this.cnt = cnt;
		System.out.printf("Constructor cnt = %d%n", this.cnt);
	}
	
	ThisCallConstructor(String str){
		this.str = str;
		System.out.printf("Constructor str = %s%n", this.str);
	}
	
	ThisCallConstructor(int cnt, String str){
		this(cnt);
		this.str = str;
		System.out.println("Constructor cnt & str");
	}
	
	ThisCallConstructor(){
		this(47, "hello");
		System.out.println("default constructor");
	}
}

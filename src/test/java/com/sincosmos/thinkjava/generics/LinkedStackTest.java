package com.sincosmos.thinkjava.generics;

import org.junit.Before;
import org.junit.Test;

public class LinkedStackTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLinkedStack() {
		LinkedStack.genericStaticMethod("String for generic static method");
		
		LinkedStack<String> linkedStack = new LinkedStack<String>();
		
		for(String str : "This is a stack test!".split(" ")){
			linkedStack.push(str);
		}
		String str;
		while((str  = linkedStack.pop()) != null){
			System.out.println(str);
		}
		
		linkedStack.genericStaticMethod(Integer.valueOf(11));
	}
}

package com.sincosmos.thinkjava.initclean;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class ArrayInitializationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testArrayInit() {
		ArrayInitialization testArrayInit = new ArrayInitialization("This is another test!");
		for( String str : testArrayInit.getStrArr() ){
			System.out.print(str + " ");
		}
		System.out.println();
		System.out.println(Arrays.toString(testArrayInit.getStrArr()));
		
		Random rand = new Random(47); 
			
 		ArrayInitialization[] arrInitRef = new ArrayInitialization[rand.nextInt(10)];	
 		for(int i=0, len=arrInitRef.length; i<len; ++i){
 			arrInitRef[i] = new ArrayInitialization("ArrayInitializtion "+ i);
 		}
	}

}

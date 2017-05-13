package com.sincosmos.thinkjava.generics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sincosmos.thinkjava.polymorphism.SubClass;

public class BasicGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGenerator() {
		Generator <SubClass> gen = new BasicGenerator<>(SubClass.class);
		for(int i=0; i<5; ++i){
			gen.next().subMethod();
		}
	}

}

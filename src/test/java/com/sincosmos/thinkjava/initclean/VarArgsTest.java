package com.sincosmos.thinkjava.initclean;

import org.junit.Before;
import org.junit.Test;

import com.sincosmos.thinkjava.polymorphism.Base;
import com.sincosmos.thinkjava.polymorphism.SubClass;

public class VarArgsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPrintVarArgs() {
		new VarArgs().printVarArgs(new Base(), new SubClass());	
	}

}

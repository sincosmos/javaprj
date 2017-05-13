package com.sincosmos.thinkjava.generics;

import org.junit.Before;
import org.junit.Test;

public class CommunicateReflectivelyTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPerform() {
		CommunicateReflectively.perform(new SmartDog());
		
		CommunicateReflectively.perform(new Mime());
	}

}

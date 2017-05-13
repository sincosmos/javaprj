package com.sincosmos.thinkjava.initclean;

import org.junit.Before;
import org.junit.Test;

public class StaticInitilizationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testStrInitedDemo() {
		StaticInitilization.staticStrInitedDemo();
		new StaticInitilization().instanceInitDemo();
	}
}

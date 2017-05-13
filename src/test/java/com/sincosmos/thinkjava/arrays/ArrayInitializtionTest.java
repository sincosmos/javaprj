package com.sincosmos.thinkjava.arrays;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayInitializtionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSphere() {
		ArrayInitializtion arrayInit = new ArrayInitializtion();
		arrayInit.sphere(new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere()});
	}

}

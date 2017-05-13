package com.sincosmos.thinkjava.initclean;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FinalizeTerminationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		FinalizeTermination terminator = new FinalizeTermination(true);
		//Proper cleanup
		terminator.checkIn();
		//Drop the reference, forget to clean up
		new FinalizeTermination(true);
		//Force the garbage collection & finalization
		System.gc();
	}
}

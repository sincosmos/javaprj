package com.sincosmos.thinkjava.initclean;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InitStringToNullTest {
	
	private InitStringToNull initStr;

	@Before
	public void setUp() throws Exception {
		initStr = new InitStringToNull();
	}

	@Test
	public void testGetStrInitToNullDemo() {
		assertNull("should be null", initStr.getStrInitToNullDemo());
	}

}

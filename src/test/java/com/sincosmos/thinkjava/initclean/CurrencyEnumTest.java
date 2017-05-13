package com.sincosmos.thinkjava.initclean;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyEnumTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		for(CurrencyEnum elem : CurrencyEnum.values()){
			System.out.print(elem + ", ordinal: " + elem.ordinal() + ", ");
			switch(elem){
			case CNY:
				System.out.println("Currency code of China");
				break;
			case USD:
				System.out.println("Currency code of America");
				break;
			case KRW:
				System.out.println("Currency code of Korea");
				break;
			default:
				System.out.println("Currency code of other countries");
				break;
			}
		}
	}

}

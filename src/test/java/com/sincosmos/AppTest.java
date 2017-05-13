package com.sincosmos;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void evaluatesExpression(){
    	App app = new App();
    	int sum = app.evaluate("1+2+3");
    	assertEquals(6, sum);
    }
}

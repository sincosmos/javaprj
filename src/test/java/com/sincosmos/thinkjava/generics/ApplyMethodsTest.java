package com.sincosmos.thinkjava.generics;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ApplyMethodsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testApply() throws Exception{
		List<Shape> shapes = new ArrayList<>();
		for(int i=0; i<2; i++){
			shapes.add(new Shape());		
		}
		System.out.println("==========" + "PART 1" + "==========");
		ApplyMethods.apply(shapes, Shape.class.getMethod("rotate"));
		System.out.println("==========" + "PART 2" + "==========");
		ApplyMethods.apply(shapes, Shape.class.getMethod("resize", int.class), 3);
		
		List<Square> squares = new ArrayList<>();
		for(int i=0; i<2; i++){
			squares.add(new Square());		
		}
		System.out.println("==========" + "PART 3" + "==========");
		ApplyMethods.apply(squares, Shape.class.getMethod("rotate"));
		System.out.println("==========" + "PART 4" + "==========");
		ApplyMethods.apply(squares, Shape.class.getMethod("resize", int.class), 3);
		
		
		System.out.println("==========" + "PART 5" + "==========");
		ApplyMethods.apply(new SeqMethodToApply<Shape>(Shape.class, 2), Shape.class.getMethod("rotate"));
		System.out.println("==========" + "PART 6" + "==========");
		ApplyMethods.apply(new SeqMethodToApply<Square>(Square.class, 2), Square.class.getMethod("rotate"));	
	}

	@Test
	public void testFill(){
		//ApplyMethods.fill(new SimpleQueue<Shape> (), null, 0);
	}
}

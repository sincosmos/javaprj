package com.sincosmos.designpattern.factory.abstractfactory.factory;

public class AbstractFactoryTest {

	public static void main(String[] args) {
		AbstractFactory chnFactory = new ChnFactory();
		chnFactory.language("ÖÐÎÄ");
		chnFactory.createScene().describe();
		chnFactory.createRole().attack("º®±ùÕÆ");
		
		
		AbstractFactory engFactory = new EngFactory();
		engFactory.language("English");
		engFactory.createScene().describe();
		engFactory.createRole().attack("magic");
	}
}

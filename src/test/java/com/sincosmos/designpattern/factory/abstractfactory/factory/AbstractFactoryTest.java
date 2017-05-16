package com.sincosmos.designpattern.factory.abstractfactory.factory;

public class AbstractFactoryTest {

	public static void main(String[] args) {
		AbstractFactory chnFactory = new ChnFactory();
		chnFactory.language("����");
		chnFactory.createScene().describe();
		chnFactory.createRole().attack("������");
		
		
		AbstractFactory engFactory = new EngFactory();
		engFactory.language("English");
		engFactory.createScene().describe();
		engFactory.createRole().attack("magic");
	}
}

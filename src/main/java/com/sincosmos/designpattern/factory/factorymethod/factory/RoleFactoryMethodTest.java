package com.sincosmos.designpattern.factory.factorymethod.factory;

public class RoleFactoryMethodTest {
	public void roleLines(IFactoryMethod factory, String scene){
		factory.createRole().lines(scene);
	}
	
	public static void main(String[] args) {
		RoleFactoryMethodTest factoryTest = new RoleFactoryMethodTest();
		factoryTest.roleLines(new ChnRoleFactory(), "ºÍ NPC ½»Ì¸");
		factoryTest.roleLines(new EngRoleFactory(), "Having dinner with a lady");
	}
}

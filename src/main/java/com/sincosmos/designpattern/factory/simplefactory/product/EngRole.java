package com.sincosmos.designpattern.factory.simplefactory.product;

/*
 * Ӣ�Ľ�ɫ��
 */
public class EngRole implements Role {

	@Override
	public void lines(String scene) {
		System.out.println("English Role speak English lines in scene " + scene);
	}

	@Override
	public void attack(String skill) {
		System.out.println("English Role attack enemy with " + skill);
	}

}

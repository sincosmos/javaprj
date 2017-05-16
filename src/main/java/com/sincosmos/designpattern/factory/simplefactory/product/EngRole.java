package com.sincosmos.designpattern.factory.simplefactory.product;

/*
 * Ó¢ÎÄ½ÇÉ«Àà
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

package com.sincosmos.designpattern.factory.simplefactory.product;

/*
 * 中文角色类
 */
public class ChnRole implements Role {

	@Override
	public void lines(String scene) {
		System.out.println("中文角色在场景" + scene + "中说中文台词！" );
	}

	@Override
	public void attack(String skill) {
		System.out.println("中文角色使用" + skill + "进行攻击！" );
	}

}

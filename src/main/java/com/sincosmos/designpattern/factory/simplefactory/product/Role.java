package com.sincosmos.designpattern.factory.simplefactory.product;

/*
 * 角色标准接口
 */
public interface Role {
	//场景台词
	void lines(String scene);
	//释放技能攻击
	void attack(String skill);
}

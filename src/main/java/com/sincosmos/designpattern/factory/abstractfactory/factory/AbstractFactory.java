package com.sincosmos.designpattern.factory.abstractfactory.factory;

import com.sincosmos.designpattern.factory.abstractfactory.product.Scene;
import com.sincosmos.designpattern.factory.simplefactory.product.Role;


/*
 * 抽象工厂类
 */
public abstract class AbstractFactory {
	// 工厂的公有方法，显示语言
	public void language(String lang){
		System.out.println("Language: " + lang);
	}
	
	//生产角色，由子类工厂根据需要具体实现
	public abstract Role createRole();
	
	//生产场景，由子类工厂根据需要具体实现
	public abstract Scene createScene();
	
}
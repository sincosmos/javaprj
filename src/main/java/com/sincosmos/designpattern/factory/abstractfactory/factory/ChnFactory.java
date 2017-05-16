package com.sincosmos.designpattern.factory.abstractfactory.factory;

import com.sincosmos.designpattern.factory.abstractfactory.product.ChnScene;
import com.sincosmos.designpattern.factory.abstractfactory.product.Scene;
import com.sincosmos.designpattern.factory.simplefactory.product.ChnRole;
import com.sincosmos.designpattern.factory.simplefactory.product.Role;

/*
 * 中文画面工厂类，生产中文角色及场景
 */
public class ChnFactory extends AbstractFactory {

	@Override
	public Role createRole() {
		return new ChnRole();
	}

	@Override
	public Scene createScene() {
		return new ChnScene();
	}

}

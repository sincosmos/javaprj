package com.sincosmos.designpattern.factory.abstractfactory.factory;

import com.sincosmos.designpattern.factory.abstractfactory.product.EngScene;
import com.sincosmos.designpattern.factory.abstractfactory.product.Scene;
import com.sincosmos.designpattern.factory.simplefactory.product.EngRole;
import com.sincosmos.designpattern.factory.simplefactory.product.Role;


/*
 * 英文画面工厂类，生产英文角色及场景
 */
public class EngFactory extends AbstractFactory {

	@Override
	public Role createRole() {
		return new EngRole();
	}

	@Override
	public Scene createScene() {
		return new EngScene();
	}

}

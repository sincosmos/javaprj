package com.sincosmos.designpattern.factory.abstractfactory.factory;

import com.sincosmos.designpattern.factory.abstractfactory.product.EngScene;
import com.sincosmos.designpattern.factory.abstractfactory.product.Scene;
import com.sincosmos.designpattern.factory.simplefactory.product.EngRole;
import com.sincosmos.designpattern.factory.simplefactory.product.Role;


/*
 * Ӣ�Ļ��湤���࣬����Ӣ�Ľ�ɫ������
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

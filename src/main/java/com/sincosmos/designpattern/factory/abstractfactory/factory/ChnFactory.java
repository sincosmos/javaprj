package com.sincosmos.designpattern.factory.abstractfactory.factory;

import com.sincosmos.designpattern.factory.abstractfactory.product.ChnScene;
import com.sincosmos.designpattern.factory.abstractfactory.product.Scene;
import com.sincosmos.designpattern.factory.simplefactory.product.ChnRole;
import com.sincosmos.designpattern.factory.simplefactory.product.Role;

/*
 * ���Ļ��湤���࣬�������Ľ�ɫ������
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

package com.sincosmos.designpattern.factory.factorymethod.factory;

import com.sincosmos.designpattern.factory.simplefactory.product.EngRole;
import com.sincosmos.designpattern.factory.simplefactory.product.Role;


/*
 * Ӣ�Ľ�ɫ������
 */
public class EngRoleFactory implements IFactoryMethod {

	@Override
	public Role createRole() {
		return new EngRole();
	}

}

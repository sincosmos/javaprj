package com.sincosmos.designpattern.factory.factorymethod.factory;

import com.sincosmos.designpattern.factory.simplefactory.product.ChnRole;
import com.sincosmos.designpattern.factory.simplefactory.product.Role;


/*
 * ���Ľ�ɫ������
 */
public class ChnRoleFactory implements IFactoryMethod {

	@Override
	public Role createRole() {
		return new ChnRole();
	}

}

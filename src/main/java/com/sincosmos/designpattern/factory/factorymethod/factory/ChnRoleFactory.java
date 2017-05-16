package com.sincosmos.designpattern.factory.factorymethod.factory;

import com.sincosmos.designpattern.factory.simplefactory.product.ChnRole;
import com.sincosmos.designpattern.factory.simplefactory.product.Role;


/*
 * 中文角色工厂类
 */
public class ChnRoleFactory implements IFactoryMethod {

	@Override
	public Role createRole() {
		return new ChnRole();
	}

}

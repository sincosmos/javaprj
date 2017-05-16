package com.sincosmos.designpattern.factory.factorymethod.factory;

import com.sincosmos.designpattern.factory.simplefactory.product.EngRole;
import com.sincosmos.designpattern.factory.simplefactory.product.Role;


/*
 * 英文角色工厂类
 */
public class EngRoleFactory implements IFactoryMethod {

	@Override
	public Role createRole() {
		return new EngRole();
	}

}

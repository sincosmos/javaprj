package com.sincosmos.designpattern.factory.simplefactory.factory;

import com.sincosmos.designpattern.factory.simplefactory.product.*;

public class RoleFactory {
	public static Role createRole(String lang){
		if("chinese".equalsIgnoreCase(lang)){
			return new ChnRole();
		}else if("english".equalsIgnoreCase(lang)){
			return new EngRole();
		}
		return null;
	}
}

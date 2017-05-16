package com.sincosmos.designpattern.factory.beanfactory.simple;

import org.junit.Test;

import com.sincosmos.designpattern.factory.beanfactory.simple.BeanDefinition;
import com.sincosmos.designpattern.factory.beanfactory.simple.BeanFactory;
import com.sincosmos.designpattern.factory.beanfactory.simple.UserBean;

public class BeanFactoryTest {

	@Test
	public void test() {
		BeanFactory<UserBean> beanFactory = new BeanFactory<>();
		beanFactory.registerBeanDefinition("user", new BeanDefinition<UserBean>(new UserBean()));
		
		UserBean user = beanFactory.getBean("user");
		user.setName("John Smith");
		System.out.println(user.getName());
	}

}

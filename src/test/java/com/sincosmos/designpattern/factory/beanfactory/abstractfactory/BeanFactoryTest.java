package com.sincosmos.designpattern.factory.beanfactory.abstractfactory;

import org.junit.Test;

import com.sincosmos.designpattern.factory.beanfactory.simple.BeanDefinition;
import com.sincosmos.designpattern.factory.beanfactory.simple.UserBean;

public class BeanFactoryTest {

	@Test
	public void test() {
		BeanFactory<UserBean> beaFactory = new AutowireCapableBeanFactory<>();
		
		BeanDefinition<UserBean> beanDefinition = new BeanDefinition<>();
		beanDefinition.setBeanClassName("com.sincosmos.designpattern.factory.beanfactory.simple.UserBean");
		beaFactory.registerBeanDefinition("user", beanDefinition);
		
		UserBean user = beaFactory.getBean("user");
		user.setName("John Smith");
		System.out.println(user.getName());
	}

}

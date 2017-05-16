package com.sincosmos.designpattern.factory.beanfactory.abstractfactory;

import com.sincosmos.designpattern.factory.beanfactory.simple.BeanDefinition;

public interface BeanFactory<T> {
	void registerBeanDefinition(String name, BeanDefinition<T> beanDefinition);
	T getBean(String name);
}

package com.sincosmos.designpattern.factory.beanfactory.simple;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory<T> {
	private Map<String, BeanDefinition<T>> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition<T>>();
	
	public T getBean(String name){
		return beanDefinitionMap.get(name).getBean();
	}
	
	public void registerBeanDefinition(String name, BeanDefinition<T> beanDefinition){
		beanDefinitionMap.put(name, beanDefinition);
	}
}

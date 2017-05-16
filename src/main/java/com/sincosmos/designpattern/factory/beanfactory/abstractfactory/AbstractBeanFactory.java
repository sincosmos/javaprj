package com.sincosmos.designpattern.factory.beanfactory.abstractfactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sincosmos.designpattern.factory.beanfactory.simple.BeanDefinition;

public abstract class AbstractBeanFactory<T> implements BeanFactory<T> {
	private Map<String, BeanDefinition<T>> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition<T>>();
	
	@Override
	public T getBean(String name){
		return beanDefinitionMap.get(name).getBean();
	}
	
	@Override
	public void registerBeanDefinition(String name, BeanDefinition<T> beanDefinition){
		T bean = createBean(beanDefinition);
		beanDefinition.setBean(bean);
		beanDefinitionMap.put(name, beanDefinition);
	}
	
	protected abstract T createBean(BeanDefinition<T> beanDefinition);
}

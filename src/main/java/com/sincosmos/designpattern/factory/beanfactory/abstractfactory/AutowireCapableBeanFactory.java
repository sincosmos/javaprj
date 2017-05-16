package com.sincosmos.designpattern.factory.beanfactory.abstractfactory;

import com.sincosmos.designpattern.factory.beanfactory.simple.BeanDefinition;

public class AutowireCapableBeanFactory<T> extends AbstractBeanFactory<T> {

	@Override
	protected T createBean(BeanDefinition<T> beanDefinition) {
		try{
			T bean = beanDefinition.getBeanClass().newInstance();
			return bean;
		}catch(InstantiationException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}
		
		return null;
	}

}

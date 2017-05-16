package com.sincosmos.designpattern.factory.beanfactory.simple;

public class BeanDefinition<T> {
	private T bean;
	
	private Class<T> beanClass;
	private String beanClassName;
	
	public BeanDefinition(){
	}
	
	public BeanDefinition(T bean){
		this.bean = bean;
	}
	
	public T getBean(){
		return bean;
	}

	public void setBean(T bean) {
		this.bean = bean;
	}

	public Class<T> getBeanClass() {
		return beanClass;
	}
	
	public void setBeanClass(Class<T> beanClass){
		this.beanClass = beanClass;
	}
	
	public String getBeanClassName(){
		return beanClassName;
	}
	
	@SuppressWarnings("unchecked")
	public void setBeanClassName(String beanClassName){
		this.beanClassName = beanClassName;
		try{
			this.beanClass = (Class<T>) Class.forName(beanClassName);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}

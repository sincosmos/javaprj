package com.sincosmos.designpattern.factory.abstractfactory.factory;

import com.sincosmos.designpattern.factory.abstractfactory.product.Scene;
import com.sincosmos.designpattern.factory.simplefactory.product.Role;


/*
 * ���󹤳���
 */
public abstract class AbstractFactory {
	// �����Ĺ��з�������ʾ����
	public void language(String lang){
		System.out.println("Language: " + lang);
	}
	
	//������ɫ�������๤��������Ҫ����ʵ��
	public abstract Role createRole();
	
	//���������������๤��������Ҫ����ʵ��
	public abstract Scene createScene();
	
}
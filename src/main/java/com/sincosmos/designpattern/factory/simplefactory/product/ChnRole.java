package com.sincosmos.designpattern.factory.simplefactory.product;

/*
 * ���Ľ�ɫ��
 */
public class ChnRole implements Role {

	@Override
	public void lines(String scene) {
		System.out.println("���Ľ�ɫ�ڳ���" + scene + "��˵����̨�ʣ�" );
	}

	@Override
	public void attack(String skill) {
		System.out.println("���Ľ�ɫʹ��" + skill + "���й�����" );
	}

}

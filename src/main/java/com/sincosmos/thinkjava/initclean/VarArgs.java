package com.sincosmos.thinkjava.initclean;

import com.sincosmos.thinkjava.polymorphism.*;
public class VarArgs {
	public void printVarArgs(Base... bases){
		for(Base base : bases){
			System.out.println(base.getClass() + " " + base.getClass().getSimpleName());
			base.baseMethod();
		}
	}
}

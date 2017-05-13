package com.sincosmos.thinkjava.initclean;

public class FinalizeTermination{
	boolean checkedOut = false;
	FinalizeTermination(boolean checkOut){
		checkedOut = checkOut;
	}
	
	void checkIn(){
		checkedOut = false;
	}
	
	protected void finalize(){
		if(checkedOut){
			System.out.println("Error: checked out");
			//super.finalize();
		}
	}
}
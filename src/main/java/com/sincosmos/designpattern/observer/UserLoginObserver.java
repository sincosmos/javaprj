package com.sincosmos.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

public class UserLoginObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		if( o instanceof UserLoginObservable) {
			UserLoginObservable loginUser = (UserLoginObservable) o;
			String loginContext = loginUser.getCurrentUser();
			System.out.println("Observer noted: " + loginContext);
			loginUser.removeCurrentUser();
		}
	}

}

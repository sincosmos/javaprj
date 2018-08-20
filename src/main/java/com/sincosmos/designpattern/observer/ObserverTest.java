package com.sincosmos.designpattern.observer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObserverTest {
	public ObserverTest() {
	}
	
	public static void main(String[] args) {
		UserLoginObservable observable = new UserLoginObservable();
		UserLoginObserver observer = new UserLoginObserver();
		observable.addObserver(observer);
		
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0; i<50; ++i) {
			exec.execute(new UserLogin(observable));
		}
		
	}
}

class UserLogin implements Runnable{
	private static int cnt = 0;
	private final int id = cnt++;
	private UserLoginObservable observable;
	public UserLogin(UserLoginObservable observable) {
		this.observable = observable;
	}
	
	@Override
	public void run() {
		observable.addLoginUser("user" + id, "this is user " + id);
		observable.removeCurrentUser();
	}
}
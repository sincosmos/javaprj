package com.sincosmos.designpattern.observer;

import java.util.Observable;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserLoginObservable extends Observable {
	private Set<String> onsiteUsers = ConcurrentHashMap.newKeySet();
	
	private ThreadLocal<String> currentUser = new ThreadLocal<>();
	
	public boolean isLogin(String user) {
		return this.onsiteUsers.contains(user);
	}
	
	/**
	 * 用户登录后，将用户加入已登录用户表，缓存用户登录信息
	 * 通知用户登录观察者，便于其执行进一步操作
	 * 
	 * @param user
	 * @param loginContext
	 */
	public void addLoginUser(String user, String loginContext) {
		this.onsiteUsers.add(user);
		currentUser.set(loginContext);
		setChanged();
		notifyObservers();
	}
	
	public void removeLoginUser(String user) {
		this.onsiteUsers.remove(user);
	}
	
	public String getCurrentUser() {
		return currentUser.get();
	}
	
	/**
	 * 使用完成后，清除 ThreadLocal 中的 LoginContext 对象
	 */
	public void removeCurrentUser() {
		currentUser.remove();
	}
}

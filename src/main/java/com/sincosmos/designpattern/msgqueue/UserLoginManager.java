package com.sincosmos.designpattern.msgqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.security.auth.login.LoginContext;

public class UserLoginManager {
	private ConcurrentHashMap<String, LoginContext> onsiteUsers
		= new ConcurrentHashMap<>();
	
	private BlockingQueue<LoginContext> userJustLogin 
		= new LinkedBlockingQueue<>();
	
	public boolean isLogin(String user) {
		return this.onsiteUsers.containsKey(user);
	}
	
	public void addLoginUser(String user, LoginContext loginContext) {
		this.onsiteUsers.put(user, loginContext);
		userJustLogin.add(loginContext);
	}
	
	public void removeLoginUser(String user) {
		this.onsiteUsers.remove(user);
	}
	
	public LoginContext getUserJustLogin() throws InterruptedException {
		return this.userJustLogin.take();
	}
}

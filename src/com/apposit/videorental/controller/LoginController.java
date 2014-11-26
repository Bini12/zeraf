package com.apposit.videorental.controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.apposit.videorental.interceptor.AuthInterceptor;
import com.apposit.videorental.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class LoginController extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	private User user = null;
	private Map<String, Object> session = null;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		if(user == null)
			return ActionReturns.RETURN_INPUT;
		
		session.put(AuthInterceptor.USER_SESSION_KEY, user);
		
		return ActionReturns.RETURN_SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void validate() {
		if(user == null)
			return;
		
		if(user.getName().length() == 0)
			addFieldError("user.name", "Please fill your name");
		
	}
	
	

}

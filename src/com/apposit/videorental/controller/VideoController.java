package com.apposit.videorental.controller;

import com.apposit.videorental.interceptor.UserAware;
import com.apposit.videorental.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class VideoController extends ActionSupport implements UserAware {

	private static final long serialVersionUID = 1L;
	private User user = null;
	
	
	@Override
	public String execute() throws Exception {
		System.out.println(user);
		return ActionReturns.RETURN_SUCCESS;
		
	}
	
	
	public String videoDetail() throws Exception {
		return ActionReturns.RETURN_SUCCESS;
	}


	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
}

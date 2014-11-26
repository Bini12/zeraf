package com.apposit.videorental.interceptor;

import com.apposit.videorental.model.User;

public interface UserAware {

	public User getUser();
	public void setUser(User user);
}

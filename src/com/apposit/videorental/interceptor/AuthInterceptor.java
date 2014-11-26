package com.apposit.videorental.interceptor;

import java.util.Map;

import com.apposit.videorental.controller.ActionReturns;
import com.apposit.videorental.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthInterceptor implements Interceptor {
	public static final String USER_SESSION_KEY = "user";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Authentication interceptor invoked!!!!");
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		User user = (User) session.get(USER_SESSION_KEY);
		
		if(user == null)
			return ActionReturns.RETURN_LOGIN;
		else {
			Action action = (Action) invocation.getAction();
			if(action instanceof UserAware) {
				((UserAware) action).setUser(user);
			}
		}
		
		return invocation.invoke();
	}

}

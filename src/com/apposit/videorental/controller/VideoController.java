package com.apposit.videorental.controller;

import com.opensymphony.xwork2.ActionSupport;

public class VideoController extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		return ActionReturns.RETURN_SUCCESS;
	}
	
	
	public String videoDetail() throws Exception {
		return ActionReturns.RETURN_SUCCESS;
	}
	
}

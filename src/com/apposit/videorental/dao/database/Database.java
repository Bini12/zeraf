package com.apposit.videorental.dao.database;

public interface Database {
	public void connect();
	public void execute(String query);
	public void disconnect();
	
}

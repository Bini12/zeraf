package com.apposit.videorental.dao.database;

public class MySql implements Database {
	
	//hide constructor
	private MySql(){};
	
	@Override
	public void connect() {
		// TODO Auto-generated method stub
		System.out.println("Mysql connection in progress....");
	}

	@Override
	public void execute(String query) {
		// TODO Auto-generated method stub
		System.out.println("MySql Excecuting qurey");
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		System.out.println("Closing connection to mysql");
	}

	
	public static Database getNewInstance() {
		return new MySql();
	}

}

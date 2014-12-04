package com.apposit.videorental.dao.database.MySql;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.apposit.videorental.dao.annotation.Column;
import com.apposit.videorental.dao.annotation.Ref;
import com.apposit.videorental.dao.annotation.Table;
import com.apposit.videorental.dao.database.Database;

import com.apposit.videorental.dao.database.command.CommandStrategy;
import com.apposit.videorental.dao.database.command.InsertCommandStrategy;
import com.apposit.videorental.dao.database.command.SelectCommandStrategy;
import com.apposit.videorental.dao.database.command.UpdateCommandStrategy;
import com.apposit.videorental.dao.database.criteria.Criteria;
import com.apposit.videorental.dao.database.Restriction;
import com.apposit.videorental.dao.database.command.DeleteCommand;
import com.apposit.videorental.dao.database.command.InsertCommand;
import com.apposit.videorental.dao.database.command.UpdateCommand;
import com.apposit.videorental.dao.database.criteria.Criteria;
import com.apposit.videorental.dao.database.criteria.SingleCriteria;

public class MySqlDB implements Database {

	// hide constructor
	private MySqlDB() throws SQLException, ClassNotFoundException {
		this.connect();
	}

	private Connection connection = null;

	@Override
	public void connect() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/video_rental?user=video_rental&password=v!d9@55");

		System.out.println("Mysql connection in progress....");
	}


	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		System.out.println("Closing connection to mysql");
	}

	public static Database getNewInstance() throws SQLException,
			ClassNotFoundException {
		return new MySqlDB();
	}

	private PreparedStatement getPreparedStatment(CommandStrategy strategy)
			throws Exception {
		String sql = strategy.getPreparedStatment();
		System.out.println(sql);
		return connection.prepareStatement(sql);
	}

	
	@Override
	public List find(Criteria criteriaObject, Class modelClass) throws SQLException, Exception {
		System.out.println("Inside Db:select method");
		List<Object> resultList = new ArrayList<>();

		SelectCommandStrategy selectStrategy = new SelectCommandStrategy(criteriaObject, modelClass);
		
		ResultSet resultSet = getPreparedStatment(selectStrategy).executeQuery();

		while (resultSet.next()) {
			Object modelObj = selectStrategy.getModelObject(resultSet, modelClass);
			if (modelObj != null)
				resultList.add(modelObj);
		}
		
		return resultList;
	}


	@Override
	public int update(Object modelObj) throws SQLException, Exception {
		UpdateCommandStrategy updateStrategy = new UpdateCommandStrategy(modelObj);
		return getPreparedStatment(updateStrategy).executeUpdate();
	}

	@Override
	public int insert(Object modelObj) throws SQLException, Exception {
		InsertCommandStrategy insertStrategy = new InsertCommandStrategy(
				modelObj);
		return getPreparedStatment(insertStrategy).executeUpdate();

	}
	
	
	public int delete(Criteria deleteParams) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

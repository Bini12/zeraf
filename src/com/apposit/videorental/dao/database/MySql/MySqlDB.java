package com.apposit.videorental.dao.database.MySql;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.apposit.videorental.dao.annotation.Column;
import com.apposit.videorental.dao.annotation.Ref;
import com.apposit.videorental.dao.database.Database;
import com.apposit.videorental.dao.database.QueryStrategy;
import com.apposit.videorental.dao.database.queryparams.*;
import com.apposit.videorental.model.Video;

import freemarker.ext.util.ModelCache;

public class MySqlDB implements Database {

	// hide constructor
	private MySqlDB() throws SQLException, ClassNotFoundException {
		this.connect();
	}

	private Connection connection = null;

	private static QueryStrategy selectStrategy = new MySqlSelectStrategy();

	@Override
	public void connect() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/video_rental?user=video_rental&password=v!d9@55");

		System.out.println("Mysql connection in progress....");
	}

	private ResultSet execute(String query) throws SQLException {
		System.out.println("MySql Excecuting qurey: \n" + query);

		PreparedStatement preparedStatment = connection.prepareStatement(query);
		return preparedStatment.executeQuery();
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

	@Override
	public boolean insert(InsertQueryParams insertParams) {

		return false;
	}

	@Override
	public List select(SelectQueryParams selectParams, Class modelClass)
			throws SQLException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException {
		System.out.println("Inside Db:select method");
		List<Object> resultList = new ArrayList<>();

		ResultSet resultSet = execute(selectStrategy.execute(selectParams));

		while (resultSet.next()) {

			resultList.add(getModelObject(resultSet, modelClass));
		}
		return resultList;
	}

	/**
	 * Accepts new instance of model object and returns object params populated
	 * accordingly!!
	 * 
	 * @param resultSet
	 * @param modelClass
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	private Object getModelObject(ResultSet resultSet, Class modelClass)
			throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException, SQLException, ClassNotFoundException {
		Object model = modelClass.newInstance();

		Field[] fields = modelClass.getDeclaredFields();

		for (Field f : fields) {
			Annotation[] fieldAnnotations = f.getAnnotations();
			// System.out.println("------FIELD------\n"+f.getName());
			for (Annotation annot : fieldAnnotations) {
				setProperty(resultSet, model, f, annot);
				// System.out.println(annot);
			}
		}

		return model;
	}

	/**
	 * maps Object to result set based on annotation info. it's not recursive!!
	 * 
	 * @param resultSet
	 * @param bean
	 * @param field
	 * @param annot
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	private void setProperty(ResultSet resultSet, Object bean, Field field,
			Annotation annot) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException, InstantiationException, ClassNotFoundException {

		// JAVA BEANS CONVENTIONAL SETTER METHOD NAME
		String setterMethodName = "set"
				+ field.getName().substring(0, 1).toUpperCase()
				+ field.getName().substring(1);

		Method setterMethod = bean.getClass().getMethod(setterMethodName,
				field.getType());
		Column colAnnot = null;
		Ref refAnnot = null;

		if (annot instanceof Column) {
			colAnnot = (Column) annot;
		} else if (annot instanceof Ref) {
			refAnnot = (Ref) annot;
		}

		if (colAnnot != null) {
			if (field.getType() == String.class) {
				setterMethod
						.invoke(bean, resultSet.getString(colAnnot.value()));
			} else if (field.getType() == int.class) {
				setterMethod.invoke(bean, resultSet.getInt(colAnnot.value()));
			} else if (field.getType() == double.class) {
				setterMethod
						.invoke(bean, resultSet.getDouble(colAnnot.value()));
			}
		} else if (refAnnot != null) {
			SelectQueryParams selectParams = new SelectQueryParams();
			selectParams.setTable("videos");
			List refObects = select(selectParams, field.getType());
			if(field.getType() == List.class) {
				setterMethod.invoke(bean, refObects);
			} else {
				setterMethod.invoke(bean, refObects.get(0));
			}
		}

	}

	@Override
	public boolean update(UpdateQueryParams updateParams) {
		return false;
	}

	@Override
	public boolean delete(DeleteQueryParams deleteParams) {
		return false;
	}

}

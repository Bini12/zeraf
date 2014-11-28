package com.apposit.videorental.dao.database;

import java.util.List;

import com.apposit.videorental.dao.database.queryparams.*;

public interface Database {
	public void connect() throws Exception;
	public void disconnect() throws Exception;

	public List select(SelectQueryParams selectParams, Class<?> modelClass) throws Exception;
	public boolean insert(InsertQueryParams insertParams)  throws Exception;
	public boolean update(UpdateQueryParams updateParams)  throws Exception;
	public boolean delete(DeleteQueryParams deleteParams)  throws Exception;
}

package com.apposit.videorental.dao.database;

import java.util.List;

import com.apposit.videorental.dao.database.criteria.Criteria;

public interface Database {
	public void connect() throws Exception;
	public void disconnect() throws Exception;


	public List find(Criteria criteriaObject, Class modelClass) throws Exception;
	public int insert(Object modelObj)  throws Exception;
	public int update(Object modelObj)  throws Exception;
	public int delete(Criteria deleteParams)  throws Exception;

}

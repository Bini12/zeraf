package com.apposit.videorental.dao.database.MySql;

import com.apposit.videorental.dao.database.AbstractSelectStrategy;
import com.apposit.videorental.dao.database.queryparams.Params;
import com.apposit.videorental.dao.database.queryparams.SelectQueryParams;


//package private strategy
class MySqlSelectStrategy extends AbstractSelectStrategy {

	@Override
	protected String getQueryString(SelectQueryParams params) {
		// TODO Auto-generated method stub
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("SELECT ");
		
		
		if(params.getSelectFields() != null) {
			//append 
		} else {
			queryBuilder.append(" * ");
		}
		
		queryBuilder.append(" FROM " + params.getTable());
		
		
		
		queryBuilder.append(";");
		return queryBuilder.toString();
	}

}

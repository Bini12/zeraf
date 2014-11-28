package com.apposit.videorental.dao.database;

import com.apposit.videorental.dao.database.queryparams.Params;
import com.apposit.videorental.dao.database.queryparams.SelectQueryParams;

public abstract class AbstractSelectStrategy implements QueryStrategy {

	@Override
	public String execute(Params params) {
		// TODO Auto-generated method stub
		if(params instanceof SelectQueryParams)
			return getQueryString((SelectQueryParams) params);
		return null;
	}

	
	protected abstract String getQueryString(SelectQueryParams params);
}

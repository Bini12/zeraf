package com.apposit.videorental.dao.database;

import com.apposit.videorental.dao.database.queryparams.Params;

public interface QueryStrategy {
	
	String execute(Params params);

}

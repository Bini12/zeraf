package com.apposit.videorental.dao.database.clause;

public interface Expression {
	void setExpression(String exp);
	String getExpression();
	boolean validate(String exp);

}

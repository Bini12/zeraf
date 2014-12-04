package com.apposit.videorental.dao.database.clause;

public abstract class AbstractClause implements Expression{

	protected String expression;

	
	public AbstractClause(String exp) {
		this.expression = exp;
	}
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	
}

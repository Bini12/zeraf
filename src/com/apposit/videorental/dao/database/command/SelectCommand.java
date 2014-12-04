package com.apposit.videorental.dao.database.command;

import com.apposit.videorental.dao.database.clause.*;


public final class SelectCommand {
	private String selectExp = null;
	
	private Expression whereClause = null;
	private Expression fromClause = null;
	private Expression orderByClause = null;
	
	public SelectCommand() {}
	
	public SelectCommand(String selectExp) {
		this.selectExp = selectExp;
	}
	
	public Expression getWhereClause() {
		return whereClause;
	}
	public SelectCommand setWhereClause(Expression whereClause) {
		this.whereClause = whereClause;
		return this;
	}
	
	
	public Expression getFromClause() {
		return fromClause;
	}
	public SelectCommand setFromClause(Expression fromClause) {
		this.fromClause = fromClause;
		return this;
	}
	
	
	public Expression getOrderByClause() {
		return orderByClause;
	}
	public SelectCommand setOrderByClause(Expression orderByClause) {
		this.orderByClause = orderByClause;
		return this;
	}

	public String getSelectExp() {
		return selectExp;
	}

	public void setSelectExp(String selectExp) {
		this.selectExp = selectExp;
	}
	

}

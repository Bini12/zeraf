package com.apposit.videorental.dao.database.clause;


class WhereClause extends AbstractClause{

	public WhereClause(String exp) {
		super(exp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate(String exp) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

class OrderByClause extends AbstractClause{

	public OrderByClause(String exp) {
		super(exp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate(String exp) {
		// TODO Auto-generated method stub
		return false;
	}


}

class JoinClause  extends AbstractClause {

	public JoinClause(String exp) {
		super(exp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate(String exp) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

class FromClause  extends AbstractClause{
	
	public FromClause(String exp) {
		super(exp);
		// TODO Auto-generated constructor stub
	}

	private JoinClause join;

	public JoinClause getJoin() {
		return join;
	}

	public void setJoin(JoinClause join) {
		this.join = join;
	}

	@Override
	public boolean validate(String exp) {
		// TODO Auto-generated method stub
		return false;
	}
	
}



public class ClauseFactory {

	public static Expression where(String exp) {
		return new WhereClause(exp);
	}
	
	public static Expression orderByClause(String exp) {
		return new OrderByClause(exp);
	}
	
	public static Expression joinClause(String exp) {
		return new JoinClause(exp);
	}
	
	public static Expression fromClause(String exp) {
		return new FromClause(exp);
	}
}

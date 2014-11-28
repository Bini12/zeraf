package com.apposit.videorental.dao.database.queryparams;

import java.util.List;

class ConditionComposite {
	private ConditionComposite nestedConditions;
	private String key;
	private String value;
	
	public ConditionComposite getNestedConditions() {
		return nestedConditions;
	}
	public void setNestedConditions(ConditionComposite nestedConditions) {
		this.nestedConditions = nestedConditions;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}

public final class SelectQueryParams implements Params{
	private String table;
	private List<String> selectFields = null;
	private ConditionComposite conditionComposite = null;
	
	public String getTable() {
		return table;
	}
	public SelectQueryParams setTable(String table) {
		this.table = table;
		return this;
	}
	@Override
	public String getTable(String tablename) {
		// TODO Auto-generated method stub
		return null;
	}
	public ConditionComposite getConditionComposite() {
		return conditionComposite;
	}
	public void setConditionComposite(ConditionComposite conditionComposite) {
		this.conditionComposite = conditionComposite;
	}
	public List<String> getSelectFields() {
		return selectFields;
	}
	public void setSelectFields(List<String> fields) {
		this.selectFields = fields;
	}
	
	
	

}

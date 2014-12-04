package com.apposit.videorental.dao.database.command;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.apposit.videorental.dao.annotation.Column;
import com.apposit.videorental.dao.annotation.Id;
import com.apposit.videorental.dao.annotation.Table;

public class UpdateCommandStrategy implements CommandStrategy {
	private Object modelObj = null;
	
	
	public UpdateCommandStrategy(Object modelObject) {
		this.modelObj = modelObject;
	}
	
	public void setModelObj(Object modelObj) {
		this.modelObj = modelObj;
	}
	
	@Override
	public String getPreparedStatment() throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sqlBilder = new StringBuilder();
		Class modelClass = modelObj.getClass();
		
		sqlBilder.append("UPDATE ");
		Table tableAnnot = (Table) modelClass.getAnnotation(Table.class);
		if (tableAnnot == null)
			throw new SQLException("Model not associated with table with @Table");
		
		sqlBilder.append(tableAnnot.name());
		
		sqlBilder.append(" SET ");
		
		Field[] fields = modelClass.getDeclaredFields();
		for(Field f: fields) {
			f.setAccessible(true);
			char q = ' ';
			if(f.getType() == String.class)
				q='\'';
			
			Column colAnnot = (Column) f.getAnnotation(Column.class);
			Id idAnnot = (Id) f.getAnnotation(Id.class);
			
			if(colAnnot != null && f.get(modelObj) != null && idAnnot == null) {
				String fieldVal = String.valueOf(f.get(modelObj));
				sqlBilder.append(colAnnot.value() + "="+ q + fieldVal + q);
			}
		}
		
		
		sqlBilder.append(" WHERE ");
		
		
		
		sqlBilder.append(" ;");
		return null;
	}

}

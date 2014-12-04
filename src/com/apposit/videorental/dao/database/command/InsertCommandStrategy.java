package com.apposit.videorental.dao.database.command;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.apposit.videorental.dao.annotation.Column;
import com.apposit.videorental.dao.annotation.Table;

public final class InsertCommandStrategy implements CommandStrategy {

	private Object modelObj = null;

	public InsertCommandStrategy(Object modelObj) {
		this.modelObj = modelObj;
	}

	public void setModelObj(Object modelObj) {
		this.modelObj = modelObj;
	}
	
	@Override
	public String getPreparedStatment() throws IllegalArgumentException, IllegalAccessException, SQLException {
		StringBuilder sqlBilder = new StringBuilder();
		Class modelClass = modelObj.getClass();
		
		sqlBilder.append("INSERT INTO ");
		Table tableAnnot = (Table) modelClass.getAnnotation(Table.class);
		if (tableAnnot == null)
			throw new SQLException("Model not associated with table with @Table");
		
		sqlBilder.append(tableAnnot.name());
		
		StringBuilder valsBuilder = new StringBuilder();
		
		Field[] fields = modelClass.getDeclaredFields();
		List<String> keys = new ArrayList<String>();
		for(Field f: fields) {
			f.setAccessible(true);
			char q = ' ';
			if(f.getType() == String.class)
				q='\'';
			
			Column colAnnot = (Column) f.getAnnotation(Column.class);
			if(colAnnot != null && f.get(modelObj) != null) {
				String fieldVal = String.valueOf(f.get(modelObj));
				keys.add(colAnnot.value());
				
				valsBuilder.append(q+fieldVal+q + ", ");
			}
		}
		
		//remove the final comma
		valsBuilder.delete(valsBuilder.length()-3, valsBuilder.length());
		
		
		sqlBilder.append(implode(keys));

		sqlBilder.append(" VALUES ");
		
		sqlBilder.append(" (" + valsBuilder + ");");
		
		
		return sqlBilder.toString();
	}
	
	private String implode(Collection<String> set) {
		StringBuilder builder = new StringBuilder(" (");
		
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String key = it.next();
			builder.append(key);
			
			if(it.hasNext())
				builder.append(",");
		}
		
		builder.append(")");
		return builder.toString();
		
	}

}

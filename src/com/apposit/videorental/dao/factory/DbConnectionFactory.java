package com.apposit.videorental.dao.factory;

import java.util.Hashtable;

import com.apposit.videorental.dao.database.Database;
import com.apposit.videorental.dao.database.MySql;

public class DbConnectionFactory {

	public enum DBTypes {
		MYSQL, SQLITE, MSSQL;
	}

	private static DbConnectionFactory instance = null;
	
	//Hashtable for trade safety
	private static Hashtable<DBTypes, Database> dbPool = new Hashtable<DBTypes, Database>();

	public DbConnectionFactory getInstance() {
		if (instance == null)
			instance = new DbConnectionFactory();
		return instance;
	}

	
	/**
	 * Because creating and estabilishing Database connections is typically expensive
	 * task we should manage a connection pool using flyweight pattern
	 * if type of database connection is already estabilished we return the reference 
	 * without the cost of creating a new one B-)///>
	 * @param type
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Database getDatabase(DBTypes type) throws IllegalArgumentException {
		Database db = dbPool.get(type);
		
		if(db == null) {
			switch(type) {
			case MYSQL:
				db = MySql.getNewInstance();
				break;
			default:
				throw new IllegalArgumentException("Unsupported database type");
			}
			
			//put the connection to dbpool
			dbPool.put(type, db);
		}
		
		return db;
	}

}

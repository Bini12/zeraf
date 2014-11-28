package com.apposit.videorental.dao.factory;

import java.sql.SQLException;
import java.util.Hashtable;

import com.apposit.videorental.dao.database.Database;
import com.apposit.videorental.dao.database.MySql.MySqlDB;

public class DbConnectionFactory {

	public enum DBTypes {
		MYSQL, SQLITE, MSSQL;
	}

//	private static DbConnectionFactory instance = null;
	
	//Hashtable for trade safety
	private static Hashtable<DBTypes, Database> dbPool = new Hashtable<DBTypes, Database>();

	
	/**
	 * Because creating and estabilishing Database connections is typically expensive
	 * operation we should reuse connection pool using flyweight pattern
	 * if type of database connection is already estabilished we return the reference 
	 * without the cost of creating a new one B-)///>
	 * @param type
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Database getDatabase(DBTypes type) throws IllegalArgumentException, SQLException, ClassNotFoundException {
		Database db = dbPool.get(type);
		
		if(db == null) {
			switch(type) {
			case MYSQL:
				db = MySqlDB.getNewInstance();
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

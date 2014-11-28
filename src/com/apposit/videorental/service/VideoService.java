package com.apposit.videorental.service;

import java.util.List;

import static com.apposit.videorental.dao.factory.DbConnectionFactory.*;

import com.apposit.videorental.dao.database.Database;
import com.apposit.videorental.dao.database.queryparams.SelectQueryParams;
import com.apposit.videorental.dao.factory.DbConnectionFactory;
import com.apposit.videorental.dao.factory.DbConnectionFactory.DBTypes;
import com.apposit.videorental.model.Video;
import com.opensymphony.xwork2.inject.Inject;

public class VideoService {
	
	@Inject
	private DbConnectionFactory dbConnectionFactory;

	public DbConnectionFactory getDbConnectionFactory() {
		return dbConnectionFactory;
	}

	public void setDbConnectionFactory(DbConnectionFactory dbConnectionFactory) {
		this.dbConnectionFactory = dbConnectionFactory;
	}
	
	public void save(Video video) {
		System.out.println("Adding video: " + video + " | " + dbConnectionFactory);
	}
	
	public List getAll() throws Exception {
		System.out.println("Fetching all video:  with " + dbConnectionFactory);
		Database db = dbConnectionFactory.getDatabase(DBTypes.MYSQL);
		SelectQueryParams selectParams = new SelectQueryParams();
		selectParams.setTable("videos");
		List videos = db.select(selectParams, Video.class);
		
		for(Object obj: videos) {
			if(obj instanceof Video) {
				Video vidObj = (Video) obj;
				System.out.println(vidObj);
				if(vidObj.getGenre() != null)
					System.out.println(vidObj.toString());
			}
		}
		
		return null;
	}

}

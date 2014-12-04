package com.apposit.videorental.service;

import java.sql.SQLException;
import java.util.List;

import static com.apposit.videorental.dao.factory.DbConnectionFactory.*;

import com.apposit.videorental.dao.database.Database;
import com.apposit.videorental.dao.database.Restriction;
import com.apposit.videorental.dao.database.clause.ClauseFactory;
import com.apposit.videorental.dao.database.clause.Expression;
import com.apposit.videorental.dao.database.command.SelectCommand;
import com.apposit.videorental.dao.database.criteria.SingleCriteria;
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
		
		SingleCriteria videoCriteria = new SingleCriteria();
		videoCriteria.addAnd(Restriction.gt("video_id", "1")).addOr(Restriction.like("video_title", "%we%")).addAnd(Restriction.gt("video_id", "3"));
		
		List<Video> videos = db.find(videoCriteria, Video.class);
		for(Object obj: videos) {
			if(obj instanceof Video) {
				Video vidObj = (Video) obj;
				System.out.println(vidObj + " | " + vidObj.getGenre());
			}
		}
		
		return null;
	}
	
	

}

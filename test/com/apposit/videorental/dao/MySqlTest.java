package com.apposit.videorental.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.junit.Before;
import org.junit.Test;

import com.apposit.videorental.dao.database.Database;
import com.apposit.videorental.dao.database.Restriction;
import com.apposit.videorental.dao.database.MySql.MySqlDB;
import com.apposit.videorental.dao.database.criteria.AndCriteria;
import com.apposit.videorental.dao.database.criteria.Criteria;
import com.apposit.videorental.dao.database.criteria.OrCriteria;
import com.apposit.videorental.dao.database.criteria.SingleCriteria;
import com.apposit.videorental.model.Video;

public class MySqlTest {

	Database db = null;
	Video vid = null;
	
	@Before
	public void init() throws Exception {
		db = MySqlDB.getNewInstance();
		
		SingleCriteria cr = SingleCriteria.newInstance(Restriction.eq("video_id", "1"));
		vid = (Video) db.find(cr, Video.class).get(0);
		
	}
	
	@Test
	public void testInsert() throws Exception {
		Video videoModel = new Video();
		videoModel.setTitle("The Transporter");
		videoModel.setType_id(2);
		videoModel.setGenre_id(1);
		
		db.insert(videoModel);
		
		assertTrue(true);
	}

	@Test
	public void testFind() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, SQLException, InvalidInputException {
		Database db = MySqlDB.getNewInstance();
		
		SingleCriteria videoCriteria = new SingleCriteria(Restriction.gte("video_id", "1"));
		
		SingleCriteria genreCriteria = SingleCriteria.newInstance(Restriction.gt("genre_id", "3")).addAnd(Restriction.eq("genre_name", "Action"));
		
		AndCriteria andCriteria = new AndCriteria();
		andCriteria.setFirstCriteria(videoCriteria);
		andCriteria.setSecondCriteria(genreCriteria);
		
		OrCriteria orCriteria = new OrCriteria();
		orCriteria.setFirstCriteria(andCriteria);
		orCriteria.setSecondCriteria(SingleCriteria.newInstance(Restriction.eq("type_id", "1")));
		
		System.out.println( andCriteria.getQueryString());
		
		Method queryMethod = MySqlDB.class.getDeclaredMethod("getSelectQuery", Criteria.class, Class.class);
		queryMethod.setAccessible(true);
		
		String query = queryMethod.invoke(db, orCriteria, Video.class).toString();
		System.out.println(query);
		assertTrue(true);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}

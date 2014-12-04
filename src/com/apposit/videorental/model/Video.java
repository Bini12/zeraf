package com.apposit.videorental.model;

import com.apposit.videorental.dao.annotation.Column;
import com.apposit.videorental.dao.annotation.Id;
import com.apposit.videorental.dao.annotation.Ref;
import com.apposit.videorental.dao.annotation.Table;


@Table(name="video")
public class Video {
	
	//video title
	@Column("video_title")
	private String title;
	
	//reference foreign key to type table
	@Column("video_type_id")
	@Id
	private int type_id;
	
	//references foreign key to genere table
	@Column("video_genre_id")
	private int genre_id;

	@Ref(on="genre", model="com.apposit.videorental.model.Genre")
	private Genre genre;
	
	public String getTitle() {
		return title;
	}


	public Genre getGenre() {
		return genre;
	}


	public void setGenre(Genre genre) {
		this.genre = genre;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getType_id() {
		return type_id;
	}



	public void setType_id(int type_id) {
		this.type_id = type_id;
	}



	public int getGenre_id() {
		return genre_id;
	}



	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}


	@Override
	public String toString() {
		return title + " : " + genre_id + " : " + type_id;
	}
}

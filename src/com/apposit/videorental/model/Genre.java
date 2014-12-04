package com.apposit.videorental.model;

import com.apposit.videorental.dao.annotation.Column;
import com.apposit.videorental.dao.annotation.Table;

@Table(name="genre")
public class Genre {

	@Column("genre_id")
	private int id;
	
	@Column("genre_title")
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "Genre: " + name;
	}
}

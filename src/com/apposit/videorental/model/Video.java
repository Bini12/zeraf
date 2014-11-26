package com.apposit.videorental.model;

public class Video {
	
	//video title
	private String title;
	
	//reference foreign key to type table
	private int type;
	
	//references foreign key to genere table
	private int genre;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getGenre() {
		return genre;
	}

	public void setGenre(int genre) {
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return title + " : " + genre + " : " + type;
	}
}

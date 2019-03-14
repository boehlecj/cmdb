package com.boehle.cmdb.dto;

public class MovieV1Dto extends MovieDto {;
	private DirectorV1Dto director;
	
	public MovieV1Dto() {
		super();
	};
	
	public MovieV1Dto(String name, String genre, String year, DirectorV1Dto director, double rating) {
		super(name, genre, year, rating);
		this.director = director;
	}
	
	public MovieV1Dto(long id, String name, String genre, String year, DirectorV1Dto director, double rating) {	
		this(name, genre, year, director, rating);
		this.id = id;
	}
	
	public DirectorV1Dto getDirector() {
		return director;
	}
	
	public void setDirector(DirectorV1Dto director) {
		this.director = director;
	}

}

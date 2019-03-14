package com.boehle.cmdb.dto;

public class MovieV2Dto extends MovieDto {
	private DirectorV2Dto director;
	private String description;
	
	public MovieV2Dto() {
		super();
	};
	
	public MovieV2Dto(String name, String genre, String year, DirectorV2Dto director, double rating) {
		super(name, genre, year, rating);
		this.director = director;
	}
	
	public MovieV2Dto(long id, String name, String genre, String year, DirectorV2Dto director, double rating, String description) {	
		super(id, name, genre, year, rating);
		this.director = director;
		this.description = description;
	}
	
	public DirectorV2Dto getDirector() {
		return director;
	}
	
	public void setDirector(DirectorV2Dto director) {
		this.director = director;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String getGenre() {
		return "Not supported in V2";
	}
}

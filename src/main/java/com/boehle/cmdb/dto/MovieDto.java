package com.boehle.cmdb.dto;

public abstract class MovieDto extends AbstractDto {
	protected long id;
	protected String name;
	protected String genre;
	protected String year;
	protected double rating;
	
	public MovieDto() {};
	
	public MovieDto(String name, String genre, String year, double rating) {
		this.name = name;
		this.genre = genre;
		this.year = year;
		this.rating = rating;
	}
	
	public MovieDto(long id, String name, String genre, String year, double rating) {	
		this(name, genre, year, rating);
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
}

package com.boehle.cmdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	//Don't want the same movie entered in twice
	@Column(unique=true)
	private String name;
	private String genre;
	private String year;
	private String director;
	private double rating;
	
	public Movie() {
		super();
	}

	public Movie(String name, String genre, String year, String director, double rating) {
		super();
		this.name = name;
		this.genre = genre;
		this.year = year;
		this.director = director;
		this.rating = rating;
	}
	
	public Movie(int id, String name, String genre, String year, String director, double rating) {	
		this(name, genre, year, director, rating);
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
	
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

}

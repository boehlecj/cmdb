package com.boehle.cmdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Movie extends AbstractEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	//Don't want the same movie entered in twice
	@Column(unique=true)
	private String name;
	private String genre;
	private String year;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
	private Director director;
	
	private double rating;
	private String description;
	
	public Movie() {
		super();
	}

	public Movie(String name, String genre, String year, Director director, double rating, String description) {
		super();
		this.name = name;
		this.genre = genre;
		this.year = year;
		this.director = director;
		this.rating = rating;
	}
	
	public Movie(long id, String name, String genre, String year, Director director, double rating, String description) {	
		this(name, genre, year, director, rating, description);
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
	
	public Director getDirector() {
		return director;
	}
	
	public void setDirector(Director director) {
		this.director = director;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

package com.movieproject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

	/*************************************************************************************************/

	/*
	 * 
	 * CREATE TABLE movies(
	 * 
	 * movieid INT(10) NOT NULL, moviename VARCHAR(100) NOT NULL, categoryid
	 * INT(10) NOT NULL, description VARCHAR(500) NOT NULL, moviefilepath
	 * VARCHAR(100) NOT NULL, url VARCHAR(500) NOT NULL, language VARCHAR(100)
	 * NOT NULL, month INT(2) NOT NULL, year INT(4) NOT NULL,
	 * 
	 * PRIMARY KEY (movieid));
	 */

	/*************************************************************************************************/

	private Integer movieid;
	private String moviename;
	private Categories categoryid;
	private String description;
	private String moviefilepath;
	private String url;
	private String language;
	private Integer month;
	private Integer year;
	private String actors;
	private double averageRating;

/*************************************************************************************************/

	@Id
	@Column(name = "movieid", unique = true, nullable = false)
	public Integer getMovieid() {
		return movieid;
	}

	public void setMovieid(Integer movieid) {
		this.movieid = movieid;
	}

	@Column(name = "moviename", unique = false, nullable = false)
	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	@ManyToOne(targetEntity = Categories.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "categoryid", referencedColumnName = "categoryid")
	public Categories getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Categories categoryid) {
		this.categoryid = categoryid;
	}

	@Column(name = "description", unique = false, nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "moviefilepath", unique = false, nullable = true)
	public String getMoviefilepath() {
		return moviefilepath;
	}

	public void setMoviefilepath(String moviefilepath) {
		this.moviefilepath = moviefilepath;
	}

	@Column(name = "language", unique = false, nullable = true)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "month", unique = false, nullable = true)
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	@Column(name = "year", unique = false, nullable = true)
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "url", unique = false, nullable = true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name = "actors", unique = false, nullable = true)
	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	@Column(name = "averageRating", unique = false, nullable = true)
	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	/*************************************************************************************************/

}

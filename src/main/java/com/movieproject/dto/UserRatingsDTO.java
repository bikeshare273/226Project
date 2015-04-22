package com.movieproject.dto;

import com.movieproject.entities.Movie;
import com.movieproject.entities.Users;

public class UserRatingsDTO {

	private Integer movieid;
	private Integer rating;
	public Integer getMovieid() {
		return movieid;
	}
	public void setMovieid(Integer movieid) {
		this.movieid = movieid;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}

}

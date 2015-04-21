package com.movieproject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="userratings")
public class UserRatings {
	
/**********************************************************************************************/
	
/*
			CREATE TABLE userratings(
			
			user_rating_id		INT(10),
			userid				INT(10),
			movieid				INT(10),
			rating				INT(2)		NOT NULL	DEFAULT 00,

			PRIMARY KEY (user_rating_id),

			FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE CASCADE,
			FOREIGN KEY (movieid) REFERENCES movies(movieid) ON DELETE CASCADE);
	
*/	

/**********************************************************************************************/
		
	private Integer user_rating_id;	
	private Users userid;
	private Movie movieid;
	private Integer rating;
	
/**********************************************************************************************/
		
	@Id
	@Column(name = "user_rating_id", unique = true, nullable = false)
	public Integer getUser_rating_id() {
		return user_rating_id;
	}

	public void setUser_rating_id(Integer user_rating_id) {
		this.user_rating_id = user_rating_id;
	}

	@ManyToOne(targetEntity = Users.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	public Users getUserid() {
		return userid;
	}

	public void setUserid(Users userid) {
		this.userid = userid;
	}

	@ManyToOne(targetEntity = Movie.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "movieid", referencedColumnName = "movieid")
	public Movie getMovieid() {
		return movieid;
	}

	public void setMovieid(Movie movieid) {
		this.movieid = movieid;
	}

	@Column(name = "rating", unique = false, nullable = false)
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
}

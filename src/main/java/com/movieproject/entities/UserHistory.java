package com.movieproject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userhistory")
public class UserHistory {

/**********************************************************************************************/	

	/*  CREATE TABLE userhistory(
		userid   	INT(10),
		movieid  	INT(10),
		categoryid	INT(10),

		PRIMARY KEY (userid, movieid),

		FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE CASCADE,
		FOREIGN KEY (movieid) REFERENCES movies(movieid) ON DELETE CASCADE,
		FOREIGN KEY (categoryid) REFERENCES categories(categoryid) ON DELETE CASCADE 

); */

/**********************************************************************************************/	

	private Integer userhistory_id;
	private Users userid;
	private Movie movieid;
	private Categories categoryid;

/**********************************************************************************************/
	
	@Id
	@Column(name = "userhistory_id", unique = true, nullable = false)
	public Integer getUserhistory_id() {
		return userhistory_id;
	}

	public void setUserhistory_id(Integer userhistory_id) {
		this.userhistory_id = userhistory_id;
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

	@ManyToOne(targetEntity = Categories.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "categoryid", referencedColumnName = "categoryid")
	public Categories getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Categories categoryid) {
		this.categoryid = categoryid;
	}

/**********************************************************************************************/

}

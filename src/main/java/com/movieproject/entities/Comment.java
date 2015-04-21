package com.movieproject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="comments")
public class Comment {

/*******************************************************************************/
	
/*
 		CREATE TABLE comments(
		
		commentid			INT(10),
		movieid				INT(10),
		userid				INT(10),
		comment				VARCHAR(200)	DEFAULT NULL,

		PRIMARY KEY (commentid),

		FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE CASCADE,
		FOREIGN KEY (movieid) REFERENCES movies(movieid) ON DELETE CASCADE);
 		
 */

/*******************************************************************************/
	
	private Integer commentid;
	private Movie	movieid;
	private Users	userid;
	private String comment;
		
/*******************************************************************************/

	@Id
	@Column(name = "commentid", unique = true, nullable = false)
	public Integer getCommentid() {
		return commentid;
	}

	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}

	@ManyToOne(targetEntity = Movie.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "movieid", referencedColumnName = "movieid")
	public Movie getMovieid() {
		return movieid;
	}

	public void setMovieid(Movie movieid) {
		this.movieid = movieid;
	}

	@ManyToOne(targetEntity = Users.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	public Users getUserid() {
		return userid;
	}

	public void setUserid(Users userid) {
		this.userid = userid;
	}

	@Column(name = "comment", unique = false, nullable = true)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
/*******************************************************************************/


}

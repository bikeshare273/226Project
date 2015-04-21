package com.movieproject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="moviecomments")
public class MovieComments {

/*************************************************************************************************/	
/* 
 			CREATE TABLE moviecomments(

			movie_comment_id	INT(10),
			movieid				INT(10),
			userid				INT(10),
			commentid			INT(10),
			
			PRIMARY KEY (movie_comment_id),

			FOREIGN KEY (movieid) REFERENCES movies(movieid) ON DELETE CASCADE,
			FOREIGN KEY (userid) REFERENCES users(userid) ON DELETE CASCADE),
			FOREIGN KEY (commentid) REFERENCES comments(userid) ON DELETE CASCADE);
 
 */
/*************************************************************************************************/
	
	private Integer movie_comment_id;
	private Movie movieid;
	private Users userid;
	private Comment commentid;
	
/*************************************************************************************************/
	
	@Id
	@Column(name = "movie_comment_id", unique = true, nullable = false)
	public Integer getMovie_comment_id() {
		return movie_comment_id;
	}

	public void setMovie_comment_id(Integer movie_comment_id) {
		this.movie_comment_id = movie_comment_id;
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
	
	@OneToOne(targetEntity = Comment.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "commentid", referencedColumnName = "commentid")
	public Comment getCommentid() {
		return commentid;
	}

	public void setCommentid(Comment commentid) {
		this.commentid = commentid;
	}
	
/*************************************************************************************************/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

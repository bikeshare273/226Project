package com.movieproject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "moviesactors")
public class MovieActors {
	
/*	
			CREATE TABLE moviesactors(
	
			movies_actor_id	INT(10)			NOT NULL,
			movieid			INT(10),
			actorid			INT(10),

			PRIMARY KEY (movies_actor_id),

			FOREIGN KEY (movieid) REFERENCES movies(movieid) ON DELETE CASCADE,
			FOREIGN KEY (actorid) REFERENCES actors(actorid) ON DELETE CASCADE);
*/
	
	
/*************************************************************************************************/

	private Integer movie_actor_id;
	private Movie movieid;
	private Actors actorid;

/*************************************************************************************************/
	
	@Id
	@Column(name = "movie_actor_id", unique = true, nullable = false)
	public Integer getMovie_actor_id() {
		return movie_actor_id;
	}

	public void setMovie_actor_id(Integer movie_actor_id) {
		this.movie_actor_id = movie_actor_id;
	}

	@ManyToOne(targetEntity = Movie.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "movieid", referencedColumnName = "movieid")
	public Movie getMovieid() {
		return movieid;
	}

	public void setMovieid(Movie movieid) {
		this.movieid = movieid;
	}

	@ManyToOne(targetEntity = Actors.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "actorid", referencedColumnName = "actorid")
	public Actors getActorid() {
		return actorid;
	}

	public void setActorid(Actors actorid) {
		this.actorid = actorid;
	}
	
/*************************************************************************************************/

}

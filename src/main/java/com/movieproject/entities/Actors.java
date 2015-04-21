package com.movieproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actors")
public class Actors {

/*********************************************************************/	

/*
 		CREATE TABLE actors(
		
		actorid			INT(10),
		actorname		VARCHAR(100),

		PRIMARY KEY (actorid));
		
*/
	
/*********************************************************************/
	private Integer actorid;
	private String actorname;

/*********************************************************************/	
	
	@Id
	@Column(name = "actorid", unique = true, nullable = false)
	public Integer getActor_id() {
		return actorid;
	}

	public void setActor_id(Integer actor_id) {
		this.actorid = actor_id;
	}

	@Column(name = "actorname", unique = true, nullable = false)
	public String getActorname() {
		return actorname;
	}

	public void setActorname(String actorname) {
		this.actorname = actorname;
	}
	
/*********************************************************************/		

	
}

package com.movieproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "recommendations")
public class Recommendations {

	/*********************************************************************/
	/*
	  			CREATE TABLE recommendations(
				reconmmendationid 	INT(10),
				userid				INT(10),
				movieid				INT(10),
				seenflag			BOOLEAN,
				ref_userid			INT(10),
	 */
	
	/*********************************************************************/
	
	private Integer reconmmendationid;
	private Integer	userid;
	private Integer	movieid;
	private boolean seenflag;
	private Integer ref_userid;
	
	/*********************************************************************/
	
	@Id
	@Column(name = "reconmmendationid", unique = true, nullable = false)
	public Integer getReconmmendationid() {
		return reconmmendationid;
	}
	public void setReconmmendationid(Integer reconmmendationid) {
		this.reconmmendationid = reconmmendationid;
	}
	
	@Column(name = "userid", unique = true, nullable = false)
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	@Column(name = "movieid", unique = true, nullable = false)
	public Integer getMovieid() {
		return movieid;
	}
	public void setMovieid(Integer movieid) {
		this.movieid = movieid;
	}
	
	@Column(name = "seenflag")
	public boolean isSeenflag() {
		return seenflag;
	}
	public void setSeenflag(boolean seenflag) {
		this.seenflag = seenflag;
	}
	
	@Column(name = "ref_userid", unique = true, nullable = false)
	public Integer getRef_userid() {
		return ref_userid;
	}
	public void setRef_userid(Integer ref_userid) {
		this.ref_userid = ref_userid;
	}
	
	/*********************************************************************/
	
}

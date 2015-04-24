package com.movieproject.dto;

public class RecommendationDTO {
	
	Integer movieid;
	String username;	//email
	String applicationMessage;
	boolean successFlag;
	
	
	public Integer getMovieid() {
		return movieid;
	}
	public void setMovieid(Integer movieid) {
		this.movieid = movieid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
	public String getApplicationMessage() {
		return applicationMessage;
	}
	public void setApplicationMessage(String applicationMessage) {
		this.applicationMessage = applicationMessage;
	}

	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	

}

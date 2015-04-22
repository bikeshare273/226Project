package com.movieproject.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.movieproject.dao.interfaces.IDaoInterfaceForMovie;
import com.movieproject.dto.MovieDTO;
import com.movieproject.entities.Categories;
import com.movieproject.entities.Movie;

public class MovieImpl {
	
	@Autowired
	IDaoInterfaceForMovie movieDao;
	
	/*
		 	private Integer movieid;	
			private String moviename;	
			private Categories categoryid;	
			private String description;	
			private String moviefilepath;
			private String url;
			private String language;	
			private Integer month;	
			private Integer year;
	 */
	
		
	public void addMovie(MovieDTO movie){
	
		
	
	}

}

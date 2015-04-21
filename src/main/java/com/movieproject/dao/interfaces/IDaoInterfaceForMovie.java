package com.movieproject.dao.interfaces;

import java.util.List;

import com.movieproject.entities.Movie;



public interface IDaoInterfaceForMovie {
	
	public Movie save(Movie movie);
	public Movie update(Movie movie);
	public void   delete(Movie movie);
	public Movie getMovieById(Integer movieid);
	public List<Movie> getMovieByName(String moviename);
	public List<Movie> getMoviesByCategory(Integer categoryid);
	public List<Movie> getMoviesByLanguage(String language);
	public List<Movie> getMoviesByYear(Integer year);
	
}

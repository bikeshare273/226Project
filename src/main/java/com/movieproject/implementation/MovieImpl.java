package com.movieproject.implementation;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.movieproject.dao.interfaces.IDaoInterfaceForCategories;
import com.movieproject.dao.interfaces.IDaoInterfaceForMovie;
import com.movieproject.dto.MovieDTO;
import com.movieproject.entities.Categories;
import com.movieproject.entities.Login;
import com.movieproject.entities.Movie;
import com.movieproject.entities.Users;
import com.movieproject.util.MovieAppUtil;

public class MovieImpl {
	
	@Autowired
	IDaoInterfaceForMovie movieDao;
	
	@Autowired
	IDaoInterfaceForCategories categoriesDao;
	
	@Autowired
	MovieAppUtil movieAppUtils;
	
	/*
	 	private Integer movieid;	
		private String moviename;	
		private Integer categoryid;	
		private String description;	
		private String moviefilepath;
		private String url;
		private String language;	
		private Integer month;	
		private Integer year;
	 */

/*****************************************************************************************/
	
	public MovieDTO addMovie(MovieDTO movie){
	
		Movie movieObject = new Movie();
		
		try { BeanUtils.copyProperties(movieObject, movie);} 
		catch (IllegalAccessException e) { e.printStackTrace(); } 
		catch (InvocationTargetException e) { e.printStackTrace(); }
		
		Integer movieid = movieAppUtils.generateIdValue(2000);
		movieObject.setMovieid(movieid);
		movie.setMovieid(movieid);

		Categories category = categoriesDao.getCategoryById(movie.getCategoryid());
		
		movieObject.setCategoryid(category);
		
		movieDao.save(movieObject);
	
		return movie;
	}
	
/*****************************************************************************************/

	public boolean deleteMovie(Integer movieid){
		
		Movie movieObject = getMovie(movieid) ;
		
		if(movieObject == null) { return false; }
		
		movieDao.delete(movieObject);
		
		return true;
		
	}
	
/*****************************************************************************************/
	
	public Movie getMovie(Integer movieid)
	{
		Movie movieObject = movieDao.getMovieById(movieid);
		
		if(movieObject == null) { return null; }
		
		return movieObject;
		
	}
	
/*****************************************************************************************/
	
	public List<Movie> getMoviesByName(String moviename)
	{
		List<Movie> moviesByName = movieDao.getMovieByName(moviename);
		
		if(moviesByName == null) { return null; }
		
		return moviesByName;
		
	}

/*****************************************************************************************/
	
	public Integer getCategoryIdForMovie(Integer movieid)
	{
		Movie movieObject = movieDao.getMovieById(movieid);
		
		if(movieObject == null) { return null; }
		
		Categories category = movieObject.getCategoryid();
		
		
		Integer categoryid =  category.getCategoryid();
	
		return categoryid;
	}
	
/*****************************************************************************************/
		
	public List<Movie> getAllMoviesForCategory(Integer categoryid)
	
	{
		
		List<Movie> moviesByCategory = movieDao.getMoviesByCategory(categoryid);
		
		if(moviesByCategory == null ) {return null;}
		
		return moviesByCategory;
	
	}
	
/*****************************************************************************************/
	
	
	public List<Movie> getAllMoviesForYear(Integer year)
	
	{
		
		List<Movie> moviesByYear = movieDao.getMoviesByYear(year);
		
		if(moviesByYear == null ) {return null;}
		
		return moviesByYear;
	
	}
	
/*****************************************************************************************/
	
	public List<Movie> getAllMoviesForLanguage(String language)
	
	{
		
		List<Movie> moviesByLanguage = movieDao.getMoviesByLanguage(language);
		
		if(moviesByLanguage == null ) {return null;}
		
		return moviesByLanguage;
	
	}
	
/*****************************************************************************************/
	
	
	
	
	
	
	
	
}

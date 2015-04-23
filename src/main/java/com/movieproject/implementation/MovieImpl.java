package com.movieproject.implementation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.movieproject.dao.interfaces.IDaoInterfaceForActors;
import com.movieproject.dao.interfaces.IDaoInterfaceForCategories;
import com.movieproject.dao.interfaces.IDaoInterfaceForMovie;
import com.movieproject.dao.interfaces.IDaoInterfaceForMovieActors;
import com.movieproject.dto.MovieDTO;
import com.movieproject.entities.Actors;
import com.movieproject.entities.Categories;
import com.movieproject.entities.Movie;
import com.movieproject.entities.MovieActors;
import com.movieproject.util.MovieAppUtil;

public class MovieImpl {

	@Autowired
	IDaoInterfaceForMovie movieDao;

	@Autowired
	IDaoInterfaceForCategories categoriesDao;

	@Autowired
	IDaoInterfaceForActors actorsDao;

	@Autowired
	IDaoInterfaceForMovieActors moviesActorsDao;

	@Autowired
	MovieAppUtil movieAppUtils;

	/*
	 * private Integer movieid; private String moviename; private Integer
	 * categoryid; private String description; private String moviefilepath;
	 * private String url; private String language; private Integer month;
	 * private Integer year;
	 */

	/*****************************************************************************************/

	public MovieDTO addMovie(MovieDTO movie) {

		Movie movieObject = new Movie();

		//BeanUtils.copyProperties(movieObject, movie);
		movieObject.setMoviename(movie.getMoviename());
		movieObject.setDescription(movie.getDescription());
		movieObject.setMoviefilepath(movie.getMoviefilepath());
		movieObject.setUrl(movie.getUrl());
		movieObject.setYear(movie.getYear());
		movieObject.setLanguage(movie.getLanguage());
		movieObject.setActors(movie.getActors());
		
		Integer movieid = movieAppUtils.generateIdValue(2000);
		movieObject.setMovieid(movieid);
		movie.setMovieid(movieid);
		
		movieObject.setAverageRating(0);
		
		Categories category = categoriesDao.getCategoryById(movie
				.getCategoryid());

		movieObject.setCategoryid(category);
		
		String actors = movie.getActors();

		movieDao.save(movieObject);
		
		updateMovieActors(movieid, actors);

		return movie;
	}

	/*****************************************************************************************/

	public boolean deleteMovie(Integer movieid) {

		Movie movieObject = getMovie(movieid);

		if (movieObject == null) {
			return false;
		}

		movieDao.delete(movieObject);

		deleteMovieActorsEntries(movieid);

		return true;

	}

	/*****************************************************************************************/

	public Movie getMovie(Integer movieid) {
		Movie movieObject = movieDao.getMovieById(movieid);

		if (movieObject == null) {
			return null;
		}

		return movieObject;

	}

	/*****************************************************************************************/

	public List<Movie> getMoviesByName(String moviename) {
		List<Movie> moviesByName = movieDao.getMovieByName(moviename);

		if (moviesByName == null) {
			return null;
		}

		return moviesByName;

	}

	/*****************************************************************************************/

	public Integer getCategoryIdForMovie(Integer movieid) {
		Movie movieObject = movieDao.getMovieById(movieid);

		if (movieObject == null) {
			return null;
		}

		Categories category = movieObject.getCategoryid();

		Integer categoryid = category.getCategoryid();

		return categoryid;
	}

/*****************************************************************************************/
	
	public List<Movie> getAllMovies()
	{
		List<Movie> allMovies = movieDao.getAllMovies();

		if (allMovies == null) {
			return null;
		}

		return allMovies;
	}
	
/*****************************************************************************************/

	public List<Movie> getAllMoviesForCategory(Integer categoryid)

	{

		List<Movie> moviesByCategory = movieDao.getMoviesByCategory(categoryid);

		if (moviesByCategory == null) {
			return null;
		}

		return moviesByCategory;

	}

	/*****************************************************************************************/

	public List<Movie> getAllMoviesForYear(Integer year)

	{

		List<Movie> moviesByYear = movieDao.getMoviesByYear(year);

		if (moviesByYear == null) {
			return null;
		}

		return moviesByYear;

	}

	/*****************************************************************************************/

	public List<Movie> getAllMoviesForLanguage(String language)

	{

		List<Movie> moviesByLanguage = movieDao.getMoviesByLanguage(language);

		if (moviesByLanguage == null) {
			return null;
		}

		return moviesByLanguage;

	}

	/*****************************************************************************************/

	public void updateMovieActors(Integer movieid, String actors) {
		List<String> actorsList = movieAppUtils.arraySplitter(actors);

		if (actorsList != null) {
			Integer actorid = 0;

			for (String actorName : actorsList) {
				Actors actor = actorsDao.getActorByName(actorName);

				if (actor == null) {
					actorid = addActor(actorName);
				} else {
					actorid = actor.getActorid();
				}

				addMovieActorsEntry(movieid, actorid);

			}// For

		}// If
	}

	/*****************************************************************************************/

	public Integer addActor(String actorName) {
		Actors actorObject = new Actors();

		Integer actor_id = movieAppUtils.generateIdValue(9000);

		actorObject.setActorid(actor_id);
		actorObject.setActorname(actorName);

		actorsDao.save(actorObject);

		return actor_id;
	}

	/*****************************************************************************************/

	public void addMovieActorsEntry(Integer movieid, Integer actorid) {

		MovieActors movieActorsEntry = new MovieActors();

		Integer movie_actor_id = movieAppUtils.generateIdValue(600);

		Movie movie = movieDao.getMovieById(movieid);
		Actors actor = actorsDao.getActorById(actorid);

		movieActorsEntry.setMovie_actor_id(movie_actor_id);
		movieActorsEntry.setMovieid(movie);
		movieActorsEntry.setActorid(actor);

		moviesActorsDao.save(movieActorsEntry);

	}

/*****************************************************************************************/

	public void deleteMovieActorsEntries(Integer movieid) {
		List<MovieActors> movieActorsEntries = new ArrayList<MovieActors>();

		movieActorsEntries = moviesActorsDao.getMovieActorsByMovieId(movieid);

		if (movieActorsEntries != null) {

			for (MovieActors movieActorsEntry : movieActorsEntries)

			{
				moviesActorsDao.delete(movieActorsEntry);
			}

		}

	}
	
/*****************************************************************************************/
	
	
	
	
	
	
	

}

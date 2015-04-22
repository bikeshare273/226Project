package com.movieproject.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.movieproject.dao.interfaces.IDaoInterfaceForMovie;
import com.movieproject.entities.Movie;

@Transactional
public class MovieDao implements IDaoInterfaceForMovie {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Movie save(Movie movie) {

		hibernateTemplate.save(movie);
		
		return null;
	}

	@Override
	public Movie update(Movie movie) {

		hibernateTemplate.update(movie);
		
		return null;
	}

	@Override
	public void delete(Movie movie) {

		hibernateTemplate.delete(movie);
	
	}

	@Override
	public Movie getMovieById(Integer movieid) {

		String query = "from Movies m where m.movieid = ?";
		@SuppressWarnings("unchecked")
		List<Movie> movies = (List<Movie>) hibernateTemplate.find(query, movieid);

		if (movies.isEmpty()) {
			return null;
		} else {
			return movies.get(0);
		}

	}

	@Override
	public List<Movie> getMovieByName(String moviename) {
		
		String query = "from Movies m where lower(m.moviename) LIKE lower(?)";
		@SuppressWarnings("unchecked")
		List<Movie> movies = (List<Movie>) hibernateTemplate.find(query, moviename);

		if (movies.isEmpty()) {
			return null;
		} else {
			return movies;
		}
	}

	@Override
	public List<Movie> getMoviesByCategory(Integer categoryid) {

		String query = "from Movies m where m.categories.categoryid = ?";
		@SuppressWarnings("unchecked")
		List<Movie> movies = (List<Movie>) hibernateTemplate.find(query, categoryid);

		if (movies.isEmpty()) {
			return null;
		} else {
			return movies;
		}
		
	}

	@Override
	public List<Movie> getMoviesByLanguage(String language) {
		
		String query = "from Movies m where m.language = ?";
		@SuppressWarnings("unchecked")
		List<Movie> movies = (List<Movie>) hibernateTemplate.find(query, language);

		if (movies.isEmpty()) {
			return null;
		} else {
			return movies;
		}
		
	}

	@Override
	public List<Movie> getMoviesByYear(Integer year) {

		String query = "from Movies m where m.year = ?";
		@SuppressWarnings("unchecked")
		List<Movie> movies = (List<Movie>) hibernateTemplate.find(query, year);

		if (movies.isEmpty()) {
			return null;
		} else {
			return movies;
		}
	}

}

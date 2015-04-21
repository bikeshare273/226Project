package com.movieproject.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.movieproject.dao.interfaces.IDaoInterfaceForMovieActors;
import com.movieproject.entities.MovieActors;

@Transactional
public class MovieActorsDao implements IDaoInterfaceForMovieActors {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public MovieActors save(MovieActors movieactors) {

		hibernateTemplate.save(movieactors);
		
		return null;
	}

	@Override
	public MovieActors update(MovieActors movieactors) {

		hibernateTemplate.update(movieactors);
		
		return null;
	}

	@Override
	public void delete(MovieActors movieactors) {

		hibernateTemplate.delete(movieactors);
		
	}

	@Override
	public MovieActors getMovieActorsById(Integer movie_actor_id) {

		String query = "from Moviesactors m where m.movie_actor_id = ?";
		@SuppressWarnings("unchecked")
		List<MovieActors> movieActors = (List<MovieActors>) hibernateTemplate.find(query, movie_actor_id);

		if (movieActors.isEmpty()) {
			return null;
		} else {
			return movieActors.get(0);
		}
		
	}

	@Override
	public List<MovieActors> getMovieActorsByMovieId(Integer movieid) {

		String query = "from Moviesactors m where m.movie.movieid = ?";
		@SuppressWarnings("unchecked")
		List<MovieActors> movieActors = (List<MovieActors>) hibernateTemplate.find(query, movieid);

		if (movieActors.isEmpty()) {
			return null;
		} else {
			return movieActors;
		}
	}

	@Override
	public List<MovieActors> getMovieActorsByActorId(Integer actorid) {

		String query = "from Moviesactors m where m.actors.actorid = ?";
		@SuppressWarnings("unchecked")
		List<MovieActors> movieActors = (List<MovieActors>) hibernateTemplate.find(query, actorid);

		if (movieActors.isEmpty()) {
			return null;
		} else {
			return movieActors;
		}
	}

	@Override
	public List<MovieActors> getMovieActorsByMovieIdAndActorId(Integer movieid,
			Integer actorid) {

		String query = "from Moviesactors m where m.movie.movieid = ? and m.actors.actorid = actorid ";
		@SuppressWarnings("unchecked")
		List<MovieActors> movieActors = (List<MovieActors>) hibernateTemplate.find(query, movieid ,actorid);

		if (movieActors.isEmpty()) {
			return null;
		} else {
			return movieActors;
		}
	}

}

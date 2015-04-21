package com.movieproject.dao.interfaces;

import java.util.List;

import com.movieproject.entities.Actors;
import com.movieproject.entities.Movie;
import com.movieproject.entities.MovieActors;

public interface IDaoInterfaceForMovieActors {
	
	public MovieActors save(MovieActors movieactors);
	public MovieActors update(MovieActors movieactors);
	public void   delete(MovieActors movieactors);
	public MovieActors getMovieActorsById(Integer movie_actor_id);
	public List<MovieActors> getMovieActorsByMovieId(Integer movieid);
	public List<MovieActors> getMovieActorsByActorId(Integer actorid);
	public List<MovieActors> getMovieActorsByMovieIdAndActorId(Integer movieid, Integer actorid);
	
}

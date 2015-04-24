package com.movieproject.dao.interfaces;

import java.util.List;

import com.movieproject.entities.Recommendations;

public interface IDaoInterfaceForRecommendations {
	
	
	public void save(Recommendations recommendation);
	public void update(Recommendations recommendation);
	public void delete(Recommendations recommendation);
	
	public Recommendations getRecommendationById(Integer reconmmendationid);
	public List<Recommendations> getRecommendationsByUserId(Integer userid);
	public List<Recommendations> getRecommendationsByMovieId(Integer movieid);
	public Recommendations getRecommendationsByUserIdAndMovieId(Integer userid, Integer movieid);
	public List<Recommendations> getRecommendationsByUserIdAndMovieIdAndRefUserId(Integer userid, Integer movieid, Integer ref_userid);
	public List<Recommendations> getUnseenRecommendedMovies(Integer userid);
}

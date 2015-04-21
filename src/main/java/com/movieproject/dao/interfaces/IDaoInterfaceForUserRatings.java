package com.movieproject.dao.interfaces;

import java.util.List;

import com.movieproject.entities.UserRatings;



public interface IDaoInterfaceForUserRatings {
		
	public UserRatings save(UserRatings userratings);
	public UserRatings update(UserRatings userratings);
	public void delete(UserRatings userratings);
	public UserRatings getUserRatingsById(Integer user_rating_id);
	public List<UserRatings> getUserRatingsByMovieId(Integer movieid);
	public List<UserRatings> getUserRatingsByUserIdAndMovieId(Integer userid, Integer movieid);
	public List<UserRatings> getUserRatingsByRating(Integer rating);

}

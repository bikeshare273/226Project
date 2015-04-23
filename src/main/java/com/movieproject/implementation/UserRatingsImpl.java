package com.movieproject.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.movieproject.dao.interfaces.IDaoInterfaceForMovie;
import com.movieproject.dao.interfaces.IDaoInterfaceForUserRatings;
import com.movieproject.dao.interfaces.IDaoInterfaceForUsers;
import com.movieproject.dto.UserRatingsDTO;
import com.movieproject.entities.Movie;
import com.movieproject.entities.UserRatings;
import com.movieproject.entities.Users;
import com.movieproject.util.MovieAppUtil;

public class UserRatingsImpl {
	
	
	/*
	 * List<UserRatings> getUserRatingsByMovieId(Integer movieid);
		public List<UserRatings> getUserRatingsByUserIdAndMovieId(Integer userid, Integer movieid);
		public List<UserRatings> getUserRatingsByRating(Integer rating);
		
		
		private Integer user_rating_id;	
	private Users userid;
	private Movie movieid;
	private Integer rating;
		
	 */
	
	
	
	@Autowired
	IDaoInterfaceForUsers userDao;
	
	@Autowired
	IDaoInterfaceForMovie movieDao;
	
	@Autowired
	MovieAppUtil movieAppUtils;
	
	@Autowired
	IDaoInterfaceForUserRatings userRatingsDao;
	
/*****************************************************************************************/
	
	public void addUserRating(UserRatingsDTO userRatingDTO, Integer userid)
	{
		UserRatings userrating = new UserRatings();
		
		Integer user_rating_id = movieAppUtils.generateIdValue(500);
		userrating.setUser_rating_id(user_rating_id);
		
		
		Integer movieid = userRatingDTO.getMovieid();
		Movie movie = movieDao.getMovieById(movieid);
		userrating.setMovieid(movie);
		
		
		Users user = userDao.getUserById(userid);
		userrating.setUserid(user);
			
		
		Integer rating = userRatingDTO.getRating();
		userrating.setRating(rating);
		
		updateAverageRatingOfMovie(movieid, rating);
		
		userRatingsDao.save(userrating);
	
	}

/*****************************************************************************************/
	
	public List<UserRatings> getUserRatingsByMovieId(Integer movieid)
	{
		List<UserRatings> userratings = userRatingsDao.getUserRatingsByMovieId(movieid); 
		
		if(userratings == null) {return null;}
		
		return userratings ;
		
	}
	
/*****************************************************************************************/
	
	public List<UserRatings> getUserRatingsByUserIdAndMovieId(Integer userid, Integer movieid){
		
	List<UserRatings> userratings = userRatingsDao.getUserRatingsByUserIdAndMovieId(userid, movieid); 
	
	if(userratings == null) {return null;}
	
	return userratings ;
	
	}
	
/*****************************************************************************************/
	
	public List<UserRatings> getUserRatingsByRating(Integer rating)
	{
		List<UserRatings> userratings = userRatingsDao.getUserRatingsByRating(rating);
		
		if(userratings == null) {return null;}
		
		return userratings ;
		
	}
	
/*****************************************************************************************/	

	public void updateAverageRatingOfMovie(Integer movieid, Integer rating)
	{
		Movie movie = movieDao.getMovieById(movieid);
		
		double updatedAverageRating = movie.getAverageRating();
		
		List<UserRatings> allRatingsForMovie = getUserRatingsByMovieId(movieid);
		
		Integer countOfRatings;
		
		 if (allRatingsForMovie == null)
		{
		
			countOfRatings = 0;
		}
		 else
		 {
			 countOfRatings = allRatingsForMovie.size();
		 }
		 
		
		double totalRating = updatedAverageRating * countOfRatings;
		
		totalRating += rating;
		
		if (totalRating != 0)
		{
			updatedAverageRating = totalRating / (countOfRatings + 1 );
		}
		else
		{	
			updatedAverageRating = 0;
		}
		
		movie.setAverageRating(updatedAverageRating);
		movieDao.update(movie);
		
	}


}

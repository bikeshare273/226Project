package com.movieproject.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.movieproject.dao.interfaces.IDaoInterfaceForCategories;
import com.movieproject.dao.interfaces.IDaoInterfaceForMovie;
import com.movieproject.dao.interfaces.IDaoInterfaceForUserHistory;
import com.movieproject.dao.interfaces.IDaoInterfaceForUsers;
import com.movieproject.entities.Categories;
import com.movieproject.entities.Movie;
import com.movieproject.entities.UserHistory;
import com.movieproject.entities.Users;
import com.movieproject.util.MovieAppUtil;


public class UserHistoryImpl {
	
	
/*
  	private Integer userhistory_id;
	private Users userid;
	private Movie movieid;
	private Categories categoryid;
 
 */

	
	@Autowired
	IDaoInterfaceForUsers userDao;
	
	@Autowired
	IDaoInterfaceForMovie movieDao;
	
	@Autowired
	IDaoInterfaceForCategories categoryDao;

	@Autowired
	MovieAppUtil movieAppUtils;
	
	@Autowired
	IDaoInterfaceForUserHistory userHistoryDao;
	
	
/*****************************************************************************************/
	
	public void addUserHistoryEntry(Integer userid, Integer movieid)
	{
		Users user = userDao.getUserById(userid);
		Movie movie = movieDao.getMovieById(movieid);
		
		Categories category = movie.getCategoryid();
		
		Integer userHistoryEntryId = movieAppUtils.generateIdValue(1234);
		
		UserHistory userhistory = new UserHistory();
		
		userhistory.setUserhistory_id(userHistoryEntryId);
		userhistory.setUserid(user);
		userhistory.setMovieid(movie);
		userhistory.setCategoryid(category);
		
		List<UserHistory> previousHistory = getUserHistoryByUserAndMovie(userid, movieid);
		
		if(previousHistory == null)
		{		
		userHistoryDao.save(userhistory);
		}		
	}

/*****************************************************************************************/
	
	public UserHistory getUserHistoryByEntryId(Integer userhistory_id)
	{
		UserHistory userhistory = userHistoryDao.getUserHistoryById(userhistory_id);
		
		if(userhistory == null) {return null;}

		return userhistory;
				
	}
	
/*****************************************************************************************/
	
	public List<UserHistory> getUserHistoryByUserId(Integer userid)
	{
		List <UserHistory> userhistoryEntries = userHistoryDao.getUserHistoryByUserId(userid);
		
		if(userhistoryEntries == null) {return null;}

		return userhistoryEntries;
				
	}
	
/*****************************************************************************************/
	
	public List<UserHistory> getUserHistoryByMovieId(Integer movieid)
	{
		List <UserHistory> userhistoryEntries = userHistoryDao.getUserHistoryByMovieId(movieid);
		
		if(userhistoryEntries == null) {return null;}

		return userhistoryEntries;
				
	}

/*****************************************************************************************/
	
	public List<UserHistory> getUserHistoryByUserAndMovie(Integer userid, Integer movieid)
	{
		List <UserHistory> userhistoryEntries = userHistoryDao.getUserHistoryByUserIdAndMovieId(userid, movieid);
		
		if(userhistoryEntries == null) {return null;}

		return userhistoryEntries;
	}
	
	
	/*****************************************************************************************/
	
	public List<UserHistory> getUserHistoryByUserAndCategory(Integer userid, Integer categoryid)
	{
		List <UserHistory> userhistoryEntries = userHistoryDao.getUserHistoryByUserIdAndCategoryId(userid, categoryid);
		
		if(userhistoryEntries == null) {return null;}

		return userhistoryEntries;
				
	}
	
/*****************************************************************************************/	
	
	public void deleteAllUserHistoryEntries(Integer userid)
	{
		List <UserHistory> userhistoryEntries = getUserHistoryByUserId(userid);
	
		if(userhistoryEntries != null)
		{
			for(UserHistory userHistoryEntry : userhistoryEntries)
			{
				userHistoryDao.delete(userHistoryEntry);
			}
		}
	
	
	}
	
	
	
	
	
	
	
}

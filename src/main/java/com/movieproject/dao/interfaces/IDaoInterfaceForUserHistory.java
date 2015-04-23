package com.movieproject.dao.interfaces;

import java.util.List;

import com.movieproject.entities.UserHistory;

public interface IDaoInterfaceForUserHistory {
	
	public UserHistory save(UserHistory userhistory);
	public UserHistory update(UserHistory userhistory);
	public void   delete(UserHistory userhistory);
	public UserHistory getUserHistoryById(Integer userhistory_id);
	public List<UserHistory> getUserHistoryByUserId(Integer userid);
	public List<UserHistory> getUserHistoryByMovieId(Integer movieid);
	public List<UserHistory> getUserHistoryByCategoryId(Integer categoryid);
	public List<UserHistory> getUserHistoryByUserIdAndMovieId( Integer userid, Integer movieid);
	public List<UserHistory> getUserHistoryByUserIdAndCategoryId(Integer userid, Integer categoryid);
	
}

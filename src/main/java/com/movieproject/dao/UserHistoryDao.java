package com.movieproject.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.movieproject.dao.interfaces.IDaoInterfaceForUserHistory;
import com.movieproject.entities.UserHistory;

@Transactional
public class UserHistoryDao implements IDaoInterfaceForUserHistory {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public UserHistory save(UserHistory userhistory) {

		hibernateTemplate.save(userhistory);
		
		return null;
	}

	@Override
	public UserHistory update(UserHistory userhistory) {

		hibernateTemplate.update(userhistory);
	
		return null;
	}

	@Override
	public void delete(UserHistory userhistory) {

		hibernateTemplate.delete(userhistory);
		
	}

	@Override
	public UserHistory getUserHistoryById(Integer userhistory_id) {

		String query = "from Userhistory u where u.userhistory_id = ?";
		@SuppressWarnings("unchecked")
		List<UserHistory> userhistory = (List<UserHistory>) hibernateTemplate.find(query, userhistory_id);

		if (userhistory.isEmpty()) {
			return null;
		} else {
			return userhistory.get(0);
		}
	}

	@Override
	public List<UserHistory> getUserHistoryByUserId(Integer userid) {

		String query = "from Userhistory u where u.users.userid = ?";
		@SuppressWarnings("unchecked")
		List<UserHistory> userhistory = (List<UserHistory>) hibernateTemplate.find(query, userid);

		if (userhistory.isEmpty()) {
			return null;
		} else {
			return userhistory;
		}
		
		
	}

	@Override
	public List<UserHistory> getUserHistoryByMovieId(Integer movieid) {
		
		String query = "from Userhistory u where u.movie.movieid = ?";
		@SuppressWarnings("unchecked")
		List<UserHistory> userhistory = (List<UserHistory>) hibernateTemplate.find(query, movieid);

		if (userhistory.isEmpty()) {
			return null;
		} else {
			return userhistory;
		}
		
	}

	@Override
	public List<UserHistory> getUserHistoryByCategoryId(Integer categoryid) {

		String query = "from Userhistory u where u.categories.categoryid = ?";
		@SuppressWarnings("unchecked")
		List<UserHistory> userhistory = (List<UserHistory>) hibernateTemplate.find(query, categoryid);

		if (userhistory.isEmpty()) {
			return null;
		} else {
			return userhistory;
		}
		
		
	}

	@Override
	public List<UserHistory> getUserHistoryByUserIdAndCategoryId(
			Integer userid, Integer categoryid) {
		
		String query = "from Userhistory u where u.users.userid = ? and u.categories.categoryid = ?";
		@SuppressWarnings("unchecked")
		List<UserHistory> userhistory = (List<UserHistory>) hibernateTemplate.find(query, userid, categoryid);

		if (userhistory.isEmpty()) {
			return null;
		} else {
			return userhistory;
		}
	}

}

package com.movieproject.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.movieproject.dao.interfaces.IDaoInterfaceForUserRatings;
import com.movieproject.entities.UserRatings;

@Transactional
public class UserRatingsDao implements IDaoInterfaceForUserRatings {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public UserRatings save(UserRatings userratings) {

		hibernateTemplate.save(userratings);
		
		return null;
	}

	@Override
	public UserRatings update(UserRatings userratings) {

		hibernateTemplate.update(userratings);
			
		return null;
	}

	@Override
	public void delete(UserRatings userratings) {

		hibernateTemplate.delete(userratings);
		
	}

	@Override
	public UserRatings getUserRatingsById(Integer user_rating_id) {

		String query = "from Userratings u where u.user_rating_id = ?";
		@SuppressWarnings("unchecked")
		List<UserRatings> userratings = (List<UserRatings>) hibernateTemplate.find(query, user_rating_id);

		if (userratings.isEmpty()) {
			return null;
		} else {
			return userratings.get(0);
		}
		
	}

	@Override
	public List<UserRatings> getUserRatingsByMovieId(Integer movieid) {

		String query = "from Userratings u where u.movie.movieid = ?";
		@SuppressWarnings("unchecked")
		List<UserRatings> userratings = (List<UserRatings>) hibernateTemplate.find(query, movieid);

		if (userratings.isEmpty()) {
			return null;
		} else {
			return userratings;
		}
		
		
	}

	@Override
	public List<UserRatings> getUserRatingsByUserIdAndMovieId(Integer userid,
			Integer movieid) {

		String query = "from Userratings u where u.users.userid = ? and u.movie.movieid = ?";
		@SuppressWarnings("unchecked")
		List<UserRatings> userratings = (List<UserRatings>) hibernateTemplate.find(query, userid ,movieid);

		if (userratings.isEmpty()) {
			return null;
		} else {
			return userratings;
		}
		
	}

	@Override
	public List<UserRatings> getUserRatingsByRating(Integer rating) {

		String query = "from Userratings u where u.rating = ?";
		@SuppressWarnings("unchecked")
		List<UserRatings> userratings = (List<UserRatings>) hibernateTemplate.find(query, rating);

		if (userratings.isEmpty()) {
			return null;
		} else {
			return userratings;
		}
	}

}

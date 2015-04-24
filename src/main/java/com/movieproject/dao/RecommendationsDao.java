package com.movieproject.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.movieproject.dao.interfaces.IDaoInterfaceForRecommendations;
import com.movieproject.entities.Actors;
import com.movieproject.entities.Recommendations;

@Transactional
public class RecommendationsDao implements IDaoInterfaceForRecommendations {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public void save(Recommendations recommendation) {

		hibernateTemplate.save(recommendation);
	}

	@Override
	public void update(Recommendations recommendation) {

		hibernateTemplate.update(recommendation);
	}

	@Override
	public void delete(Recommendations recommendation) {

		hibernateTemplate.delete(recommendation);
	}

	@Override
	public Recommendations getRecommendationById(Integer reconmmendationid) {

		String query = "from Recommendations r where r.reconmmendationid = ?";
		@SuppressWarnings("unchecked")
		List<Recommendations> recommendations = (List<Recommendations>) hibernateTemplate.find(query, reconmmendationid);

		if (recommendations.isEmpty()) {
			return null;
		} else {
			return recommendations.get(0);
		}
	}

	@Override
	public List<Recommendations> getRecommendationsByUserId(Integer userid) {
		
		String query = "from Recommendations r where r.userid = ?";
		@SuppressWarnings("unchecked")
		List<Recommendations> recommendations = (List<Recommendations>) hibernateTemplate.find(query, userid);

		if (recommendations.isEmpty()) {
			return null;
		} else {
			return recommendations;
		}
	}

	@Override
	public List<Recommendations> getRecommendationsByMovieId(Integer movieid) {

		String query = "from Recommendations r where r.movieid = ?";
		@SuppressWarnings("unchecked")
		List<Recommendations> recommendations = (List<Recommendations>) hibernateTemplate.find(query, movieid);

		if (recommendations.isEmpty()) {
			return null;
		} else {
			return recommendations;
		}
	}

	@Override
	public Recommendations getRecommendationsByUserIdAndMovieId(	Integer userid, Integer movieid) {
		
		String query = "from Recommendations r where r.userid = ? and r.movieid = ?";
		@SuppressWarnings("unchecked")
		List<Recommendations> recommendations = (List<Recommendations>) hibernateTemplate.find(query, userid, movieid);

		if (recommendations.isEmpty()) {
			return null;
		} else {
			return recommendations.get(0);
		}
	}

	@Override
	public List<Recommendations> getRecommendationsByUserIdAndMovieIdAndRefUserId(
			Integer userid, Integer movieid, Integer ref_userid) {

		String query = "from Recommendations r where r.userid = ? and r.movieid = ? and ref_userid = ?";
		@SuppressWarnings("unchecked")
		List<Recommendations> recommendations = (List<Recommendations>) hibernateTemplate.find(query, userid, movieid, ref_userid);

		if (recommendations.isEmpty()) {
			return null;
		} else {
			return recommendations;
		}
	}

	@Override
	public List<Recommendations> getUnseenRecommendedMovies(Integer userid) {

		String query = "from Recommendations r where r.userid = ? and r.seenflag is false";
		@SuppressWarnings("unchecked")
		List<Recommendations> recommendations = (List<Recommendations>) hibernateTemplate.find(query, userid);

		if (recommendations.isEmpty()) {
			return null;
		} else {
			return recommendations;
		}
	}
	
}

package com.movieproject.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.movieproject.dao.interfaces.IDaoInterfaceForMovie;
import com.movieproject.dao.interfaces.IDaoInterfaceForRecommendations;
import com.movieproject.entities.Movie;
import com.movieproject.entities.Recommendations;
import com.movieproject.util.MovieAppUtil;

public class RecommendationsImpl {

	@Autowired
	IDaoInterfaceForRecommendations recommendationDao;

	@Autowired
	MovieAppUtil movieAppUtils;

	@Autowired
	IDaoInterfaceForMovie movieDao;

	/*************************************************************************************************************/

	public void addRecommendation(Integer userid, Integer movieid,
			Integer ref_userid) {

		boolean recoFlag = verifyRecommendationForUserWithMovieExists(userid,
				movieid);

		if (recoFlag == true) {
			updateRecommendationAsNew(userid, movieid, ref_userid);
		} else {
			Recommendations recommendation = new Recommendations();

			Integer reconmmendationid = movieAppUtils.generateIdValue(100);

			recommendation.setReconmmendationid(reconmmendationid);
			recommendation.setUserid(userid);
			recommendation.setMovieid(movieid);
			recommendation.setSeenflag(false);
			recommendation.setRef_userid(ref_userid);

			recommendationDao.save(recommendation);
		}
	}

	public void updateRecommendations(Integer userid, Integer movieid) {
		boolean recoFlag = verifyRecommendationForUserWithMovieExists(userid,
				movieid);

		if (recoFlag) {
			updateRecommendationAsSeen(userid, movieid);
		}

	}

	/*************************************************************************************************************/

	public void updateRecommendationAsSeen(Integer userid, Integer movieid) {
		Recommendations recommendation = recommendationDao
				.getRecommendationsByUserIdAndMovieId(userid, movieid);

		if (recommendation.isSeenflag() == false) {
			recommendation.setSeenflag(true);
		}

		recommendationDao.update(recommendation);
	}

	public void updateRecommendationAsNew(Integer userid, Integer movieid,
			Integer ref_userid) {
		Recommendations recommendation = recommendationDao
				.getRecommendationsByUserIdAndMovieId(userid, movieid);

		if (recommendation.isSeenflag() == true) {
			recommendation.setSeenflag(false);
		}
		recommendation.setRef_userid(ref_userid);

		recommendationDao.update(recommendation);
	}

	/*************************************************************************************************************/

	public boolean verifyRecommendationForUserExists(Integer userid) {
		List<Recommendations> recommendation = recommendationDao
				.getRecommendationsByUserId(userid);

		if (recommendation == null) {
			return false;
		}

		return true;
	}

	public boolean verifyUnseenRecommendationsExistForUser(Integer userid) {
		List<Recommendations> recommendations = recommendationDao
				.getUnseenRecommendedMovies(userid);

		if (recommendations == null) {
			return false;
		}

		return true;
	}

	/*************************************************************************************************************/

	public boolean verifyRecommendationForUserWithMovieExists(Integer userid,
			Integer movieid) {

		Recommendations recommendation = recommendationDao
				.getRecommendationsByUserIdAndMovieId(userid, movieid);

		if (recommendation == null) {
			return false;
		}

		return true;
	}

	/*************************************************************************************************************/

	public List<Movie> getUnseenRecommondedMovies(Integer userid) {
		List<Recommendations> recommendations = recommendationDao
				.getUnseenRecommendedMovies(userid);

		if (recommendations == null) {
			return null;
		}

		List<Integer> listOfUnseenMovieIds = new ArrayList<Integer>();

		int counter = 0;

		for (Recommendations recommendation : recommendations) {
			counter++;
			listOfUnseenMovieIds.add(recommendation.getMovieid());
			if (counter == 6) {
				break;
			}
		}

		Movie movie = new Movie();
		List<Movie> movies = new ArrayList<Movie>();

		for (Integer id : listOfUnseenMovieIds) {
			movie = movieDao.getMovieById(id);

			if (movie != null) {
				movies.add(movie);
			}

		}

		return movies;
	}

	/*************************************************************************************************************/

	public void deleteRecommendationEntries(Integer movieid) {

		List<Recommendations> recommendations = recommendationDao
				.getRecommendationsByMovieId(movieid);

		if (recommendations != null) {
			for (Recommendations recommendation : recommendations) {
				recommendationDao.delete(recommendation);
			}

		}

	}

}

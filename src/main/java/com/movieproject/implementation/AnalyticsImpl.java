package com.movieproject.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.movieproject.dao.interfaces.IDaoInterfaceForMovie;
import com.movieproject.entities.Movie;
import com.movieproject.entities.UserHistory;

public class AnalyticsImpl {

	@Autowired
	MovieImpl movieImpl;

	@Autowired
	IDaoInterfaceForMovie movieDao;

	@Autowired
	UserHistoryImpl userHistoryImpl;

	/*****************************************************************************/

	public List<Movie> getTopRatedSixMovies()
	{

		List<Movie> fetchedMovies = movieDao.getMoviesOrderedByTopRatings();

		List<Movie> movies = new ArrayList<Movie>();


		if (fetchedMovies == null) {
			return null;
		} else {

			int size = fetchedMovies.size();

			int counter = 0;

			for(int i = 0 ; i < size; i++)
			{
				counter++;
				movies.add(fetchedMovies.get(i));

				if (counter == 6){break;}
			}

			return movies;
		}
	}

	/*****************************************************************************/	


	public List<Movie> getRecentlyWatchedMovies(Integer userid)
	{

		List<UserHistory> userhistoryentries = userHistoryImpl.getUserHistoryByUserId(userid); 

		List<Integer> movieIdList = new ArrayList<Integer>();

		Integer movieid = 0;

		int counter = 0;

		if(userhistoryentries == null){return null;}
		else{

			for(UserHistory userHistoryEntry : userhistoryentries)
			{
				movieid = userHistoryEntry.getMovieid().getMovieid();

				if(! movieIdList.contains(movieid))
				{
					counter++;
					movieIdList.add(movieid);
				}

				if (counter == 6){break;}
			}

			Movie movie = new Movie();
			List<Movie> movies = new ArrayList<Movie>();

			for(Integer id : movieIdList)
			{
				movie = movieDao.getMovieById(id);

				if(movie != null)
				{
					movies.add(movie);
				}

			}

			return movies;
		}

	}	

	/*****************************************************************************/	

}

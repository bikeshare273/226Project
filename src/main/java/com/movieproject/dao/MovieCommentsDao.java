package com.movieproject.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.movieproject.dao.interfaces.IDaoInterfaceForMovieComments;
import com.movieproject.entities.MovieComments;

@Transactional
public class MovieCommentsDao implements IDaoInterfaceForMovieComments {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public MovieComments save(MovieComments moviecomments) {

		hibernateTemplate.save(moviecomments);
		
		return null;
	}

	@Override
	public MovieComments update(MovieComments moviecomments) {

		hibernateTemplate.update(moviecomments);
		
		return null;
	}

	@Override
	public void delete(MovieComments moviecomments) {

		hibernateTemplate.delete(moviecomments);
		
	}

	@Override
	public MovieComments getMovieCommentsById(Integer movie_comment_id) {

		String query = "from MovieComments m where m.movie_comment_id = ?";
		@SuppressWarnings("unchecked")
		List<MovieComments> movieComments = (List<MovieComments>) hibernateTemplate.find(query, movie_comment_id);

		if (movieComments.isEmpty()) {
			return null;
		} else {
			return movieComments.get(0);
		}
	}

	@Override
	public List<MovieComments> getMovieCommentsByMovieId(Integer movieid) {

		String query = "from Moviecomments m where m.movieid.movieid = ?";
		@SuppressWarnings("unchecked")
		List<MovieComments> movieComments = (List<MovieComments>) hibernateTemplate.find(query, movieid);

		if (movieComments.isEmpty()) {
			return null;
		} else {
			return movieComments;
		}
		
		
	}

	@Override
	public List<MovieComments> getMovieCommentsByUserId(Integer userid) {

		String query = "from Moviecomments m where m.userid.userid = ?";
		@SuppressWarnings("unchecked")
		List<MovieComments> movieComments = (List<MovieComments>) hibernateTemplate.find(query, userid);

		if (movieComments.isEmpty()) {
			return null;
		} else {
			return movieComments;
		}
	}

	@Override
	public List<MovieComments> getMovieCommentsByMovieAndUser(Integer movieid,	Integer userid) {

		String query = "from Moviecomments m where  m.movieid.movieid = ? and m.userid.userid = ?";
		@SuppressWarnings("unchecked")
		List<MovieComments> movieComments = (List<MovieComments>) hibernateTemplate.find(query, movieid, userid);

		if (movieComments.isEmpty()) {
			return null;
		} else {
			return movieComments;
		}
		
	}

}

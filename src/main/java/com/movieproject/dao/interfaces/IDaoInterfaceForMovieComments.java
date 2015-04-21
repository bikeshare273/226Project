package com.movieproject.dao.interfaces;

import java.util.List;

import com.movieproject.entities.MovieComments;

public interface IDaoInterfaceForMovieComments {

	public MovieComments save(MovieComments moviecomments);
	public MovieComments update(MovieComments moviecomments);
	public void   delete(MovieComments moviecomments);
	public MovieComments getMovieCommentsById(Integer movie_comment_id);
	public List<MovieComments> getMovieCommentsByMovieId(Integer movieid);
	public List<MovieComments> getMovieCommentsByUserId(Integer userid);
	public List<MovieComments> getMovieCommentsByMovieAndUser(Integer movieid, Integer userid);
	
}

package com.movieproject.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.movieproject.dao.interfaces.IDaoInterfaceForComment;
import com.movieproject.dao.interfaces.IDaoInterfaceForMovie;
import com.movieproject.dao.interfaces.IDaoInterfaceForUsers;
import com.movieproject.dto.CommentDTO;
import com.movieproject.entities.Comment;
import com.movieproject.entities.Movie;
import com.movieproject.entities.Users;
import com.movieproject.util.MovieAppUtil;

public class CommentImpl {

	@Autowired
	IDaoInterfaceForUsers userDao;
	
	@Autowired
	IDaoInterfaceForMovie movieDao;
	
	@Autowired
	IDaoInterfaceForComment commentDao;
		
	@Autowired
	MovieAppUtil movieAppUtils;
	
	
	public Integer addComment(CommentDTO commentDTO, Integer userid)
	{
		
		Comment comment = new Comment();
		
		Integer commentid = movieAppUtils.generateIdValue(4000);
		comment.setCommentid(commentid);
		
		Movie movie = movieDao.getMovieById(commentDTO.getMovieid());
		comment.setMovieid(movie);
		
		Users user = userDao.getUserById(userid);
		comment.setUserid(user);
		
		String commentString = commentDTO.getComment(); 
		comment.setComment(commentString);
		
		commentDao.save(comment);
	
		return commentid ;
	}
	
/*****************************************************************************************/
	
	public Comment getComment(Integer commentid)
	{
		
		Comment comment = commentDao.getCommentsById(commentid);
		
		if(comment == null) {return null;}
		
		return comment ;
	}
	
/*****************************************************************************************/
	
	public List<Comment> getAllCommentsForMovie(Integer movieid)
	{
		
		List<Comment> comments = commentDao.getCommentsByMovie(movieid);
		
		if(comments == null) {return null;}
		
		return comments ;
	}
	
/*****************************************************************************************/
	
	
	public List<Comment> getAllCommentsByUserForAllMovies(Integer userid)
	{
		
		List<Comment> comments = commentDao.getCommentsByUser(userid);
		
		if(comments == null) {return null;}
		
		return comments ;
	}
	
/*****************************************************************************************/
	
	public List<Comment> getAllCommentsByUserForAMovie(Integer userid, Integer movieid)
	{
		
		List<Comment> comments = commentDao.getCommentsByUserAndMovie(userid, movieid);
		
		if(comments == null) {return null;}
		
		return comments ;
	}
	
/*****************************************************************************************/	
	
	public void deleteCommment(Integer commentid)
	{
		Comment comment = commentDao.getCommentsById(commentid);
		
		commentDao.delete(comment);
	}
	
/*****************************************************************************************/
	
	
	
	
}

package com.movieproject.dao.interfaces;

import java.util.List;

import com.movieproject.entities.Comment;

public interface IDaoInterfaceForComment {
	
	public void save(Comment comment);
	public void update(Comment comment);
	public void   delete(Comment comment);
	public Comment getCommentsById(Integer commentid);
	public List<Comment> getCommentsByMovie(Integer movieid);
	public List<Comment> getCommentsByUser(Integer userid);
	public List<Comment> getCommentsByUserAndMovie(Integer userid, Integer movieid);
		
}

package com.movieproject.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.movieproject.dao.interfaces.IDaoInterfaceForComment;
import com.movieproject.entities.Comment;

@Transactional
public class CommentDao implements IDaoInterfaceForComment {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public void save(Comment comment) {

		hibernateTemplate.save(comment);
		

	}

	@Override
	public void update(Comment comment) {

		hibernateTemplate.update(comment);
		
	}

	@Override
	public void delete(Comment comment) {

		hibernateTemplate.delete(comment);
		
	}

	@Override
	public Comment getCommentsById(Integer commentid) {
	
		String query = "from Comments c where c.commentid = ?";
		@SuppressWarnings("unchecked")
		List<Comment> comment = (List<Comment>) hibernateTemplate.find(query, commentid);

		if (comment.isEmpty()) {
			return null;
		} else {
			return comment.get(0);
		}
	}

	@Override
	public List<Comment> getCommentsByMovie(Integer movieid) {
	
		String query = "from Comments c where c.movie.movieid = ?";
		@SuppressWarnings("unchecked")
		List<Comment> commentsForTheMovie = (List<Comment>) hibernateTemplate.find(query, movieid);

		if (commentsForTheMovie.isEmpty()) {
			return null;
		} else {
			return commentsForTheMovie;
		}
	}

	@Override
	public List<Comment> getCommentsByUser(Integer userid) {
		
		String query = "from Comments c where c.users.userid = ?";
		@SuppressWarnings("unchecked")
		List<Comment> commentsByTheUser = (List<Comment>) hibernateTemplate.find(query, userid);

		if (commentsByTheUser.isEmpty()) {
			return null;
		} else {
			return commentsByTheUser;
		}
	}

	@Override
	public List<Comment> getCommentsByUserAndMovie(Integer userid,	Integer movieid) {
		
		String query = "from Comments c where c.users.userid = ? and c.movie.movieid = ?";
		@SuppressWarnings("unchecked")
		List<Comment> commentByUserForAMovie = (List<Comment>) hibernateTemplate.find(query, userid, movieid);

		if (commentByUserForAMovie.isEmpty()) {
			return null;
		} else {
			return commentByUserForAMovie;
		}
	}

}

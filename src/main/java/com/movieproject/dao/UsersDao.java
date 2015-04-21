package com.movieproject.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.movieproject.dao.interfaces.IDaoInterfaceForUsers;
import com.movieproject.entities.Users;

@Transactional
public class UsersDao implements IDaoInterfaceForUsers {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Users save(Users user) {

		hibernateTemplate.save(user);
		
		return null;
	}

	@Override
	public Users update(Users user) {

		hibernateTemplate.update(user);
		
		return null;
	}

	@Override
	public void delete(Users user) {

		hibernateTemplate.delete(user);
		
	}

	@Override
	public Users getUserById(Integer userid) {

		String query = "from Users u where u.userid = ?";
		@SuppressWarnings("unchecked")
		List<Users> users = (List<Users>) hibernateTemplate.find(query, userid);

		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
		
	}

	@Override
	public Users getUserByUserName(String username) {

		String query = "from Users u where u.username = ?";
		@SuppressWarnings("unchecked")
		List<Users> users = (List<Users>) hibernateTemplate.find(query, username);

		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
		
	}
	
	@Override
	public Users getUserByIdAndUserName(Integer userid, String username) {

		String query = "from Users u where u.userid = ? and u.username = ?";
		@SuppressWarnings("unchecked")
		List<Users> users = (List<Users>) hibernateTemplate.find(query, userid, username);

		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
		
	}
	
	
	
	

	@Override
	public Users getUserByUserMobileNumber(String mobile_number) {

		String query = "from Users u where u.mobile_number = ?";
		@SuppressWarnings("unchecked")
		List<Users> users = (List<Users>) hibernateTemplate.find(query, mobile_number);

		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
		
	}

	@Override
	public List<Users> getUsersByCity(String city) {

		String query = "from Users u where u.city = ?";
		@SuppressWarnings("unchecked")
		List<Users> users = (List<Users>) hibernateTemplate.find(query, city);

		if (users.isEmpty()) {
			return null;
		} else {
			return users;
		}
		
	}

	@Override
	public List<Users> getUsersByCountry(String country) {

		String query = "from Users u where u.country = ?";
		@SuppressWarnings("unchecked")
		List<Users> users = (List<Users>) hibernateTemplate.find(query, country);

		if (users.isEmpty()) {
			return null;
		} else {
			return users;
		}
		
	}

}

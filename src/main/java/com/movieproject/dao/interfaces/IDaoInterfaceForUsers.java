package com.movieproject.dao.interfaces;

import java.util.List;

import com.movieproject.entities.Users;

public interface IDaoInterfaceForUsers {

	public Users save(Users user);
	public Users update(Users user);
	public void delete(Users user);
	public Users getUserById(Integer userid);
	public Users getUserByUserName(String username);
	public Users getUserByIdAndUserName(Integer userid, String username);
	public Users getUserByUserMobileNumber(String mobile_number);
	public List<Users> getUsersByCity(String city);
	public List<Users> getUsersByCountry(String country);
	public List<Users> getAllUsers();

}

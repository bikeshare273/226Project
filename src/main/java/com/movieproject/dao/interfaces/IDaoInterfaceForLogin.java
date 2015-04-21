package com.movieproject.dao.interfaces;

import java.util.List;

import com.movieproject.entities.Login;

public interface IDaoInterfaceForLogin {
	
	public Login save(Login login);
	public Login update(Login login);
	public void   delete(Login login);
	public List<Login> getAllLogins();
	public Login getLoginByUserId(Integer userid);
	public Login getLoginByUserName(String username);
	public Login getLoginByUserNameAndPassword(String username, String password);
	public Login getLoginByUserNameAndSessionId(String username, Integer sessionid);
	
	/*
		public void save(Login login);
		public Login getObject(String id);
		public Login getUserBasedOnUsername(String username);
		public List<Login> getAllObjects();
		public void removeObject(String id);
		public void updateObject(Login login);
		public Login getObject(String username, String password);
		public Login getObjectOnSession(String username, String sessionid);*/

}

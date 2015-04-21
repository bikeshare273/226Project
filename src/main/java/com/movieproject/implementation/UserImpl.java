package com.movieproject.implementation;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;

import com.movieproject.dao.interfaces.IDaoInterfaceForLogin;
import com.movieproject.dao.interfaces.IDaoInterfaceForUsers;
import com.movieproject.dto.UserDTO;
import com.movieproject.entities.Login;
import com.movieproject.entities.Users;
import com.movieproject.util.MovieAppUtil;

import org.apache.commons.beanutils.BeanUtils;

public class UserImpl {
	
	@Autowired
	IDaoInterfaceForUsers usersDao;
	
	@Autowired
	IDaoInterfaceForLogin loginDao;

	@Autowired
	MovieAppUtil movieAppUtils;
	
	
/***********************************************************************************/
	
	public UserDTO createUser(UserDTO user)
	
	{
		
		Users userObject = new Users();
		Login loginObject = new Login();

		try { BeanUtils.copyProperties(userObject, user);} 
		catch (IllegalAccessException e) { e.printStackTrace(); } 
		catch (InvocationTargetException e) { e.printStackTrace(); }
		
		Integer userid = movieAppUtils.generateIdValue(1000);
		userObject.setUserid(userid);
		//userObject.setEmail(user.getUsername());

		loginObject.setUserid(userid);
		loginObject.setUsername(user.getUsername());
		loginObject.setPassword(movieAppUtils.passwordEncrypter(user.getPassword()));
	
		usersDao.save(userObject);
		loginDao.save(loginObject);
		
		user.setUserid(userid);
		
		return user;
		
	}
	
/************************************************************************************/
	
public UserDTO updateUser(UserDTO user)
			
{
		Users userObject = usersDao.getUserById(user.getUserid());
		
		if(userObject == null) {return null;}
		
		user.setEmail(userObject.getEmail());
	
		try { BeanUtils.copyProperties(userObject, user);} 
		catch (IllegalAccessException e) { e.printStackTrace(); } 
		catch (InvocationTargetException e) { e.printStackTrace(); }
				
		usersDao.update(userObject);
		
		return user;
}
	
/************************************************************************************/
		
public UserDTO getUser(Integer userid)

{
		UserDTO userDTO = new UserDTO();

		Users user = usersDao.getUserById(userid);
		Login login = loginDao.getLoginByUserId(userid);
		
		try { BeanUtils.copyProperties(userDTO, user);} 
		catch (IllegalAccessException e) { e.printStackTrace(); } 
		catch (InvocationTargetException e) { e.printStackTrace(); }
				
		userDTO.setUsername(login.getUsername());
		
		return userDTO;
}
	
/************************************************************************************/
	
	
public boolean checkUniqueUsername(String username)

{
		Users user = usersDao.getUserByUserName(username);
			
		if(user == null) {return true;}
		else			 {return false;}

}

/************************************************************************************/
	
}

package com.movieproject.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.movieproject.dao.interfaces.IAuthInterfaceForLogin;
import com.movieproject.dao.interfaces.IDaoInterfaceForLogin;
import com.movieproject.dto.LoginDTO;
import com.movieproject.entities.Login;
import com.movieproject.util.MovieAppUtil;

public class AuthenticationImplemtation implements IAuthInterfaceForLogin{

	@Autowired
	IDaoInterfaceForLogin loginDao;

	@Autowired
	MovieAppUtil movieAppUtils;
	

	@Override
	public LoginDTO login(LoginDTO loginDTO) {

		String username = loginDTO.getUsername();
		String password = loginDTO.getPassword();
		System.out.println("password-> "+password);
		password = movieAppUtils.passwordEncrypter(password);
		System.out.println("password en -> "+password);
		try{

			Login login = loginDao.getLoginByUserNameAndPassword(username, password);
			
			if(login == null){

			loginDTO.setMessage("Invalid Username/Password");
			loginDTO.setLoginValidationStatus(false);
			}
	
			else
	
			{
				/* Generate New SessionId */
				
				Integer sessionid = movieAppUtils.generateIdValue(0);
				
				login.setSessionid(sessionid);
				
				loginDao.update(login);
				
				//set session id in header
				loginDTO.setSessionId(sessionid);
				loginDTO.setUserid(login.getUserid());
				loginDTO.setMessage("Login Sucessful");
				loginDTO.setLoginValidationStatus(true);
			}
	}catch(Exception e){
		e.printStackTrace();
	}
	return loginDTO;
	}
}


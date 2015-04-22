package com.movieproject.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.movieproject.configuration.MovieAppBeansConfiguration;
import com.movieproject.dao.TestDao;
import com.movieproject.dao.interfaces.DemoInterface;
import com.movieproject.dao.interfaces.IAuthInterfaceForLogin;
import com.movieproject.dao.interfaces.ITestDao;
import com.movieproject.dto.DemoDto;
import com.movieproject.dto.LoginDTO;
import com.movieproject.dto.MovieDTO;
import com.movieproject.dto.UserDTO;
import com.movieproject.entities.Movie;
import com.movieproject.entities.Test;
import com.movieproject.entities.Users;
import com.movieproject.implementation.MovieImpl;
import com.movieproject.implementation.UserImpl;
import com.movieproject.interceptor.SessionValidatorInterceptor;
import com.movieproject.util.MovieAppUtil;


@RestController
@EnableAutoConfiguration
@ComponentScan
@RequestMapping("/api/v1/*")
@Import(MovieAppBeansConfiguration.class)
public class MovieAppController extends WebMvcConfigurerAdapter {	
	
	@Autowired
	DemoInterface demoInterface;
	
	@Autowired
	ITestDao testDao;
	
	@Autowired
	UserImpl userImpl;
	
	@Autowired
	MovieImpl movieImpl;
	
	
	@Autowired
	IAuthInterfaceForLogin authInterfaceForLogin;
	
	@Autowired
	SessionValidatorInterceptor sessionValidatorInterceptor;
	
	@Autowired
	MovieAppUtil movieAppUtils;
	
/***********************************************************************************************/
    
							/* Login, Logout and Session Management */

/***********************************************************************************************/
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(sessionValidatorInterceptor).addPathPatterns("/api/v1/users/*");
        registry.addInterceptor(sessionValidatorInterceptor).addPathPatterns("/api/v1/loggedin");
    }
	
    @RequestMapping(value="/loggedin", method = RequestMethod.GET)
	@ResponseBody
	private boolean logeedin() {
        return true;
	}
    
    
    @RequestMapping("/login")
	@ResponseBody
	private LoginDTO login(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response) {
		loginDTO = authInterfaceForLogin.login(loginDTO);
        response.addCookie(new Cookie("sessionid", Integer.toString(loginDTO.getSessionId())));
        response.addCookie(new Cookie("username", loginDTO.getUsername()));
        response.addCookie(new Cookie("userid", Integer.toString(loginDTO.getUserid())));
        return loginDTO;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	private boolean logout(HttpServletResponse response) {
		
        response.addCookie(new Cookie("sessionid", ""));
        response.addCookie(new Cookie("username", ""));
        response.addCookie(new Cookie("userid", ""));
        
        return true;
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/verifyUniqueUsername/{username}", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkUniqueUsername(@PathVariable String username) {
		return userImpl.checkUniqueUsername(username);
	}
    	
    
/***********************************************************************************************/
    
									/* User Entity APIs */
	
/***********************************************************************************************/
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public UserDTO createUser(@Valid @RequestBody UserDTO user) {
		return userImpl.createUser(user);
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	@ResponseBody
	public UserDTO updateUser(@Valid @RequestBody UserDTO user, @CookieValue("userid") int userid) {
		
		user.setUserid(userid);
		
		return userImpl.updateUser(user);
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/users/{userid}", method = RequestMethod.GET)
	@ResponseBody
	public UserDTO getUser(@PathVariable Integer userid) {
		
		return userImpl.getUser(userid);		
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/fetchuser", method = RequestMethod.GET)
	@ResponseBody
	public UserDTO fetchUser(@CookieValue("userid") int userid) {
		
		return userImpl.getUser(userid);		
	
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/fetchallusers", method = RequestMethod.GET)
	@ResponseBody
	public List<Users> fetchAllUsers() {
		
		return userImpl.getAllUsers();		
	
	}
	
    
/***********************************************************************************************/
										
									/* Movie Entity APIs */
	
/***********************************************************************************************/

	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/addmovie", method = RequestMethod.POST)
	@ResponseBody
	public void addMovie(@Valid @RequestBody MovieDTO movie) {
		
		movieImpl.addMovie(movie);
	}
		
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/getmovie", method = RequestMethod.GET)
	@ResponseBody
	public Movie getMovie(@Valid @RequestBody Integer movieid) {
		
		return movieImpl.getMovie(movieid);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/fetchallmovies", method = RequestMethod.GET)
	@ResponseBody
	public List<Movie> fetchAllMovies() {
		
		return movieImpl.getAllMovies();		
	
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/getmoviesofcategory", method = RequestMethod.GET)
	@ResponseBody
	public List<Movie> getMoviesOfCategoty(@Valid @RequestBody Integer categoryid) {
		
		return movieImpl.getAllMoviesForCategory(categoryid);
	
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/getmoviesforlanguage", method = RequestMethod.GET)
	@ResponseBody
	public List<Movie> getMoviesForLanguage(@Valid @RequestBody String language) {
		
		return movieImpl.getAllMoviesForLanguage(language);
	
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/getmoviesforyear", method = RequestMethod.GET)
	@ResponseBody
	public List<Movie> getMoviesForYear(@Valid @RequestBody Integer year) {
		
		return movieImpl.getAllMoviesForYear(year);
	
	}
			
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/deletemovie", method = RequestMethod.POST)
	@ResponseBody
	public void deleteMovie(@Valid @RequestBody Integer movieid) {
		
		movieImpl.deleteMovie(movieid);
	
	}
	
/***********************************************************************************************/
	
								/* MovieApp */

/***********************************************************************************************/
	
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	// User Related Operations
	@RequestMapping(value = "/demo/{username}", method = RequestMethod.GET)
	@ResponseBody
	public DemoDto demoWelcomeController(@PathVariable String username) {
		DemoDto demoDto = new DemoDto();
		demoDto.setMessage(demoInterface.printWelcomeMessage(username));
		return demoDto;
	}
	
	@RequestMapping(value = "/demo/test/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Test dbtest(@PathVariable int id) {
		
		Test test = new Test();
		
		DateTime dt = new DateTime();
				
		test.setTestIdString(dt.toString());
		test.setTestString("Last loaded record => " + id);
		testDao.saveTest(test);
		Test savedTest = testDao.getTest(test);
		
		return savedTest;
	
	}
	
	

}

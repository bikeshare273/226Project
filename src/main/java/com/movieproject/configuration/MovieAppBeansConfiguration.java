package com.movieproject.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;

import com.movieproject.dao.ActorsDao;
import com.movieproject.dao.CategoriesDao;
import com.movieproject.dao.CommentDao;
import com.movieproject.dao.LoginDao;
import com.movieproject.dao.MovieActorsDao;
import com.movieproject.dao.MovieCommentsDao;
import com.movieproject.dao.MovieDao;
import com.movieproject.dao.RecommendationsDao;
import com.movieproject.dao.TestDao;
import com.movieproject.dao.UserHistoryDao;
import com.movieproject.dao.UserRatingsDao;
import com.movieproject.dao.UsersDao;
import com.movieproject.dao.interfaces.DemoInterface;
import com.movieproject.dao.interfaces.IAuthInterfaceForLogin;
import com.movieproject.dao.interfaces.IDaoInterfaceForActors;
import com.movieproject.dao.interfaces.IDaoInterfaceForCategories;
import com.movieproject.dao.interfaces.IDaoInterfaceForComment;
import com.movieproject.dao.interfaces.IDaoInterfaceForLogin;
import com.movieproject.dao.interfaces.IDaoInterfaceForMovie;
import com.movieproject.dao.interfaces.IDaoInterfaceForMovieActors;
import com.movieproject.dao.interfaces.IDaoInterfaceForMovieComments;
import com.movieproject.dao.interfaces.IDaoInterfaceForRecommendations;
import com.movieproject.dao.interfaces.IDaoInterfaceForUserHistory;
import com.movieproject.dao.interfaces.IDaoInterfaceForUserRatings;
import com.movieproject.dao.interfaces.IDaoInterfaceForUsers;
import com.movieproject.dao.interfaces.ITestDao;
import com.movieproject.implementation.AnalyticsImpl;
import com.movieproject.implementation.AuthenticationImplemtation;
import com.movieproject.implementation.CommentImpl;
import com.movieproject.implementation.DemoImpl;
import com.movieproject.implementation.MovieImpl;
import com.movieproject.implementation.RecommendationsImpl;
import com.movieproject.implementation.UserHistoryImpl;
import com.movieproject.implementation.UserImpl;
import com.movieproject.implementation.UserRatingsImpl;
import com.movieproject.interceptor.SessionValidatorInterceptor;
import com.movieproject.util.MovieAppUtil;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class MovieAppBeansConfiguration {
	
/********************************************************************************************************************/
	
													/* Impl Beans */

/********************************************************************************************************************/
	
	@Bean
    public SessionValidatorInterceptor sessionValidatorInterceptor() {
        return new SessionValidatorInterceptor();
    }
	
	@Bean
	public DemoInterface getDemo(){
		return new DemoImpl();
	}	
		
	
	@Bean
	public MovieAppUtil getMovieAppUtil(){
		return new MovieAppUtil();
	}
	
	@Bean
	public UserImpl getUserImpl(){
		return new UserImpl();
	}
	
	@Bean
	public MovieImpl getMovieImpl(){
		return new MovieImpl();
	}
	
	
	@Bean
	public CommentImpl getCommentImpl(){
		return new CommentImpl();
	}
	
	@Bean
	public UserRatingsImpl getUserRatingsImpl(){
		return new UserRatingsImpl();
	}
		
	@Bean
	public IAuthInterfaceForLogin getAuthInterfaceForLogin(){
		return new AuthenticationImplemtation();
	}
	
	@Bean
	public AnalyticsImpl getAalyticsImpl(){
		return new AnalyticsImpl();
	}
	
	@Bean
	public UserHistoryImpl getUserHistoryImpl(){
		return new UserHistoryImpl();
	}
	
	@Bean
	public RecommendationsImpl getRecommendationsImpl(){
		return new RecommendationsImpl();
	}
	
	
/********************************************************************************************************************/	

													/* DAO Beans */
	
/********************************************************************************************************************/
	
	@Bean
	public ITestDao testdao() {
		return new TestDao();
	}
		
	@Bean
	public IDaoInterfaceForActors getActorsDao(){
		return new ActorsDao();
	}
	
	@Bean
	public IDaoInterfaceForCategories getCategortiesDao(){
		return new CategoriesDao();
	}
	
	@Bean
	public IDaoInterfaceForComment getCommentDao(){
		return new CommentDao();
	}
	
	@Bean	
	public IDaoInterfaceForMovie getMovieDao(){
		return new MovieDao();
	}
	
	@Bean
	public IDaoInterfaceForMovieActors getMovieActorsDao(){
		return new MovieActorsDao();
	}
	
	@Bean
	public IDaoInterfaceForMovieComments getMovieCommentsDao(){
		return new MovieCommentsDao();
	}
	
	@Bean
	public IDaoInterfaceForUserHistory getUserHistoryDao(){
		return new UserHistoryDao();
	}
	
	@Bean
	public IDaoInterfaceForUserRatings getUserRatingsDao(){
		return new UserRatingsDao();
	}
	
	@Bean
	public IDaoInterfaceForUsers getUsersDao(){
		return new UsersDao();
	}
	
	@Bean
	public IDaoInterfaceForLogin getLoginDao(){
		return new LoginDao();
	}
	
	@Bean
	public IDaoInterfaceForRecommendations getRecommendationsDao(){
		return new RecommendationsDao();
	}	
	
		
/********************************************************************************************************************/
		
								      /* Configuration Beans */ 
	
/********************************************************************************************************************/
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
		/*dataSource.setUrl("jdbc:mysql://localhost:3306/movieapp");
		dataSource.setUsername("root");
		dataSource.setPassword("");*/
		dataSource.setUrl("jdbc:mysql://db4free.net:3306/movieapp");
		dataSource.setUsername("movieadmin");
		dataSource.setPassword("movieadmin");
		dataSource.setInitialSize(2);
		dataSource.setMaxTotal(5);
		return dataSource;
	}
	
/********************************************************************************************************************/

	/**
	 * @return HibernateTemplate()
	 * This is bean creation method for HibernateTemplate.
	 */
	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());

	}

/********************************************************************************************************************/

	/**
	 * @return SessionFactory()
	 * This is bean creation method for SessionFactory. 
	 */
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
		builder.scanPackages("com.movieproject.controller", "com.movieproject.interfaces", "com.movieproject.entities");
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		builder.addProperties(hibernateProperties);
		return builder.buildSessionFactory();
	}

/********************************************************************************************************************/

	/**
	 * @return HibernateTransactionManager()
	 * This is bean creation method for HibernateTransactionManager.
	 */
	@Bean
	@Primary
	public HibernateTransactionManager hibTransMan() {
		return new HibernateTransactionManager(sessionFactory());
	}

/********************************************************************************************************************/
	
	}


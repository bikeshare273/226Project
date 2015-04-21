package com.movieproject.dao.interfaces;

import com.movieproject.entities.Test;

public interface ITestDao {

	public void saveTest(Test test);
	
	public Test getTest(Test test);
	
}

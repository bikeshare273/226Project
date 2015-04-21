package com.movieproject.dao.interfaces;

import com.movieproject.entities.Categories;

public interface IDaoInterfaceForCategories {

	public Categories save(Categories category);
	public Categories update(Categories category);
	public void   delete(Categories category);
	public Categories getCategoryById(Integer categoryid);
	public Categories getActorByCategoryName(String categoryname);

}

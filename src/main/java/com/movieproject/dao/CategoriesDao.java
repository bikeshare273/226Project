package com.movieproject.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.movieproject.dao.interfaces.IDaoInterfaceForCategories;
import com.movieproject.entities.Categories;

@Transactional
public class CategoriesDao implements IDaoInterfaceForCategories {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Categories save(Categories category) {

		hibernateTemplate.save(category);
		
		return null;
	}

	@Override
	public Categories update(Categories category) {

		hibernateTemplate.update(category);
		
		return null;
	}

	@Override
	public void delete(Categories category) {
		
		hibernateTemplate.delete(category);
		
	}

	@Override
	public Categories getCategoryById(Integer categoryid) {

		String query = "from Categories c where c.categoryid = ?";
		@SuppressWarnings("unchecked")
		List<Categories> category = (List<Categories>) hibernateTemplate.find(query, categoryid);

		if (category.isEmpty()) {
			return null;
		} else {
			return category.get(0);
		}
	}

	@Override
	public Categories getActorByCategoryName(String categoryname) {

		String query = "from Categories c where c.categoryname = ?";
		@SuppressWarnings("unchecked")
		List<Categories> category = (List<Categories>) hibernateTemplate.find(query, categoryname);

		if (category.isEmpty()) {
			return null;
		} else {
			return category.get(0);
		}
	}

}

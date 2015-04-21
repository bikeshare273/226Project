package com.movieproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "categories")
public class Categories {

/**********************************************************************/
	
/*
 		CREATE TABLE categories(
		
		categoryid		INT(10),
		categoryname	VARCHAR(100)		NOT NULL,
		categorydesc	VARCHAR(200)		NOT NULL,

		PRIMARY KEY (categoryid));
  
 */
	
/**********************************************************************/
	
	private Integer categoryid;	
	private String	categoryname;	
	private String	categorydesc;

/**********************************************************************/	
		
	@Id
	@Column(name = "categoryid", unique = true, nullable = false)
	public Integer getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	@Column(name = "categoryname", unique = false, nullable = false)
	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	@Column(name = "categorydesc", unique = false, nullable = true)
	public String getCategorydesc() {
		return categorydesc;
	}

	public void setCategorydesc(String categorydesc) {
		this.categorydesc = categorydesc;
	}

/**********************************************************************/	
	
}

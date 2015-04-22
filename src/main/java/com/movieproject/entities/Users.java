package com.movieproject.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users {

/**********************************************************************************************/
	
/*	
			CREATE TABLE users(
					
			userid				INT(10),
			name				VARCHAR(100)			
			mobile_number		VARCHAR(14)			NOT NULL,
			buildingno			VARCHAR(10)			NOT NULL,
			street				VARCHAR(100)		NOT NULL,
			apartmentno			VARCHAR(20)			NOT NULL,
			city				VARCHAR(20)			NOT NULL,
			zipcode				VARCHAR(6)			NOT NULL,
			state				VARCHAR(20)			NOT NULL,
			country				VARCHAR(20)			NOT NULL,

			PRIMARY KEY (userid));
*/
	
/**********************************************************************************************/
	@Id
	@Column(name = "userid", unique = true, nullable = false)	
	private Integer userid;	
	
	@Column(name = "name", unique = false, nullable = false )
	private String name;
	
	@Column(name = "email", unique = true, nullable = false)	
	private String email;	
	
	@Column(name = "mobile_number", unique = false, nullable = true)
	private String mobile_number;
	
	@Column(name = "buildingno", unique = false, nullable = true)
	private String buildingno;
	
	@Column(name = "street",unique = false, nullable = true)
	private String street;
	
	@Column(name = "apartmentno",unique = false, nullable = true)
	private String apartmentno;
	
	@Column(name = "address",unique = false, nullable = true)
	private String address;
	
	@Column(name = "city",unique = false, nullable = true)
	private String city;
	
	@Column(name = "zipcode",unique = false, nullable = true)
	private String zipcode;
	
	@Column(name = "state",unique = false, nullable = true)
	private String state;
	
	@Column(name = "country",unique = false, nullable = true)
	private String country;

	/**********************************************************************************************/

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	public String getFirstandlastname() {
		return name;
	}

	public void setFirstandlastname(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getBuildingno() {
		return buildingno;
	}

	public void setBuildingno(String buildingno) {
		this.buildingno = buildingno;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getApartmentno() {
		return apartmentno;
	}

	public void setApartmentno(String apartmentno) {
		this.apartmentno = apartmentno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

/**********************************************************************************************/	
	
	
}

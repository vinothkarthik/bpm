package com.vin.bpm.usertask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {


	@Id
	@GeneratedValue
	Long userId;
	
	String name;

	String dept;

	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String name,String dept) {
		this.name=name;this.dept=dept;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}

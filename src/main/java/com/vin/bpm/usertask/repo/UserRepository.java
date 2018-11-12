package com.vin.bpm.usertask.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vin.bpm.usertask.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByName(String name); 
}

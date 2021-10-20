package com.hplus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hplus.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	@Query("select u from User u where u.username= :name")
    public User searchByName(@Param("name") String username);
}

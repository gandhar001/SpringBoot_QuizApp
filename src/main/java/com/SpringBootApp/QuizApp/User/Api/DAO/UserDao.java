package com.SpringBootApp.QuizApp.User.Api.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SpringBootApp.QuizApp.User.Api.Entities.UserEntity;


@Repository
public interface UserDao extends CrudRepository<UserEntity,Long> {
	
	  UserEntity findByUsername(String username);


}

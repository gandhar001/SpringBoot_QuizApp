package com.SpringBootApp.QuizApp.QuizModule.User.Api.Services;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.SpringBootApp.QuizApp.QuizModule.User.Api.DTO.RequestDTO.JWTRequest;
import com.SpringBootApp.QuizApp.QuizModule.User.Api.DTO.RequestDTO.UserDTO;

interface JWTUserDetailsService extends UserDetailsService  {

	
   public Map<String,Object> registerUser(UserDTO user);
   public Map<String,Object> authenticateUser(JWTRequest authUser) throws Exception;
}

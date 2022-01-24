package com.SpringBootApp.QuizApp.User.Api.Services.Definitions;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.SpringBootApp.QuizApp.User.Api.DTO.RequestDTO.JWTRequest;
import com.SpringBootApp.QuizApp.User.Api.DTO.RequestDTO.UserDTO;


public interface JWTUserDetailsService extends UserDetailsService  {

	
   public Map<String,Object> registerUser(UserDTO user)throws Exception;
   public Map<String,Object> authenticateUser(JWTRequest authUser) throws Exception;
 
}

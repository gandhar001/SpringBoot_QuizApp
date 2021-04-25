package com.SpringBootApp.QuizApp.QuizModule.User.Api.Controllers;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.SpringBootApp.QuizApp.QuizModule.User.Api.DTO.RequestDTO.JWTRequest;
import com.SpringBootApp.QuizApp.QuizModule.User.Api.DTO.RequestDTO.UserDTO;
import com.SpringBootApp.QuizApp.QuizModule.User.Api.DTO.ResponseDTO.UserResDTO;
import com.SpringBootApp.QuizApp.QuizModule.User.Api.Services.JWTUserDetailsServiceImpl;

@RestController
@RequestMapping(value = ("${api.path}/user"))
public class UserController {

	@Autowired
	private JWTUserDetailsServiceImpl userService;

	@PostMapping(value = "/register")
	public ResponseEntity<UserResDTO> registerUser(@RequestBody UserDTO userDTO) throws Exception {

		UserResDTO apiResponse = null;

		Map<String, Object> registerUserMap = userService.registerUser(userDTO);

		if (registerUserMap.get("status")=="failed")
		{
			apiResponse = new UserResDTO(registerUserMap, "failed", HttpStatus.BAD_REQUEST, LocalDateTime.now());
		}

		else if (registerUserMap.get("status") == "success")
			apiResponse = new UserResDTO(registerUserMap, "success", HttpStatus.CREATED, LocalDateTime.now());

		return new ResponseEntity<UserResDTO>(apiResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<UserResDTO> authenticateUser(@RequestBody JWTRequest authUser) throws Exception {

		UserResDTO apiResponse = null;

		Map<String, Object> authUserMap = userService.authenticateUser(authUser);
	
		if (authUserMap.get("status")=="failed")
		{
		
			apiResponse = new UserResDTO(authUserMap, "Failed", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
		}

		else if (authUserMap.get("status") == "success")
		{
			apiResponse = new UserResDTO(authUserMap, "success", HttpStatus.OK, LocalDateTime.now());
		}
		return new ResponseEntity<UserResDTO>(apiResponse, HttpStatus.OK);

	}

}

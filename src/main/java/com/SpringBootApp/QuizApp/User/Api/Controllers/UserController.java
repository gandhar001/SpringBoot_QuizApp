package com.SpringBootApp.QuizApp.User.Api.Controllers;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.SpringBootApp.QuizApp.User.Api.DTO.RequestDTO.JWTRequest;
import com.SpringBootApp.QuizApp.User.Api.DTO.RequestDTO.UserDTO;

import com.SpringBootApp.QuizApp.User.Api.Services.Implementation.JWTUserDetailsServiceImpl;
import com.SpringBootApp.QuizApp.Utils.ResponseDTO.ResDTO;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = ("${api.path}/user"))
public class UserController {

	@Autowired
	private JWTUserDetailsServiceImpl userService;

	@PostMapping(value = "/register")
	public ResponseEntity<ResDTO> registerUser(@RequestBody UserDTO userDTO) throws Exception {

		ResDTO apiResponse = null;

		Map<String, Object> registerUserMap = userService.registerUser(userDTO);

		if (registerUserMap.get("status") == "failed")

			apiResponse = new ResDTO(registerUserMap, "failed", HttpStatus.BAD_REQUEST, LocalDateTime.now());

		else if (registerUserMap.get("status") == "success")
			apiResponse = new ResDTO(registerUserMap, "success", HttpStatus.CREATED, LocalDateTime.now());

		return new ResponseEntity<ResDTO>(apiResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<ResDTO> authenticateUser(@RequestBody JWTRequest authUser) throws Exception {

		ResDTO apiResponse = null;

		Map<String, Object> authUserMap = userService.authenticateUser(authUser);

		if (authUserMap.get("status") == "failed")

			apiResponse = new ResDTO(authUserMap, "failed", HttpStatus.UNAUTHORIZED, LocalDateTime.now());

		else if (authUserMap.get("status") == "success")
			apiResponse = new ResDTO(authUserMap, "success", HttpStatus.OK, LocalDateTime.now());

		return new ResponseEntity<ResDTO>(apiResponse, HttpStatus.OK);

	}

}

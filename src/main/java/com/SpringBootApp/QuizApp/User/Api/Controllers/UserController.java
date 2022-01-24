package com.SpringBootApp.QuizApp.User.Api.Controllers;




import javax.validation.Valid;

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
	public ResponseEntity<ResDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws Exception {
		return new ResponseEntity<ResDTO>(new ResDTO(userService.registerUser(userDTO)), HttpStatus.OK);
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<ResDTO> authenticateUser(@RequestBody @Valid JWTRequest authUser) throws Exception {		
		return new ResponseEntity<ResDTO>(new ResDTO(userService.authenticateUser(authUser)), HttpStatus.OK);

	}

}

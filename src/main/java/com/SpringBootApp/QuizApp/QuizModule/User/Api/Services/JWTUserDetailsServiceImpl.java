package com.SpringBootApp.QuizApp.QuizModule.User.Api.Services;

import org.hibernate.exception.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SpringBootApp.QuizApp.QuizModule.User.Api.DAO.UserDao;
import com.SpringBootApp.QuizApp.QuizModule.User.Api.DTO.RequestDTO.JWTRequest;
import com.SpringBootApp.QuizApp.QuizModule.User.Api.DTO.RequestDTO.UserDTO;

import com.SpringBootApp.QuizApp.QuizModule.User.Api.Entities.UserEntity;
import com.SpringBootApp.QuizApp.QuizModule.User.Api.Utils.JwtToken;

@Service
public class JWTUserDetailsServiceImpl implements JWTUserDetailsService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(JWTUserDetailsServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtToken jwtToken;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		UserEntity user = null;

		try {
			user = userDao.findByUsername(userName);

		} catch (UsernameNotFoundException ex) {
			throw new UsernameNotFoundException("User not found with username: " + userName);

		}
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

	@Override
	public Map<String, Object> registerUser(UserDTO user) {

		String userId = null;
		Map<String, Object> registerUserRes = new HashMap<>();
		UserEntity newUser = null;
		try {

			newUser = new UserEntity(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(),
					bCryptPasswordEncoder.encode(user.getPassword()));

			userId = (userDao.save(newUser).getUserId()).toString();

			if (userId != null) {
				registerUserRes.put("userId", userId);
				registerUserRes.put("status", "success");
				logger.info("user with user id " + userId + "created successfully");

			}

		} catch (ConstraintViolationException ex) {

			registerUserRes.put("status", "failed");
			registerUserRes.put("exception",ex.getClass().getSimpleName());			
			logger.error(ex.toString());
		} catch (Exception ex) {
			registerUserRes.put("status", "failed");
			registerUserRes.put("exception", ex.getClass().getSimpleName());
						
			logger.error(ex.toString());
		}

		return registerUserRes;

	}

	public Map<String, Object> authenticateUser(JWTRequest authUser) throws Exception {
		System.out.println("token generated123");
		Map<String, Object> resMap = new HashMap<>();
		UserDetails userDetails = null;
		String authToken = null;

		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword()));

			userDetails = loadUserByUsername(authUser.getUsername());

			authToken = "Bearer " + jwtToken.generateToken(userDetails);
			if (authToken != null) {
				resMap.put("status", "success");
				resMap.put("authToken", authToken);
				resMap.put("userName", authUser.getUsername());

				System.out.println(authToken + "token generated");
			}

		} catch (DisabledException e) {

			resMap.put("status","failed");
			resMap.put("exception",e.getClass().getSimpleName());
			logger.error(e.getLocalizedMessage());
			

		} catch (BadCredentialsException ex) {

			resMap.put("status","failed");
			resMap.put("exception", ex.getClass().getSimpleName());
			logger.error( ex.getLocalizedMessage());
			

		} catch (Exception ex) {
			resMap.put("status","failed");
			resMap.put("exception", ex.getClass().getSimpleName());
			logger.error( ex.getLocalizedMessage());

		}
		return resMap;
	}

}

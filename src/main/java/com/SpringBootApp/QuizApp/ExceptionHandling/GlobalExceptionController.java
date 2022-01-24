package com.SpringBootApp.QuizApp.ExceptionHandling;

import java.util.HashMap;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses.ApiErrorVO;
import com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses.DatabaseException;
import com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses.GenericServiceException;
import com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses.ResourceNotFoundException;

import com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses.UnableToSaveException;
import com.SpringBootApp.QuizApp.ExceptionHandling.ResponseDTO.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleNotFoundException(ResourceNotFoundException ex,
			HttpServletRequest request) {
		logger.error("ResourceNotFound Exception occured while making request", ex, request.getRequestURI());

		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(ex.getRequestedResource(), "RESOURCE_NOT_FOUND",
				ex.getMessage(), request.getRequestURI()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = AuthenticationException.class)
	public ResponseEntity<ExceptionResponse> handleAuthenticationFailureException(AuthenticationException ex,
			HttpServletRequest request) {
		
		logger.error("AuthenticationException Exception occured while making request", ex, request.getRequestURI());

		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("", "USER_AUTHENTICATION_FAILED",
				ex.getMessage(), request.getRequestURI()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)

	public ResponseEntity<ExceptionResponse> handleEntityValidationException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		logger.error("MethodArgumentNotValidException Exception occured while making request", ex,
				request.getRequestURI());

		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		List<ApiErrorVO> errorFields = fieldErrors.stream().map(f-> new ApiErrorVO(f.getDefaultMessage(),f.getField())).collect(Collectors.toList());
		

		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse(new HashMap<String, Object>() {
					private static final long serialVersionUID = 1L;
					{
						put("errorFields", errorFields);

					}
				}, "VALIDATION_FAILED", ex.getMessage(), request.getRequestURI()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnableToSaveException.class)
	public ResponseEntity<ExceptionResponse> handleUnableToSaveException(UnableToSaveException ex,
			HttpServletRequest request) {
		logger.error("UnableToSaveException Exception occured while making request", ex, request.getRequestURI());

		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse(null, "ENTITY_UNABLE_TO_SAVE", ex.getMessage(), request.getRequestURI(),"errorOccured"),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex,
			HttpServletRequest request) {
		logger.error("ConstraintViolationException Exception occured while making request", ex);
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse("", "SQL_CONSTRAINT_VIOLATED", ex.getMessage(), request.getRequestURI()),
				HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(JDBCConnectionException.class)
	public ResponseEntity<ExceptionResponse> handleJDBCConnectionException(JDBCConnectionException ex,
			HttpServletRequest request) {
		logger.error("JDBCConnectionException Exception occured while making request", ex);
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse(null, "DATABASE_CONNECTION_ERROR", ex.getMessage(), request.getRequestURI(),"errorOccured"),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex,
			HttpServletRequest request) {
		logger.error("IllegalArgumentException Exception occured while making request", ex, request.getRequestURI());
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse("", "ILLEGAL_REQUEST_PARAMETERS", ex.getMessage(), request.getRequestURI()),
				HttpStatus.BAD_REQUEST);

	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleGenericServiceException(Exception ex,
			HttpServletRequest request) {
		logger.error("GenericServiceException Exception occured while making request", ex, request.getRequestURI());
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse( null,"SERVICE_EXECUTION_FAILURE", ex.getMessage(), request.getRequestURI(),"errorOccured"),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<ExceptionResponse> handleDatabaseException(DatabaseException ex,
			HttpServletRequest request) {
		logger.error("DatabaseException Exception occured while making request", ex, request.getRequestURI());
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse(ex.getRequestedResource(), "DATABASE_EXCEPTION", ex.getMessage(), request.getRequestURI(),"errorOccured"),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}
}



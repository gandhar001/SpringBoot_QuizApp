package com.SpringBootApp.QuizApp.Quiz.Api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.CreateQuizDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuizSubmissionDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.Services.Definitions.QuizService;


import com.SpringBootApp.QuizApp.Utils.ResponseDTO.ResDTO;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = ("${api.path}/quiz"))
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping(value = "/create-quiz")
	public ResponseEntity<ResDTO> CreateQuiz(@RequestBody CreateQuizDTO createQuizDTO) throws Exception {

		return new ResponseEntity<ResDTO>(new ResDTO(quizService.createQuiz(createQuizDTO)), HttpStatus.OK);
	}

	@GetMapping(value = "/search-category")
	public ResponseEntity<ResDTO> searchCategory(@RequestParam("categoryKey") String categoryKey,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "4") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) throws Exception {

		return new ResponseEntity<ResDTO>(new ResDTO(quizService.searchCategory(categoryKey, pageNo, pageSize, sortBy)),
				HttpStatus.OK);
	}

	@GetMapping(value = "{categoryId}/fetch-quizes")
	public ResponseEntity<ResDTO> fetchQuizesWithCategory(@PathVariable("categoryId") String categoryId,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "1") Integer pageSize,
			@RequestParam(defaultValue = "category") String sortBy) throws Exception {

		return new ResponseEntity<ResDTO>(
				new ResDTO(quizService.fetchQuizesWithCategory(categoryId, pageNo, pageSize, sortBy)), HttpStatus.OK);
	}

	@GetMapping(value = "/search-quiz")
	public ResponseEntity<ResDTO> searchQuiz(@RequestParam("quizKey") String quizKey,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "1") Integer pageSize,
			@RequestParam(defaultValue = "category") String sortBy) throws Exception {

		return new ResponseEntity<ResDTO>(new ResDTO(quizService.searchQuiz(quizKey, pageNo, pageSize, sortBy)),
				HttpStatus.OK);
	}

	@GetMapping(value = "{quizId}/fetch-questions")
	public ResponseEntity<ResDTO> fetchQuizQuestions(@PathVariable("quizId") String quizId) throws Exception {

		return new ResponseEntity<ResDTO>(new ResDTO(quizService.fetchQuizQuestions(quizId)), HttpStatus.OK);
	}

	@PostMapping(value = "{quizId}/generate-result")
	public ResponseEntity<ResDTO> generateQuizResult(@PathVariable("quizId") String quizId,
			@RequestBody QuizSubmissionDTO quizSubmissionDTO) throws Exception {

		return new ResponseEntity<ResDTO>(new ResDTO(quizService.generateQuizResult(quizSubmissionDTO, quizId)),
				HttpStatus.OK);
	}

}

package com.SpringBootApp.QuizApp.Quiz.Api.Controllers;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.CreateQuizDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuizSubmissionDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.Services.Implementation.QuizServiceImpl;

import com.SpringBootApp.QuizApp.Utils.ResponseDTO.ResDTO;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = ("${api.path}/quiz"))
public class QuizController {

	@Autowired
	private QuizServiceImpl quizService;
 
	@PostMapping(value = "/create-quiz")
	public ResponseEntity<ResDTO> CreateQuiz(@RequestBody CreateQuizDTO createQuizDTO) throws Exception {

		ResDTO apiResponse = null;

		Map<String, Object> createQuizMap = quizService.createQuiz(createQuizDTO);

		if (createQuizMap.get("status") == "failed") {
			apiResponse = new ResDTO(createQuizMap, "failed", HttpStatus.BAD_REQUEST, LocalDateTime.now());
		}

		else if (createQuizMap.get("status") == "success")
			apiResponse = new ResDTO(createQuizMap, "success", HttpStatus.CREATED, LocalDateTime.now());

		return new ResponseEntity<ResDTO>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(value = "/fetch-categories")
	public ResponseEntity<ResDTO> fetchCategories() throws Exception {

		ResDTO apiResponse = null;

		Map<String, Object> fetchCategoriesMap = quizService.fetchCategories();

		if (fetchCategoriesMap.get("status") == "failed") {
			apiResponse = new ResDTO(fetchCategoriesMap, "failed", HttpStatus.BAD_REQUEST, LocalDateTime.now());
		}

		else if (fetchCategoriesMap.get("status") == "success")
			apiResponse = new ResDTO(fetchCategoriesMap, "success", HttpStatus.OK, LocalDateTime.now());

		return new ResponseEntity<ResDTO>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(value = "{categoryId}/fetch-quizes")
	public ResponseEntity<ResDTO> fetchQuizes(@PathVariable("categoryId") String categoryId) throws Exception {

		ResDTO apiResponse = null;

		Map<String, Object> fetchQuizesMap = quizService.fetchQuizes(Long.valueOf(categoryId));

		if (fetchQuizesMap.get("status") == "failed") {
			apiResponse = new ResDTO(fetchQuizesMap, "failed", HttpStatus.BAD_REQUEST, LocalDateTime.now());
		}

		else if (fetchQuizesMap.get("status") == "success")
			apiResponse = new ResDTO(fetchQuizesMap, "success", HttpStatus.OK, LocalDateTime.now());

		return new ResponseEntity<ResDTO>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(value = "{quizId}/fetch-questions")
	public ResponseEntity<ResDTO> fetchQuizQuestions(@PathVariable("quizId") String quizId) throws Exception {

		ResDTO apiResponse = null;

		Map<String, Object> fetchQuestionsMap = quizService.fetchQuizQuestions(Long.valueOf(quizId));

		if (fetchQuestionsMap.get("status") == "failed") {
			apiResponse = new ResDTO(fetchQuestionsMap, "failed", HttpStatus.BAD_REQUEST, LocalDateTime.now());
		}

		else if (fetchQuestionsMap.get("status") == "success")
			apiResponse = new ResDTO(fetchQuestionsMap, "success", HttpStatus.OK, LocalDateTime.now());

		return new ResponseEntity<ResDTO>(apiResponse, HttpStatus.OK);
	}

	@PostMapping(value = "{quizId}/generate-result")
	public ResponseEntity<ResDTO> generateQuizResult(@PathVariable("quizId") String quizId, @RequestBody QuizSubmissionDTO quizSubmissionDTO)
			throws Exception {

		ResDTO apiResponse = null;

		Map<String, Object> fetchQuizResultMap = quizService.generateQuizResult(quizSubmissionDTO,
				Long.valueOf(quizId));

		if (fetchQuizResultMap.get("status") == "failed") {
			apiResponse = new ResDTO(fetchQuizResultMap, "failed", HttpStatus.BAD_REQUEST, LocalDateTime.now());
		}

		else if (fetchQuizResultMap.get("status") == "success")
			apiResponse = new ResDTO(fetchQuizResultMap, "success", HttpStatus.OK, LocalDateTime.now());

		return new ResponseEntity<ResDTO>(apiResponse, HttpStatus.OK);
	}

}

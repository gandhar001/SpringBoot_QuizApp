package com.SpringBootApp.QuizApp.Quiz.Api.Controllers;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.CreateQuizDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.Services.Implementation.QuizServiceImpl;

import com.SpringBootApp.QuizApp.Utils.ResponseDTO.ResDTO;

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

}


package com.SpringBootApp.QuizApp.Quiz.Api.Services.Implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBootApp.QuizApp.Quiz.Api.DAO.QuestionDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DAO.QuizCategoryDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DAO.QuizDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.CreateQuizDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuestionDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuizDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizResDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizCategoriesDTO;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizQuestionsDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.CategoryDTO;

import com.SpringBootApp.QuizApp.Quiz.Api.Entities.Quiz;
import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizAnswers;
import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizCategory;
import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizOptions;
import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizQuestion;
import com.SpringBootApp.QuizApp.Quiz.Api.Services.Definitions.QuizService;

import com.SpringBootApp.QuizApp.User.Api.Services.Implementation.JWTUserDetailsServiceImpl;

@Service
public class QuizServiceImpl implements QuizService {

	private static final Logger logger = LoggerFactory.getLogger(JWTUserDetailsServiceImpl.class);

	@Autowired
	private QuizCategoryDAO categoryDAO;

	@Autowired
	private QuizDAO quizDAO;

	@Autowired
	private QuestionDAO questionDAO;

	@Override
	public Map<String, Object> createQuiz(CreateQuizDTO createQuizDTO) throws Exception {

		Map<String, Object> createQuizRes = new HashMap<>();

		List<QuizQuestion> quizQuestions = null;
		List<Quiz> quizes = null;
		List<String> quizCategories = null;

		List<QuizAnswers> quizAnswers = null;
		List<QuizOptions> quizOptions = null;

		try {

			quizCategories = new ArrayList<>();
			for (CategoryDTO category : createQuizDTO.getQuizCategories())

			{
				quizes = new ArrayList<>();
				for (QuizDTO quiz : category.getQuizes()) {

					quizQuestions = new ArrayList<>();
					for (QuestionDTO question : quiz.getQuestions()) {

						if (question.getQuizAnswers().size() != 0) {

							quizAnswers = new ArrayList<>();
							for (String quizAnswer : question.getQuizAnswers()) {
								quizAnswers.add(new QuizAnswers(quizAnswer));
							}
						}

						if (question.getQuizOptions().size() != 0) {
							quizOptions = new ArrayList<>();
							for (String quizOption : question.getQuizOptions()) {
								quizOptions.add(new QuizOptions(quizOption));
							}
						}
						if (question != null && quizAnswers.size() != 0 && quizOptions.size() != 0) {

							quizQuestions.add(new QuizQuestion(question.getQuestion(), question.getDescription(),
									question.getQuestionType(), String.valueOf(quizOptions.size()),
									String.valueOf(quizAnswers.size()), question.getQuestionScore(), quizAnswers,
									quizOptions));
						}

					}

					if (quizQuestions.size() != 0 && quiz != null) {

						quizes.add(new Quiz(quiz.getQuizName(), quiz.getDescription(), quiz.getAllocatedPoints(),
								quiz.getAllocatedTime(), String.valueOf(quizQuestions.size()), quiz.getMaxScore(),
								quizQuestions));
					}

				}

				if (quizes.size() != 0 && category != null) {

					quizCategories.add(String.valueOf(categoryDAO.save(new QuizCategory(category.getCategory(),
							category.getDescription(), String.valueOf(quizes.size()), quizes)).getCategoryId()));
				}

			}

			if (createQuizDTO.getQuizCategories().size() == quizCategories.size()) {

				createQuizRes.put("createdQuizes", quizCategories);
				createQuizRes.put("status", "success");
				logger.info("quizes created successfully!!");
			}

		} catch (EntityNotFoundException ex) {
			createQuizRes.put("status", "failed");
			createQuizRes.put("exception", ex.getClass().getSimpleName());
			logger.error(ex.getLocalizedMessage());
		} catch (Exception ex) {

			createQuizRes.put("status", "failed");
			createQuizRes.put("exception", ex.getClass().getSimpleName());
			logger.error(ex.getLocalizedMessage());
		}
		return createQuizRes;
	}

	@Override
	public Map<String, Object> fetchCategories() throws Exception {
		Map<String, Object> fetchCategoriesRes = new HashMap<>();

		List<QuizCategoriesDTO> quizCategories = null;
		try {
			quizCategories = categoryDAO.fetchCategories();

			if (quizCategories.size() != 0) {
				fetchCategoriesRes.put("fetchedCategories", quizCategories);
				fetchCategoriesRes.put("status", "success");
				logger.info("categories fetched successfully!!");
			}

		} catch (EntityNotFoundException ex) {
			fetchCategoriesRes.put("status", "failed");
			fetchCategoriesRes.put("exception", ex.getClass().getSimpleName());
			logger.error(ex.getLocalizedMessage());
		} catch (Exception ex) {

			fetchCategoriesRes.put("status", "failed");
			fetchCategoriesRes.put("exception", ex.getClass().getSimpleName());
			logger.error(ex.getLocalizedMessage());
		}

		return fetchCategoriesRes;
	}

	@Override
	public Map<String, Object> fetchQuizes(Long categoryId) throws Exception {

		Map<String, Object> fetchQuizesRes = new HashMap<>();
		List<QuizResDTO> fetchedQuizes = null;
		List<Quiz> quizes = null;
		try {
			quizes = quizDAO.fetchQuiz(categoryId);

			if (quizes.size() != 0) {
				fetchedQuizes = new ArrayList<>();

				for (Quiz quiz : quizes) {
					if (quiz != null) {
						fetchedQuizes.add(new QuizResDTO(quiz.getQuizName(), quiz.getDescription(),
								quiz.getAllocatedPoints(), quiz.getAllocatedTime(), quiz.getTotalQuestions(),
								quiz.getMaxScore(), quiz.getCreatedAt(), quiz.getUpdatedAt()));
					}
				}
				fetchQuizesRes.put("fetchedQuizes", quizes);
				fetchQuizesRes.put("status", "success");
				logger.info("quizes fetched successfully!!");
			}

		} catch (EntityNotFoundException ex) {
			fetchQuizesRes.put("status", "failed");
			fetchQuizesRes.put("exception", ex.getClass().getSimpleName());
			logger.error(ex.getLocalizedMessage());
		} catch (Exception ex) {

			fetchQuizesRes.put("status", "failed");
			fetchQuizesRes.put("exception", ex.getClass().getSimpleName());
			logger.error(ex.getLocalizedMessage());
		}

		return fetchQuizesRes;
	}

	@Override
	public Map<String, Object> fetchQuizQuestions(Long quizId) throws Exception {

		Map<String, Object> fetchQuestionsRes = new HashMap<>();
		List<QuizQuestionsDTO> fetchedQuestions = null;

		List<QuizQuestion> quizQuestions = null;
		try {
			quizQuestions = questionDAO.fetchQuizQuestions(quizId);

			if (quizQuestions.size() != 0) {
				fetchedQuestions = new ArrayList<>();
				for (QuizQuestion quizQuestion : quizQuestions) {
					if (quizQuestion != null) {
						fetchedQuestions.add(new QuizQuestionsDTO(quizQuestion.getQuestionId(),
								quizQuestion.getQuestion(), quizQuestion.getDescription(),
								quizQuestion.getQuestionType(), quizQuestion.getTotalOptions(),
								quizQuestion.getQuestionScore(), quizQuestion.getOptions(), quizQuestion.getCreatedAt(),
								quizQuestion.getUpdatedAt()));
					}
				}

				fetchQuestionsRes.put("fetchedQuestions", fetchedQuestions);
				fetchQuestionsRes.put("status", "success");
				logger.info("Questions fetched successfully!!");
			}

		} catch (EntityNotFoundException ex) {
			fetchQuestionsRes.put("status", "failed");
			fetchQuestionsRes.put("exception", ex.getClass().getSimpleName());
			logger.error(ex.getLocalizedMessage());
		} catch (Exception ex) {

			fetchQuestionsRes.put("status", "failed");
			fetchQuestionsRes.put("exception", ex.getClass().getSimpleName());
			logger.error(ex.getLocalizedMessage());
		}

		return fetchQuestionsRes;
	}

}

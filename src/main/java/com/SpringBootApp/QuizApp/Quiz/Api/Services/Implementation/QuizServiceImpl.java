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

import com.SpringBootApp.QuizApp.Quiz.Api.DAO.AnswerDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DAO.OptionDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DAO.QuestionDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DAO.QuizCategoryDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DAO.QuizDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.CreateQuizDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuestionDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuizDTO;
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

	@Override
	public Map<String, Object> createQuiz(CreateQuizDTO createQuizDTO) throws Exception {

		// TODO Auto-generated method stub
		Map<String, Object> createQuizRes = new HashMap<>();

		List<QuizQuestion> createdQuestions = new ArrayList<>();
		List<Quiz> createdQuizes = new ArrayList<>();
		List<String> createdCategories = new ArrayList<>();

		List<QuizAnswers> quizAnswers = new ArrayList<>();
		List<QuizOptions> options = new ArrayList<>();

		int totalOptions = 0;

		int totalAnswers = 0;
		int totalQuestions = 0;
		int totalQuizes = 0;

		try {

			for (CategoryDTO category : createQuizDTO.getQuizCategories())

			{

				for (QuizDTO quiz : category.getQuizes()) {

					for (QuestionDTO question : quiz.getQuestions()) {

						totalOptions = question.getQuizOptions().size();
						totalAnswers = question.getQuizAnswers().size();
						if (totalAnswers != 0) {

							for (String quizAnswer : question.getQuizAnswers()) {
								quizAnswers.add(new QuizAnswers(quizAnswer));
							}
						}

						if (totalOptions != 0) {
							for (String quizOption : question.getQuizOptions()) {
								options.add(new QuizOptions(quizOption));
							}
						}
						if (quizAnswers != null && options != null) {

							createdQuestions.add(new QuizQuestion(question.getQuestion(), question.getDescription(),
									question.getQuestionType(), String.valueOf(totalOptions),
									String.valueOf(totalAnswers), question.getQuestionScore(), quizAnswers, options));
						}

					}

					totalQuestions = quiz.getQuestions().size();
					if (createdQuestions != null && totalQuestions != 0) {
						createdQuizes.add(new Quiz(quiz.getQuizName(), quiz.getDescription(), quiz.getAllocatedPoints(),
								quiz.getAllocatedTime(), String.valueOf(totalQuestions), quiz.getMaxScore(),
								createdQuestions));
					}

				}
				totalQuizes = category.getQuizes().size();

				if (createdQuizes != null && totalQuizes != 0) {
					createdCategories.add(String.valueOf(categoryDAO.save(new QuizCategory(category.getCategory(),
							category.getDescription(), String.valueOf(totalQuizes), createdQuizes)).getCategoryId()));
				}

			}

			if (createQuizDTO.getQuizCategories().size() == createdCategories.size()) {
				createQuizRes.put("createdQuizes", createdCategories);
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

}

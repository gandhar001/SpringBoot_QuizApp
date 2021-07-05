
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

import com.SpringBootApp.QuizApp.Quiz.Api.DAO.AttemptedQuizesDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DAO.QuestionDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DAO.QuizCategoryDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DAO.QuizDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.CreateQuizDTO;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuestionDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuizDTO;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuizOptionsDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuizSubmissionDTO;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.SubmittedQuizDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizResDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.GeneratedQuizResultDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizCategoriesDTO;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizQuestionsDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.CategoryDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.Entities.AttemptedQuizes;
import com.SpringBootApp.QuizApp.Quiz.Api.Entities.Quiz;

import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizCategory;
import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizOptions;
import com.SpringBootApp.QuizApp.Quiz.Api.Entities.QuizQuestion;
import com.SpringBootApp.QuizApp.Quiz.Api.Services.Definitions.QuizService;
import com.SpringBootApp.QuizApp.User.Api.DAO.UserDao;
import com.SpringBootApp.QuizApp.User.Api.Entities.UserEntity;
import com.SpringBootApp.QuizApp.User.Api.Services.Implementation.JWTUserDetailsServiceImpl;

@Service
public class QuizServiceImpl implements QuizService {

	private static final Logger logger = LoggerFactory.getLogger(JWTUserDetailsServiceImpl.class);

	@Autowired
	private QuizCategoryDAO categoryDAO;

	@Autowired
	private QuizDAO quizDAO;

	@Autowired
	private UserDao userDAO;

	@Autowired
	private AttemptedQuizesDAO attemptedQuizesDAO;

	@Autowired
	private QuestionDAO questionDAO;

	@Override
	public Map<String, Object> createQuiz(CreateQuizDTO createQuizDTO) throws Exception {

		Map<String, Object> createQuizRes = new HashMap<>();

		List<QuizQuestion> quizQuestions = null;
		List<Quiz> quizes = null;
		List<String> quizCategories = null;
		Double totalAnswers = 0.0;
		List<QuizOptions> quizOptions = null;

		try {

			quizCategories = new ArrayList<>();
			for (CategoryDTO category : createQuizDTO.getQuizCategories())

			{
				quizes = new ArrayList<>();
				for (QuizDTO quiz : category.getQuizes()) {

					quizQuestions = new ArrayList<>();
					for (QuestionDTO question : quiz.getQuestions()) {

						if (question.getQuizOptions().size() > 0) {
							quizOptions = new ArrayList<>();
							totalAnswers = 0.0;
							for (QuizOptionsDTO quizOption : question.getQuizOptions()) {

								if (quizOption.getIsAnswer()) {
									totalAnswers++;
								}
								quizOptions.add(new QuizOptions(quizOption.getOption(), quizOption.getIsAnswer()));
							}
						}

						if (question != null && totalAnswers > 0 && quizOptions.size() > 0) {

							quizQuestions.add(new QuizQuestion(question.getQuestion(), question.getDescription(),
									question.getQuestionType(), Double.valueOf(quizOptions.size()),
									Double.valueOf(totalAnswers), Double.valueOf(question.getQuestionScore()),
									quizOptions));
						}

					}

					if (quizQuestions.size() > 0 && quiz != null) {

						quizes.add(new Quiz(quiz.getQuizName(), quiz.getDescription(),
								Double.valueOf(quiz.getPassingPercentage()), Double.valueOf(quiz.getAllocatedPoints()),
								Double.valueOf(quiz.getAllocatedTime()), Double.valueOf(quizQuestions.size()),
								Double.valueOf(quiz.getMaxScore()), quizQuestions));
					}

				}

				if (quizes.size() > 0 && category != null) {

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

			if (quizes.size() > 0) {
				fetchedQuizes = new ArrayList<>();

				for (Quiz quiz : quizes) {
					if (quiz != null) {
						fetchedQuizes.add(new QuizResDTO(String.valueOf(quiz.getQuizId()), quiz.getQuizName(),
								quiz.getDescription(), String.valueOf(quiz.getAllocatedPoints()),
								String.valueOf(quiz.getAllocatedTime()), String.valueOf(quiz.getTotalQuestions()),
								String.valueOf(quiz.getMaxScore()), String.valueOf(quiz.getPassingPercentage()),
								quiz.getCreatedAt(), quiz.getUpdatedAt()));
					}
				}
				fetchQuizesRes.put("fetchedQuizes", fetchedQuizes);
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

			if (quizQuestions.size() > 0) {
				fetchedQuestions = new ArrayList<>();
				for (QuizQuestion quizQuestion : quizQuestions) {
					if (quizQuestion != null) {
						fetchedQuestions.add(new QuizQuestionsDTO(String.valueOf(quizQuestion.getQuestionId()),
								quizQuestion.getQuestion(), quizQuestion.getDescription(),
								quizQuestion.getQuestionType(), String.valueOf(quizQuestion.getTotalOptions()),
								String.valueOf(quizQuestion.getQuestionScore()), quizQuestion.getOptions(),
								quizQuestion.getCreatedAt(), quizQuestion.getUpdatedAt()));
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

	@Override
	public Map<String, Object> generateQuizResult(QuizSubmissionDTO quizSubmissionDTO, Long quizId)
			throws Exception {
		Map<String, Object> generateQuizResultRes = new HashMap<>();
		Quiz quiz = null;
		UserEntity user = null;
		QuizQuestion attemptedQuestion = null;
		SubmittedQuizDTO quizQuestion = null;
		AttemptedQuizes attemptedQuiz = null;
		List<Long> attemptedQuestionIds = null;
		List<QuizQuestion> attemptedQuestions = null;
		List<QuizQuestion> correctAnswers = null;
		List<SubmittedQuizDTO> submittedQuestions = null;
		Double totAttemptedQuestions = 0.0;
		Double totAttempts = 0.0;
		Double correctOptions = 0.0;
		Double processedPercentage = 0.0;
		Double processedScore = 0.0;
		String attemptStatus = "Failed";
		Double processedPoints = 0.0;
		Boolean quizCleared = false;
		Double totCorrectAnswers = 0.0;
		Double totIncorrectAnswers = 0.0;
		Long userId = null;
		GeneratedQuizResultDTO generatedQuizResult = null;

		try {

			userId = Long.valueOf(quizSubmissionDTO.getUserId());
			user = userDAO.findById(userId).get();
			if (user == null) {

				generateQuizResultRes.put("status", "failed");
				generateQuizResultRes.put("errorMessage", "User does not exist");
				logger.info("User with user id :" + userId + " does not exist.");
				return generateQuizResultRes;

			}

			if (quizId == null) {
				generateQuizResultRes.put("status", "failed");
				generateQuizResultRes.put("errorMessage", "Quiz does not exist");
				logger.info("Quiz with quiz id :" + quizId + " does not exist.");
				return generateQuizResultRes;
			} else {
				quiz = quizDAO.findById(quizId).get();
				attemptedQuiz = attemptedQuizesDAO.fetchAttemptedQuiz(quizId);
			}
			try {
				totAttemptedQuestions = Double.valueOf(quizSubmissionDTO.getSubmittedQuiz().size());
				if (totAttemptedQuestions > 0.0) {

					attemptedQuestionIds = new ArrayList<>();
					attemptedQuestions = new ArrayList<>();
					submittedQuestions = quizSubmissionDTO.getSubmittedQuiz();

					if (submittedQuestions.size() > 0) {
						for (SubmittedQuizDTO submittedQuiz : submittedQuestions) {
							if (submittedQuiz.getQuestionId() != null) {
								attemptedQuestionIds.add(Long.valueOf(submittedQuiz.getQuestionId()));
							}
						}
					}

					attemptedQuestions = questionDAO.fetchAttemptedQuestions(attemptedQuestionIds, quizId);

					if (attemptedQuestions.size() > 0) {
						attemptedQuestions.sort((o1, o2) -> String.valueOf(o1.getQuestionId())
								.compareTo(String.valueOf(o2.getQuestionId())));
					}
					if (submittedQuestions.size() > 0) {
						submittedQuestions.sort((o1, o2) -> String.valueOf(o1.getQuestionId())
								.compareTo(String.valueOf(o2.getQuestionId())));

					}

				}

			} catch (NullPointerException ex) {
				generateQuizResultRes.put("status", "failed");
				generateQuizResultRes.put("exception", ex.getClass().getSimpleName());
				logger.error(ex.getLocalizedMessage());
			} catch (ArrayIndexOutOfBoundsException ex) {
				generateQuizResultRes.put("status", "failed");
				generateQuizResultRes.put("exception", ex.getClass().getSimpleName());
				logger.error(ex.getLocalizedMessage());
			} catch (Exception ex) {
				generateQuizResultRes.put("status", "failed");
				generateQuizResultRes.put("exception", ex.getClass().getSimpleName());
				logger.error(ex.getLocalizedMessage());
			}

			try {
				if (attemptedQuestions.size() > 0 && submittedQuestions.size() > 0) {
					correctAnswers = new ArrayList<>();

					for (int i = 0; i < attemptedQuestions.size(); i++) {

						attemptedQuestion = attemptedQuestions.get(i);
						correctOptions = 0.0;

						quizQuestion = submittedQuestions.get(i);

						if (attemptedQuestion != null && quizQuestion != null
								&& String.valueOf(attemptedQuestion.getQuestionId())
										.equalsIgnoreCase(quizQuestion.getQuestionId())) {

							for (String optionId : quizQuestion.getAttemptedOptions()) {

								if (optionId != null) {
									for (QuizOptions quizOption : attemptedQuestion.getOptions()) {
										if (quizOption != null && String.valueOf(quizOption.getOptionId())
												.equalsIgnoreCase(optionId)) {
											if (quizOption.getIsAnswer()) {

												correctOptions++;

											}

										}

									}
								}
							}
							if (correctOptions.equals(attemptedQuestion.getTotalAnswers())
									&& Double.valueOf(quizQuestion.getAttemptedOptions().size())
											.equals(attemptedQuestion.getTotalAnswers())) {
								correctAnswers.add(attemptedQuestion);
							}

						}

					}

				}
			} catch (NullPointerException ex) {
				generateQuizResultRes.put("status", "failed");
				generateQuizResultRes.put("exception", ex.getClass().getSimpleName());
				logger.error(ex.getLocalizedMessage());
			}

			catch (ArrayIndexOutOfBoundsException ex) {
				generateQuizResultRes.put("status", "failed");
				generateQuizResultRes.put("exception", ex.getClass().getSimpleName());
				logger.error(ex.getLocalizedMessage());
			} catch (Exception ex) {

				generateQuizResultRes.put("status", "failed");
				generateQuizResultRes.put("exception", ex.getClass().getSimpleName());
				logger.error(ex.getLocalizedMessage());

			}
			try {

				totCorrectAnswers = Double.valueOf(correctAnswers.size());
				totIncorrectAnswers = totAttemptedQuestions - totCorrectAnswers;

				if (totCorrectAnswers > 0) {

					for (QuizQuestion correctQuestion : correctAnswers) {
						processedScore = processedScore + Double.valueOf(correctQuestion.getQuestionScore());

					}
					if (processedScore > 0) {
						processedPercentage = processedScore * 100 / quiz.getMaxScore();
					}

					if (processedPercentage > 0 && processedPercentage >= quiz.getPassingPercentage()) {

						quizCleared = true;

						attemptStatus = "Passed";
						processedPoints = quiz.getAllocatedPoints();

						if (user != null) {
							user.setProFactor(processedPoints);
							userDAO.save(user);
						}

						if (attemptedQuiz != null) {

							totAttempts = attemptedQuiz.getTotalAttempts();
							if (totAttempts == null) {
								totAttempts = 1.0;
							} else {
								totAttempts++;
							}

							attemptedQuiz.setAttemptStatus(quizCleared);
							attemptedQuiz.setMarksScored(processedScore);
							attemptedQuiz.setPercentageScored(processedPercentage);
							attemptedQuiz.setTotalAttempts(totAttempts);

						} else {
							totAttempts++;
							attemptedQuiz = new AttemptedQuizes(user, quiz, totAttempts, processedPercentage,
									processedScore, quizCleared);

						}
						if (attemptedQuiz != null)
							attemptedQuizesDAO.save(attemptedQuiz);

					}

				}

			} catch (NullPointerException ex) {
				generateQuizResultRes.put("status", "failed");
				generateQuizResultRes.put("exception", ex.getClass().getSimpleName());
				logger.error(ex.getLocalizedMessage());
			}

			catch (Exception ex) {
				generateQuizResultRes.put("status", "failed");
				generateQuizResultRes.put("exception", ex.getClass().getSimpleName());
				logger.error(ex.getLocalizedMessage());

			}
			generatedQuizResult = new GeneratedQuizResultDTO(String.valueOf(processedPercentage),
					String.valueOf(totAttemptedQuestions), String.valueOf(totCorrectAnswers),
					String.valueOf(totIncorrectAnswers), String.valueOf(processedScore), String.valueOf(attemptStatus),
					String.valueOf(processedPoints));

			if (generatedQuizResult != null) {
				generateQuizResultRes.put("generatedQuizResult", generatedQuizResult);
				generateQuizResultRes.put("status", "success");
				logger.info("Quiz Result generated successfully ");
			}

		} catch (EntityNotFoundException ex) {
			generateQuizResultRes.put("status", "failed");
			generateQuizResultRes.put("exception", ex.getClass().getSimpleName());
			logger.error(ex.getLocalizedMessage());
		} catch (NullPointerException ex) {

			generateQuizResultRes.put("status", "failed");
			generateQuizResultRes.put("exception", ex.getClass().getSimpleName());
			logger.error(ex.getLocalizedMessage());
		} catch (Exception ex) {

			generateQuizResultRes.put("status", "failed");
			generateQuizResultRes.put("exception", ex.getClass().getSimpleName());
			logger.error(ex.getLocalizedMessage());
		}

		return generateQuizResultRes;
	}

}

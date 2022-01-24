package com.SpringBootApp.QuizApp.Quiz.Api.Services.Implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses.DatabaseException;
import com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses.ResourceNotFoundException;
import com.SpringBootApp.QuizApp.ExceptionHandling.ExceptionClasses.UnableToSaveException;
import com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories.AttemptedQuizesDAO;
import com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories.QuestionDAO;

import com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories.QuizCategoryRepository;

import com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories.QuizRepository;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.CreateQuizDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuestionDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuizDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuizOptionsDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.QuizSubmissionDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.RequestDTO.SubmittedQuizDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizResDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.GeneratedQuizResultDTO;
import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizCategoryDTO;
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
	private QuizCategoryRepository categoryDAO;

	@Autowired
	private QuizRepository quizDAO;

	@Autowired
	private UserDao userDAO;

	@Autowired
	private AttemptedQuizesDAO attemptedQuizesDAO;

	@Autowired
	private QuestionDAO questionDAO;

	@Override

	public Map<String, Object> createQuiz(CreateQuizDTO createQuizDTO) {
		Map<String, Object> createQuizRes = new HashMap<>();
		List<QuizQuestion> quizQuestions = null;
		List<Quiz> quizes = null;

		List<Quiz> updateQuizes = new ArrayList<>();
		List<QuizCategory> quizCategories = new ArrayList<>();
		Quiz newQuiz;
		long quizCount = 0;

		Double totalAnswers = 0.0;
		List<QuizOptions> quizOptions = null;
		QuizCategory quizCategory = null;
		QuizCategory newCategory = null;
		quizCategories = new ArrayList<>();
		for (CategoryDTO category : createQuizDTO.getQuizCategories())

		{
			if (category != null) {
				if (category.getQuizes().size() > 0) {
					quizCategory = categoryDAO.findByCategory(category.getCategory());

					quizes = new ArrayList<>();

					for (QuizDTO quiz : category.getQuizes()) {
						if (quiz.getQuestions().size() > 0) {
							quizQuestions = new ArrayList<>();
							for (QuestionDTO question : quiz.getQuestions()) {

								if (question.getQuizOptions().size() > 0) {
									quizOptions = new ArrayList<>();
									totalAnswers = 0.0;
									for (QuizOptionsDTO quizOption : question.getQuizOptions()) {

										if (quizOption.getIsAnswer()) {
											totalAnswers++;
										}
										quizOptions
												.add(new QuizOptions(quizOption.getOption(), quizOption.getIsAnswer()));
									}
								}

								if (question != null && totalAnswers > 0 && quizOptions.size() > 0) {

									quizQuestions.add(new QuizQuestion(question.getQuestion(),
											question.getDescription(), question.getQuestionType(),
											Double.valueOf(quizOptions.size()), Double.valueOf(totalAnswers),
											Double.valueOf(question.getQuestionScore()), quizOptions));
								}

							}
						}

						if (quizQuestions.size() > 0 && quiz != null) {

							newQuiz = new Quiz(quiz.getQuizName(), quiz.getDescription(),
									Double.valueOf(quiz.getPassingPercentage()),
									Double.valueOf(quiz.getAllocatedPoints()), Double.valueOf(quiz.getAllocatedTime()),
									Double.valueOf(quizQuestions.size()), Double.valueOf(quiz.getMaxScore()),
									quizQuestions);
							if (quizCategory != null) {
								newQuiz.setQuizCategory(quizCategory);
								updateQuizes.add(newQuiz);

							} else {
								quizes.add(newQuiz);
							}
						}

					}

				}

				if (quizCategory != null) {
					
					quizCategory.setQuizCount(
							String.valueOf(Long.valueOf(quizCategory.getQuizCount()) + updateQuizes.size()));

				} else {
					quizCategory = new QuizCategory(category.getCategory(), category.getDescription(),
							String.valueOf(quizes.size()), quizes);

				}
				quizCategories.add(quizCategory);
			}

		}
		if (updateQuizes.size() != 0) {
			quizDAO.saveAll(updateQuizes);
		}
		if (quizCategories.size() > 0) {
			categoryDAO.saveAll(quizCategories);
		}

		logger.info("quizes created successfully!!");

		return createQuizRes;

	}

	@Override
	@Transactional
	public Map<String, Object> searchCategory(String categoryKey, Integer pageNo, Integer pageSize, String sortBy)
			throws Exception {
		Map<String, Object> searchCategoryRes = new HashMap<>();

		Pageable paging;
		List<QuizCategoryDTO> pagedResult = new ArrayList<>();
		try {

			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

			pagedResult = categoryDAO.searchCategory(categoryKey, paging);

		} catch (Exception ex) {
			logger.error("error occured while fetching quizes categories with categoryKey", ex);
			throw new DatabaseException("categoryKey", categoryKey, "Error occured while fetching quizes categories");
		}
		if (pagedResult == null || pagedResult.size() == 0) {
			logger.error("Unable to fetch quizes categories");
			throw new ResourceNotFoundException("categoryKey", categoryKey,
					"Unable to fetch quizes categories with categoryKey");
		}

		searchCategoryRes.put("fetchedCategories", pagedResult);
		logger.info("categories fetched successfully!!");

		return searchCategoryRes;
	}

	@Override
	@Transactional
	public Map<String, Object> searchQuiz(String quizKey, Integer pageNo, Integer pageSize,
			String sortBy) throws Exception {
		Map<String, Object> searchQuizRes = new HashMap<>();
		Pageable paging;
		List<QuizResDTO> pagedResult = new ArrayList<>();

		try {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
			pagedResult = quizDAO.searchQuiz(quizKey, paging);

		} catch (Exception ex) {
			logger.error("error occured while fetching quizes with quizKey", ex);
			throw new DatabaseException("quizKey", quizKey, "Error occured while fetching quizes with quizKey");
		}

		if (pagedResult == null || pagedResult.size() == 0) {
			logger.error("Unable to fetch quizes with quizKey", quizKey);
			throw new ResourceNotFoundException("quizKey", quizKey, "Unable to fetch quizes with quizKey");

		}
		searchQuizRes.put("fetchedQuizes", pagedResult);

		logger.info("quizes fetched successfully!!");

		return searchQuizRes;
	}

	
	@Override
	@Transactional
	public Map<String, Object> fetchQuizesWithCategory(String categoryId, Integer pageNo, Integer pageSize, String sortBy)
			throws Exception {
		
		Map<String, Object> fetchQuizesRes = new HashMap<>();
		Pageable paging;
		List<QuizResDTO> pagedResult = new ArrayList<>();

		try {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
			pagedResult = quizDAO.fetchQuizesWithCategory( Long.valueOf(categoryId), paging);

		} catch (Exception ex) {
			logger.error("error occured while fetching quizes with categoryId", ex);
			throw new DatabaseException("categoryId", categoryId, "Error occured while fetching quizes with categry");
		}

		if (pagedResult == null || pagedResult.size() == 0) {
			logger.error("Unable to fetch quizes with categry", categoryId);
			throw new ResourceNotFoundException("categoryId", categoryId, "Unable to fetch quizes with quizKey");

		}
		fetchQuizesRes.put("fetchedQuizes", pagedResult);

		logger.info("quizes fetched successfully!!");

		return fetchQuizesRes;
	}
	
	
	@Override
	@Transactional
	public Map<String, Object> fetchQuizQuestions(String quizId) {

		Map<String, Object> fetchQuestionsRes = new HashMap<>();
		List<QuizQuestionsDTO> fetchedQuestions = new ArrayList<>();
		List<QuizQuestion> quizQuestions = new ArrayList<>();

		try {

			quizQuestions = questionDAO.fetchQuizQuestions(Long.valueOf(quizId));

		}

		catch (Exception ex) {

			logger.error("error occured while fetching quiz questions with quizId", ex);
			throw new DatabaseException("quizId", quizId, "Error occured while fetching quiz questions with quizId");

		}
		if (quizQuestions == null || quizQuestions.size() == 0) {
			throw new ResourceNotFoundException("quizId", quizId, "Unable to fetch questions with quiz");
		}
		quizQuestions.forEach((quizQuestion) -> {

			if (quizQuestion != null) {
				fetchedQuestions.add(new QuizQuestionsDTO(quizQuestion.getQuestionId(), quizQuestion.getQuestion(),
						quizQuestion.getDescription(), quizQuestion.getQuestionType(), quizQuestion.getTotalOptions(),
						quizQuestion.getQuestionScore(), quizQuestion.getQuizOptions()));
			}
		});

		fetchQuestionsRes.put("fetchedQuestions", fetchedQuestions);

		logger.info("Questions fetched successfully!!");

		return fetchQuestionsRes;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> generateQuizResult(QuizSubmissionDTO quizSubmissionDTO, String quizId) {
		Map<String, Object> generateQuizResultRes = new HashMap<>();
		Quiz quiz = null;
		UserEntity user = null;

		AttemptedQuizes attemptedQuiz = null;

		List<Long> attemptedQuestionIds = new ArrayList<>();

		// wrapper is used to modify the variables in lamba expression
		var wrapper = new Object() {
			List<QuizQuestion> attemptedQuestions = null;
			List<QuizQuestion> correctAnswers = new ArrayList<>();

		};
		List<SubmittedQuizDTO> submittedQuestions = null;
		double totAttemptedQuestions = 0.0;
		double totAttempts = 0.0;
		double processedPercentage = 0.0;
		double processedScore = 0.0;
		String attemptStatus = "Failed";
		double processedPoints = 0.0;
		Boolean quizCleared = false;
		double totCorrectAnswers = 0.0;
		double totIncorrectAnswers = 0.0;

		long userId;
		long currentQuizId;
		GeneratedQuizResultDTO generatedQuizResult = null;

		currentQuizId = Long.valueOf(quizId);
		userId = Long.valueOf(quizSubmissionDTO.getUserId());
		try {
			user = userDAO.findById(userId).get();

		} catch (Exception ex) {
			logger.error("error occured while fetching user with userId", ex, userId);
			throw new DatabaseException("userId", String.valueOf(userId),
					"Error occured while fetching user with userId");
		}
		if (user == null) {
			logger.error("Unable to fetch user with userId", userId);
			throw new ResourceNotFoundException("userId", String.valueOf(userId), "Unable to fetch user with userId");
		}
		try {

			quiz = quizDAO.findById(currentQuizId).get();

		} catch (Exception ex) {
			logger.error("error occured while fetching quiz with quizId", ex, quizId);
			throw new DatabaseException("quizId", quizId, "Error occured while fetching quiz with quizId");
		}
		if (quiz == null) {
			logger.error("Unable to fetch quiz with quizId", quizId);
			throw new ResourceNotFoundException("quizId", quizId, "Unable to fetch quiz with quizId");
		}
		submittedQuestions = quizSubmissionDTO.getSubmittedQuiz();

		totAttemptedQuestions = Double.valueOf(submittedQuestions.size());

		if (totAttemptedQuestions > 0.0) {

			attemptedQuestionIds = submittedQuestions.stream()
					.map(submittedQuestion -> Long.valueOf(submittedQuestion.getQuestionId()))
					.collect(Collectors.toList());

			try {
				wrapper.attemptedQuestions = questionDAO.fetchAttemptedQuestions(attemptedQuestionIds, currentQuizId);

			} catch (Exception ex) {
				logger.error("error occured while fetching attempted questions with quizId", ex, quizId);
				throw new DatabaseException("quizId", quizId,
						"Error occured while fetching attempted questions with quizId");
			}

			if (wrapper.attemptedQuestions == null) {
				logger.error("Unable to fetch attempted questions for quizId", quizId);
				throw new ResourceNotFoundException("quizId", quizId, "Unable to fetch attempted questions for quizId");
			}
//					if (attemptedQuestions.size() > 0) {
//						attemptedQuestions.sort((o1, o2) -> String.valueOf(o1.getQuestionId())
//								.compareTo(String.valueOf(o2.getQuestionId())));
//					}
//					if (submittedQuestions.size() > 0) {
//						submittedQuestions.sort((o1, o2) -> String.valueOf(o1.getQuestionId())
//								.compareTo(String.valueOf(o2.getQuestionId())));
//
//					}

			try {
				submittedQuestions.forEach((submittedQuestion) -> {

					if (submittedQuestion == null) {

						logger.info("Submitted question is null ");

						return;
					}

					Double correctOptions = 0.0;
					QuizQuestion attemptedQuestion = wrapper.attemptedQuestions.stream().filter(question -> String
							.valueOf(question.getQuestionId()).equalsIgnoreCase(submittedQuestion.getQuestionId()))
							.findAny().orElse(null);

					if (attemptedQuestion == null) {
						logger.info("AttemptedQuestion question is null ");
						return;
					}

					for (String optionId : submittedQuestion.getAttemptedOptions()) {

						if (optionId == null || optionId.isEmpty()) {

							logger.info("Submitted option is null or empty for question ",
									submittedQuestion.getQuestionId());
							continue;

						}

						QuizOptions quizOption = attemptedQuestion.getQuizOptions().stream()
								.filter(option -> String.valueOf(option.getOptionId()).equalsIgnoreCase(optionId))
								.findFirst().orElse(null);

						if (quizOption.getIsAnswer()) {

							correctOptions++;

						}

					}

					if (correctOptions.equals(attemptedQuestion.getTotalAnswers())
							&& Double.valueOf(submittedQuestion.getAttemptedOptions().size())
									.equals(attemptedQuestion.getTotalAnswers())) {
						wrapper.correctAnswers.add(attemptedQuestion);
					}

				});
			} catch (Exception ex) {

				logger.error("Error occured while verifying the attempted questions for attempted quiz", ex);

			}
			totCorrectAnswers = Double.valueOf(wrapper.correctAnswers.size());

			if (totCorrectAnswers > 0) {
				totIncorrectAnswers = totAttemptedQuestions - totCorrectAnswers;

				processedScore = (wrapper.correctAnswers.stream())
						.map(correctAnswer -> correctAnswer.getQuestionScore()).reduce(0.0, (a, b) -> a + b);

				if (processedScore > 0.0) {
					processedPercentage = processedScore * 100 / quiz.getMaxScore();
				}

				if (processedPercentage > 0 && processedPercentage >= quiz.getPassingPercentage()) {

					quizCleared = true;

					attemptStatus = "Passed";
					processedPoints = quiz.getAllocatedPoints();

					user.setProFactor(processedPoints);
					try {
						userDAO.save(user);
					} catch (Exception ex) {
						logger.error("Error occured while updating user pro-factor", ex);
						throw new UnableToSaveException("Error occured while updating user pro-factor");
					}
					try {

						attemptedQuiz = attemptedQuizesDAO.fetchAttemptedQuiz(currentQuizId, userId);

						if (attemptedQuiz != null) {

							totAttempts = attemptedQuiz.getTotalAttempts();
							if (totAttempts == 0.0) {
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

						attemptedQuizesDAO.save(attemptedQuiz);
						
					} catch (Exception ex) {
						logger.error("Error occured while fetching or updating attempted quiz", ex);
						throw new DatabaseException("quizId", quizId,
								"Error occured while fetching or updating attempted quiz");
					}

				}

			}

		}
		generatedQuizResult = new GeneratedQuizResultDTO(String.valueOf(processedPercentage),
				String.valueOf(totAttemptedQuestions), String.valueOf(totCorrectAnswers),
				String.valueOf(totIncorrectAnswers), String.valueOf(processedScore), String.valueOf(attemptStatus),
				String.valueOf(processedPoints));

		generateQuizResultRes.put("generatedQuizResult", generatedQuizResult);

		logger.info("Quiz Result generated successfully ");

		return generateQuizResultRes;
	}

	

}

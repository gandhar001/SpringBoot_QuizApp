package com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories.CustomQueries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizResDTO;

@Repository
@Transactional(readOnly = true)
public class QuizCustomImpl implements QuizCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<QuizResDTO> searchQuiz(String quizKey, Pageable paging) {

		String customQuery = "SELECT new com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizResDTO(qz.quizId,qz.quizName,"
				+ " qz.description,qz.allocatedPoints,qz.allocatedTime,"
				+ "qz.totalQuestions,qz.maxScore,qz.passingPercentage, qz.quizCategory)"
				+ "from Quiz qz where qz.quizName LIKE ?1";

		TypedQuery<QuizResDTO> query = entityManager.createQuery(customQuery, QuizResDTO.class);
		query.setParameter(1, "%" + quizKey + "%");
		query.setFirstResult(paging.getPageNumber());
		query.setMaxResults(paging.getPageSize());

		return query.getResultList();
	}

	@Override
	public List<QuizResDTO> fetchQuizesWithCategory(long categoryId, Pageable paging) {

		String customQuery = "SELECT new com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizResDTO(qz.quizId,qz.quizName,"
				+ " qz.description,qz.allocatedPoints,qz.allocatedTime,qz.totalQuestions,qz.maxScore,qz.passingPercentage, qz.quizCategory) "
				+ "from Quiz qz where qz.quizCategory.categoryId = ?1";

		TypedQuery<QuizResDTO> query = entityManager.createQuery(customQuery, QuizResDTO.class);
		query.setParameter(1, categoryId);
		query.setFirstResult(paging.getPageNumber());
		query.setMaxResults(paging.getPageSize());

		return query.getResultList();
	}

}

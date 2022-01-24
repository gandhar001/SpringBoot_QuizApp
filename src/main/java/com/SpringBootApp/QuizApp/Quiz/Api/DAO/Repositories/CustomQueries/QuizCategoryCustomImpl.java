package com.SpringBootApp.QuizApp.Quiz.Api.DAO.Repositories.CustomQueries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizCategoryDTO;

@Repository
@Transactional(readOnly = true)
public class QuizCategoryCustomImpl implements QuizCategoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<QuizCategoryDTO> searchCategory(String categoryKey, Pageable paging) {
		String customQuery = "SELECT new com.SpringBootApp.QuizApp.Quiz.Api.DTO.ResponseDTO.QuizCategoryDTO(qc.categoryId,qc.category,qc.description,qc.quizCount) from QuizCategory qc";
      
		if (categoryKey != null) {
			customQuery = customQuery.concat(" where qc.category like '%" + categoryKey + "%'");

		}

		TypedQuery<QuizCategoryDTO> query = entityManager.createQuery(customQuery, QuizCategoryDTO.class);

		query.setFirstResult(paging.getPageNumber());
		query.setMaxResults(paging.getPageSize());

		return query.getResultList();
	}

}

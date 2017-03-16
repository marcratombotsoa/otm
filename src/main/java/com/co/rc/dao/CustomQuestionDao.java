package com.co.rc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.co.rc.model.Question;

@Repository
public class CustomQuestionDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Question findByIdAndFetchAnswers(Long id) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Question.class, "question");
		criteria.add(Restrictions.eq("id", id));
		criteria.createCriteria("answers", "answer", JoinType.LEFT_OUTER_JOIN);
		
		return (Question) criteria.uniqueResult();
	}
}

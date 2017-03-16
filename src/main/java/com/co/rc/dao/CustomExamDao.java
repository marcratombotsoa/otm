package com.co.rc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.co.rc.model.Exam;

@Repository
public class CustomExamDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Exam findByIdAndFetchQuestions(Long id) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Exam.class, "exam");
		criteria.add(Restrictions.eq("id", id));
		criteria.createCriteria("questions", "question", JoinType.LEFT_OUTER_JOIN);
		
		return (Exam) criteria.uniqueResult();
	}
}

package com.co.rc.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.co.rc.model.Answer;
import com.co.rc.model.Question;
import com.co.rc.model.User;

public interface AnswerDao extends CrudRepository<Answer, Long> {

	@Query("select answer.id from User user left join user.answers answer where user = ?1 and answer.question = ?2")
	List<Long> findAllByUserAndQuestion(User user, Question question);
	
	@Query("select answer.id from Answer answer where answer.question = ?1 and answer.correct is true")
	List<Long> findCorrectAnswers(Question question);
	
	@Modifying
	@Transactional
	@Query(value = "insert into user_answer values(?1, ?2)", nativeQuery = true)
	void insertUserAnswer(Long userId, Long answerId);
	
	@Modifying
	@Transactional
	@Query(value = "delete from user_answer where user_id = ?1 and answer_id in (?2)", nativeQuery = true)
	void removeUserAnswers(Long userId, List<Long> answerId);
	
	@Modifying
	@Transactional
	@Query(value = "delete ua from user_answer ua "
			+ "inner join answer a on a.id = ua.answer_id "
			+ "inner join question q on q.id = a.question_id "
			+ "where q.exam_id = ?1 and ua.user_id = ?2", nativeQuery = true)
	void resetUserAnswers(Long examId, Long userId);
	
	
	@Query(value = "select q.id, ua.answer_id from user_answer ua "
			+ "join answer a on a.id = ua.answer_id "
			+ "join question q on q.id = a.question_id "
			+ "where q.exam_id = ?1 and ua.user_id = ?2 group by q.id", nativeQuery = true)
	List<Object[]> findUserAnswerByQuestion(Long examId, Long userId);
}

package com.co.rc.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.util.Set;

import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.rc.model.Exam;
import com.co.rc.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GlobalMappingTest {
	
	@Autowired
	private ExamDao examDao;

	@Test(expected = LazyInitializationException.class)
	public void testRetrieveExamWithoutQuestions() {
		Exam exam = examDao.findById(1l);
		Set<Question> questions = exam.getQuestions();
		
		assertNotNull(questions);
		assertFalse(questions.isEmpty());
	}
}

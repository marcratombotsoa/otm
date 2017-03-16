package com.co.rc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_exam")
public class UserExam {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne(targetEntity = Exam.class)
	@JoinColumn(name = "exam_id", nullable = false)
	private Exam exam;
	
	@Column(name = "start_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Column(name = "end_date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date endDate;
	
	@Column(name = "score")
	private Integer score;

	public UserExam() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}

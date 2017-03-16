package com.co.rc.model;

import java.util.SortedSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.google.common.collect.Sets;

@Entity
@Table(name = "question")
public class Question implements Comparable<Question> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(optional = false, targetEntity = Exam.class)
	@JoinColumn(name = "exam_id", nullable = false)
	private Exam exam;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private QuestionTypeEnum type;
	
	@Column(name = "order", nullable = false)
	private Integer order;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "question", targetEntity = Answer.class)
	@OrderBy("order ASC")
	private SortedSet<Answer> answers = Sets.newTreeSet();

	public Question() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public QuestionTypeEnum getType() {
		return type;
	}

	public void setType(QuestionTypeEnum type) {
		this.type = type;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public SortedSet<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(SortedSet<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", description=" + description + ", exam=" + exam + ", type="
				+ type + "]";
	}

	@Override
	public int compareTo(Question o) {
		return order.compareTo(o.order);
	}
}

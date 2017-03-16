package com.co.rc.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.SortedSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Sets;

@Entity
@Table(name = "exam")
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "duration", nullable = false)
	private Long duration;
	
	@Column(name = "pass_score", nullable = false)
	private Long passScore;
	
	@Column(name = "maximum_score", nullable = false)
	private Long maximumScore;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "exam", targetEntity = Question.class)
	@OrderBy("order ASC")
	private SortedSet<Question> questions = Sets.newTreeSet();
	
	public Exam() {
		super();
	}

	public Exam(Long id, String title, String description, Long duration) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Exam [id=" + id + ", title=" + title + ", description=" + description + ", duration=" + duration + "]";
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

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public SortedSet<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(SortedSet<Question> questions) {
		this.questions = questions;
	}

	public Long getPassScore() {
		return passScore;
	}

	public void setPassScore(Long passScore) {
		this.passScore = passScore;
	}

	public Long getMaximumScore() {
		return maximumScore;
	}

	public void setMaximumScore(Long maximumScore) {
		this.maximumScore = maximumScore;
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
		Exam other = (Exam) obj;
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

	public int getMinimumPassScorePercent() {
		return BigDecimal.TEN.multiply(BigDecimal.TEN).multiply(new BigDecimal(passScore))
				.divide(new BigDecimal(maximumScore), 0, RoundingMode.HALF_UP).intValue();
	}
}

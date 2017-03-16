package com.co.rc.model.dto;

public class QuestionDTO {

	private Long questionId;
	private String title;
	private int order;
	private boolean answered;

	public QuestionDTO(Long questionId, String title, int order, boolean answered) {
		super();
		this.questionId = questionId;
		this.title = title;
		this.order = order;
		this.answered = answered;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}

	@Override
	public String toString() {
		return "QuestionDTO [questionId=" + questionId + ", title=" + title + ", order=" + order + ", answered="
				+ answered + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (answered ? 1231 : 1237);
		result = prime * result + order;
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
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
		QuestionDTO other = (QuestionDTO) obj;
		if (answered != other.answered)
			return false;
		if (order != other.order)
			return false;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}

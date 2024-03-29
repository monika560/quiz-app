package com.model;

import java.util.List;

public class QuizWrapper {

	private Integer quizId;
	private List<QuestionWrapper> questions;
	public Integer getQuizId() {
		return quizId;
	}
	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}
	public List<QuestionWrapper> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionWrapper> questions) {
		this.questions = questions;
	}
	public QuizWrapper(Integer quizId, List<QuestionWrapper> questions) {
		super();
		this.quizId = quizId;
		this.questions = questions;
	}
	
}

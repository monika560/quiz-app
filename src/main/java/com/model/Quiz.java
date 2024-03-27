package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Quiz {
	@Id
	int id;
	
	@Column(name = "quiz_title")
	String quizTitle;

	public int getId() {
		return id;
	}

	public String getQuizTitle() {
		return quizTitle;
	}

	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}
	
	
	
}

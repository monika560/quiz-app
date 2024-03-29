package com.exception;

public class QuizNotFound extends RuntimeException{

	public QuizNotFound(String msg) {
		super(msg);
	}
}

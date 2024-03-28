package com.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exception.CategoryNotFound;
import com.model.Question;
import com.model.Quiz;
import com.repository.QuestionRepository;
import com.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	public ResponseEntity createQuiz(String category, Integer noOfQuestions,String title) {
		List<Question> questionList;
		try {
		questionList=questionRepository.findRandomQuestionsByCategory(category,noOfQuestions);
		if(questionList==null || questionList.isEmpty()) {
			throw new CategoryNotFound("Requested category not found for creating quiz");
		}
		}catch(CategoryNotFound ex) {
			return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
		}
		Quiz quiz=new Quiz();
		quiz.setQuizTitle(title);
		quiz.setQuestion(questionList);
		quizRepository.save(quiz);
		return new ResponseEntity("Success",HttpStatus.OK);
		
	}

}

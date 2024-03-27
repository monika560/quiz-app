package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exception.CategoryNotFound;
import com.exception.QuestionNotFoundException;
import com.model.Question;
import com.repository.QuestionRepository;


@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	public ResponseEntity getAllQuestions() {
		try {
			return new ResponseEntity(questionRepository.findAll(),HttpStatus.OK);
			}
			catch(Exception ex) {
				return new ResponseEntity(new ArrayList(), HttpStatus.BAD_REQUEST);
			}
		}

	public ResponseEntity getAllQuestionsByCategory(String category) {
		try {
			List<Question> questionList = questionRepository.findByCategory(category);
			if(questionList==null || questionList.isEmpty()) {
				throw new CategoryNotFound("This category does not exist");
			}
		return new ResponseEntity(questionList,HttpStatus.OK);
		}
		catch(CategoryNotFound ex) {
			return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		}

	public ResponseEntity addQuestion(Question question) {
		try {
		questionRepository.save(question);
		return new ResponseEntity("Success",HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity("Success", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity deleteQuestion(Integer questionId) {
		try {
			Question question=questionRepository.findById(questionId).orElse(null);
			if(question==null) {
				throw new QuestionNotFoundException("Question id is not present or already deleted");
			}
			questionRepository.deleteById(questionId);
			return new ResponseEntity("Deleted",HttpStatus.OK);
			}catch(QuestionNotFoundException ex) {
				return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
			}
	}

	public ResponseEntity updateQuestion(Question question) {
		try {
			Question questionData=questionRepository.findById(question.getId()).orElse(null);
			if(questionData==null) {
				throw new QuestionNotFoundException("Question id does not present or already deleted");
			}
			
			questionRepository.save(question);
			return new ResponseEntity("Updated",HttpStatus.OK);
			}catch(QuestionNotFoundException ex) {
				return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
			}
	}
}

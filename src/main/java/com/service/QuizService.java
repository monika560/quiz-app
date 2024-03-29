package com.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exception.CategoryNotFound;
import com.exception.QuizNotFound;
import com.model.Question;
import com.model.QuestionWrapper;
import com.model.Quiz;
import com.model.QuizWrapper;
import com.model.Response;
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

	public ResponseEntity getQuizQuestions(Integer quizId) {
		Quiz quiz;
		try{
			quiz=quizRepository.findById(quizId).orElse(null);
		
		if(quiz==null) {
			throw new QuizNotFound("Requested quiz does not found");
		}
		}catch(QuizNotFound ex) {
			return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
		}
		
		List<Question> queFromDb=quiz.getQuestion();
		List<QuestionWrapper> queForUser=new ArrayList();
		for(Question question:queFromDb) {
			QuestionWrapper qw=new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
			queForUser.add(qw);
		}
		
		return new ResponseEntity(queForUser,HttpStatus.OK);
	}

	public ResponseEntity getAllQuizQuestions() {
		List<QuizWrapper> quizeListForUser;
		List<Quiz> quizList=quizRepository.findAll();
		try {
		if(quizList==null || quizList.isEmpty()) {
			throw new QuizNotFound("Quiz not yet created or does not exist");
		}
		quizeListForUser=new ArrayList();
		for(Quiz quiz:quizList) {
			List<Question> queFromDb=quiz.getQuestion();
			List<QuestionWrapper> queForUser=new ArrayList();
			for(Question question:queFromDb) {
				QuestionWrapper qw=new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
				queForUser.add(qw);
			}
			QuizWrapper qw=new QuizWrapper(quiz.getId(),queForUser);
			quizeListForUser.add(qw);
		}
		}catch(QuizNotFound ex) {
			return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(quizeListForUser, HttpStatus.OK);
	}

	public ResponseEntity getResultForQuiz(Integer quizId, List<Response> responseList) {
		Quiz quiz=quizRepository.findById(quizId).orElse(null);
		Integer score=0;
		try {
		if(quiz==null) {
			throw new QuizNotFound("Quiz does not exist");
		}
		List<Question> questionFromDb=quiz.getQuestion();
		for(Response response:responseList) {
			for(Question question:questionFromDb) {
				if(response.getId().equals(question.getId())) {
					if(response.getUserUesponse().equals(question.getRightAnser())) {
						score++;
					}
				}
			}
		}
		}
		catch(QuizNotFound ex) {
		return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity("Score = "+score,HttpStatus.OK);
	}

}

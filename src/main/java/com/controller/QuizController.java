package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Response;
import com.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping()
	public ResponseEntity<String> createQuiz(@RequestParam("category") String category, @RequestParam("noOfQuestion") Integer noOfQuestions, @RequestParam("title") String title){
		return quizService.createQuiz(category, noOfQuestions,title);
	
	}
	
	@GetMapping("/{quizId}")
	public ResponseEntity getAllQuestions(@PathVariable("quizId") Integer quizId) {
		return quizService.getQuizQuestions(quizId);
	}
	
	@GetMapping()
	public ResponseEntity getAllQuizQuestions() {
		return quizService.getAllQuizQuestions();
	}
  	
	@PostMapping("/submit/{quizId}")
	public ResponseEntity getResultForQuiz(@PathVariable("quizId") Integer quizId, @RequestBody List<Response> response) {
		return quizService.getResultForQuiz(quizId,response);
	}
	
	

}

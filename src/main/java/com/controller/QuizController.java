package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping()
	public ResponseEntity<String> createQuiz(@RequestParam("category") String category, @RequestParam("noOfQuestion") Integer noOfQuestions, @RequestParam("title") String title){
		quizService.createQuiz(category, noOfQuestions,title);
		return null;
	}

}

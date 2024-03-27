package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Question;
import com.service.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@GetMapping("/allQuestions")
	public ResponseEntity getAllQuestion(){
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/allQuestions/{category}")
	public ResponseEntity getAllQuestionByCategory(@PathVariable("category") String category){
		return questionService.getAllQuestionsByCategory(category);
	}
	
	@PostMapping()
	public ResponseEntity addQuestion(@RequestBody Question question){
		return questionService.addQuestion(question);
	}
	
	@DeleteMapping("/{questionId}")
	public ResponseEntity deleteQuestion(@PathVariable("questionId") Integer questionId){
		return questionService.deleteQuestion(questionId);
	}
	
	@PutMapping()
	public ResponseEntity updateQuestion(@RequestBody Question question){
		return questionService.updateQuestion(question);
	}
	
	
}

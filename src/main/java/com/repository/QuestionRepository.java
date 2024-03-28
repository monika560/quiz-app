package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Question;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String category);

//	@Query(value = "SELECT * FROM question q where q.category=:category ORDER BY RAND() LIMIT :noOfQuestions",nativeQuery = true)
	
	@Query(value = "SELECT * FROM question q where q.category=:category ORDER BY RAND() limit :noOfQuestions",nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, Integer noOfQuestions);

	
	
}

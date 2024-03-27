package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Question;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String category);

	
	
}

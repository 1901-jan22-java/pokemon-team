package com.pokemon.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pokemon.models.Trainer;

class TrainerRepositoryTest {

	@Test
	void test() {
		try {
			ApplicationContext context 
			= new ClassPathXmlApplicationContext("beans.xml");
		
			TrainerRepository repo = (TrainerRepository)
				context.getBean(TrainerRepository.class);
		
			List<Trainer> users = repo.findAll();
			System.out.println(users);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

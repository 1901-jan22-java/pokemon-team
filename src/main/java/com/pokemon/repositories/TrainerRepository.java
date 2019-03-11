package com.pokemon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pokemon.models.Trainer;

@Repository
@Transactional
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

	Trainer findByUsernameIgnoreCase(String username);
	
}

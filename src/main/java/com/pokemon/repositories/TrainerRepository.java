package com.pokemon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.models.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

	Trainer findByUsernameIgnoreCase(String username);
	
}

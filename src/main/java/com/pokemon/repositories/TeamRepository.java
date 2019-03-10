package com.pokemon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.models.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
	
	Team findBytrainerIdIgnoreCase(String username);
	
}

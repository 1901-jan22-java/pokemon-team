package com.pokemon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pokemon.models.Team;
import com.pokemon.models.Trainer;

@Repository
@Transactional
public interface TeamRepository extends JpaRepository<Team, Integer> {
	
	List<Team> findBytrainer(Trainer t);
	
}

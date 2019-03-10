package com.pokemon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pokemon.models.Team;
import com.pokemon.repositories.TeamRepository;

@Service
@Transactional
public class TeamService {
	
	@Autowired
	private TeamRepository teamRepo;
	
	public Team add(Team t) {
		return teamRepo.save(t);
	}
	
	public Team findByUsername(String username) {
		System.out.println("in find by username");
		return teamRepo.findBytrainerIdIgnoreCase(username);
	}
	
}

package com.pokemon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pokemon.models.Team;
import com.pokemon.models.Trainer;
import com.pokemon.repositories.TeamRepository;
import com.pokemon.repositories.TrainerRepository;

@Service
@Transactional
public class TeamService {
	
	@Autowired
	private TeamRepository teamRepo;
	
	public Team add(Team team) {
		if (teamRepo.exists(team.getId()))
			return teamRepo.save(new Team(team.getTrainer(), team.getSlot1(), team.getSlot2(), team.getSlot3(), team.getSlot4(), team.getSlot5(), team.getSlot6()));
		return teamRepo.save(team);
	}
	
	public Team findByTrainer(Trainer trainer) {
		System.out.println("in find by username");
		return teamRepo.findBytrainer(trainer);
	}
	
	public Team UpdateTeam(Team team) {
		if (!teamRepo.exists(team.getId()))
			return null;
		return teamRepo.save(team);
	}
}

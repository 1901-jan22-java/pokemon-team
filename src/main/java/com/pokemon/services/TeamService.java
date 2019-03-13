package com.pokemon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pokemon.models.Pokemon;
import com.pokemon.models.Team;
import com.pokemon.models.Trainer;
import com.pokemon.repositories.PokemonRepository;
import com.pokemon.repositories.TeamRepository;
import com.pokemon.repositories.TrainerRepository;

@Service
@Transactional
public class TeamService {
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private TrainerRepository trainerRepo;
	
	@Autowired
	private PokemonRepository pokemonRepo;
	
	private Pokemon checkIfTeamSlotIsPredefined(Team pokeslot, int slot) {
		switch (slot) {
		case 1:
			if (pokeslot.getSlot1() != null)
				return pokemonRepo.save(pokeslot.getSlot1());
		case 2:
			if (pokeslot.getSlot2() != null)
				return pokemonRepo.save(pokeslot.getSlot2());
		case 3:
			if (pokeslot.getSlot3() != null)
				return pokemonRepo.save(pokeslot.getSlot3());
		case 4:
			if (pokeslot.getSlot4() != null)
				return pokemonRepo.save(pokeslot.getSlot4());
		case 5:
			if (pokeslot.getSlot5() != null)
				return pokemonRepo.save(pokeslot.getSlot5());
		case 6:
			if (pokeslot.getSlot6() != null)
				return pokemonRepo.save(pokeslot.getSlot6());
		}
		return null;
	}
	
	public Team add(Team team) {
		Trainer t = trainerRepo.findByUsernameIgnoreCase(team.getTrainer().getUsername());
		System.out.println("getting ready to create new team");
		System.out.println("creating new team");
		return teamRepo.save(new Team(t, 
				checkIfTeamSlotIsPredefined(team, 1), 
				checkIfTeamSlotIsPredefined(team, 2), 
				checkIfTeamSlotIsPredefined(team, 3), 
				checkIfTeamSlotIsPredefined(team, 4), 
				checkIfTeamSlotIsPredefined(team, 5), 
				checkIfTeamSlotIsPredefined(team, 6)));
	}
	
	public List<Team> findByTrainer(Trainer trainer) {
		return teamRepo.findBytrainer(trainer);
	}
	
	private void setSlot(Team team, Team change, int slot) {
		switch(slot) {
		case 1:
			if (change.getSlot1() != null && pokemonRepo.exists(change.getSlot1().getId()))
				team.setSlot1(change.getSlot1());
			break;
		case 2:
			if (change.getSlot2() != null && pokemonRepo.exists(change.getSlot2().getId()))
				team.setSlot2(change.getSlot2());
			break;
		case 3:
			if (change.getSlot3() != null && pokemonRepo.exists(change.getSlot3().getId()))
				team.setSlot3(change.getSlot3());
			break;
		case 4:
			if (change.getSlot4() != null && pokemonRepo.exists(change.getSlot4().getId()))
				team.setSlot4(change.getSlot4());
			break;
		case 5:
			if (change.getSlot5() != null && pokemonRepo.exists(change.getSlot5().getId()))
				team.setSlot5(change.getSlot5());
			break;
		case 6:
			if (change.getSlot6() != null && pokemonRepo.exists(change.getSlot6().getId()))
				team.setSlot6(change.getSlot6());
			break;
		}
	}
	
	public Team UpdateTeam(Team team) {
		Team t = findByTrainer(team.getTrainer()).stream().filter(f -> f.getId() == team.getId()).findFirst().get();
		if (t != null && !teamRepo.exists(team.getId()))
			return null;
		checkIfTeamSlotIsPredefined(team, 1);
		checkIfTeamSlotIsPredefined(team, 2);
		checkIfTeamSlotIsPredefined(team, 3);
		checkIfTeamSlotIsPredefined(team, 4);
		checkIfTeamSlotIsPredefined(team, 5);
		checkIfTeamSlotIsPredefined(team, 6);
		setSlot(t, team, 1);
		setSlot(t, team, 2);
		setSlot(t, team, 3);
		setSlot(t, team, 4);
		setSlot(t, team, 5);
		setSlot(t, team, 6);
		return teamRepo.save(t);
	}
}

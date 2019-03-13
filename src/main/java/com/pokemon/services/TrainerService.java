package com.pokemon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pokemon.models.Trainer;
import com.pokemon.repositories.TrainerRepository;

@Service
@Transactional
public class TrainerService {
	
	@Autowired
	private TrainerRepository trainerRepo;
	
	public String hashString(String str) {
		String saltedhash = "n0w" + str.hashCode() + "$@1ted";
		String CompleteHash = saltedhash.hashCode() + "";
		return CompleteHash;
	}
	
	public Trainer add(Trainer t) {
		
		return trainerRepo.save(new Trainer(t.getUsername(), hashString(t.getPassword()), t.getFirstName(), t.getLastName()));
	}
	
	public Trainer findByUsername(String username) {
		System.out.println("in find by username " + trainerRepo.findByUsernameIgnoreCase(username));
		return trainerRepo.findByUsernameIgnoreCase(username);
	}

	public List<Trainer> getAll() {
		return (List<Trainer>) trainerRepo.findAll();
	}
	
}

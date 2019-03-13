package com.pokemon.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.models.Team;
import com.pokemon.models.Trainer;
import com.pokemon.services.TeamService;
import com.pokemon.services.TrainerService;

@RestController
@CrossOrigin
public class TeamController {

	@Autowired
	private TeamService service;
	@Autowired
	private TrainerService tservice;
	
	@RequestMapping(value="/teams", method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Team> add(@RequestBody @Valid Team team){
		System.out.println(team.toString());
		tservice.findByUsername(team.getTrainer().getUsername());
		Team t = service.add(team);
		if(t == null) return new ResponseEntity<Team>(HttpStatus.CONFLICT);
		return new ResponseEntity<Team>(t, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/teams/getAll", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Team>> findByTrainer(@RequestBody Trainer trainer){
		List<Team> t = service.findByTrainer(trainer);
		return new ResponseEntity<List<Team>>(t, HttpStatus.OK);
	}
	
	@RequestMapping(value="/teams/get", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Team> findById(@RequestBody Team team){
		List<Team> t = service.findByTrainer(team.getTrainer());
		return new ResponseEntity<Team>(t.stream().filter(f -> f.getId() == team.getId()).findFirst().get(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/teams", method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Team> Updateteam(@RequestBody @Valid Team team){
		if (service.UpdateTeam(team) == null) return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Team>(team, HttpStatus.OK);
	}
	
}

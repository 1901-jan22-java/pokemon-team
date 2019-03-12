package com.pokemon.controllers;

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

@RestController
@CrossOrigin
public class TeamController {

	@Autowired
	private TeamService service;
	
	@RequestMapping(value="/teams", method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Team> add(@RequestBody @Valid Team team){
		Team t = service.add(team);
		if(t == null) return new ResponseEntity<Team>(HttpStatus.CONFLICT);
		return new ResponseEntity<Team>(t, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/teams", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Team> findByTrainer(@RequestBody @Valid Trainer trainer){
		Team t = service.findByTrainer(trainer);
		return new ResponseEntity<Team>(t, HttpStatus.OK);
	}
	
	@RequestMapping(value="/teams", method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Team> Updateteam(@RequestBody @Valid Team team){
		if (service.UpdateTeam(team) == null) return new ResponseEntity<Team>(HttpStatus.CONFLICT);
		return new ResponseEntity<Team>(team, HttpStatus.OK);
	}
	
}

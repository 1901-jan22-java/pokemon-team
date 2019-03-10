package com.pokemon.controllers;

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
import com.pokemon.services.TeamService;

//@RestController
//@CrossOrigin
//public class TeamController {
//
//	@Autowired
//	private TeamService service;
//	
//	@RequestMapping(value="/teams", method=RequestMethod.POST, 
//			consumes=MediaType.APPLICATION_JSON_VALUE, 
//			produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Team> add(@RequestBody Team team){
//		Team t = service.add(team);
//		if(t == null) return new ResponseEntity<Team>(HttpStatus.CONFLICT);
//		return new ResponseEntity<Team>(t, HttpStatus.CREATED);
//	}
//	
//	@RequestMapping(value="/teams/username={name}", method=RequestMethod.POST,
//			consumes=MediaType.APPLICATION_JSON_VALUE,
//			produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Team> findByUsername(@PathVariable String name){
//		Team t = service.findByUsername(name);
//		return new ResponseEntity<Team>(t, HttpStatus.OK);
//	}
//	
//}

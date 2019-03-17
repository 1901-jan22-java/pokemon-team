package com.pokemon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.Validators.TrainerValidator;
import com.pokemon.models.Trainer;
import com.pokemon.services.SecurityService;
import com.pokemon.services.TrainerService;

@RestController
@CrossOrigin
public class TrainerController {

	@Autowired
	private TrainerService service;
	
	 @Autowired
	 private SecurityService securityService;

	 @Autowired
	 private TrainerValidator userValidator;

	
	@RequestMapping(value="/registration", method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Trainer> add(@RequestBody Trainer user, BindingResult bindingResult){
		 userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Trainer>(HttpStatus.CONFLICT);
        }

		Trainer u = service.add(user);
		if(u == null) return new ResponseEntity<Trainer>(HttpStatus.CONFLICT);
		return new ResponseEntity<Trainer>(u, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/trainers", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trainer>> findAll(){
		return new ResponseEntity<List<Trainer>>(service.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetails> findByUsername(@RequestBody Trainer loginInfo){
		System.out.println(loginInfo.toString());
		try {
			UserDetails u = service.login(loginInfo);
			if (u != null) {
				return new ResponseEntity<UserDetails>(u, HttpStatus.OK);
			}
			return new ResponseEntity<UserDetails>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<UserDetails>(HttpStatus.UNAUTHORIZED);
		}
	}
	
}

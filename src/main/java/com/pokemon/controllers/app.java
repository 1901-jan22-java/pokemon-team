package com.pokemon.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.pokemon.models.Team;
import com.pokemon.services.TeamService;

public class app {
	
	@Autowired
	private static TeamService service;
	
	public static void main(String[] args) {
		Team t = service.add(new Team());
	}
}

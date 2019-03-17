package com.pokemon.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pokemon.models.Trainer;
import com.pokemon.repositories.TrainerRepository;

@Service
@Transactional
public class TrainerService implements UserDetailsService {
	
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
	
	public UserDetails login(Trainer trainer) throws UsernameNotFoundException {
		Trainer t = findByUsername(trainer.getUsername());
		if (t != null && t.getPassword().equals(hashString(trainer.getPassword())))
			return loadUserByUsername(t.getUsername());
		throw new UsernameNotFoundException("Username/Password is not currect");
	}
	
	public Trainer findByUsername(String username) {
		return trainerRepo.findByUsernameIgnoreCase(username);
	}

	public List<Trainer> getAll() {
		return (List<Trainer>) trainerRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Trainer t = findByUsername(username);
		if (t != null)
		{
			Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority("Pokemon Trainer"));
            return new org.springframework.security.core.userdetails.User(t.getUsername(), t.getPassword(), grantedAuthorities);
		}
		throw new UsernameNotFoundException("Username/Password is not currect");
	}
	
}

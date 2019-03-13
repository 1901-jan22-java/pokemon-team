package com.pokemon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pokemon.models.Pokemon;

@Repository
@Transactional
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
}

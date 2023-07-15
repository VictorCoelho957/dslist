package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;

@Service  //registar como como componente do sistema
public class GameService {
	
	@Autowired
	private GameRepository gameRepository; //injeção da interface GameRepository nesta classe
	
	public List<GameMinDTO> findAll(){
		List<Game> result=gameRepository.findAll();
		List<GameMinDTO> dto= result.stream().map(x-> new GameMinDTO(x)).toList();
		return dto;
	}

}

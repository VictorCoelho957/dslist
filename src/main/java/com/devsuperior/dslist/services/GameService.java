package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;


@Service  //registar como como componente do sistema
public class GameService {
	
	@Autowired
	
	private GameRepository gameRepository; //injeção da interface GameRepository nesta classe
	
	@Transactional(readOnly=true)//
	public GameDTO findById(Long id) {
		Game result=gameRepository.findById(id).get(); //buscando no banco
		return new GameDTO(result); //convertendo para DTO	
	}
	
	@Transactional(readOnly=true)//
	public List<GameMinDTO> findAll(){
		List<Game> result=gameRepository.findAll();
		List<GameMinDTO> dto= result.stream().map(x-> new GameMinDTO(x)).toList();
		return dto;
	}
	//retorna lista de games por id
	@Transactional(readOnly=true)//
	public List<GameMinDTO> findByList(Long listId){
		List<GameMinProjection> result=gameRepository.searchByList(listId);
		return result.stream().map(x-> new GameMinDTO(x)).toList();
	
	}
	

}

package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.dto.ReplacementDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;
import com.devsuperior.dslist.services.GameListService;
import com.devsuperior.dslist.services.GameService;


@Service  //registar como como componente do sistema
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository; //injeção da interface GameRepository nesta classe
	
	@Autowired
	private GameRepository gameRepository; //injeção da interface GameRepository nesta classe
	
	
	@Transactional(readOnly=true)//
	public List<GameListDTO> findAll(){
		List<GameList> result=gameListRepository.findAll();
		return result.stream().map(x-> new GameListDTO(x)).toList();
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list=gameRepository.searchByList(listId);  //busca ista de gamres  na memoria
		//mover/remover objetos de posição
		GameMinProjection obj =list.remove(sourceIndex);
		///adcionar em uma posição especifica na lista
		list.add(destinationIndex, obj);
		
	//maximo e minimos das posições
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		//laço de repetição para trocar de posição
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}

}

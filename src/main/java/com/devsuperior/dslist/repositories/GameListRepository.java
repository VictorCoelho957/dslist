package com.devsuperior.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.entities.GameList;

//classe de acesso a dados no banco de dados
public interface GameListRepository  extends JpaRepository<GameList, Long>{
	
	

}

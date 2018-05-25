package com.chat.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.model.Funcao;

public interface FuncaoRepository extends JpaRepository<Funcao , Long>{
	//List<Funcao> findAll();
}

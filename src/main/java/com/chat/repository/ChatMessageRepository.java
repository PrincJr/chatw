package com.chat.repository;


import org.springframework.data.repository.CrudRepository;

import com.chat.model.Mensage;

import java.util.List;

public interface ChatMessageRepository extends CrudRepository<Mensage, Long> {
    List<Mensage> findAllByOrderByDataAsc();
    
  
    List<Mensage> findByAuthorAndRecebedor(Long author , Long recebedor);
    
    
}


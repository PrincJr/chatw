package com.chat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.chat.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
		Usuario findByUsername(String username);
		@Override
		List<Usuario> findAll();
		
		Usuario findRoleByUsername ( String username);
}

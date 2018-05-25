package com.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.chat.model.Usuario;
import com.chat.repository.UsuarioRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioRepository.findByUsername(username);
		if(usuario == null) {
			  throw new UsernameNotFoundException(username);
		}else {
			
			return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList(usuario.getRoles()));
		}
		
	}

}

package com.chat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chat.model.Usuario;
import com.chat.repository.UsuarioRepository;

@Controller
public class ProfileController {

	@Autowired
	private UsuarioRepository usersRepository;
	
	@RequestMapping("/perfil")
	public ModelAndView index() {
		ModelAndView andView = new ModelAndView("profile");
		/*
		 * pega o usuario logado
		 */
		String usuarioLogado = new String("");
		SecurityContext context = SecurityContextHolder.getContext();
		if (context instanceof SecurityContext) {
			Authentication authentication = context.getAuthentication();
			if (authentication instanceof Authentication) {
				usuarioLogado = authentication.getName().toString();
				// ate consigo pegar o username, mas o id nao consigo =S
				// usuario = (Usuario)
				// SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //
				// tentei dessa forma mas nao deu certo
			}
		}
		List<Usuario> users = usersRepository.findAll();
		andView.addObject("users", users);
		andView.addObject("usuarioLogado", usuarioLogado);
		Usuario usuarioF = usersRepository.findByUsername(usuarioLogado);
		andView.addObject("funcao", usuarioF.getFuncao());
		return andView;
	}

}

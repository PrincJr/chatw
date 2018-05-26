package com.chat.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chat.model.Mensage;
import com.chat.model.Usuario;
import com.chat.repository.ChatMessageRepository;
import com.chat.repository.UsuarioRepository;

@Controller
@RestController
public class ChatController {

	private String pegausuarioLogado() {

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
		return usuarioLogado;
	} 
	
	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@Autowired
	private UsuarioRepository usersRepository;

	@RequestMapping("/")
	public ModelAndView chat() {
		List<Usuario> users = usersRepository.findAll();
		ModelAndView andView = new ModelAndView("index");
		andView.addObject("users", users);
		/*
		 * pega o usuario logado
		 */
	
		Usuario user = usersRepository.findRoleByUsername(pegausuarioLogado());
		andView.addObject("usuarioLogado", user.getUsername());
		andView.addObject("usuarioLogadoID", user.getId());
		andView.addObject("role", user.getRoles());

		return andView;
	}

	/**
	 * pega conversar ?:)
	 */
	@Autowired
	UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/mensagem/detalhe/json/{id}")
	@ResponseBody
	public List<Mensage> getMensagem(@PathVariable("id") String id) {

		StringBuffer str = new StringBuffer(id);
		str.replace(0, str.indexOf(" ") + 4, "");
		Usuario usuarioLogadoInfo = usersRepository.findByUsername(pegausuarioLogado());

		List<Mensage> mensagensR = chatMessageRepository.findByAuthorAndRecebedor(usuarioLogadoInfo.getId(), Long.valueOf(str.toString()));
		List<Mensage> mensagensF = chatMessageRepository.findByAuthorAndRecebedor(Long.valueOf(str.toString()),usuarioLogadoInfo.getId());
		List<Mensage> mensagens = new ArrayList<Mensage>();
		mensagens.addAll(mensagensR);
		mensagens.addAll(mensagensF);

		Collections.sort(mensagens, new Comparator<Mensage>() {
			@Override
			public int compare(Mensage o1, Mensage o2) {
				if (o1.getData() == null || o2.getData() == null)
					return 0;
				return o1.getData().compareTo(o2.getData());
			}
		});
		return mensagens;
	}

	/**
	 * Ver as mensagem
	 */
	@RequestMapping(value = "/mensagem/detalhe/{id}", method = RequestMethod.GET)
	public ModelAndView mensagem(@PathVariable("id") String id) {
		ModelAndView andView = new ModelAndView("mensagem");

		// Elimina o nome do id= *-
		StringBuffer str = new StringBuffer(id);
		str.replace(0, str.indexOf(" ") + 4, "");

		Optional<Usuario> usuarioF =  usuarioRepository.findById(Long.valueOf(str.toString()));
	
		List<Usuario> users = usersRepository.findAll();
		andView.addObject("user", usuarioF.get().getUsername());
		andView.addObject("users", users);
		andView.addObject("usuarioLogado", pegausuarioLogado());
		/*
		 * Informação do usuário selecionado
		 */
		
		andView.addObject("funcao", usuarioF.get().getFuncao());
		andView.addObject("userID", usuarioF.get().getId());
		Usuario user = usersRepository.findRoleByUsername( pegausuarioLogado());
		andView.addObject("role", user.getRoles());
		andView.addObject("usuarioLogadoID", user.getId());
		return andView;
	}

	String c[] = { MediaType.APPLICATION_JSON_VALUE };

	/**
	 * Envia mensagem
	 */

	

	@RequestMapping(value = "/mensagem/detalhe/json/", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<Mensage> mensagemEnvia(@RequestBody Mensage mensage) {

		/*
		 * Dados nescesários para salvar mensagem
		 */

		mensage.setData(new java.util.Date());

		chatMessageRepository.save(mensage);

		List<Mensage> mensagensR = chatMessageRepository.findByAuthorAndRecebedor(mensage.getAuthor(),
				mensage.getRecebedor());
		List<Mensage> mensagensF = chatMessageRepository.findByAuthorAndRecebedor(mensage.getRecebedor(),
				mensage.getAuthor());
		List<Mensage> mensagens = new ArrayList<Mensage>();
		mensagens.addAll(mensagensR);
		mensagens.addAll(mensagensF);

		Collections.sort(mensagens, new Comparator<Mensage>() {
			@Override
			public int compare(Mensage o1, Mensage o2) {
				if (o1.getData() == null || o2.getData() == null)
					return 0;
				return o1.getData().compareTo(o2.getData());
			}
		});
		return mensagens;

	}


}

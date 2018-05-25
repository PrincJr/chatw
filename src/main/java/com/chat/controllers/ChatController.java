package com.chat.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chat.model.AjaxResponseBody;
import com.chat.model.Mensage;
import com.chat.model.Usuario;
import com.chat.repository.ChatMessageRepository;
import com.chat.repository.UsuarioRepository;

@Controller
public class ChatController {

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
		String usuarioLogado = new String("");
		SecurityContext context = SecurityContextHolder.getContext();
		if (context instanceof SecurityContext) {
			Authentication authentication = context.getAuthentication();
			if (authentication instanceof Authentication) {
				usuarioLogado = authentication.getName().toString();
			}
		}
		Usuario user = usersRepository.findRoleByUsername(usuarioLogado);
		andView.addObject("usuarioLogado", usuarioLogado);
		andView.addObject("role", user.getRoles());

		return andView;
	}

	/**
	 * pega conversar ?:)
	 */
	@Autowired
	UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/mensagem/detalhe/json/{usuario}")
	@ResponseBody
	public List<Mensage> getMensagem(@PathVariable("usuario") String usuario) {

		StringBuffer str = new StringBuffer(usuario);
		str.replace(0, str.indexOf(" ") + 9, "");
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

		List<Mensage> mensagensR = chatMessageRepository.findByAuthorAndRecebedor(usuarioLogado, str.toString());
		List<Mensage> mensagensF = chatMessageRepository.findByAuthorAndRecebedor(str.toString(), usuarioLogado);
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
	@RequestMapping(value = "/mensagem/detalhe/{usuario}", method = RequestMethod.GET)
	public ModelAndView mensagem(@PathVariable("usuario") String usuario) {
		ModelAndView andView = new ModelAndView("mensagem");

		// Elimina o nome do id= *-
		StringBuffer str = new StringBuffer(usuario);
		str.replace(0, str.indexOf(" ") + 9, "");

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

		//

		List<Usuario> users = usersRepository.findAll();
		andView.addObject("user", str.toString());
		andView.addObject("users", users);
		andView.addObject("usuarioLogado", usuarioLogado);
		Usuario usuarioF = usuarioRepository.findByUsername(str.toString());
		andView.addObject("funcao", usuarioF.getFuncao());

		Usuario user = usersRepository.findRoleByUsername(usuarioLogado);
		andView.addObject("role", user.getRoles());
		return andView;
	}

	String c[] = { MediaType.APPLICATION_JSON_VALUE };

	/**
	 * Envia mensagem
	 */

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

	@RequestMapping(value = "/mensagem/detalhe/json/", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<Mensage> mensagemEnvia(@RequestBody Mensage mensage) {

		/*
		 * Dados nescesários para salvar mensagem
		 */

		mensage.setAuthor(pegausuarioLogado());
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

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public HttpEntity<List<Mensage>> list() {
		List<Mensage> chatMessageModelList = chatMessageRepository
				.findAll(new PageRequest(0, 5, Sort.Direction.DESC, "createDate")).getContent();
		return new ResponseEntity<List<Mensage>>(chatMessageModelList, HttpStatus.OK);
	}

}

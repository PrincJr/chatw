package com.chat.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Mensage implements Comparable<Mensage> {
	@Id
	private String id;

	private String mensagem;

	private String author;
	private String recebedor;
	private Date data;

	public Mensage(String mensagem, String author, String recebedor, Date data) {
		// TODO Auto-generated constructor stub
		this.mensagem = mensagem;
		this.author = author;
		this.recebedor = recebedor;
		this.data = data;
	}
	public Mensage() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return author;
	}

	public void setName(String name) {
		this.author = name;
	}

	public String getRecebedor() {
		return recebedor;
	}

	public void setRecebedor(String recebedor) {
		this.recebedor = recebedor;
	}

	@Override
	public int compareTo(Mensage arg0) {
		if (getData() == null || arg0.getData() == null)
			return 0;
		return getData().compareTo(arg0.getData());
	}
}

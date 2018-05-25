package com.chat.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="mensagens")
public class Mensage implements Comparable<Mensage> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;


	private String mensagem;

	private Long author;
	private Long recebedor;
	private Date data;

	public Mensage(String mensagem, Long author, Long recebedor, Date data) {
		// TODO Auto-generated constructor stub
		this.mensagem = mensagem;
		this.author = author;
		this.recebedor = recebedor;
		this.data = data;
	}
	public Mensage() {
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Long getAuthor() {
		return author;
	}
	public void setAuthor(Long author) {
		this.author = author;
	}
	public Long getRecebedor() {
		return recebedor;
	}
	public void setRecebedor(Long recebedor) {
		this.recebedor = recebedor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public int compareTo(Mensage arg0) {
		if (getData() == null || arg0.getData() == null)
			return 0;
		return getData().compareTo(arg0.getData());
	}
}

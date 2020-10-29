package br.com.prog3.aula17.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "login")
	private String login;
	@Column(name = "senha")
	private int senha;
	@Column(name = "recebeprom")
	private boolean recebeprom;
	public Cliente() {
	}
	public Cliente(String login, int senha) {
		this.login = login;
		this.senha = senha;
		this.recebeprom = false;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	public boolean isRecebeProm() {
		return recebeprom;
	}
	public void setRecebeProm(boolean recebeprom) {
		this.recebeprom = recebeprom;
	}
}
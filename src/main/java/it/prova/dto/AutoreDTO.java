package it.prova.dto;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import it.prova.model.Autore;

public class AutoreDTO {

	private Long id;
	private String nome;
	private String cognome;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascita;
	private String nickname;

	public AutoreDTO() {
	}

	public AutoreDTO(Autore autoreInput) {
		this.id = autoreInput.getId();
		this.nome = autoreInput.getNome();
		this.cognome = autoreInput.getCognome();
		this.dataNascita = autoreInput.getDataNascita();
		this.nickname = autoreInput.getNickname();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public static Autore buildAutoreInstance(AutoreDTO input) {
		return new Autore(input.getId(),input.getNome(), input.getCognome(), input.getDataNascita(), input.getNickname());
	}

	public static Autore buildAutoreInstanceForFindByExample(AutoreDTO input) {
		Autore example = new Autore();
		example.setNome(StringUtils.isNotBlank(input.getNome()) ? input.getNome() : null);
		example.setCognome(StringUtils.isNotBlank(input.getCognome()) ? input.getCognome() : null);
		example.setNickname(StringUtils.isNotBlank(input.getNickname()) ? input.getNickname() : null);
		example.setDataNascita(input.getDataNascita());
		return example;
	}

}

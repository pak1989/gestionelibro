package it.prova.dto;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import it.prova.model.Autore;
import it.prova.model.Libro;

public class LibroDTO {

	private Long id;
	private String titolo;
	private String genere;
	private Integer numeroPagine;
	private Long isbn;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataPubblicazione;
	private Autore autore;

	public LibroDTO() {
	}

	public LibroDTO(Libro libroInput) {
		this.id = libroInput.getId();
		this.titolo = libroInput.getTitolo();
		this.genere = libroInput.getGenere();
		this.numeroPagine = libroInput.getNumeroPagine();
		this.dataPubblicazione = libroInput.getDataPubblicazione();
		this.isbn = libroInput.getIsbn();
		this.autore = libroInput.getAutore();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public Integer getNumeroPagine() {
		return numeroPagine;
	}

	public void setNumeroPagine(Integer numeroPagine) {
		this.numeroPagine = numeroPagine;
	}

	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	public static Libro buildLibroInstance(LibroDTO input) {
		return new Libro(input.getId(), input.getTitolo(), input.getGenere(), input.getNumeroPagine(), true,
				input.getIsbn(), input.getDataPubblicazione(), input.getAutore());
	}

	public static Libro buildLibroInstanceForFindByExample(LibroDTO input) {
		Libro example = new Libro();
		example.setTitolo(StringUtils.isNotBlank(input.getTitolo()) ? input.getTitolo() : null);
		example.setGenere(StringUtils.isNotBlank(input.getGenere()) ? input.getGenere() : null);
		example.setNumeroPagine(StringUtils.isNumeric(input.getNumeroPagine() + "") ? input.getNumeroPagine() : null);
		example.setDataPubblicazione(input.getDataPubblicazione());
		example.setIsbn(StringUtils.isNumeric(input.getIsbn() + "") ? input.getIsbn() : null);
		example.setAutore(input.getAutore());
		return example;
	}

	@Override
	public String toString() {
		return "LibroDTO [id=" + id + ", titolo=" + titolo + ", genere=" + genere + ", numeroPagine=" + numeroPagine
				+ ", dataPubblicazione=" + dataPubblicazione + ", ISBN=" + isbn + ", autore=" + autore.getNome() + "]";
	}

}

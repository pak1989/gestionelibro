package it.prova.service;

import java.util.List;

import it.prova.model.Libro;

public interface LibroService {
	
	public List<Libro> listAllLibri();

	public Libro caricaSingolo(Long id);

	public void aggiorna(Libro libroInstance);

	public void inserisciNuovo(Libro libroInstance);

	public void rimuovi(Libro libroInstance);

	public List<Libro> findByExample(Libro example);
	
	public List<Libro> listAllLibriEager();
	
	public void cambiaStatoAttivo(Long idLibro);

}

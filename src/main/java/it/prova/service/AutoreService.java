package it.prova.service;

import java.util.List;

import it.prova.model.Autore;

public interface AutoreService {
	
	public List<Autore> listAllAutori();

	public Autore caricaSingolo(Long id);

	public void aggiorna(Autore autoreInstance);

	public void inserisciNuovo(Autore autoreInstance);

	public void rimuovi(Autore autoreInstance);

	public List<Autore> findByExample(Autore example);
	
	public List<Autore> cercaByNomeILike(String term);

}

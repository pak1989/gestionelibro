package it.prova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.model.Libro;
import it.prova.repository.AutoreRepository;
import it.prova.repository.LibroRepository;

@Component
public class LibroServiceImpl implements LibroService {
	
	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutoreRepository autoreRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Libro> listAllLibri() {
		return (List<Libro>) libroRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Libro> listAllLibriEager() {
		return (List<Libro>) libroRepository.findAllEager();
	}

	@Override
	public Libro caricaSingolo(Long id) {
		autoreRepository.count();
		return libroRepository.findOne(id);
	}

//	@Override
//	public Libro caricaSingoloEager(Long id) {
//		return libroRepository.findOneEager(id);
//	}

	@Override
	@Transactional
	public void aggiorna(Libro libroInstance) {
		libroRepository.save(libroInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Libro libroInstance) {
		libroRepository.save(libroInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Libro libroInstance) {
		libroRepository.delete(libroInstance);
	}

	@Transactional(readOnly = true)
	public List<Libro> findByExample(Libro exampleInput) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		autoreRepository.findAll();
		return (List<Libro>) libroRepository.findAll(Example.of(exampleInput, matcher));
	}
	
	@Transactional
	public void cambiaStatoAttivo(Long idLibro) {
		Libro libroDaModificare = libroRepository.findOne(idLibro);
		libroDaModificare.setAttivo(libroDaModificare.getAttivo() ? false : true);
	}

}

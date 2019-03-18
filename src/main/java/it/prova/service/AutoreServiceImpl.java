package it.prova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.model.Autore;
import it.prova.repository.AutoreRepository;

@Component
public class AutoreServiceImpl implements AutoreService {

	@Autowired
	private AutoreRepository autoreRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Autore> listAllAutori() {
		return (List<Autore>) autoreRepository.findAll();
	}

	@Override
	public Autore caricaSingolo(Long id) {
		return autoreRepository.findOne(id);
	}

	@Override
	@Transactional
	public void aggiorna(Autore autoreInstance) {
		autoreRepository.save(autoreInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Autore autoreInstance) {
		autoreRepository.save(autoreInstance);
	}

	@Override
	public void rimuovi(Autore autoreInstance) {
		autoreRepository.delete(autoreInstance);
	}

	@Transactional(readOnly = true)
	public List<Autore> findByExample(Autore exampleInput) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING); // Match string
																										// containing
																										// pattern
		// .withIgnoreCase();
		return (List<Autore>) autoreRepository.findAll(Example.of(exampleInput, matcher));
	}

	@Transactional(readOnly = true)
	public List<Autore> cercaByNomeILike(String term) {
		return autoreRepository.findAllByNomeContaining(term);
	}
}

package it.prova.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.model.Autore;

public interface AutoreRepository extends CrudRepository<Autore, Long>,QueryByExampleExecutor <Autore> {
	
	List<Autore> findAllByNomeContaining(String term);

}

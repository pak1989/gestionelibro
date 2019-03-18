package it.prova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.model.Libro;

public interface LibroRepository extends CrudRepository<Libro, Long>,QueryByExampleExecutor <Libro> {
	
	 @Query("FROM Libro lib LEFT JOIN FETCH lib.autore")
	 List<Libro> findAllEager();

//	 @Query("FROM Libro lib LEFT JOIN FETCH lib.autore WHERE lib.id = ?1%")
//	 Libro findOneEager(Long id);

}

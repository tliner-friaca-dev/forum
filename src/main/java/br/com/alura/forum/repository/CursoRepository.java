package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	// List<Curso> findByNome(String nomeCurso);
	
	Curso findByNome(String nomeCurso);
	
}

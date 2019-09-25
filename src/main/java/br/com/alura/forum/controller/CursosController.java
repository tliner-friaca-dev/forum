package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.CursoDto;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursoRepository;

@RestController
public class CursosController {

	@Autowired
	private CursoRepository cursoRepository;

	@RequestMapping("/cursos")
	public List<CursoDto> lista(String nomeCurso){
		
		if (nomeCurso == null) {
			List<Curso> cursos = cursoRepository.findAll();
			return CursoDto.converter(cursos);
		}else {
			List<Curso> cursos = cursoRepository.findByNome(nomeCurso);
			return CursoDto.converter(cursos);
		}
	}
	

}

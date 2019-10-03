package br.com.alura.forum.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.CursoDto;
import br.com.alura.forum.controller.dto.DetalhesDoCursoDto;
import br.com.alura.forum.controller.form.CursoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursoRepository;

@RestController
@RequestMapping("/cursos")
public class CursosController {

	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<CursoDto> lista(String nomeCurso){
		
		if (nomeCurso == null) {
			List<Curso> cursos = cursoRepository.findAll();
			return CursoDto.converter(cursos);
		}//else {
			//List<Curso> cursos = cursoRepository.findByNome(nomeCurso);
			//return CursoDto.converter(cursos);
		//}
		return null;
	}
	
	@GetMapping("/{id}")
	public DetalhesDoCursoDto detalhar(@PathVariable Long id) {
		Curso curso = cursoRepository.getOne(id);
		
		return new DetalhesDoCursoDto(curso);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CursoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCursoForm form){
		Curso curso = form.atualizar(id, cursoRepository);
		return ResponseEntity.ok(new CursoDto(curso));
	}

}















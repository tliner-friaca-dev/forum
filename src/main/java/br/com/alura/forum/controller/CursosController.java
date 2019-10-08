package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.CursoDto;
import br.com.alura.forum.controller.dto.DetalhesDoCursoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
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
	
	@PostMapping
	public ResponseEntity<CursoDto> cadastrar(@RequestBody @Valid CursoForm cursoForm, UriComponentsBuilder uriBuilder){
		Curso curso = cursoForm.converter(cursoRepository);
		cursoRepository.save(curso);
		
		URI uri = uriBuilder.path("curso/{id}").buildAndExpand(curso.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CursoDto(curso));
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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		cursoRepository.deleteById(id);
		return ResponseEntity.ok().build(); 
	}

}















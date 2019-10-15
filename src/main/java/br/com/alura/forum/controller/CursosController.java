package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
	@Transactional
	public ResponseEntity<CursoDto> cadastrar(@RequestBody @Valid CursoForm cursoForm, UriComponentsBuilder uriBuilder){
		Curso curso = cursoForm.converter(cursoRepository);
		cursoRepository.save(curso);
		
		URI uri = uriBuilder.path("curso/{id}").buildAndExpand(curso.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CursoDto(curso));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoCursoDto> detalhar(@PathVariable Long id) {
		Optional<Curso> curso = cursoRepository.findById(id);
		if (curso.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoCursoDto(curso.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CursoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCursoForm form){
		Optional<Curso> cursoOptional = cursoRepository.findById(id);
		if (cursoOptional.isPresent()) {
			Curso curso = form.atualizar(id, cursoRepository);
			return ResponseEntity.ok(new CursoDto(curso));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Curso> cursoOptional = cursoRepository.findById(id);
		if (cursoOptional.isPresent()) {
			cursoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}















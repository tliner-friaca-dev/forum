package br.com.alura.forum.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursoRepository;

public class AtualizacaoCursoForm {
	
	@NotNull @NotEmpty @Length(min = 10)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Curso atualizar(Long id, CursoRepository cursoRepository) {
		Curso curso = cursoRepository.getOne(id);
		curso.setNome(this.nome);
		return curso;
	}

}

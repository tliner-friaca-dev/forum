package br.com.alura.forum.controller.dto;

import br.com.alura.forum.modelo.Curso;

public class DetalhesDoCursoDto {
	
	private Long id;
	private String nome;
	private String categoria;
	
	public DetalhesDoCursoDto(Curso curso) {
		this.id = curso.getId();
		this.nome = curso.getNome();
		this.categoria = curso.getCategoria();
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}

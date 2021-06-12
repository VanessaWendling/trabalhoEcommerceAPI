package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Produto;

public class ProdutoMostrarDTO {
	private String nome;
	private String url;
	
	public ProdutoMostrarDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProdutoMostrarDTO(Produto produto) {
		super();
		this.nome = produto.getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}

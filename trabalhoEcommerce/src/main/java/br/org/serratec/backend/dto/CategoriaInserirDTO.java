package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Categoria;

public class CategoriaInserirDTO {
		private String nome;
		private String descricao;
		
		public CategoriaInserirDTO() {
			// TODO Auto-generated constructor stub
		}

		public CategoriaInserirDTO(Categoria categoria) {
			super();
			this.nome = categoria.getNome();
			this.descricao = categoria.getDescricao();
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
}

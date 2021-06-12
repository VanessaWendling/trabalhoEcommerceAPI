package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Endereco;

public class EnderecoInserirDTO {
	private String cep;
	
	public EnderecoInserirDTO() {
		// TODO Auto-generated constructor stub
	}

	public EnderecoInserirDTO(Endereco endereco) {
		super();
		this.cep = endereco.getCep();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
}

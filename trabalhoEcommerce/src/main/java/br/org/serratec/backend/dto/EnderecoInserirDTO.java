package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Endereco;

public class EnderecoInserirDTO {
	private String cep;
	private Integer numero;
	
	public EnderecoInserirDTO() {
		// TODO Auto-generated constructor stub
	}

	public EnderecoInserirDTO(Endereco endereco) {
		super();
		this.cep = endereco.getCep();
		this.numero = endereco.getNumero();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
}

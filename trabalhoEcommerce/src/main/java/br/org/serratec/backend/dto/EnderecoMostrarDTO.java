package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Endereco;

public class EnderecoMostrarDTO {
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String complemento;
	private String uf;
	
	public EnderecoMostrarDTO() {
		// TODO Auto-generated constructor stub
	}

	public EnderecoMostrarDTO(Endereco endereco) {
		super();
		this.cep = endereco.getCep();
		this.logradouro = endereco.getRua();
		this.bairro = endereco.getBairro();
		this.localidade = endereco.getCidade();
		this.uf = endereco.getEstado();
		this.complemento = endereco.getComplemento();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	
	
}
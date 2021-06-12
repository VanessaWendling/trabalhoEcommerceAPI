package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Endereco;

public class EnderecoMostrarDTO {

	private String rua;
	private Integer numero;
	private String bairro;
	private String cidade;
	private String estado;
	
	public EnderecoMostrarDTO() {
		// TODO Auto-generated constructor stub
	}

	public EnderecoMostrarDTO(Endereco endereco) {
		super();
		this.rua = endereco.getRua();
		this.numero = endereco.getNumero();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.estado = endereco.getEstado();
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
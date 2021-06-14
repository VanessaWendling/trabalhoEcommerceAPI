package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;

public class CadastroMostrarDTO {
	private Long id;
	private String nomeUsuario;
	/*private String rua;
	private Integer numero;
	private String bairro;
	private String cidade;
	private String estado;*/
	private Endereco enderecos;
	public CadastroMostrarDTO(Cliente cliente) {
		super();
		this.id = cliente.getIdCliente();
		this.nomeUsuario = cliente.getNomeUsuario();
//		this.rua = endereco.getRua();
//		this.numero = endereco.getNumero();
//		this.bairro = endereco.getBairro();
//		this.cidade = endereco.getCidade();
//		this.estado = endereco.getEstado();
		this.enderecos = cliente.getEnderecos();
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
//	public String getRua() {
//		return rua;
//	}
//	public void setRua(String rua) {
//		this.rua = rua;
//	}
//	public Integer getNumero() {
//		return numero;
//	}
//	public void setNumero(Integer numero) {
//		this.numero = numero;
//	}
//	public String getBairro() {
//		return bairro;
//	}
//	public void setBairro(String bairro) {
//		this.bairro = bairro;
//	}
//	public String getCidade() {
//		return cidade;
//	}
//	public void setCidade(String cidade) {
//		this.cidade = cidade;
//	}
//	public String getEstado() {
//		return estado;
//	}
//	public void setEstado(String estado) {
//		this.estado = estado;
//	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Endereco getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(Endereco enderecos) {
		this.enderecos = enderecos;
	}
	
	
}

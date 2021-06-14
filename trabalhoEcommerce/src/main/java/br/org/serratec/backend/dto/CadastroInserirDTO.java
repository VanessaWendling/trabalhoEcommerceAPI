package br.org.serratec.backend.dto;

import java.time.LocalDate;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;

public class CadastroInserirDTO {

	private String nomeCompleto;
    private String nomeUsuario;
    private String email;
    private String cpf;
    private String senha;
    private LocalDate dataNasc;
    private String telefone;
    private String cep;
    private Integer numero;
	
    public CadastroInserirDTO(Cliente cliente, Endereco endereco) {
		this.nomeCompleto = cliente.getNomeCompleto();
		this.nomeUsuario = cliente.getNomeUsuario();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
		this.senha = cliente.getSenha();
		this.dataNasc = cliente.getDataNasc();
		this.telefone = cliente.getTelefone();
		this.cep = endereco.getCep();
		this.numero = endereco.getNumero();
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

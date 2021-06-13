package br.org.serratec.backend.dto;

import java.time.LocalDate;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;

public class ClienteInserirDTO {
	private String nomeCompleto;
    private String nomeUsuario;
    private String email;
    private String cpf;
    private String senha;
    private LocalDate dataNasc;
	
	public ClienteInserirDTO() {
		// TODO Auto-generated constructor stub
	}

	public ClienteInserirDTO(Cliente cliente) {
		super();
		this.nomeCompleto = cliente.getNomeCompleto();
		this.nomeUsuario = cliente.getNomeUsuario();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
		this.senha = cliente.getSenha();
		this.dataNasc = cliente.getDataNasc();
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
}

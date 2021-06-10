package br.org.serratec.backend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "cliente")
public class Cliente {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long idCliente;
	
	@NotBlank(message = "Nome Invalido")
	@Size(max = 60, message = "Nome acima de 60 caracteres")
	@Column(name = "nome_completo")
	private String nomeCompleto;
	
	@NotBlank(message = "Nome do usuario Invalido")
	@Size(max = 20, message = "Nome acima de 20 caracteres")
	@Column(name = "nome_usuario")
	private String nomeUsuario;
	
	@NotBlank(message = "email vazio")
	@Column
	@Email(message = "Email invalido")
	private String email;
	
	@NotBlank(message = "cpf vazio")
	@Column
	@CPF(message = "CPF no formato invalido")
	private String cpf;
	
	@Past
	@Column(name = "data_nasc")
	private LocalDate dataNasc;

	@Embedded
	private Endereco endereco;
	


	public Cliente(Long idCliente,
			@NotBlank(message = "Nome Invalido") @Size(max = 60, message = "Nome acima de 60 caracteres") String nomeCompleto,
			@NotBlank(message = "Nome do usuario Invalido") @Size(max = 20, message = "Nome acima de 20 caracteres") String nomeUsuario,
			@NotBlank(message = "email vazio") @Email(message = "Email invalido") String email,
			@NotBlank(message = "cpf vazio") @CPF(message = "CPF no formato invalido") String cpf,
			@Past LocalDate dataNasc, Endereco endereco) {
		super();
		this.idCliente = idCliente;
		this.nomeCompleto = nomeCompleto;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.endereco = endereco;
	}


	public Cliente() {
		super();
	}


	public Long getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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


	public LocalDate getDataNasc() {
		return dataNasc;
	}


	public void setDataNascimento(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	 
}

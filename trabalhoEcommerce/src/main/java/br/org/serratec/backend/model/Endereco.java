package br.org.serratec.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	@ApiModelProperty(value = "Identidicador único de Endereco")
	private Long id;
	
	@NotBlank (message = "Campo CEP vazio")
	@Size(max = 9)
	@ApiModelProperty(value = "Cep", required = true)
	private String cep;
	
	
	@Size(max = 100)
	@ApiModelProperty(value = "Rua")
	private String rua;
	
	
	@Size(max = 50)
	@ApiModelProperty(value = "Bairro")
	private String bairro;
	
	@Size(max = 30)
	@ApiModelProperty(value = "Cidade")
	private String cidade;
	
	@Size(max = 20)
	@ApiModelProperty(value = "Complemento")
	private String complemento;
	
	@Size(max = 2)
	@ApiModelProperty(value = "Estado")
	private String estado;
	
	
	public Endereco() {
		// TODO Auto-generated constructor stub
	}
	
	public Endereco(Long id, String cep, String rua, String bairro, String cidade, String complemento,
			String estado) {
		super();
		this.id = id;
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.complemento = complemento;
		this.estado = estado;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
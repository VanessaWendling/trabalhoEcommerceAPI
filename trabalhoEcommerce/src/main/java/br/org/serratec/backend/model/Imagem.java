package br.org.serratec.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Imagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_imagem")
	@ApiModelProperty(value = "Identificador único da Imagem")
	private Long id;
	
	//smp que for trabalhar com foto precisa colocar
	@Lob // significa que vai trabalhar com binário
	@ApiModelProperty(value = "Dados", required = true)
	private byte[] dados;
	@ApiModelProperty(value = "Tipo", required = true)
	private String tipo;
	@ApiModelProperty(value = "Nome", required = true)
	private String nome;
	
	@OneToOne
	@JoinColumn (name = "id_produto")
	//@ApiModelProperty(value = "Produto", required = true)
	private Produto produto;
	
	public Imagem() {
		// TODO Auto-generated constructor stub
	}

	public Imagem(Long id, byte[] dados, String tipo, String nome, Produto produto) {
		super();
		this.id = id;
		this.dados = dados;
		this.tipo = tipo;
		this.nome = nome;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
		Imagem other = (Imagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

package br.org.serratec.backend.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	@ApiModelProperty(value = "Identificador único do Produto")
	private Long id;
	
	@NotBlank (message = "Campo NOME vazio")
    @Size(max = 30, message = "NOME acima de 30 caracteres")
	@ApiModelProperty(value = "Nome", required = true)
    private String nome;

    @Size(max = 100, message = "DESCRIÇÃO acima de 100 caracteres")
    @ApiModelProperty(value = "Descricao")
    private String descricao;

    @Past
    @Column(name = "data_cadastro")
    @ApiModelProperty(value = "Data de Cadastro")
    private LocalDate dataCadastro;


    @Column(name = "valor_unitario")
    @ApiModelProperty(value = "Valor Unitário", required = true)
    private Double valorUnitario;
	
	@JsonBackReference
	@ManyToOne 
	@JoinColumn(name = "id_categoria")
	@ApiModelProperty(value = "Categoria")
	private Categoria categoria;
	
	@OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
	private Imagem imagem;
	
	/*@JsonBackReference
	@OneToMany(mappedBy = "produto")
	@ApiModelProperty(value = "Lista de Pedidos")
	private List<ItemPedido> itemPedido = new ArrayList<ItemPedido>();*/
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(Long id,
			@NotBlank(message = "Campo NOME vazio") @Size(max = 30, message = "NOME acima de 30 caracteres") String nome,
			@Size(max = 100, message = "DESCRIÇÃO acima de 100 caracteres") String descricao,
			@Past LocalDate dataCadastro, Double valorUnitario, Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;
		this.valorUnitario = valorUnitario;
		this.categoria = categoria;
		//this.itemPedido = itemPedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/*public List<ItemPedido> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(List<ItemPedido> itemPedido) {
		this.itemPedido = itemPedido;
	}*/

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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
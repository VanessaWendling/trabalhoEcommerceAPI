package br.org.serratec.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	@ApiModelProperty(value = "Identificador único do Pedido Item")
	private Long idItemPedido;

	@NotBlank (message = "Campo PREÇO VENDA vazio")
    @Column(name = "preco_venda")
	@ApiModelProperty(value = "Preço de venda", required = true)
    private Integer precoVenda;

    @NotBlank (message = "Campo QUANTIDADE vazio")
    @Column(name = "quantidade")
    @ApiModelProperty(value = "Quantidade", required = true)
    private Integer quantidade;

	@ManyToOne
	@JoinColumn(name="id_pedido")
	@ApiModelProperty(value = "Identificador único do Pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="id_produto")
	@ApiModelProperty(value = "Identificador único do Produto")
	private Produto produto;
	

	public ItemPedido() {
		// TODO Auto-generated constructor stub
	}

	public ItemPedido(Long idItemPedido, Integer precoVenda, Integer quantidade, Pedido pedido, Produto produto) {
		super();
		this.idItemPedido = idItemPedido;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
		this.pedido = pedido;
		this.produto = produto;
	}

	public Long getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Long idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Integer precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		result = prime * result + ((idItemPedido == null) ? 0 : idItemPedido.hashCode());
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
		ItemPedido other = (ItemPedido) obj;
		if (idItemPedido == null) {
			if (other.idItemPedido != null)
				return false;
		} else if (!idItemPedido.equals(other.idItemPedido))
			return false;
		return true;
	}

}

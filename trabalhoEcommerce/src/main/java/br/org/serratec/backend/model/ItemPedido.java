package br.org.serratec.backend.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Long iditemPedido;

	@Column(name = "preco_venda")
	private Integer precoVenda;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Embedded
	private Pedido pedido;

	public ItemPedido() {
		// TODO Auto-generated constructor stub
	}

	public ItemPedido(Long iditemPedido, Integer precoVenda, Integer quantidade, Pedido pedido) {
		super();
		this.iditemPedido = iditemPedido;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
		this.pedido = pedido;
	}

	public Long getIditemPedido() {
		return iditemPedido;
	}

	public void setIditemPedido(Long iditemPedido) {
		this.iditemPedido = iditemPedido;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iditemPedido == null) ? 0 : iditemPedido.hashCode());
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
		if (iditemPedido == null) {
			if (other.iditemPedido != null)
				return false;
		} else if (!iditemPedido.equals(other.iditemPedido))
			return false;
		return true;
	}

}

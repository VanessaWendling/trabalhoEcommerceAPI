package br.org.serratec.backend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	@ApiModelProperty(value = "Identificador único do Pedido")
	private Long idPedido;

	@Column(name = "data_pedido")
	@ApiModelProperty(value = "Data do Pedido", required = true)
	private LocalDate dataPedido;

	@Column(name = "data_entrega")
	@ApiModelProperty(value = "Data de Entrega", required = true)
	private LocalDate dataEntrega;

	@Column(name = "data_envio")
	@ApiModelProperty(value = "Data de Envio", required = true)
	private LocalDate dataEnvio;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "Status do Pedido", required = true)
	private Status status;

	@JsonManagedReference
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	@ApiModelProperty(value = "Lista de Pedidos")
	private List<ItemPedido> itens;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@ApiModelProperty(value = "Identificador único do Cliente")
	private Cliente cliente;
	
	@Transient
	private Double total;

	public Pedido(Long idPedido, LocalDate dataPedido, LocalDate dataEntrega, LocalDate dataEnvio, Status status,
			List<ItemPedido> itens, Cliente cliente, Double total) {
		super();
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.dataEnvio = dataEnvio;
		this.status = status;
		this.itens = new ArrayList<ItemPedido>();
		this.cliente = cliente;
		this.total = new Double (0);
	}
	public Pedido() {
		 this.itens = new ArrayList<ItemPedido>();
	     this.total = new Double(0);
    }

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

//	public Double getTotal() {
//		this.setTotal();
//		return this.total;
//	}
//	
//	public void setTotal() {
//		for (ItemPedido itemPedido : itens) {
//			total += itemPedido.getSubTotal();
//		}
//	}

	
	
	public List<ItemPedido> getItens() {
		return itens;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
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
		Pedido other = (Pedido) obj;
		if (idPedido == null) {
			if (other.idPedido != null)
				return false;
		} else if (!idPedido.equals(other.idPedido))
			return false;
		return true;
	}

}
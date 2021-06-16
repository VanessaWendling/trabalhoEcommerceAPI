package br.org.serratec.backend.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.ItemPedido;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.model.Status;

public class PedidoInserirDTO {

	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private Status status;
	private Cliente cliente;
	private Produto produto;
	private List<ItemPedido> itens = new ArrayList<>();
	
	public PedidoInserirDTO() {
	}
	
	public PedidoInserirDTO(Pedido pedido) {
		super();
		this.dataPedido = pedido.getDataPedido();
		this.dataEntrega = pedido.getDataEntrega();
		this.dataEnvio = pedido.getDataEnvio();
		this.status = pedido.getStatus();
		this.itens = pedido.getItens();
		
	}
	

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
}

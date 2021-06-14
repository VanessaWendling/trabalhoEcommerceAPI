package br.org.serratec.backend.dto;

import java.util.List;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.model.Pedido;


public class ClienteMostrarDTO {
	private Long id;
    private String nomeCompleto;
    private Endereco enderecos;
    //private List<Pedido> pedidos;
    
    public ClienteMostrarDTO() {
		// TODO Auto-generated constructor stub
	}

	public ClienteMostrarDTO(Cliente cliente) {
		super();
		this.id = cliente.getIdCliente();
		this.nomeCompleto = cliente.getNomeCompleto();
		this.enderecos = cliente.getEnderecos();
		//this.pedidos = cliente.getPedidos();
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Endereco getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Endereco enderecos) {
		this.enderecos = enderecos;
	}
/*
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    
    
	
}

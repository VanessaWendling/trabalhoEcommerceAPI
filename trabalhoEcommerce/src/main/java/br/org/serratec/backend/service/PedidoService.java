package br.org.serratec.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;
import br.org.serratec.backend.dto.PedidoInserirDTO;
import br.org.serratec.backend.dto.PedidoMostrarDTO;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.ItemPedido;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.model.Status;
import br.org.serratec.backend.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private MailConfig mailConfig;
	
//	public List<PedidoMostrarDTO> listar() {
//		List<PedidoMostrarDTO> pedidoDTOs = new ArrayList<PedidoMostrarDTO>();
//		List<Pedido> pedidos = pedidoRepository.findAll();
//		for (Pedido pedido : pedidos) {
//			pedido.getTotal();
//			PedidoMostrarDTO dto = new PedidoMostrarDTO(pedido);
//			pedidoDTOs.add(dto);
//		}
//		return pedidoDTOs;
//	}
	
	
	public List<PedidoMostrarDTO> listar(){
        List<PedidoMostrarDTO> pedidoDTOs = new ArrayList<PedidoMostrarDTO>();
        List<Pedido> pedidos = pedidoRepository.findAll();
        for (Pedido pedido : pedidos) {
                pedido.getTotal();
                PedidoMostrarDTO dto = new PedidoMostrarDTO(pedido);
                pedidoDTOs.add(dto);
            }
        return pedidoDTOs;
    }

	public PedidoMostrarDTO buscar(Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (!pedido.isPresent()) {
			return null;
		} 
		return new PedidoMostrarDTO(pedido.get());
	}
	
	public PedidoMostrarDTO atualizar(Long id, PedidoInserirDTO pedidoInserirDTO) {
		if (!pedidoRepository.existsById(id)) {
			return null;
		}
		Pedido pedido = new Pedido();
		pedido.setIdPedido(id);
		pedido.getDataPedido();
		pedido.setDataEntrega(pedidoInserirDTO.getDataEntrega());
		pedido.setDataEnvio(pedidoInserirDTO.getDataEnvio());
		pedido.setStatus(Status.ENTREGUE);
		mailConfig.enviarEmail(pedidoInserirDTO.getCliente().getEmail(), "Pedido Enviado", pedidoInserirDTO.getItens().toString());
		pedido = pedidoRepository.save(pedido);
		
		return new PedidoMostrarDTO(pedido);
	}
	
	
	public Double calcularValorTotal(List<ItemPedido> itensPedido) {
        Double total = 0.00;

        for (ItemPedido itemPedido1 : itensPedido) {
            total += itemPedido1.getSubTotal();
        }
        return total;
    }
	
	public PedidoMostrarDTO inserir(PedidoInserirDTO pedidoInserirDTO) {
		Pedido pedido = new Pedido();
		Cliente cliente = new Cliente();
		Produto produto = new Produto();
		ItemPedido itemPedido = new ItemPedido();
		cliente.setIdCliente(pedidoInserirDTO.getCliente().getIdCliente());
		pedido.setCliente(pedidoInserirDTO.getCliente());
		pedido.setDataEntrega(pedidoInserirDTO.getDataEntrega());
		pedido.setDataEnvio(pedidoInserirDTO.getDataEnvio());
		pedido.setStatus(pedidoInserirDTO.getStatus());
		pedido.setDataPedido(LocalDate.now());
		produto.setId(pedidoInserirDTO.getProduto().getId());
		for (ItemPedido itemPedido2 : pedidoInserirDTO.getItens()) {
			itemPedido.setQuantidade(itemPedido2.getQuantidade());
			System.out.println(itemPedido2.getQuantidade());
			itemPedido.setPrecoVenda(itemPedido2.getPrecoVenda());
			System.out.println(itemPedido2.getQuantidade());
			itemPedido.getSubTotal();
		}
		pedido.setTotal(calcularValorTotal(pedidoInserirDTO.getItens()));
		System.out.println(pedido.getTotal());
		pedido = pedidoRepository.save(pedido);
		return new PedidoMostrarDTO(pedido);
	}
	
	public boolean deletar(Long id) {
		if (!pedidoRepository.existsById(id)) {
			return false;
		}
		pedidoRepository.deleteById(id);
		return true;
	}
	
}
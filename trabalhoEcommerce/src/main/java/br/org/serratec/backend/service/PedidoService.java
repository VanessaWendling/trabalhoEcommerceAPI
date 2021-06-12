package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.PedidoMostrarDTO;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<PedidoMostrarDTO> listar() {
		List<PedidoMostrarDTO> pedidoDTOs = new ArrayList<PedidoMostrarDTO>();
		List<Pedido> pedidos = pedidoRepository.findAll();
		for (Pedido pedido : pedidos) {
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
	
	public PedidoMostrarDTO atualizar(Long id, Pedido pedido) {
		if (!pedidoRepository.existsById(id)) {
			return null;
		}
		pedido.setIdPedido(id);
		pedido = pedidoRepository.save(pedido);
		return new PedidoMostrarDTO(pedido);
	}
	
	public PedidoMostrarDTO inserir(Pedido pedido) {
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
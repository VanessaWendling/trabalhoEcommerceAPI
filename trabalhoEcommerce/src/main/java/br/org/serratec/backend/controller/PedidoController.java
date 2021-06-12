package br.org.serratec.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.dto.PedidoMostrarDTO;
import br.org.serratec.backend.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<PedidoMostrarDTO>> listar() {
		List<PedidoMostrarDTO> pedidoDTOs = pedidoService.listar();
		return ResponseEntity.ok(pedidoDTOs);
	}
	/*
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoMostrarDTO inserir(@Valid @RequestBody PedidoInserirDTO pedidoInserir) {
		return pedidoService.inserir(pedidoInserir);
	}*/
}

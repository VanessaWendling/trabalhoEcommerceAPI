package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.dto.CategoriaInserirDTO;
import br.org.serratec.backend.dto.CategoriaMostrarDTO;
import br.org.serratec.backend.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired 
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<CategoriaMostrarDTO>> listar(){
		List<CategoriaMostrarDTO> categorias = categoriaService.listar();
		return ResponseEntity.ok(categorias);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CategoriaMostrarDTO inserir (@Valid @RequestBody CategoriaInserirDTO categoriaInserirDTO){
		CategoriaMostrarDTO dto = categoriaService.inserir(categoriaInserirDTO);
		return dto;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		if (!categoriaService.deletar(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	/*@GetMapping ("/{id}")
	public CategoriaMostrarDTO atualizar(@PathVariable Long id, CategoriaInserirDTO categoriaInserirDTO) {
		if (!categoriaService.atualizar(id, categoriaInserirDTO)) {
			
		}
	}*/
	
	@GetMapping ("/{id}")
	public ResponseEntity <CategoriaMostrarDTO> buscar (@PathVariable Long id){
		CategoriaMostrarDTO categoriaMostrarDTO = categoriaService.buscar(id);
		if (categoriaMostrarDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoriaMostrarDTO);
	}
	
	/*@GetMapping ("/{nome}")
	public ResponseEntity<CategoriaMostrarDTO> buscarPorNome (@PathVariable String nome){
		List<CategoriaMostrarDTO> categoriaMostrarDTO = categoriaService.buscarPorNome(nome);
		if (categoriaMostrarDTO == null) {
			return ResponseEntity.notFound().build();
		}

	}*/
	
	
}
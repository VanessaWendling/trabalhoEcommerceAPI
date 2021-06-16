package br.org.serratec.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.dto.CategoriaInserirDTO;
import br.org.serratec.backend.dto.CategoriaMostrarDTO;
import br.org.serratec.backend.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired 
	private CategoriaService categoriaService;
	
	@GetMapping
	@ApiOperation(value = "Listar Categorias", notes = "Listar Categorias")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categorias listadas com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<List<CategoriaMostrarDTO>> listar(){
		List<CategoriaMostrarDTO> categorias = categoriaService.listar();
		return ResponseEntity.ok(categorias);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar Categorias", notes = "Cadastro Categorias")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Categoria cadastrada com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public CategoriaMostrarDTO inserir (@Valid @RequestBody CategoriaInserirDTO categoriaInserirDTO){
		CategoriaMostrarDTO dto = categoriaService.inserir(categoriaInserirDTO);
		return dto;
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar determinada Categoria", notes = "Deletar Categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categoria deletada com sucesso"),
			@ApiResponse(code = 204, message = "Sem Conteúdo"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		if (!categoriaService.deletar(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping ("/porid/{id}")
	@ApiOperation(value = "Buscar categoria por ID", notes = "Buscar por ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categoria encontrada com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity <CategoriaMostrarDTO> buscar (@PathVariable Long id){
		CategoriaMostrarDTO categoriaMostrarDTO = categoriaService.buscar(id);
		if (categoriaMostrarDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoriaMostrarDTO);
	}
	
	@GetMapping ("/pornome/{nome}")
	@ApiOperation(value = "Buscar categoria por nome", notes = "Buscar por nome")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categoria encontrada com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<CategoriaMostrarDTO> buscarPorNome (@PathVariable String nome){
		CategoriaMostrarDTO dto = categoriaService.buscarPorNome(nome);
		if (dto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar determinado Categoria", notes = "Atualizar Categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categoria atualizado com sucesso"),
			@ApiResponse(code = 201, message = "Categoria criado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<CategoriaMostrarDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaInserirDTO categoriaInserirDTO) {
		CategoriaMostrarDTO categoriaMostrarDTO = categoriaService.atualizar(id, categoriaInserirDTO);
		if (categoriaMostrarDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoriaMostrarDTO);
	}
}
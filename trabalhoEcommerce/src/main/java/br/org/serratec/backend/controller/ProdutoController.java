package br.org.serratec.backend.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.backend.dto.ProdutoInserirDTO;
import br.org.serratec.backend.dto.ProdutoMostrarDTO;
import br.org.serratec.backend.model.Imagem;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.service.ImagemService;
import br.org.serratec.backend.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ImagemService imagemService;

	
	@PostMapping
    @ResponseStatus (HttpStatus.CREATED)
	public ProdutoMostrarDTO inserir(@RequestParam MultipartFile file, @RequestPart Produto produto) throws IOException{
        return produtoService.inserir(produto, file);

	}
	
	@GetMapping ("/{id}/imagem")
    public ResponseEntity<byte[]> buscarPorFoto (@PathVariable Long id){
        Imagem imagem = imagemService.buscar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", imagem.getTipo());
        headers.add("content-length", String.valueOf(imagem.getDados().length));
        return new ResponseEntity<>(imagem.getDados(), headers, HttpStatus.OK);
    }
    
    @GetMapping
	public ResponseEntity<List<ProdutoMostrarDTO>> listar() {
		List<ProdutoMostrarDTO> produtoDTOs = produtoService.listar();
		return ResponseEntity.ok(produtoDTOs);
	}
    
    
    @GetMapping("/{id}")
	public ResponseEntity<ProdutoMostrarDTO> buscarProduto(@PathVariable Long id) {
		ProdutoMostrarDTO produtoDTO =  produtoService.buscar(id);
		return ResponseEntity.ok(produtoDTO);
	}
    
    @DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar determinada Produto", notes = "Deletar Produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Produto deletada com sucesso"),
			@ApiResponse(code = 204, message = "Sem Conteúdo"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		if (!produtoService.deletar(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
    
    @PutMapping("/{id}")
	@ApiOperation(value = "Atualizar determinado Produto", notes = "Atualizar Produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Produto atualizado com sucesso"),
			@ApiResponse(code = 201, message = "Produto criado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<ProdutoMostrarDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoInserirDTO produtoInserirDTO) {
		ProdutoMostrarDTO produtoMostrarDTO = produtoService.atualizar(id, produtoInserirDTO);
		if (produtoMostrarDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtoMostrarDTO);
	}

}
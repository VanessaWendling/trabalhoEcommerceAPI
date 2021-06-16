package br.org.serratec.backend.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.dto.ProdutoInserirDTO;
import br.org.serratec.backend.dto.ProdutoMostrarDTO;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ImagemService imagemService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoMostrarDTO> listar() {
		List<ProdutoMostrarDTO> listaProdutosDTO = new ArrayList<ProdutoMostrarDTO>();
		List<Produto> listaProdutos = produtoRepository.findAll();
		for (Produto produto : listaProdutos) {
			listaProdutosDTO.add(adicionarFotoURL(produto));
		}
		return listaProdutosDTO;
	}

	
	public ProdutoMostrarDTO adicionarFotoURL(Produto produto) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/{id}/foto").buildAndExpand(produto.getId()).toUri();
		System.out.println("URI: " + uri);
		ProdutoMostrarDTO produtoDTO = new ProdutoMostrarDTO();
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setCategoria(produto.getCategoria());
		produtoDTO.setDataCadastro(produto.getDataCadastro());
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setValorUnitario(produto.getValorUnitario());
		produtoDTO.setUrl(uri.toString());
		return produtoDTO;
	}
	
	public ProdutoMostrarDTO buscar(Long id) {
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		return adicionarFotoURL(produtoOptional.get());
	}
	
	public ProdutoMostrarDTO inserir(Produto produto, MultipartFile file) throws IOException {
		imagemService.inserir(produtoRepository.save(produto), file);
		return adicionarFotoURL(produto);
	}


	public ProdutoMostrarDTO inserir (ProdutoInserirDTO produtoInserirDTO) {
		Produto produto = new Produto();
		produto.setNome(produtoInserirDTO.getNome());
		produto.setDescricao(produtoInserirDTO.getDescricao());
		produto = produtoRepository.save(produto);
		return new ProdutoMostrarDTO(produto);
	}
	
	public boolean deletar(Long id) {
		if (!produtoRepository.existsById(id)) {
			return false;
		}
		produtoRepository.deleteById(id);
		return true;
	}
	
	public ProdutoMostrarDTO atualizar(Long id, ProdutoInserirDTO produtoInserirDTO) {
		Produto produto = new Produto();
		if (!produtoRepository.existsById(id)) {
			return null;
		}
		produto.setId(id);
		produto.setCategoria(produtoInserirDTO.getCategoria());
		produto.setDataCadastro(produtoInserirDTO.getDataCadastro());
		produto.setDescricao(produtoInserirDTO.getDescricao());
		produto.setNome(produtoInserirDTO.getNome());
		produto.setValorUnitario(produtoInserirDTO.getValorUnitario());
		produto = produtoRepository.save(produto);
		return new ProdutoMostrarDTO(produto);
	}
}
	




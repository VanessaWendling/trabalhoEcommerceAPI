package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.CategoriaInserirDTO;
import br.org.serratec.backend.dto.CategoriaMostrarDTO;
import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	public List<CategoriaMostrarDTO> listar(){
		List<CategoriaMostrarDTO> categoriaDTOs = new ArrayList<CategoriaMostrarDTO>();
		List<Categoria> categorias = categoriaRepository.findAll();
		
		for(Categoria categoria: categorias) {
			CategoriaMostrarDTO dto = new CategoriaMostrarDTO(categoria);
			categoriaDTOs.add(dto);
		}
		return categoriaDTOs;
	}
	
	public CategoriaMostrarDTO inserir (CategoriaInserirDTO categoriaInserirDTO) {
		Categoria categoria = new Categoria();
		categoria.setNome(categoriaInserirDTO.getNome());
		categoria.setDescricao(categoriaInserirDTO.getDescricao());
		categoria = categoriaRepository.save(categoria);
		return new CategoriaMostrarDTO(categoria);
	}
	
	public boolean deletar(Long id) {
		if (!categoriaRepository.existsById(id)) {
			return false;
		}
		categoriaRepository.deleteById(id);
		return true;
	}
	public CategoriaMostrarDTO buscar (Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (!categoria.isPresent()) {
			return null;
		}
		return new CategoriaMostrarDTO(categoria.get());
	}
	
		public CategoriaMostrarDTO buscarPorNome (String nome) {
		Optional<Categoria> categoria = categoriaRepository.findByNome(nome);
		if (!categoria.isPresent()) {
			return null;
		}
		return new CategoriaMostrarDTO(categoria.get());
	}
		
		public CategoriaMostrarDTO atualizar(Long id, CategoriaInserirDTO categoriaInserirDTO) {
			Categoria categoria = new Categoria();
			if (!categoriaRepository.existsById(id)) {
				return null;
			}
			categoria.setId(id);
			categoria.setDescricao(categoriaInserirDTO.getDescricao());
			categoria.setNome(categoriaInserirDTO.getNome());
			categoria = categoriaRepository.save(categoria);
			return new CategoriaMostrarDTO(categoria);
		}
}

package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.backend.dto.EnderecoMostrarDTO;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository enderecoRepository;

	
	public List<EnderecoMostrarDTO> listar(){
		List<EnderecoMostrarDTO> enderecoDTOs = new ArrayList<EnderecoMostrarDTO>();
		List<Endereco> enderecos = enderecoRepository.findAll();
		
		for(Endereco endereco: enderecos) {
			EnderecoMostrarDTO dto = new EnderecoMostrarDTO(endereco);
			enderecoDTOs.add(dto);
		}
		return enderecoDTOs;
	}
	public EnderecoMostrarDTO buscar(String cep) {
        Optional<Endereco> enderecoOptional = Optional.ofNullable(enderecoRepository.findByCep(cep)); 
        if (enderecoOptional.isPresent()) { 
            return new EnderecoMostrarDTO(enderecoOptional.get());
        } else { 
            RestTemplate rs = new RestTemplate();
            String uri = "https://viacep.com.br/ws/" + cep + "/json/";
            Optional<EnderecoMostrarDTO> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, EnderecoMostrarDTO.class));
            if (enderecoViaCep.get().getCep() != null) {
                String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
                enderecoViaCep.get().setCep(cepSemTraco);
                return inserir(enderecoViaCep.get());
            } else { 
                return null;
            }
        }
    }

	public EnderecoMostrarDTO inserir(EnderecoMostrarDTO enderecoMostrarDTO) {
		Endereco endereco = new Endereco();
		endereco.setBairro(enderecoMostrarDTO.getBairro());
		endereco.setCep(enderecoMostrarDTO.getCep());
		endereco.setCidade(enderecoMostrarDTO.getLocalidade());
		endereco.setEstado(enderecoMostrarDTO.getUf());
		endereco.setRua(enderecoMostrarDTO.getLogradouro());
		endereco.setComplemento(enderecoMostrarDTO.getComplemento());
		enderecoRepository.save(endereco);
		return new EnderecoMostrarDTO(endereco);
	}
}

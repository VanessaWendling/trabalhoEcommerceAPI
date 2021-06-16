package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;
import br.org.serratec.backend.dto.ClienteInserirDTO;
import br.org.serratec.backend.dto.ClienteMostrarDTO;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MailConfig mailConfig;

	
	public List<ClienteMostrarDTO> listar() {
		List<ClienteMostrarDTO> clienteDTOs = new ArrayList<ClienteMostrarDTO>();
		List<Cliente> clientes = clienteRepository.findAll();
		for (Cliente cliente : clientes) {
			ClienteMostrarDTO dto = new ClienteMostrarDTO(cliente);
			clienteDTOs.add(dto);
		}
		return clienteDTOs;
	}

	public ClienteMostrarDTO inserir(ClienteInserirDTO clienteInserirDTO) throws EmailException {
		Cliente u = clienteRepository.findByEmail(clienteInserirDTO.getEmail()); 
		if (u != null) {
			throw new EmailException("Email já existente | Insira outro");
		}
		Cliente clienteSetar = new Cliente();
		Endereco endereco = new Endereco();
		clienteSetar.setEnderecos(clienteInserirDTO.getEndereco());
		clienteSetar.setNomeCompleto(clienteInserirDTO.getNomeCompleto());
		clienteSetar.setNomeUsuario(clienteInserirDTO.getNomeUsuario());
		clienteSetar.setEmail(clienteInserirDTO.getEmail());
		clienteSetar.setCpf(clienteInserirDTO.getCpf());
		clienteSetar.setSenha(bCryptPasswordEncoder.encode(clienteInserirDTO.getSenha()));
		clienteSetar.setDataNasc(clienteInserirDTO.getDataNasc());
		endereco.setCep(clienteInserirDTO.getEndereco().getCep());
		clienteSetar = clienteRepository.save(clienteSetar);
		mailConfig.enviarEmail(clienteInserirDTO.getEmail(), "Cadastro de Cliente", clienteSetar.toString());
		return new ClienteMostrarDTO(clienteSetar);
	}
	
	public boolean deletar(Long id) {
		if (!clienteRepository.existsById(id)) {
			return false;
		}
		clienteRepository.deleteById(id);
		return true;
	}
	
	public ClienteMostrarDTO atualizar(Long id, ClienteInserirDTO clienteInserirDTO) {
		Cliente cliente = new Cliente();
		if (!clienteRepository.existsById(id)) {
			return null;
		}
		cliente.setIdCliente(id);
		cliente.setCpf(clienteInserirDTO.getCpf());
		cliente.setNomeCompleto(clienteInserirDTO.getNomeCompleto());
		cliente.setNomeUsuario(clienteInserirDTO.getNomeUsuario());
		cliente.setEmail(clienteInserirDTO.getEmail());
		cliente.setSenha(clienteInserirDTO.getSenha());
		cliente.setTelefone(clienteInserirDTO.getTelefone());
		cliente.setEnderecos(clienteInserirDTO.getEndereco());
		cliente.setDataNasc(clienteInserirDTO.getDataNasc());
		cliente = clienteRepository.save(cliente);
		return new ClienteMostrarDTO(cliente);
	}
	
	public ClienteMostrarDTO buscar (Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent()) {
			return null;
		}
		return new ClienteMostrarDTO(cliente.get());
	}
	
		public ClienteMostrarDTO buscarPorNome (String nome) {
		Optional<Cliente> cliente = clienteRepository.findByNomeCompleto(nome);
		if (!cliente.isPresent()) {
			return null;
		}
		return new ClienteMostrarDTO(cliente.get());
	}
	
}

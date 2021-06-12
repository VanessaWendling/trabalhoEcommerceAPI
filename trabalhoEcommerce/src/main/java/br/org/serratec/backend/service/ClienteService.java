package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;
import br.org.serratec.backend.dto.ClienteMostrarDTO;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MailConfig mailConfig;
	
	public List<ClienteMostrarDTO> listar(){
		List<ClienteMostrarDTO> clienteDTOs = new ArrayList<ClienteMostrarDTO>();
		List<Cliente> clientes = clienteRepository.findAll();
		
		for(Cliente cliente: clientes) {
			ClienteMostrarDTO dto = new ClienteMostrarDTO(cliente);
			clienteDTOs.add(dto);
		}
		return clienteDTOs;
	}
	/*
	public Cliente buscar (Long id) {
		Cliente cliente = clienteRepository.getById(id);
		if(cliente != null)
		return clienteRepository.findById(id).get();
	else 
		throw new ("Cliente não existente");
	}*/
	
		public ClienteMostrarDTO inserir(Cliente cliente) throws EmailException{
	        Cliente u = clienteRepository.findByEmail(cliente.getEmail()); //irá fazer o Find para conferir se o email digitado já existe
	        if (u != null) {
	            throw new EmailException("Email já existente | Insira outro");
	        }
	        //setar as variáveis, a criptografia da senha e depois salvar os dados passados
	        Cliente clienteSetar = new Cliente();
	        clienteSetar.setNomeCompleto(cliente.getNomeCompleto());
	        clienteSetar.setNomeUsuario(cliente.getNomeUsuario());
	        clienteSetar.setEmail(cliente.getEmail());
	        clienteSetar.setCpf(cliente.getCpf());
	        clienteSetar.setSenha(bCryptPasswordEncoder.encode(cliente.getSenha()));
	        clienteSetar.setDataNasc(cliente.getDataNasc());
	        clienteSetar.setEnderecos(cliente.getEnderecos());
	        clienteSetar.setPedidos(cliente.getPedidos());
	        clienteSetar =  clienteRepository.save(clienteSetar);
	        mailConfig.enviarEmail(cliente.getEmail(), "Cadastro de Cliente", clienteSetar.toString());
	        return new ClienteMostrarDTO(clienteSetar);
	    }
		
		


	
}

package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;
import br.org.serratec.backend.dto.ItemPedidoDTO;
import br.org.serratec.backend.model.ItemPedido;
import br.org.serratec.backend.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    @Autowired
    private MailConfig config;
    
    public List<ItemPedidoDTO> listar(){
        List<ItemPedidoDTO> itemPedidoDTOs = new ArrayList<ItemPedidoDTO>();
        List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
        for (ItemPedido itemPedido : itemPedidos) {
            itemPedido.setSubTotal();
            ItemPedidoDTO dto = new ItemPedidoDTO(itemPedido);
            itemPedidoDTOs.add(dto);
        }
        return itemPedidoDTOs;
    }
    
    public Optional<ItemPedidoDTO> buscarItemPedidoId(Long id) {
        ItemPedidoDTO itemPedidoPedidoDTO = new ItemPedidoDTO();
        Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);

        itemPedidoPedidoDTO.setId(itemPedido.get().getIdItemPedido());
        itemPedidoPedidoDTO.setQuantidade(itemPedido.get().getQuantidade());
        itemPedidoPedidoDTO.setIdPedido(itemPedido.get().getPedido().getIdPedido());
        itemPedidoPedidoDTO.setIdProduto(itemPedido.get().getProduto().getId());
        itemPedidoPedidoDTO.setNomeProduto(itemPedido.get().getProduto().getNome());
        itemPedidoPedidoDTO.setPrecoVenda(itemPedido.get().getPrecoVenda());
        itemPedidoPedidoDTO.setSubTotal(itemPedido.get().getSubTotal());
        config.enviarEmail(itemPedido.get().getPedido().getCliente().getEmail(), "Informações do pedido", itemPedido.toString());
        return Optional.ofNullable(itemPedidoPedidoDTO);

    }
    
    public ItemPedido inserir(ItemPedido itemPedido) {
        itemPedido = itemPedidoRepository.save(itemPedido);
        return itemPedido; 
    }
    
    public boolean deletar(Long id) {
        if (!itemPedidoRepository.existsById(id)) {
            return false;
        } else {
            itemPedidoRepository.deleteById(id);
            return true;
        }
    }
    
}

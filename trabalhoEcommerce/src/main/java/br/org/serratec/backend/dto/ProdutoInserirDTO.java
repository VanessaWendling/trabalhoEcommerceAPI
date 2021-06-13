package br.org.serratec.backend.dto;

import java.time.LocalDate;

import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.model.Produto;

public class ProdutoInserirDTO {
    private String nome;
    private String descricao;
    private Integer qntEstoque;
    private LocalDate dataCadastro;
    private Double valorUnitario;
	private Categoria categoria;
	
	public ProdutoInserirDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProdutoInserirDTO(Produto produto) {
		super();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.qntEstoque = produto.getQntEstoque();
		this.dataCadastro = produto.getDataCadastro();
		this.valorUnitario = produto.getValorUnitario();
		this.categoria = produto.getCategoria();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQntEstoque() {
		return qntEstoque;
	}

	public void setQntEstoque(Integer qntEstoque) {
		this.qntEstoque = qntEstoque;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}

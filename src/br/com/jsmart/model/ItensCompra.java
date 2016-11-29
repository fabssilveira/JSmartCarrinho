package br.com.jsmart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class ItensCompra {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigoItensCompra;
	private int codigoCompra;
	private int codigoProduto;
	private int quantidade;
	
	public int getCodigoCompra() {
		return codigoCompra;
	}
	public void setCodigoCompra(int codigoCompra) {
		this.codigoCompra = codigoCompra;
	}
	public int getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getCodigoItensCompra() {
		return codigoItensCompra;
	}
	public void setCodigoItensCompra(int codigoItensCompra) {
		this.codigoItensCompra = codigoItensCompra;
	}
	
}

package br.com.jsmart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Pagamento {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigoPagamento;
	private String descricao;
	
	public int getCodigoPagamento() {
		return codigoPagamento;
	}
	public void setCodigoPagamento(int codigoPagamento) {
		this.codigoPagamento = codigoPagamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}

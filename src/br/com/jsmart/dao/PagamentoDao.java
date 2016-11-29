package br.com.jsmart.dao;

import javax.persistence.EntityManager;
import br.com.jsmart.model.Pagamento;

public class PagamentoDao {
	
	private EntityManager em;
	
	public Pagamento consulta(int codigoPagamento){
		return em.find(Pagamento.class, codigoPagamento);
	}

}

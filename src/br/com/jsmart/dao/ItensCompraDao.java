package br.com.jsmart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jsmart.model.ItensCompra;

public class ItensCompraDao {

	private EntityManager em;
	
	public ItensCompraDao(EntityManager em){
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItensCompra> buscaItens(int codigoCompra){
		
		Query qr = em.createQuery("select i from ItensCompra as i "+
          "where i.codigoCompra = :codigoCompra");
		qr.setParameter("codigoCompra", codigoCompra);
		
		List<ItensCompra> retorno = qr.getResultList();
		return retorno;
	}
	
	public void salvar(ItensCompra item){
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
	}
}

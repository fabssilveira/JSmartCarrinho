package br.com.jsmart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jsmart.model.Compra;

public class CompraDao{

	private EntityManager em;
	
	public CompraDao(EntityManager em){
		this.em = em;
	}
	
	public void salvar(Compra compra){
		em.getTransaction().begin();
		em.persist(compra);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Compra> buscaCompras(int codigoCliente){
		
		Query qr = em.createQuery("select c from Compra as c "+
          "where c.codigoCliente = :codigoCliente");
		qr.setParameter("codigoCliente", codigoCliente);
		
		List<Compra> retorno =  qr.getResultList();
		return retorno;
	}
	
	public Compra consulta(int codigoCompra){
		return em.find(Compra.class, codigoCompra);
	}
	
}

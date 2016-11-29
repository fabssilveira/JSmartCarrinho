package br.com.jsmart.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.jsmart.model.Produto;

public class ProdutoDao {

	private EntityManager em;
	
	public ProdutoDao(EntityManager em){
		this.em = em;
	}

	public Produto consulta(int codigoProduto) {
		return em.find(Produto.class, codigoProduto);
	}

	public Produto consultaCodigoBarras(String codigoBarras) {
		Query qr = em.createQuery("select p from Produto as p " + "where p.codigoBarras = :codigoBarras");
		qr.setParameter("codigoBarras", codigoBarras);
		try {
			Produto retorno = (Produto) qr.getSingleResult();
			return retorno;
		} catch (NoResultException e) {
			return null;
		}
	}

}

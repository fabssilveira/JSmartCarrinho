package br.com.jsmart.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.jsmart.model.Cliente;

public class ClienteDao {
	
	private EntityManager em;
	
	public ClienteDao(EntityManager em){
		this.em = em;
	}
	
	public void salvar(Cliente cliente){
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
	}
	
	public Cliente consultar(int codigo){
		return em.find(Cliente.class, codigo);
	}
	
	public Cliente buscaLogin(String userLogin){
		
		Query qr = em.createQuery("select c from Cliente as c "+
          "where c.userLogin = :userLogin");
		qr.setParameter("userLogin", userLogin);
		
		try{
			Cliente retorno = (Cliente) qr.getSingleResult();
			return retorno;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public Cliente buscaCpf(String cpf){
		
		Query qr = em.createQuery("select c from Cliente as c "+
		 "where c.cpf = :cpf");
		qr.setParameter("cpf", cpf);
				
		try{
			Cliente retorno = (Cliente) qr.getSingleResult();
			return retorno;
		}catch(NoResultException e){
			return null;
		}
	}
								
}

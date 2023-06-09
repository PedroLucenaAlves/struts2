package br.com.treinaweb.struts2.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinaweb.struts2.entidades.Pessoa;
import br.com.treinaweb.struts2.utils.JpaUtils;

public class PessoaService {

	public List<Pessoa> todas(){
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			pessoas = em.createQuery("from Pessoa", Pessoa.class).getResultList();
			return pessoas;
		} finally {
			if (em != null) { //Se o EntityManager for diferente de nulo, ele sera fechado
				em.close();
			}
		}
		
	}
	
	public Pessoa porId(Integer id) {
		Pessoa resultado = new Pessoa();
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			resultado = em.find(Pessoa.class, id);  //Pessoa.class (captura um objeto da classe pessoa) id = chave primaria
			return resultado;
			
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public void inserir(Pessoa p) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin(); //inicia a transacao
			em.persist(p); //persiste 
			em.getTransaction().commit(); //comita a transacao
			
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public List<Pessoa> pesquisarPessoaPorNome(String nome){
		EntityManager em = null;
		try {
			//por padrao, todo metodo que seja especifico para algo, "inserir, pesquisar, etc" deve utilizar uma query que aplique a funcao do metodo
			 em = JpaUtils.getEntityManager();
			 List<Pessoa> resultado = em //"p" foi um apelido que damos
					 .createQuery("from Pessoa p where lower(p.nome) like lower(concat('%', :nome, '%'))", Pessoa.class)
					 .setParameter("nome", nome).getResultList();
			 return resultado;
		} finally {
			if(em != null) {
				em.close();
			}
		}
	}
	
//	//public void remover(Pessoa p) {
//		try {
//			em = JpaUtils.getEntityManager();
//			
//		}
//		finally {
//			if
//		}
//		
//	}
	
}

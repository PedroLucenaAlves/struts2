package br.com.treinaweb.struts2.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe feita para criacao dos EntitiesManagers que sao necessarios para conseguir realizar a conexao com o banco atraves da JPA
 * 
 * @author pedro.lucena
 *
 */

public class JpaUtils {
	
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = null; //static para que toda vez que realizar uma conexao com o banco ele nao precise ler o persistence.xml 
	
	public static EntityManager getEntityManager() {
		if(ENTITY_MANAGER_FACTORY == null) {
			ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("tw-struts2-fundamentos"); //nome do nosso persistence-unit do arquivo persistence
		}
		return ENTITY_MANAGER_FACTORY.createEntityManager(); //Criacao do EntityManager
	}

}

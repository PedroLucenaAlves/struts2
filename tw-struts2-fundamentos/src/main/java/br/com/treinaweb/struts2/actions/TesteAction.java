package br.com.treinaweb.struts2.actions;

import com.opensymphony.xwork2.ActionSupport;
             
                          //extendeu a ActionSupport que é da xwork que faz o projeto ser action-based
public class TesteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7432816271355151265L; //serializa a classe
	
	
	@Override //SOBRESCRITA DO METODO DA ACTIONSUPPORT QUE EXECUTA A ACTION (ESTE É UM MÉTODO PADRÃO DE INICIALIZACAO DA ACTION NO STRUTS
	public String execute() {
	System.out.println("método execute");
		return "TESTE";
	}
	

}

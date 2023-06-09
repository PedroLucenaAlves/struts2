package br.com.treinaweb.struts2.controllers;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import br.com.treinaweb.struts2.entidades.Pessoa;
import br.com.treinaweb.struts2.servicos.PessoaService;

//extendeu a ActionSupport que é da xwork que faz o projeto ser action-based
public class PessoaController extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2325797793748458851L;

	private Pessoa pessoa;

	// BEANS PARA UTILIZAR NA PAGINA DE LISTAGEM
	private List<Pessoa> pessoas;
	private List<Pessoa> pessoasPorNome;
	private String mensagemErro; // utilizaremos apenas o get para os dois pois os valores nao serao alterados

	public String inserirPessoa() {
		System.out.println("método inserirPessoa");
		return "OK";
	}

	public String listar() {
		try {
			PessoaService ps = new PessoaService();
			this.pessoas = ps.todas(); // captura todas as pessoas para passar para a lista
			return SUCCESS; // SUCESS é uma constante de ActionSuport
		} catch (Exception e) {
			this.mensagemErro = e.getMessage(); // pega a mensagem do erro que for gerado e atribui a variavel
			return ERROR; // ERROR é uma constante de ActionSuport
		}
	}

	public String mostrar() {
		try {
			int id = Integer.parseInt(ActionContext.getContext().getParameters().get("id").getValue());
			PessoaService ps = new PessoaService();
			this.pessoa = ps.porId(id);
			return SUCCESS;
		} catch (Exception e) {
			this.mensagemErro = e.getMessage();
			return ERROR;
		}

	}

	public String inserir() {
		try {
			PessoaService ps = new PessoaService();
			ps.inserir(this.pessoa);
			return SUCCESS;

		} catch (Exception e) {
			this.mensagemErro = e.getMessage();
			return ERROR;
		}
	}
	
	public String pesquisarPorNome() {
		PessoaService ps = new PessoaService();
		String nome = ActionContext.getContext().getParameters().get("nome").getValue();
		this.pessoasPorNome = ps.pesquisarPessoaPorNome(nome); //NOME QUE É PESQUISADO E DA QUERY STRING
		return SUCCESS;
	}
	
	@Override // sobrescrita de um método da classe ActionSuport
	//metodo criado para que ao cadastrar uma pessoa, o nome e a idade atendam os requisitos passados abaixo
	public void validate() {
		if (this.pessoa != null) { //validacao realizada para verificar antes da filtragem se a pessoa é diferente de nulo para evitar NullPointerException
			if (this.pessoa.getNome() == "") { 
				addFieldError("pessoa.nome", "O nome é obrigatório! "); // pessoa.nome = o bean e o campo do bean que foi gerado o erro / a mensagem pode ser qualquer uma
			}
//			if (this.pessoa.getNome() =! " ") {
//				addFieldError("pessoa.nome", "Não podem ser incluidos símbolos ou números no nome!");
//			}
			if (this.pessoa.getNome().length() <= 3) {
				addFieldError("pessoa.nome", "O nome deve conter no mínimo 4 caractres ");
			}
			if (this.pessoa.getIdade() == null) {
				addFieldError("pessoa.idade", "A idade deve ser um número válido!");
			}

		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public List<Pessoa> getPessoasPorNome() {
		return pessoasPorNome;
	}

}

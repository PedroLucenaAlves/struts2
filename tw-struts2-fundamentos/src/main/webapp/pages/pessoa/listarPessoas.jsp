<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listagem de Pessoas</title>
</head>
<body>
	<h3>Listagem de pessoas</h3>
	
	<s:url action="paginaCadastroPessoa" var="paginaCadastroPessoaUrl"></s:url>
	<s:a href="%{paginaCadastroPessoaUrl}">Cadastrar nova pessoa</s:a>
	
	<p>
		<input type="text" id="txt-nome"/>
		<button id="btn-pesquisar">Pesquisar</button>
	</p>
	
	<table border="1" id="tbl-pessoas">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Idade</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<!-- corpo da tabela -->

			<s:iterator value="pessoas">
				<!-- tag iterator funciona como um foreach, foi criada para a tabela ir atualizando sozinha para cada pessoa que entrar nela -->
				<tr>
					<!-- Para cada pessoa que o iterator gerar, ele ira ler o nome e a idade (conforme tag abaixo) -->
					<td><s:property value="nome" /></td>
					<td><s:property value="idade" /></td>
					<td>	
						<!-- mostrarPessoa?id=1 (queryString) -->							<!-- var é a variavel feita que ira capturar a action -->
						<s:url action="mostrarPessoa" var="mostrarPessoaUrl">
							<s:param name="id"><s:property value="id"/></s:param>
						</s:url>
						<s:a href="%{mostrarPessoaUrl}">Mostrar</s:a> <!-- %{}captura a varivael var -->
					</td>
					
				</tr>
			</s:iterator>
		</tbody>
	</table>

	<!-- Verifica o valor do Bean -->
	<s:if test="mensagemErro != '' ">
		<!-- Se a mensagemErro (Bean) for diferente de uma String vazia -->
		<p>Mensagem de erro: </p>
		<p style="color: red;">
			<s:property value="mensagemErro" />
		</p>
	</s:if>
	
	<!-- CDN no Jquery capturada no site (minified) -->
	<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>

	<script> 
		$(document).ready(function() { //btn-pesquisar é o id "#" que atribuimos ao nosso botao para executar esta tarefa no JS
			$('#btn-pesquisar').click(function(){
				var nome = $('#txt-nome').val(); //pega o valor do que for digitado pelo usuario
				$.ajax({ 
					method: 'GET',
					url: 'pesquisarPorNome.action?nome=' + nome,
					success: function(dados){
						$('#tbl-pessoas > tbody tr').remove(); //seleciona a tabela pessoas, entra no tbody "indicado pela seta >" depois no tr e remove as linhas
						$.each(dados, function(idx, pessoa){  //forEach do AJAX idx=indice
							$('#tbl-pessoas > tbody').append(
									'<tr>' +
									'  <td>' + 	pessoa.nome + '</td>'
									'  <td>' + 	pessoa.idade + '</td>'
									'  <td></td>' //linha de link sera continuada em outro curso de struts pois sera integrado as tagsLibs no AJAX
									'</tr>'
							);
						});
					},
					error: function(){
						alert('Deu erro.');
					}; 
				});
			});
		});
	</script>

</body>
</html>
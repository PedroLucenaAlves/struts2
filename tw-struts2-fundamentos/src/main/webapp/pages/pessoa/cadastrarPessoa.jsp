<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro</title>
</head>
<body>

	<h3>Cadastro de pessoa</h3>
	
	<s:form action="inserirPessoa">
		<!-- tag de ligacao com o JavaBean -->
		<s:textfield label="Nome" name="pessoa.nome"></s:textfield> <!-- Campo de texto / por padrao o struts ira procurar o metodo getNome ja que pessoa.nome foi apontada no name-->
		<s:textfield label="Idade" name="pessoa.idade"></s:textfield> <!-- name e utilizado para realizar uma acao com outra linguagem ou passar o nome do input para o banco de dados -->
		<s:submit value="Salvar"></s:submit>
	</s:form>
	
	<s:if test="mensagemErro != '' ">
		<!-- Se a mensagemErro (Bean) for diferente de uma String vazia -->
		<p>Mensagem de erro: </p>
		<p style="color: red;">
			<s:property value="mensagemErro" />
		</p>
	</s:if>

</body>
</html>
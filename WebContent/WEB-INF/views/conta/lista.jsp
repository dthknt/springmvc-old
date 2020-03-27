<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listar Contas</title>

<script src="resources/js/jquery.js"></script>
<script type="text/javascript">

	function pagou() {
		alert("Pagou");
	}
	
	function pagarConta(id) {
		$.post("pagaConta", {'id' : id}, function() {
			  $("#conta_"+id).html("Paga");
		});
	}
	

</script>

</head>
<body>
	<table>
		<tr>
			<th>Código</th>
			<th>Descrição</th>
			<th>Valor</th>
			<th>Tipo</th>
			<th>Pago ?</th>
			<th>Data Pagamento</th>
			<th>Ação</th>
		</tr>
		
		<c:forEach items="${contas}" var="conta">
			<tr>
				<td>${conta.id}</td>
				<td>${conta.descricao}</td>
				<td>${conta.valor}</td>
				<td>${conta.tipo}</td>
				<td>
					<c:if test="${conta.paga}">
					Paga
					</c:if>
					<c:if test="${conta.paga eq false}">
					Não Paga
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${conta.dataPagamento.time}" pattern="dd/MM/yyyy" />
				</td>
				<td>
					<a href="exibirConta?id=${conta.id}">Alterar</a>
					<a href="removeConta?id=${conta.id}">Excluir</a>
					
					<span id="conta_${conta.id}">
					<c:if test="${conta.paga eq false}">
						<a href="#" onclick="pagarConta(${conta.id})">Pagar</a>
					</c:if>
					</span>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
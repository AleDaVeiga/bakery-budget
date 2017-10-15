<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Padaria - Controle das contas a pagar</title>
		<jsp:include page="../templates/header.jsp"/>
	</head>
    <body>
		<jsp:include page="../templates/menu.jsp"/>
		<div class="container">
	        <div class="row">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    <h3 class="panel-title clearfix">
	                        Administração de contas a pagar
	                        <span class="pull-right">
                               	<a href="${contextPath}/" class="btn btn-default btn-sm">
                               		<span class="glyphicon glyphicon-home"></span>
                               	</a>
                               	<a href="${contextPath}/bill-pay/" class="btn btn-success btn-sm">
                               		<span class="glyphicon glyphicon-plus"></span>
                               	</a>
	                        </span>
	                    </h3>
	                </div>
	                <div class="panel-body">
	                    <div class="row">
	                        <div class="col-md-12">
	                            <table class="table table-striped">
	                                <thead>
	                                <tr>
	                                    <th style="width: 10px">#</th>
	                                    <th>Filial</th>
	                                    <th>Nome</th>
	                                    <th>Data</th>
	                                    <th>Valor</th>
	                                    <th class="text-right">Ação</th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach var="bill" items="${billPay}" >
		                                    <tr>
		                                        <td>${bill.id}</td>
		                                        <td>${bill.business.name}</td>
		                                        <td>${bill.name}</td>
		                                        <td><fmt:formatDate value="${bill.dateLaunch}" pattern="dd/MM/yyyy" /></td>
		                                        <td class="text-danger">
		                                        	<fmt:formatNumber value="${bill.value}" type="currency"/>
		                                        </td>
		                                        <td class="text-right">
		                                        	<a href="${contextPath}/bill-pay/${bill.id}/deduct-bills" class="btn btn-success btn-sm">
		                                        		<span class="glyphicon glyphicon-download-alt"></span>
		                                        	</a>
		                                        	<a href="${contextPath}/bill-pay/${bill.id}" class="btn btn-warning btn-sm">
		                                        		<span class="glyphicon glyphicon-pencil"></span>
		                                        	</a>
		                                        	<form:form method="DELETE" action="${contextPath}/bill-pay/${bill.id}" style="display:inline">
									             		<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmRemove" data-title="Excluir conta a pagar" data-message="Deseja realmente excluir esta conta a pagar">
		                                        			<span class="glyphicon glyphicon-trash"></span>
		                                        		</button>
		                                        	</form:form>
		                                        </td>
		                                    </tr>
	                                	</c:forEach>
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <jsp:include page="../templates/footer.jsp"/>
	    <jsp:include page="../templates/confirm_remove.jsp"/>
	</body>
</html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Padaria - Controle das filiais</title>
		<jsp:include page="../templates/header.jsp"/>
	</head>
    <body>
		<jsp:include page="../templates/menu.jsp"/>
		<div class="container">
	        <div class="row">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    <h3 class="panel-title clearfix">
	                        Administração de filiais
	                        <span class="pull-right">
                               	<a href="${contextPath}/" class="btn btn-default btn-sm">
                               		<span class="glyphicon glyphicon-home"></span>
                               	</a>
                               	<a href="${contextPath}/business/" class="btn btn-success btn-sm">
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
	                                    <th>Nome</th>
	                                    <th>CNPJ</th>
	                                    <th class="text-right">Ação</th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach var="bus" items="${business}" >
		                                    <tr>
		                                        <td>${bus.id}</td>
		                                        <td>${bus.name}</td>
		                                        <td>${bus.document}</td>
		                                        <td class="text-right">
		                                        	<a href="${contextPath}/business/${bus.id}" class="btn btn-warning btn-sm">
		                                        		<span class="glyphicon glyphicon-pencil"></span>
		                                        	</a>
		                                        	<form:form method="DELETE" action="${contextPath}/business/${bus.id}" style="display:inline">
									             		<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmRemove" data-title="Excluir filial" data-message="Deseja realmente excluir esta filial?">
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
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Padaria - Controle das baixas das contas a pagar</title>
		<jsp:include page="../../templates/header.jsp"/>
	</head>
    <body>
		<jsp:include page="../../templates/menu.jsp"/>
		<div class="container">
	        <div class="row">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    <h3 class="panel-title clearfix">
	                        Administração de baixas
	                        <span class="pull-right">
                               	<a href="${contextPath}/bill-pay" class="btn btn-default btn-sm">
                               		<span class="glyphicon glyphicon-home"></span>
                               	</a>
                               	<a href="${contextPath}/bill-pay/${billPay.id}/deduct-bill/" class="btn btn-success btn-sm">
                               		<span class="glyphicon glyphicon-plus"></span>
                               	</a>
	                        </span>
	                    </h3>
	                </div>
	                <div class="panel-body">
	                	<ul class="list-group">
	                		<li class="list-group-item">
	                			<div class="row">
	                				<div class="col-xs-4">
	                					<p>Conta a pagar</p>
	                				</div>
	                				<div class="col-xs-8 text-right">
	                					<p>${billPay.id}</p>
	                				</div>
	                			</div>
	                		</li>
	                		<li class="list-group-item">
	                			<div class="row">
	                				<div class="col-xs-4">
	                					<p>Nome:</p>
	                				</div>
	                				<div class="col-xs-8 text-right">
	                					<p>${billPay.name}</p>
	                				</div>
	                			</div>
	                		</li>
	                		<li class="list-group-item">
	                			<div class="row">
	                				<div class="col-xs-4">
	                					<p>Data de lançamento:</p>
	                				</div>
	                				<div class="col-xs-8 text-right">
	                					<p><fmt:formatDate value="${billPay.dateLaunch}" pattern="dd/MM/yyyy" /></p>
	                				</div>
	                			</div>
	                		</li>
	                		<li class="list-group-item">
	                			<div class="row">
	                				<div class="col-xs-4">
	                					<p>Valor:</p>
	                				</div>
	                				<div class="col-xs-8 text-right">
	                					<p><fmt:formatNumber value="${billPay.value}" type="currency"/></p>
	                				</div>
	                			</div>
	                		</li>
	                	</ul>
	                    <div class="row">
	                        <div class="col-md-12">
	                            <table class="table table-striped">
	                                <thead>
	                                <tr>
	                                    <th style="width: 10px">#</th>
	                                    <th>Data</th>
	                                    <th>Valor</th>
	                                    <th class="text-right">Ação</th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach var="ded" items="${deductBill}" >
		                                    <tr>
		                                        <td>${ded.id}</td>
		                                        <td><fmt:formatDate value="${ded.datePayment}" pattern="dd/MM/yyyy" /></td>
		                                        <td class="text-success">
		                                        	<fmt:formatNumber value="${ded.value}" type="currency"/>
		                                        </td>
		                                        <td class="text-right">
		                                        	<a href="${contextPath}/bill-pay/${ded.billPay.id}/deduct-bill/${ded.id}" class="btn btn-warning btn-sm">
		                                        		<span class="glyphicon glyphicon-pencil"></span>
		                                        	</a>
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
	    <jsp:include page="../../templates/footer.jsp"/>
	</body>
</html>
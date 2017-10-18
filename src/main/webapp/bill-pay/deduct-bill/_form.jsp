<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:if test="${messageSuccess != null}">
	<div class="alert alert-success alert-dismissable">
		<a href="#" class="close" data-dismiss="alert" aria-label="Fecha">&times;</a>
		<strong>Sucesso!</strong> ${messageSuccess}
	</div>
</c:if>
<c:if test="${messageError != null}">
	<div class="alert alert-danger alert-dismissable">
		<a href="#" class="close" data-dismiss="alert" aria-label="Fecha">&times;</a>
		<strong>Erro!</strong> ${messageError}
	</div>
</c:if>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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
	<li class="list-group-item">
		<div class="row">
			<div class="col-xs-4">
				<p>Saldo:</p>
			</div>
			<div class="col-xs-8 text-right text-danger">
				<p><fmt:formatNumber value="${billPay.business.balance}" type="currency"/></p>
			</div>
		</div>
	</li>
</ul>
<spring:bind path="datePayment">
	<div class="form-group">
		<label class="control-label">Data de pagamento</label>
		<form:input type="date" path="datePayment" class="form-control"></form:input>
		<form:errors path="datePayment"></form:errors>
	</div>
</spring:bind>
<spring:bind path="value">
	<div class="form-group">
		<label class="control-label">Valor</label>
		<form:input path="value" class="form-control"></form:input>
		<form:errors path="value"></form:errors>
	</div>
</spring:bind>
<div class="text-right">
	<a href="${contextPath}/bill-pay" class="btn btn-default">
		<span class="glyphicon glyphicon-home"></span>
	</a>
	<a href="${contextPath}/bill-pay/${billPay.id}/deduct-bills" class="btn btn-default">
		<span class="glyphicon glyphicon-th-list"></span>
	</a>
	<button type="submit" class="btn btn-success">
		<span class="glyphicon glyphicon-floppy-disk"></span>
	</button>
</div>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
<spring:bind path="name">
	<div class="form-group">
		<label class="control-label">Nome</label>
		<form:input path="name" class="form-control" placeholder="Nome"></form:input>
		<form:errors path="name"></form:errors>
	</div>
</spring:bind>
<spring:bind path="document">
	<div class="form-group">
		<label class="control-label">CNPJ</label>
		<form:input path="document" class="form-control" placeholder="CNPJ"></form:input>
		<form:errors path="document"></form:errors>
	</div>
</spring:bind>
<div class="text-right">
	<a href="${contextPath}/" class="btn btn-default">
		<span class="glyphicon glyphicon-home"></span>
	</a>
	<a href="${contextPath}/business" class="btn btn-default">
		<span class="glyphicon glyphicon-th-list"></span>
	</a>
	<button type="submit" class="btn btn-success">
		<span class="glyphicon glyphicon-floppy-disk"></span>
	</button>
</div>
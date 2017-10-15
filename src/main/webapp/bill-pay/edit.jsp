<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
                            Editar conta a pagar
                        </h3>
                    </div>
                    <div class="panel-body">
                        <form:form method="PUT" modelAttribute="billPayForm" class="form-signin" action="${contextPath}/bill-pay/${billPayForm.id}">
                        	<jsp:include page="_form.jsp"/>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
	    <jsp:include page="../templates/footer.jsp"/>
	</body>
</html>
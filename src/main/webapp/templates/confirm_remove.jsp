<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- Model Dialog Remove -->
<!-- jQuery (necessary for Modal JavaScript) -->
<!-- Modal Dialog -->
<div class="modal fade" id="confirmRemove" role="dialog" aria-labelledby="confirmDeleteLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">Excluir permanentemente</h4>
			</div>
			<div class="modal-body">
				<p>Você está certo disso?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Não</button>
				<button type="button" class="btn btn-danger" id="confirm">Sim</button>
			</div>
		</div>
	</div>
</div>
<!-- Custom JS - (Remove) -->
<script src="${contextPath}/js/remove.js"></script>
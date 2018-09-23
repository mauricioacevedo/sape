<jsp:useBean id="destino" class="java.lang.String" scope="request"/>

<%-- se requiere ke desde afuera se configure una variable destino con el nombre de la plantilla a la cual se redireccionara al cliente cuando la operacion sea exitosa. --%>

	<form name="formaUp" id="formaUp" action="${pageContext.request.contextPath}/utils/UploadServlet" enctype="multipart/form-data" method="POST">
		Cargar Archivo <input name="file1" type="file" />&nbsp;&nbsp;<input class="boton" type="submit" value="procesar">
		 <input type="hidden" name="destino" value="${destino}">
		</form>

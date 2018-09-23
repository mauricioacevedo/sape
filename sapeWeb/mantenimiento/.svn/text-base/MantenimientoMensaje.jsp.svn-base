<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="mensaje" class="java.lang.String" scope="request" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- tipo es para diferenciar si es un mensaje en popup o en pantalla normal --%>
<jsp:useBean id="tipo" class="java.lang.String" scope="request" />

<!-- MatenimientoMensaje.jsp -->
<html>

<c:choose>
	<c:when test="${empty tipo or fn:contains(tipo, 'normal')}">
		<jsp:include page="../encabezado.jsp" flush="true" />
	</c:when>
</c:choose>


<body text=black bgcolor="white" topmargin="0" leftmargin="0" marginwidth="0" marginheight="0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<br>
<center><h1>Mensaje</h1></center>
<br>
<br>
<center>${mensaje}</center>
<br>
<center>
<c:choose>
	<c:when test="${empty tipo or fn:contains(tipo, 'normal')}"> 
	<input type="button" name="atras" onclick="javascript:window.history.back();" value="regresar" class="boton">
	</c:when>
	<c:otherwise>
	<input type="button" name="atras" onclick="javascript:window.close();" value="cerrar" class="boton">
	</c:otherwise>
</c:choose>
</center>
</body>
</html>

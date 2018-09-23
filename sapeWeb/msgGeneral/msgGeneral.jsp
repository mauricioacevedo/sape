					<!-- msgGeneral.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="msg" class="java.lang.String" scope="request" />
<jsp:useBean id="tipo" class="java.lang.String" scope="request" />
<jsp:useBean id="url" class="java.lang.String" scope="request" />
<html>
<head>
<script language="JavaScript" src="javascript/varios.js"> </script>

		<script language="JavaScript">


			function operacion(){

				var tipo = '$tipo';

				if(tipo = 'pop-upTelnetMantenimientoClose'){
					window.opener.location.href= "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoTipoNodo";
				}

			}

			function back(url) {
				location.href= url;
			}
		</script>
</head>
<body onunload="javascript:operacion();" bgcolor="white">
<c:if test="${empty tipo}">
	<jsp:include page="../encabezado.jsp" flush="true" />
</c:if>

<br>
<div align="center"><h1>Mensaje</h1></div>
<br>
<br>
<div align="center">${msg}</div>
<br>
<center>
<c:choose>
	<c:when test="${tipo == '' or tipo == 'normal'}">
		<input type="button" name="atras" onclick="javascript:back('${url}');" value="regresar">
	</c:when>
	<c:otherwise>
			<input type="button" name="atras" onclick="javascript:window.close();" value="cerrar">
	</c:otherwise>
</c:choose>
</center>
</body>
</html>

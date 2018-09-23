				<!-- matrizSiplexPRO.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="par" value="${requestScope.par}" />
<html>
<head>
<TITLE>SAPE - Editar Par ${param.par}</TITLE>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script languaje="JavaScript">
</script>
</head>
<body bgcolor="white">
<br>
<table align="center" border="0" width="100%">
<tr>
	<td align="center" class="mensajeCentral">EDITAR PAR</td>
</tr>
</table>
<br>
<form action="${pageContext.request.contextPath}/actionSape?accion=${param.accion}&operacion=guardarParMatriz" method="post">
<input type="hidden" name="par" value="${param.par}">
<table align="center" border="1" width="70%">
<tr align="center">
	<td width="50%">
		Par:
	</td>
	<td width="50%">
		${par.par}
	</td>
</tr>
<tr align="center" height="60">
	<td width="50%">
		Tel&eacute;fono:
	</td>	
	<td width="50%">
		<input type="text" size="10" maxlength="10" name="txtTelefono" id="txtTelefono" value="${par.telefono}" class="texto">
	</td>
</tr>
<tr>
	<td align="center">
		<input type="submit" Value="Aceptar" class="boton">
	</td>
	<td align="center">
		<input type="button" Value="Cancelar" onClick="window.close();" class="boton">
	</td>
</tr>
</table>
</form>
</body>
</html>

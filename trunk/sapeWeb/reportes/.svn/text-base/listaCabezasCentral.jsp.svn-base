		<!-- listaCabezasCentral.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% java.util.List listaCabezas = (java.util.List) request.getAttribute("listaCabezas"); %>
<html>
<head>
<title>SAPE - Lista Cabezas Telefono: ${param.telefono}</style>
<script language="JavaScript" src="javascript/varios.js"> </script>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white">
<br>
<table align="center" border="0" width="100%">
<tr>
	<td align="center" colspan="6" class="mensajeCentral">
	Lista Cabezas Tel&eacute;fono: ${param.telefono}.  Central: ${requestScope.central}
	</td>
</tr>

<tr>
	<!--td align="center" class="header-reporte">Site ID</td-->
	<td align="center" class="header-reporte">Site</td>
	<td align="center" class="header-reporte">IP Servidor</td>
	<td align="center" class="header-reporte">IP Cabeza</td>
	<td align="center" class="header-reporte">IP esclavo</td>
	<td align="center" class="header-reporte">Estado</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaCabezas}" var="tiponodo">

	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}">
		<!--td align="center" height="30">${tiponodo.id}</td-->
		<td align="center">${tiponodo.site}</td>
		<td align="center">${tiponodo.ipServidor} : ${tiponodo.puertoServidor}</td>
		<td align="center">${tiponodo.ipCabeza} : ${tiponodo.puertoCabeza}</td>
		<td align="center">${tiponodo.ipEsclavo} : ${tiponodo.puertoEsclavo}</td>
		<c:choose>
			<c:when test="${tiponodo.estado == 'F'}">
				<td align="center" class="fallo${row}" >Fuera de Servicio</td>
			</c:when>
			<c:when test="${tiponodo.estado == 'M'}">
				<td align="center" class="mantenimiento${row}" >Mantenimiento</td>
			</c:when>
			<c:when test="${tiponodo.estado == 'I'}">
				<td align="center" class="inter${row}" >Interactiva</td>
			</c:when>
			<c:otherwise>
				<td align="center">Operando</td>
			</c:otherwise>
		</c:choose>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>

</table>
<br>
<div align="center">
<input type="button" onclick="javascript:window.close();" value="cerrar" class="boton">
</div>
</body>
</html>

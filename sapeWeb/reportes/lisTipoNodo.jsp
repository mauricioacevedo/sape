	<!-- cprsTipoNodo.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<jsp:useBean id="tipoNodo" scope="request" class="com.osp.sape.maestros.TipoNodo" />
<% java.util.List listaLis = (java.util.List) request.getAttribute("listaLis"); %>


<html>
<head>
<title>SAPE - LI's Cabeza ${tipoNodo.site}</title>
<script language="JavaScript" src="javascript/varios.js"> </script>
</head>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0" bgcolor="white">
<br>

<table align="center" border="0" cols="1" width="100%">
<tr bgcolor="Black">
	<td align="center" colspan="3" class="mensajeCentral">LI's Cabeza ${tipoNodo.site}: ${fn:length(listaLis)}.</td>
</tr>
<tr bgcolor="#BDB5A5">
	<td align="center" class="header-reporte">LI</td>
	<td align="center" class="header-reporte">Central</td>
	<td align="center" class="header-reporte">Telefono</td>
</tr>
<c:forEach var="siplexProLI" items="${listaLis}">
<c:set var="row" value="${i%2 == 0? 0: 1}"/>
<tr class="row${row}">
	<td align="center">${siplexProLI.li}</td>
	<td align="center">${siplexProLI.central}</td>
	<td align="center">${siplexProLI.telefono}</td>
</tr>
<c:set var="i" value="${i +1}"/>
</c:forEach>
<tr bgcolor="Black">
	<td class="mensajeCentral" colspan="3">Fin del reporte</td>
</tr>
</table>
<br>

<center><input type="button" onclick="javascript:window.close();" value="cerrar" class="boton"></center>

</body>
</html>

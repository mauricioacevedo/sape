		<!-- programacionClientes.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="lista" class="java.util.ArrayList" scope="request"/>
<c:set var="listaHoras" value="${lista[0]}"/>
<html>
<title>SAPE - Programacion de Clientes</title>
<script language="JavaScript" src="javascript/common.js"> </script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="WHITE" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
<BR>
<BR>
<table width=50% align=CENTER>
<tr>
<td align=center class="mensajeCentral">
	Programaci&oacute;n de Clientes
</td>
</tr>
</table>
<br>
<br>
<table width=50% align=CENTER border=1>
	<tr class="header-reporte">
		<td width="40%" align="center">Hora</td>
		<td width="60%" align="center">Estado</td>
	</tr>
<c:forEach begin="0" end="23" var="i">
	<tr>
	<td width="40%">Hora ${i <= 9 ?"0":""}${i}</td>
	<td width="60%" align=center>
	<img src="imagenes_sape/${fn:contains(listaHoras[i],'1')? 'check.gif' : 'nocheck.gif'}" width="10%" border="0"></td>
	</tr>
</c:forEach>
</table>
<BR>
<table width=50% align=CENTER>
<TR><TD align="center">
	<input type="button" class="boton" value="Cerrar" onClick="window.close();">
</TD></TR>
</table>

</body>
</html>

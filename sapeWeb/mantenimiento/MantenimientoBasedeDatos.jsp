					<!-- MantenimientoBasedeDatos.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="fIni" value="${requestScope.fIni}" />
<c:set var="fFin" value="${requestScope.fFin}" />
<html>
<head>
<title>SAPE - Mantenimiento Base de Datos</title>
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">
<!--

function eliminarVisitas() {
	if (!confirm("Confirma Eliminar las Visitas en el rango de fechas seleccionado?")) {
		return false;
	}
	return validarCamposRangosFechas(document.frmVisitas.fechaIni, document.frmVisitas.fechaFin);
}

function eliminarRastros() {
	if (!confirm("Confirma Eliminar los rastros en el rango de fechas seleccionado?")) {
		return false;
	}
	return validarCamposRangosFechas(document.frmRastros.fechaIni, document.frmRastros.fechaFin);
}
//-->
</script>
</head>

<jsp:include page="../encabezado.jsp" flush="true" />
<body bgcolor="white">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<br><br>
<table align="center" width="80%">
<tr>
	<td align="center" class="mensajeCentral">Base de Datos</td>
</tr>
<tr height="40">
<td align="center">
<c:if test="${not empty requestScope.mensaje}">
	${requestScope.mensaje}
</c:if>
</td>
</tr>

 <tr>
	 <td align="center"> <a href="http://${pageContext.request.serverName}/Respaldo" target="_blank"><b>Respaldos Bases de Datos SAPE</b></a> </td>
 </tr>
</table>
<br><br>
<form name="frmVisitas" method="post" action="/sape/actionSape">
<input type="hidden" name="accion" value="mantenimiento">
<input type="hidden" name="operacion" value="eliminarVisitas">
<table align="center">
<tr>
	<td align="center">Borrar Visitas:</td>
	<td width="270" align="center">
	 	Desde Fecha: 
		<input type="text"class="texto" name="fechaIni" maxlength="10" size="10" value="${fIni}">
		(aaaa-mm-dd)
	</td>
	<td width="270" align="center">
		Hasta Fecha: 
		<input type="text" class="texto" name="fechaFin" maxlength="10" size="10" value="${fFin}">
		(aaaa-mm-dd)
	</td>
	<td><input class="boton" type="submit" onclick="return eliminarVisitas()" value="Aceptar"></td>
</tr>
</table>
</form>
<br><br>
<form name="frmRastros" method="post" action="/sape/actionSape">
<input type="hidden" name="accion" value="mantenimiento">
<input type="hidden" name="operacion" value="eliminarRastros">
<table align="center">
<tr>
	<td align="center">Borrar Rastros:</td>
	<td width="270" align="center">
	 	Desde Fecha: 
		<input type="text"class="texto" name="fechaIni" maxlength="10" size="10" value="${fIni}">
		(aaaa-mm-dd)
	</td>
	<td width="270" align="center">
		Hasta Fecha: 
		<input type="text" class="texto" name="fechaFin" maxlength="10" size="10" value="${fFin}">
		(aaaa-mm-dd)
	</td>
	<td><input class="boton" type="submit" onclick="return eliminarRastros()" value="Aceptar"></td>
</tr>
</table>
</form>

</form></body></html>

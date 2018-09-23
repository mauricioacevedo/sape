			<!-- detallesCentral.jsp -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="listaDetalles" value="${requestScope.listaDetallesCentral}" />
<c:set var="totalEventos" value="${requestScope.totalEventos}" />
<jsp:useBean id="query" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Detalles de la Central ${param.central}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
</head>
<script language="JavaScript">

function informeGen(format){

	var val = format.formatos.options[format.formatos.selectedIndex].value;

location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=exportarInforme&pantalla=detallesCentral&formato="+val+"${query}";

}



</script>
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
<br>
<p class="mensajeCentral" align="center">Detalles de la Cetral ${param.central}. ${fn:length(listaDetalles)} Registros</p>
<table width="520" align="center">

<tr>
<td align="right" colspan="6">
			<form name="informes">
			&nbsp;Exportar a: &nbsp;
			<select name="formatos" >
				<option value="pdf">PDF</option>
				<option value="csv">CSV</option>
				<option value="xls">XLS</option>
			</select>&nbsp;
			<input name="informe" class="boton" onClick="javascript:informeGen(document.informes);" type="button" value="Aceptar">
		</form>
</td>

</tr>

<tr class="header-reporte" height="30">
	<td align="center">Fecha</td>
	<td align="center"width="80">Central</td>
	<td align="center" width="150">Tipo Nodo</td>
	<td align="center">Codigo Ver</td>
	<td align="center">Cantidad</td>
	<td align="center">%</td>
</tr>
<c:set var="row" value="1" />
<c:forEach var="detalle" items="${listaDetalles}">
<c:set var="row" value="${(row+1)%2}" />
<tr class="row${row}">
	<td align="center">${detalle.fecha}</td>
	<td align="center">${detalle.central}</td>
	<td align="center">${detalle.site}</td>
	<td>${detalle.codigoVer}</td>
	<td align="center">${detalle.cantidad}</td>
	<td align="center">
		<fmt:formatNumber value="${detalle.cantidad/totalEventos*100}" pattern="##.##"/>
	</td>
</tr>
</c:forEach>
<tr class="fin-reporte">
	<td colspan="4">&nbsp;&nbsp;TOTALES</td>
	<td align="center">${totalEventos}</td>
	<td align="center">100%</td>
</tr>
</table>
<br>
<div align="center">
	<input type="button" class="boton" value="Cerrar" onClick="window.close();">
</div>
<br>
</body>
</html>

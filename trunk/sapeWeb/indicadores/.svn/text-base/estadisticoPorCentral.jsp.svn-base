					<!-- estadisticoPorCentral.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<c:set var="listaEstadosCentral" value="${requestScope.listaEstadosCentral}" />
<c:set var="totalExito" value="0" />
<c:set var="totalAdvertencia" value="0" />
<c:set var="totalFallo" value="0" />
<c:set var="totalInesperado" value="0" />
<c:set var="totalTotal" value="0" />
<html>
<head>
<title>SAPE - Estadisticas Por Central</title>
<script language="JavaScript" src="javascript/varios.js"></script>
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript">
addCalendar("DateIni", "calIni", "fechaIni", "frmBuscar");
addCalendar("DateFin", "calFin", "fechaFin", "frmBuscar");

function validarBuscar() {
	var forma = document.frmBuscar;
	if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
		return;
	}
	location.href="${pageContext.request.contextPath}/actionSape?accion=${param.accion}&fechaIni="+forma.fechaIni.value+"&fechaFin="+forma.fechaFin.value;
}

function estadisticoPorCodv(central) {
	if(central == '') return;
	location.href= "${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=estadisticaCodigosVer&central="+central+"&fIni=${fIni}&fFin=${fFin}";
}

function detallesCentral(central) {

	if(central == ''){
		return;
	}

	var url = "${pageContext.request.contextPath}/actionSape?accion=${param.accion}&operacion=detallesCentral&central=" + central + "&fechaIni=${fIni}&fechaFin=${fFin}";
	var vent = window.open(url, 'detallesCentral','scrollbars=yes,resizable=yes,hotkeys=no,height=555,width=750,left=100,top=50,menubar=yes,toolbar=no');
	vent.focus();
}

function grafica(datos){
	if(datos == ''){
		return;
	}

	var url = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=mostrarGraficaEfectividad&datos="+datos;
	var vent2 = window.open(url, 'GraficaCentrales','scrollbars=yes,resizable=yes,hotkeys=no,height=555,width=750,left=100,top=50,menubar=yes,toolbar=no');
	vent2.focus();
}

function informeGen(format){

	var forma = document.frmBuscar;
	var txtFechaIni = forma.fechaIni;
	if (txtFechaIni.value.length == 0){
		window.alert("Ingrese la fecha INICIAL");
		txtFechaIni.focus();
		return;
	}
	var txtFechaFin = forma.fechaFin;
	if (txtFechaFin.value.length = 0) {
		window.alert("Ingrese la fecha FINAL");
		txtFechaFin.focus();
		return;
	}

		var val = format.formatos.options[format.formatos.selectedIndex].value;

location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=exportarInforme&pantalla=estadisticoPorCentral&formato="+val+"&fechaIni="+txtFechaIni.value+"&fechaFin="+txtFechaFin.value;

}

</script>
</head>
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
<jsp:include page="../encabezado.jsp" flush="true" />

<form name="frmBuscar">
<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
	<tr>
		<td>&nbsp;</td>
		<td align="left" class="header-filtro" colspan="2">DESDE : </td>
		<td align="left" class="header-filtro">HASTA : </td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="left" colspan="2">
			<input type="text" class="texto" name="fechaIni" maxlength="10" size="10" value="${fIni}">
		<span class="calendario"><a href="javascript:showCal('DateIni', 5, 5)" title="Click Para Abrir El Calendario" class="calendario"> (aaaa-mm-dd)</a></span>
		<div id="calIni" style="position:relative; visibility: hidden;"></td>

		<td align=left>
			<input type="text" class="texto" name="fechaFin" maxlength="10" size="10" value="${fFin}">
			<span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateFin', 5, 5)">(aaaa-mm-dd)</a></span>
			<div id="calFin" style="position:relative; visibility: hidden;">
		</td>
	  <td>
			<input type="button" class="boton" name="buscar" onClick="validarBuscar();" value="Aceptar">
			<tags:ayudas pagina="Estacentral"/>		</td>
  </tr>
</table>
</form>

<p class="mensajeCentral" align="center">Estadisticas de Pruebas por Central. ${fn:length(listaEstadosCentral)} Centrales </p>
<table align="center" whidth="400">
<tr class="header-reporte" height="30">
	<td align="center" width="80">Central</td>
	<td align="center" width="80">Exito</td>
	<td align="center" width="80">Advertencia</td>
	<td align="center" width="80">Fallo</td>
	<td align="center" width="80">Inesperado</td>
	<td align="center" width="80">Efectividad</td>
	<td align="center" width="90">Total</td>
	<td align="center" width="90">Efectividad %</td>
</tr>
<c:set var="row" value="1" />
<c:forEach var="estado" items="${listaEstadosCentral}">
<c:set var="totalCentral" value="${estado.exito + estado.advertencia + estado.fallo + estado.inesperado}" />
<c:set var="totalExito" value="${totalExito + estado.exito}" />
<c:set var="totalAdvertencia" value="${totalAdvertencia + estado.advertencia}" />
<c:set var="totalFallo" value="${totalFallo + estado.fallo}" />
<c:set var="totalInesperado" value="${totalInesperado + estado.inesperado}" />
<c:set var="totalTotal" value="${totalTotal  + estado.total}" />
<c:set var="totalEfectividad" value="${totalEfectividad  + estado.exito + estado.advertencia}" />
<c:set var="row" value="${(row+1)%2}" />
<tr class="row${row}">
	<td align="center">
		<a title="Detalles por Codigo Ver" href="javascript:estadisticoPorCodv('${estado.central}');">
			${estado.central}
		</a>
	</td>
	<td align="center">${estado.exito}</td>
	<td align="center">${estado.advertencia}</td>
	<td align="center">${estado.fallo}</td>
	<td align="center">${estado.inesperado}</td>
	<td align="center">${estado.exito + estado.advertencia}</td>

	<td align="center">
		<a title="Detalles por Tipo de Nodo" href="javascript:detallesCentral('${estado.central}');">
			${estado.total}
		</a>
	</td>
	<td align="center">
		<c:set var="data" value="Central ${estado.central}, ${estado.total} registros.*Concepto*Valor*Exito*${estado.exito}*Advertencia*${estado.advertencia}*Fallo*${estado.fallo}*Inesperados*${estado.inesperado}" />
		<a title="Grafica de la Central ${estado.central}" href="javascript:grafica('${data}');">
		<fmt:formatNumber value="${(estado.exito + estado.advertencia)/estado.total*100}" pattern="##.##"/>
		</a>
	</td>
</tr>
</c:forEach>
<tr class="fin-reporte">
	<td align="center">TOTALES</td>
	<td align="center">${totalExito}</td>
	<td align="center">${totalAdvertencia}</td>
	<td align="center">${totalFallo}</td>
	<td align="center">${totalInesperado}</td
	><td align="center">${totalEfectividad}</td>
	<td align="center">${totalTotal}</td>
	<td align="center">
		<fmt:formatNumber value="${(totalExito + totalAdvertencia)/totalTotal*100}" pattern="##.##"/>
	</td>
</tr>
</table>
<br>
<table width="400" align="center">
<tr class="graficaLink">
<c:set var="data" value=" *Concepto*Valor*Exito*${totalExito}*Advertencia*${totalAdvertencia}*Fallo*${totalFallo}*Inesperados*${totalInesperado}" />
	<td align="center" valign="top"><a href="javascript:grafica('TOTALES, ${totalTotal} registros. Efectividad <fmt:formatNumber value="${(totalExito + totalAdvertencia)/totalTotal*100}" pattern="##.##"/>${data}');">Grafica Totales</a></td>
	<td align="center" valign="bottom">

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
</table>
</body>
</html>

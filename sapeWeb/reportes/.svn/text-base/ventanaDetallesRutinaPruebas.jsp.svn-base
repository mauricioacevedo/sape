			<!-- ventanaDetallesRutinaPruebas.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="listaDetalles" value="${requestScope.listaDetalles}" />
<jsp:useBean id="idPrueba" class="java.lang.String" scope="request"/>
<jsp:useBean id="tipo" class="java.lang.String" scope="request"/>
<jsp:useBean id="codv" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Detalle Prueba Programada</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<style type="text/css">
 .malo0 {background-color: #FF3300;}
 .malo1 {background-color: #FF3300;}
 .bueno0 {background-color: #44a041;}
 .bueno1 {background-color: #44a041;}
 .regular0 {background-color: #FFCC00;}
 .regular1 {background-color: #FFCC00;}
</style>
<script language="JavaScript">
<!--//
function Abre_ventanaDetallePrueba(idprueba) {
	this.window.focus();
	v601=window.open("${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono&operacion=mostrarResultados&ticket="+idprueba, 'window601','scrollbars=no,resizable=yes,hotkeys=no,height=600,width=650,left=0,top=0,menubar=no,toolbar=no');
	v601.focus();
}

function informeGen(format){
	
	var val = format.formatos.options[format.formatos.selectedIndex].value;	
	document.location = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=abrirVentanaDetalles&exportar=yes&ticket=${idPrueba}&formato="+val+"&tipo=${tipo}&codv=${codv}";
}


function irAPagina(pagina, cantidadRegistros, order){

	if(isNaN(cantidadRegistros)){
		alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
		document.formaPaginacion.regXPag.focus();
		return;
	}

	var query = '${query}';

	location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=estadisticoPruebasProgramadas&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;

}


//-->
</script>
</head>
<body>
<br>

<br>


<table align="center" width="100%">

<tr>
<td colspan="9" width="100%">
<c:if test="${tipo == 'detalles'}">
<a href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=estadisticoPruebasProgramadas&prueba=${idPrueba}">Ver Estadistico</a>
</c:if>
<jsp:include page="paginacion.jsp" flush="true" />
</td>
</tr>
<tr bgcolor="BLACK">
	<td align="center" width="100" class="header-reporte">Prueba</td>
	<td align="center" width="90" class="header-reporte">Telefono</td>
	<td align="center" width="250" class="header-reporte">CAP(A-B, A-T, B-T)</td>
	<td align="center" width="250" class="header-reporte">RES(A-B, A-T, B-T)</td>
	<td align="center" class="header-reporte">Codigo Ver</td>
	<%--td align="center" class="header-reporte" title="Clasificacion del Par">Clasif.</td--%>
	<td align="center" class="header-reporte" title="Velocidad Up Stream">VelUp</td>
	<td align="center" class="header-reporte" title="Velocidad Down Stream">velDown</td>
	<td align="center" class="header-reporte" title="Bueno Para Datos">BPD</td>	
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaDetalles}" var="detalle">
<c:set var="row" value="${i%2 == 0? 0 : 1}" />
<tr class="row${row}">

	<c:choose>
		<c:when test="${detalle.transaccion_spp == '0'}">
			<td align="center">-</td>
		</c:when>
		<c:otherwise>
			<td align="center"><a name="Idprueba" href="javascript:Abre_ventanaDetallePrueba('${detalle.transaccion_spp}');" title="Consultar Detalle de Prueba Electrica">${detalle.transaccion_spp}</a></td>
		</c:otherwise>
	</c:choose>

	<td align="center">${detalle.telefono}</td>

	<td align="center">${detalle.capacitancias}</td>
	<td align="center">${detalle.resistencias}</td>

		<!-- le quito el C.xxx.SIPLEXPRO -->
	<c:set var="codv" value="${detalle.codigover}" />
	<c:if test="${fn:startsWith(codv, 'C.')}"><c:set var="codv" value="${fn:split(codv, '.')[1]}" /></c:if>
	<td align="center">${codv}</td>

	<c:choose>
	<c:when test="${detalle.calificacion == 'B'}">
		<c:set var="claseCalificacion" value="bueno${row}" />
	</c:when>
	<c:when test="${detalle.calificacion == 'N'}">
		<c:set var="claseCalificacion" />
	</c:when>
	<c:otherwise>
		<c:set var="claseCalificacion" value="malo${row}" />
	</c:otherwise>
	</c:choose>
	<%--td align="center" class="${claseCalificacion}">${detalle.calificacion}</td--%>
	<td align="center">${detalle.velocidadUP}</td>
	<td align="center">${detalle.velocidadDown}</td>
	<c:choose>
	<c:when test="${detalle.calificacionDatos == 'B'}">
		<c:set var="claseCalificacion" value="bueno${row}" /> 	
	</c:when>
	<c:when test="${detalle.calificacionDatos == 'N'}">
		<c:set var="claseCalificacion" />
	</c:when>
		<c:when test="${detalle.calificacionDatos == 'R'}">
		<c:set var="claseCalificacion" value="regular${row}"/>
	</c:when>

		<c:when test="${detalle.calificacionDatos == 'M'}">
		<c:set var="claseCalificacion" value="malo${row}"/>
	</c:when>
	<c:otherwise>
		<c:set var="claseCalificacion" />
	</c:otherwise>
	
	
	</c:choose>
	<td align="center" class="${claseCalificacion}">${detalle.calificacionDatos}</td>	
</tr>
<c:set var="i" value="${i + 1}" />
</c:forEach>
<tr bgcolor="BLACK">
<td class="fin-reporte" colspan="9">Total ${fn:length(listaDetalles)} Registros.</td>
</tr>
</table>

 <br>
 
 <table align="center">
<tr>
	<td align="left" class="graficaLink">
		<%--c:if test="${tipo == 'detalles'}">
			<a href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=estadisticoPruebasProgramadas&prueba=${idPrueba}">Ver Estadistico</a>
		</c:if--%>
	</td>
	<td align="right" colspan ="9">
		<form name="informes">
			<font color="Black">Exportar a: &nbsp;</font>
			<select name="formatos">
				<option value="pdf">PDF</option>
				<option value="csv">CSV</option>
				<option value="xls">XLS</option>
			</select>
			<input name='informe' class="boton" onClick='javascript:informeGen(document.informes);' type='button' value='Aceptar'>
				<c:if test="${empty tipo or tipo == 'patallaPorCodigos'}">
				&nbsp;&nbsp;<input type="button" class="boton" onClick="window.close();" value="Cerrar">
				</c:if>
		</form>
	</td>
</tr>
</table>
 
 <br>
	<center>

	</center>
</body>
</html>

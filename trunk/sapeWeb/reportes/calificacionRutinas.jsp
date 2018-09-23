				<!-- calificacionRutinas.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="listaDetalles" value="${requestScope.listaDetalles}" />
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<jsp:useBean id="valorFiltro" class="java.lang.String" scope="request"/>
<jsp:useBean id="filtro" class="java.lang.String" scope="request"/>
<jsp:useBean id="pagActual" class="java.lang.String" scope="request"/>
<jsp:useBean id="orderBy" class="java.lang.String" scope="request"/>
<jsp:useBean id="cantidadTotalRegistros" class="java.lang.String" scope="request"/>
<jsp:useBean id="query" class="java.lang.String" scope="request"/>
<jsp:useBean id="msgCentral" class="java.lang.String" scope="request"/>

<html>
<head>
<title>SAPE - Calificacion de Rutinas</title>

<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="javascript" src="javascript/tooltip/domLib.js"></script>
<script language="javascript" src="javascript/tooltip/domTT.js"></script>
<script language="JavaScript">
<!--
addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");


	function verif_entradas(forma , order, regXpag, pagActual) {

		if(isNaN(regXpag)){
			alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
			document.formaPaginacion.regXPag.focus();
			return;
		}

		var oneChar;
		var op = forma.opciones.options[forma.opciones.selectedIndex].value

		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}

		direcciona(op,forma.fechaIni.value,forma.fechaFin.value,order,regXpag,'');
		return;
	}


	function direcciona(opcion,fechaIni,fechaFin,orderBy,regXpag,pagActual) {
		location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=calificacionRutinas&filtro="+opcion+"&fIni="+fechaIni+"&fFin="+fechaFin+"&orderBy="+orderBy+"&regPorPagina="+regXpag+"&pagActual="+pagActual;
		return;
	}




function Regresar() {
  if (navigator.appName == "Netscape")
    window.back();
  else
    window.history.back();
}



function irAPagina(pagina, cantidadRegistros, order){

	if(isNaN(cantidadRegistros)){
		alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
		document.formaPaginacion.regXPag.focus();
		return;
	}

	var query = '${query}';
	location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=calificacionRutinas&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;

}

	function informeGen(format,pagina, cantidadRegistros, order){
	
		var val = format.formatos.options[format.formatos.selectedIndex].value;
		var query = '${query}';
		document.location = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=exportarInforme&pantalla=consultaPruebas&formato="+val+"&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;
	}

	var domTT_classPrefix = 'domTTOverlib';
 
	function toolTip(padreEvento, evento,codv,desc) {
		var titulo = "";
	    var contenido = " Codigo Ver: "+codv+"<br> Descripcion: "+desc;
	    return mostrarDivAyuda(padreEvento, evento,'',contenido);
	}
 

	function estadistico(id){
		document.location = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=estadisticoPruebasProgramadas&prueba="+id;
	}
	
	function reporteMeses(){

		var forma = document.forma1;
		
		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}
	
		var fIni=forma.fechaIni.value;
		var fFin=forma.fechaFin.value;
	
		var calificacion=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=mensualCalificacionRutinas&fIni="+fIni+"&fFin="+fFin, 'window700','scrollbars=yes,resizable=yes,hotkeys=yes,height=350,width=600,left=0,top=0,menubar=yes,toolbar=no');
		calificacion.focus();
	}

//-->
</script>
<style type="text/css">
 .malo0 {background-color: #FF3300;}
 .malo1 {background-color: #FF3300;}
 .bueno0 {background-color: #44a041;}
 .bueno1 {background-color: #44a041;}
 .regular0 {background-color: #FFCC00;}
 .regular1 {background-color: #FFCC00;}
</style>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<!-- link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/javascript/tooltip/example.css"-->
<style>@import url(javascript/tooltip/example.css);</style>
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<jsp:include page="../encabezado.jsp" flush="true" />


<!-- INICIO DE LA FORMA DE BUSQUEDA -->

<form name="forma1">
<table width="100%" cellspacing=0 cellpadding=0 border=0 align="center">
	<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td  class="header-filtro" align="left">Buscar por:</td>
		<td align="left" class="header-filtro">DESDE : </td>
		<td align="left" class="header-filtro">HASTA : </td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td  align="left">

		<select name="opciones">
			<option value="todas" ${filtro == ''?'selected':''}>Todos</option> 
			<option value="buenos" ${filtro == 'buenos'?'selected':''}>Pares BPD</option>
			<option value="malos" ${filtro == 'malos'?'selected':''}>Pares No BPD</option>
			<option value="regulares" ${filtro == 'regulares'?'selected':''}>Pares RPD</option>
			
		</select>&nbsp;&nbsp;&nbsp;
		<!-- input class="texto" type="text" name="valor" value="${valorFiltro}" size="10" maxlength="40"></td-->

		<td align="left">
			<input type="text"class="texto" name="fechaIni" maxlength="10" size="10" value="${fIni}">
		<span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateIni', 5, 5)"> (aaaa-mm-dd)</a></span>
		<div id="calIni" style="position:relative; visibility: hidden;"></td>

		<td align=left>
			<input type="text" class="texto" name="fechaFin" maxlength="10" size="10" value="${fFin}">
			<span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateFin', 5, 5)">(aaaa-mm-dd)</a></span>
			<div id="calFin" style="position:relative; visibility: hidden;">
		</td>
		<td>
			<input type="hidden" value="realizarConsultaPrueba" name="operacion">
			<input type="button" class="boton" name="buscar" onclick="verif_entradas(document.forma1,'${orderBy}',document.formaPaginacion.regXPag.value,'${pagActual}')" value="Aceptar">
		</td>
  </tr>
</table>
</form>

<!-- INICIO DEL CONTENIDO DEL REPORTE -->


<P></P>

<table width="100%" border="0" BGCOLOR="black" align="center">
<tr>
	<td align="center" class="mensajeCentral">Calificacion de Pruebas por Rutinas ${msgCentral} <%--tags:ayudas pagina="Consultaprueba"/--%> </td>
</tr>
</table>


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
	<c:otherwise>
		<c:set var="claseCalificacion" value="malo${row}" />
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

<center><a href="javascript:reporteMeses();">Ver estadistica mensual</a></center>

</body>
</html>

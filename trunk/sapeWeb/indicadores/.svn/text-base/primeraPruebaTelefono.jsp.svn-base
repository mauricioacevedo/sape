					<!-- primeraPruebaTelefono.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="listaPrimero" value="${requestScope.listaPrimero}" />
<c:set var="fechaInicial" value="${requestScope.fechaInicial}" />
<c:set var="fechaFinal" value="${requestScope.fechaFinal}" />
<c:set var="orderBy" value="${requestScope.orderBy}"/>
<c:set var="pagActual" value="${requestScope.pagActual}"/>
<c:set var="filtro" value="${requestScope.filtro}"/>
<c:set var="valorFiltro" value="${requestScope.valorFiltro}"/>
<jsp:useBean id="query" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Primera Prueba de Tel&eacute;fonos</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">
	addCalendar("DateIni", "calIni", "fechaIni", "frmBuscar");
	addCalendar("DateFin", "calFin", "fechaFin", "frmBuscar");

	function validarBuscar() {
		var forma = document.frmBuscar;
		var txtFechaIni = forma.fechaIni;
		var txtFechaFin = forma.fechaFin;
		var cboFiltro = forma.filtro;
		var txtValorFiltro = forma.valorFiltro;
		if (cboFiltro.options[cboFiltro.selectedIndex].value != "" && txtValorFiltro.value == "") {
			window.alert("Ingrese el dato a buscar");
			txtValorFiltro.focus();
			return;
		}

		if (!validarCamposRangosFechas(txtFechaIni, txtFechaFin)) {
			return;
		}
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=${param.accion}&fechaIni="+txtFechaIni.value+"&fechaFin="+txtFechaFin.value+"&filtro=" + cboFiltro[cboFiltro.selectedIndex].value + "&valorFiltro=" + txtValorFiltro.value;
	}


	function detallePruebas(telefono) {
		var url = "${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=detallePruebas&fechaIni=${fechaInicial}&fechaFin=${fechaFinal}&opcion=telefono&valor="+telefono;
		var vent = window.open(url, 'detallesTelefono','scrollbars=yes,resizable=yes,hotkeys=yes,height=555,width=750,left=100,top=50,menubar=yes,toolbar=no');
		vent.focus();
	}

	function irAPagina(pagina, cantidadRegistros, order){
		if(isNaN(cantidadRegistros)) {
			alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
			document.formaPaginacion.regXPag.focus();
			return;
		}
		var query = '${query}';
		location.href="${pageContext.request.contextPath}/actionSape?accion=${param.accion}&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;
	}


	function mostrarGrafica(){

		var forma = document.frmBuscar;
		var txtFechaIni = forma.fechaIni;
		var txtFechaFin = forma.fechaFin;

		if (txtFechaIni.value.length == 0){
			window.alert("Ingrese la fecha INICIAL");
			txtFechaIni.focus();
			return;
		}

		if (txtFechaFin.value.length = 0) {
			window.alert("Ingrese la fecha FINAL");
			txtFechaFin.focus();
			return;
		}



		var url = "${pageContext.request.contextPath}/actionSape?accion=${param.accion}&operacion=graficaPrimeraPrueba&fechaIni="+txtFechaIni.value+"&fechaFin="+txtFechaFin.value;
		v900=window.open(url, 'window900', 'scrollbars=yes,resizable=yes,hotkeys=yes,height=600,width=800,left=100,top=50,menubar=yes,toolbar=no');
		v900.focus();
	}


function informeGen(format){

		var forma = document.frmBuscar;
		var txtFechaIni = forma.fechaIni;
		var txtFechaFin = forma.fechaFin;
		var cboFiltro = forma.filtro;
		var txtValorFiltro = forma.valorFiltro;
		if (cboFiltro.options[cboFiltro.selectedIndex].value != "" && txtValorFiltro.value == "") {
			window.alert("Ingrese el dato a buscar");
			txtValorFiltro.focus();
			return;
		}
		if (txtFechaIni.value.length == 0){
			window.alert("Ingrese la fecha INICIAL");
			txtFechaIni.focus();
			return;
		}

		if (txtFechaFin.value.length = 0) {
			window.alert("Ingrese la fecha FINAL");
			txtFechaFin.focus();
			return;
		}

		var val = format.formatos.options[format.formatos.selectedIndex].value;

location.href="${pageContext.request.contextPath}/actionSape?accion=${param.accion}&fechaIni="+txtFechaIni.value+"&fechaFin="+txtFechaFin.value+"&filtro=" + cboFiltro[cboFiltro.selectedIndex].value + "&valorFiltro=" + txtValorFiltro.value+"&exportar=yes&pantalla=primeraPruebaTelefono&formato="+val;


//location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=exportarInforme&pantalla=estadisticoPorCentral&formato="+val+"&fechaIni="+txtFechaIni.value+"&fechaFin="+txtFechaFin.value;

}


</script>

</head>
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
<jsp:include page="../encabezado.jsp" flush="true" />

<form name="frmBuscar">
<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
	<tr>
		<td>&nbsp;</td>
		<td align="left" class="header-filtro">Buscar Por:</td>
		<td align="left" class="header-filtro">Desde : </td>
		<td align="left" class="header-filtro">Hasta : </td>
		<td></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="left" class="header-filtro">
			<select name="filtro">
				<option value="">Ninguno</option>
				<option value="telefono"${filtro == 'telefono'?' selected':''}>Telefono</option>
				<option value="cantidad"${filtro == 'cantidad'?' selected':''}>Cantidad</option>
				<option value="tipoprueba"${filtro == 'tipoprueba'?' selected':''}>Tipo Prueba</option>
				<option value="codv"${filtro == 'codv'?' selected':''}>Codigo Ver</option>
				<option value="site"${filtro == 'site'?' selected':''}>Tipo Nodo</option>
				<option value="cliente"${filtro == 'cliente'?' selected':''}>Usuario</option>
				<option value="estado"${filtro == 'estado'?' selected':''}>Estado</option>												
			</select>
			&nbsp;
			&nbsp;
			<input type="text" class="texto" name="valorFiltro" id="valorFiltro" maxlength="30" size="10" value="${valorFiltro}">
		</td>
		<td align="left">
			<input type="text" class="texto" name="fechaIni" maxlength="10" size="10" value="${fechaInicial}">
		<span class="calendario"><a href="javascript:showCal('DateIni', 5, 5)" title="Click Para Abrir El Calendario" class="calendario"> (aaaa-mm-dd)</a></span>
		<div id="calIni" style="position:relative; visibility: hidden;"></td>

		<td align="left">
			<input type="text" class="texto" name="fechaFin" maxlength="10" size="10" value="${fechaFinal}">
			<span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateFin', 5, 5)">(aaaa-mm-dd)</a></span>
			<div id="calFin" style="position:relative; visibility: hidden;">
		</td>
		<td>
			<input type="button" class="boton" name="buscar" onClick="validarBuscar();" value="Aceptar">
		</td>
  </tr>
</table>
</form>

<p class="mensajeCentral" align="center">Primera Prueba de Tel&eacute;fono. ${requestScope.totalRegistros} Registros.<tags:ayudas pagina="Primeraprueba"/></p>

<jsp:include page="../reportes/paginacion.jsp" flush="true" /> </td>

<table width="100%" align="center">
<tr class="header-reporte" height="30">
	<td align="center" width="70">
	<c:choose>
		<c:when test="${orderBy == 'primero DESC'}">
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'primero ASC')" title="Ordenar Por Prueba Ascendentemente">Prueba</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'primero DESC')" title="Ordenar Por Prueba Descendentemente">Prueba</a>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center"width="80">
	<c:choose>
		<c:when test="${orderBy == 'telefono DESC'}">
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'telefono ASC')" title="Ordenar Por Telefono Ascendentemente">T&eacute;lefono</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'telefono DESC')" title="Ordenar Por Telefono Descendentemente">T&eacute;lefono</a>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center">
	<c:choose>
		<c:when test="${orderBy == 'cantidad DESC'}">
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'cantidad ASC')" title="Ordenar Por Cantidad Ascendentemente">Cant Pruebas</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'cantidad DESC')" title="Ordenar Por Cantidad Descendentemente">Cant Pruebas</a>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center">
	<c:choose>
		<c:when test="${orderBy == 'tipoprueba DESC'}">
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipoprueba ASC')" title="Ordenar Por Tipo de Prueba Ascendentemente">Tipo Prueba</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipoprueba DESC')" title="Ordenar Por Tipo de Prueba Descendentemente">Tipo Prueba</a>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center">
		<c:choose>
		<c:when test="${orderBy == 'codv DESC'}">
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'codv ASC')" title="Ordenar Por Codigo Ver Ascendentemente">Codigo Ver</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'codv DESC')" title="Ordenar Por Codigo Ver Descendentemente">Codigo Ver</a>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center">
	<c:choose>
		<c:when test="${orderBy == 'estado DESC'}">
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'estado ASC')" title="Ordenar Por Estado Ascendentemente">Estado</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'estado DESC')" title="Ordenar Por Estado Descendentemente">Estado</a>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center">
	<c:choose>
		<c:when test="${orderBy == 'site DESC'}">
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'site ASC')" title="Ordenar Por Site Ascendentemente">Site</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'site DESC')" title="Ordenar Por Site Descendentemente">Site</a>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center">
	<c:choose>
		<c:when test="${orderBy == 'cliente DESC'}">
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'cliente ASC')" title="Ordenar Por Usuario Ascendentemente">Usuario</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'cliente DESC')" title="Ordenar Por Usuario Descendentemente">Usuario</a>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center">
	<c:choose>
		<c:when test="${orderBy == 'fecha_inicial DESC'}">
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fecha_inicial ASC')" title="Ordenar Por Fecha Ascendentemente">Fecha</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fecha_inicial DESC')" title="Ordenar Por Fecha Descendentemente">Fecha</a>
		</c:otherwise>
	</c:choose>
	</td>	
	<td align="center">
	<c:choose>
		<c:when test="${orderBy == 'duracion DESC'}">
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'duracion ASC')" title="Ordenar Por Duracion Ascendentemente">Duraci&oacute;n</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'duracion DESC')" title="Ordenar Por Duracion Descendentemente">Duraci&oacute;n</a>
		</c:otherwise>
	</c:choose>
	</td>	
</tr>
<c:set var="row" value="1" />
<c:forEach var="registro" items="${listaPrimero}">
<c:set var="row" value="${(row+1)%2}" />
<tr class="row${row}" align="center">
	<td>
		<a href=javascript:Abre_ventanaDetallePrueba('${registro.idPrueba}'); title="Consultar Evento">${registro.idPrueba}</a>
	</td>
	<td>${registro.telefono}</td>
	<td>
		<a href="javascript:detallePruebas('${registro.telefono}')" title="Ver Pruebas">${registro.cantidad}</a>
	</td>
	<td>${registro.tipoPrueba}</td>
	<td>${registro.codigoVer}</td>
	<td>${registro.estado}</td>	
	<td>${registro.tipoNodo}</td>
	<td>${registro.cliente}</td>
	<td><fmt:formatDate value= "${registro.fechaInicial}" type="both" pattern="yy-MM-dd HH:mm:ss"/></td>
	<td><fmt:formatNumber value="${registro.duracion}" maxFractionDigits="2" minFractionDigits="2" /> seg</td>
</tr>
</c:forEach>
<tr class="fin-reporte">
	<td colspan="10">&nbsp;&nbsp;Fin De Reporte</td>
</tr>
</table>
<br>
<table align="center" width="50%">

<tr>
<td align="center" class="graficaLink">
	<a href="javascript:mostrarGrafica();">Grafica</a>
</td>
<td align="center">
	<form name="informes">
		 &nbsp;Exportar a: &nbsp;
		<select name="formatos">
		<option value="pdf">PDF</option>
		<option value="csv">CSV</option>
		<option value="xls">XLS</option>
		</select>&nbsp;
		<input name="informe" class="boton" onClick="javascript:informeGen(document.informes);" type="button" value="Aceptar">
	</form>
</td>
</tr>
</table>

<br>
</body>
</html>

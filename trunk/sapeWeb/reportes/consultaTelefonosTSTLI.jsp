					<!--  consultaTelefonosTSTLI.jsp  -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listaRequerimientos" value="${requestScope.listaRequerimientos}" />
<jsp:useBean id="msgCentral" class="java.lang.String" scope="request"/>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<jsp:useBean id="pagActual" class="java.lang.String" scope="request"/>
<jsp:useBean id="orderBy" class="java.lang.String" scope="request"/>
<jsp:useBean id="filtro" class="java.lang.String" scope="request"/>
<jsp:useBean id="valorFiltro" class="java.lang.String" scope="request"/>
<jsp:useBean id="cola" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Consulta de Telefonos Por Cola (${cola})</title>

<!--ATENCION:
TENER MUCHO CUIDADO, SE ESTA HACIENDO UN SUBMIT CON EL FORM Y EL SERVLET
TOMA COMO VARIABLE OPERACION EL INPUT HIDDEN KE HAY MAS ABAJO, NO ESTA TOMANDO
EL VALOR DE OPERACION KE UNO LE DA POR URL
-->

<style type="text/css">
	.cerrado {background-color: #ff5900;}
	.especial{background-color: #ff0000;}
</style>
<script language="JavaScript" src="javascript/varios.js"></script>
<script language="JavaScript" src="javascript/calendar.js"></script>
<script language="JavaScript">
<!--

addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");


	function direcciona(forma) {
		
		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}

		var val1 = forma.fechaIni.value;
		var val2 = forma.fechaFin.value;

		forma1 = document.formaPaginacion;

		var regXpag = forma1.regXPag.value;

		if(regXpag.length < 1  || isNaN(regXpag)){
			alert("Ingrese una cantidad VALIDA de Registros por pagina.");
			return;
		}

		var order = '${orderBy}';

		var filtro = forma.filtrar.options[forma.filtrar.selectedIndex].value;

		var cola = forma.colas.options[forma.colas.selectedIndex].value;

		var valorFiltro = forma.txtBuscar.value;

		location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=realizarConsultaTelefonosTSTLI&fechaIni="+val1+"&fechaFin="+val2+"&filtro="+filtro+"&valorFiltro="+valorFiltro+"&orderBy="+order+"&regPorPagina="+regXpag+"&cola="+cola;
		return;
	}

	function irAPagina(pagina, cantidadRegistros, order){

		if(isNaN(cantidadRegistros)){
			alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
			document.formaPaginacion.regXPag.focus();
			return;
		}
		var query = '${query}';
		location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=realizarConsultaTelefonosTSTLI&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;

	}

	function informeGen(format,pagina, cantidadRegistros, order){
		var val = format.formatos.options[format.formatos.selectedIndex].value;
		if(isNaN(cantidadRegistros)){
			alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
			document.formaPaginacion.regXPag.focus();
			return;
		}

		var query = '${query}';
		location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=realizarConsultaTelefonosTSTLI&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query+"&exportar=yes&pantalla=consultaTelefonosTSTLI&formato="+val;
	}

	function mostrarGrafica(modo){

		var forma = document.forma1;

		var val1 = forma.fechaIni.value;
		var val2 = forma.fechaFin.value;

		if(val1 == '' || val2 == '' ){
			alert("Ingrese una fecha!!!!");
			return;
		}


		var forma1 = document.formaPaginacion;

		var regXpag = forma1.regXPag.value;

		if(regXpag.length < 1  || isNaN(regXpag)){
			alert("Ingrese una cantidad VALIDA de Registros por pagina.");
			return;
		}

		var order = '${orderBy}';

		var filtro = forma.filtrar.options[forma.filtrar.selectedIndex].value;

		var valorFiltro = forma.txtBuscar.value;

		var cola = forma.colas.options[forma.colas.selectedIndex].value;


		if (modo == '1'){
			v800=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=mostrarGraficaInicialTSTLI&tipo=1&fechaIni="+val1+"&fechaFin="+val2+"&filtro="+filtro+"&valorFiltro="+valorFiltro+"&orderBy="+order+"&regPorPagina="+regXpag+"&cola="+cola, 'window800','scrollbars=yes,hotkeys=yes,height=575,width=750,left=100,resizable=yes,top=50,menubar=yes,toolbar=no');
			v800.focus();
		} else if (modo == '2') {
			v900=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=mostrarGraficaInicialTSTLI&tipo=2&fechaIni="+val1+"&fechaFin="+val2+"&filtro="+filtro+"&valorFiltro="+valorFiltro+"&orderBy="+order+"&regPorPagina="+regXpag+"&cola="+cola, 'window900','scrollbars=yes,hotkeys=yes,height=575,width=750,left=100,resizable=yes,top=50,menubar=yes,toolbar=no');
			v900.focus();
		} else if (modo == '3') {
			v700=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=mostrarGraficaInicialTSTLI&tipo=3&fechaIni="+val1+"&fechaFin="+val2+"&filtro="+filtro+"&valorFiltro="+valorFiltro+"&orderBy="+order+"&regPorPagina="+regXpag+"&cola="+cola, 'window700','scrollbars=yes,hotkeys=yes,height=575,width=750,left=100,resizable=yes,top=50,menubar=yes,toolbar=no');
			v700.focus();
		}
	}
	
	function cambiarEstado(columna, oid) {
		var estado = columna.getElementsByTagName("div")[0];
		var respuesta = prompt("Entre el nuevo estado para el teléfono\n(II,ET,NI)");
		if (respuesta == null) { return; }
		if (respuesta != 'II' && respuesta != 'ET' && respuesta != 'NI') {
				alert("Respuesta inválida");
			return;
		} 
			//si es el mismo, no llamo al servlet
		if (estado.innerHTML == respuesta) {
			return;
		}
		var url = '${pageContext.request.contextPath}/actionSape?accion=reportesColas&operacion=actualizarEstado&oid='+oid+'&estadoNuevo='+respuesta;
		cargarHttpRequest(url, "GET", estadoCambiado);
		estado.innerHTML = respuesta;
	}
	
	
	function estadoCambiado() {
	    if (req.readyState == 4) {
	        if (req.status == 200) {
				var respuesta = req.responseText;
				alert(respuesta);
	        }
	    }
	}
	
	function desbloquearTelefono() {
		var respuesta = prompt("Entre el Telefono a Desbloquear");
		if (respuesta == null) { return; }
		if (respuesta == "" || isNaN(respuesta)) {
			alert ("Telefono invalido!");
			return; 
		}
		var url = '${pageContext.request.contextPath}/actionSape?accion=reportesColas&operacion=desbloquearTelefono&telefono=' + respuesta;
		cargarHttpRequest(url, "GET", telefonoDesbloueado);
	}
	
	
	function telefonoDesbloueado() {
	    if (req.readyState == 4) {
	        if (req.status == 200) {
				var respuesta = req.responseText;
				alert(respuesta);
	        }
	    }
	}

//-->
</script>

</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0" bgcolor="white">
<jsp:include page="../encabezado.jsp" flush="true" />

<form name="forma1" method="get" action="">
<input type="hidden" value="realizarConsultaTelefonosTSTLI" name="operacion">

<table width="100%" cellspacing=0 cellpadding=0 border=0 align="center">
  <tr>
  <td>&nbsp;&nbsp;&nbsp;</td>
  <td align=left class="header-filtro">COLA :</td>
  <td align=left class="header-filtro">
     BUSCAR POR:
  </td>
   <td align=left class="header-filtro">
        DESDE :
    </td>
    <td align=left class="header-filtro">
        HASTA :
    </td>
  </tr>
  <tr>
    <td>&nbsp;&nbsp;&nbsp;</td>
	<td align="left">
	<select name="colas">
		<option value="TSTLI" ${cola == 'TSTLI'?'selected':''}>TSTLI</option>
		<option value="ANALI" ${cola == 'ANALI'?'selected':''}>ANALI</option>
		<option value="REPLI" ${cola == 'REPLI'?'selected':''}>REPLI</option>
		<option value="DIST"  ${cola == 'DIST'?'selected':''}>DIST</option>
		<option value="REPCA" ${cola == 'REPCA'?'selected':''}>REPCA</option>
		<option value="GRANC" ${cola == 'GRANC'?'selected':''}>GRANC</option>
	</select></td>
    <td align="left">
  	<select name="filtrar">
		<option value="ninguno" ${filtro == 'ninguno'?'selected':''}>Ninguno</option>
		<option value="identificador" ${filtro == 'identificador'?'selected':''}>Telefono</option>
		<option value="tipo_nodo" ${filtro == 'tipo_nodo'?'selected':''}>Tipo Nodo</option>
		<option value="area_trabajo_id" ${filtro == 'area_trabajo_id'?'selected':''}>Area Trabajo</option>
		<option value="secuencia" ${filtro == 'secuencia'?'selected':''}>Secuencia</option>
		<option value="armario_id" ${filtro == 'armario_id'?'selected':''}>Armario</option>
		<option value="estado" ${filtro == 'estado'?'selected':''}>Estado</option>
		<option value="subzona_id" ${filtro == 'subzona_id'?'selected':''}>Subzona</option>
		<option value="strip_id" ${filtro == 'strip_id'?'selected':''}>Strip</option>
		<option value="cable" ${filtro == 'cable'?'selected':''}>Cable</option>
		<option value="par_primario_id" ${filtro == 'par_primario_id'?'selected':''}>Par Primario</option>
		<option value="caja_id" ${filtro == 'caja_id'?'selected':''}>Caja</option>
		<option value="par_secundario_id" ${filtro == 'par_secundario_id'?'selected':''}>Par Secundario</option>
		<option value="producto_id" ${filtro == 'producto_id'?'selected':''}>Producto</option>
		<option value="tipo_cliente" ${filtro == 'tipo_cliente'?'selected':''}>Tipo Cliente</option>
		<option value="cola_enruta" ${filtro == 'cola_enruta'?'selected':''}>Cola Enruta</option>
		<option value="codobserva" ${filtro == 'codobserva'?'selected':''}>Codigo Enruta</option>
	</select>
	&nbsp;&nbsp;<input class="texto" type="text" name="txtBuscar" size="10" value="${valorFiltro}">
    </td>
    <td align="left">
        <input type="text" class="texto" name="fechaIni" maxlength="10" size="10" value="${fIni}">
        <span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateIni', 5, 5)"> (aaaa-mm-dd)</a></span>
        <div id="calIni" style="position:relative; visibility: hidden;">
    </td>
    <td align="left">
        <input type="text" class="texto" name="fechaFin" maxlength="10" size="10" value="${fFin}">
        <span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateFin', 5, 5)">(aaaa-mm-dd)</a></span>
        <div id="calFin" style="position:relative; visibility: hidden;">
    </td>
	<td>
		<input class="boton" type="button" name="buscar" onClick="direcciona(document.forma1)" value="Aceptar">
	</td>
  </tr>
</table>
</form>

<table width="100%" border="0" bgcolor="black" align="center" cellpadding="2" cellspacing="0">
<tr valign="middle">
	<td align="center" class="mensajeCentral">${msgCentral}<tags:ayudas pagina="Tstli"/></td>
</tr>
</table>

<!-- paginacion!!!!!!!! -->
<jsp:include page="paginacion.jsp" flush="true" />


<table width="100%" border="0" align="center">
<tr class="header-reporte">
	<td height="30" width="80" align="center">
		<c:choose>
			<c:when test="${orderBy == 'identificador DESC'}">
				<span title="Filtrar por TELEFONO Ascendentemente"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'identificador ASC')">T&eacute;lefono</a></span>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por TELEFONO Descendentemente"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'identificador DESC')">T&eacute;lefono</a></span>
			</c:otherwise>
	</c:choose></td>
	<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'tipo_nodo DESC'}">
				<span title="Filtrar por TIPO NODO Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipo_nodo ASC')">Tipo<br>Nodo</a></span>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por TIPO NODO Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipo_nodo DESC')">Tipo<br>Nodo</a></span>
			</c:otherwise>
		</c:choose>
	</td>
	<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'area_trabajo_id DESC'}">
				<font color="white">
					<span title="Filtrar por AREA DE TRABAJO Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'area_trabajo_id ASC')">Area<br>Trabajo</a></span>
				</font>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por AREA DE TRABAJO Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'area_trabajo_id DESC')">Area<br>Trabajo</a></span>
			</c:otherwise>
		</c:choose>
	</td>
	<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'cable DESC'}">
				<font color="white">
					<span title="Filtrar por CABLE Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'cable ASC')">Cable</a></span>
				</font>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por CABLE Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'cable DESC')">Cable</a></span>
			</c:otherwise>
		</c:choose>
	</td>
	<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'strip_id DESC'}">
				<font color="white">
					<span title="Filtrar por STRIP Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'strip_id ASC')">Strip</a></span>
				</font>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por STRIP Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'strip_id DESC')">Strip</a></span>
			</c:otherwise>
		</c:choose>
		-
		<c:choose>
			<c:when test="${orderBy == 'par_primario_id DESC'}">
				<span title="Filtrar por PAR PRIMARIO Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'par_primario_id ASC')">Par</a></span>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por PAR PRIMARIO Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'par_primario_id DESC')">Par</a></span>
			</c:otherwise>
		</c:choose>
	</td>
	<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'armario_id DESC'}">
				<span title="Filtrar por ARMARIO Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'armario_id ASC')">Armario</a></span>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por ARMARIO Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'armario_id DESC')">Armario</a></span>
			</c:otherwise>
		</c:choose>
	</td>
	<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'caja_id DESC'}">
					<span title="Filtrar por CAJA Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'caja_id ASC')">Caja</a></span>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por CAJA Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'caja_id DESC')">Caja</a></span>
			</c:otherwise>
		</c:choose>
		-
		<c:choose>
			<c:when test="${orderBy == 'par_secundario_id DESC'}">
				<span title="Filtrar por PAR SECUNDARIO Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'par_secundario_id ASC')">Par
			</c:when>
			<c:otherwise>
				<span title="Filtrar por PAR SECUNDARIO Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'par_secundario_id DESC')">Par</span>
			</c:otherwise>
		</c:choose>
	</td>
	<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'secuencia DESC'}">
				<span title="Filtrar por SECUENCIA Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'secuencia ASC')">Sec</a></span>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por SECUENCIA Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'secuencia DESC')">Sec</a></span>
			</c:otherwise>
		</c:choose>
	</td>

	<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'subzona_id DESC'}">
				<span title="Filtrar por SUBZONA Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'subzona_id ASC')">Subzona</a></span>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por SUBZONA Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'subzona_id DESC')">Subzona</a></span>
			</c:otherwise>
		</c:choose>
	</td>
		<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'producto_id DESC'}">
				<span title="Filtrar por PRODUCTO Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'producto_id ASC')">Prod</a></span>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por PRODUCTO Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'producto_id DESC')">Prod</a></span>
			</c:otherwise>
		</c:choose>
	</td>
	<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'tipo_cliente DESC'}">
				<span title="Filtrar por TIPO CLIENTE Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipo_cliente ASC')">Tipo<br />Cli</a></span>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por TIPO CLIENTE Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipo_cliente DESC')">Tipo<br />Cli</a></span>
			</c:otherwise>
		</c:choose>
	</td>
	<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'fecha DESC'}">
				<span title="Filtrar por FECHA DE IMPORTACION Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fecha ASC')">Fecha</a></span>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por FECHA DE IMPORTACION Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fecha DESC')">Fecha</a></span>
			</c:otherwise>
		</c:choose>
	</td>
	
	<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'estado DESC'}">
					<span title="Filtrar por ESTADO Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'estado ASC')">Est</a></span>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por ESTADO Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'estado DESC')">Est</a></span>
			</c:otherwise>
		</c:choose>
	</td>	
	
	<td height="30" align="center">
		<c:choose>
			<c:when test="${orderBy == 'cola_enruta DESC'}">
				<span title="Filtrar por COLA Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'cola_enruta ASC')">Enruta</a></span>
			</c:when>
			<c:otherwise>
				<span title="Filtrar por COLA Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'cola_enruta DESC')">Enruta</a></span>
			</c:otherwise>
		</c:choose>
	</td>


</tr>
<c:set var="i" value="0" />
<c:forEach items="${listaRequerimientos}" var="evento">
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}">
		<TD align="center">
			<c:choose>
			<c:when test="${evento.ticketPrueba == '0'}">
				${evento.identificador}
			</c:when>
			<c:otherwise>
				<a href="javascript:Abre_ventanaDetallePrueba('${evento.ticketPrueba}');">${evento.identificador}</a>
			</c:otherwise>
		</c:choose>
			
		</td>
		<TD align="center">${evento.tipo_nodo}</td>
		<TD align="center">${evento.area_trabajo_id}</td>
		<TD align="center">${evento.cable}</td>
		<TD align="center">${evento.strip_id}-${evento.par_primario_id}</td>
		<TD align="center">${evento.armario_id}</td>
		<TD align="center">${evento.caja_id}-${evento.par_secundario_id}</td>
		<TD align="center">${evento.secuencia}</td>
		
		<c:choose>
			<c:when test="${evento.subzona_id == 'EGC'}">
				<TD align="center" class="especial">${evento.subzona_id}</td>
				<TD align="center"${empty evento.producto?"":"class=\"especial\""} >${evento.producto}</td>
			</c:when>
			<c:otherwise>
				<TD align="center">${evento.subzona_id}</td>
				<TD align="center"${evento.producto == 'PBX'? "class=\"especial\"":""}>${evento.producto}</td>
			</c:otherwise>
		</c:choose>
		
		<TD align="center">${evento.tipoCliente}</td>
		<TD align="center">
			<fmt:formatDate value= "${evento.fecha}" type="both" pattern="yy-MM-dd HH:mm:ss"/>
		</td>
		<c:set var="cambiarEstado" value="" />
		<c:if test="${sessionScope.usuario.nivel == '3'}">
			<c:set var="cambiarEstado" value=" onClick=\"cambiarEstado(this, ${evento.oid});\"" />
		</c:if>
		<TD align="center" class="estado${evento.estado}${row}"${cambiarEstado}>
			<div>${evento.estado}</div>
		</td>
		<td align="center"${evento.colaEnruta == "CERRADO" ? " class=\"cerrado\"" : ""}>${evento.colaEnruta}-${evento.codObservacion}</td>
	</tr>
	<c:set var="i" value="${i + 1}"/>
</c:forEach>
	<tr>
		<td class="fin-reporte" colspan="14">Fin de reportes</td>
	</tr>
</table>


<br>

<table width="750" align="center">
<tr align="center">
	<td align="rigth">Gr&aacute;ficas: </td>
	<td class="graficaLink" align="center">
		<a href="javascript:mostrarGrafica('1');" title="Mostrar grafica por Enrutamiento(Cola-Codigo)">Cola-C&oacute;digo</a>
		&nbsp; - &nbsp;  
		<a href="javascript:mostrarGrafica('2');" title="Mostrar grafica por Cola">Cola</a>
		&nbsp; - &nbsp; 
		<a href="javascript:mostrarGrafica('3');" title="Mostrar grafica por Telefono">Tel&eacute;fono</a>
	</td>
	<td align="center" width="240">
		<form name="informes">
			&nbsp;Exportar a: &nbsp;
			<select name="formatos" >
				<option value="pdf">PDF</option>
				<option value="csv">CSV</option>
				<option value="xls">XLS</option>
			</select>&nbsp;
			<input name="informe" class="boton" onClick="javascript:informeGen(document.informes,'${pagActual}',document.formaPaginacion.regXPag.value,'${orderBy}');" type="button" value="Aceptar">
		</form>
	</td>
	<c:if test="${sessionScope.usuario.nivel == '3'}">
	<td align="center" width="160">
		<a href="javascript:desbloquearTelefono();" Title="Desbloquea un telefono que aparece bloqueado en el OSS">Desbloquear Telefono</a>
	</td>
	</c:if>
</tr>
</table>
</body>
</html>

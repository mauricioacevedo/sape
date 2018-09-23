				<!-- indicadoresCabezales.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<jsp:useBean id="pagActual" class="java.lang.String" scope="request"/>
<jsp:useBean id="orderBy" class="java.lang.String" scope="request"/>
<jsp:useBean id="cantidadTotalRegistros" class="java.lang.String" scope="request"/>
<jsp:useBean id="filtro" class="java.lang.String" scope="request"/>
<jsp:useBean id="valorFiltro" class="java.lang.String" scope="request"/>
<jsp:useBean id="listaAutotests" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="listaSites" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="listaCentrales" class="java.util.ArrayList" scope="request"/>
<html>
<head>
<title>SAPE - Indicadores de Cabezales</title>
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">

addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");

	function irAPagina(pagina, cantidadRegistros, order){
		if(isNaN(cantidadRegistros)){
			alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
			document.formaPaginacion.regXPag.focus();
			return;
		}	
		var query = '${query}';
		location.href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=indicadoresCabezales&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;
	}
	
	
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

		var filtro = "b.site";

		var valorFiltro = forma.filtrar.options[forma.filtrar.selectedIndex].value;

		
		if(valorFiltro == 'ninguno') {
			filtro = "";
			valorFiltro = "";
		}
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=indicadoresCabezales&fIni="+val1+"&fFin="+val2+"&filtro="+filtro+"&valorFiltro="+valorFiltro+"&orderBy="+order+"&regPorPagina="+regXpag;
		return;
	}

	
	function updateCabeza() {
		var centrales = document.getElementById("listaCentrales");
		var nuevaCentral = centrales.options[centrales.selectedIndex].value;
		//alert('nueva: ' + nuevaCentral);

		var funcion = recuperarListaCabezas;
		cargarHttpRequest("${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono&operacion=buscarSite&central=" + nuevaCentral, "GET", funcion);
	return true;
	}
	
	
	function recuperarListaCabezas() {
		if (req.readyState == 4) {
        	if (req.status == 200) {
				var respuesta = req.responseXML;

				//lleno el combo de los tipos de las cabezas
				var XMLTipoPruebas = req.responseXML.getElementsByTagName("cabezas")[0];

				var cabezas =  document.getElementById("cabezas");
				cabezas.options.length = 0;
				for (i = 0; i < XMLTipoPruebas.childNodes.length; i++) {
					var opcion = XMLTipoPruebas.childNodes[i].childNodes[0].nodeValue;
					var data = opcion.split(",");
					addOpcion(cabezas,data[0],data[1]);
				}
        	}
    	}
	}
	
	function cargarHttpRequest(url, metodo, funcion) {
    	if (window.XMLHttpRequest) {
        	req = new XMLHttpRequest();
    	} else if (window.ActiveXObject) {
        	req = new ActiveXObject("Microsoft.XMLHTTP");
    	}
    	req.open(metodo, url, true);
    	req.onreadystatechange = funcion;
    	req.send(null);
	}

	function probarCabeza(){
		
		var cabezas = document.getElementById("cabezas");
		//alert("kiere probar cabeza: "+cabeza.length);
		
		var centrales = document.getElementById("listaCentrales");
		var nuevaCentral = centrales.options[centrales.selectedIndex].value;
		
		if(nuevaCentral != "todas"){
			if(cabezas.length <= 0){return};
		}
		
		var cabeza = "";
		if(nuevaCentral == "todas")
			cabeza = nuevaCentral.toUpperCase();
		else
			cabeza = cabezas.options[cabezas.selectedIndex].value;
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=probarCabezal&id="+cabeza;
		return;
		
		
	}
</script>


<jsp:include page="../encabezado.jsp" flush="true" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<style type="text/css">
  .atencion {background-color: #ff5900;}
  .error {background-color: #ff0000;}
</style>
</head>
<body bgcolor="white">

<form name="forma1">

<table width="100%" cellspacing=0 cellpadding=0 border=0 bgcolor="white" align="center">
  <tr>
  <td>&nbsp;&nbsp;&nbsp;</td>
  <td align=left class="header-filtro">
     BUSCAR POR SITE:
  </td>
   <td align=left class="header-filtro">
        DESDE :
    </td>
    <td align=left class="header-filtro"s>
        HASTA :
    </td>
  </tr>
  <tr>
    <td>&nbsp;&nbsp;&nbsp;</td>
    <td align="left">
  	<select name="filtrar">
		<option value="ninguno">Todos</option>
		<c:forEach items="${listaSites}" var="site">
		<option value="${site}">${site}</option>
		</c:forEach>
	</select>
	&nbsp;&nbsp;<!--input type="text" name="txtBuscar" size="10" value="${valorFiltro}"-->
    </td>
    <td align="left">
        <input class="texto" type="text" name="fechaIni" maxlength="10" size="10" value="${fIni}">
        <span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateIni', 5, 5)"> (aaaa-mm-dd)</a></span>
        <div id="calIni" style="position:relative; visibility: hidden;">
    </td>
    <td align="left">
        <input class="texto" type="text" name="fechaFin" maxlength="10" size="10" value="${fFin}">
        <span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateFin', 5, 5)">(aaaa-mm-dd)</a></span>
        <div id="calFin" style="position:relative; visibility: hidden;">
    </td>
	<td>
		<input class="boton" type="button" name="buscar" onClick="direcciona(document.forma1)" value="Aceptar">
	</td>
  </tr>
</table>
</form>

<table width="100%" border="0" align="center">
<tr>
	<td align="center" class="mensajeCentral">Numero de registros ${cantidadTotalRegistros}, Rango de fechas: ${fIni} a ${fFin}<tags:ayudas pagina="IndicadoresCabezales"/></td>
</tr>
</table>

<jsp:include page="../reportes/paginacion.jsp" flush="true" />

<table width="100%" border="0" align="center">
<tr class="header-reporte">

	<td width="10%" height="30" align="center">

	<c:choose>
		<c:when test="${orderBy == 'site DESC'}">
			<span title="Filtrar por Site Ascendentemente"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'site ASC')">Site</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Site Descendentemente"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'site DESC')">Site</a></span>
		</c:otherwise>
	</c:choose>

	</td>
	<td width="15%" height="30" align="center">

	<c:choose>
		<c:when test="${orderBy == 'fecha DESC'}">
			<a title="Filtrar por FECHA Ascendentemente" href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fecha ASC')">Fecha</a>
	 	</c:when>
	 	<c:otherwise>
			<a title="Filtrar por FECHA Descendentemente" href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fecha DESC')">Fecha</a>
		</c:otherwise>
	</c:choose>

	</td>
	<td width="10%" height="30" align="center">

	<c:choose>
		<c:when test="${orderBy == 'procesador DESC'}">
			<span title="Filtrar por Procesador Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'procesador ASC')">Procesador</a></span>
		</c:when>
		<c:otherwise>
			<span title="Filtrar por Procesador Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'procesador DESC')">Procesador</a></span>
		</c:otherwise>
	</c:choose>

	</td>
	<td width="10%" height="30" align="center">

	<c:choose>
		<c:when test="${orderBy == 'modulo_pruebas DESC'}">
			<span title="Filtrar por Modulo Pruebas Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'modulo_pruebas ASC')">Mod. Pruebas</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Modulo de Pruebas Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'modulo_pruebas DESC')">Mod. Pruebas</a></span>
		</c:otherwise>
	</c:choose>

	</td>
	<td width="6%" height="30" align="center">
	<c:choose>
		<c:when test="${orderBy == 'ethernet DESC'}">
			<span title="Filtrar por Ethernet Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'ethernet ASC')">Ethernet</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Ethernet Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'ethernet DESC')">Ethernet</a></span>
		</c:otherwise>
	</c:choose>

	</td>

	<td width="6%" height="30" align="center">
	<c:choose>
		<c:when test="${orderBy == 'modem DESC'}">
			<span title="Filtrar por MODEM Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'modem ASC')">Modem</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por MODEM Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'modem DESC')">Modem</a></span>
		</c:otherwise>
	</c:choose>

	</td>

	<td width="5%" height="30" align="center">

	<c:choose>
		<c:when test="${orderBy == 'flash_mem DESC'}">
			<span title="Filtrar por Flash_mem Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'flash_mem ASC')">Flash<br>mem</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Flash_mem Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'flash_mem DESC')">Flash<br>mem</a></span>
		</c:otherwise>
	</c:choose>

	</td>

	<td width="8%" height="30" align="center">

	<c:choose>
		<c:when test="${orderBy == 'tarjeta_expansion DESC'}">
			<span title="Filtrar por Tarjeta de Expansion Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tarjeta_expansion ASC')">Tarj.Exp</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Tarjeta de Expansion Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tarjeta_expansion DESC')">Tarj.Exp</a></span>
		</c:otherwise>
	</c:choose>

	</td>

	<td width="8%" height="30" align="center">

	<c:choose>
		<c:when test="${orderBy == 'controlador_troncal DESC'}">
			<span title="Filtrar por Controlador Troncal Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'controlador_troncal ASC')">Contr.Tronc</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Controlador Troncal Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'controlador_troncal DESC')">Contr.Tronc</a></span>
		</c:otherwise>
	</c:choose>

	</td>

	<td width="16%" height="30" align="center">

	<c:choose>
		<c:when test="${orderBy == 'observacion DESC'}">
			<span title="Filtrar por Observacion Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'observacion ASC')">Observacion</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Observacion Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'observacion DESC')">Observacion</a></span>
		</c:otherwise>
	</c:choose>

	</td>

	<td width="6%" height="30" align="center">

	<c:choose>
		<c:when test="${orderBy == 'ultimousuario DESC'}">
			<span title="Filtrar por Ultimo Usuario Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'ultimousuario ASC')">Usuario</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Ultimo Usuario Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'ultimousuario DESC')">Usuario</a></span>
		</c:otherwise>
	</c:choose>
	</td>

</tr>
<c:set var="i" value="0"/>
<c:forEach items="${listaAutotests}" var="test">
<tr class="row${i%2 == 0? 0 : 1}">
<td align="center">${test.site}</td>
<td align="center"><fmt:formatDate value= "${test.fecha}" type="both" pattern="yy-MM-dd HH:mm:ss"/></td>
<td align="center"${test.procesador != 'OK' and test.procesador != 'ERROR'?" class='atencion'":""}${test.procesador == 'ERROR'?" class='error'":""}>${test.procesador}</td>
<td align="center"${test.modulo_pruebas != 'OK' and test.modulo_pruebas != 'ERROR'?" class='atencion'":""}${test.modulo_pruebas == 'ERROR'?" class='error'":""}>${test.modulo_pruebas}</td>
<td align="center"${test.ethernet != 'OK' and test.ethernet != 'ERROR'?" class='atencion'":""}${test.ethernet == 'ERROR'?" class='error'":""}>${test.ethernet}</td>
<td align="center"${test.modem != 'OK'  and test.modem != 'ERROR'?" class='atencion'":""}${test.modem == 'ERROR'?" class='error'":""}>${test.modem}</td>
<td align="center"${test.flash_mem != 'OK' and test.flash_mem != 'ERROR'?" class='atencion'":""}${test.flash_mem == 'ERROR'?" class='error'":""}>${test.flash_mem}</td>
<td align="center"${test.tarjeta_expansion != 'OK' and test.tarjeta_expansion != 'ERROR'?" class='atencion'":""}${test.tarjeta_expansion == 'ERROR'?" class='error'":""}>${test.tarjeta_expansion}</td>
<td align="center"${test.controlador_troncal != 'OK' and test.controlador_troncal != 'ERROR'?" class='atencion'":""}${test.controlador_troncal == 'ERROR'?" class='error'":""}>${test.controlador_troncal}</td>
<td align="center">${test.observacion}</td>
<td align="center">${test.ultimoUsuario}</td>

</tr>
<c:set var="i" value="${i +1}"/>
</c:forEach>
</table>
<table width="100%" border="0" BGCOLOR="black" align="center">
	<tr><td class="mensajeCentral">Total ${cantidadTotalRegistros} Registros.</td></tr>
</table>
<br>
<table width="500" align="center">
<tr>
<td width="60" align="center">Probar :</td>
<td width="120" align="center">
	<select name="listaCentrales" id="listaCentrales" onChange="javascript:updateCabeza();">
	<c:forEach items="${listaCentrales}" var="central">
	<option value="${central}">${central}</option>
	</c:forEach>
	</select>
</td>
<td width="130" align="center"><select name="cabezas" id="cabezas"></select></td>
<td width="*" align="center"><input class="boton" type="button" name="go" value="Probar" onClick="javascript:probarCabeza();"></td>
</tr>
</table>
</body>
<script language="JavaScript">updateCabeza();</script>
</html>

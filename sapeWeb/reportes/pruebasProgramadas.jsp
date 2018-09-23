				<!--pruebasProgramadas.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:useBean id="listaRutinas" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<jsp:useBean id="valorOpcion" class="java.lang.String" scope="request"/>
<jsp:useBean id="opcion" class="java.lang.String" scope="request"/>
<jsp:useBean id="pagActual" class="java.lang.String" scope="request"/>
<jsp:useBean id="orderBy" class="java.lang.String" scope="request"/>
<jsp:useBean id="cantidadTotalRegistros" class="java.lang.String" scope="request"/>
<jsp:useBean id="query" class="java.lang.String" scope="request"/>

<html>
<head>
<title>SAPE - Pruebas Programadas</title>

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

		if(op == 'todos'){

			direcciona(op,'',forma.fechaIni.value,forma.fechaFin.value,order,regXpag,pagActual);
			return;
		}

	/*	if ( forma.valor.value.length<1 ){
			window.alert("Valor de Busqueda  NO VALIDO");
			forma.valor.focus();
			return;
		}*/

/*		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}
*/

		if(op == "telefono"){

			var telefono = forma.valor.value;
			if (telefono.length > ${applicationScope.cantDigitosEntorno}) {
				alert("El telefono debe ser maximo de ${applicationScope.cantDigitosEntorno} digitos");
			} else {
					//si no es numerico verifico que tenga el %
				var ultima = telefono.substring(telefono.length - 1,telefono.length);
				 if (ultima == "%") {
				 	if (!isNaN(telefono.substr(0,telefono.length - 1))) {
							//el 25 es para convertir el % en %25
					 	direcciona("rangoTelefono",telefono+"25",forma.fechaIni.value,forma.fechaFin.value,order,regXpag,pagActual);
						return;
					} else {
						alert("Telefono "+forma.valor.value+" NO VALIDO (solo numeros)");
						forma.valor.focus();
						return;
					}
				 } else {
				 	if (isNaN(telefono)) {
						alert("Telefono "+forma.valor.value+" NO VALIDO (solo numeros)");
						forma.valor.focus();
						return;
					}
				 }
			}
		}
		direcciona(op,forma.valor.value,forma.fechaIni.value,forma.fechaFin.value,order,regXpag,'');
		return;
	}


	function direcciona(opcion,valor,fechaIni,fechaFin,orderBy,regXpag,pagActual) {
		location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=consultaPruebasProgramadas&opcion="+opcion+"&valorOpcion="+valor+"&fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&orderBy="+orderBy+"&regPorPagina="+regXpag+"&pagActual="+pagActual;
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
	location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=consultaPruebasProgramadas&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;

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

//-->
</script>
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
			<option value="todos" ${opcion == ''?'selected':''}>Todos</option> 
			<option value="CA" ${opcion == 'CA'?'selected':''}>Cable</option>
			<option value="AR" ${opcion == 'AR'?'selected':''}>Armario</option>
			<option value="CL" ${opcion == 'CL'?'selected':''}>Cliente</option>
			<option value="SE" ${opcion == 'SE'?'selected':''}>Serie</option>
		</select>&nbsp;&nbsp;&nbsp;
		<input class="texto" type="text" name="valor" value="${valorOpcion}" size="10" maxlength="40"></td>

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
	<td align="center" class="mensajeCentral"> ${msgCentral} <%--tags:ayudas pagina="Consultaprueba"/--%> </td>
</tr>
</table>

<jsp:include page="paginacion.jsp" flush="true" />

<script language="javascript" src="javascript/tooltip/domLib.js"></script>
<script language="javascript" src="javascript/tooltip/domTT.js"></script>
<style>@import url(javascript/tooltip/example.css);</style>
<script language="Javascript">
	var domTT_classPrefix = 'domTTOverlib';
 
	function toolTip(padreEvento, evento,codv,desc) {
		var titulo = "";
	    var contenido = " Codigo Ver: "+codv+"<br> Descripcion: "+desc;
	    return mostrarDivAyuda(padreEvento, evento,'',contenido);
	}
//-->
</script>

<table width="100%" border="0" align="center">
<tr>

	<td width="5%" height="30" align="center" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'id DESC'}">
			<span title="Filtrar por ID Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'id ASC')">Id</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por ID Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'id DESC')">Id</a></span>
		</c:otherwise>
	</c:choose>
	</td>
	
	<td width="10%" height="30" align="center" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'tipo DESC'}">
			<a class="header-filtro" title="Ordenar por Tipo de Rutina Ascendentemente" href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipo ASC')">Tipo</a>
	 	</c:when>
	 	<c:otherwise>
			<a title="Ordenar por Tipo de Rutina Descendentemente" class="header-filtro" href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipo DESC')">Tipo</a>
		</c:otherwise>
	</c:choose>
	</td>

	<td width="10%" height="30" align="center" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'valorTipo DESC'}">
			<a class="header-filtro" title="Ordenar por Rutina Ascendentemente" href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'valorTipo ASC')">Rutina</a>
	 	</c:when>
	 	<c:otherwise>
			<a title="Ordenar por Rutina Descendentemente" class="header-filtro" href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'valorTipo DESC')">Rutina</a>
		</c:otherwise>
	</c:choose>
	</td>
	
	<td width="10%" height="30" align="center" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'usuario DESC'}">
			<a class="header-filtro" title="Ordenar por Usuario Ascendentemente" href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'usuario ASC')">Usuario</a>
	 	</c:when>
	 	<c:otherwise>
			<a title="Ordenar por Usuario Descendentemente" class="header-filtro" href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'usuario DESC')">Usuario</a>
		</c:otherwise>
	</c:choose>
	</td>
	
	<td width="15%" height="30" align="center" class="header-reporte">
		
	<c:choose>
		<c:when test="${orderBy == 'tipoDePrueba DESC'}">
	 		<font color=white>
			<span title="Filtrar por Tipo de Prueba Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipoDePrueba ASC')">Tipo Prueba</a></span></font>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Tipo de Prueba Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipoDePrueba DESC')">Tipo Prueba</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
	<td width="15%" height="30" align="center" class="header-reporte">
		
	<c:choose>
		<c:when test="${orderBy == 'fechaProgramada DESC'}">
			<font color=white>
			<span title="Filtrar por Fecha Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fechaProgramada ASC')">Fecha</a></span></font>
		</c:when>
		<c:otherwise>
			<span title="Filtrar por Fecha Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fechaProgramada DESC')">Fecha</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
	<td width="10%" height="30" align="center" class="header-reporte">
	
	<c:choose>
		<c:when test="${orderBy == 'tipoHorario DESC'}">
	 		<font color=white>
			<span title="Filtrar por Tipo de Horario Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipoHorario ASC')">Horario</a></span></font>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Tipo de Horario Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipoHorario DESC')">Horario</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
	<td width="30%" height="30" align="center" class="header-reporte">Programacion</td>
	
	<td width="15%" height="30" align="center" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'habilitado DESC'}">
	 		<font color=white>
			<span title="Filtrar por Estado Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'habilitado ASC')">Estado</a></span></font>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Estado Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'habilitado DESC')">Estado</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
	
</tr>
<c:set var="i" value="0" />
<c:forEach items="${listaRutinas}" var="rutina">
	<tr class="row${i%2 == 0? 0 : 1}">
		<TD height="20" align="CENTER"><a href="javascript:estadistico('${rutina.id}');">${rutina.id}</a></td>
		<TD height="20" align="CENTER">
		<c:choose>
		<c:when test="${rutina.tipo == 'SE'}">Serie</c:when>
		<c:when test="${rutina.tipo == 'CA'}">Cable</c:when>
		<c:when test="${rutina.tipo == 'AR'}">Armario</c:when>
		<c:when test="${rutina.tipo == 'CL'}">Cliente</c:when>
		</c:choose>		
		</td>
		<TD height="20" align="CENTER">${rutina.valorTipo}</td>
		<TD height="20" align="CENTER">${rutina.usuario}</td>
		<TD height="20" align="CENTER">${rutina.tipoDePrueba}</td>
		<TD height="20" align="CENTER"><fmt:formatDate value= "${rutina.fechaProgramada}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<TD height="20" align="CENTER">${rutina.tipoHorario}</td>
		<TD height="20" align="CENTER">
		<c:choose>
			<c:when test="${rutina.tipoHorario=='H'}"><%-- POR HORAS --%>
				Horas: ${rutina.horas} ${rutina.dias != ''?', Dias:':''}${rutina.dias}
			</c:when>
			<c:when test="${rutina.tipoHorario=='D'}"><%-- DEFINIDO POR EL USUARIO --%>
				
				<table width="100%" align=center>
				<tr class="fuentePequena">
				<td>${rutina.fechaInicial != ''?'Fecha Inicial:':''}${rutina.fechaInicial}</td><td>Hora Inicial:${rutina.horaInicial }</td>
				</tr>
				<tr class="fuentePequena">
				<td>${rutina.fechaFinal != ''?'Fecha Final:':''}${rutina.fechaFinal}</td><td>${rutina.horaFinal != ''?'Hora Final:':''}${rutina.horaFinal}</td>
				</tr>
				</table></font>
			</c:when>
		</c:choose>
		</td>
		<TD height="20" align="CENTER">${rutina.habilitado == true ?"habilitada":"deshabilitada"}</td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>
</table>

<table width="100%" border="0" BGCOLOR="black" align="center">
<tr>
	<td class="fin-reporte">Fin de reportes</td>
</tr>
</table>
<br>
<%-- center>
	<form name="informes">
		 &nbsp;Exportar a: &nbsp;
		<select name="formatos" >
		<option value="pdf">PDF</option>
		<option value="csv">CSV</option>
		<option value="xls">XLS</option>
		</select>&nbsp;
		<input name="informe" class="boton" onClick="javascript:informeGen(document.informes,'${pagActual}',document.formaPaginacion.regXPag.value,'${orderBy}');" type="button" value="Aceptar">
	</form>	
</center--%>
<br>



</body>
</html>

					<!--consultaRutinaPruebas.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="listaEventos" value="${requestScope.listaEventos}" />
<jsp:useBean id="msgCentral" class="java.lang.String" scope="request"/>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<jsp:useBean id="opcion" class="java.lang.String" scope="request"/>
<jsp:useBean id="offset" class="java.lang.String" scope="request"/>
<jsp:useBean id="regPorPagina" class="java.lang.String" scope="request"/>
<jsp:useBean id="pagActual" class="java.lang.String" scope="request"/>
<jsp:useBean id="totalPaginas" class="java.lang.String" scope="request"/>
<jsp:useBean id="orderBy" class="java.lang.String" scope="request"/>
<jsp:useBean id="cantidadTotalRegistros" class="java.lang.String" scope="request"/>
<jsp:useBean id="query" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Rutina de Pruebas por Evento</title>
<jsp:include page="../encabezado.jsp" flush="true" />
<!--ATENCION:
TENER MUCHO CUIDADO, SE ESTA HACIENDO UN SUBMIT CON EL FORM Y EL SERVLET
TOMA COMO VARIABLE OPERACION EL INPUT HIDDEN KE HAY MAS ABAJO, NO ESTA TOMANDO
EL VALOR DE OPERACION KE UNO LE DA POR URL!!!!!!
-->
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>

<script language="JavaScript">
<!--
addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");

	function verif_entradas(forma , order, regXpag, pagActual){
		
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
		location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=realizarConsultaRutinaPruebas&opcion="+opcion+"&fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&orderBy="+orderBy+"&regPorPagina="+regXpag+"&pagActual="+pagActual;
		return;
	}

	function Abre_ventanaDetalle(idprueba) {
	  v900=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=abrirVentanaDetalles&ticket="+idprueba, 'detallePruebaProg','scrollbars=yes,resizable=yes,hotkeys=yes,height=555,width=700,menubar=yes,toolbar=no');
	  v900.focus();
	}


	function irAPagina(pagina, cantidadRegistros, order){
	
		if(isNaN(cantidadRegistros)){
			alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
			document.formaPaginacion.regXPag.focus();
			return;
		}
		
		var query = '${query}';
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=realizarConsultaRutinaPruebas&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;
	}


	function eliminarTipoPrueba(transaccion,prueba){
		if(confirm("Esta Seguro de borrar la prueba "+prueba+" \ncon numero de Transaccion: "+transaccion+" ?")) {
			location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=eliminarTipoPrueba&tipoPrueba="+prueba+"&transaccion="+transaccion;
		}
	}

//-->
</script>

</head>

<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<form name="forma1">
<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
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
			<option value="todos"${opcion == 'todos'? 'selected' : ''}>Todos</option>
			<option value="CA"${opcion == 'CA'? 'selected' : ''}>Cable</option>
			<option value="CL"${opcion == 'CL'? 'selected' : ''}>Cliente</option>
			<option value="AR"${opcion == 'AR'? 'selected' : ''}>Armario</option>
		</select>
		<td align="left">
			<input type="text" class="texto" name="fechaIni" maxlength="10" size="10" value="${fIni}">
		<span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateIni', 5, 5)"> (aaaa-mm-dd)</a></span>
		<div id="calIni" style="position:relative; visibility: hidden;"></td>
	
		<td align=left>
			<input type="text" class="texto" name="fechaFin" maxlength="10" size="10" value="${fFin}">
			<span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateFin', 5, 5)">(aaaa-mm-dd)</a></span>
			<div id="calFin" style="position:relative; visibility: hidden;">
		</td>
		<td>
			<input type="button" class="boton" name="buscar" onClick="verif_entradas(document.forma1,'${orderBy}',document.formaPaginacion.regXPag.value,'${pagActual}')" value="Aceptar">
		</td>
  </tr>
</table>
</form>

<P></P>

<table width="100%" border="0" BGCOLOR="black" align="center">
<tr>
	<td align="center" class="mensajeCentral">${msgCentral} <tags:ayudas pagina="Pruebaporevento"/></td>
</tr>
</table>

<jsp:include page="paginacion.jsp" flush="true" />

<table width="100%" border="0" align="center">
<tr bgcolor="BLACK">
	<td width="90" height="30" align="center" class="header-reporte">
		
	<c:choose>
		<c:when test="${orderBy == 'transaccion DESC'}">
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'transaccion ASC')" title="Ordenar por TRANSACCION Ascendentemente" class="header-filtro">Transacci&oacute;n</a>
	 	</c:when>
	 	<c:otherwise>
			<a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'transaccion DESC')" title="Ordenar por TRANSACCION Descendentemente" class="header-filtro">Transacci&oacute;n</a>
		</c:otherwise>
	</c:choose>
		
	</td>
	<td width="*" height="30" align="center" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'tipoDePrueba DESC'}">
			<span title="Ordenar por TIPO DE PRUEBA Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipoDePrueba ASC')">Tipo de Prueba</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Ordenar por TIPO DE PRUEBA Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipoDePrueba DESC')">Tipo de Prueba</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
	<td width="110" height="30" align="center" class="header-reporte">
		
	<c:choose>
		<c:when test="${orderBy == 'numeroPruebas DESC'}">
			<span title="Ordenar por NUMERO DE PRUEBAS Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'numeroPruebas ASC')">Num. Pruebas</a></span>
		</c:when>
		<c:otherwise>
			<span title="Ordenar por NUMERO DE PRUEBAS Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'numeroPruebas DESC')">Num. Pruebas</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
	<td width="140" height="30" align="center" class="header-reporte">
	
	<c:choose>
		<c:when test="${orderBy == 'fechaIni DESC'}">
			<span title="Ordenar por FECHA INICIAL Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fechaIni ASC')">Fecha Inicial</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Ordenar por FECHA INICIAL Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fechaIni DESC')">Fecha Inicial</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
	<td width="140" height="30" align="center" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'fechaFin DESC'}">
			<span title="Ordenar por FECHA FINAL Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fechaFin ASC')">Fecha Final</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Ordenar por FECHA FINAL Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fechaFin DESC')">Fecha Final</a></span>
		</c:otherwise>
	</c:choose>
	</td>
	<td width="130" height="30" align="center" class="header-reporte">
		Duraci&oacute;n
	</td>	
</tr>
<c:set var="i" value="0" />
<c:forEach items="${listaEventos}" var="evento">
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}">
		<TD height="20" align="CENTER"><a name="Idprueba" title="Consultar Evento" href=javascript:Abre_ventanaDetalle('${evento.transaccion}');>${evento.transaccion}</a></td>
						
		<c:choose>
			<c:when test="${fn:startsWith(evento.tipoDePrueba,'CL')}">
				<TD height="20" align="CENTER" class="cliente${row}">
					<c:choose>
						<c:when test="${sessionScope.usuario.nivel == '1'}">
							${evento.tipoDePrueba}
						</c:when>
						<c:otherwise>
							<a href="javascript:eliminarTipoPrueba('${evento.transaccion}','${evento.tipoDePrueba}');">${evento.tipoDePrueba}</a>
						</c:otherwise>
					</c:choose>
				</td>
			</c:when>
			<c:when test="${fn:startsWith(evento.tipoDePrueba,'AR')}">
				<TD height="20" align="CENTER" class="armario${row}">
					
					<c:choose>
						<c:when test="${sessionScope.usuario.nivel == '1'}">
							${evento.tipoDePrueba}
						</c:when>
						<c:otherwise>
							<a href="javascript:eliminarTipoPrueba('${evento.transaccion}','${evento.tipoDePrueba}');">${evento.tipoDePrueba}</a>
						</c:otherwise>
					</c:choose>
				</td>
			</c:when>

			<c:otherwise>
				<TD height="20" align="CENTER">
					<c:choose>
						<c:when test="${sessionScope.usuario.nivel == '1'}">
							${evento.tipoDePrueba}
						</c:when>
						<c:otherwise>
							<a href="javascript:eliminarTipoPrueba('${evento.transaccion}','${evento.tipoDePrueba}');">${evento.tipoDePrueba}</a>
						</c:otherwise>
					</c:choose>
				</td>
			</c:otherwise>
		</c:choose>
		
		<TD height="20" align="CENTER">${evento.numeroPruebas}</td>
		<TD height="20" align="CENTER"><fmt:formatDate value= "${evento.fechaIni}" type="both" pattern="yy-MM-dd HH:mm:ss"/></td>
		<TD height="20" align="CENTER"><fmt:formatDate value= "${evento.fechaFin}" type="both" pattern="yy-MM-dd HH:mm:ss"/></td>
		<TD align="center">${evento.duracion}</td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>

</table>


<table width="100%" border="0" BGCOLOR="black" align="center">
<tr>
	<td class="mensajeCentral">Fin de reportes</td>
</tr>
</table>
</body>
</html>

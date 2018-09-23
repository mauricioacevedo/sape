			<!--  visitas.jsp  -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% java.util.List listaVisitas = (java.util.List) request.getAttribute("listaVisitas"); %>

<%-- Dejarlo para ejemplo % java.util.List listaEstados = (java.util.List) request.getAttribute("listaEstados"); --%>

<jsp:useBean id="user" class="java.lang.String" scope="request"/>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>


<html>
<head>
<title>Visitas - S A P E </title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"></script>
<script language="JavaScript"></script>



<script language="JavaScript">
<!--

addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");


	function direcciona(forma) {
		//var val1 = forma.fechaIni.value;
		//var val2 = forma.fechaFin.value;

		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}

		var user = forma.txtBuscar.value;

		var val1 = forma.fechaIni.value;
		var val2 = forma.fechaFin.value;


		/*if( user == '' ) {

			alert("Ingrese un Telefono!");
			return;
		}*/

		location.href="${pageContext.request.contextPath}/actionSape?accion=auditoria&operacion=inicio&fIni="+val1+"&fFin="+val2+"&user="+user;
		return;
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


		if (modo == '1'){
			v800=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=mostrarGraficaInicialTSTLI&tipo=1&fechaIni="+val1+"&fechaFin="+val2, 'window800','scrollbars=yes,hotkeys=yes,height=600,width=800,left=100,resizable=yes,top=50,menubar=yes,toolbar=no');
			v800.focus();
		} else if (modo == '2') {
			v900=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=mostrarGraficaInicialTSTLI&tipo=2&fechaIni="+val1+"&fechaFin="+val2, 'window900','scrollbars=yes,hotkeys=yes,height=600,width=800,left=100,resizable=yes,top=50,menubar=yes,toolbar=no');
			v900.focus();
		}
	}

//-->
</script>

</head>
<script language="JavaScript" src="javascript/varios.js"> </script>
<jsp:include page="../../encabezado.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">


<form name="forma1" method="get">

<table width="100%" cellspacing=0 cellpadding=0 border=0 bgcolor="white" align="center">
  <tr>
  <td>&nbsp;&nbsp;&nbsp;</td>
  <td align=left class="header-filtro">
     BUSCAR USUARIO:
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
	&nbsp;&nbsp;<input type="text" name="txtBuscar" size="10" value="${user}">
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
	 	<%--tags:ayudas pagina="Estatelefono"/--%></td>
  </tr>
</table>
</form>
<br>
<br>

<table width="60%" align="center">
<tr bgcolor="Black">
	<td colspan="5" align="center" class="mensajeCentral">Visitas al Sistema SAPE</td>
</tr>
	<tr bgcolor="Black">

		<td align="center" class="header-reporte">Usuario</td>
		<td align="center" class="header-reporte">Fecha de Ingreso</td>
		<td align="center" class="header-reporte">Ip</td>
		<td align="center" class="header-reporte">Fecha de Salida</td>
		<td align="center" class="header-reporte">Duracion</td>
	</tr>
<c:set var="i" value="0" />
<c:forEach items="${listaVisitas}" var="visita">
	<tr class="row${i%2 == 0? 0 : 1}">

		<td align="center">${visita.usuario}</td>
		<td align="center">
			<fmt:formatDate value="${visita.fechaIngreso}" type="both" pattern="yy-MM-dd HH:mm:ss" />
		</td>
		<td align="center">${visita.ip}</td>
		<td align="center">
			<fmt:formatDate value="${visita.fechaSalida}" type="both" pattern="yy-MM-dd HH:mm:ss" />
		</td>
		<td align="center">${visita.duracion}</td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>
<tr bgcolor="Black">
	<td colspan="5" align="left" class="mensajeCentral">Total de Registros ${fn:length(listaVisitas)} .</td>
</tr>
</table>


<br><br>

</body>
</html>

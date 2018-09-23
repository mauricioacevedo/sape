<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<jsp:useBean id="lista" class="java.util.ArrayList" scope="request"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>S A P E - Calificacion de pares por mes</title>

</head>
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">
<!--
addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");

	function verif_entradas(forma) {

		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}
		var fIni = forma.fechaIni.value;
		var fFin = forma.fechaFin.value;
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=mensualCalificacionRutinas&fIni="+fIni+"&fFin="+fFin;
	}


-->
</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
<!-- INICIO DE LA FORMA DE BUSQUEDA -->

<form name="forma1">
<table width="100%" cellspacing=0 cellpadding=0 border=0 align="center">
	<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td align="left" class="header-filtro">DESDE : </td>
		<td align="left" class="header-filtro">HASTA : </td>
	</tr>
	<tr>
		<td>&nbsp;</td>

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
			<input type="button" class="boton" name="buscar" onclick="verif_entradas(document.forma1)" value="Aceptar">
		</td>
  </tr>
</table>
</form>

<P></P>


<table align="center" width="100%">

<tr bgcolor="BLACK">
	<td align="center" class="header-reporte">Mes</td>
	<td align="center" class="header-reporte">Buenos para Datos</td>
	<td align="center" class="header-reporte">Malos para Datos</td>
	<td align="center" class="header-reporte">Regulares para Datos</td>
	<td align="center" class="header-reporte">No probados</td>
	<td align="center" class="header-reporte">Total Pruebas</td>
</tr>
 
<c:set var="i" value="0" />
<c:forEach items="${lista}" var="detalle">
<c:set var="row" value="${i%2 == 0? 0 : 1}" />
<tr class="row${row}">

	<td align="center">
	<fmt:setLocale value="ES_es" scope="session"/>
	<fmt:formatDate value= "${detalle[0]}" type="date" pattern="MMMM - yyyy" />

	</td>
	<td align="center">${detalle[1]}</td>
	<td align="center">${detalle[2]}</td>
	<td align="center">${detalle[3]}</td>
	<td align="center">${detalle[4]}</td>
	<td align="center">${detalle[5]}</td>
</tr>
<c:set var="i" value="${i + 1}" />
</c:forEach>
<tr bgcolor="BLACK">
<td class="fin-reporte" colspan="9">Total ${fn:length(lista)} Registros.</td>
</tr>
</table>
</table>

</body>
</html>
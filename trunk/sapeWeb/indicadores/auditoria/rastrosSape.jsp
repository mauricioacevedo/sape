			<!--  rastrosSape.jsp  -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listaRastros" value="${requestScope.listaRastros}" />
<jsp:useBean id="user" class="java.lang.String" scope="request"/>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<jsp:useBean id="tipo" class="java.lang.String" scope="request"/>

<html>
<head>
<title>SAPE - Rastros del Sistema</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"></script>
<script language="JavaScript">
<!--

addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");


	function direcciona(forma) {
		//var val1 = forma.fechaIni.value;
		//var val2 = forma.fechaFin.value;
		var combo = forma.comboTipos;
		
		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}
		
		var val1 = forma.fechaIni.value;
		var val2 = forma.fechaFin.value;

		var user = forma.txtBuscar.value;

		var tipo = combo.options[combo.selectedIndex].value;

		location.href="${pageContext.request.contextPath}/actionSape?accion=auditoria&operacion=rastrosSape&fIni="+val1+"&fFin="+val2+"&user="+user+"&tipo="+tipo;
		return;
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
     USUARIO:
  </td>
   <td align=left class="header-filtro">
        DESDE:
    </td>
    <td align=left class="header-filtro"s>
        HASTA:
    </td>
    <td align=left class="header-filtro"s>
        TIPO:
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
	<td align="left">
        <select name="comboTipos">
        	<option value="todos" ${tipo == '' or tipo == 'todos' or empty tipo?'selected':''}>Todos</option>
        	<option value="1" ${tipo == '1'?'selected':''}>Login Fallidos</option>
        	<option value="2" ${tipo == '2'?'selected':''}>Cambio en Pantalla</option>
        	<option value="3" ${tipo == '3'?'selected':''}>Consultas Paginas</option>
        	<option value="4" ${tipo == '4'?'selected':''}>Registros Eliminados</option>
        </select>
    </td>
	 
	 	<td>
		<input class="boton" type="button" name="buscar" onClick="direcciona(document.forma1)" value="Aceptar">
	 	<%--tags:ayudas pagina="Estatelefono"/--%></td>
	 
  </tr>
</table>
</form>
<br>
<br>

<table width="80%" align="center">
<tr bgcolor="Black">
	<td colspan="4" align="center" class="mensajeCentral">Rastros del Sistema SAPE</td>
</tr>
	<tr bgcolor="Black">

		<td align="center" class="header-reporte">Usuario</td>
		<td align="center" class="header-reporte">Fecha</td>
		<td align="center" class="header-reporte">Tipo Interaccion</td>
		<td align="center" class="header-reporte">Descripcion</td>
	</tr>
<c:set var="i" value="0" />
<c:forEach items="${listaRastros}" var="rastro">
	<tr class="row${i%2 == 0? 0 : 1}">

		<td align="center">${rastro.usuario}</td>
		<td align="center">
			<fmt:formatDate value="${rastro.fecha}" type="both" pattern="yy-MM-dd HH:mm:ss" />
		</td>
		<td align="center">
		<c:choose>
			<c:when test="${rastro.tipo == '1'}">Login Fallido</c:when>
			<c:when test="${rastro.tipo == '2'}">Cambio</c:when>
			<c:when test="${rastro.tipo == '3'}">Consulta</c:when>
			<c:when test="${rastro.tipo == '4'}">Eliminaci&oacute;n</c:when>
			<c:otherwise>${rastro.tipo}</c:otherwise>
		</c:choose>
		</td>
		<td align="center">${rastro.descripcion}</td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>
<tr bgcolor="Black">
	<td colspan="4" align="left" class="mensajeCentral">Total de Registros ${fn:length(listaRastros)}.</td>
</tr>
</table>

<br>
</body>
</html>

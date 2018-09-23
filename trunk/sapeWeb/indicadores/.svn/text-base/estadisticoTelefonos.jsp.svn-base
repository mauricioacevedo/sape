			<!--  estadisticoTelefonos.jsp  -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listaCodigos" value="${requestScope.listaCodigos}" />
<jsp:useBean id="listaPrimeraUltima" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="listaClientes" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="listaEstados" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="telefono" class="java.lang.String" scope="request"/>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Reporte Por Telefono</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">
<!--
addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");


	function direcciona(forma) {
		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}

		var telefono = forma.txtBuscar.value;
		if( telefono == '' ) {
			alert("Ingrese un Telefono!");
			return;
		}

		var val1 = forma.fechaIni.value;
		var val2 = forma.fechaFin.value;

		location.href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=estadisticaTelefonos&fIni="+val1+"&fFin="+val2+"&telefono="+telefono;
		return;
	}

//-->
</script>

</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body bgcolor="white">

<form name="forma1" method="get" action="">

<table width="100%" cellspacing=0 cellpadding=0 border=0 bgcolor="white" align="center">
  <tr>
  <td>&nbsp;&nbsp;&nbsp;</td>
  <td align=left class="header-filtro">
     BUSCAR TELEFONO:
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
	&nbsp;&nbsp;<input type="text" name="txtBuscar" size="10" value="${telefono}">
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
	 	<tags:ayudas pagina="Estatelefono"/></td>
  </tr>
</table>
</form>
<br>
	<c:set var="totalRegistros" value="0"/>

	<c:forEach items="${listaEstados}" var="estado">
		<c:set var="totalRegistros" value="${totalRegistros +estado[0]}"/>
	</c:forEach>

<table width="60%" align="center">
<tr bgcolor="Black">
	<td colspan="6" align="center"><font color="White"><b>Resultados para el telefono <font color="red">${telefono}</font>. ${totalRegistros} pruebas.</b></font></td>
</tr>
<tr bgcolor="white">
	<c:forEach items="${listaEstados}" var="estado">
		<td align="center">${estado[1]} : ${estado[0]}</td>
	</c:forEach>
</tr>
</table>

<br><br>

<table width="60%" align="center">
<tr bgcolor="Black">
	<td colspan="3" align="center"><font color="White"><b>Primer y ultimo registro.</b></font></td>
</tr>
	<tr bgcolor="Black">

		<td align="center" class="header-reporte">Prueba</td>
		<td align="center" class="header-reporte">Codigo Ver</td>
		<td align="center" class="header-reporte">Fecha</td>

	</tr>
<c:set var="i" value="0" />
<c:forEach items="${listaPrimeraUltima}" var="cliente">
	<tr class="row${i%2 == 0? 0 : 1}">

		<td align="center"><a href="javascript:Abre_ventanaDetallePrueba('${cliente[0]}');">${cliente[0]}</a></td>
		<td align="center">${cliente[1]}</td>

		<td align="center">
			<fmt:formatDate value="${cliente[2]}" type="both" pattern="yy-MM-dd HH:mm:ss" />
		</td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>
</table>


<br><br>

<table width="60%" align="center">
<tr bgcolor="Black">
	<td colspan="6" align="center"><font color="White"><b>Clientes que probaron el numero</b></font></td>
</tr>
<tr bgcolor="black">
	<td align="center" class="header-reporte">Cliente</td>
	<td align="center" class="header-reporte">Cantidad</td>
	<td align="center" class="header-reporte">Estado de la Prueba</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaClientes}" var="cliente">
	<tr class="row${i%2 == 0? 0 : 1}">

		<td align="center">${cliente[1]}</td>
		<td align="center">${cliente[0]}</td>
		<td align="center">${cliente[2]}</td>

	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>

</table>

<br><br>

<table width="60%" align="center">
<tr bgcolor="Black">
	<td colspan="6" align="center"><font color="White"><b>Relacion de Codigos Ver</b></font></td>
</tr>
<tr bgcolor="black">
	<td align="center" class="header-reporte">Codigo Ver</td>
	<td align="center" class="header-reporte">Cantidad</td>
	<td align="center" class="header-reporte">Descripcion</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaCodigos}" var="codigo">
	<tr class="row${i%2 == 0? 0 : 1}">

		<td align="center">${codigo[1]}</td>
		<td align="center">${codigo[0]}</td>
		<td align="center">${codigo[2]}</td>

	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>

</table>

</body>
</html>

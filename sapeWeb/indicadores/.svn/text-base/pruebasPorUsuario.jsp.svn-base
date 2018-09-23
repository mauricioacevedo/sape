			<!-- pruebasPorUsuario.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<% java.util.List listaEventos = (java.util.List) request.getAttribute("listaEventos"); %>
<jsp:useBean id="telefono" class="java.lang.String" scope="request"/>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<jsp:useBean id="orderBy" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Estadistico Por Usuarios</title>

</head>
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">
	addCalendar("DateIni", "calIni", "fechaIni", "frmBuscar");
	addCalendar("DateFin", "calFin", "fechaFin", "frmBuscar");
</script>

<script language="JavaScript">

	function enviar(forma,orderBy) {
		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}

		var fIni = forma.fechaIni.value;
		var fFin = forma.fechaFin.value;
		var user = forma.txtBuscar.value;

		location.href = "${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=estadisticaUsuarios&fIni="+fIni+"&fFin="+fFin+"&usuario="+user+"&orderBy="+orderBy;
	}

	function estadisticoHoras(id) {

		var forma = document.frmBuscar;

		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}

		var fIni = forma.fechaIni.value;
		var fFin = forma.fechaFin.value;

		var winUser=window.open("${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=ventanaEstadisticaUsuario&fIni="+fIni+"&fFin="+fFin+"&usuario="+id, 'window900','scrollbars=yes,resizable=yes,hotkeys=yes,height=600,width=780,left=100,top=50,menubar=yes,toolbar=no');
		winUser.focus();

	}

	function pruebasUsuario(usuario) {

		var forma = document.frmBuscar;
		
		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}

		var fIni = forma.fechaIni.value;
		var fFin = forma.fechaFin.value;

		v900=window.open("${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=detallePruebas&fechaIni="+forma.fechaIni.value+"&fechaFin="+forma.fechaFin.value+"&usuario="+usuario, 'indicadoresUsuario','scrollbars=yes,resizable=yes,hotkeys=yes,height=555,width=750,left=100,top=50,menubar=yes,toolbar=no');
		v900.focus();
	}

</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
<jsp:include page="../encabezado.jsp" flush="true" />


<form name="frmBuscar">

<table width="100%" cellspacing=0 cellpadding=0 border=0 bgcolor="white" align="center">


  <tr>
  <td>&nbsp;&nbsp;&nbsp;</td>
  <td align=left class="header-filtro">
     BUSCAR USUARIO:
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
	&nbsp;&nbsp;<input class="texto" type="text" name="txtBuscar" size="10" value="${telefono}">
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
		<input class="boton" type="button" name="buscar" onClick="enviar(document.frmBuscar,'${orderBy}');" value="Aceptar">
	</td>
  </tr>
</table>
</form>

<br>

<table width="50%" border="0" align="center">
	<tr bgcolor="black">
		<td align="center" class="mensajeCentral"  height="25" colspan="8">Estadistico de Usuarios. ${fn:length(listaEventos)} usuarios.<tags:ayudas pagina="Estausuario"/></td>
	</tr>
<tr class="header-reporte">
	<td align="center" rowspan="2">
	<c:set var="order" value="${orderBy == 'cliente ASC'?'cliente DESC':'cliente ASC'}" />
	<a href="javascript:enviar(document.frmBuscar,'${order}');">Usuario</a>
	</td>
	<td align="center" colspan="4">Estados</td>
	<td align="center" rowspan="2">Total</td>
	<td align="center" colspan="2">Efectividad(Exito+Adv.)</td>
</tr>
<tr class="header-reporte">
	<td align="center">Exito</td>
	<td align="center">Fallidas</td>
	<td align="center">Advertencia</td>
	<td align="center">Inesperados</td>
	<td align="center">Cant</td>
	<td align="center">%</td>
</tr>
<c:set var="i" value="0" />

<c:set var="exito" value="0" />
<c:set var="fracaso" value="0" />
<c:set var="advertencia" value="0" />
<c:set var="inesperados" value="0" />
<c:set var="efectivida" value="0" />
<c:set var="total" value="0" />


<c:forEach items="${listaEventos}" var="evento">
	<tr class="row${i%2 == 0? 0 : 1}">
		<td height="20" align="center"><a href="javascript:estadisticoHoras('${evento[0]}');" title="Grafica por Horas">${evento[0]}</a></td>
		<td align="center">${evento[1]}</td><c:set var="exito" value="${exito + evento[1]}" />
		<td align="center">${evento[2]}</td><c:set var="fracaso" value="${fracaso + evento[2]}" />
		<td align="center">${evento[3]}</td><c:set var="advertencia" value="${advertencia + evento[3]}"/>
		<td align="center">${evento[4]}</td><c:set var="inesperados" value="${inesperados + evento[4]}" />
		<td align="center"><a href="javascript:pruebasUsuario('${evento[0]}')" title="Pruebas del Usuario">${evento[5]}</a></td><c:set var="total" value="${total + evento[5]}" />
		<td align="center">${evento[1]+evento[3]}</td><c:set var="efectivida" value="${efectivida + evento[1]+evento[3]}" />
		<td align="center"><fmt:formatNumber value="${(evento[1]+evento[3])/evento[5]}" type="percent" pattern="##.###%"/></td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>
<tr class="fin-reporte">
	<td height="20" align="center">TOTALES</td>
	<td align="center">${exito}</td>
	<td align="center">${fracaso}</td>
	<td align="center">${advertencia}</td>
	<td align="center">${inesperados}</td>
	<td align="center">${total}</td>
	<td align="center">${efectivida}</td>
	<td align="center"><fmt:formatNumber value="${efectivida/total}" type="percent" pattern="##.###%"/></td>
</tr>
</table>

<br>

</body>
</html>

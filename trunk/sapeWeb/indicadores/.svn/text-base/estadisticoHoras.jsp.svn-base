<!-- estadisticoHoras.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="lista" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Estadistico Por Horas</title>
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">

	addCalendar("DateIni", "calIni", "fechaIni", "forma1");
	addCalendar("DateFin", "calFin", "fechaFin", "forma1");

	function enviarInfo(forma) {

		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}

		var fIni = forma.fechaIni.value;
		var fFin = forma.fechaFin.value;

		location.href = "${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=estadisticaHoras&fIni="+fIni+"&fFin="+fFin;
	}

</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
</head>
<body bgcolor="white">
<jsp:include page="../encabezado.jsp" flush="true" />


<form name="forma1">
<table width="100%" cellspacing=0 cellpadding=0 border=0 align="center">
  <tr>
    <td>&nbsp;&nbsp;&nbsp;</td>
    <td align="left" class="header-filtro">
        DESDE : &nbsp;<input class="texto" type="text" name="fechaIni" maxlength="10" size="10" value="${fIni}">
        <span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateIni', 5, 5)"> (aaaa-mm-dd)</a></span>
        <div id="calIni" style="position:relative; visibility: hidden;">
    </td>
    <td align="left" class="header-filtro">
        HASTA : &nbsp;<input class="texto" type="text" name="fechaFin" maxlength="10" size="10" value="${fFin}">
        <span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateFin', 5, 5)">(aaaa-mm-dd)</a></span>
        <div id="calFin" style="position:relative; visibility: hidden;">
    </td>
	<td>
		<input class="boton" type="button" name="buscar" onClick="enviarInfo(document.forma1);" value="Aceptar"><tags:ayudas pagina="Estahora"/>
	</td>
  </tr>
</table>
</form>

<br>

<table width="50%" border="0" align="center">
	<tr bgcolor="black">
		<td align="center" class="mensajeCentral"  height="25" colspan="8">Estadistico por Horas<br>
	    </td>
	</tr>
<tr bgcolor="black">
<td class="header-reporte" align="center" rowspan="2">Hora</td>
<td class="header-reporte" align="center" colspan="4">Estados</td>
<td class="header-reporte" align="center" rowspan="2">Cantidad</td>
<td class="header-reporte" align="center" rowspan="2">Efectividad<br>(Exito+Adver)</td>
<td class="header-reporte" align="center" rowspan="2">%</td>
</tr>
<tr bgcolor="Black">
<td align="center" class="header-reporte">Exito</td>
<td align="center" class="header-reporte">Fallidas</td>
<td align="center" class="header-reporte">Advertencia</td>
<td align="center" class="header-reporte">Inesperados</td>
</tr>

<c:set var="exito" value="0" />
<c:set var="fallidas" value="0" />
<c:set var="advertencia" value="0" />
<c:set var="inesperados" value="0" />
<c:set var="efectivida" value="0" />
<c:set var="totalRegistros" value="0" />

<c:set var="i" value="0" />

<c:set var="datos" value="Grafica por Horas*Hora*Cantidad" scope="request"/>

<c:forEach items="${lista}" var="evento">
	<tr class="row${i%2 == 0? 0 : 1}">
		<TD height="20" align="CENTER">${evento[0]}</td>
		<td align="center">${evento[1]}${empty evento[1]?'0':''}</td>
		<td align="center">${evento[2]}${empty evento[2]?'0':''}</td>
		<td align="center">${evento[3]}${empty evento[3]?'0':''}</td>
		<td align="center">${evento[4]}${empty evento[4]?'0':''}</td>
		<c:set var="total" value="${evento[1]+evento[2]+evento[3]+evento[4]}" />
		<TD align="CENTER">${total}</td>
		<td align="center">${evento[1]+evento[3]}</td>
		<c:set var="datos" value="${datos}*${evento[0]}*${total}" scope="request"/>
		<td align="center"><fmt:formatNumber value="${(evento[1]+evento[3])/total}" type="percent" pattern="##.###%"/></td>

		<c:set var="exito" value="${exito + evento[1]}" />
		<c:set var="fallidas" value="${fallidas + evento[2]}" />
		<c:set var="advertencia" value="${advertencia + evento[3]}" />
		<c:set var="inesperados" value="${inesperados + evento[4]}" />
		<c:set var="efectivida" value="${efectivida + evento[1]+evento[3]}" />
		<c:set var="totalRegistros" value="${totalRegistros + total}" />

	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>

<tr class="fin-reporte">
		<td height="20" align="CENTER">TOTALES</td>
		<td align="CENTER">${exito}</td>
		<td align="CENTER">${fallidas}</td>
		<td align="CENTER">${advertencia}</td>
		<td align="CENTER">${inesperados}</td>
		<td align="CENTER">${totalRegistros}</td>
		<TD align="CENTER">${efectivida}</td>
		<td align="center"><fmt:formatNumber value="${efectivida/totalRegistros}" type="percent" pattern="##.###%"/></td>
</tr>

</table>

<br><br>
<table align="center" width="20%" height="20%">
<tr>
<td width="100%" height="100%">
<c:set var="noBoton" value="sin_boton_es_mejor" scope="request"/>
<jsp:include page="../reportes/graficaEfectividadPruebas.jsp" flush="true" />
</td>
</tr>

</table>

</body>
</html>

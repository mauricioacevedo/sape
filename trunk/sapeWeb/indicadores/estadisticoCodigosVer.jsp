					<!-- estadisticoCodigosVer.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<jsp:useBean id="lista" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<jsp:useBean id="central" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Estadistico Por Codigos Ver</title>

<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">

	addCalendar("DateIni", "calIni", "fechaIni", "forma1");
	addCalendar("DateFin", "calFin", "fechaFin", "forma1");


	function mostrarPruebasCodv(valorCodv) {

		var forma = document.forma1;

		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}

		var fIni = forma.fechaIni.value;
		var fFin = forma.fechaFin.value;

		var reporteCodv=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=realizarConsultaPrueba&opcion=${empty central?"codv":"codvCentral"}&valor=" + valorCodv + "${empty central?"":"*"}${central}&fechaIni=" + fIni+"&fechaFin="+fFin+"&modoReporte=popup",'reporteCodv','scrollbars=yes,resizable=yes,hotkeys=yes,height=550,width=700,left=100,top=50,menubar=yes,toolbar=no');
		reporteCodv.focus();
		return;
	}



	function enviar1(forma) {

		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}
		
		var fIni = forma.fechaIni.value;
		var fFin = forma.fechaFin.value;

		location.href = "${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=estadisticaCodigosVer&fIni="+fIni+"&fFin="+fFin;
	}

</script>
</head>
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<jsp:include page="../encabezado.jsp" flush="true" />


<form name="forma1">

<table width="100%" cellspacing=0 cellpadding=0 border=0 bgcolor="white" align="center">

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
		<input class="boton" type="button" name="buscar" onClick="enviar1(document.forma1);" value="Aceptar"><tags:ayudas pagina="Estacodigover"/>
	</td>
  </tr>
</table>
</form>

<br>

<table width="50%" border="0" align="center">
	<tr bgcolor="black">
		<td align="center" class="mensajeCentral"  height="25" colspan="7">Estadistico por Codigos Ver <c:if test="${not empty central}">( Central ${central} )</c:if></td>
	</tr>
<tr>
<td class="header-reporte" align="center" bgcolor="black">Codigo Ver</td>
<td class="header-reporte" align="center" bgcolor="black">Descripci&oacute;n</td>
<td class="header-reporte" align="center" bgcolor="black">Cantidad</td>
</tr>

<c:set var="i" value="0" />
<c:set var="total" value="0" />
<c:set var="datos" value="*Codigo Ver*Cantidad" scope="request"/>

<c:forEach items="${lista}" var="evento">
	<tr class="row${i%2 == 0? 0 : 1}">
		<TD align="CENTER">${empty evento[0]?'NULL':evento[0]}</td>
		<td align="center">${evento[1]}</td>
		<td align="center">
		<c:choose>
		<c:when test="${empty evento[0]}">
			${evento[2]}
		</c:when>
		<c:otherwise>
		<a href="javascript:mostrarPruebasCodv('${evento[0]}');">${evento[2]}</a>
		</c:otherwise>
		</c:choose>
		</td>
		<c:set var="total" value="${total + evento[2]}" />
		<c:set var="datos" value="${datos}*${empty evento[0]?'NULL':evento[0]}*${evento[2]}" scope="request"/>
		
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>

<tr class="fin-reporte">
	<td colspan="2" bgcolor="Black" align="center">TOTAL</td>
	<td align="center" bgcolor="Black">${total}</td>
</tr>

<c:set var="datos" value="Grafica de Codigos Ver. ${total} Registros.${datos}" scope="request"/>

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

			<!-- estadisticoTecnologia.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="listaVistas" value="${requestScope.listaVistas}" />
<c:set var="fIni" value="${requestScope.fIni}" />
<c:set var="fFin" value="${requestScope.fFin}" />
<%--jsp:useBean id="query" class="java.lang.String" scope="request"/--%>

<html>
<head>
<title>Estadistico por Tecnologia - S A P E</title>
</head>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="javascript">
	addCalendar("DateIni", "calIni", "fechaIni", "forma1");
	addCalendar("DateFin", "calFin", "fechaFin", "forma1");

	function enviar1(forma) {

		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}

		var fIni = forma.fechaIni.value;
		var fFin = forma.fechaFin.value;

		location.href = "${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=indicadoresTecnologia&fIni="+fIni+"&fFin="+fFin;
	}
	
	function mostrarGrafica(tec){
		var datos = document.getElementById('datosGrafica'+tec).value;
		abrirGrafica(datos);
	}
	
	function estadisticoCodv(tech){
		var forma = document.forma1;

		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}

		var fIni = forma.fechaIni.value;
		var fFin = forma.fechaFin.value;
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=estadisticaCodigosVer&fIni="+fIni+"&fFin="+fFin+"&tech="+tech;
	}
	
	
	
</script>

<jsp:include page="../encabezado.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white">
<br>
<br>
<table align="center" width="60%">
<tr>
<td colspan="8" align="left">
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
</td>
</tr>

<tr>
<td colspan="8" class="mensajeCentral" align="center">Indicadores Por Tecnologia</td>
</tr>


<tr class="header-reporte">
	<td align="center" rowspan="2">Tecnologia</td>
	<td align="center" colspan="4">Estados</td>
	<td align="center" rowspan="2" class="mensajeCentral" bgcolor="black">Total</td>
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

<c:set var="exito" value="0" />
<c:set var="fracaso" value="0" />
<c:set var="advertencia" value="0" />
<c:set var="inesperados" value="0" />
<c:set var="efectivida" value="0" />
<c:set var="total" value="0" />
<c:set var="subtotal" value="0" />
<c:set var="subefec" value="0" />

<!-- tr bgcolor="black">
	<td align="center" width="20%" class="header-reporte">Nombre</td>
	<td align="center" width="20%" class="header-reporte">Exito</td>
	<td align="center" width="20%" class="header-reporte">Fallo</td>
	<td align="center" width="20%" class="header-reporte">Advertencia</td>
	<td align="center" width="20%" class="header-reporte">Inesperados</td>
</tr-->
<c:set var="i" value="0" />
<c:forEach items="${listaVistas}" var="view">
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}">
		<c:set var="subtotal" value="${view.exitos+view.fallas+view.advertencias+view.inesperados}" />
		<c:set var="subefec" value="${view.exitos+view.advertencias}" />
		<td align="center" width="20%">&nbsp;<a href="javascript:estadisticoCodv('${view.nombre}');">${view.nombre}</a></td>
		<td align="center">&nbsp;${view.exitos}</td><c:set var="exito" value="${exito + view.exitos}" />
		<td align="center">&nbsp;${view.fallas}</td><c:set var="fracaso" value="${fracaso + view.fallas}" />
		<td align="center">&nbsp;${view.advertencias}</td><c:set var="advertencia" value="${advertencia + view.advertencias}"/>
		<td align="center">&nbsp;${view.inesperados}</td><c:set var="inesperados" value="${inesperados + view.inesperados}" />
		<td align="center">&nbsp;${subtotal}</td><c:set var="total" value="${total + subtotal}" />
		<td align="center">&nbsp;${subefec}</td><c:set var="efectivida" value="${efectivida + subefec}" />
		<c:set var="datosGrafica" value="Efectividad por Tecnologia ${view.nombre}.* *Valor*Exito*${view.exitos}*Fallo*${view.fallas}*Advertencia*${view.advertencias}*Inesperados*${view.inesperados}" />
		<input type="hidden" id="datosGrafica${i}" value="${datosGrafica}">
		<td align="center">&nbsp;<a href="javascript:mostrarGrafica('${i}');"><fmt:formatNumber value="${subefec/subtotal}" type="percent" pattern="##.###%"/></a></td>
		<!-- td align="center" width="15%"><a name="modificarCodigoVer" href="javascript:modificar('${codvetb.codvSAPE}','${codvetb.codvETB}')">Modificar</a> - <a name="eliminarCodigoVer" href=javascript:eliminarCodigoVer("${codvetb.codvSAPE}","${codvetb.codvETB}");>Eliminar</a></font></td-->
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
	<c:set var="datosGrafica" value="Efectividad por Tecnologia TOTAL.* *Valor*Exito*${exito}*Fallo*${fracaso}*Advertencia*${advertencia}*Inesperados*${inesperados}" />
	<input type="hidden" id="datosGrafica${i}" value="${datosGrafica}">
	<td align="center" class="linkBlanco"><a href="javascript:mostrarGrafica('${i}');" class="linkBlanco"><fmt:formatNumber value="${efectivida/total}" type="percent" pattern="##.###%"/></a></td>
</tr>

</table>

</body>
</html>
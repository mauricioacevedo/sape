				<!--consultaEfectividadPrueba.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="listaCentrales" value="${requestScope.listaCentrales}" />
<jsp:useBean id="msgCentral" class="java.lang.String" scope="request"/>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<jsp:useBean id="cantidadTotalRegistros" class="java.lang.String" scope="request"/>
<jsp:useBean id="A" class="java.lang.String" scope="request"/>
<jsp:useBean id="E" class="java.lang.String" scope="request"/>
<jsp:useBean id="F" class="java.lang.String" scope="request"/>
<jsp:useBean id="N" class="java.lang.String" scope="request"/>
<jsp:useBean id="tiempoPromedio" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Efectividad de Prueba</title>
<jsp:include page="../encabezado.jsp" flush="true" />

<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>
<style type="text/css">
.header-alternativo {background-color: BLACK; color: WHITE}
.exito0 {background-color: #c4c4c4}
/*.exito1 {background-color: inherits}*/
.fallo0 {background-color: #e4e4e4}
/*.fallo1 {background-color: inherits}*/
.advertencia0 {background-color: #c4c4c4}
/*.advertencia1 {background-color: inherits}*/
.inesperado0 {background-color: #e4e4e4}
/*.inesperado1 {background-color: inherits}*/
.efectividad0 {background-color: #c4c4c4}
/*.efectividad1 {background-color: inherits}*/

.statusEfectividad {background-color: #ff5900; color: WHITE}

.statusAdvertencia {background-color: #ff5900; color: WHITE}

.total-reporte {background-color: #FFaa00; color: black; font-weight: normal; }
</style>
<script language="JavaScript">
<!--
addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");


function enviar2( tipo, forma){

		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}

		v900=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=abrirVentanaEfectividad&opcion="+tipo+"&fechaIni="+forma.fechaIni.value+"&fechaFin="+forma.fechaFin.value, 'window900','scrollbars=yes,resizable=yes,hotkeys=yes,height=555,width=750,left=100,top=50,menubar=yes,toolbar=no');
		v900.focus();
	}


	function mostrarGrafica(){
		var datos= 'Grafica de Efectividad, ${cantidadTotalRegistros} registros. * *Valor*Exito*${E}*Fracaso*${F}*Advertencia*${A}*Inesperados*${N}';
		v900=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=mostrarGraficaEfectividad&datos="+datos, 'window900','scrollbars=yes,resizable=yes,hotkeys=yes,height=600,width=800,left=100,top=50,menubar=yes,toolbar=no');
		v900.focus();

	}

	function enviar(forma){

		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}
		location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=inicioEfectividadPrueba&fechaIni="+forma.fechaIni.value+"&fechaFin="+forma.fechaFin.value;

	}

	function informeGen(forma){

		var val = forma.formatos.options[forma.formatos.selectedIndex].value;
		
		if (!validarCamposRangosFechas(document.forma1.fechaIni, document.forma1.fechaFin)) {
			return;
		}
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=inicioEfectividadPrueba&fechaIni="+document.forma1.fechaIni.value+"&fechaFin="+document.forma1.fechaFin.value+"&exportar=yes&pantalla=efectividadPrueba&formato="+val;
	}

//-->
</script>

</head>

<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<form name="forma1">
<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
	<tr>
		<td>&nbsp;</td>
		<td align="left" class="header-filtro" colspan="2">DESDE : </td>
		<td align="left" class="header-filtro">HASTA : </td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="left" colspan="2">
			<input type="text" class="texto" name="fechaIni" maxlength="10" size="10" value="${fIni}">
		<span class="calendario"><a href="javascript:showCal('DateIni', 5, 5)" title="Click Para Abrir El Calendario" class="calendario"> (aaaa-mm-dd)</a></span>
		<div id="calIni" style="position:relative; visibility: hidden;"></td>

		<td align=left>
			<input type="text" class="texto" name="fechaFin" maxlength="10" size="10" value="${fFin}">
			<span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateFin', 5, 5)">(aaaa-mm-dd)</a></span>
			<div id="calFin" style="position:relative; visibility: hidden;">
		</td>
		<td>
			<input type="button" class="boton" name="buscar" onClick="enviar(document.forma1)" value="Aceptar">
		</td>
  </tr>
</table>
</form>

<P></P>

<table width="100%" border="0" BGCOLOR="black" align="center">
<tr>
	<td align="center" class="mensajeCentral">${msgCentral}<tags:ayudas pagina="Efectividadprueba"/></td>
</tr>
</table>

<br>
<table width="100%" align="center">

	<tr class="header-reporte">
		<td rowspan="2" width="*" height="23" align="center" class="header-alternativo">SITE</td>
		<td rowspan="2" width="80" height="23" align="center">TOTAL</td>
		<td colspan="2" width="110" height="23" align="center" class="header-alternativo">EXITO</td>
		<td colspan="2" width="110" height="23" align="center">FALLO</td>
		<td colspan="2" width="110" height="23" align="center" class="header-alternativo">ADVERTENCIA</td>
		<td colspan="2" width="110" height="23" align="center">INESPERADO</td>
		<td colspan="2" width="140" height="23" align="center" class="header-alternativo">EFECTIVIDAD</td>
		<td rowspan="2" width="80" height="23" align="center">TIEMPO PROMEDIO</td>
	</tr>

	<tr class="header-reporte">
		<td height="23" width="60" align="center">Cant</td>
		<td height="23" align="center">%</td>
		<td height="23" width="60" align="center">Cant</td>
		<td height="23" align="center">%</td>
		<td height="23" width="60" align="center">Cant</td>
		<td height="23" align="center">%</td>
		<td height="23" width="60" align="center">Cant</td>
		<td height="23" align="center">%</td>
		<td height="23" width="70" align="center">Cant</td>
		<td height="23" width="70" align="center">%</td>
	</tr>

<c:set var="i" value="0" />
<c:set var="efectividad" value="0" />
<c:forEach items="${listaCentrales}" var="central">
	<c:set var="row" value="${i%2 == 0? 0 : 1}" />
	<tr class="row${row}">
		<td height="22" align="center"><i>${central[5]}</i></td>
		<td height="22" align="center"><a href="javascript:enviar2('reportePorSite&site=${central[5]}',document.forma1);">${central[0]}</a></td>
		<td height="22" align="center" class="exito${row}">${central[1]}</td>
		<td width="6%" align="center" class="exito${row}"><fmt:formatNumber value="${central[1]/central[0]*100}" type="percent" pattern="##.##"/></td>
		<td height="22" align="center" class="fallo${row}">${central[2]}</td>
		<td align="center" class="fallo${row}"><fmt:formatNumber value="${central[2]/central[0]*100}" type="percent" pattern="##.##"/></td>
		<td height="22" align="center" class="advertencia${row}">${central[3]}</td>

		<c:choose>
		<c:when test="${central[3]/central[0] > 0.80}">
			<c:set var="claseAdvertencia" value="statusAdvertencia" />
		</c:when>
		<c:otherwise>
			<c:set var="claseAdvertencia" value="advertencia${row}" />
		</c:otherwise>
		</c:choose>


		<td align="center" class="${claseAdvertencia}"><fmt:formatNumber value="${central[3]/central[0]*100}" type="percent" pattern="##.##"/></td>
		<td height="22" align="center" class="inesperado${row}">${central[4]}</td>
		<td align="center" class="inesperado${row}"><fmt:formatNumber value="${central[4]/central[0]*100}" type="percent" pattern="##.##"/></td>
		<td height="22" align="center" class="efectividad${row}"><span title="Exito[${central[1]}] + Advertencia[${central[3]}]">${central[1]+central[3]}</span></td>
		<c:set var="clase" value="efectividad${row}" scope="page" />

		<c:choose>
		<c:when test="${(central[1]+central[3])/central[0] < 0.90}">
			<c:set var="claseEfectividad" value="statusEfectividad" />
		</c:when>

		<c:otherwise>
			<c:set var="claseEfectividad" value="efectividad${row}" />
		</c:otherwise>
		</c:choose>

		<td align="center" class="${claseEfectividad}">
			<fmt:formatNumber value="${(central[1]+central[3])/central[0]*100}" type="percent" pattern="##.##"/>
		</td>
<c:set var="efectividad" value="${efectividad+central[1]+central[3]}" />
		<td height="22" align="center">${central[6]}</td>
	</tr>
	<c:set var="i" value="${i + 1}" />
	</c:forEach>

	<tr>
		<td class="fin-reporte" height="22" rowspan="2" align="center">TOTALES</td>
		<td height="22" align="center" class="total-reporte"><a href="javascript:enviar2('todos',document.forma1);">${cantidadTotalRegistros}</a></td>
		<td height="22" align="center" class="total-reporte"><a href="javascript:enviar2('E',document.forma1);">${E}</a></td>
		<td align="center" class="total-reporte"><fmt:formatNumber value="${E/cantidadTotalRegistros}" type="percent" pattern="##.###%"/></td>

		<td height="22" align="center" class="total-reporte"><a href="javascript:enviar2('F',document.forma1);">${F}</a></td>
		<td align="center" class="total-reporte"><fmt:formatNumber value="${F/cantidadTotalRegistros}" type="percent" pattern="##.###%"/></td>

		<td height="22" align="center" class="total-reporte"><a href="javascript:enviar2('A',document.forma1);">${A}</a></td>
		<td align="center" class="total-reporte"><fmt:formatNumber value="${A/cantidadTotalRegistros}" type="percent" pattern="##.###%"/></td>

		<td height="22" align="center" class="total-reporte"><a href="javascript:enviar2('N',document.forma1);">${N}</a></td>
	<td align="center" class="total-reporte"><fmt:formatNumber value="${N/cantidadTotalRegistros}" type="percent" pattern="##.###%"/></td>

		<td height="22" align="center" class="total-reporte"><a href="javascript:enviar2('EA',document.forma1);">${efectividad}</a></td>
		<td align="center" class="total-reporte"><fmt:formatNumber value="${efectividad/cantidadTotalRegistros}" type="percent" pattern="##.###%"/></td>

		<td height="22" align="center" class="total-reporte">${tiempoPromedio}</td>
	</tr>
<tr class="fin-reporte">
<td align="center">100%</td>
<td colspan="2" align="center">EXITO</td>
<td colspan="2" align="center">FALLO</td>
<td colspan="2" align="center">ADVERTENCIA</td>
<td colspan="2" align="center">INSPERADO</td>
<td colspan="2" align="center">EFECTIVIDAD</td>
<td>&nbsp;</td>
</tr>
</table>
<br>
<table align="center" width="50%">
<tr>
<td align="center" class="graficaLink">
	<a href="javascript:mostrarGrafica();">Grafica</a>
</td>
<td align="center">
	<form name="informes">
		 &nbsp;Exportar a: &nbsp;
		<select name="formatos">
		<option value="pdf">PDF</option>
		<option value="csv">CSV</option>
		<option value="xls">XLS</option>
		</select>&nbsp;
		<input name="informe" class="boton" onClick="javascript:informeGen(document.informes);" type="button" value="Aceptar">
	</form>
</td>
</tr>
</table>
</body>
</html>

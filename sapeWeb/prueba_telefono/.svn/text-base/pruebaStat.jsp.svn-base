			<!-- pruebaAtu.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="prueba" value="${requestScope.pruebaStat}"/>
<c:set var="evento" value="${requestScope.evento}"/>
<c:set var="descCodigoVer" value="${requestScope.descCodigoVer}"/>
<html>
<head>
<title>SAPE - Detalle de Prueba</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<style type="text/css">
.bordePrueba {border-style :solid; border-color: black}
.resaltado {color: #ff5900; font-weight: bold}
</style>
</head>
<body bgcolor="white">
<table align="center" border="0" width="100%" cellpadding="0" cellspacing="0" class="bordePrueba">
<tr bgcolor="white">
		<td width="30" background="imagenes_sape/rellenoLateral.jpg">&nbsp;</td>
		<td width="188">
			<img src="imagenes_sape/cabeza_superior.jpg">
		</td>
		<td align="center" background="imagenes_sape/rellenoLateral.jpg" width="*">
			<b><font color="WHITE" size="-1">Sistema Administrativo de Pruebas Extendidas
			<br>
			Resultados de la Prueba El&eacute;ctrica </font></b>
		</td>
</tr>
<tr bgcolor="white">
		<td width="30">&nbsp;</td>
		<td width="188">
			<img src="imagenes_sape/cabeza_inferior.jpg">
		</td>
		<td align="center" width="*">&nbsp;</td>
</tr>
</table>
<br>
<table width="570" border="0" align="center">
	<tr>
		<td width="160" class="header-reporte"> Prueba N&uacute;mero: &nbsp;${evento.id} </td>
		<td width="*">&nbsp;</td>
	</tr>
</table>

<table border="1" align="center" width="570">
	<tr> 
		<td height="36">Tel&eacute;fono: &nbsp;<span class="resaltado">${evento.telefono}</span></td>
		<td height="36">Usuario: &nbsp;${evento.cliente}</td>
		<td height="36">Tipo Prueba: &nbsp;<span class="resaltado">${evento.tipoPrueba}</span></td>
	</tr>
	<tr> 
		<td height="36">
			Fecha: &nbsp;<fmt:formatDate value="${evento.fechaInicial}" type="both" pattern="yy-MM-dd HH:mm:ss" />
		</td>
		<td height="36">
			Duraci&oacute;n: &nbsp;<fmt:formatNumber value="${evento.duracion}" maxFractionDigits="2" minFractionDigits="2" /> seg
		</td>
		<td height="36">
			Central: &nbsp;${evento.central}<br>
		</td>
	</tr>	
</table>
<br>
<table border="1" align="center" width="570">	
	<tr>
	<td colspan="3">
		<table width="100%">
			<tr>
				<td>ATM Received Cells Counter: ${prueba.rxCells}</td>
				<td>ATM Transmitted Cells Counter: ${prueba.txCells}</td>
			</tr>
		</table>
	</td>
	<tr>
		<td align="center" width="270">
			&nbsp;
		</td>
		<td align="center" width="150">
			<strong>Near End</strong>
		</td>
		<td align="center" width="150">
			<strong>Far End</strong>
		</td>
	</tr>
	<tr>
		<td align="left">
			Header Error Check Interleaved:
		</td>
		<td>${prueba.neHec}</td>
		<td>${prueba.feHec}</td>
	</tr>
	<tr>
		<td align="left">
			CRC Interleaved:
		</td>
		<td>${prueba.neCrc}</td>
		<td>${prueba.feCrc}</td>
	</tr>
	<tr>
		<td align="left">
			Severely Errored Frames Counter:
		</td>
		<td>${prueba.neSef}</td>
		<td>${prueba.feSef}</td>
	</tr>
	<tr>
		<td align="left">
			Reed Solomon Forward Error Interleaved:
		</td>
		<td>${prueba.neRsfe}</td>
		<td>${prueba.feRsfe}</td>
	</tr>
	<tr>
		<td colspan="3">
			State: ${prueba.state}
		</td>
	</tr>
	<tr>
		<td colspan="3">
			C&oacute;digo Ver: <span class="resaltado">${prueba.codv}</span>. ${descCodigoVer}
		</td>
	</tr>
</table>
<table align="right" border="0" cellspacing="0" height="40">
	<tr valign="middle">
		<td align="center" width="120">
			<input type="button" value="Cerrar" class="boton" onClick="window.close();">
		</td>
	</tr>
</table>
</body>
</html>

			<!-- pruebaAtu.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sapeTaglib" uri="/WEB-INF/tags/sape-taglib.tld" %>
<c:set var="prueba" value="${requestScope.pruebaAtu}"/>
<c:set var="evento" value="${requestScope.evento}"/>
<c:set var="descCodigoVer" value="${requestScope.descCodigoVer}"/>
<html>
<head>
<title>SAPE - Detalle de Prueba</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<style type="text/css">
.bordePrueba {border-style :solid; border-color: black}
.resaltado {color: #ff5900; font-weight: bold}
.valorUp {background-color: GREEN; color: WHITE;}
.valorDown {background-color: RED; color: WHITE;}
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
<table border="1" align="center">
	<tr> 
		<td height="36" width="130">Tel&eacute;fono: &nbsp;<span class="resaltado">${evento.telefono}</span></td>
		<td height="36" width="160">Usuario: &nbsp;${evento.cliente}</td>
		<td height="36" width="210">Tipo Prueba: &nbsp;<span class="resaltado">${evento.tipoPrueba}</span></td>
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
	<tr height="15">
		<td colspan="3"></td>
	</tr>
	<tr>
		<td align="center">
			<strong>Prueba</strong>
		</td>
		<td align="center">
			<strong>DownStream</strong>
		</td>
		<td align="center">
			<strong>UpStream</strong>
		</td>
	</tr>
	<tr>
		<td align="center">
			Stream BitRate:
		</td>
		<td>${prueba.maxdsbr}</td>
		<td>${prueba.maxusbr}</td>
	</tr>
	<tr>
		<td align="center">
			Actual Interleaved Bit Rate:
		</td>
		<td>${prueba.intdsbr}</td>
		<td>${prueba.intusbr}</td>
	</tr>
	<tr>
		<td align="center">
			Actual Fast Bit Rate:
		</td>
		<td>${prueba.fdsbr}</td>
		<td>${prueba.fusbr}</td>
	</tr>
	<tr>
		<td align="center">
			Capacity:
		</td>
		<td>${prueba.capds}</td>
		<td>${prueba.capus}</td>
	</tr>
	<tr>
		<td align="center">
			Signal Noise Ratio Margin:
		</td>
		<td class="<sapeTaglib:formatearResultado tipoResultado="signalNoiseRatioMargin" valor="${prueba.snrmds}" />">${prueba.snrmds}</td>
		<td class="<sapeTaglib:formatearResultado tipoResultado="signalNoiseRatioMargin" valor="${prueba.snrmus}" />">${prueba.snrmus}</td>
	</tr>
	<tr>
		<td align="center">
			TX Power:
		</td>
		<td class="<sapeTaglib:formatearResultado tipoResultado="txPower" valor="${prueba.pwrds}" />">${prueba.pwrds}</td>
		<td class="<sapeTaglib:formatearResultado tipoResultado="txPower" valor="${prueba.pwrus}" />">${prueba.pwrus}</td>
	</tr>
	<tr>
		<td align="center">
			Attenuation:
		</td>
		<td class="<sapeTaglib:formatearResultado tipoResultado="atenuacionDown" valor="${prueba.attnds}" />">${prueba.attnds}</td>
		<td class="<sapeTaglib:formatearResultado tipoResultado="atenuacionUp" valor="${prueba.attnus}" />">${prueba.attnus}</td>
	</tr>
	<tr>
		<td colspan="3">
			<table width="100%">
				<tr>
					<td>OpMode: ${prueba.opmode}</td>
					<td>State: ${prueba.state}</td>
				</tr>
			</table>
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

			<!-- pruebaPing.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sapeTaglib" uri="/WEB-INF/tags/sape-taglib.tld" %>
<c:set var="prueba" value="${requestScope.pruebaPing}"/>
<c:set var="evento" value="${requestScope.evento}"/>
<c:set var="descCodigoVer" value="${requestScope.descCodigoVer}"/>
<html>
<head>
<title>SAPE - Detalle de Prueba</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<style type="text/css">
.bordePrueba {border-style :solid; border-color: black}
.resaltado {color: #ff5900; font-weight: bold}
.estadoOk {color: GREEN; font-weight: bold}
.estadoError {color: RED; font-weight: bold}
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
		Connectado: &nbsp;<span class="<sapeTaglib:formatearResultado tipoResultado="estadoPing" valor="${prueba.connect}" />">${prueba.connect}</span>
	</td>
	<tr>
		<td colspan="3">
			Usuario: &nbsp;${prueba.loginId}
		</td>
	</tr>
	<tr>
		<td colspan="3">
			Login: &nbsp;<span class="<sapeTaglib:formatearResultado tipoResultado="estadoPing" valor="${prueba.login}" />">${prueba.login}</span>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<table width="100%">
				<tr>
					<td width="50%">Ip Asignada: ${prueba.ipAssigned}</td>
					<td width="50%">Gateway Asignado: ${prueba.gwAssigned}</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<table width="100%">
				<tr>
					<td width="50%">DNS Primario: ${prueba.priDns}</td>
					<td width="50%">DNS Secundario: ${prueba.secDns}</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			Ip Destino: ${prueba.ipDestiny}
		</td>
	</tr>
	<tr>
		<td>Paquetes Enviados: ${prueba.packetsSent}</td>
		<td>
			Ping: &nbsp;<span class="<sapeTaglib:formatearResultado tipoResultado="estadoPing" valor="${prueba.ping}" />">${prueba.ping}</span>
		</td>
		<td>Paquetes Recibidos: ${prueba.packetsRec}</td>
	</tr>
	<tr>
		<td colspan="3" align="center">
			<strong>Tiempo de Ida y Vuelta</strong>
		</td>
	</tr>	
	<tr>
		<td>M&iacute;nimo: ${prueba.minRoundTrip}</td>
		<td>M&aacute;ximo: ${prueba.maxRoundTrip}</td>
		<td>Promedio: ${prueba.avgRoundTrip}</td>		
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

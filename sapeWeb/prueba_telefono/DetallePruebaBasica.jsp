					<!-- DetallePruebaBasica.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="prueba" class="com.osp.sape.maestros.PruebaBasica" scope="request"/>
<jsp:useBean id="evento" class="com.osp.sape.maestros.EventoSape" scope="request"/>
<jsp:useBean id="tipocentral" class="java.lang.String" scope="request"/>
<jsp:useBean id="descCodigoVer" class="java.lang.String" scope="request"/>
<jsp:useBean id="haveGraph" class="java.lang.String" scope="request"/>
<jsp:useBean id="resistenciaMenorSIPLEXPRO" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Detalle de Prueba</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<style type="text/css">
.bordePrueba {border-style :solid; border-color: black}
.bordeDescripcion {
	background-color: #000000;
	color: white;
}
</style>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">

function DatosGrafica(id){
	 this.window.focus();
	 v2000=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=mostrarGrafica&ticket="+id, 'window2000','scrollbars=no,resizable=yes,hotkeys=no,height=550,width=570,left=0,top=0');
	 v2000.focus();
}
</script>

</head>
<body bgcolor="WHITE" marginheight="5" marginwidth="5" topmargin="5" leftmargin="5">
<table align="center" border="0" width="100%" cellpadding="0" cellspacing="0">
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
<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" height="25">
	<tr >
		<td width="150" class="header-reporte"> Prueba N&uacute;mero: &nbsp;${evento.id} </td>
		<td width="*">&nbsp;</td>
	</tr>
</table>


<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr> 
		<td height="36" width="33%">Tel&eacute;fono: &nbsp;<font color="#ff5900"><b>${prueba.telefono}</b></font></td>
		<td height="36" width="33%">Usuario: &nbsp;${evento.cliente}</td>
		<td height="36" width="34%">Tipo Prueba: &nbsp;${evento.tipoPrueba}</td>
	</tr>
	<tr> 
		<td height="36" width="119">Repique: &nbsp;${prueba.campanario} </td>
		<td height="36" width="163">
			Fecha: &nbsp;<fmt:formatDate value="${evento.fechaInicial}" type="both" pattern="yy-MM-dd HH:mm:ss" />
			<br>
			Duraci&oacute;n: &nbsp;<fmt:formatNumber value="${evento.duracion}" maxFractionDigits="2" minFractionDigits="2" /> seg
		</td>
		<td height="36" width="208">
			Central: &nbsp;${evento.central}<br>
			Tecnolog&iacute;a: &nbsp;${tipocentral}
		</td>
	</tr>
</table>


	<table align="center" bgcolor="WHITE" border="1" cellpadding="0" cellspacing="0" width="100%" class="bordePrueba" >
	<tr class="header-reporte">
		<td height="21" width="5%" align="center" class="bordePrueba">&nbsp; </td>
		<td height="21" align="center" class="bordePrueba"><strong>VAC</strong></td>
		<td height="21" align="center" class="bordePrueba"><strong>VDC</strong></td>
		<td height="21" align="center" class="bordePrueba"><strong>RES</strong></td>
		<td height="21" align="center" class="bordePrueba"><strong>CAP</strong></td>
		<td height="21" align="center" class="bordePrueba"><strong>FREC</strong></td>

		<c:if test="${evento.tipoPrueba == 'bas2' || evento.tipoPrueba == 'bas2-clip' || evento.tipoPrueba == 'bas2_force' || evento.tipoPrueba == 'ext4'}">
		<td height="21" width="20%" align="center" class="bordePrueba"> CAP TOTAL </td>
		</c:if>
		
	</tr>
	<tr> 
			<td width="5%" class="bordePrueba"><strong>A-T</strong></td>
			<td height="29" align="center">  ${prueba.acv_tiptoground} </td>
			<td height="29" align="center">  ${prueba.dcv_tiptoground} </td>
			
			<td height="29" align="center">
			
			<c:choose>
				<c:when test="${resistenciaMenorSIPLEXPRO == '1'}">
					<font color="red">${prueba.res_tiptoground} </font>
				</c:when>
				<c:otherwise>
					${prueba.res_tiptoground} 
				</c:otherwise>
			</c:choose>

			</td>
			
			<td height="29" align="center">  ${prueba.rea_tiptoground} </td>
			<td height="29" align="center">  ${prueba.acv_frec_tiptoground} </td>
		
			<c:if test="${evento.tipoPrueba == 'bas2' || evento.tipoPrueba == 'bas2-clip' || evento.tipoPrueba == 'bas2_force' || evento.tipoPrueba == 'ext4'}">
			<td height="29" align="center"> ${prueba.cap_total_at} </td>
			</c:if>
	</tr>
	<tr> 
		<td width="5%" class="bordePrueba"><strong>B-T</strong></td>
			<td height="29" align="center"> ${prueba.acv_ringtoground} </td>
			<td height="29" align="center"> ${prueba.dcv_ringtoground} </td>
			
			<td height="29" align="center">
			<c:choose>
				<c:when test="${resistenciaMenorSIPLEXPRO == '2'}">
					<font color="red">${prueba.res_ringtoground} </font>
				</c:when>
				<c:otherwise>
					${prueba.res_ringtoground}
				</c:otherwise>
			</c:choose>
			</td>
			
			<td height="29" align="center"> ${prueba.rea_ringtoground} </td>
			<td height="29" align="center"> ${prueba.acv_frec_ringtoground} </td>
			
			<c:if test="${evento.tipoPrueba == 'bas2' || evento.tipoPrueba == 'bas2-clip' || evento.tipoPrueba == 'bas2_force' || evento.tipoPrueba == 'ext4'}">
			<td height="29" align="center"> ${prueba.cap_total_bt} </td>
			</c:if> 
	</tr>
	<tr> 
		<td height="29" width="5%" class="bordePrueba"><strong>A-B</strong></td>
			<td height="29" align="center"> ${prueba.acv_tiptoring} </td>
			<td height="29" align="center"> ${prueba.dcv_tiptoring} </td>
			
			<td height="29" align="center">
			<c:choose>
				<c:when test="${resistenciaMenorSIPLEXPRO == '3'}">
					<font color="red">${prueba.res_tiptoring} </font>
				</c:when>
				<c:otherwise>
					${prueba.res_tiptoring}
				</c:otherwise>
			</c:choose>
			  
			</td>
			
			<td height="29" align="center"> ${prueba.rea_tiptoring} </td>
			<td height="29" align="center"> ${prueba.acv_frec_tiptoring}</td>
			
			<c:if test="${evento.tipoPrueba == 'bas2' || evento.tipoPrueba == 'bas2-clip' || evento.tipoPrueba == 'bas2_force' || evento.tipoPrueba == 'ext4'}">
			<td height="29" align="center"> ${prueba.cap_total_ab} </td>
			</c:if>
			
	</tr>
	</table>


	<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr> 
		<td height="30" width="30%" colspan="4"> IAC(A-B) ${prueba.iac_tiptoring} </td>
		<td height="30" width="30%">IDC(A-B) ${prueba.idc_tiptoring}</td>
		<td height="30" width="30%">FREC. ${prueba.iac_frec_tiptoring}</td>
	</tr>
	</table>

<table align="center" bgcolor="" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr> 
	<td align="center" height="30" width="50%">	Distancia: &nbsp; ${prueba.distancia}</td>
	<td align="center" height="30" width="50%"> CodigoVer: &nbsp; <font color="#ff5900"><b>${prueba.codigoVer}</b></font></td>
</tr>
</table>

<table align="center" bgcolor="WHITE" border="1" cellpadding="0" cellspacing="0" width="100%">
<tr> 
	<td height="23" colspan="2"> Descripci&oacute;n de la Prueba: </td>
</tr>
<c:choose>
	<c:when test="${evento.tipoPrueba == 'circuit'}">
		<c:set var="array" value="${fn:split(prueba.dispositivo,'~')}"/>
	<tr align="center">
	<td colspan="2" height="30" width="120"></td>
	<td colspan="5" align="left" height="30" width="500">
		Frecuencia tono <font color="#ff5900"> ${array[0]} </font>&nbsp;&nbsp;
		Potencia tono   <font color="#ff5900"> ${array[1]} </font></td>
	</tr>
	</c:when>
	<c:otherwise>
<tr align="center">
	<td width="120" class="bordeDescripcion"> Descripci&oacute;n 1 </td>
	<td align="left" width="500"><font color="#ff5900">${descCodigoVer}</font><br>
	<c:if test="${not empty resistenciaMenorSIPLEXPRO && tipocentral == 'SIPLEXPRO'}">
		<font color="red">Se sugiere una prueba manual adicional de aislamiento.</font>
	</c:if>
	</td>
</tr>

<tr align="center">
	<td width="120" class="bordeDescripcion">Descripci&oacute;n 2</td>
	<td align="left" width="500">&nbsp;(Velocidad Up = ${prueba.velocidadup} Velocidad Down = ${prueba.velocidaddown})</td>
</tr>
</c:otherwise>
</c:choose>
</table>
<br>
<c:if test="${haveGraph != 'noTieneGrafica'}">
<table align="center" border="0" cellspacing="0" height="50">
	<tr valign="middle">
		<td align="center" width="100">
			<a href="javascript:DatosGrafica('${evento.id}')"><img name="grafica" src="imagenes_sape/grafica.gif" alt="DatosGrafica" border="0"></a>
		</td>
	</tr>
</table>
</c:if>
<table align="right" border="0" cellspacing="0" height="40">
	<tr valign="middle">
		<td align="center" width="120">
			<input type="button" value="Cerrar" class="boton" onClick="window.close();">
		</td>
	</tr>
</table>
</body>
</html>

			<!-- primeraPruebaTelefono.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="estadoCyclades" value="${requestScope.estadoCyclades}" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<html>
	<head>
		<title>SAPE - Estado Cyclades</title>
	</head>
	<jsp:include page="../encabezado.jsp" flush="true" />
	<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
		<br>
		<table width="65%" border="0" align="center">
			<tr>
				<td colspan="5">
					<p class="mensajeCentral" align="center">Estado Cyclades</p>
				</td>
			</tr>
		</table>
		<br>
		<table width="65%" border="0" align="center">
			<tr>
				<td colspan="4" align="left">
					<c:if test="${estadoCyclades.sincronizado ne 'Synchronized'}" >
						<font color="red"><strong>${estadoCyclades.sincronizado}</strong></font>
					</c:if>
					<c:if test="${estadoCyclades.sincronizado eq 'Synchronized'}" >
						<font color="blue"><strong>${estadoCyclades.sincronizado}</strong></font>
					</c:if>
		
					<strong>Alarma Roja:</strong>
					<c:if test="${estadoCyclades.alarmaRoja ne 'OFF'}" >
						<font color="red"><strong>${estadoCyclades.alarmaRoja}</strong></font>
					</c:if>
					<c:if test="${estadoCyclades.alarmaRoja eq 'OFF'}" >
						<font color="blue"><strong>${estadoCyclades.alarmaRoja}</strong></font>
					</c:if>
					
					<strong>Alarma Azul:</strong>
					<c:if test="${estadoCyclades.alarmaAzul ne 'OFF'}" >
						<font color="red"><strong>${estadoCyclades.alarmaAzul}</strong></font>
					</c:if>
					<c:if test="${estadoCyclades.alarmaAzul eq 'OFF'}" >
						<font color="blue"><strong>${estadoCyclades.alarmaAzul}</strong></font>
					</c:if>
		
					<strong>Alarma Amarilla:</strong>
					<c:if test="${estadoCyclades.alarmaAmarilla ne 'OFF'}" >
						<font color="red"><strong>${estadoCyclades.alarmaAmarilla}</strong></font>
					</c:if>
					<c:if test="${estadoCyclades.alarmaAmarilla eq 'OFF'}" >
						<font color="blue"><strong>${estadoCyclades.alarmaAmarilla}</strong></font>
					</c:if>
				</td>
				<td align="right">
					<input name="refresco" class="boton" onClick="javascript:document.location.reload();" type="button" value="Refrescar">
				</td>
			</tr>
		</table>
		<br>
		<table width="65%" border="0" align="center">
			<tr class="header-reporte">
				<td align="center">
					<font size="3px">
						Id
					</font>
				</td>
				<td align="center">
					<font size="3px">
						Estado canal
					</font>
				</td>
				<td align="center">
					<font size="3px">
						Tel&eacute;fono
					</font>
				</td>
				<td align="center">
					<font size="3px">
						Estado modem
					</font>
				</td>
			</tr>
			<c:forEach begin="0" end="29" var="i">
				<c:set var="row" value="${i%2 == 0? 1: 0}"/>
				<c:set var="col" value="white"/>
				<c:choose>
					<c:when test="${estadoCyclades.modems[i].estado eq 'IDLE'}">
						<c:set var="col" value="#c1c1c1"/>
					</c:when>
					<c:when test="${estadoCyclades.modems[i].estado eq 'CONNECTED'}">
						<c:set var="col" value="#818ac1"/>
					</c:when>
				</c:choose>	
				<tr class="row${row}">
					<td height="22" align="center">
						${estadoCyclades.canales[i].id}
					</td>					
					<td height="22" align="center">
						${estadoCyclades.canales[i].estado}
					</td>
					<td height="22" align="center">
						${estadoCyclades.canales[i].telefono}
					</td>
					<td width="25%" height="22" align="center" bgcolor="${col}">
						${estadoCyclades.modems[i].estado}
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" align="center">
					<br>
					<input name="refresco" class="boton" onClick="javascript:document.location.reload();" type="button" value="Refrescar">
				</td>
			</tr>
		</table>
	</body>
</html>
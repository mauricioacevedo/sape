				<!-- matrizSiplexPRO.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="telefonos" value="${requestScope.telefonosMatriz}" />
<html>
<head>
<TITLE>SAPE - Matriz SiplexPRO</TITLE>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<style type="text/css">
.telefono {font-size: 10px}
.matriz {border-style: solid; border-color: #ffaa00}
</style>
<script languaje="JavaScript">
	function abrirPar(par) {
		var url = "${pageContext.request.contextPath}/actionSape?accion=${param.accion}&operacion=editarPar&par=" + par;
		var vent = window.open(url, 'editarPar','scrollbars=no,resizable=no,hotkeys=no,height=300,width=400,left=150,top=100,menubar=yes,toolbar=no');
		vent.focus();
	}
</script>
</head>
<body bgcolor="white">
<jsp:include page="../../encabezado.jsp" flush="true" />
<br>
<table align="center" border="0" width="100%">
<tr>
	<td align="center" class="mensajeCentral">MATRIZ SIPLEXPRO</td>
</tr>
</table>
<br>
<table align="center" border="0" width="100%">
<tr class="header-reporte" align="center" height="20">
	<td>
		76 - 100
	</td>	
	<td>
		51 - 75
	</td>
	<td>
		26 - 50
	</td>			
	<td>
		1 - 25
	</td>	
</tr>
<tr height="15">
	<td></td>	
	<td></td>
	<td></td>			
	<td></td>	
</tr>
<tr>
	<td>
		<table align="center" border="1" class="matriz">
		<c:forEach var="i" begin="1" end="5">
		<%-- Comienzo es para que las tablas se muestren descendetemente --%>
		<c:set var="comienzo" value="${81-i}" />
		<tr align="center" height="40">
			<c:set var="par" value="${20+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${15+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${10+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${5+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
		</tr>
		</c:forEach>
		</table>
	</td>
	<td>
		<table align="center" border="1" class="matriz">
		<c:forEach var="i" begin="1" end="5">
		<%-- Comienzo es para que las tablas se muestren descendetemente --%>
		<c:set var="comienzo" value="${56-i}" />
		<tr align="center" height="40">
			<c:set var="par" value="${20+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${15+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${10+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${5+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
		</tr>
		</c:forEach>
		</table>
	</td>
	<td>
		<table align="center" border="1" class="matriz">
		<c:forEach var="i" begin="1" end="5">
		<%-- Comienzo es para que las tablas se muestren descendetemente --%>
		<c:set var="comienzo" value="${31-i}" />
		<tr align="center" height="40">
			<c:set var="par" value="${20+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${15+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${10+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${5+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
		</tr>
		</c:forEach>
		</table>
	</td>
	<td>
		<table align="center" border="1" class="matriz">
		<c:forEach var="i" begin="1" end="5">
		<%-- Comienzo es para que las tablas se muestren descendetemente --%>
		<c:set var="comienzo" value="${6-i}" />
		<tr align="center" height="40">
			<c:set var="par" value="${20+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${15+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${10+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${5+comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
			<c:set var="par" value="${comienzo}" />
			<c:set var="nombre" value="p${par}" />
			<td width="40" class="matriz">
				<a href="javascript:abrirPar('${par}')">${par}</a>
				<br>
				<div class="telefono">${telefonos[nombre]}</div>
			</td>
		</tr>
		</c:forEach>
		</table>
	</td>
</tr>
</table>
<br>
<div align="center"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoSiplexPRO">Regresar</a></div>
</body>
</html>

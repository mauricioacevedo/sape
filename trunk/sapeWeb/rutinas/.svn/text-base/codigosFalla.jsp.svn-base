<!-- codigosFalla.jsp -->

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="listaCodigos" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="listaNaturalezas" class="java.util.ArrayList" scope="request"/>

<html><head></head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<jsp:include page="../encabezado.jsp" flush="true" />
<script language="JavaScript">
<!--
	function eliminarCodigo(id) {
		if(confirm("BORRAR el codigo "+id+" ?"))	location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=eliminarCodigoFalla&fallaId="+id+"&tipo=falla";
	}
	
	function eliminarNaturaleza(id) {
		if(confirm("BORRAR la naturaleza "+id+" ?"))	location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=eliminarCodigoFalla&fallaId="+id+"&tipo=naturaleza";
	}
	
-->
</script>
<body bgcolor="white" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div align="center"><a name="agregarPermitidos" href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=agregarCodigoFalla">Agregar Codigo</a></div>
<br>
<br>

<table width="100%" align="center">
<tr>
<td width="50%" height="*" valign="top">
<table align="center" width="80%">

<tr bgcolor="Black">
	<td align="center" class="mensajeCentral" colspan="3">Codigos de Falla <tags:ayudas pagina="FALTA_AYUDA"/></td>
</tr>

<tr bgcolor="black">
	<td align="center" width="40%" class="header-reporte">Id Falla</td>
	<td align="center" width="45%" class="header-reporte">Nombre</td>
	<td align="center" width="15%" class="header-reporte">Opcion</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaCodigos}" var="codigo">

	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}">
		<td align="center" width="40%">${codigo.fallaId}</td>
		<td align="center" width="45%">${codigo.nombre}</td>
		<td align="center" width="15%"><a name="modificarCodigo" href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=modificarCodigoFalla&fallaId=${codigo.fallaId}&tipo=falla">Modificar</a><br><a name="eliminarCodigo" href=javascript:eliminarCodigo('${codigo.fallaId}');>Eliminar</a></td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>
</table>
</td>
<td width="50%" height="*" valign="top">
<table align="center" width="80%">

<tr bgcolor="Black">
	<td align="center" class="mensajeCentral" colspan="3">Naturalezas de Reclamo <tags:ayudas pagina="FALTA_AYUDA"/></td>
</tr>

<tr bgcolor="black">
	<td align="center" width="40%" class="header-reporte">Id Falla</td>
	<td align="center" width="45%" class="header-reporte">Nombre</td>
	<td align="center" width="15%" class="header-reporte">Opcion</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaNaturalezas}" var="naturaleza">

	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}">
		<td align="center" width="40%">${naturaleza.fallaId}</td>
		<td align="center" width="45%">${naturaleza.nombre}</td>
		<td align="center" width="15%"><a name="modificarCodigo" href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=modificarCodigoFalla&fallaId=${naturaleza.fallaId}&tipo=naturaleza">Modificar</a><br><a name="eliminarCodigo" href=javascript:eliminarNaturaleza('${naturaleza.fallaId}');>Eliminar</a></td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>
</table>
</td>

</tr>
</table>

<br>
<div align="center"><a name="agregarCodigo" href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=agregarCodigoFalla">Agregar Codigo</a></div>

</body></html>

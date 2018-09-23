<!-- MantenimientoPermitidos.jsp -->

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% java.util.List listaPermitidos = (java.util.List) request.getAttribute("listaPermitidos"); %>

<html><head></head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<jsp:include page="../encabezado.jsp" flush="true" />
<body bgcolor="white">

<script language="JavaScript">
<!--
	function eliminarPermitidos(ip,cliente) {
		if(confirm("BORRAR Ip " +ip+" del usuario "+ cliente+" ?"))
			location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=eliminarPermitidos&ip="+ip+"&cliente="+cliente;

}
-->
</script>


<div align="center"><a name="agregarPermitidos" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarPermitidos">Agregar Ip Permitido</a></div>
<br>
<br>

<table align="center" width="60%">

<tr bgcolor="Black">
	<td align="center" class="mensajeCentral" colspan="3">Cliente IP Permitidos<tags:ayudas pagina="Ip"/></td>
</tr>

<tr bgcolor="black">
	<td align="center" width="40%" class="header-reporte">IP</td>
	<td align="center" width="45%" class="header-reporte">Cliente</td>
	<td align="center" width="15%" class="header-reporte">Opcion</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaPermitidos}" var="permitidos">

	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}">
		<td align="center" width="40%">${permitidos.ip}</td>
		<td align="center" width="45%">${permitidos.cliente}</td>
		<td align="center" width="15%"><a name="modificarPermitidos" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=modificarPermitidos&ip=${permitidos.ip}">modificar</a><br><a name="eliminarPermitidos" href=javascript:eliminarPermitidos("${permitidos.ip}","${permitidos.cliente}");>eliminar</a></td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>

</table>
<br>
<div align="center"><a name="agregarPermitidos" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarPermitidos">Agregar Ip Permitido</a></div>

</body></html>

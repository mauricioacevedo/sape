<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- inicioFast.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listaFast" value="${requestScope.listaFast}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mantenimiento Fast</title>
</head>
<script language="javascript">

function modificarFast(tel){
	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=modificarFast&telefono="+tel;
}

function eliminarFast(id,numeroFast){
	
	if(confirm("Se eliminara el Fast "+numeroFast+", esta seguro?")){
		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=eliminarFast&id="+id;
	}
}


</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white">
<jsp:include page="../../encabezado.jsp" flush="true" />


<div align="center"><a name="agregarFast" href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=agregarFast">Agregar nuevo Fast</a></div>
<br>
<br>

<table align="center" width="40%">

<!--tr>
	<td align="right" colspan="8">
		<form name="informes">
			<font color="Black">Exportar a &nbsp;</font>
			<select name="formatos">
				<option value="pdf">PDF</option>
				<option value="csv">CSV</option>
				<option value="xls">XLS</option>
			</select>
			<input class="boton" name='informe' ONCLICK='javascript:informeGen(document.informes);' type='button' value='Aceptar'>
		</form>
	</td>
</tr-->

<!-- tr>
<td colspan="8" align="left">
<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=cargarArchivoFast">Cargar Archivo Fast</a>
</td>
</tr-->
<tr bgcolor="black">
	<td align="center" colspan="8">
		<font color="WHITE" class="mensajeCentral">	Fast EPM </font>
	<tags:ayudas pagina="FALTA_AYUDA_FastEPM"/></td>
</tr>

<tr bgcolor="black">
	<td align="center" width="15%" class="header-reporte">Numero de Fast</td>
	<td align="center" width="15%" class="header-reporte">Telefono Fast</td>
	<td align="center" width="15%" class="header-reporte">Cabeza</td>
	<td align="center" width="15%" class="header-reporte">Opcion</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaFast}" var="fast">

<c:set var="row" value="${ i % 2 == 0? 0 : 1}"/>
<tr class="row${row}">
	<td align="center">${fast.numeroFast}</td>
	<td align="center">${fast.telefonoFast}</td>
	<td align="center">${fast.id}</td>
	<td align="center"><a name="modificarFast" href="javascript:modificarFast('${fast.telefonoFast}');">modificar</a><br><a name="eliminarFast" href="javascript:eliminarFast('${fast.telefonoFast}','${fast.numeroFast}');">eliminar</a></td>
</tr>
<c:set var="i" value="${i + 1}"/>
</c:forEach>

</table>

</body>
</html>
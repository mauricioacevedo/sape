<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- inicioIndigo.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listaIndigo" value="${requestScope.listaIndigo}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mantenimiento Indigos</title>
</head>
<script language="javascript">

function modificarIndigo(id){
	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=modificarIndigo&id="+id;
}

function eliminarIndigo(id,numeroIndigo){
	
	if(confirm("Se eliminara el Indigo "+numeroIndigo+", esta seguro?")){
		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=eliminarIndigo&id="+id;
	}
}


</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white">
<jsp:include page="../../encabezado.jsp" flush="true" />

<div align="center"><a name="agregarIndigo" href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=agregarIndigo">Agregar nuevo Indigo</a></div>
<br>
<br>
<table align="center" width="50%">
<tr>
<td align="left" colspan="3">
<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=cargarArchivoIndigo">Cargar Archivo Indigo</a>
</td>
<td align="right" colspan="5">
<!--input class="boton" name='transferirFast' onclick='javascript:transferirFast(document.formaGral);' type='button' value='Transferir Fast'>
&nbsp;&nbsp;
<input class="boton" name='transferirIndigo' onclick='javascript:transferirIndigo(document.formaGral);' type='button' value='Transferir Indigo'>
-->
</td>
</tr>

<form name="formaGral">
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
<tr>
<td colspan="8" align="left">
</td>
</tr>
<tr bgcolor="black">
	<td align="center" colspan="8">
		<font color="WHITE" class="mensajeCentral">	Indigo EPM </font>
	<tags:ayudas pagina="FALTA_AYUDA_IndigoEPM"/></td>
</tr>

<tr bgcolor="black">
	<td align="center" width="5%" class="header-reporte">Id</td>
	<td align="center" width="15%" class="header-reporte">Numero de Indigo</td>
	<td align="center" width="15%" class="header-reporte">Ip Indigo</td>
	<td align="center" width="10%" class="header-reporte">Puerto Indigo</td>
	<td align="center" width="15%" class="header-reporte">Opcion</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaIndigo}" var="indigo">

<c:set var="row" value="${ i % 2 == 0? 0 : 1}"/>
<tr class="row${row}">
	<td align="center">${indigo.id}</td>
	<td align="center">${indigo.numeroIndigo}</td>
	<td align="center">${indigo.ip}</td>
	<td align="center">${indigo.port}</td>
	<td align="center"><a name="modificarIndigo" href="javascript:modificarIndigo('${indigo.id}');">modificar</a><br><a name="eliminarIndigo" href="javascript:eliminarIndigo('${indigo.id}','${indigo.numeroIndigo}');">eliminar</a></td>
</tr>
<c:set var="i" value="${i + 1}"/>
</c:forEach>

</table>
</form>
</body>
</html>
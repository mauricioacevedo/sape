<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% java.util.List listaCabezaPrueba = (java.util.List) request.getAttribute("listaCabezaPrueba"); %>
<!-- MantenimientoCabezaPrueba.jsp -->
<html>

<script language="JavaScript">
<!--
function eliminarCabezaPrueba(idcabeza) {

	if(confirm("BORRAR Clase de Nodo ID="+idcabeza))
    	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=eliminarCabezaPrueba&id="+idcabeza;
}

function informeGen(format){

	var val = format.formatos.options[format.formatos.selectedIndex].value;
	document.location = "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoCabezaPrueba&exportar=yes&pantalla=cabezaPrueba&formato="+val;
}

-->
</script>


<head></head>
<jsp:include page="../encabezado.jsp" flush="true" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white">



<div align="center"><a name="agregarCabezaPrueba" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarCabezaPrueba">Agregar Clase de Nodo</a></div>
<br>
<br>

<table align="center" width="70%">

<tr>
	<td align="right" bgcolor="white" colspan ="11">
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
</tr>


<tr bgcolor="Black">
	<td align="center" class="mensajeCentral" colspan="4">Clase de Nodos <tags:ayudas pagina="ClaseNodo"/></td>
</tr>


<tr bgcolor="black">
	<td align="center" width="10%" class="header-reporte">Clase ID</td>
	<td align="center" width="15%" class="header-reporte">Nombre</td>
	<td align="center" width="15%" class="header-reporte">Vendor</td>
	<td align="center" width="15%" class="header-reporte">Opcion</td>
</tr>


<c:set var="i" value="0" />
<c:forEach items="${listaCabezaPrueba}" var="cabezaprueba">
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}">
		<td align="center" width="10%">${cabezaprueba.id}</td>
		<td align="center" width="15%">${cabezaprueba.nombre}</td>
		<td align="center" width="15%">${cabezaprueba.proveedor}</td>
		<td align="center" width="15%"><a name="modificarCabezaPrueba" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=modificarCabezaPrueba&id=${cabezaprueba.id}">modificar</a><br><a name="eliminarCabezaPrueba" href=javascript:eliminarCabezaPrueba("${cabezaprueba.id}");>eliminar</a></td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>

</table>
<br>
<div align="center"><a name="agregarCabezaPrueba" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarCabezaPrueba">Agregar Clase de Nodo</a></div>

</body></html>

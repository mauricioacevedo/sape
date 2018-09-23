					<!-- MantenimientoSerie.jsp  -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listaSerie" value="${requestScope.listaSerie}" />
<html>
<head>
<title>SAPE - Series Numericas</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript">
<!--
function eliminarSerie (id,cabezaid,inicial,Final) {
if(confirm("BORRAR Serie con: \nCabeza ID="+cabezaid+"\nSerie Inicial="+inicial+"\nSerie Final="+Final))
    location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=eliminarSerie&id="+id+"&cabezaid="+cabezaid+"&Inicial="+inicial+"&Final="+Final;
}

function informeGen(format){

	var val = format.formatos.options[format.formatos.selectedIndex].value;	
	document.location = "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoSeries&exportar=yes&pantalla=series&formato="+val;
}

-->
</script>
</head>
<body bgcolor="white">
<jsp:include page="../encabezado.jsp" flush="true" />
<div align="center"><a name="agregarSerie" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarSerie">Agregar Serie</a></div>
<br>

<table align="center" width="70%">
<tr>
	<td align="right" colspan ="11">
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
	<td align="center" colspan="11" class="mensajeCentral">Series Numericas<tags:ayudas pagina="Series"/></td>
</tr>

<tr bgcolor="black">
<td align="center" width="10%" class="header-reporte">ID</td>
<td align="center" width="15%" class="header-reporte">Incial</td>
<td align="center" width="15%" class="header-reporte">Final</td>
<td align="center" width="10%" class="header-reporte">Central</td>
<td align="center" width="15%" class="header-reporte">Tipo</td>
<td align="center" width="10%" class="header-reporte">ID Cabeza</td>
<td align="center" width="25%" class="header-reporte">Opcion</td>
</tr>

<c:set var="i" value="0" />

<c:forEach items="${listaSerie}" var="serie">
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}" height="25">
		<td align="center" width="10%">${serie.id}</td>
		<td align="center" width="15%"><i>${serie.serieInicial}</i></td>
		<td align="center" width="15%"><i>${serie.serieFinal}</font></i></td>
		<td align="center" width="10%"><i>&nbsp;${serie.central}</font></i></td>
		<td align="center" width="15%"><i>${serie.tipocentral}</font></i></td>
		<td align="center" width="10%">${serie.cabezaId}</font></td>
		<td align="center" width="25%">
			<a name="modificarSerie" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=modificarSerie&id=${serie.id}">Modificar</a>
			 - <a name="eliminarSerie" href=javascript:eliminarSerie('${serie.id}','${serie.cabezaId}','${serie.serieInicial}','${serie.serieFinal}');>Eliminar</a>
		</td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>
</table>
<br>
<div align="center"><a name="agregarSerie" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarSerie">Agregar Serie</a></div>
</form>
</body></html>

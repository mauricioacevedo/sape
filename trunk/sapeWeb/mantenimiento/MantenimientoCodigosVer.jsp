<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sapeTaglib" uri="/WEB-INF/tags/sape-taglib.tld" %>
<% java.util.List listaCodigosVer = (java.util.List) request.getAttribute("listaCodigosVer"); %>


<html><head></head>
<jsp:include page="../encabezado.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white">

<script language="JavaScript">
<!--
function informeGen(format){

	var val = format.formatos.options[format.formatos.selectedIndex].value;
	document.location = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=exportarInforme&pantalla=codigosVer&formato="+val;

}

 function eliminarCodigoVer(codigover) {
  if(confirm("A continuacion se eliminara el Codigo : \""+codigover+"\". \nEsta seguro? "))
     location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=eliminarCodigoVer&codigoVer="+codigover;
}

-->
</script>
<!--style type="text/css">

 td { color: black; font-family: "Arial, Helvetica, sans-serif"; font-size: 10pt }

</style-->


<div align="center">
<a name="agregarCodigoVer" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarCodigoVer">Agregar nuevo C&oacute;digo</a>

<c:if test="${sapeTaglib:isVisible('CODVETB')}">
- <a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=codvETB">CodigosVer ETB</a>
</c:if>
</div>
    
<br>

<table align="center" width="100%">

<tr>
	<td align="right" bgcolor="white" colspan="6">
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
	<td align="center" class="mensajeCentral" colspan="4"> Codigos Ver <tags:ayudas pagina="CodigoVer"/></td>
</tr>

<tr bgcolor="black">
	<td align="center" width="10%" class="header-reporte">C&oacute;digo Ver</td>
	<td align="center" width="35%" class="header-reporte">Descripci&oacute;n</td>
	<td align="center" width="40%" class="header-reporte">Comentario</td>
	<td align="center" width="15%" class="header-reporte">Opci&oacute;n</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaCodigosVer}" var="codigover">
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}" height="25">
		<td align="center" width="10%">&nbsp;${codigover.codigoVer}</td>
		<td align="center" width="35%">&nbsp;${codigover.clasificacion}</td>
		<td align="center" width="40%">&nbsp;${codigover.comentarios}</td>
		<td align="center" width="15%">
			<a name="modificarCodigoVer" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=modificarCodigoVer&codigoVer=${codigover.codigoVer}">Modificar</a> - <a name="eliminarCodigoVer" href=javascript:eliminarCodigoVer("${codigover.codigoVer}");>Eliminar</a>
		</td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>

</table>
<br>
<div align="center"><a name="agregarCodigoVer" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarCodigoVer">Agregar nuevo C&oacute;digo</a></div>

</body></html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- cargarArchivoIndigo.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="listaIndigo" value="${requestScope.listaIndigo}" />
<c:set var="indigoSeleccionado" value="${requestScope.indigoSeleccionado}" />
<jsp:useBean id="destino" class="java.lang.String" scope="request"/>
<jsp:useBean id="existeArchivo" class="java.lang.String" scope="request"/>
<jsp:useBean id="msg" class="java.lang.String" scope="request"/>

<html>

<script language="javascript">
	function seleccionarIndigo(){
		var combo = document.getElementById('comboIndigo');
		var idIndigo = combo.options[combo.selectedIndex].value;
		
		if(idIndigo == '-1'){
			alert('Seleccione un Indigo.');
			return;
		}
		
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=seleccionarIndigo&id="+idIndigo;
	}	
</script>
<body topmargin="0" leftmargin="0" alink="olive" link="#330099" bgcolor="white">
<jsp:include page="../../encabezado.jsp" flush="true" />

<br>
<br>
<center><font color="red">${msg}</font></center>
<table align="center" width="600">

<tr bgcolor="black">
	<td align="center" colspan="8">
		<font color="WHITE" class="mensajeCentral">Cargar Archivo Indigo</font>
</tr>
<tr><td height="20" colspan="2"></td></tr>
<tr>
<td align="center">Seleccione un Indigo: </td>
<td align="center">
<select id="comboIndigo" onchange="javascript:seleccionarIndigo();" ${not empty indigoSeleccionado?'disabled':''}>
		<option value="-1">Seleccione:</option>
		
		<c:forEach items="${listaIndigo}" var="indigo">
			<option value="${indigo.id}" ${indigoSeleccionado == indigo.id?'selected':''}>${indigo.site}</option>
			<c:set var="numeroIndigo" value="${indigoSeleccionado == indigo.id?indigo.site:''}" />
		</c:forEach>

</select>
</td>
</tr>

<c:if test="${not empty indigoSeleccionado}">

<tr><td height="20" colspan="2"></td></tr>

<tr>
<td align="center">Obtener archivo: </td>
<td align="center">

<c:choose>
<c:when test="${not empty existeArchivo && existeArchivo == 'false'}">
No se ha cargado archivo de configuracion para el Indigo ${numeroIndigo}.
</c:when>
<c:otherwise>
<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=obtenerArchivoIndigo&id=${indigoSeleccionado}">Archivo de Configuracion Indigo</a>
</c:otherwise>
</c:choose>
</td>
</tr>

<tr><td height="20" colspan="2"></td></tr>

<tr>
<td colspan="2" align="center"><jsp:include page="../../utils/fileUpload.jsp" flush="true" /></td>
</tr>

<tr>
</tr>

</c:if>

</table>

</body>
</html>
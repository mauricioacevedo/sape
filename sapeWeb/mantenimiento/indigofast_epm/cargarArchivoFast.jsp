<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- cargarArchivoFast.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="listaFast" value="${requestScope.listaFast}" />
<c:set var="fastSeleccionado" value="${requestScope.fastSeleccionado}" />
<jsp:useBean id="destino" class="java.lang.String" scope="request"/>
<jsp:useBean id="existeArchivo" class="java.lang.String" scope="request"/>
<jsp:useBean id="msg" class="java.lang.String" scope="request"/>

<html>

<script language="javascript">
	function seleccionarFast(){
		var combo = document.getElementById('comboFast');
		var telFast = combo.options[combo.selectedIndex].value;
		
		if(telFast == '-1'){
			alert('Seleccione un Fast.');
			return;
		}
		
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=seleccionarFast&telFast="+telFast;
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
		<font color="WHITE" class="mensajeCentral">Cargar Archivo Fast</font>
</tr>
<tr><td height="20" colspan="2"></td></tr>
<tr>
<td align="center">Seleccione un Fast: </td>
<td align="center">
<select id="comboFast" onchange="javascript:seleccionarFast();" ${not empty fastSeleccionado?'disabled':''}>
		<option value="-1">Seleccione:</option>
		
		<c:forEach items="${listaFast}" var="fast">
			<option value="${fast.telefonoFast}" ${fastSeleccionado == fast.telefonoFast?'selected':''}>${fast.numeroFast}</option>
			<c:set var="numeroFast" value="${fastSeleccionado == fast.telefonoFast?fast.numeroFast:''}" />
		</c:forEach>

</select>
</td>
</tr>

<c:if test="${not empty fastSeleccionado}">

<tr><td height="20" colspan="2"></td></tr>

<tr>
<td align="center">Obtener archivo: </td>
<td align="center">

<c:choose>
<c:when test="${not empty existeArchivo && existeArchivo == 'false'}">
No se ha cargado archivo de configuracion para el Fast ${numeroFast}.
</c:when>
<c:otherwise>
<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=obtenerArchivoFast&telefono=${fastSeleccionado}">Archivo de Configuracion Fast</a>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- operacionesBasicas.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listaFast" value="${requestScope.listaFast}" />
<c:set var="listaIndigo" value="${requestScope.listaIndigo}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Operaciones sobre Indigos</title>
</head>
<script language="javascript">

function transferenciaFast(){
	
	var comboIndigo = document.getElementById('indigos');
	var comboFast = document.getElementById('fast');
	
	var indigo = comboIndigo.options[comboIndigo.selectedIndex].value;
	var numeroIndigo =comboIndigo.options[comboIndigo.selectedIndex].text;
	var fast = comboFast.options[comboFast.selectedIndex].value;
	var nombreFast = comboFast.options[comboFast.selectedIndex].text;
	
	if(indigo == '-1'){
		alert('Seleccione un Indigo');
		comboIndigo.focus();
		return;
	}
	
	if (fast == '-1') {
		alert('Seleccione un fast');
		comboFast.focus();
		return;
	}
	
	if(confirm("Se hara transferencia de archivo a el Fast "+nombreFast+"\na traves del indigo "+numeroIndigo+", desea continuar?")){
		var winTransfer=window.open("${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=transferenciaFast&indigo="+indigo+
									"&fast="+fast,'Modificar','scrollbars=yes,resizable=yes,hotkeys=yes,height=420,width=500,left=150,top=100,menubar=no,toolbar=no');
		winTransfer.focus();
	}
}

function transferenciaIndigo(){
	var comboIndigo = document.getElementById('indigos');
	
	var indigo = comboIndigo.options[comboIndigo.selectedIndex].value;
	var numeroIndigo =comboIndigo.options[comboIndigo.selectedIndex].text;
	
	if(indigo == '-1'){
		alert('Seleccione un Indigo');
		comboIndigo.focus();
		return;
	}
	
	if(confirm('Se iniciara la transferencia de archivo al indigo '+numeroIndigo+", desea continuar?")){
		var winTransfer=window.open("${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=transferenciaIndigo&indigo="+indigo,'Modificar','scrollbars=yes,resizable=yes,hotkeys=yes,height=420,width=500,left=150,top=100,menubar=no,toolbar=no');
		winTransfer.focus();
	}
}

function abortarIndigo(){
	var comboIndigo = document.getElementById('indigos');
	
	var indigo = comboIndigo.options[comboIndigo.selectedIndex].value;
	var numeroIndigo =comboIndigo.options[comboIndigo.selectedIndex].text;
	
	if(indigo == '-1'){
		alert('Seleccione un Indigo');
		comboIndigo.focus();
		return;
	}
	if(confirm('Se abortara el indigo '+numeroIndigo+", desea continuar?")){
		var winTransfer=window.open("${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=abortarIndigo&indigo="+indigo,'Modificar','scrollbars=yes,resizable=yes,hotkeys=yes,height=420,width=500,left=150,top=100,menubar=no,toolbar=no');
		winTransfer.focus();
	}
}

function resetIndigo(){
	var comboIndigo = document.getElementById('indigos');
	
	var indigo = comboIndigo.options[comboIndigo.selectedIndex].value;
	var numeroIndigo =comboIndigo.options[comboIndigo.selectedIndex].text;
	
	if(indigo == '-1'){
		alert('Seleccione un Indigo');
		comboIndigo.focus();
		return;
	}

	if(confirm('Se hara un reset al indigo '+numeroIndigo+", desea continuar?")){
		var winTransfer=window.open("${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=resetIndigo&indigo="+indigo,'Modificar','scrollbars=yes,resizable=yes,hotkeys=yes,height=420,width=500,left=150,top=100,menubar=no,toolbar=no');
		winTransfer.focus();
	}
}

</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white">
<jsp:include page="../../encabezado.jsp" flush="true" />

<br>
<br>
<div align="center"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=cargarArchivoIndigo">Cargar Archivo Indigo</a></div>

<table align="center" width="40%">

<tr bgcolor="black">
	<td align="center" colspan="2">
		<font color="WHITE" class="mensajeCentral">Operaciones Sobre Indigos</font>
	<tags:ayudas pagina="FALTA_AYUDA_OperacionesBasicas"/></td>
</tr>
<tr><td align="center" colspan="2" height="20"></td>
<tr>
	<td align="center">Seleccione un Indigo</td>
	<td align="center">
	
	<select id="indigos">
		<option value="-1">Seleccione:</option>
		<c:forEach items="${listaIndigo}" var="indigo">
		<option value="${indigo.id}">${indigo.site}</option>
		</c:forEach>
	</select>
	
	</td>
</tr>

<!-- tr>
	<td align="center">Seleccione un Fast</td>
	<td align="center">
	
	<select id="fast">
		<option value="-1">Seleccione:</option>
		<c:forEach items="${listaFast}" var="fast">
		<option value="${fast.telefonoFast}">${fast.numeroFast} (${fast.telefonoFast})</option>
		</c:forEach>
	</select>
	
	
	</td>
</tr-->

<tr bgcolor="black">
	<td align="center" colspan="2">
		<font color="WHITE" class="mensajeCentral">Opciones:</font>
	</td>
</tr>

<tr><td align="center" colspan="2" height="20"></td></tr>

<!-- tr>
<td align="center" colspan="2">
<input type="button" name="tFast" value="Transferencia a Fast" onclick="javascript:transferenciaFast();" class="boton">
</td>
</tr-->

<tr><td align="center" colspan="2" height="20"></td></tr>

<tr>
<td align="center" colspan="2">
<input type="button" name="tIndigo" value="Transferencia a Indigo" onclick="javascript:transferenciaIndigo();" class="boton">
</td>
</tr>

<tr><td align="center" colspan="2" height="20"></td></tr>

<tr>
<td align="center" colspan="2">
<input type="button" name="rIndigo" value="Resetear Indigo" onclick="javascript:resetIndigo();" class="boton">
</td>
</tr>

<tr><td align="center" colspan="2" height="20"></td></tr>

<tr>
<td align="center" colspan="2">
<input type="button" name="aIndigo" value="Abortar Indigo" onclick="javascript:abortarIndigo();" class="boton">
</td>
</tr>
</table>

</body>
</html>
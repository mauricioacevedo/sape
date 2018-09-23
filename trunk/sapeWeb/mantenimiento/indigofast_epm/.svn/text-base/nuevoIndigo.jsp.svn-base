<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- nuevoIndigo.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Esta variable determinara si se trata de una modificacion o de un nuevo registro --%>
<c:set var="tipoOperacion" value="${requestScope.tipoOperacion}" />
<c:set var="listaCentrales" value="${requestScope.listaCentrales}" />
<c:set var="indigo" value="${requestScope.indigo}" />

<html>

<script language="javascript">
	
	function modificarIndigo(){
		var numero = document.getElementById('numero');
		var ip= document.getElementById('ip');
		var puerto = document.getElementById('puerto');
		
		var query = "&id=${indigo.id}";
		query +="&ip="+ip.value;
		
		if(isNaN(numero.value)){
			alert('Ingrese una cantidad numerica para el numero de Indigo.');
			numero.focus();
			return;
		}
		
		query +="&numero="+numero.value;
		
		if(isNaN(puerto.value)){
			alert('Ingrese una cantidad numerica para el puerto del Indigo.');
			puerto.focus();
			return;
		}
			
		query +="&puerto="+puerto.value;
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=doModificarIndigo"+query;
	
	}
	
	function nuevoIndigo(){
		var numero = document.getElementById('numero');
		var ip= document.getElementById('ip');
		var puerto = document.getElementById('puerto');
		
		var query = "";
		query +="&ip="+ip.value;
		
		if(isNaN(numero.value)){
			alert('Ingrese una cantidad numerica para el numero de Indigo.');
			numero.focus();
			return;
		}
		
		query +="&numero="+numero.value;
		
		if(isNaN(puerto.value)){
			alert('Ingrese una cantidad numerica para el puerto del Indigo.');
			puerto.focus();
			return;
		}	
		
		query +="&puerto="+puerto.value;
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=doAgregarIndigo"+query;
		
	}
</script>
<body topmargin="0" leftmargin="0" alink="olive" link="#330099" bgcolor="white">
<jsp:include page="../../encabezado.jsp" flush="true" />
<br><br>
<table width="300" align="center">
<tr bgcolor="black">
	<td align="center" colspan="8">
		<font color="WHITE" class="mensajeCentral">${tipoOperacion=='nuevo'?'Nuevo':'Modificar'} Indigo</font>
</tr>

<tr>
<td align="center" width="40%">Numero de Indigo</td>
<td align="center"><input name="numero" id="numero" value="${indigo.numeroIndigo}" type="text" ${tipoOperacion == 'modificar'?'disabled':''} size="15"></td>
</tr>

<tr>
	<td align="center">Ip Indigo</td>
	<td align="center"><input name="ip" id="ip" value="${indigo.ip}" type="text" size="15"></td>
</tr>


<tr>
	<td align="center">Puerto Indigo</td>
	<td align="center"><input name="puerto" id="puerto" value="${indigo.port}" type="text" size="6"></td>
</tr>

</table>

<br><br>
<center>
<input class="boton" name="almacenarInfo" onclick="javascript:${tipoOperacion=='nuevo'?'nuevoIndigo':'modificarIndigo'}()" value="Guardar" type="button">
<input class="boton" name="cancelar" onclick="javascript:window.back()" value="cancelar" type="button">
</center>


</body>
</html>
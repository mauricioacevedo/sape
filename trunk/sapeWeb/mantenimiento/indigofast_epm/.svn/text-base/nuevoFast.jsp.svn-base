<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- nuevoFast.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Esta variable determinara si se trata de una modificacion o de un nuevo registro --%>
<c:set var="tipoOperacion" value="${requestScope.tipoOperacion}" />
<c:set var="listaIndigos" value="${requestScope.listaIndigos}" />
<c:set var="fast" value="${requestScope.fast}" />

<html>

<script language="javascript">
	
	function modificarFast(){
		var numero = document.getElementById('numero');
		var telefono= document.getElementById('telefono');
		var cabezas1 = document.getElementById('cabezas1');
		var cabezas2 = document.getElementById('cabezas2');
				
		var query = "&id=${fast.id}";
		
		if(central.value.length == 0){
			alert('Ingrese un nombre de central asociado al Fast.');
			numero.focus();
			return;
		}
		
		query +="&central="+central.value;
		
		if(isNaN(numero.value)){
			alert('Ingrese una cantidad numerica para el numero de Fast.');
			numero.focus();
			return;
		}
		
		query +="&numero="+numero.value;
		
		if(isNaN(telefono.value) || telefono.value.length != 7){
			alert('Ingrese una telefono de 7 caracteres para el telefono de Fast.');
			telefono.focus();
			return;
		}
		query +="&telefono="+telefono.value;
		var cabeza1 = cabezas1.options[cabezas1.selectedIndex].value;
		var cabeza2 = cabezas2.options[cabezas2.selectedIndex].value;
		if(cabeza1 == '-1'){
			alert('Selecciones el Indigo de Prueba 1 para el Fast.');
			cabezas1.focus();
			return;
		}
	
		if(cabeza2 == '-1'){
			alert('Selecciones el Indigo de Prueba 2 para el Fast.');
			cabezas2.focus();
			return;
		}
	
		
		query +="&cabeza1="+cabeza1+"&cabeza2="+cabeza2;
		
		var ll = document.getElementById('ll').value;
		var vertical = document.getElementById('vertical').value;
		
		query +="&ll="+ll+"&vertical="+vertical;
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=doModificarFast"+query;
	
	}
	
	function nuevoFast(){
		var central = document.getElementById('central');
		var numero = document.getElementById('numero');
		var telefono= document.getElementById('telefono');
		var cabezas1 = document.getElementById('cabezas1');
		var cabezas2 = document.getElementById('cabezas2');
		
		var query = "";
		
		if(central.value.length == 0){
			alert('Ingrese un nombre de central asociado al Fast.');
			numero.focus();
			return;
		}
		
		query +="&central="+central.value;
		
		if(isNaN(numero.value)){
			alert('Ingrese una cantidad numerica para el numero de Fast.');
			numero.focus();
			return;
		}
		
		query +="&numero="+numero.value;
		
		if(isNaN(telefono.value) || telefono.value.length != 7){
			alert('Ingrese una telefono de 7 caracteres para el telefono de Fast.');
			telefono.focus();
			return;
		}
		query +="&telefono="+telefono.value;
		
		var cabeza1 = cabezas1.options[cabezas1.selectedIndex].value;
		var cabeza2 = cabezas2.options[cabezas2.selectedIndex].value;
		if(cabeza1 == '-1'){
			alert('Selecciones el Indigo de Prueba 1 para el Fast.');
			cabezas1.focus();
			return;
		}
	
		if(cabeza2 == '-1'){
			alert('Selecciones el Indigo de Prueba 2 para el Fast.');
			cabezas2.focus();
			return;
		}
	
		
		query +="&cabeza1="+cabeza1+"&cabeza2="+cabeza2;

		
		var ll = document.getElementById('ll').value;
		var vertical = document.getElementById('vertical').value;
		
		query +="&ll="+ll+"&vertical="+vertical;
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=doAgregarFast"+query;
		
	}
</script>
<body topmargin="0" leftmargin="0" alink="olive" link="#330099" bgcolor="white">
<jsp:include page="../../encabezado.jsp" flush="true" />
<br><br>
<table width="300" align="center">
<tr bgcolor="black">
	<td align="center" colspan="8">
		<font color="WHITE" class="mensajeCentral">${tipoOperacion=='nuevo'?'Nuevo':'Modificar'} Fast</font>
</tr>

<tr>
<td align="center" width="40%">Central</td>
<td align="center"><input name="numero" id="numero" value="${fast.central}" type="text" ${tipoOperacion == 'modificar'?'disabled':''} size="15"></td>
</tr>

<tr>
<td align="center" width="40%">Numero de Fast</td>
<td align="center"><input name="numero" id="numero" value="${fast.numeroFast}" type="text" ${tipoOperacion == 'modificar'?'disabled':''} size="15"></td>
</tr>

<tr>
	<td align="center">Telefono Fast</td>
	<td align="center"><input name="telefono" id="telefono" value="${fast.telefonoFast}" type="text" size="15" ${tipoOperacion == 'modificar'?'disabled':''}></td>
</tr>


<tr>
	<td align="center">Cabeza</td>
	<td align="center">
		<select id="cabezas1">
			<option value="-1">Seleccione:</option>
			
			<c:forEach items="${listaIndigos}" var="indigo">
				<option value="${indigo.id}" ${indigo.id == fast.id?'selected':''}>${indigo.site}</option>
			</c:forEach>
			
		</select>
	</td>
</tr>

<tr>
	<td align="center">Cabeza 2</td>
	<td align="center">
		<select id="cabezas2">
			<option value="-1">Seleccione:</option>
			
			<c:forEach items="${listaIndigos}" var="indigo">
				<option value="${indigo.id}" ${indigo.id == fast.id2?'selected':''}>${indigo.site}</option>
			</c:forEach>
			
		</select>
	</td>
</tr>


<tr>
	<td align="center">LL</td>
	<td align="center"><input name="ll" id="ll" value="${fast.ll}" type="text" size="15"></td>
</tr>


<tr>
	<td align="center">Vertical</td>
	<td align="center"><input name="vertical" id="vertical" value="${fast.vertical}" type="text" size="15"></td>
</tr>

</table>

<br><br>
<center>
<input class="boton" name="almacenarInfo" onclick="javascript:${tipoOperacion=='nuevo'?'nuevoFast':'modificarFast'}()" value="Guardar" type="button">
<input class="boton" name="cancelar" onclick="javascript:window.back()" value="cancelar" type="button">
</center>


</body>
</html>
<!-- nuevoCodigoFalla.jsp -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="fallaIdViejo" class="java.lang.String" scope="request"/>
<jsp:useBean id="nombreViejo" class="java.lang.String" scope="request"/>
<jsp:useBean id="tipo" class="java.lang.String" scope="request"/>

<c:set var="modo" value="nuevo"/>

<c:if test="${not empty fallaIdViejo && not empty nombreViejo}">
<c:set var="modo" value="modificar"/>
</c:if>

<html><head><title>Codigo de Falla - SAPE</title>

<script language="JavaScript">

	function direcciona() {
		forma=this.document.altausuario;
		forma.submit();
	}


	function verif_entradas(forma) {

		if(forma.fallaId.value.length<1) {
			window.alert("Codigo Incorrecto. Intente nuevamente");
			forma.fallaId.focus();
			return;
		}
		if(forma.nombre.value.length<1) {
			window.alert("Nombre NO VALIDO");
			forma.nombre.focus();
			return;
		}
		
		var tipoCombo = document.getElementById("tipos");
		var tipo = tipoCombo.options[tipoCombo.selectedIndex].value;
		
		if(tipo == -1){
			alert('Seleccione el tipo de codigo');
			tipoCombo.focus();
			return;
		}
		
		confirmacion=window.confirm("Estos son los datos de "+tipoCombo.options[tipoCombo.selectedIndex].text+":\nId de Falla="+forma.fallaId.value+"\nNombre="+forma.nombre.value);

		if (confirmacion) Alta(forma.fallaId.value,forma.nombre.value,tipo);
}

function Alta(id,nombre,tipo) {
	location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=hacerInsertCodigoFalla&fallaId="+id+"&nombre="+nombre+"&tipo="+tipo;
}


	function modificar(forma) {

		if(forma.fallaId.value.length<1) {
			window.alert("Codigo Incorrecto. Intente nuevamente");
			forma.fallaId.focus();
			return;
		}
		if(forma.nombre.value.length<1) {
			window.alert("Nombre NO VALIDO");
			forma.nombre.focus();
			return;
		}
		
		var tipoCombo = document.getElementById("tipos");
		
		var tipo = tipoCombo.options[tipoCombo.selectedIndex].value;
		
		
		
		confirmacion=window.confirm("Estos son los datos de "+tipoCombo.options[tipoCombo.selectedIndex].text+" :\nId de Falla="+forma.fallaId.value+"\nNombre="+forma.nombre.value);
		var id =forma.fallaId.value;
		var nombre=forma.nombre.value;
		if (confirmacion){
location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=hacerModificacionCodigoFalla&fallaId="+id+"&nombre="+nombre+"&fallaIdViejo=${fallaIdViejo}&tipo="+tipo;
		}
	}




</script></head>
<jsp:include page="../encabezado.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body topmargin="0" leftmargin="0" alink="olive" bgcolor="white" link="#330099">
<form name="altapermitidos">
<br>

<table align="center" bgcolor="BLACK" width="40%">
<tr>
	<td align="center" class="mensajeCentral">${modo == 'nuevo' ?"Nuevo ":"Modificar "}Codigo</td>
</tr>
</table>

<table align="center" border="1" cols="2" width="40%">

<tr>
<td>
	<font color="BLACK" face="Verdana" size="3">Codigo</font>
</td>
	<td><input name="fallaId" size="20" type="text" value="${modo == 'nuevo' ?'':fallaIdViejo}" class="texto"></td>
</tr>

<tr>
	<td><font color="BLACK" face="Verdana" size="3">Nombre</font></td>
	<td><input name="nombre" type="text" value="${modo == 'nuevo'?'':nombreViejo}" class="texto"></td>
</tr>
<tr>
	<td><font color="BLACK" face="Verdana" size="3">Tipo de Codigo</font></td>
	<td>
		<select name="tipos" id="tipos" ${modo=='modificar'?"disabled":""}>
			<option value="-1">Seleccione:</option>
			<option value="falla" ${tipo == 'falla'?"selected":""}>Codigo de Falla</option>
			<option value="naturaleza" ${tipo == 'naturaleza'?"selected":""}>Naturaleza de Reclamo</option>
		</select>
	
	</td>
</tr>

</table>
<table align="center" border="0" cols="1" width="60%">
<tr>
</tr>

<tr>
<td align="center">
<c:choose>
<c:when test="${modo == 'nuevo'}">
<input name="Alta" onclick="javascript:verif_entradas(document.altapermitidos)" value="agregar codigo" type="button" class="boton">
</c:when>
<c:otherwise>
<input name="Modificar" onclick="javascript:modificar(document.altapermitidos)" value="modificar" type="button" class="boton"> 
</c:otherwise>
</c:choose>
</td>

</tr>
</table>

</form>

</body></html>

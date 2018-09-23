<!-- MantenimientoModificarTipoNodo.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="tiponodo" class="com.osp.sape.maestros.TipoNodo" scope="request" />

<html><head><title>Modificar Tipo de Nodo - SAPE</title>

<script language="JavaScript">
<!--

function verif_entradas(forma) {


	
	
	if(forma.ipservidor.value.length<1) {
		window.alert("Nombre ip servidor NO VALIDO");
		forma.ipservidor.focus();
		return;
	}
	
	if(forma.puertoservidor.value.length<1 || isNaN(forma.puertoservidor.value)) {
		window.alert("Puerto servidor NO VALIDO");
		forma.puertoservidor.focus();
		return;
	}
	
	if(forma.ipcabeza.value.length<1) {
		window.alert("Nombre ip cabeza NO VALIDO");
		forma.ipcabeza.focus();
		return;
	}
	
	if(forma.puertocabeza.value.length<1 || isNaN(forma.puertocabeza.value)) {
		window.alert("Puerto cabeza NO VALIDO");
		forma.puertocabeza.focus();
		return;
	}

	var ipEsclavo = forma.ipesclavo.value;
	var puertoEsclavo = forma.puertoesclavo.value;
	
	if(ipEsclavo.length<1) {
		window.alert("Ingrese un Ip Esclavo.");
		forma.ipesclavo.focus();
		return;
	}
	
	if(puertoEsclavo.length<1 || isNaN(puertoEsclavo)) {
		window.alert("Puerto Esclavo NO VALIDO");
		forma.puertoesclavo.focus();
		return;
	}
	
	
	var cboEstado = forma.estado.options[forma.estado.selectedIndex];
	if (cboEstado.value=="-1") {
		window.alert("Seleccione el estado del Tipo Nodo");
		cboEstado.focus();
		return;
	}
   
	var tipoCabezaNombre = '${tiponodo.tipoCabeza.nombre}';
	var tipoCabezaId = '${tiponodo.tipoCabeza.id}';
	var site = '${tiponodo.site}' ;

		
	confirmacion=window.confirm("Estos son los datos para el Tipo de Nodo:\n" +
				"\nNombre: "+site +
				"\nCabeza: "+tipoCabezaNombre+
				"\nIP Servidor: "+forma.ipservidor.value+
				"\nPuerto Servidor: "+forma.puertoservidor.value+
				"\nIP Cabeza: "+forma.ipcabeza.value+
				"\nPuerto Cabeza: "+forma.puertocabeza.value + 
				"\nIP Esclavo: "+ipEsclavo+
				"\nPuerto Esclavo: "+puertoEsclavo +
				"\nEstado: " + cboEstado.text);

	if (confirmacion) modificar(site,forma.ipservidor.value,forma.puertoservidor.value,forma.ipcabeza.value,forma.puertocabeza.value,tipoCabezaId,ipEsclavo,puertoEsclavo, cboEstado.value);


}

function modificar(nombre,ipservidor,puertoservidor,ipcabeza,puertocabeza,tipocabeza,ipEsclavo,puertoEsclavo, estado) {

	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=realizarModificacionTipoNodo&id=${tiponodo.id}&nombre="+nombre+"&ipservidor="+ipservidor+"&puertoservidor="+puertoservidor+"&ipcabeza="+ipcabeza+"&puertocabeza="+puertocabeza+"&tipocabeza="+tipocabeza+"&estado="+estado+"&ipEsclavo="+ipEsclavo+"&puertoEsclavo="+puertoEsclavo;
	//alert("La informacion se Actualizo con exito.");
	window.opener.location.reload();
	//window.close();
}
//-->
</script>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body topmargin="0" leftmargin="0" alink="olive" link="#330099" bgcolor="white">
<form name="altanodos">
<br>

<table align="center" bgcolor="BLACK" cols="1" width="80%">

<tr align="center">
	<td align="center">
		<font color="white" face="Arial" size="4">
		<b>
			Modificar Tipo de Nodo
		</b>
		</font>
	</td>
</tr>
</table>
<br>
<br>
<table align="center" cols="2" width="80%" border="1">
<tr>
	<td>
		<font color="BLACK" face="Verdana" size="3">
			Nombre SITE
		</font>
	</td>
	<td align="center">
		<font color="BLACK" face="Verdana" size="3">
		${tiponodo.site}
		</font>
	</td>
</tr>


<tr>
	<td>
		<font color="BLACK" face="Verdana" size="3">
			Tipo Cabeza
		</font>
	</td>
	<td align="center">
		<font color="BLACK" face="Verdana" size="3">${tiponodo.tipoCabeza.nombre}
<!--select name="tipocabeza" disabled="true">
	<option value="0">Seleccione</option>
	<c:forEach items="${requestScope.listaCabezas}" var="cabeza">
	<option value="${cabeza.id}"${tiponodo.tipoCabeza.id == cabeza.id? " selected" : ""}>${cabeza.nombre}</option>
	</c:forEach>
</select>
-->
		</font>
	</td>
</tr>

<tr>
<td>
	<font color="BLACK" face="Verdana" size="3">
		Tipo de Central
	</font>
	</td>
	<td align="center"><font color="BLACK" face="Verdana" size="3">
		${tiponodo.tipoCentral}</font>
	</td>
		
</tr>

<tr>
<td colspan="2">
<table width="*">
<tr>
<td align="center" width="25%"><font color="BLACK" face="Verdana" size="3">IP Servidor</font></td>
<td align="center" width="25%"><input class="texto" name="ipservidor" size="15" value="${tiponodo.ipServidor}" maxlength="20" type="text"></td>
<td align="center" width="25%"><font color="BLACK" face="Verdana" size="3">Puerto</font></td>
<td align="center" width="25%"><input class="texto" name="puertoservidor" size="7" value="${tiponodo.puertoServidor}" maxlength="20" type="text"></td>
</tr>
</table>

</td>
</tr>



<tr>
<td colspan="2">

<table width="*">
<tr>
<td align="center" width="25%"><font color="BLACK" face="Verdana" size="3">IP Cabeza</font></td>
<td align="center" width="25%"><input class="texto" name="ipcabeza" size="15" value="${tiponodo.ipCabeza}" maxlength="20" type="text"></td>
<td align="center" width="25%"><font color="BLACK" face="Verdana" size="3">Puerto</font></td>
<td align="center" width="25%"><input class="texto" name="puertocabeza" size="7" value="${tiponodo.puertoCabeza}" maxlength="20" type="text"></td>
</tr>
</table>

</td>
</tr>

<tr>
<td colspan="2">

<table width="*">
<tr>
<td align="center" width="25%"><font color="BLACK" face="Verdana" size="3">Ip Esclavo</font></td>
<td align="center" width="25%"><input class="texto" name="ipesclavo" size="15" value="${tiponodo.ipEsclavo}" maxlength="20" type="text"></td>
<td align="center" width="25%"><font color="BLACK" face="Verdana" size="3">Puerto</font></td>
<td align="center" width="25%"><input class="texto" name="puertoesclavo" size="7" value="${tiponodo.puertoEsclavo}" maxlength="20" type="text"></td>
</tr>
</table>

</td>
</tr>


<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Estado
</font>
</td>
<td>
<select name="estado">
	<option value="-1">Seleccione</option>
	<option value="O"${tiponodo.estado == "O"? " selected" : ""}>Operando</option>
	<option value="F"${tiponodo.estado == "F"? " selected" : ""}>Fuera de Operacion</option>
</select>
</td>
</tr>

<tr>
<td colspan="2">

Ultima modificacion: ${tiponodo.usuarioUltimoCambio}
</td>
</tr>
</table>

<table align="center" border="0" cols="1" width="60%">

<tr>

<td><center>
	<font color="RED" face="verdana" size="4">
	<br>
		<input class="boton" name="AltaTipoNodo" onclick="javascript:verif_entradas(document.altanodos)" value="Modificar" type="button">
		&nbsp;&nbsp;&nbsp;
		<input class="boton" name="close" onclick="javascript:window.close();" value="cerrar" type="button">

	</font>
	</center>
</td>
</tr>
</table>

</form>

</body></html>

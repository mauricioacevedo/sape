					<!-- pantallaRutinaClientes.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% java.util.List listaClientes = (java.util.List) request.getAttribute("listaClientes"); %>
<% java.util.List estadistica = (java.util.List) request.getAttribute("estadistica"); %>
<jsp:useBean id="totalClientes" class="java.lang.String" scope="request"/>
<jsp:useBean id="msg" class="java.lang.String" scope="request"/>
<jsp:useBean id="estado" class="java.lang.String" scope="request"/>
<jsp:useBean id="cliente" class="java.lang.String" scope="request"/>
<jsp:useBean id="destino" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Rutinas Por Clientes</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript">

 function cerrar() {
  parent.window.close();
 }

 function masivo() {
	location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=inicioImporteMasivos";
 }

 function consulta() {
  location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=consultaHorarioClientes";
 }

function verif_entradas() {

	var telefono = document.getElementById('telefono');
	var nombre = document.getElementById('nombre');
	
	var oneChar;
	
	if(telefono.value.length < ${applicationScope.cantDigitosEntorno}) {
		window.alert("CAMPO Telefono NO VALIDO");
	    telefono.focus();
	    return;
	 } else {
	 for (var i = 0; i < telefono.value.length; i++) {
	    oneChar = telefono.value.charAt(i)
	    if (oneChar < "0" || oneChar > "9") {
	      window.alert("CAMPO Telefono NO VALIDO (solo numeros)");
	      telefono.focus();
	      return;
	     }
	   }
	}


  if(nombre.value.length<1) {
      window.alert("Nombre nombre NO VALIDO");
      nombre.focus();
      return;
   }


confirmacion=window.confirm("Telefono="+telefono.value+", Cliente="+nombre.value);


if (confirmacion) Alta(telefono.value,nombre.value);
}

function Alta(telefono,nombre) {
location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=insertarRutinaClientes&telefono="+telefono+"&nombre="+nombre;
}


function baja (telefono) {
if(confirm("BORRAR Cliente con telefono = "+telefono+"?\nSe borraran todos los registros\ncon este telefono."))
    location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=eliminarRutinaClientes&telefono="+telefono+"&cliente=${cliente}";
}


function buscarEstado(estado) {

	location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=pantallaRutinaCliente&estado="+estado+"&cliente=${cliente}";
}


function eliminarRango() {


	var inicio = document.getElementById("inicio");
	var fin = document.getElementById("fin");


	if(isNaN(inicio.value)){
		alert('Ingrese Solo numeros telefonicos de ${applicationScope.cantDigitosEntorno} digitos.');
		inicio.focus();
		return;
	}

	if(isNaN(fin.value)){
		alert('Ingrese Solo numeros telefonicos de ${applicationScope.cantDigitosEntorno} digitos.');
		fin.focus();
		return;
	}

	if(inicio.value > fin.value){
		alert('Rango incorrecto. El telefono inicial debe ser menor al final.');
		inicio.focus();
		return;
	}

	var confirmacion=window.confirm('Eliminar el rango de telefonos\n'+
									'desde: '+inicio.value+' hasta: '+fin.value);

	if(confirmacion){
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=eliminarRangoRutinaClientes&inicio="+inicio.value+"&fin="+fin.value+"&cliente=${cliente}";
	}

}




</script></head>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body topmargin="0" leftmargin="0" bgcolor="WHITE">
<br>
<table align="center" bgcolor="black" width="90%">
<tr>
	<td align="center">
		<font color="white" face="Arial" size="4"><b>Rutinas por Clientes</b></font>
	<tags:ayudas pagina="Importecliente"/></td>
</tr>
</table>
<p>
<CENTER><c:if test="${msg != ''}" ><font color="black" size="3">${msg}</font></c:if></CENTER>
</p>

<table align="center" border="1" cols="2" width="90%">
<tr>
	<td width="30%" align="center"> Telefono </td>
	<td> <input id="telefono" name="telefono" class="texto" size="10" value="" maxlength="10" type="text"> </td>
</tr>

<tr>
	<td width="30%" align="center"> Nombre </td>
	<td> <input id="nombre" name="nombre" class="texto" size="30" value="" maxlength="50" type="text">&nbsp;&nbsp; <input name="Alta" class="boton" onClick="javascript:verif_entradas();" value="insertar" type="button"> </td>
</tr>
<tr>

<td colspan="2" align="center">
		Borrar rango de : <input class="texto" id="inicio" type="text" name="inicio" size="9" maxlength="${applicationScope.cantDigitosEntorno}"> hasta: <input class="texto" id="fin" type="text" name="fin" size="9" maxlength="${applicationScope.cantDigitosEntorno}"> <input class="boton" type="button" name="aceptar" value="eliminar" onclick="javascript:eliminarRango();">
</td>
</tr>

</table>

<br>
<table align="center" border="0" cols="1" width="90%">
<tr>
<td align="center" colspan="2">
	<jsp:include page="../utils/fileUpload.jsp" flush="true" />
</td>
</tr>
<tr>
	<td align="center">
		<!--  input name="Consulta" class="boton" onClick="JavaScript:consulta();" value=" Consultar Horario " type="button"-->
		<input name="masivoss" class="boton" onClick="javascript:masivo();" value="Importe Masivo" type="button">
		<input name="Cerrar" class="boton" onClick="javascript:cerrar();" value=" Cerrar " type="button">
	</td>
</tr>
<tr>
	<td align="center">

	</td>
</tr>

</table>

<table align="center" border="0" cols="10" width="85%" cellspacing="0">
<tr>

<c:set var="todos" value="0"/>

<c:forEach items="${estadistica}" var="linea" >
	<td align="center">
		<a href="javascript:buscarEstado('${linea[1]}');">${linea[1] == 'II'?"Por Realizar":""}${linea[1] == 'ET'?"Realizados":""}${linea[1] == 'PI'?"Prueba Iniciada":""} ${linea[0]}</a>
		<c:set var="todos" value="${todos + linea[0]}"/>
	</td>
</c:forEach>

	<td align="center">
		<a href="javascript:buscarEstado('');">Todos ${todos}</a>&nbsp;
	</td>
</tr>
</table>


<table align="center" bgcolor="black" border="0" cols="1" width="100%">
<tbody>

<c:if test="${empty estado}">
	<c:set var="estado" value=""/>
</c:if>
<tr>
<td align="center" class="mensajeCentral">Numero de registros ${totalClientes} ${not empty estado ?" en estado ":""}${estado} para la rutina por cliente <font color="white">${cliente}</td>
</tr>

</tbody>
</table>
<table align="center" border="0" cols="2" width="100%">
<tbody>
<tr bgcolor="BLACK">
<td align="center" width="25%" class="header-reporte">Telefono</td>
<td align="center" width="65%" class="header-reporte">Nombre</td>
<td align="center" width="10%" class="header-reporte">Estado</td>
</tr>
</tbody>
</table>



<table align="center" border="0" width="100%">
<tbody>
<c:set var="i" value="0" />
<c:forEach items="${listaClientes}" var="cliente">
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	
	<tr class="row${row}">
		<td align="center" width="25%"><a name="baja" title="Borrar Telefono/Cliente" href="javascript:baja(${cliente.telefono});">${cliente.telefono}</a></td>
		<td align="center" width="65%">&nbsp;${cliente.nombre}</td>
		<td align="center" width="10%" class="estado${cliente.estatus}${row}">&nbsp;${cliente.estatus}</td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>

</tbody>
</table>

</body></html>

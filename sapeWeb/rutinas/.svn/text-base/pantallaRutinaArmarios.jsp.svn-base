						<!-- pantallaRutinaArmarios.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% java.util.List listaRutinas = (java.util.List) request.getAttribute("listaClientes"); %>
<% java.util.List estadistica = (java.util.List) request.getAttribute("estadistica"); %>
<jsp:useBean id="totalRutinas" class="java.lang.String" scope="request"/>
<jsp:useBean id="msg" class="java.lang.String" scope="request"/>
<jsp:useBean id="armario" class="java.lang.String" scope="request"/>
<jsp:useBean id="estado" class="java.lang.String" scope="request"/>
<jsp:useBean id="destino" class="java.lang.String" scope="request"/>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<title>SAPE - Rutinas Por Armario</title>
  
<script language="JavaScript">

	function cerrar() {
		parent.window.close();
	}

	function consulta(armario) {
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=consultaHorarioArmarios&armario="+armario;
		return;
	}

	function importar(armario) {
		if(confirm("Esta seguro de importar la informacion\ndel armario "+armario+" ?"))
			location.href="${pageContext.request.contextPath}/actionSape?accion=rutinasArmario&operacion=importar&armario=" + armario + "#fin";
		return;
	}

function eliminar(){
		var forma = document.forma;
		var valor2= "";

		var telABorrar = '';

		var cantTel = forma.chkTel.length;

		if(cantTel == undefined){
			if(forma.chkTel.checked == false){
				return;
			}
			telABorrar = forma.chkTel.value+",";

		}else if(forma.chkTodas.checked){

			telABorrar = 'todos';

		}else{
			for (j = 0; j < cantTel; j++) {
				if(forma.chkTel[j].checked){
					valor2 = forma.chkTel[j].value;
					telABorrar = telABorrar + forma.chkTel[j].value+",";
				}
			}
			if(valor2 == ""){
				return;
			}
		}


	if(confirm("Se borraran los telefonos seleccionados.\nEsta seguro?")){
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=eliminarRutinaArmarios&telefono="+telABorrar+"&armario=${armario}";
	}
}



function seleccionarTodas() {
	var form = document.forma;
	var estado = form.chkTodas.checked;
	var cantColas = form.chkTel.length;

	if(cantColas == undefined){

		form.chkTel.checked = estado;
		return;
	}

	for (i = 0; i < cantColas; i++) {
		form.chkTel[i].checked = estado;
	}
}

function buscarEstado(estado) {

	location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=pantallaRutinaArmario&armario=${armario}&estado="+estado;
}

</script></head>

<body topmargin="0" leftmargin="0" alink="olive" link="#330099">
<br>
<table align="center" bgcolor="black" width="90%">
<tr>
<td>
<font color="white" face="Arial" size="4"></font><center><font color="white" face="Arial" size="4"><b>
Pruebas Rutinarias Por Armario
</b></font><tags:ayudas pagina="Importearmario"/>
</center></td>
</tr>
</table>

<p>
<CENTER><c:if test="${msg != ''}" ><font color="black" size="3">${msg}</font></c:if></CENTER>
</p>
<table align="center" border="0" cols="1" width="90%">
<tr>
</tr>

<tr>
	<td align="center">
		<!-- input type="button" class="boton" name="Consulta" onClick="JavaScript:consulta('${armario}');" value=" Consultar Horario "-->
		<input type="button" class="boton" onClick="javascript:eliminar()" value=" Borrar ">
		<input type="button" class="boton" onClick="javascript:importar('${armario}')" value="Importar En Linea">
	</td>
</tr>
<tr>
<td colspan="3" height="10"></td>
</tr>
<tr>
<td align="center">
	<jsp:include page="../utils/fileUpload.jsp" flush="true" />
</td>
</tr>
<tr>
	<td align="center">
		<input class="boton" name="Cerrar" onClick="javascript:cerrar()" value=" Cerrar " type="button">
	</td>
</tr>

</table>
<br>
<form name="forma">
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


</td>

</tr>
</table>

<table align="center" border="0" cols="1" width="100%" cellspacing="0">

<c:if test="${empty estado}">
	<c:set var="estado" value=""/>
</c:if>

<tr bgcolor="black">
<td><center><font color="white" face="Arial, Helvetica, sans-serif" size="2"><b>Numero de registros <font color="white">${totalRutinas}</font> ${not empty estado ?" en estado ":""}${estado} para el armario <font color="white">${armario}</font></b></font></center></td>
</tr>
</table>
<table align="center" border="0" cols="2" width="100%">
<tr bgcolor="black">
	<td align="center" width="15%" class="header-reporte">Todas<input type="checkbox" name="chkTodas" onClick="seleccionarTodas();"></td>
	<td align="center" width="15%" class="header-reporte">Telefono</td>
	<td align="center" width="15%" class="header-reporte">Tipo Cliente</td>
	<td align="center" width="45%" class="header-reporte">Direccion</td>
	<td align="center" width="10%" class="header-reporte">Estado</td>
</tr>
</table>
<table align="center" border="0" width="100%">
<c:set var="i" value="0" />
<c:forEach items="${listaRutinas}" var="cliente">
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
<tr class="row${row}">
	<td align="center" width="15%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chkTel" value="${cliente.telefono}"> </td>
	<td align="center" width="15%"><i>${cliente.telefono}</i></td>
	<td align="center" width="15%"><i>&nbsp;${cliente.tipoCliente}</i></td>
	<td align="center" width="45%"><font size="-1">&nbsp;${cliente.direccion}</font></td>
	<td align="center" width="10%" class="estado${cliente.estatus}${row}"><i>&nbsp;${cliente.estatus}</i></td>
	<c:set var="i" value="${i +1}"/>
</tr>
</c:forEach>
</table>
</form>

</body>
</html>

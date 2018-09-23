			<!-- pantallaRutinaCables.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jsp2" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sapeTaglib" uri="/WEB-INF/tags/sape-taglib.tld" %>
<% java.util.List listaRutinas = (java.util.List) request.getAttribute("listaClientes"); %>
<% java.util.List estadistica = (java.util.List) request.getAttribute("estadistica"); %>
<jsp:useBean id="totalRutinas" class="java.lang.String" scope="request"/>
<jsp:useBean id="msg" class="java.lang.String" scope="request"/>
<jsp:useBean id="cable" class="java.lang.String" scope="request"/>
<jsp:useBean id="estado" class="java.lang.String" scope="request"/>
<jsp:useBean id="destino" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Rutinas Por Cable</title>

<script language="JavaScript">

 function cerrar() {
  parent.window.close();
 }

	function importar(cable) {
		<%--jsp2:set var="tipoCable" value="" />
		<jsp2:if test="${sapeTaglib:isVisible('OPCION_CABLE_DIRECTO')}">	
			var tipo= document.getElementById("tipo_cable_metrotel");
			
			if(tipo.checked){//es un cable de red directa.
				if(confirm("Esta seguro de importar la informacion\ndel cable de red directa "+cable+" ?"))
   				location.href="${pageContext.request.contextPath}/actionSape?accion=rutinasCable&operacion=importar&tipoCable=directo&cable=" + cable + "#fin";
   				return;
			}
			<jsp2:set var="tipoCable" value="&tipoCable=primario" />
			// lo puedo dejar seguir y asumo que es un cable de red primaria.
		</jsp2:if--%>

		if(confirm("Esta seguro de importar la informacion\ndel cable "+cable+" ?"))
   			location.href="${pageContext.request.contextPath}/actionSape?accion=rutinasCable&operacion=importar&cable=" + cable + "#fin";
   		return;
	}

 function consulta(cable) {
	location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=consultaHorarioCables&cable="+cable;
 }

  function verif_entradas(forma) {


  var oneChar;

  if(forma.telefono.value.length<7) {
     window.alert("CAMPO Telefono NO VALIDO");
     forma.telefono.focus();
      return;
   } else {
    for (var i = 0; i < forma.telefono.value.length; i++) {
      oneChar = forma.telefono.value.charAt(i)
      if (oneChar < "0" || oneChar > "9") {
        window.alert("CAMPO Telefono NO VALIDO (solo numeros)");
        forma.telefono.focus();
        return;
       }
     }
  }


  if(forma.nombre.value.length<1) {
      window.alert("Nombre nombre NO VALIDO");
      forma.nombre.focus();
      return;
   }


confirmacion=window.confirm("Telefono="+forma.telefono.value+", Cliente="+forma.nombre.value);


if (confirmacion) Alta(forma.telefono.value,forma.nombre.value);
}


function eliminar(){

		var form = document.forma;
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
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=eliminarRutinaCables&telefono="+telABorrar+"&cable=${cable}";
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



function baja (telefono) {

var central = "${central}";
var cabl = "${cable}";
if(confirm("BORRAR Cliente= "+telefono))
    location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=eliminarRutinaCables&telefono="+telefono+"&central="+central+"&cable="+cabl;
}

function buscarEstado(estado) {

	location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=pantallaRutinaCable&cable=${cable}&estado="+estado;
}

</script></head>

<body topmargin="0" leftmargin="0" bgcolor="WHITE">
<script language="JavaScript" src="javascript/common.js"> </script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">

<br>
<table align="center" bgcolor="black" width="90%">
<tr>
<td align="center">
	<font color="white" face="Arial" size="4"><b>
	Pruebas Rutinarias Por Cable
	</b></font><tags:ayudas pagina="Importecable"/>
</td>
</tr>
</table>

<p>
<CENTER><jsp2:if test="${msg != ''}" ><font color="black" size="3">${msg}</font></jsp2:if></CENTER>
</p>
<table align="center" border="0" cols="1" width="90%">
<tr>

<td align="center">
	<!-- input class="boton" name="Consulta" onClick="JavaScript:consulta('${cable}');" value=" Consultar Horario " type="button"-->
	<input class="boton" onClick="javascript:eliminar();" value=" Borrar " type="button">
	<input class="boton" onClick="javascript:importar('${cable}')" value="Importar En Linea"  type="button">
	
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
<table align="center" border="0" cols="10" width="100%" cellspacing="0">
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


<table align="center" border="0" cols="1" width="100%" cellspacing="0">
<tr bgcolor="black">
<c:if test="${empty estado}">
	<c:set var="estado" value=""/>
</c:if>
<td align="center" class="mensajeCentral">
	Numero de registros ${totalRutinas} ${not empty estado ?" en estado ":""}${estado} para el Cable ${cable}</td>
</tr>
</table>


<table align="center" border="0" cols="2" width="100%">
<tr bgcolor="black">
<td align="center" width="15%" class="header-reporte">Todas <input type="checkbox" name="chkTodas" onClick="seleccionarTodas();"></td>
<td align="center" width="15%" class="header-reporte">Telefono</td>
<td align="center" width="15%" class="header-reporte">Tipo Cliente</td>
<td align="center" width="45%" class="header-reporte">Direcci&oacute;n</td>
<td align="center" width="10%" class="header-reporte">Estado</td>
</tr>
</table>

<table align="center" border="0" width="100%">
<c:set var="i" value="0" />
<jsp2:forEach items="${listaRutinas}" var="cliente">
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
<tr class="row${row}">
	<td  align="center" width="15%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chkTel" value="${cliente.telefono}"> </td>
	<td align="center" width="15%"><i>${cliente.telefono}</i></td>
	<td align="center" width="15%"><i>&nbsp;${cliente.tipoCliente}</i></td>
	<td align="center" width="45%"><font size="-1">&nbsp;${cliente.direccion}</font></td>
	<td align="center" width="10%" class="estado${cliente.estatus}${row}"><i>&nbsp;${cliente.estatus}</i></td>
	<c:set var="i" value="${i +1}"/>
</tr>
</jsp2:forEach>
</table>
</form>

</body></html>

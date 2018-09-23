				<!-- contingenciaSiplexPRO.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="listaTipoNodo" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="hostCyclades" class="java.lang.String" scope="request"/>

<html>
<title>SAPE - Contingencia SiplexPRO</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript" src="javascript/varios.js"></script>
<script language="JavaScript">

	function buscarCabezas() {
		var cboCentral = document.getElementById("cboCentral");
		var cboCabeza = document.getElementById("cboCabeza");
		var central = cboCentral.options[cboCentral.selectedIndex].value;

		//alert("buscarCabezas " + central);

		if(central == "-1") {
			cboCabeza.options.length = 1;
			return;
		}
		var url = "${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono&operacion=buscarSite&central=" + central;
		//alert(url);
		cargarHttpRequest(url, "GET", recibirCabezas);
	}


	function recibirCabezas() {
		//alert("recibirCabezas");
		var cboCabeza = document.getElementById("cboCabeza");
	    if (req.readyState == 4) {
	        if (req.status == 200) {
				var respuesta = req.responseXML;

				//lleno el combo de los tipos de las cabezas
				var XMLTipoPruebas = req.responseXML.getElementsByTagName("cabezas")[0];

				cboCabeza.options.length = 1;
				for (i = 0; i < XMLTipoPruebas.childNodes.length; i++) {
					var opcion = XMLTipoPruebas.childNodes[i].childNodes[0].nodeValue;
					var data = opcion.split(",");
					addOpcion(cboCabeza,data[0],data[1]);
				}
	        }
    	}
	}

function cambiar() {

		var valor2= "";
		var forma = document.forma;
		var tiponodos = '';
		var cantTel = forma.chk.length;

		if(cantTel == undefined){
			if(forma.chk.checked == false){
				return;
			}
			tiponodos = forma.chk.value+",";

		}else if(forma.todas.checked){

			tiponodos = 'todos';

		}else{

			for (j = 0; j < cantTel; j++) {
				if(forma.chk[j].checked){
					valor2 = forma.chk[j].value;
					tiponodos = tiponodos + forma.chk[j].value+",";
				}
			}
			if(valor2 == ""){
				return;
			}
		}


	if(confirm("Se cambiara el tipo de conexion de las cabezas seleccionadas.\nEsta seguro?")){

			location.href = "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=contingenciaSiplexPROCambiar&tipoNodos=" + tiponodos;

	}
}


function seleccionarTodas() {

	var form = document.forma;
	var estado = form.todas.checked;
	var cantColas = form.chk.length;

	if(cantColas == undefined){

		form.chk.checked = estado;
		return;
	}

	for (i = 0; i < cantColas; i++) {
		form.chk[i].checked = estado;
	}
}



</script>
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
<jsp:include page="../../encabezado.jsp" flush="true" />
<br>
<br>
<div align="center"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoSiplexPRO">Regresar</a></div>
<br>
<br>
<form name="forma">
<table align="center" width="80%" >
	<tr bgcolor="black">
		<td align="center" colspan="6" class="mensajeCentral" height="30">Contingencia SiplexPRO <tags:ayudas pagina="Contingencia"/></td>
	</tr>

<tr bgcolor="#BDB5A5">
	<td class="header-reporte" align="center">Cambiar<br><input id="todas" type="checkbox" value="todas" name="todas" onClick="seleccionarTodas();"></td>
	<td class="header-reporte" align="center">ID</td>
	<td class="header-reporte" align="center">Site</td>
	<!--td>IP Servidor</td-->
	<td class="header-reporte" align="center">IP Cabeza</td>
	<td class="header-reporte" align="center">Ip Esclavo</td>
	<td class="header-reporte" align="center">Tipo Conexion</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaTipoNodo}" var="tiponodo">

	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}">
		<td align="center"><input id="chk" name="chk" type="checkbox" value="${tiponodo.id}"></td>
		<td align="center" height="30">${tiponodo.id}</td>
		<td align="center">${tiponodo.site}</td>
		<!--td align="center">${tiponodo.ipServidor} : ${tiponodo.puertoServidor}</td-->
		<td align="center">${tiponodo.ipCabeza} : ${tiponodo.puertoCabeza}</td>
		<td align="center">${tiponodo.ipEsclavo} : ${tiponodo.puertoEsclavo}</td>
		<td align="center"${tiponodo.ipEsclavo == hostCyclades?" class=estadoII":""}${row}>${tiponodo.ipEsclavo == hostCyclades?"Modem":"Red Corporativa"}</td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>
</table>
<br>
<div align="center"><input type="button" onClick="cambiar();" value="Cambiar" class="boton"></div>
</form>
<br>
<br>
<div align="center"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoSiplexPRO">Regresar</a></div>
</body>
</html>



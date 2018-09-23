				<!-- desbloqueoSiplexPRO.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="listaCentrales" class="java.util.ArrayList" scope="request"/>
<html>
<title>SAPE - Desbloqueo SiplexPRO</title>
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

function desbloquear() {
	var cboCentral = document.getElementById("cboCentral");
	var cboCabeza = document.getElementById("cboCabeza");

	if (cboCentral.selectedIndex == 0) {
		alert("Seleccione Central");
		return;
	}
	
	if (cboCabeza.selectedIndex == 0) {
		alert("Seleccione Cabeza");
		return;
	}
	var nombreCabeza = cboCabeza.options[cboCabeza.selectedIndex].text;
		var codCabeza = cboCabeza.options[cboCabeza.selectedIndex].value;
	if (!confirm("Confirma que desea desbloquear la cabeza \r\n" + nombreCabeza + " (" + codCabeza + ")?")) 
		return;

	location.href = "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=desbloqueoSiplexPRO&desbloquear=" + codCabeza;


}


</script>
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
<jsp:include page="../../encabezado.jsp" flush="true" />
<br>
<br>

<table align="center" width="350" >
	<tr bgcolor="black">
		<td align="center" valign="middle" class="mensajeCentral" height="30">Desbloqueo de SiplexPRO<tags:ayudas pagina="Desbloqueo"/></td>
	</tr>
	<tr>
		<td align="center" valign="middle" height="40">
			1. Seleccione Central
			<select id="cboCentral" onChange="javascript:buscarCabezas();">
				<option value="-1">Seleccione: </option>
				<c:forEach items="${listaCentrales}" var="central">
				<option value="${central}"> ${central} </option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td align="center" valign="middle" height="40">
			2. Seleccione Cabeza <select id="cboCabeza">
				<option value="-1">Seleccione: </option>
			</select>
		</td>
	</tr>
</table>
<br>
<div align="center"><input id="btnDesbloquear" type="button" onClick="desbloquear();" value="Desbloquear" class="boton"></div>
<br>
<br>
<div align="center"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoSiplexPRO">Regresar</a></div>
</body>
</html>



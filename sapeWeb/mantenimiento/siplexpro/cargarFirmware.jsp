						<!-- cargarFirmware.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="listaCentrales" class="java.util.ArrayList" scope="request"/>

<html>
<title>SAPE - Cargar Firmware</title>
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

function procesar() {
	var cboCentral = document.getElementById("cboCentral");
	var cboCabeza = document.getElementById("cboCabeza");

	var fileManager = document.getElementById("fileManager");
	
	if (cboCentral.selectedIndex == 0) {
		alert("Seleccione Central");
		return;
	}
	
	if (cboCabeza.selectedIndex == 0) {
		alert("Seleccione Cabeza");
		return;
	}
	
	if(fileManager.value== null || fileManager.value == ''){
		alert("Seleccione el Firmware para actualizar la cabeza.");
		fileManager.focus();
		return;
	}
	
	
	var nombreCabeza = cboCabeza.options[cboCabeza.selectedIndex].text;
	var codCabeza = cboCabeza.options[cboCabeza.selectedIndex].value;
	if (!confirm("A continuacion se actualizara el firmware de la cabeza \r\n" + nombreCabeza + " (" + codCabeza + "). Esta seguro ?")) 
		return;

	
	var destino = document.getElementById("destino");
	destino.value= "${requestScope.destino}&idCabeza="+codCabeza;
	var forma = document.getElementById("formaUp");
	
	forma.submit();
}


</script>
<body bgcolor="white">
<jsp:include page="../../encabezado.jsp" flush="true" />
<br>
<br>

<table align="center" width="500" >
	<tr bgcolor="black">
		<td align="center" valign="middle" class="mensajeCentral" height="30" colspan="2">
			Cargar Firmware SiplexPRO
		</td>
	</tr>
	<tr>
		<td align="center" valign="middle" height="40" colspan="2">
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
		<td align="center" valign="middle" height="50" colspan="2">
			2. Seleccione Cabeza <select id="cboCabeza">
				<option value="-1">Seleccione: </option>
			</select>
			<br>
			<font size="-1" color="RED">Recuerde: La Cabeza debe estar Deshabilitada</font>
		</td>
	</tr>
	<tr>
		<td align="center" valign="middle" height="40" width="50%">
			3. Seleccione el firmware 
		</td>
		<td align="center" valign="middle" height="40">
			<form name="formaUp" id="formaUp" action="${pageContext.request.contextPath}/utils/UploadServlet" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="destino" value="" id="destino">
			<input id="fileManager" name="file1" type="file" />
			</form>
		</td>
	</tr>
</table>
<br>
<div align="center"><input id="btnProcesar" type="button" onClick="procesar();" value="Cargar" class="boton"></div>
<br>
<br>
<c:if test="${requestScope.firmwareIniciado == true}">
<div align="center"><font color="red">Ya hay una Actualizaci&oacute;n de  Firmware Corriendo.</font></div>
</c:if>
<div align="center">
<a href="${pageContext.request.contextPath}/actionSape?accion=firmware&operacion=progresoFirmware">Progreso Firmware</a>
</div>
<br>
<br>
<div align="center"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoSiplexPRO">Regresar</a></div>

</body>
</html>



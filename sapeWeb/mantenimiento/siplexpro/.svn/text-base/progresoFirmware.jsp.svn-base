					<!-- progresoFirmware.jsp -->
<jsp:useBean id="firmware" class="com.osp.sape.utils.Firmware" scope="request" />
<html>
<head>
<title>SAPE - Progreso Firmware</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript" src="javascript/varios.js"></script>
<script language="javascript">

var req;  	//con esta hago las conexiones al sape.
var tiempoRefresco = 10000;
var refrescar = true;		//si se sigue refrescando o para.


function consultarEstado(tipo) {
		//tipo puede ser manual (1) o automatico (0)
	if (!refrescar) return;
	if (tipo == 0) funcion = recibirEstadoAuto;
	else funcion = recibirEstadoManual;
	cargarHttpRequest("${pageContext.request.contextPath}/actionSape?accion=firmware&operacion=estadoProceso", "GET", funcion);
}


function recibirEstadoAuto () {
	if (req.readyState == 4) {
        if (req.status == 200) {
		     		
     		cargarRequest();
     			
     		if (refrescar) {
				setTimeout("consultarEstado(0)", tiempoRefresco);
			}
        }
   	}
}


//este no maneja el timeout del recibirestadoAuto
function recibirEstadoManual () {
	if (req.readyState == 4) {
        if (req.status == 200) {
     		cargarRequest();
        }
   	}
}


function cargarRequest() {
	//alert("recibo: " + req.responseText);
 	var raiz = req.responseXML.getElementsByTagName("firmware")[0];
 	var estado = raiz.getElementsByTagName("estado")[0].childNodes[0].nodeValue;
 	var progreso = raiz.getElementsByTagName("progreso")[0].childNodes[0].nodeValue;
 	var duracion = raiz.getElementsByTagName("duracion")[0].childNodes[0].nodeValue;
 	var restante = raiz.getElementsByTagName("restante")[0].childNodes[0].nodeValue;
 	
 	if (estado == "[ERROR] No hay firmware iniciado." || estado == "ERROR" || estado == "[ERROR] No Socket.") {
    	document.getElementById("btnCancelar").disabled = true;
		document.getElementById("btnEstado").disabled = true;
 		refrescar = false;
 	}
 		
 	if (estado == "Carga Completa." || estado == "Comprobando Archivo." || estado == "Actualizando Firmware.") {
			//cuando el archivo ya esta montado no se puede cancelar el proceso.
		document.getElementById("btnCancelar").disabled = true;
 	}

	if (estado == "Firmware Cargado. Actualizacion Exitosa!") {
		alert("Actualizacion Exitosa!");
		document.getElementById("btnEstado").disabled = true;
 		refrescar = false;
 	}
 		
 	document.getElementById("estado").innerHTML = estado;
 	document.getElementById("progreso").innerHTML = progreso;
 	document.getElementById("barraProgreso").width = progreso + "%";
 	document.getElementById("duracion").innerHTML = duracion;
 	document.getElementById("restante").innerHTML = restante;     		
}

function cancelarUpdate() {
	if (confirm("Cancelar la Actualizacion?")) {
		document.getElementById("btnEstado").disabled = true;
		document.getElementById("btnCancelar").disabled = true;
   		refrescar = false;
		cargarHttpRequest("${pageContext.request.contextPath}/actionSape?accion=firmware&operacion=cancelarUpdate", "GET", recibirCancelar);
	}
}


function recibirCancelar() {
	if (req.readyState == 4) {
        if (req.status == 200) {
    		alert("Respuesta de Cancelacion: \n" + req.responseText);
        }
   	}
}
</script>
</head>
<body bgcolor="white" onload="consultarEstado(0);">
<jsp:include page="../../encabezado.jsp" flush="true" />
<br>
<br>
<table align="center" width="400">
<tr>
	<td colspan="2" align="center" class="mensajeCentral">
		Cabeza Actual
	</td>
</tr>
<tr>
	<td colspan="2">
	Cabeza: ${firmware.site}
	</td>
</tr>
<tr>
	<td>
	Ip: ${firmware.ipCabeza}
	</td>
	<td>
	Puerto: ${firmware.puertoCabeza}
	</td>
</tr>
<tr>
	<td>
	Versi&oacute;n: ${firmware.version}
	</td>
	<td>
	Archivo Binario: ${firmware.binFile}
	</td>
</tr>
<tr>
	<td>
	Tama&ntilde;o: ${firmware.tamano}
	</td>
	<td>
	Paquetes: ${firmware.paquetes}
	</td>
</tr>
</table>

<br>
<table width="550" border="1" align="center">
<tr>
	<td colspan="3" align="center">
		ESTADO ACTUALIZACION
	</td>
</tr>
<tr>
	<td>
		Progreso: <span id="progreso">0</span> %
	</td>
	<td>
		Tiempo Restante Aprox.: <span id="restante"></span> s.
	</td>
	<td>
		Duraci&oacute;n: <font color="blue"><span id="duracion"></span></font> s.
	</td>
</tr>
<tr height="45">
	<td colspan="3" align="left" valign="middle">
		<table width="0" border="0" height="25" cellspace="0" cellpadding="0" id="barraProgreso" bgcolor="blue">
		<tr><td></td></tr>
		</table>
	</td>
</tr>
<tr>
	<td colspan="3" id="estado" align="center">
		Progreso Actualizaci&oacute;n
	</td>
</tr>
</table>

<br>
<div align="center">
	<input type="button" id="btnEstado" value="Refrescar" onclick="consultarEstado(1);"> 
	&nbsp; - &nbsp; 
	<input type="button" id="btnCancelar" value="Cancelar" onclick="cancelarUpdate();">
</div>
</body>
</html>
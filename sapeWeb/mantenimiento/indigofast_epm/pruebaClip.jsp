			<!-- pruebaClip.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sapeTaglib" uri="/WEB-INF/tags/sape-taglib.tld" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- Los fast que se pretenden probar --%>
<c:set var="listaCentral" value="${requestScope.listaCentral}" />
<c:set var="listaIndigo" value="${requestScope.listaIndigo}" />

<html>
<head>

<title>SAPE - Pruebas Telef&oacute;nicas</title>
<style type="text/css">
	div.n1{background: #ffaa00}
	div.n2{background: #ffaa00}
	div.n3{background: #ffaa00}
	div.n4{background: #ffaa00}
	.pruebaTerminada a:link { font-weight: bold; font-size: 14px; color: blue; text-decoration: none }
	.pruebaTerminada a:visited { font-weight: bold; font-size: 14px; color: blue; text-decoration: none }
	.pruebaTerminada a:hover { font-weight: bold; font-size: 14px; color: blue; text-decoration: none }
</style>

<link rel="stylesheet" type="text/css" href="javascript/curvas/niftyCorners.css">
<link rel="stylesheet" type="text/css" href="javascript/curvas/niftyPrint.css" media="print">
<script type="text/javascript" src="javascript/curvas/nifty.js"></script>

<script language="JavaScript">

function check() {
	if(!NiftyCheck())
    	return;

	Rounded("div#n3","bottom","#FFF","transparent");
	Rounded("div#n1","top","#FFF","transparent");
	Rounded("div#n4","bottom","#FFF","transparent");

}

</script>

<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="javascript">

var COLOR_MAL = "RED";
var COLOR_BIEN = "WHITE";
var COLOR_FOCUS = "#ffff80";
	//con esta hago las conexiones al sape.
var req;


function cargarHttpRequest(url, metodo, funcion) {
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    req.open(metodo, url, true);
    req.onreadystatechange = funcion;
    req.send(null);
}

function probar() {
	var centralLista = document.getElementById("central");
	var indigoLista = document.getElementById("indigo");
	var fast = document.getElementById("fast").value;
	var telefono = document.getElementById("telefono").value;
	var id = document.getElementById("id").value;
	var ip = document.getElementById("ip").value;
	var puerto = document.getElementById("puerto").value;
	var ipCabeza = document.getElementById("ipCabeza").value;
	var puertoCabeza = document.getElementById("puertoCabeza").value;
	
	var central = centralLista.options[centralLista.selectedIndex].value;
	var indigo = indigoLista.options[indigoLista.selectedIndex].text

	var estado = document.getElementById("estado");

	var btnProbar = document.getElementById("btnProbar");
	var btnInteractiva = document.getElementById("btnInteractiva");
	var btnNueva = document.getElementById("btnNueva");
	var btnDetalles = document.getElementById("btnDetalle");

	var iframDetalles = document.getElementById("iframDetalles");

	var codv = document.getElementById("codigoVer");
	var desc = document.getElementById("descCodv");

	codv.innerHTML = "";
	desc.innerHTML = "";

	centralLista.disabled = true;
	indigoLista.disabled = true;
	
	estado.innerHTML = "Estado: <font color=\"red\">" + "PROBANDO..." + " </font>";
	
	btnProbar.disabled = true;
	btnInteractiva.disabled = true;
	btnNueva.disabled = true;
	btnDetalles.disabled = false;
	btnDetalles.focus();

	//aqui ya comienzo la prueba
	var url = "${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono&operacion=pruebaClipFTX&id="+id+"&tel=" + telefono + "&tipoPrueba=clipFTX&central="+central+"&fast="+fast+"&indigo="+indigo+"&ip="+ip+"&puerto="+puerto+"&ipCabeza="+ipCabeza+"&puertoCabeza="+puertoCabeza;
	iframDetalles.src = url;
}


function nuevaPrueba() {
	var listaCentral = document.getElementById("central");
	var listaIndigo = document.getElementById("indigo");
	var txtFast = document.getElementById("fast");
	var txtTelefono = document.getElementById("telefono");

	var estado = document.getElementById("estado");
	var btnProbar = document.getElementById("btnProbar");
	var btnInteractiva = document.getElementById("btnInteractiva");

	var btnNueva = document.getElementById("btnNueva");
	var btnDetalles = document.getElementById("btnDetalle");
	var iframDetalles = document.getElementById("iframDetalles");


	var codv = document.getElementById("codigoVer");
	var desc = document.getElementById("descCodv");

	codv.innerHTML = "";
	desc.innerHTML = "";

	estado.innerHTML = "Estado: No hay prueba.";
	btnProbar.disabled = false;
	btnInteractiva.disabled = false;
	btnNueva.disabled = true;
	btnDetalles.disabled = true;

	iframDetalles.src = "about:blank";
	//txtTelefono.value = "";
	//listaCentral.value = "";
	//txtFast.value = "";
	
	listaCentral.disbaled = false;
	listaIndigo.disabled = false;
	listaCentral.focus();
}

function recuperarFastSerie() {
    if (req.readyState == 4) {
        if (req.status == 200) {
			var estado = document.getElementById("estado");
			var respuesta = req.responseXML;
						
			fast = document.getElementById("fast");
			telefono = document.getElementById("telefono");
			id = document.getElementById("id");
			ip = document.getElementById("ip");
			puerto = document.getElementById("puerto");
			ipCabeza = document.getElementById("ipCabeza");
			puertoCabeza = document.getElementById("puertoCabeza");
			
			fast.value = respuesta.getElementsByTagName("fast")[0].childNodes[0].nodeValue;
			
			telefono.value = respuesta.getElementsByTagName("telefono")[0].childNodes[0].nodeValue;
			
			id.value = respuesta.getElementsByTagName("id")[0].childNodes[0].nodeValue;
			
			ip.value = respuesta.getElementsByTagName("ip")[0].childNodes[0].nodeValue;
			puerto.value = respuesta.getElementsByTagName("puerto")[0].childNodes[0].nodeValue;
			
			ipCabeza.value = respuesta.getElementsByTagName("ipCabeza")[0].childNodes[0].nodeValue;
			puertoCabeza.value = respuesta.getElementsByTagName("puertoCabeza")[0].childNodes[0].nodeValue;
			
			
			if (fast.value != "NOT FOUND" && telefono.value != "NOT FOUND") {
				btnProbar.disabled = false;

			}
        }
    }
}


function foco(campo, entra) {
	if (entra) {
		campo.style.background = COLOR_FOCUS;
	} else {
		campo.style.background = COLOR_BIEN;
	}
}


function pruebaTerminada(prueba, ticketPrueba, codv, desc) {
	
	var central = document.getElementById("central");
	var indigo = document.getElementById("indigo");
	
	var estado = document.getElementById("estado");
	var btnNueva = document.getElementById("btnNueva");
	var btnProbar = document.getElementById("btnProbar");
	var btnInteractiva = document.getElementById("btnInteractiva");
	var btnDetalle = document.getElementById("btnDetalle");


	var codigoVer = document.getElementById("codigoVer");

	var descCodv = document.getElementById("descCodv");

	var operacion = "mostrarResultados";

	estado.innerHTML = "<div class=\"pruebaTerminada\">Estado: <a href=\"javascript:mostrarDetallePrueba("+ ticketPrueba + ", '" + operacion + "')\">" + "Prueba Terminada" + "</a></div>";
	codigoVer.innerHTML = "Codigo Ver: <font color=\"red\">"+codv+"</font>";
	descCodv.innerHTML = "Descripcion: <font color=\"red\">"+desc+"</font>";
	
	central.disabled = false;
	indigo.disabled = false;
	
	btnNueva.disabled = false;
	btnProbar.disabled = false;
	btnInteractiva.disabled = false;
}


function mostrarDetallePrueba(idprueba, operacion) {
	var detalle=window.open("/sape/actionSape?accion=pruebaTelefono&operacion=" + operacion + "&ticket="+idprueba, 'window900','scrollbars=yes,resizable=yes,hotkeys=yes,height=600,width=650,left=0,top=0,menubar=yes,toolbar=no');
	detalle.focus();
}


function historicoCentral() {
	var listaCentrales = document.getElementById("listaCentral");
	listaTelefonos.options.length = 0;
	var url = "${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono&operacion=historicoTelefonos&idUsuario=${sessionScope.usuario.id}";
	cargarHttpRequest(url, "GET", eventoHistorico);
}


function eventoHistorico() {
	var listaTelefonos = document.getElementById("listaTelefonos");
	if (req.readyState == 4) {
		if (req.status == 200) {
			var XMLTelefonos = req.responseXML.getElementsByTagName("telefono")[0];
			for (i = 0; i < XMLTelefonos.childNodes.length; i++) {
				var opcion = XMLTelefonos.childNodes[i];
				addOpcion(listaTelefonos,opcion.getAttribute("idPrueba"),opcion.childNodes[0].nodeValue);
			}
		}
	}
}


function mostrarDetalle() {
	var capa = document.getElementById('divFrame');
	var iframe = document.getElementById('iframDetalles');

	if(capa.style.visibility == "visible"){
		capa.style.visibility = "hidden";
		iframe.height = 65;
	} else {
		capa.style.visibility = "visible";
		iframe.height = 200;
	}
}


function mostrarHistorico() {
	var listaTelefonos = document.getElementById("listaTelefonos");
	var prueba = listaCentrales.options[listaTelefonos.selectedIndex].value;
	mostrarDetallePrueba(prueba, "mostrarResultados");
}

function inicializarPagina() {
	historicoCentrales();

	document.getElementById("central").focus();
	check();
}


function update() {
	var central = document.getElementById("central");
	var nuevaCentral = central.options[central.selectedIndex].value;
	var nuevoIndigo = indigo.options[indigo.selectedIndex].value;
//	var nuevoFast = fast.options[fast.selectedIndex].value;

	var funcion = recuperarFastSerie;
	//cargarHttpRequest("${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=buscarFastSerie&central=" + nuevaCentral + "&fast=" + nuevoFast + "&indigo=" + nuevoIndigo, "GET", funcion);
	cargarHttpRequest("${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=buscarFastSerie&central=" + nuevaCentral + "&indigo=" + nuevoIndigo, "GET", funcion);
	return true;
}

function interactiva(){

	var telCliente = document.getElementById("telefono");
	var ip = document.getElementById("ipCabeza").value;
	var puerto = document.getElementById("puertoCabeza").value;

	//alert('la prueba: '+prueba+' telefono: '+telCliente.value)

	//var telOper = document.getElementById("telefono").value;

	//alert('el telefono: '+telOper);
	//if ( telOper == '') {
		telOper = prompt('Ingrese el telefono del Operador');

		if(telOper == '' || telOper == null || telOper == 'null'){
			return;
		}

		if (isNaN(telOper) || telOper.length != ${applicationScope.cantDigitosEntorno} ){
			alert("Ingrese una cantidad numerica de ${applicationScope.cantDigitosEntorno} digitos.");
			return;
		}
		//var divContac = document.getElementById("divContacto");
		//divContac.innerHTML = "<font color='red'>Contacto:</font> "+telOper;
	//}


//alert('pasa a abrir la ventana');
	var winInter = window.open("${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono&operacion=telnetInteractivaClipFTX&telCliente="+telCliente.value+"&telOperador="+telOper+"&ip="+ip+"&puerto="+puerto+"&tipoPrueba=clip",'Mantenimiento','scrollbars=yes,hotkeys=yes,height=460,width=450,left=50,top=50,menubar=no,toolbar=no');
	winInter.focus();
}

//
--></script>

</head>

<jsp:include page="../../encabezado.jsp" flush="true" />
<body bgcolor="white" onload="check();" topmargin="0" leftmargin="0" marginwidth="0" marginheight="0">

<br>
<div align="center" class="tituloPagina"><img src="imagenes_sape/pruebaClip.gif" name="titulo"  border="0" height="20"><tags:ayudas pagina="Clip"/></div>

<br>
<table width="100%" border="0">
<tr>
<td width="*">
	<input type="hidden" name="hndCabezaId" id="hndCabezaId" value="">

	<table width="100%" height="100%">
	<tr>
		<td width="50%" height="50%" valign="top">
		<div id="news">
			<div class="n1" id="n1" align="left">
				&nbsp;
				Prueba Clip
				<div class="n4" id="n4" style="position:relative;visibility:hidden;background-color:#ffaa00;"></div>
			</div>
			<div class="n2" id="n2" align="center" style="position:relative;visibility:visible;background-color:#ffaa00;">

				<table width="100%" height="100%" cellspacing="0">
					<tr>
						<td></td> <!-- borde de la prueba -->
						<td bgcolor="white" width="100%" height="100%" align="center">
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="2">
								<!-- Tabla de la Prueba -->
								<table width="50%" align="left">
									<tr>
									<td align="center">
										Central:&nbsp;&nbsp;
									</td>
									<td>
										<select name="central" id="central" onchange="javascript:update();">
											<c:forEach items="${listaCentral}" var="central">
												<option value="${central.central}">${central.central}</option>
											</c:forEach>
										</select>
									</td>
									<td align="center">
										Fast:&nbsp;&nbsp;
									</td>
									<td>
										<input type="text" name="fast" id="fast" disabled>
									</td>
									<td align="center">
										Indigo:&nbsp;&nbsp;
									</td>
									<td>
										<select name="indigo" id="indigo" onchange="javascript:update();">
											<c:forEach items="${listaIndigo}" var="indigo">
												<option value="${indigo.site}">${indigo.site}</option>
											</c:forEach>
										</select>
									</td>
									<td align="center">
										Telefono:&nbsp;&nbsp;
									</td>
									<td width="20%">
										<input type="text" name="telefono" id="telefono" size="12" disabled>
									</td>
									</tr>
								</table>
								<!-- Tabla del Telefono -->
								</td>
							</tr>
							<tr>
								<td width="220">
									<!-- Tabla de Estado y codigoVer -->
									<table width="100%">
									<tr>
										<td height="30" align="center">
											<div id="estado">Estado: No hay prueba.</div>
										</td>
									</tr>
									<tr>
										<td align="left" height="60">
											<div id="codigoVer"></div><div id="descCodv"></div>
										</td>
									</tr>
									</table>
									<!-- Tabla de Estado y codigoVer -->
								</td>
								<td>
									<!-- Tabla de Detalle y botones -->
									<table width="100%">
										<tr>
											<td>
												<div id="divFrame" style="position:relative;visibility:hidden;">
													<iframe id="iframDetalles" width="100%" height="65"></iframe>
												</div>
											</td>
										</tr>
										<tr>
										<td align="right" height="25">
											<input type="button" class="boton" id="btnProbar" value="Probar" onclick="probar();">
											<c:if test="${sapeTaglib:isVisible('INTERACTIVAS')}">
												<input type="button" class="boton" id="btnInteractiva" value="Monitoreo" onclick="interactiva();">
											</c:if>
											<input type="button" class="boton" id="btnNueva" value="Nueva Prueba" onclick="nuevaPrueba();">
											<input type="button" class="boton" id="btnDetalle" value="Detalles" onclick="mostrarDetalle();" disabled="true">

											<input type="hidden" name="ip" id="ip">
											<input type="hidden" name="puerto" id="puerto">
											<input type="hidden" name="ipCabeza" id="ipCabeza">
											<input type="hidden" name="puertoCabeza" id="puertoCabeza">
											<input type="hidden" name="id" id="id">
										</td>
										</tr>
									</table>
									<!-- Tabla de Detalle y botones -->
								</td>
							</tr>
						</table>
						</td>
						<td></td> <!-- borde de la prueba -->
					</tr>
				</table>
				<div class="n3" id="n3" style="position:relative;background-color:#ffaa00;"></div>
			</div>
		</div>
		</td>
	</tr>
</table>
</td>
</tr>
</table>
<br>
<div align="center"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=inicioFast">Regresar</a></div>
<script language="JavaScript">update();</script>

</html>
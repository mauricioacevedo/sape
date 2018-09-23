			<!-- pruebaClip.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- este es el telefono del operador para el monitoreo --%>
<jsp:useBean id="telOperador" class="java.lang.String" scope="request"/>
<jsp:useBean id="listaCentrales" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="pruebas" class="java.util.ArrayList" scope="request"/>

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
	<c:forEach begin="1" end="2" var="prueba">

		Rounded("div#n3${prueba}","bottom","#FFF","transparent");
		Rounded("div#n1${prueba}","top","#FFF","transparent");
		Rounded("div#n4${prueba}","bottom","#FFF","transparent");
	</c:forEach>
	}

	function change(nombreCapa) {

		var capa = document.getElementById("n2"+nombreCapa);
		var capa2 = document.getElementById("n3"+nombreCapa);
		var capa3 = document.getElementById("n4"+nombreCapa);

		var frame = document.getElementById("divFrame"+nombreCapa);

		var fr = document.getElementById("iframDetalles"+nombreCapa);

		var tels = document.getElementById("listaTelefonos");

		if(capa.style.visibility == "visible"){

			capa.style.visibility = "hidden";
			capa2.style.visibility = "hidden";
			capa3.style.visibility = "visible";
			frame.style.visibility = "hidden";

			capa.style.position = "absolute";
			capa2.style.position = "absolute";
			frame.style.position = "absolute";
			capa3.style.position = "relative";
			fr.width = "0";

			var c1 = document.getElementById("n21");
			var c2 = document.getElementById("n22");
			var c3 = document.getElementById("n23");
			var c4 = document.getElementById("n24");

			var tam = 8;

			if(c1.style.visibility == "visible")
				tam = tam + 10;
			if(c2.style.visibility == "visible")
				tam = tam + 10;
			if(c3.style.visibility == "visible")
				tam = tam + 10;
			if(c4.style.visibility == "visible")
				tam = tam + 10;

			tels.size = tam;

			//alert("uno="+String(tam)+" el otro = "+tam);

		} else {

			capa.style.position = "relative";
			capa.style.visibility = "visible";
			frame.style.position = "relative";
			frame.style.visibility = "visible";
			capa2.style.position = "relative";
			capa2.style.visibility = "visible";
			capa3.style.position = "absolute";
			capa3.style.visibility = "hidden";

			var te = document.getElementById("tel"+nombreCapa);
			te.focus();
			fr.width = "100%";

			var c1 = document.getElementById("n21");
			var c2 = document.getElementById("n22");
			var c3 = document.getElementById("n23");
			var c4 = document.getElementById("n24");

			var tam = 8;

			if(c1.style.visibility == "visible")
				tam = tam + 8;
			if(c2.style.visibility == "visible")
				tam = tam + 9;
			if(c3.style.visibility == "visible")
				tam = tam + 9;
			if(c4.style.visibility == "visible")
				tam = tam + 9;

			tels.size = tam;
		}
	}

</script>

<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="javascript">
<!--

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


function getKey(e) {
	if (window.event)
	   return window.event.keyCode;
	else if (e){
		var h = e.keyCode;

		if (h == 0 || h == '0')
			return e.which;
		else
			return h;

	}
}

function validarTelefono(e, prueba) {
	var tecla = getKey(e);
	var txtPrueba = document.getElementById("tel" + prueba);
	var tipoPrueba = document.getElementById("tipoPrueba" + prueba);
	txtPrueba.style.background = COLOR_FOCUS;

	//alert(tecla);
	switch(tecla) {
		case 118:
			if(!e.ctrlKey){
				txtPrueba.style.background = COLOR_MAL;
				return false;
			}
			break;
		case 99:
			if(!e.ctrlKey){
				txtPrueba.style.background = COLOR_MAL;
				return false;
			}
			break;
		case 13:
			tipoPrueba.focus();
			break;
		case null:
		case 9:
		case 8:
		case 46:
		case 45:
		case 36:
		case 35:
		case 34:
		case 33:
		case 37:
		case 39:
		case 0:
		case 27:
			return true;
			break;
		default:
			keychar = String.fromCharCode(tecla);
			//alert(keychar);
				// solo numeros
			if ((("0123456789").indexOf(keychar) == -1)) {
				txtPrueba.style.background = COLOR_MAL;
				return false;
			}
			break;
	}
	return true;
}


function validarTipoPrueba(e, prueba) {
	var tecla = getKey(e);
//	alert(tecla);
	if (tecla == 13) {
			document.getElementById("btnProbar" + prueba).focus();
	}
}

function probarTelefono(prueba) {
	var listaClip = document.getElementById("tipoClip" + prueba);
	var tipoPrueba = document.getElementById("tipoPrueba" + prueba);
	var centrales = document.getElementById("centrales" + prueba);
	var cabezas = document.getElementById("cabezas" + prueba);

	var probe = tipoPrueba.options[tipoPrueba.selectedIndex].value;
	var central = centrales.options[centrales.selectedIndex].value;
	var clip = listaClip.options[listaClip.selectedIndex].value;
	var cabeza = cabezas.options[cabezas.selectedIndex].value;

	var estado = document.getElementById("estado" + prueba);

	var btnProbar = document.getElementById("btnProbar" + prueba);
	var btnNueva = document.getElementById("btnNueva" + prueba);
	var btnDetalles = document.getElementById("btnDetalle" + prueba);

	var iframDetalles = document.getElementById("iframDetalles" + prueba);

	var codv = document.getElementById("codigoVer" + prueba);
	var desc = document.getElementById("descCodv" + prueba);

	codv.innerHTML = "";
	desc.innerHTML = "";

	//txtTelefono.disabled = true;
	tipoPrueba.disabled = true;
	estado.innerHTML = "Estado: <font color=\"red\">" + "PROBANDO..." + " </font>";
	btnProbar.disabled = true;

	btnNueva.disabled = true;
	btnDetalles.disabled = false;
	//mostrarDetalle(prueba);
	btnDetalles.focus();

		//aqui ya comienzo la prueba

		//alert('datos: prueba='+probe+' central='+central+' clip='+clip+' cabeza='+cabeza);

	var url = "${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono&operacion=probarTelefono&tel1=" + clip + "&numeroPrueba=" + prueba + "&tipoPrueba1=" + probe+"&central="+central+"&cabeza="+cabeza;
	iframDetalles.src = url;

}


function nuevaPrueba (prueba) {
	var txtTelefono = document.getElementById("tel" + prueba);
	var txtCentral = document.getElementById("txtCentral" + prueba);
	var txtTecnologia = document.getElementById("txtTecnologia" + prueba);
	var tipoPrueba = document.getElementById("tipoPrueba" + prueba);
	var estado = document.getElementById("estado" + prueba);
	var btnProbar = document.getElementById("btnProbar" + prueba);

	var btnNueva = document.getElementById("btnNueva" + prueba);
	var btnDetalles = document.getElementById("btnDetalle" + prueba);
	var iframDetalles = document.getElementById("iframDetalles" + prueba);


	var codv = document.getElementById("codigoVer" + prueba);
	var desc = document.getElementById("descCodv" + prueba);

	codv.innerHTML = "";
	desc.innerHTML = "";

	//txtTelefono.disabled = false;
	tipoPrueba.options.length = 0;
	tipoPrueba.disabled = false;
	estado.innerHTML = "Estado: No hay prueba.";
	btnProbar.disabled = true;
	btnNueva.disabled = false;
	btnDetalles.disabled = true;

	iframDetalles.src = "about:blank";
	txtTelefono.value = "";
	txtCentral.value = "";
	txtTecnologia.value = "";
	txtTelefono.focus();
}


function verif_entradas(prueba) {

	var centrales = document.getElementById("centrales" + prueba);
	var central = centrales.options[centrales.selectedIndex].value;

	//var txtTelefono = document.getElementById("tel" + prueba);
	var btnProbar = document.getElementById("btnProbar" + prueba);

	//var txtCentral = document.getElementById("txtCentral" + prueba);
	//var txtTecnologia = document.getElementById("txtTecnologia" + prueba);


		//como nose como llamar un evento con parametros hago estos if.
	var funcion = recuperarSerie1;
	//if (prueba == 2) funcion = recuperarSerie2;
	//if (prueba == 3) funcion = recuperarSerie3;
	//if (prueba == 4) funcion = recuperarSerie4;

	cargarHttpRequest("${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono&operacion=buscarSite&central=" + central, "GET", funcion);
 	return true;
}

function recuperarSerie1() {
	recuperarSerie(1);
}

function recuperarSerie2() {
	recuperarSerie(2);
}

function recuperarSerie3() {
	recuperarSerie(3);
}

function recuperarSerie4() {
	recuperarSerie(4);
}


function recuperarSerie(prueba) {
	//var tipoPrueba = document.getElementById("tipoPrueba" + prueba);
	//var btnProbar = document.getElementById("btnProbar" + prueba);
	//var hndCabezaId = document.getElementById("hndCabezaId" + prueba);



    if (req.readyState == 4) {
        if (req.status == 200) {
			var estado = document.getElementById("estado" + prueba);
			var respuesta = req.responseXML;
			//var txtCentral = document.getElementById("txtCentral" + prueba);
			//var txtTecnologia = document.getElementById("txtTecnologia" + prueba);
			txtCentral.value = respuesta.getElementsByTagName("central")[0].childNodes[0].nodeValue;
			txtTecnologia.value = respuesta.getElementsByTagName("tipoCentral")[0].childNodes[0].nodeValue;
			hndCabezaId.value = respuesta.getElementsByTagName("cabezaId")[0].childNodes[0].nodeValue;

			if (txtCentral.value != "NOT FOUND" && hndCabezaId.value != "0") {
				btnProbar.disabled = false;

			}

				//lleno el combo de los tipos de pruebas.
			var XMLTipoPruebas = req.responseXML.getElementsByTagName("pruebas")[0];
			tipoPrueba.options.length = 0;
			for (i = 0; i < XMLTipoPruebas.childNodes.length; i++) {
				var opcion = XMLTipoPruebas.childNodes[i];
				addOpcion(tipoPrueba,opcion.childNodes[0].nodeValue,opcion.childNodes[0].nodeValue);
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
//	alert("pruebaTerminada: " + prueba + ", " + ticketPrueba);

	var tipoPrueba = document.getElementById("tipoPrueba" + prueba);
	var estado = document.getElementById("estado" + prueba);
	var btnNueva = document.getElementById("btnNueva" + prueba);
	var btnProbar = document.getElementById("btnProbar" + prueba);
	var btnDetalle = document.getElementById("btnDetalle" + prueba);


	var codigoVer = document.getElementById("codigoVer" + prueba);

	var descCodv = document.getElementById("descCodv" + prueba);

//	var tel = document.getElementById("tel" + prueba);

	//historicoTelefonos();

	var operacion = "mostrarResultados";
	if (tipoPrueba.options[tipoPrueba.selectedIndex].value == "categorias") {
		operacion = "categorias";
	}

	estado.innerHTML = "<div class=\"pruebaTerminada\">Estado: <a href=\"javascript:mostrarDetallePrueba("+ ticketPrueba + ", '" + operacion + "')\">" + "Prueba Terminada" + "</a></div>";
	codigoVer.innerHTML = "Codigo Ver: <font color=\"red\">"+codv+"</font>";
	descCodv.innerHTML = "Descripcion: <font color=\"red\">"+desc+"</font>";
	btnNueva.disabled = false;
	btnProbar.disabled = false;

	//tel.disabled = false;
	tipoPrueba.disabled= false;
	//tel.focus();
}


function mostrarDetallePrueba(idprueba, operacion) {
  var detalle=window.open("/sape/actionSape?accion=pruebaTelefono&operacion=" + operacion + "&ticket="+idprueba, 'window900','scrollbars=yes,resizable=yes,hotkeys=yes,height=600,width=650,left=0,top=0,menubar=yes,toolbar=no');
  detalle.focus();
}


function historicoTelefonos() {
	var listaTelefonos = document.getElementById("listaTelefonos");
	listaTelefonos.options.length = 0;
	var url = "${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono&operacion=historicoTelefonos&idUsuario=${sessionScope.usuario.id}";
	//alert(url);
	cargarHttpRequest(url, "GET", eventoHistorico);
}


function eventoHistorico() {
	var listaTelefonos = document.getElementById("listaTelefonos");
	if (req.readyState == 4) {
	if (req.status == 200) {
		var XMLTelefonos = req.responseXML.getElementsByTagName("telefonos")[0];
		for (i = 0; i < XMLTelefonos.childNodes.length; i++) {
			var opcion = XMLTelefonos.childNodes[i];
			addOpcion(listaTelefonos,opcion.getAttribute("idPrueba"),opcion.childNodes[0].nodeValue);
		}
	}
	}
}


function mostrarDetalle(nombreFrame) {

	var capa = document.getElementById('divFrame'+nombreFrame);
	var iframe = document.getElementById('iframDetalles'+nombreFrame);

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
	var prueba = listaTelefonos.options[listaTelefonos.selectedIndex].value;
	//alert(prueba);
	mostrarDetallePrueba(prueba, "mostrarResultados");
}

function inicializarPagina() {
	historicoTelefonos();

	var txtTelefono = document.getElementById("tel1");
	txtTelefono.focus();
	check();
}

	function updateCabeza(prueba) {

		var centrales = document.getElementById("centrales"+prueba);
		var nuevaCentral = centrales.options[centrales.selectedIndex].value;

		var funcion = recuperarListaCabezas;
		cargarHttpRequest("${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono&operacion=buscarSite&central=" + nuevaCentral, "GET", funcion);
		return true;
	}

	function recuperarListaCabezas() {


	 if (req.readyState == 4) {
        if (req.status == 200) {
			//var estado = document.getElementById("estado" + prueba);
			var respuesta = req.responseXML;
			//var txtCentral = document.getElementById("txtCentral" + prueba);
			//var txtTecnologia = document.getElementById("txtTecnologia" + prueba);
			//txtCentral.value = respuesta.getElementsByTagName("central")[0].childNodes[0].nodeValue;
			//txtTecnologia.value = respuesta.getElementsByTagName("tipoCentral")[0].childNodes[0].nodeValue;
			//hndCabezaId.value = respuesta.getElementsByTagName("cabezaId")[0].childNodes[0].nodeValue;

				//lleno el combo de los tipos de las cabezas
			var XMLTipoPruebas = req.responseXML.getElementsByTagName("cabezas")[0];

			var cabezas =  document.getElementById("cabezas1");
			cabezas.options.length = 0;
			for (i = 0; i < XMLTipoPruebas.childNodes.length; i++) {
				var opcion = XMLTipoPruebas.childNodes[i].childNodes[0].nodeValue;

				var data = opcion.split(",");

				addOpcion(cabezas,data[0],data[1]);
			}
        }
    }

	}
//-->
</script>
</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body bgcolor="white" onload="check();" topmargin="0" leftmargin="0" marginwidth="0" marginheight="0">

<br>
<div align="center" class="tituloPagina"><img src="imagenes_sape/pruebaClip.gif" name="titulo"  border="0" height="20"><tags:ayudas pagina="Clip"/></div>

<br>
<table width="100%" border="0">
<tr>
<td width="*">
		<!-- Se muestran las pantallas de pruebas -->
	<c:forEach begin="1" end="1" var="prueba">
	<input type="hidden" name="hndCabezaId${prueba}" id="hndCabezaId${prueba}" value="">

	<table width="100%" height="100%">
	<tr>
		<td width="50%" height="50%" valign="top">
		<div id="news${prueba}">
			<div class="n1" id="n1${prueba}" align="left">
				&nbsp;
				Prueba Clip
				<div class="n4" id="n4${prueba}" style="position:relative;visibility:hidden;background-color:#ffaa00;"></div>
			</div>
			<div class="n2" id="n2${prueba}" align="center" style="position:relative;visibility:visible;background-color:#ffaa00;">

				<table width="100%" height="100%" cellspacing="0">
					<tr>
						<td></td> <!-- borde de la prueba -->
						<td bgcolor="white" width="100%" height="100%" align="center">
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="2">
								<!-- Tabla del Telefono -->
								<table width="100%">
									<tr>
									<td align="center">
										Tipo de Clip:&nbsp;&nbsp;
										<select name="tipoClip${prueba}" id="tipoClip${prueba}">
											<option value="clip">Clip</option>
											<option value="clip1">Clip1</option>
											<option value="clip2">Clip2</option>
										</select>
									</td>
									<td align="center">
										Central:&nbsp;&nbsp;
										<select name="centrales${prueba}" id="centrales${prueba}" onchange="javascript:updateCabeza('${prueba}');">
											<c:forEach items="${listaCentrales}" var="central">
											<option value="${central}">${central}</option>
											</c:forEach>
										</select>
									</td>
									<td align="center">
										Cabeza&nbsp;&nbsp;
										<!--input id="txtTecnologia${prueba}" name="txtTecnologia${prueba}" type="text" size="9" disabled="true" value="SIPLEXPRO"-->
										<select name="cabezas${prueba}" id="cabezas${prueba}">
										</select>
									</td>
									<td align="center">
										Tipo de Prueba:&nbsp;&nbsp;
										<select name="tipoPrueba${prueba}" id="tipoPrueba${prueba}" onkeypress="validarTipoPrueba(event, ${prueba});">
											<c:forEach items="${pruebas}" var="probe">
												<option value="${probe}">${probe}</option>
											</c:forEach>
										</select>
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
											<div id="estado${prueba}">Estado: No hay prueba.</div>
										</td>
									</tr>
									<tr>
										<td align="left" height="60">
											<div id="codigoVer${prueba}"></div><div id="descCodv${prueba}"></div>
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
												<div id="divFrame${prueba}" style="position:relative;visibility:hidden;">
													<iframe id="iframDetalles${prueba}" width="100%" height="65"></iframe>
												</div>
											</td>
										</tr>
										<tr>
										<td align="right" height="25">
											<input type="button" class="boton" id="btnProbar${prueba}" value="Probar" onclick="probarTelefono(${prueba});">

											<input type="button" class="boton" id="btnNueva${prueba}" value="Nueva Prueba" onclick="nuevaPrueba(${prueba});">
											<input type="button" class="boton" id="btnDetalle${prueba}" value="Detalles" onclick="mostrarDetalle('${prueba}');" disabled="true">
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
				<div class="n3" id="n3${prueba}" style="position:relative;background-color:#ffaa00;"></div>
			</div>
		</div>
		</td>
	</tr>
</table>
	</c:forEach>
</td>
</tr>
</table>
<br>
<div align="center"><a href="${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono">Regresar</a></div>
<script language="JavaScript">updateCabeza('1');</script>
</body>
</html>

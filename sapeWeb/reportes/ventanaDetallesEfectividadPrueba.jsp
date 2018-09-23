			<!-- ventanaDetallesEfectividadPrueba.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% java.util.List listaEventos = (java.util.List) request.getAttribute("listaEventos"); %>
<jsp:useBean id="cantidadTotalRegistros" class="java.lang.String" scope="request"/>
<jsp:useBean id="query" class="java.lang.String" scope="request"/>

<html>
<head>
<title>SAPE - Efectividad de la Prueba</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>

<script language="JavaScript">
<!--
addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");


	function verif_entradas(forma , order, regXpag, pagActual){
		
		if(isNaN(regXpag)){
			alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
			document.formaPaginacion.regXPag.focus();
			return;
		}

		var oneChar;
		var op = forma.opciones.options[forma.opciones.selectedIndex].value

			if ( forma.fechaIni.value.length<1 ){
				window.alert("Ingrese la fecha INICIAL");
				forma.valor.focus();
				return;
			}

			if ( forma.fechaFin.value.length<1 ){
				window.alert("Ingrese la fecha FINAL");
				forma.valor.focus();
				return;
			}

			direcciona(op,forma.fechaIni.value,forma.fechaFin.value,order,regXpag,pagActual);
			return;

	}

	function direcciona(opcion,fechaIni,fechaFin,orderBy,regXpag,pagActual) {
		location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=abrirVentanaEfectividad&opcion="+opcion+"&fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&orderBy="+orderBy+"&regPorPagina="+regXpag+"&pagActual="+pagActual;
		return;
	}



function Regresar() {
  if (navigator.appName == "Netscape")
    window.back();
  else
    window.history.back();
}

function irAPagina(pagina, cantidadRegistros, order){

	if(isNaN(cantidadRegistros)){
		alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
		document.formaPaginacion.regXPag.focus();
		return;
	}
	
	var query = '${query}';
	
	location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=abrirVentanaEfectividad&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;
}

function informeGen(format,pagina, cantidadRegistros, order){
	
	var val = format.formatos.options[format.formatos.selectedIndex].value;

	var query = '${query}&totalRegistros=${cantidadTotalRegistros}';
	
	document.location = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=exportarInforme&pantalla=ventanaDetallesEfectividad&formato="+val+"&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;
	
}

//-->
</script>

</head>

<body bgcolor="white">
	<jsp:include page="cuerpoConsultaPruebas.jsp" flush="true" />
</body>
</html>

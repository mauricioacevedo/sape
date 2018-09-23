			<!-- ventanaConsultaPruebas.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% java.util.List listaEventos = (java.util.List) request.getAttribute("listaEventos"); %>
<jsp:useBean id="cantidadTotalRegistros" class="java.lang.String" scope="request"/>
<jsp:useBean id="query" class="java.lang.String" scope="request"/>

<html>
<head>
<title>SAPE - Detalle de Pruebas</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>

<script language="JavaScript">
<!--
addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");

	function cerrar() {
		parent.window.close();
		die;
	}

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
		location.href="${pageContext.request.contextPath}/actionSape?accion=${param.accion}&operacion=${param.operacion}&fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&orderBy="+orderBy+"&regPorPagina="+regXpag+"&pagActual="+pagActual+"&usuario=${param.usuario}";
		return;
	}

function irAPagina(pagina, cantidadRegistros, order){

	if(isNaN(cantidadRegistros)){
		alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
		document.formaPaginacion.regXPag.focus();
		return;
	}	
	location.href="${pageContext.request.contextPath}/actionSape?accion=${param.accion}&operacion=${param.operacion}&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+"${query}&usuario=${param.usuario}";
}

function informeGen(format,pagina, cantidadRegistros, order){
	
	var val = format.formatos.options[format.formatos.selectedIndex].value;

	var query = '${query}&totalRegistros=${cantidadTotalRegistros}';
	document.location = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=exportarInforme&pantalla=consultaPruebas&formato="+val+"&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;
	
}

//-->
</script>

</head>

<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
	<jsp:include page="cuerpoConsultaPruebas.jsp" flush="true" />
</body>
</html>

				<!--consultaPruebas.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="fIni" class="java.lang.String" scope="request"/>
<jsp:useBean id="fFin" class="java.lang.String" scope="request"/>
<jsp:useBean id="valorOpcion" class="java.lang.String" scope="request"/>
<jsp:useBean id="opcion" class="java.lang.String" scope="request"/>
<jsp:useBean id="pagActual" class="java.lang.String" scope="request"/>
<jsp:useBean id="orderBy" class="java.lang.String" scope="request"/>
<jsp:useBean id="cantidadTotalRegistros" class="java.lang.String" scope="request"/>
<jsp:useBean id="query" class="java.lang.String" scope="request"/>
<jsp:useBean id="modoReporte" class="java.lang.String" scope="request"/>

<html>
<head>
<title>SAPE - Consulta de Pruebas</title>

<script language="JavaScript" src="javascript/calendar.js"> </script>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="javascript" src="javascript/tooltip/domLib.js"></script>
<script language="javascript" src="javascript/tooltip/domTT.js"></script>
<script language="JavaScript">
<!--
addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");


	function verif_entradas(forma , order, regXpag, pagActual) {

		if(isNaN(regXpag)){
			alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
			document.formaPaginacion.regXPag.focus();
			return;
		}

		var oneChar;
		var op = forma.opciones.options[forma.opciones.selectedIndex].value


		if(op == 'ninguno'){
			
			if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
				return;
			}

			direcciona(op,'',forma.fechaIni.value,forma.fechaFin.value,order,regXpag,pagActual)
			return;
		}

		if ( forma.valor.value.length<1 ){
			window.alert("Valor de Busqueda  NO VALIDO");
			forma.valor.focus();
			return;
		}

		if (!validarCamposRangosFechas(forma.fechaIni, forma.fechaFin)) {
			return;
		}


		if(op == "telefono"){
			/**var last="";
			for (var i = 0; i < forma.valor.value.length; i++) {
				oneChar = forma.valor.value.charAt(i);

				if (oneChar < "0" || oneChar > "9") {
					if(oneChar == "%" && i == 3 && forma.valor.value.length == 4){
						direcciona("rangoTelefono",last,forma.fechaIni.value,forma.fechaFin.value,order,regXpag,pagActual);
						return;
					}

					window.alert("Telefono "+forma.valor.value+" NO VALIDO (solo numeros)");
					forma.valor.focus();
					return;
				}
				last += oneChar;
			}*/
				//valido que el telefono tenga solo numeros y que la ultima pueda ser un %
			var telefono = forma.valor.value;
			if (telefono.length > ${applicationScope.cantDigitosEntorno}) {
				alert("El telefono debe ser maximo de ${applicationScope.cantDigitosEntorno} digitos");
			} else {
					//si no es numerico verifico que tenga el %
				var ultima = telefono.substring(telefono.length - 1,telefono.length);
				 if (ultima == "%") {
				 	if (!isNaN(telefono.substr(0,telefono.length - 1))) {
							//el 25 es para convertir el % en %25
					 	direcciona("rangoTelefono",telefono+"25",forma.fechaIni.value,forma.fechaFin.value,order,regXpag,pagActual);
						return;
					} else {
						alert("Telefono "+forma.valor.value+" NO VALIDO (solo numeros)");
						forma.valor.focus();
						return;
					}
				 } else {
				 	if (isNaN(telefono)) {
						alert("Telefono "+forma.valor.value+" NO VALIDO (solo numeros)");
						forma.valor.focus();
						return;
					}
				 }
			}
		}
		direcciona(op,forma.valor.value,forma.fechaIni.value,forma.fechaFin.value,order,regXpag,'');
		return;
	}


	function direcciona(opcion,valor,fechaIni,fechaFin,orderBy,regXpag,pagActual) {
		location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=realizarConsultaPrueba&opcion="+opcion+"&valor="+valor+"&fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&orderBy="+orderBy+"&regPorPagina="+regXpag+"&pagActual="+pagActual;
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

	location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=realizarConsultaPrueba&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;

}



	function informeGen(format,pagina, cantidadRegistros, order){
	
		var val = format.formatos.options[format.formatos.selectedIndex].value;
		var query = '${query}';
		document.location = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=exportarInforme&pantalla=consultaPruebas&formato="+val+"&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;
	}

	var domTT_classPrefix = 'domTTOverlib';
 
	function toolTip(padreEvento, evento,codv,desc) {
		var titulo = "";
	    var contenido = " Codigo Ver: "+codv+"<br> Descripcion: "+desc;
	    return mostrarDivAyuda(padreEvento, evento,'',contenido);
	}
 


//-->
</script>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<!-- link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/javascript/tooltip/example.css"-->
<style>@import url(javascript/tooltip/example.css);</style>
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
<c:if test="${empty modoReporte}">
<!-- if para ke no muestre la forma de buskeda de pruebas!!! -->
<jsp:include page="../encabezado.jsp" flush="true" />


<form name="forma1">
<table width="100%" cellspacing=0 cellpadding=0 border=0 align="center">
	<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td  class="header-filtro" align="left">Buscar por:</td>
		<td align="left" class="header-filtro">DESDE : </td>
		<td align="left" class="header-filtro">HASTA : </td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td  align="left">

		<select name="opciones">
			<option value="ninguno"${opcion == 'ninguno'? 'selected' : ''}>Ninguno</option>
			<option value="prueba"${opcion == 'prueba' ? 'selected' : ''}>Prueba</option>
			<option value="telefono"${opcion == 'telefono' || opcion == 'rangoTelefono' ? 'selected' : ''}>Telefono</option>
			<option value="codv"${opcion == 'codv'? 'selected' : ''}>CodigoVer</option>
			<option value="cliente"${opcion == 'cliente'? 'selected' : ''}>Usuario</option>
			<option value="tipoPrueba"${opcion == 'tipoPrueba'? 'selected' : ''}>Tipo Prueba</option>
			<option value="central"${opcion == 'central'? 'selected' : ''}>Central</option>
			<option value="tecnologia"${opcion == 'tecnologia'? 'selected' : ''}>Tecnologia</option>
		</select>&nbsp;&nbsp;&nbsp;
		<input class="texto" type="text" name="valor" value="${valorOpcion}" size="10" maxlength="40"></td>

		<td align="left">
			<input type="text"class="texto" name="fechaIni" maxlength="10" size="10" value="${fIni}">
		<span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateIni', 5, 5)"> (aaaa-mm-dd)</a></span>
		<div id="calIni" style="position:relative; visibility: hidden;"></td>

		<td align=left>
			<input type="text" class="texto" name="fechaFin" maxlength="10" size="10" value="${fFin}">
			<span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateFin', 5, 5)">(aaaa-mm-dd)</a></span>
			<div id="calFin" style="position:relative; visibility: hidden;">
		</td>
		<td>
			<input type="hidden" value="realizarConsultaPrueba" name="operacion">
			<input type="button" class="boton" name="buscar" onclick="verif_entradas(document.forma1,'${orderBy}',document.formaPaginacion.regXPag.value,'${pagActual}')" value="Aceptar">
		</td>
  </tr>
</table>
</form>

</c:if>


	<jsp:include page="cuerpoConsultaPruebas.jsp" flush="true" />
</body>
</html>

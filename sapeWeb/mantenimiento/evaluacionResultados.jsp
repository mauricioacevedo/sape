<!-- evaluacionResultados.jsp -->

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="listaSecuencias" class="java.util.ArrayList" scope="request"/>

<html>
<head>
<title>SAPE - Evaluacion Resultados</title>
<script language="JavaScript">

<c:set var="k" value="6" />
<%-- esta variable es para el control de la cantidad de codigos ver, recuerde cambiar tambien
la variable k dentro de la clase evaluacionResultados --%>

<!--
addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");

	function validarDatos(forma){

		var usrDB = document.getElementById("usuarioDB");

		if (usrDB.value == ''){
			alert('Ingrese un Usuario correcto para la base de datos.');
			usrDB.focus();
			return;
		}

		var usuarioDB = usrDB.value;			// usuario de la DB ya validado!!!


		var colaND = document.getElementById("colaND");
		var codigoND = document.getElementById("codigoND");

		if (colaND.value == ''){ alert('Ingrese la cola de condiciones ND.'); colaND.focus(); return;}
		if (codigoND.value == ''){ alert('Ingrese el codigo de condiciones ND.'); codigoND.focus(); return;}

		var colND = colaND.value;
		var codigND = codigoND.value;


		var cole = document.getElementById("colaError");
		var codigoe = document.getElementById("codigoError");

		if (cole.value == ''){ alert('Ingrese la cola de error.'); cole.focus(); return;}
		if (codigoe.value == ''){ alert('Ingrese el codigo de error.'); codigoe.focus(); return;}

		var colaError = cole.value;
		var codigoError = codigoe.value;		// cola error y codigo error validados.

		var query = '';
		for( i=0; i < 28 + ${k} ; i++) {
			var cola = document.getElementById("cola"+i);
			var codigo = document.getElementById("codigo"+i);
			var codv = document.getElementById("codv"+i).value;

			if (cola.value == '') { alert('Ingrese la cola de la condicion '+(i+1)+'.'); cola.focus(); return; }
			if (codigo.value == '') { alert('Ingrese el codigo de la condicion '+(i+1)+'.'); codigo.focus(); return; }

			query += '&cola'+i+'='+cola.value+'&codigo'+i+'='+codigo.value+'&codv'+i+'='+codv;

		}
		query += '&usuarioFenix='+usuarioDB;
		query += '&colaError='+colaError+'&codigoError='+codigoError;
		query += '&colaND='+colND+'&codigoND='+codigND;

		var crepetido = document.getElementById("clienteRepetido");
		var cola = document.getElementById("siCola");
		var codigo = document.getElementById("siCodigo");
		if(crepetido.value == '' || isNaN(crepetido.value)){ alert('Ingrese una cantidad Numerica.'); crepetido.focus(); return;}

		if (cola.value == '') { alert('Ingrese la cola para la condicion de Cierre.'); cola.focus(); return; }
		if (codigo.value == '') { alert('Ingrese el codigo para la condicion de Cierre.'); codigo.focus(); return; }

		query += '&clienteRepetido='+crepetido.value+'&siCola='+cola.value+'&siCodigo='+codigo.value;

		var coladia = document.getElementById("colaDia");
		var colanoche = document.getElementById("colaNoche");
		var codigonoche = document.getElementById("codigoNoche");
		var codigodia = document.getElementById("codigoDia");
		var horaNoche = document.getElementById("horaNoche");

		if (coladia.value == '' || colanoche.value == '' || codigonoche.value == '' || codigodia.value == ''){
			alert('Faltan datos para los horarios de GRANC.');
			return;
		}

		if (isNaN(horaNoche.value)){
			alert('La hora nocturna de GRANC debe ser una cantidad\nnumerica entre 0 y 23.');
			horaNoche.focus();
			return;
		}

		if(horaNoche.value > 23 || horaNoche.value < 0 ) {
			alert('La hora nocturna de GRANC debe ser una cantidad\nnumerica entre 0 y 23.');
			horaNoche.focus();
			return;
		}

		query += '&colaDia='+coladia.value+'&colaNoche='+colanoche.value;
		query += '&codigoNoche='+codigonoche.value+'&codigoDia='+codigodia.value;
		query += '&horaNoche='+horaNoche.value;

		if(confirm("Se actualizara la plantilla de Evaluacion de Resultados con los nuevos valores.\nEsta Seguro?"))
			location.href='${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=guardarPlantillaEvaluacion'+query;
			
	}



-->
</script>
<!--style type="text/css">

 td { color: black; font-family: "Arial, Helvetica, sans-serif"; font-size: 10pt }

 .header-reporte { font-weight: bold; font-size: 12; color: black }
 .mensajeCentral { font-weight: bold; font-size: 14; text-decoration: none; color: white}

 .row0 {background-color: #e7c366;}
 .row1 {background-color: #e7d29e;}

</style-->

</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<jsp:include page="../encabezado.jsp" flush="true" />
<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0" bgcolor="white">
<br><br>



<form name="datosEvaluacion">
<table align="center" width="80%" >
<tr bgcolor="black">
	<td colspan="5" align="center"><font color="white" size="+1"><b>Evaluacion de Resultados </b></font><tags:ayudas pagina="Evaluacion"/></td>
</tr>

<tr align="center" bgcolor="#BDB5A5">
	<td width="100%" colspan="4">
		<table width="100%" border="1" cellspacing="0" cellpadding="0">
		<tr class="row1">
			<td>Usuario DB: <input class="texto" id="usuarioDB" style="text-transform:uppercase" type="text" name="usuarioDB" value="${listaSecuencias[29+k]}" size="15"> </td>
		</tr>
		</table>
	</td>
</tr>
<tr>
<td width="100%" colspan="3"></td>
</tr>

<tr class="row0">
	<td width="100%" colspan="4">
	<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<td width="40%" align="center" colspan="2">CONDICION DE ND</td>
	<td width="60%" align="center">Cola: <input class="texto" id="colaND" style="text-transform:uppercase" type="text" name="colaError" value="${listaSecuencias[32+k].cola}" size="5">, Codigo: <input class="texto" id="codigoND" type="text" name="codigoError" value="${listaSecuencias[32+k].codigo}" size="4"></td>
	</table>
	</td>
</tr>

<tr class="row1">
	<td width="100%" colspan="4">
	<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<td width="40%" align="center" colspan="2">CONDICION DE ERROR</td>
	<td width="60%" align="center">Cola: <input class="texto" id="colaError" style="text-transform:uppercase" type="text" name="colaError" value="${listaSecuencias[30+k].cola}" size="5">, Codigo: <input class="texto" id="codigoError" type="text" name="codigoError" value="${listaSecuencias[30+k].codigo}" size="4"></td>
	</table>
	</td>
</tr>

<tr>
<td width="100%" colspan="3"></td>
</tr>

<tr align="center">
	<!--td width="5%" align="center" class="header-reporte">Num</td-->
	<td width="10%" align="center" class="barraTitulos">CodigoVer</td>
	<td width="55%" align="center" class="barraTitulos">Descripcion</td>
	<td width="30%" align="center" class="barraTitulos">Destino</td>
</tr>


<c:set var="j" value="0" />

<c:forEach begin="0" end="${k+27}" step="1" var="i">

<tr class="row${j%2 == 0? 0 : 1}">
	<!--td align="center">${i+1}</td-->
	<td align="center">${listaSecuencias[i].codv}<input class="texto" id="codv${i}" type="hidden" value="${listaSecuencias[i].codv}"></td>
	<td align="center">${listaSecuencias[i].desc}</td>
	<td align="center">Cola: <input class="texto" id="cola${i}" style="text-transform:uppercase" type="text" name="cola${i}" value="${listaSecuencias[i].cola}" size="5">, Codigo: <input class="texto" id="codigo${i}" type="text" name="codigo${i}" value="${listaSecuencias[i].codigo}" size="4"></td>
</tr>
	<c:set var="j" value="${j+1}" />
</c:forEach>

<tr>
<td width="100%" colspan="3"></td>
</tr>


<tr class="row1">
	<td colspan="4" width="100%">
	<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<td colspan="3" align="center" width="50%">Condicion de CIERRE</td>
	<td colspan="1" align="center">

		<table width="100%" border="1" align="right">
			<tr align="center"><td rowspan="3" width="45%">Cliente reclama aparato?<BR><BR>Cliente es&nbsp;&nbsp;&nbsp; Repetido >=<input class="texto" id="clienteRepetido" type="text" name="repetido" size="2" value="${listaSecuencias[28+k].clienteRepetido}">?</td><td rowspan="1" width="100%" height="100%"><font color="Red"><left>Si:</left></font> Cola: <input class="texto" id="siCola" style="text-transform:uppercase" type="text" name="cola_si" value="${listaSecuencias[28+k].siCola}" size="5">,<br><br> Codigo: <input class="texto" id="siCodigo" type="text" name="codSi" value="${listaSecuencias[28+k].siCodigo}" size="4"></td></tr>
			<tr align="center"><td rowspan="2" width="100%" height="100%"><font color="Red">No:</font> CERRAR</td></tr>
		</table>

	</td>
	</table>
	</td>
</tr>

<tr>
<td width="100%" colspan="3"></td>
</tr>

<tr class="row0">
<td width="100%" colspan="4">

	<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<tr>
	<td colspan="3" align="center">Cola GRANC dia (Subzona = EGC, Producto = PBX)</td>
	<td align="center">Cola: <input class="texto" id="colaDia" style="text-transform:uppercase" type="text" name="colaDia" value="${listaSecuencias[31+k].colaDia}" size="5">, Codigo: <input class="texto" id="codigoDia" type="text" name="codigoDia" value="${listaSecuencias[31+k].codigoDia}" size="4"></td>
	</tr>
		<tr class="row1">
	<td colspan="3" align="center">Cola GRANC Noche</td>
	<td align="center">Cola: <input class="texto" id="colaNoche" style="text-transform:uppercase" type="text" name="colaNoche" value="${listaSecuencias[31+k].colaNoche}" size="5">, Codigo: <input class="texto" id="codigoNoche" type="text" name="codigoNoche" value="${listaSecuencias[31+k].codigoNoche}" size="4"></td>
	</tr>
		<tr>
	<td colspan="3" align="center">Hora de GRANC noche(entre las 0 horas y 23 horas)</td>
	<td align="center">Hora: <input class="texto" id="horaNoche" style="text-transform:uppercase" type="text" maxlength="2" name="horaNoche" value="${listaSecuencias[31+k].horaGrancNoche}" size="2"></td>
	</tr>
	</table>
</td>
</tr>

</table>
<br>
<br>
<center><input class="boton" type="button" value="aceptar" onClick="javascript:validarDatos(document.datosEvaluacion)"></center>
</form>
<!--
<br>
<br>
<a name="1"></a>
[1]:
<table border="1">
	<tr>
		<td>

- CONDICION DE ERROR:<br>
	* No hay Licencia disponible<br>
	* Vac_at es nula<br>
	* Vac_at es ""<br>
	* Suscriptor ocupado<br>
	* Fast Ocupado<br>
	* Error<br>
	* Falla
		</td>
	</tr>
</table>-->
</body>
</html>

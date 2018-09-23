<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="listaCampos" value="${requestScope.listaCampos}" />
<c:set var="listaReporteadores" value="${requestScope.listaReporteadores}" />
<c:set var="reporteador" value="${requestScope.reporteador}" />
<c:set var="msg" value="${requestScope.msg}" />
<html>
<head>
<title>SAPE - Reporteador</title>
</head>
<script language="JavaScript" src="javascript/varios.js"></script>
<script language="JavaScript" src="javascript/calendar.js"></script>
<script type="text/javascript" language="javascript">

addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");
addCalendar("DateMail", "calMail", "fechaMail", "forma2");

function validaciones() {
		
	var titulo = document.getElementById('tituloReporte');
	
	if(!validarString(titulo.value)){
		alert('Ingrese un titulo para el reporte');
		titulo.focus();
		return;
	}

	<c:set var="delim" value="" />
	<c:forEach items="${listaCampos}" var="campo"><c:set var="name" value="${name}${delim}${campo.campo}" /><c:set var="delim" value="," /></c:forEach>
	
	var names = '${name}'.split(',');
	
	var listaCampos = "";
	var listaLabels = "";
	var elementoOrdenador = "";
	
	var deli = "";
	for(i=0;i<names.length;i++) {
		var chek = document.getElementById('checks'+names[i]);
		if(chek.checked){
			listaCampos += deli+names[i];
			var label = document.getElementById('textos'+names[i]);
			if(label.value == ''){
				alert('Debe asignar un label para el campo');
				label.focus();
				return
			}
			listaLabels += deli+label.value;
			var radio = document.getElementById('radios'+names[i]);
			if(radio.checked){
				elementoOrdenador = names[i];
			}
		}
		deli =",";
	}

	if(elementoOrdenador == ''){
		//elementoOrdenador = 'prueba';
		elementoOrdenador = names[0];
	}


	if(listaCampos == ''){
		alert('Seleccione almenos un campo para mostrar.');
		return;
	}

	var selectBuscar = document.getElementById('selectBuscar');
	
	var buscar = selectBuscar.options[selectBuscar.selectedIndex].value;
	
	if(buscar == 'seleccione'){
		alert('Seleccione un valor para el tipo de busqueda.');
		selectBuscar.focus();
		return;
	}
	
	var selectCondicion = document.getElementById('selectCondicion');
	var condicion = selectCondicion.options[selectCondicion.selectedIndex].value;

	var valorCondicion = document.getElementById('valorCondicion');
	
	var fechaIni = document.getElementById('fechaIni');
	var fechaFin = document.getElementById('fechaFin');
	
	if (!validarCamposRangosFechas(fechaIni, fechaFin)) {
			return;
	}

	var selectFormatos = document.getElementById('selectFormatos');

	var formato = selectFormatos.options[selectFormatos.selectedIndex].value;
	
	var emails = document.getElementById('emails');
	if(emails.value == ''){
		alert('Ingrese almenos un correo electronico');
		emails.focus();
		return;
	}

	var fechaMail = document.getElementById('fechaMail');
	if(fechaMail.value == ''){
		alert('Ingrese la fecha de generacion del reporte');
		fechaMail.focus();
		return;
	}
	
	var selectHora = document.getElementById('selectHora');
	var hora = selectHora.options[selectHora.selectedIndex].value;
	
	var selectMinuto = document.getElementById('selectMinuto');
	var minuto = selectMinuto.options[selectMinuto.selectedIndex].value;
	
	//alert(titulo.value);
	var query = "&campos="+listaCampos;
	query += "&labels="+listaLabels;
	query += "&titulo="+titulo.value;
	query += "&orderBy="+elementoOrdenador;
	query += "&buscar="+buscar+"&condicion="+condicion+"&valorCondicion="+valorCondicion.value;
	query += "&desde="+fechaIni.value+"&hasta="+fechaFin.value;
	query += "&formato="+formato+"&destino="+emails.value;
	query += "&fechaMail="+fechaMail.value+" "+hora+":"+minuto+":00";
	
	var hide = document.getElementById('nuevoHidden');
	if(hide.value == '') {//Van a actualizar un registro.
		query += "&idPlantillaActual=${reporteador.id}"
	}
	
	location.href='${pageContext.request.contextPath}/actionSape?accion=reporteador&operacion=guardarPantilla'+query;
}


function validarString(cadena){

	if(cadena.length  > 1 ) return true;
	else return false;
}


function verificarRadios(nombreCampo){
	var check = document.getElementById('checks'+nombreCampo);
	if(!check.checked){
		check.checked=true;
	}
}

function nuevo() {
	var titulo = document.getElementById('tituloReporte');
	titulo.value='';
	
	<c:set var="delim" value="" />
	<c:set var="name" value="" />
	<c:forEach items="${listaCampos}" var="campo"><c:set var="labels" value="${labels}${delim}${campo.nombre}" /><c:set var="name" value="${name}${delim}${campo.campo}" />	<c:set var="delim" value="," /></c:forEach>
	
	var names1 = '${name}'.split(',');
	var labels1 = '${labels}'.split(',');
	
	for(i=0;i<names1.length;i++) {
		var chek = document.getElementById('checks'+names1[i]);
		
		if(chek == 'null' || chek == null) {
			alert('null: '+names1[i]);
		}
		
		chek.checked=false;
			
		var label = document.getElementById('textos'+names1[i]);
		label.value = labels1[i];
		
		var radio = document.getElementById('radios'+names1[i]);
		if(radio.checked) {
			radio.checked=false;
		}
		
		if(names1[i] == 'prueba') {
			chek.checked=true;
			radio.checked=true;
		}
	}

	var valorCondicion = document.getElementById('valorCondicion');
	valorCondicion.value='';
	
	var fechaIni = document.getElementById('fechaIni');
	var fechaFin = document.getElementById('fechaFin');
	
	fechaIni.value=formatDate(new Date(),"yyyy-MM-dd");
	fechaFin.value=formatDate(new Date(),"yyyy-MM-dd");
	
	var emails = document.getElementById('emails');
	emails.value='';
	

	var fechaMail = document.getElementById('fechaMail');
	fechaMail.value=formatDate(new Date(),"yyyy-MM-dd");
	
	resetearCombo('selectMinuto');
	resetearCombo('selectHora');
	resetearCombo('selectFormatos');
	resetearCombo('selectCondicion');
	resetearCombo('selectBuscar');
	
	var btnGuardar = document.getElementById('botonGuardar');
	var btnEliminar = document.getElementById('botonEliminar');
	
	btnGuardar.disabled=false;
	btnEliminar.disabled=true;
	var hide = document.getElementById('nuevoHidden');
	hide.value="yes";
}

function cargarReporteador() {
	var id = getValueid('selectReporteadores');
	if(id == 'seleccione'){
		return;
	}
	location.href='${pageContext.request.contextPath}/actionSape?accion=reporteador&operacion=cargarReporteador&id='+id;
}

function eliminar() {
	var titulo = '${reporteador.titulo}';
	var id = getValueid('selectReporteadores');
	if(titulo != '') {
		if(confirm('Esta seguro que desea borrar el reporte '+titulo+' ?')){
			location.href='${pageContext.request.contextPath}/actionSape?accion=reporteador&operacion=eliminarReporteador&id='+id+"&nombre="+titulo;
		}
	}	
}

</script>
<body>
<jsp:include page="../encabezado.jsp" flush="true" />
<br>
<table align="center" width="100%">
	<tr><td align="center" class="mensajeCentral">REPORTEADOR SAPE </td></tr>
</table>

<br>
<div align="center">
Reportes Anteriores (ordenados por id desc): 
<select name="selectReporteadores" id="selectReporteadores" onChange="javascript:cargarReporteador();">
	<option value="seleccione">Seleccione:</option>
	<c:forEach items="${listaReporteadores}" var="r">
	<option value="${r.id}" ${r.id == reporteador.id?'selected':''}>${r.titulo}</option>
	</c:forEach>
</select>
</div>

<br>
<center>${msg}</center>
<br>
T&iacute;tulo Del Reporte: <input type="text" size="60" value="${reporteador.titulo}" id="tituloReporte" class="texto">
<br>
<br>
SELECCIONE CAMPOS 
<table border="1" align="center" width="100%">
<tr>
	<c:set var="i" value="0" />
	<c:forEach begin="0" end="1">
	<td>
		CAMPO
	</td>
	<td>
		MOSTRAR
	</td>
	<td width="30">
		ORDEN
	</td>
	<c:if test="${i == 0}">
		<td width="50">&nbsp</td>
	</c:if>
	<c:set var="i" value="1" />
	</c:forEach>
</tr>
<c:set var="i" value="0" />
<c:forEach var="campo" items="${listaCampos}">
<c:if test="${i%2 == 0}">
<tr>
</c:if>
		<%-- Ciclo para ke la jsp tenga la capacidad de cargar la info del bean reporteador ke venga por el request --%>
		<c:set var="listaVars" value="${fn:split(reporteador.campos,',')}" />
		<c:set var="listaLabs" value="${fn:split(reporteador.etiquetas,',')}" />
		<c:set var="k" value="0" />
  		<c:set var="label" value="" />
		<c:forEach items="${listaVars}" var="v">
			<c:if test="${v ==campo.campo}">
					<c:set var="label" value="${listaLabs[k]}" />
			</c:if>
			<c:set var="k" value="${k + 1}" />
		</c:forEach>

	<td>
		<input type="checkbox" value="${campo.nombre}" id="checks${campo.campo}" name="check${campo.campo}" ${label !=''?'checked':''}> ${campo.nombre}
	</td>
	<td>
		<input type="text" size="15" value="${label !=''?label:campo.nombre}" id="textos${campo.campo}" name="textos${campo.campo}" class="texto" >

	</td>
	<td align="center">
		<input type="radio" name="orden" value="${campo.campo}" id="radios${campo.campo}" name="radios${campo.campo}" onclick="javascript:verificarRadios('${campo.campo}');" ${reporteador.orderBy == campo.campo?'checked':''}>
	</td>
	<c:if test="${i%2 == 0}">
	<td>&nbsp;</td>
	</c:if>
<c:if test="${i%2 == 1}">
</tr>
</c:if>
<c:set var="i" value="${i + 1}" />
</c:forEach>
<table>
<br>
<table border="1">
<tr>
	<td>Buscar Por</td>
	<td>Condicion</td>	
	<td>Valor</td>	
	<td>Desde Fecha </td>	
	<td>Hasta Fecha </td>	
</tr>
<tr>
	<td>
		<select name="selectBuscar" id="selectBuscar">
			<option value="seleccione">seleccione</option>
			<c:forEach var="campo" items="${listaCampos}">
				<c:if test="${campo.filtro == true}">
				<option value="${campo.campo}" ${reporteador.colCondicion == campo.campo?'selected':''}>${campo.nombre}</option>
				</c:if>
			</c:forEach>
		</select>
	</td>
	<td>
		<select name="selectCondicion" id="selectCondicion">
			<option ${reporteador.tipoCondicion == '='?'selected':''}>=</option>
			<option ${reporteador.tipoCondicion == '>'?'selected':''}>&gt;</option>
			<option ${reporteador.tipoCondicion == '<'?'selected':''}>&lt;</option>
			<option ${reporteador.tipoCondicion == '!='?'selected':''}>!=</option>
		</select>
	</td>
	<td>
		<input type="text" class="texto" value="${reporteador.valorCondicion}" name="valorCondicion" id="valorCondicion">
	</td>
	<form name="forma1">
	<td>
		<input type="text" class="texto" name="fechaIni" id="fechaIni" maxlength="10" size="10" value="${reporteador.desdeFecha}">
        <span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateIni', 5, 5)"> (aaaa-mm-dd)</a></span>
        <div id="calIni" style="position:relative; visibility: hidden;">
	</td>
	<td>
        <input type="text" class="texto" name="fechaFin" id="fechaFin" maxlength="10" size="10" value="${reporteador.hastaFecha}">
        <span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateFin', 5, 5)">(aaaa-mm-dd)</a></span>
        <div id="calFin" style="position:relative; visibility: hidden;">
	</td>
	</form>
</tr>
</table>
<br>
<table border="1" width="100%">
<tr>
	<td width="195" align="center">
	Formato de envio: 
	<select name="selectFormatos" id="selectFormatos">
		<%-- option value="html" ${reporteador.formato == 'html'?'selected':''}>HTML</option--%>
		<option value="csv" ${reporteador.formato == 'csv'?'selected':''}>CSV</option>
		<option value="xls" ${reporteador.formato == 'xls'?'selected':''}>XLS</option>
		<option value="pdf" ${reporteador.formato == 'pdf'?'selected':''}>PDF</option>
	</select>
	</td>
	<td width="*" align="center">
		Direcci&oacute;n (es): 
		<input type="text" size="90%" name="emails" id="emails" class="texto" value="${reporteador.valorMedio}">
	</td>
</tr>
</table>
<br>
<br>
<table border="1" width="550" align="center">
<tr>
	<td colspan="2" align="center">
		FECHA GENERACION
	</td>
</tr>
<tr>
	<form name="forma2">
	<td align="center">
		<table width="60%">
		<tr>
		<td width="70%">
		
		<c:set var="dateMail" value="${fn:split(reporteador.fechaEnvio,' ')}" />
		
		Fecha: <input type="text" class="texto" name="fechaMail" id="fechaMail" maxlength="10" size="10" value="${dateMail[0]}">
        <span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateMail', 5, 5)">(aaaa-mm-dd)</a></span>
        <div id="calMail" style="position:relative; visibility: hidden;"></div>
		</td>
		<td>
			<c:set var="timeMail" value="${fn:split(dateMail[1],':')}" />
			<select name="selectHora" id="selectHora">
				<c:forEach var="hora" begin="0" end="23">
					<c:set var ="hora2" value="${hora <= 9?'0':''}${hora}" />
					<option value="${hora2}" ${timeMail[0]== hora2?'selected':''}>${hora2}</option>
				</c:forEach>
			</select>
		</td>
		<td> : </td>
		<td>
			<select name="selectMinuto" id="selectMinuto">
				<option value="00" ${timeMail[1]== '00'?'selected':''}>00</option>
				<option value="15" ${timeMail[1]== '15'?'selected':''}>15</option>
				<option value="30" ${timeMail[1]== '30'?'selected':''}>30</option>
				<option value="45" ${timeMail[1]== '45'?'selected':''}>45</option>
			</select>
		</td>
		</tr>
		</table> 
	</td>
	</form>	
</tr>
</table>
<br>
<div align="center">
	<input type="button" value="Nuevo" class="boton" onclick="javascript:nuevo();">
	<input type="button" id="botonGuardar" value="Guardar" onclick="javascript:validaciones();" class="boton" ${empty reporteador?'disabled':''}>
	<input type="button" id="botonEliminar" value="Eliminar" onclick="javascript:eliminar();" class="boton" ${empty reporteador?'disabled':''}>
	<input type="hidden" name="nuevoHidden" id="nuevoHidden" value="">
</div>
</body>
</html>
					<!-- MantenimientoTipoNodo.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% java.util.List listaTipoNodo = (java.util.List) request.getAttribute("listaTipoNodo"); %>
<jsp:useBean id="state" class="java.lang.String" scope="request"/>
<html>
<head>
<title>Mantenimiento de Tipos de Nodo - SAPE</title>
<style type="text/css">
  
  td { color: black; font-family: "Arial, Helvetica, sans-serif"; font-size: 10pt }

 .header-reporte { font-weight: bold; font-size: 12; color: black }
 .mensajeCentral { font-weight: bold; font-size: 14; text-decoration: none; color: white}

.row0 {background-color: #e7c366;}
.row1 {background-color: #e7d29e;}
.fallo0 {background-color: #942f10;}
.fallo1 {background-color: #ff613d;}
.mantenimiento0 {background-color: #333cb7;}
.mantenimiento1 {background-color: #6b7fff;}
.inter0 {background-color: #44a041;}
.inter1 {background-color: #60e35c;}
.linkCPR a:link { text-decoration: none; color: blue }
.linkCPR a:visited { text-decoration: none; color: blue }
.linkCPR a:hover { text-decoration: underline; color: black }

</style>


<script language="JavaScript">
//funcion de modificar
function modificarTipoNodo (forma) {

	var cantTel = forma.chkTel.length;

	var id = "";
	var i = -1;
	
	if(cantTel == undefined ){ 
		id = forma.chkTel.value;
		j =1;
		i=2;
	}else {
	
		for (j = 0; j < cantTel; j++ ) {
			if(forma.chkTel[j].checked){
				
				id = forma.chkTel[j].value;
				j = cantTel +1;
				i = 2;
			}
		}
	}
	if(i  == -1){
		alert("Debe seleccionar almenos un registro para modificar.");
		return;
	}
	
	this.window.focus();
	v210=window.open("${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=modificarTipoNodo&id="+id,'Modificar','scrollbars=yes,resizable=yes,hotkeys=yes,height=420,width=500,left=150,top=100,menubar=no,toolbar=no');
	v210.focus();
	document.location = "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoTipoNodo";

	}

	function mantenimientoTipoNodo(forma){
		
		var cantTel = forma.chkTel.length;
		var id = "";
		var i = -1;
		var idd = "";
	
		for (j = 0; j < cantTel; j++ ) {
			if(forma.chkTel[j].checked){

				id = forma.chkTel[j].value;
				idd=forma.chkTel[j].id;
				j = cantTel +1;
				i = 2;
			}
		}
		if(i  == -1){
			alert("Debe seleccionar almenos un registro para realizar Matenimiento.");
			return;
		}

		this.window.focus();
		v210=window.open("${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=telnetMantenimientoTipoNodo&id="+id+"&nombreNodo="+idd,'Mantenimiento','scrollbars=yes,resizable=yes,hotkeys=yes,height=530,width=570,left=50,top=50,menubar=no,toolbar=no');
		v210.focus();
	}

	function informeGen(format){
		var val = format.formatos.options[format.formatos.selectedIndex].value;
		document.location = "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoTipoNodo&exportar=yes&pantalla=tipoNodo&formato="+val;
	}
	
	function mostrarCPRS(id) {
		var pagina = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=cprsTipoNodo&tipoNodo="+id;
		vCPR=window.open(pagina,'CPRS','scrollbars=yes,hotkeys=yes,height=530,width=570,left=10,top=10,menubar=no,toolbar=no');
		vCPR.focus();
	}

	function mostrarLIS(id) {
		var pagina = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=lisTipoNodo&tipoNodo="+id;
		vCPR=window.open(pagina,'LIS','scrollbars=yes,hotkeys=yes,height=530,width=570,left=10,top=10,menubar=no,toolbar=no');
		vCPR.focus();
	}
	
	function mostrarDLUS(id) {
		var pagina = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=dlusTipoNodo&tipoNodo="+id;
		vCPR=window.open(pagina,'CPRS','scrollbars=yes,hotkeys=yes,height=530,width=570,left=10,top=10,menubar=no,toolbar=no');
		vCPR.focus();
	}
</script>
</head>
<body bgcolor="white" text="black">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<jsp:include page="../encabezado.jsp" flush="true" />

<!--div align="center"><a name="agregarTipoNodo" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarTipoNodo">Agregar nuevo Tipo de Nodo</a></div-->
<br>
<br>
<table align="center" border="0" cols="1" width="100%">
<tr>
<td></td>
<td align="center"><input class="boton" name='modificar' onclick='javascript:modificarTipoNodo(document.formaGral);' type='button' value='Modificar'></td>
<td align="center"> <input class="boton" name='mantenimiento' ${state == 'M' or state == 'I' ?'disabled':''} onclick='javascript:mantenimientoTipoNodo(document.formaGral);' type='button' value='Mantenimiento'></td>
<td align="center"> &nbsp;</td>
<td>&nbsp;</td>
<td align="right" colspan ="6" valign="middle">
<form name="informes">
	<font color="Black">Exportar a &nbsp;</font>
	<select name="formatos">
		<option value="pdf">PDF</option>
		<option value="csv">CSV</option>
		<option value="xls">XLS</option>
	</select>
	<input class="boton" name='informe' ONCLICK='javascript:informeGen(document.informes);' type='button' value='Aceptar'>
</form>
</td>
</td>
</tr>
<form name="formaGral">
<tr bgcolor="Black">
	<td align="center" colspan="11" class="mensajeCentral">
	Tipo de Nodos<tags:ayudas pagina="Tiponodo"/></td>
</tr>

<tr bgcolor="#BDB5A5">
	<td align="center" class="header-reporte">Site ID</td>
	<td align="center" class="header-reporte">Site</td>
	<td align="center" class="header-reporte">Cabeza Prueba</td>
	<td align="center" class="header-reporte">IP Servidor</td>
	<!--td align="center" class="header-reporte">Puerto Servidor</td-->
	<td align="center" class="header-reporte">IP Cabeza</td>
	<!--td align="center" class="header-reporte">Puerto Cabeza</td-->
	<td align="center" class="header-reporte">IP esclavo</td>
	<!--td align="center" class="header-reporte">Puerto Esclavo</td-->
	<td align="center" class="header-reporte">Estado</td>
	<td align="center" class="header-reporte" width="60">Opcion</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaTipoNodo}" var="tiponodo">
<!-- tiponodo: ${tiponodo} -->
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}">
		<td align="center" height="30">${tiponodo.id}</td>
		<td align="center">${tiponodo.site}</td>
		<td align="center">
			<c:choose>
				<c:when test="${tiponodo.tipoCentral == 'SIPLEXPRO-CPR'}">
					<span class="linkCPR" title="Ver CPR's Asociados a la Cabeza">
					<a href="javascript:mostrarCPRS(${tiponodo.id})">${tiponodo.tipoCabeza.nombre}</a>
					</span>
				</c:when>
				<c:when test="${tiponodo.tipoCentral == 'SIPLEXPRO-AXE'}">
					<span class="linkCPR">
					<a title="Ver LI's Asociados a la Cabeza" href="javascript:mostrarLIS(${tiponodo.id})">${tiponodo.tipoCabeza.nombre}</a>
					</span>
				</c:when>
				<c:when test="${tiponodo.tipoCentral == 'SIPLEXPRO-EWSD'}">
					<span class="linkCPR">
					<a title="Ver DLU's Asociados a la Cabeza" href="javascript:mostrarDLUS(${tiponodo.id})">${tiponodo.tipoCabeza.nombre}</a>
					</span>
				</c:when>
				<c:otherwise>
					${tiponodo.tipoCabeza.nombre}
				</c:otherwise>
			</c:choose>
		</td>
			
		<td align="center">${tiponodo.ipServidor} : ${tiponodo.puertoServidor}</td>
		<!--td align="center">${tiponodo.puertoServidor}</td-->
		<td align="center">${tiponodo.ipCabeza} : ${tiponodo.puertoCabeza}</td>
		<!--td align="center">${tiponodo.puertoCabeza}</td-->
		<td align="center">${tiponodo.ipEsclavo} : ${tiponodo.puertoEsclavo}</td>
		<!--td align="center">${tiponodo.puertoEsclavo}</td-->

		<!--td align="center" class="${tiponodo.estado == "O" ? "" : "fallo"}${row}" >${tiponodo.estado == "O" ? "Operando" : "Fuera de Servicio"}</td-->
		<c:choose>
			<c:when test="${tiponodo.estado == 'F'}">
				<td align="center" class="fallo${row}" >Fuera de Servicio</td>
			</c:when>
			<c:when test="${tiponodo.estado == 'M'}">
				<td align="center" class="mantenimiento${row}" >Mantenimiento</td>
			</c:when>
			<c:when test="${tiponodo.estado == 'I'}">
				<td align="center" class="inter${row}" >Interactiva</td>
			</c:when>
			<c:otherwise>
				<td align="center" class="" >Operando</td>
			</c:otherwise>
		</c:choose>
		
		<td align="center">
		<!-- esto se saco del if el 24-10-2005 : tiponodo.estado == 'M' || -->
			<input name="chkTel" id="${tiponodo.tipoCabeza.nombre}" type="radio" value="${tiponodo.id}" unchecked ${tiponodo.estado == 'I' ?" disabled ":""}>
		</td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>

</table>
</form>
<br>
<!--div align="center"><a name="agregarTipoNodo" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarTipoNodo">Agregar nuevo Tipo de Nodo</a></div-->
</body>

</html>

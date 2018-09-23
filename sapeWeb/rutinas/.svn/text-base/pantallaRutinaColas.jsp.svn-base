					<!-- pantallaRutinaColas.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="cola" class="java.lang.String" scope="request"/>
<jsp:useBean id="listaAsignados" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="listaCodigos" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="listaNaturalezasAsignadas" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="listaNaturalezas" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="msg" class="java.lang.String" scope="request"/>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<title>SAPE - Rutinas Por COLA</title>

<script language="JavaScript">

	function cerrar() {
		parent.window.close();
	}

	function consulta(armario) {
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=consultaHorarioArmarios&armario="+armario;
		return;
	}

	function importar(armario) {
		if(confirm("Esta seguro de importar la informacion\ndel armario "+armario+" ?"))
			location.href="${pageContext.request.contextPath}/actionSape?accion=rutinasArmario&operacion=importar&armario=" + armario + "#fin";
		return;
	}

function adicionar(){
		
		var codigosFalla = getDatosForma('1');
		var naturalezasReclamo = getDatosForma('2');

		//if(codigosFalla == "" && naturalezasReclamo == "")
			//return;
		var mensaje = "A continuacion se asociaran ";
		var an = "";
		if(codigosFalla !=""){
			mensaje += "los siguientes codigos de falla ["+codigosFalla+"]\n"
			an = " y ";
		}
		if(naturalezasReclamo != ""){
			mensaje += an+"las naturalezas de reclamo ["+naturalezasReclamo+"]\n";
		}
		
		mensaje += "con la cola ${cola}. Esta Seguro?";
		
		if(codigosFalla == "" && naturalezasReclamo == ""){
			mensaje = "Se quitaran todos los codigos de Falla y naturalezas asignadas a esta cola (${cola}).\nEsta seguro?";
			
			if(confirm(mensaje)){
				location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=adicionarCodigosCola&codigosFalla=&naturalezasReclamo=&cola=${cola}";
			}
			return;			
		}
		
		if(confirm(mensaje)){
			location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=adicionarCodigosCola&codigosFalla="+codigosFalla+"&naturalezasReclamo="+naturalezasReclamo+"&cola=${cola}";
	}
}


function getDatosForma(idForma){

		var forma = document.getElementById("forma"+idForma);
		
		var valor2= "";
		var codigos = '';
		var cantTel = forma.chkTel.length;

		if(cantTel == undefined){
			if(forma.chkTel.checked == false){
				return "";
			}
			codigos = forma.chkTel.value+",";

		}else if(forma.chkTodas.checked){
			codigos = 'todos';
		}else{
			for (j = 0; j < cantTel; j++) {
				if(forma.chkTel[j].checked){
					valor2 = forma.chkTel[j].value;
					codigos = codigos + forma.chkTel[j].value+",";
				}
			}
			if(valor2 == ""){
				return "";
			}
		}
	
		return codigos;	

}


function seleccionarTodas(idForma) {
	var form = document.getElementById("forma"+idForma);
	var estado = form.chkTodas.checked;
	var cantColas = form.chkTel.length;

	if(cantColas == undefined){

		form.chkTel.checked = estado;
		return;
	}

	for (i = 0; i < cantColas; i++) {
		form.chkTel[i].checked = estado;
	}
}

function buscarEstado(estado) {

	location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=pantallaRutinaArmarios&armario=${armario}&estado="+estado;
}

</script></head>

<body topmargin="0" leftmargin="0" alink="olive" link="#330099">
<br>
<table align="center" bgcolor="black" width="90%">
<tr>
<td align="center">
<font color="white" face="Arial" size="4"><b>
Pruebas Rutinarias Por Cola ${cola}
</b></font><tags:ayudas pagina="FALTA_AYUDA"/>
</td>
</tr>
</table>

<p>
<CENTER><c:if test="${msg != ''}" ><font color="black" size="3">${msg}</font></c:if></CENTER>
</p><br>
<table align="center" border="0" cols="1" width="90%">
<tr>
</tr>

<tr>
	<td align="center">
		<input type="button" class="boton" name="Consulta" onClick="JavaScript:adicionar();"  value="Aceptar">
		<input class="boton" name="Cerrar" onClick="javascript:cerrar()" value=" Cerrar " type="button">
	</td>
</tr>
</table>
<br>
</td>
</tr>
</table>

<table align="center" border="0" cols="1" width="100%" cellspacing="0">

<c:if test="${empty estado}">
	<c:set var="estado" value=""/>
</c:if>

<tr bgcolor="black">
<td align="center"><font color="white" face="Arial, Helvetica, sans-serif" size="2"><b>Codigos para la cola ${cola} </b></font></td>
</tr>
</table>


<table align="center" border="0" width="100%">
<tr>
<td width="50%" valign="top">
<%-- [INICIO] CODIGOS DE FALLA --%>

<form name="forma1" id="forma1">
<table align="center" border="0" width="100%">

<tr bgcolor="black">
<td align="center" colspan="3"><font color="white" face="Arial, Helvetica, sans-serif" size="2"><b>Codigos de Falla </b></font></td>
</tr>

<tr bgcolor="black">
	<td align="center" width="30" class="header-reporte">Todos<input type="checkbox" name="chkTodas" onClick="seleccionarTodas('1');"></td>
	<td align="center" width="20" class="header-reporte">Codigo</td>
	<td align="center" width="90" class="header-reporte">Nombre</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaCodigos}" var="codigo">
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
<tr class="row${row}">

	<c:set var="asignado" value=""/>
	<c:forEach items="${listaAsignados}" var="asig">
		<c:if test="${codigo.fallaId == asig}">
				<c:set var="asignado" value="checked"/>
		</c:if>
	</c:forEach>


	<td align="center" width="30">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chkTel" value="${codigo.fallaId}" ${asignado}> </td>
	<td align="center" width="20">${codigo.fallaId}</td>
	<td align="center" width="90">${codigo.nombre}</td>
</tr>
<c:set var="i" value="${i+1}" />
</c:forEach>
</table>
</form>

<%--[FIN] CODIGOS DE FALLA --%>

</td>
<td width="50%" valign="top">
<%-- [INICIO] NATURALEZAS DE RECLAMO --%>

<form name="forma2" id="forma2">
<table align="center" border="0" width="100%">
<tr bgcolor="black">
<td align="center" colspan="3"><font color="white" face="Arial, Helvetica, sans-serif" size="2"><b>Naturalezas de Reclamo </b></font></td>
</tr>
<tr bgcolor="black">
	<td align="center" width="30" class="header-reporte">Todos<input type="checkbox" name="chkTodas" onClick="seleccionarTodas('2');"></td>
	<td align="center" width="20" class="header-reporte">Codigo</td>
	<td align="center" width="90" class="header-reporte">Nombre</td> 
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaNaturalezas}" var="naturaleza">
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
<tr class="row${row}">

	<c:set var="asignado" value=""/>
	<c:forEach items="${listaNaturalezasAsignadas}" var="asig">
		<c:if test="${naturaleza.fallaId == asig}">
				<c:set var="asignado" value="checked"/>
		</c:if>
	</c:forEach>


	<td align="center" width="30">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chkTel" value="${naturaleza.fallaId}" ${asignado}> </td>
	<td align="center" width="20">${naturaleza.fallaId}</td>
	<td align="center" width="90">${naturaleza.nombre}</td>
</tr>
<c:set var="i" value="${i+1}" />
</c:forEach>
</table>
</form>


<%-- [FIN] NATURALEZAS DE RECLAMO --%>
</td>
</tr>
</table>
</body>
</html>

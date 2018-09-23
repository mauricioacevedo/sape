		<!-- mantenimientoLI.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="li" scope="request" class="com.osp.sape.maestros.SiplexproLI" />
<% java.util.List listaCabezas = (java.util.List) request.getAttribute("listaCabezas"); %>
<jsp:useBean id="destino" class="java.lang.String" scope="request"/>
<html>
<head>
<TITLE>SAPE - Mantenimiento LI's</TITLE> 
<style type="text/css">

.mensajeCentral { font-weight: bold; font-size: 14; text-decoration: none; color: white; background: black}
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript">
<!--


	function eliminarLI(){
		var tel = document.getElementById("txtTelefono").value;
		
		if(tel == ""){
			alert("El campo telefono es vacio");
			return;
		}
		
		if(confirm("Se eliminara el LI asignado al telefono "+tel+"\nEsta seguro?")){
			location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=eliminarLI&telefono="+tel;
		}
	}

	function validarTelefono(forma) {
		var txtTelefono = forma.txtBuscarTelefono;
		var telefono = txtTelefono.value;
		if (telefono.length < ${applicationScope.cantDigitosEntorno}) {
			alert ("El telefono debe ser de ${applicationScope.cantDigitosEntorno} digitos!");
			txtTelefono.focus();
			return false;
		}
		if (isNaN(telefono)) {
			alert ("El telefono debe ser numerico!");
			txtTelefono.focus();
			return false;
		}
		return true;
	}

		
	function validarCampos(forma) {
		//alert('validarCampos: ' + forma);
		var cboCabeza = forma.cboCabeza;
		//alert('cboCabeza: ' + cboCabeza.selectedIndex);
		if (cboCabeza.selectedIndex == 0) {
			alert("Seleccione una Cabeza!");
			cboCabeza.focus();
			return false;
		}
		var txtli = forma.txtli;
		if (txtli.value == "") {
			alert("LI no valido!");
			txtli.focus();
			return false;
		}
		
		if(confirm("Se actualizara el LI del telefono "+document.getElementById('txtTelefono').value + "?")){
			return true;
		} else{
			return false;
		}
	}
	
	function listarTipoNodos (telefono) {
		if (isNaN(telefono) || parseInt(telefono) == 0) {
			alert("telefono no valido!");
			return;
		}
		var pagina = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=listaCabezasCentral&telefono=" + telefono;
		var ventana = 
		window.open(pagina,'ListaCabezasCentral','scrollbars=yes,resizable=yes,hotkeys=yes,height=400,width=750,left=20,top=10,menubar=no,toolbar=no');
		ventana.focus();
	}
//-->
</script>
</head>
<body bgcolor="white" onLoad="document.frmBuscarTelefono.txtBuscarTelefono.focus();">
<jsp:include page="../../encabezado.jsp" flush="true" />
<br>
<br>
<table align="center" border="0" cols="1" width="100%">
<tr>
	<td align="center" class="mensajeCentral">Mantenimiento LI's SIPLEXPRO</td>
</tr>
</table>
<br>
<form name="frmBuscarTelefono" action="actionSape">

<input type="hidden" name="accion" value="mantenimientoSiplexPro">
<input type="hidden" name="operacion" value="buscarLI">
<TABLE width="300" border="1" align="center">
  <tr>
	<td>Tel&eacute;fono:</td>
	<td><INPUT type="text" name="txtBuscarTelefono" size="10" maxlength="${applicationScope.cantDigitosEntorno}" class="texto"></td>
	<td><INPUT type="submit" name="cmdAceptarTelefono" value="Buscar" onClick="return validarTelefono(document.frmBuscarTelefono);" class="boton"></td>
  </tr>
</TABLE>
</form>
<br>
<br>
<form name="fmrLI">
<input type="hidden" name="accion" value="mantenimientoSiplexPro">
<input type="hidden" name="operacion" value="actualizarLI">
<input type="hidden" name="telefono" value="${li.telefono}">
<table width="300" align="center">
<TR>
	<TD>Tel&eacute;fono:</TD>
	<td><input type="text" name="txtTelefono" id="txtTelefono" disabled="true" value="${li.telefono}" class="texto"></td>
</TR>
<tr>
	<TD>Central:</TD>
	<td><input type="text" name="txtCentral" disabled="true" value="${li.central}" class="texto"></td>
</tr>
<tr>
	<TD><span title="Consultar Cabezas con la central ${li.central}"><a href="javascript:listarTipoNodos(${li.telefono})">Cabeza:</a></span></TD>
	<td>
		<select name="cboCabeza">
			<option>Seleccione</option>
			<c:forEach var="cabeza" items="${listaCabezas}">
			<option value="${cabeza.id}"${cabeza.id == li.tipoNodo.id ? " selected" : ""}>${cabeza.site}</option>
			</c:forEach>
		</select> 
	</td>
</tr>
<tr>
	<TD>LI:</TD>
	<td><input type="text" name="txtli" value="${li.li}" maxlength="10" class="texto"></td>
</tr>
<tr>
	<TD align="center" height="90">
		<input type="submit" name="cmdActualizar" value="Actualizar" onClick="return validarCampos(document.fmrLI);" class="boton">
	</TD>
	<TD align="center" height="90">
		<input type="button" name="eliminar" value="Eliminar" onClick="javascript:eliminarLI();" class="boton">
	</TD>
</tr>
</table>
</form>

<center>
<jsp:include page="../../utils/fileUpload.jsp" flush="true" />
</center>
<br>
<div align="center"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoSiplexPRO">Regresar</a></div>
</body>
</html>

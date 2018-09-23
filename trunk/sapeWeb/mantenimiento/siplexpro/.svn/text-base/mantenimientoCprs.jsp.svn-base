				<!-- mantenimientoCprs.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cpr" scope="request" class="com.osp.sape.maestros.CPRSiplexPro" />
<% java.util.List listaCabezas = (java.util.List) request.getAttribute("listaCabezas"); %>
<html>
<head>
<TITLE>SAPE - Mantenimiento Cpr's</TITLE>
<style type="text/css">

.mensajeCentral { font-weight: bold; font-size: 14; text-decoration: none; color: white; background: black}
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript">
<!--
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
		var txtCPR = forma.txtCPR;
		if (isNaN(txtCPR.value) || parseInt(txtCPR.value) == 0) {
			alert("CPR no valido!");
			txtCPR.focus();
			return false;
		}
		return true;
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
	<td align="center" class="mensajeCentral">Mantenimiento CPR's SIPLEXPRO <tags:ayudas pagina="cpr"/></td>
</tr>
</table>
<br>
<form name="frmBuscarTelefono" action="actionSape">
<input type="hidden" name="accion" value="mantenimiento">
<input type="hidden" name="operacion" value="buscarCPR">
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
<form name="fmrCPR">
<input type="hidden" name="accion" value="mantenimiento">
<input type="hidden" name="operacion" value="actualizarCPR">
<input type="hidden" name="telefono" value="${cpr.telefono}">
<table width="300" align="center">
<TR>
	<TD>Tel&eacute;fono:</TD>
	<td><input type="text" name="txtTelefono" disabled="true" value="${cpr.telefono}" class="texto"></td>
</TR>
<tr>
	<TD>Central:</TD>
	<td><input type="text" name="txtCentral" disabled="true" value="${cpr.central}" class="texto"></td>
</tr>
<tr>
	<TD><span title="Consultar Cabezas con la central ${cpr.central}"><a href="javascript:listarTipoNodos(${cpr.telefono})">Cabeza:</a></span></TD>
	<td>
		<select name="cboCabeza">
			<option>Seleccione</option>
			<c:forEach var="cabeza" items="${listaCabezas}">
			<option value="${cabeza.id}"${cabeza.id == cpr.tipoNodo.id ? " selected" : ""}>${cabeza.site}</option>
			</c:forEach>
		</select> 
	</td>
</tr>
<tr>
	<TD>CPR:</TD>
	<td><input type="text" name="txtCPR" value="${cpr.cpr}" maxlength="1" class="texto"></td>
</tr>
<tr>
	<TD colspan="2" align="center" height="90">
		<input type="submit" name="cmdActualizar" value="Actualizar" onClick="return validarCampos(document.fmrCPR);" class="boton">
	</TD>
</tr>
</table>
</form>

<div align="center"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoSiplexPRO">Regresar</a></div>

</body>
</html>

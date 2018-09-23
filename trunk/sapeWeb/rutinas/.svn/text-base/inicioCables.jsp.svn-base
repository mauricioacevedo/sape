			<!-- inicioCables.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="jsp2" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- java.util.List listaArmarios = (java.util.List) request.getAttribute("listaArmarios"); --%>
<% java.util.List listaCables = (java.util.List) request.getAttribute("listaCables"); %>
<jsp:useBean id="msgCables" class="java.lang.String" scope="request"/>
<jsp:useBean id="maxPruebas" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Rutinas Por Cable</title>
<script language="JavaScript" src="javascript/common.js"> </script>
<script language="JavaScript">

  function limpia_forma(forma) {
    forma.reset();
    return;
  }
	


function verif_entradas_porcable(forma) {

if  ( forma.c19.checked ) c19=1;
 else c19=0;

if  ( forma.c20.checked ) c20=1;
 else c20=0;

if  ( forma.c21.checked ) c21=1;
 else c21=0;

if  ( forma.c22.checked ) c22=1;
 else c22=0;

if  ( forma.c23.checked ) c23=1;
 else c23=0;

if  ( forma.c24.checked ) c00=1;
 else c00=0;

if  ( forma.c1.checked ) c01=1;
 else c01=0;

if  ( forma.c2.checked ) c02=1;
 else c02=0;

if  ( forma.c3.checked ) c03=1;
 else c03=0;

if  ( forma.c4.checked ) c04=1;
 else c04=0;

if  ( forma.c5.checked ) c05=1;
 else c05=0;

if  ( forma.c6.checked ) c06=1;
 else c06=0;

confirmacion=window.confirm(" Hora 19="+c19+", Hora 20 ="+c20+", Hora 21="+c21+", Hora 22="+c22+", Hora 23="+c23+", Hora 00="+c00+", Hora 01="+c01+", Hora 02="+c02+", Hora 03="+c03+", Hora 04="+c04+", Hora 05="+c05+", Hora 06="+c06);

var ca = forma.cable.value;
	if (confirmacion) {
		this.window.focus();
		v210=window.open("${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=actualizarRutinaCable&c19="+c19+"&c20="+c20+"&c21="+c21+"&c22="+c22+"&c23="+c23+"&c00="+c00+"&c01="+c01+"&c02="+c02+"&c03="+c03+"&c04="+c04+"&c05="+c05+"&c06="+c06+"&cable="+ca,'window210','scrollbars=yes, resizable=yes,hotkeys=yes,height=500,width=600,left=0,top=0,menubar=yes,toolbar=no');
		v210.focus();
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=inicioCables";
	}
}


function cargarHorarioCable(forma){
	
	var ca=forma.selectCables.options[forma.selectCables.selectedIndex].value;
	if(ca == -1){
		return;
	}
	document.getElementById('iframeOculto').src='${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=cargarHorarioCable&cable='+ca;
	
}


function retirarRutinaCable(forma){
	
	var ca =forma.selectCables.options[forma.selectCables.selectedIndex].value;
	
	if(ca == -1){
		return;
	}
	
	if(window.confirm("Esta seguro que desea eliminar\nEl Cable: "+ca+"?"))
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=eliminarHorarioCable&cable="+ca;
}

function Abre_ventanaCable(forma) {

	var cable=forma.cable.value;   
  
  	if(cable.length < 1){
  		alert("Seleccione un CABLE!");
		return;
  	}	
	
	v203=window.open("${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=pantallaRutinaCables&cable="+cable,'window203','scrollbars=yes, resizable=yes,hotkeys=yes,height=570,width=600,left=0,top=0,menubar=yes,toolbar=no');
	v203.focus();
	
  }

</script>
</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0" bgcolor="WHITE">
<BR>
<table align="center" width="720" bgcolor="BLACK">
<TR><TD class="mensajeCentral" align="center">
HORARIOS DE RUTINAS DE PRUEBAS
</TD></TR>
</table>
<BR>
<iframe id="iframeOculto" name="iframeOculto" style="border: 0px none ; width: 400px; height: 0px;" visibility="false"></iframe>
<BR>
<form name="rutinasxcable">
<table width="720" border="1" cellspacing="0" cellpadding="0" align="center" height="40">
	<TR valign="middle">
		<TD align="center" class="mensajeCentral" colspan="14">
			POR CABLE
			<jsp2:if test="${msgCables != ''}" >&nbsp;&nbsp;(${msgCables}) </jsp2:if>
			&nbsp;<tags:ayudas pagina="Rcable"/>
		</TD>
	</TR>
		<tr class="fondo2">
			<td width="50" height="15" align="center"><b><font face="Arial" size="1">HORARIO</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">19</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">20</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">21</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">22</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">23</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">24</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">1</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">2</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">3</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">4</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">5</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">6</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">Detalles</font></b></td>
		</tr>
		<tr>
			<td width="50"  height="15" class="fondo2">
				<font face="Verdana" size="2" COLOR="BLACK"><B>Cable: <input type="text" name="cable" id="campoCable" size="9" class="texto"></font></b></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c19" id="cab1"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c20" id="cab2"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c21" id="cab3"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c22" id="cab4"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c23" id="cab5"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c24" id="cab6"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c1" id="cab7"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c2" id="cab8"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c3" id="cab9"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c4" id="cab10"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c5" id="cab11"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c6" id="cab12"></td>
			<td width="40"  align="center" height="15" class="fondo2">
				<input type="button" class="boton" value="Editar" OnClick="javascript:Abre_ventanaCable(document.rutinasxcable)" name="editarCliente"></td>
		</tr>
		<tr>
		<td width="25%" class="fondo2">
		<font face="Verdana" size="2" COLOR="BLACK">
		<b>Actuales:&nbsp;&nbsp;&nbsp;</b></font><select name="selectCables" onChange="javascript:cargarHorarioCable(document.rutinasxcable)">
		<option value="-1">Seleccione:</option>
		<jsp2:forEach items="${listaCables}" var="ca">
			<option value="${ca}">${ca}</option>
		</jsp2:forEach>
		</select>
			<td colspan="12">
				<input type="button" class="boton" value="Eliminar Cable" onClick="javascript:retirarRutinaCable(document.rutinasxcable);">
			</td>
		</td>
		<td class="fondo2">&nbsp;</td>
		</tr>
		
	</table>
	<br>
	<table width="330" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr> 
			<td align="center" width="33%">
				<input type="button" class="boton" value="Aceptar" onClick="verif_entradas_porcable(document.rutinasxcable)">
				&nbsp;&nbsp;
				<input type="button" class="boton" value="Limpiar" onClick="limpia_forma(document.rutinasxcable)">
			</td>
		</tr>
	</table>
</form>
<br>
	<center>${fn:length(listaCables)} rutinas de ${maxPruebas}</center>
<br>
<center><a href="${pageContext.request.contextPath}/actionSape?accion=rutinas">Regresar</a></center>

</body>
</html>

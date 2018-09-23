				<!-- inicioArmarios.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="jsp2" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% java.util.List listaArmarios = (java.util.List) request.getAttribute("listaArmarios"); %>
<jsp:useBean id="totalClientes" class="java.lang.String" scope="request"/>
<jsp:useBean id="msgArmarios" class="java.lang.String" scope="request"/>
<jsp:useBean id="maxPruebas" class="java.lang.String" scope="request"/>

<html>
<head> 
<title>SAPE - Rutinas Por Armario</title>
<script language="JavaScript" src="javascript/common.js"> </script>
<script language="JavaScript">

function limpia_forma(forma) {
    forma.reset();
    return;
}
	

function verif_entradas_porarmario(forma) {

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

var arm = forma.armario.value;
	if (confirmacion) {
		this.window.focus();
		v210=window.open("${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=actualizarRutinaArmario&c19="+c19+"&c20="+c20+"&c21="+c21+"&c22="+c22+"&c23="+c23+"&c00="+c00+"&c01="+c01+"&c02="+c02+"&c03="+c03+"&c04="+c04+"&c05="+c05+"&c06="+c06+"&armario="+arm,'window210','scrollbars=yes,resizable=yes,hotkeys=yes,height=500,width=600,left=0,top=0,menubar=yes,toolbar=no');
		v210.focus();
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=inicioArmarios";
	}
}


function cargarHorarioArmarios(forma){

	var arm=forma.selectArmarios.options[forma.selectArmarios.selectedIndex].value;
	if(arm == -1){
		return;
	}
	document.getElementById('iframeOculto').src='${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=cargarHorarioArmario&armario='+arm;
	
}


function retirarRutinaArmario(forma){
	var arm =forma.selectArmarios.options[forma.selectArmarios.selectedIndex].value;
	if(arm == -1){	
		return;
	}
	
	if(window.confirm("Esta seguro que desea eliminar\nEl armario: "+arm+"?"))
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=eliminarHorarioArmario&armario="+arm;
}


function Abre_ventanaArmario(forma) {

  this.window.focus();
  var armario=forma.armario.value;
  
  if(armario.length < 1){
  	alert("Seleccione un ARMARIO");
	return;
  }
  
  v203=window.open("${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=pantallaRutinaArmarios&armario="+armario,'window203','scrollbars=yes,resizable=yes,hotkeys=yes,height=570,width=600,left=0,top=0,menubar=yes,toolbar=no');
  v203.focus();
}

</script>
</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0" bgcolor="WHITE">
<BR>
<table align="center" width="720">
<TR><TD align="center" class="mensajeCentral">
HORARIOS DE RUTINAS DE PRUEBAS
</TD></TR>
</table>
<BR>

<iframe id="iframeOculto" name="iframeOculto" style="border: 0px none ; width: 400px; height: 0px;" visibility="false"></iframe>


<BR>
<table align="center" width="720" bgcolor="BLACK">
<TR>
	<TD align="center" class="mensajeCentral"> 
    POR ARMARIO
    <jsp2:if test="${msgArmarios != ''}" >&nbsp;&nbsp;(${msgArmarios}) </jsp2:if> <tags:ayudas pagina="Rarmario"/>
</TD>
</TR>
</table>

<form name="rutinasxarmario">
<table width="720" border="1" cellspacing="0" cellpadding="0" align="center" height="40">
		<tr class="fondo2">             
			<td width="50" height="20" align="center"><b><font face="Arial" size="1">HORARIO</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">19</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">20</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">21</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">22</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">23</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">00</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">1</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">2</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">3</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">4</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">5</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">6</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">Detalles</font></b></td>
		</tr>
		<tr> 
			<td width="50" height="20" class="fondo2">
				<font face="Verdana" size="2" COLOR="BLACK"><B>Armario: 
				<input tipe="text" class="texto" name="armario" id="campoArmario" size="8"></font></b>
			</td>
			<td width="20"  height="15" align="center"><input type="radio" name="c19" id="arm1"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c20" id="arm2"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c21" id="arm3"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c22" id="arm4"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c23" id="arm5"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c24" id="arm6"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c1" id="arm7"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c2" id="arm8"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c3" id="arm9"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c4" id="arm10"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c5" id="arm11"></td>
			<td width="20"  height="15" align="center"><input type="radio" name="c6" id="arm12"></td>
			<td width="40"  align="center" height="15" class="fondo2">
				<input type="button" class="boton" value=Editar OnClick="javascript:Abre_ventanaArmario(document.rutinasxarmario)" name="editarArmario">
				</td>
		</tr>
		<tr>
		<td class="fondo2" width="25%">
		<font face="Verdana" size="2" COLOR="BLACK">
		<b>Actuales:&nbsp;&nbsp;&nbsp;</b></font><select name="selectArmarios" onChange="javascript:cargarHorarioArmarios(document.rutinasxarmario)">
		<option value="-1">Seleccione:</option>
		<jsp2:forEach items="${listaArmarios}" var="arm">
			<option value="${arm}">${arm}</option>
		</jsp2:forEach>
		</select>
			<td colspan="12">
				<input type="button" class="boton" value="Eliminar Armario" onClick="javascript:retirarRutinaArmario(document.rutinasxarmario);">
				
			</td>
		</td>
		<td class="fondo2">&nbsp;</td>
		</tr>
	</table>
	<br>
	<table width="330" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr> 
			<td align="center" width="33%">
				<input type="button" class="boton" name="aceptarxcliente" value="Aceptar" onClick="verif_entradas_porarmario(document.rutinasxarmario)">
				&nbsp;&nbsp;
				<input type="button" class="boton" name="Limpiarxcliente" value="Limpiar" onClick="limpia_forma(document.rutinasxarmario)">
			</td>
		</tr>
	</table>	
</form>
<br>
	<center>${fn:length(listaArmarios)} rutinas de ${maxPruebas}</center>
<br>
<center><a href="${pageContext.request.contextPath}/actionSape?accion=rutinas">Regresar</a></center>

</body>
</html>

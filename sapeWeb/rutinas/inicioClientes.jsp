					<!-- inicioClientes.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="totalClientes" class="java.lang.String" scope="request"/>
<jsp:useBean id="vectorClientes" class="java.util.ArrayList" scope="request"/>
<c:set var="listaClientes" value="${vectorClientes[0]}"/>
<html>
<head> 
<title>SAPE - Rutinas Por Clientes</title>
<script language="JavaScript" src="javascript/common.js"> </script>
<script language="JavaScript">

  function limpia_forma(forma) {
    //forma.reset();
	var i = 0;
	for(i;i<24;i++) {
		
		var radio = document.getElementById('c'+i);
		
		radio.checked = false;
	}
	
	
    return;
    }
	
function verif_entradas_porcliente(forma) {

	var i=0;
	var query = "";
	var queryShow = "";
	for(i;i<24;i++){
		
		var radio = document.getElementById('c'+i);
		
		if(radio.checked){
			query += "&c"+i+"=1";
			if(i<=9){
				queryShow += " HORA 0"+i+"\n";
			}else{
				queryShow += " HORA "+i+"\n";
			}
			
			
		} else {
			query += "&c"+i+"=0";
		}
	}
	
	var msg = "Se tendran rutinas en las siguientes horas:\n"+queryShow;
if(queryShow == ""){
	msg = "No se selecciono ninguna hora para hacer pruebas de rutina.";
}

confirmacion=window.confirm(msg);


if (confirmacion) {
this.window.focus();
v210=window.open("${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=actualizarRutinaClientes"+query,'window210','scrollbars=yes, resizable=yes,hotkeys=yes,height=500,width=600,left=0,top=0,menubar=yes,toolbar=no');
v210.focus();

}
}
<!-- ------------------------------------------------------------------------------- -->

function Abre_ventanaCliente() {
  
  v203=window.open("${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=pantallaRutinaClientes",'window203','scrollbars=yes, resizable=yes,hotkeys=yes,height=600,width=600,left=0,top=0,menubar=yes,toolbar=no');
  v203.focus();
}

</script>
</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0" bgcolor="WHITE">
<BR>
<table align="center" width="720" bgcolor="BLACK">
<TR><TD align="center" class="mensajeCentral">
HORARIOS DE RUTINAS DE PRUEBAS
</TD></TR>
</table>
<BR>
<iframe id="iframeOculto" name="iframeOculto" style="border: 0px none ; width: 400px; height: 0px;" visibility="false"></iframe>
<BR>
<table align="center" width="720" bgcolor="BLACK">
<TR>
  <TD align="center" class="mensajeCentral">Por Clientes '${totalClientes}' <tags:ayudas pagina="Rcliente"/> </TD>
</TR>
</table> 

<form name="rutinasxcliente">
<table width="720" border="1" cellspacing="0" cellpadding="0" align="center" height="40">

		<tr class="fondo2">
			<td width="50" height="15" align="center"><b><font face="Verdana" size="3" COLOR="BLACK">Hora</font></b></td>
			
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">00</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">01</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">02</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">03</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">04</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">05</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">06</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">07</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">08</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">09</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">10</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">11</font></b></td>
			<td width="20" height="15" align="center"><b><font face="Verdana" size="3" COLOR="BLACK">Detalles</font></b></td>
		</tr>
		<tr>
			<td width="50" height="15" class="fondo2"><font face="Verdana" size="3" COLOR="BLACK"><B><center>Activo</center></font></b></td>
			<c:forEach begin="0" end="11" var="i">
			<td width="20" height="15" align="center" bgcolor="WHITE">
				<input id="c${i}" type="radio" name="c${i}" ${fn:contains(listaClientes[i],'1')?"checked":""}></td>
			</c:forEach>
			
			<td width="20" rowspan="3" align="center" height="15">
				<input type="button" class="boton" value="Editar" OnClick="javascript:Abre_ventanaCliente()" name="editarCliente">
			</td>
		</tr>

		<tr class="fondo2">
			<td width="50" height="15" align="center"><b><font face="Verdana" size="3" COLOR="BLACK">Hora</font></b></td>
			<c:forEach begin="12" end="23" var="i">
			<td width="20" height="15" align="center"><b><font face="Arial" size="1">${i}</font></b></td>
			</c:forEach>
		</tr>
		<tr>
			<td width="50" height="15" class="fondo2"><font face="Verdana" size="3" COLOR="BLACK"><B><center>Activo</center></font></b></td>
			<c:forEach begin="12" end="23" var="i">
			<td width="20"  height="15" align="center" bgcolor="WHITE">
				<input type="radio" name="c${i}" id="c${i}" ${fn:contains(listaClientes[i],'1')?"checked":""}>
			</td>
			</c:forEach>
		</tr>
	</table>
	<br>
	<table width="330" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr> 
			<td align="center" width="33%">
				<input type="button" class="boton" value="Aceptar" onClick="verif_entradas_porcliente(document.rutinasxcliente)">
				&nbsp;&nbsp;
				<input type="button" class="boton" value="Limpiar" onClick="limpia_forma(document.rutinasxcliente)">
			</td>
		</tr>
	</table>
</form>
<br>
<br>
<center><a href="${pageContext.request.contextPath}/actionSape?accion=rutinas">Regresar</a></center>

</body>
</html>

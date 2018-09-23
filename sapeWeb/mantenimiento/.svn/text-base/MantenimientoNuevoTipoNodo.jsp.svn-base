				<!-- MantenimientoNuevoTipoNodo.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head>
<title>Sistema Administrativo de Pruebas Extendidas - SAPE - Alta Tipos de Nodo</title>
  


<script language="JavaScript">


function verif_entradas(forma) {

  if(forma.nombre.value.length<1) {
      window.alert("Nombre del Tipo Nodo no valido");
      forma.nombre.focus();
      return;
   }
 
   if (forma.tipocabeza.options[forma.tipocabeza.selectedIndex].value==0) {
    window.alert("Seleccione Tipo de Cabeza");
    forma.tipocabeza.focus();
    return;
   }


  if(forma.ipservidor.value.length<1) {
      window.alert("Ip servidor no valido");
      forma.ipservidor.focus();
      return;
   }

  if(forma.puertoservidor.value.length<1) {
      window.alert("Puerto servidor no valido");
      forma.puertoservidor.focus();
      return;
   }

  if(forma.ipcabeza.value.length<1) {
      window.alert("Ip cabeza no valido");
      forma.ipcabeza.focus();
      return;
   }

  if(forma.puertocabeza.value.length<1) {
      window.alert("Puerto cabeza no valido");
      forma.puertocabeza.focus();
      return;
   }
   
  var cboEstado = forma.estado.options[forma.estado.selectedIndex];
  if (cboEstado.value=="0") {
    window.alert("Seleccione el estado del Tipo Nodo");
    cboEstado.focus();
    return;
  }

confirmacion=window.confirm("Estos son los datos para el nuevo Tipo de Nodo:\n" +
				"\nNombre: "+forma.nombre.value +
				"\nCabeza: "+forma.tipocabeza.options[forma.tipocabeza.selectedIndex].text+
				"\nIP Servidor: "+forma.ipservidor.value+
				"\nPuerto Servidor: "+forma.puertoservidor.value+
				"\nIP Cabeza: "+forma.ipcabeza.value+
				"\nPuerto Cabeza: "+forma.puertocabeza.value + 
				"\nEstado: " + cboEstado.text);
if (confirmacion) insertar(forma.nombre.value,forma.ipservidor.value,forma.puertoservidor.value,forma.ipcabeza.value,forma.puertocabeza.value,forma.tipocabeza.options[forma.tipocabeza.selectedIndex].value, cboEstado.value);


}

function insertar(nombre,ipservidor,puertoservidor,ipcabeza,puertocabeza,tipocabeza, estado) {
location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=ejecutarAgregarTipoNodo&id=${tiponodo.id}&nombre="+nombre+"&ipservidor="+ipservidor+"&puertoservidor="+puertoservidor+"&ipcabeza="+ipcabeza+"&puertocabeza="+puertocabeza+"&tipocabeza="+tipocabeza+"&estado="+estado;
}

</script></head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body topmargin="0" leftmargin="0" alink="olive" link="#330099" bgcolor="white">
<form name="altanodos">
<br>
<table align="center" bgcolor="BLACK" cols="1" width="40%">
<tbody><tr>
<td>
<font color="white" face="Arial" size="4"></font><center><font color="white" face="Arial" size="4"><b>
Alta Tipo de Nodo
</b></font>
</center></td>
</tr>
</tbody></table>

<p>

</p><table align="center" border="1" cols="2" width="40%">

<tbody><tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Nombre SITE
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input name="nombre" size="20" value="" maxlength="20" type="text">
</font>
</td>
</tr>


<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Tipo Cabeza
</font>
</td>
<td>
<select name="tipocabeza">
	<option value="0">Seleccione</option>
	<c:forEach items="${requestScope.listaCabezas}" var="cabeza">
	<option value="${cabeza.id}">${cabeza.nombre}</option>
	</c:forEach>
</select>
</td>
</tr>



<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
IP Servidor
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input name="ipservidor" size="20" value="" maxlength="20" type="text">
</font>
</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Puerto Servidor
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input name="puertoservidor" size="20" value="" maxlength="20" type="text">
</font>
</td>
</tr>


<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
IP Cabeza
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input name="ipcabeza" size="20" value="" maxlength="20" type="text">
</font>
</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Puerto Cabeza
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input name="puertocabeza" size="20" value="" maxlength="20" type="text">
</font>
</td>
</tr>
<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Estado
</font>
</td>
<td>
<select name="estado">
	<option value="0">Seleccione</option>
	<option value="O">Operando</option>
	<option value="F">Fallo</option>
</select>
</td>
</tr>

</tbody></table>
<table align="center" border="0" cols="1" width="60%">
<tbody><tr>
</tr>

<tr>

<td><font color="RED" face="verdana" size="4"></font><center>
<font color="RED" face="verdana" size="4"><input name="AltaTipoNodo" onclick="javascript:verif_entradas(document.altanodos)" value=" Alta Tipo de Nodo " type="button"></font></center></td>

</tr>
</tbody></table>

</form>

</body></html>

<html><head><title>Alta Clase de Nodo - SAPE</title>
  


<script language="JavaScript">


function verif_entradas(forma) {

  if(forma.nombre.value.length<1) {
      window.alert("Nombre nombre cabeza NO VALIDA");
      forma.nombre.focus();
      return;
   }
 

  if(forma.provedor.value.length<1) {
      window.alert("Nombre provedor NO VALIDO");
      forma.provedor.focus();
      return;
   }

confirmacion=window.confirm("Estos son los datos \npara la nueva Clase de Nodo:\n\nNombre Cabeza="+forma.nombre.value+"\nVendor="+forma.provedor.value);

if (confirmacion) insertar(forma.nombre.value,forma.provedor.value);


}

function insertar(nombre,provedor) {
location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=ejecutarAgregarCabezaPrueba&nombre="+nombre+"&proveedor="+provedor;

}

</script></head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body topmargin="0" leftmargin="0" alink="olive" bgcolor="white" link="#330099">
<form name="altaclasenodos">
<br>
<table align="center" bgcolor="BLACK" cols="1" width="40%">
<tbody><tr>
<td>
<font color="white" face="Arial" size="4"></font><center><font color="white" face="Arial" size="4"><b>
Alta Clase de Nodo
</b></font>
</center></td>
</tr>
</tbody></table>

<p>

</p><table align="center" border="1" cols="2" width="40%">

<tbody><tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Nombre de cabeza de prueba
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
Vendor
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input name="provedor" size="20" value="" maxlength="20" type="text">
</font>
</td>
</tr>


</tbody></table><table align="center" border="0" cols="1" width="60%">
<tbody><tr>
</tr>

<tr>

<td><font color="RED" face="verdana" size="4"></font><center>
<font color="RED" face="verdana" size="4"><input name="AltaClaseNodo" onclick="javascript:verif_entradas(document.altaclasenodos)" value=" Alta Clase de Nodo " type="button"></font></center></td>



</tr>
</tbody></table>

</form>

</body></html>

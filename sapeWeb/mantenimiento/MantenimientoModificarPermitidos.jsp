<!-- MantenimientoModificarPermitidos.jsp -->

<jsp:useBean id="permitidos" class="com.osp.sape.maestros.Permitidos" scope="request" />

<html><head><title>Alta IP Permitidos - SAPE</title>

<script language="JavaScript">

  function direcciona() {
forma=this.document.altausuario;
forma.submit();
}


  function verif_entradas(forma) {

  if(forma.ip.value.length<1) {
      window.alert("IP NO VALIDO");
      forma.ip.focus();
      return;
   }

  if(forma.cliente.value.length<1) {
      window.alert("Nombre cliente NO VALIDO");
      forma.liente.focus();
      return;
   }

confirmacion=window.confirm("Estos son los datos del nuevo ip : \nIP="+forma.ip.value+"\nCliente="+forma.cliente.value);

if (confirmacion) Alta(forma.ip.value,forma.cliente.value);
}

function Alta(ip,cliente) {
	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=realizarModificacionPermitidos&ip_anterior=${permitidos.ip}&ip="+ip+"&cliente="+cliente;
}

</script></head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<jsp:include page="../encabezado.jsp" flush="true" />
<body topmargin="0" leftmargin="0" alink="olive" bgcolor="white" link="#330099">
<form name="altapermitidos">
<br>
<table align="center" bgcolor="BLACK" width="40%">
<tr>
<td>
<font color="white" face="Arial" size="4"></font><center><font color="white" face="Arial" size="4"><b>
Modificacion de IP Permitidos
</b></font>
</center></td>
</tr>
</table>

<p></p>

<table align="center" border="1" cols="2" width="40%">
<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
IP
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input class="texto" name="ip" size="20" value="${permitidos.ip}" maxlength="20" type="text">
</font>
</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Nombre Cliente
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input class="texto" name="cliente" size="20" value="${permitidos.cliente}" maxlength="20" type="text">
</font>
</td>
</tr>


</table>

<table align="center" border="0" cols="1" width="60%">
<tr>
</tr>

<tr>

<td><font color="RED" face="verdana" size="4"></font><center>
<font color="RED" face="verdana" size="4"><input class="boton" name="Alta" onclick="javascript:verif_entradas(document.altapermitidos)" value=" Modificar IP Permitido " type="button"></font></center></td>



</tr>
</table>

</form>

</body></html>

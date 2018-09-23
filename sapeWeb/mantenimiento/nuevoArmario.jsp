<!-- nuevoArmario.jsp -->

<html><head><title>Armario Nuevo - S A P E</title>
  
<script language="JavaScript">

    
  function verif_entradas(forma) {

  if(forma.armario.value.length<1) {
      window.alert("ARMARIO NO VALIDO");
      forma.armario.focus();
      return;
   }
   
  if(forma.distancia.value.length<1 || isNaN(forma.distancia.value)) {
      window.alert("Distancia de Armario incorrecta.");
      forma.distancia.focus();
      return;
   }

confirmacion=window.confirm("Estos son los datos del nuevo Armario:\narmario="+forma.armario.value+"\nDistancia="+forma.distancia.value);

if (confirmacion) Alta(forma.armario.value,forma.distancia.value);
}

function Alta(armario,distancia) {
	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=doAgregarArmario&armario="+armario+"&distancia="+distancia;
}

</script></head>
<jsp:include page="../encabezado.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body topmargin="0" leftmargin="0" alink="olive" bgcolor="white" link="#330099">
<form name="arm">
<br>
<table align="center" bgcolor="BLACK" width="40%">
	<tr>
		<td align="center">
			<font color="white" face="Arial" size="4"><b>
			Nuevo Armario</b></font>
		</td>
	</tr>
</table>

<table align="center" border="1" cols="2" width="40%">

<tr>
	<td align="center">
		<font color="BLACK" face="Verdana" size="3">Armario</font>
	</td>
	<td align="center">
		<font color="BLACK" face="Verdana" size="2">
			<input name="armario" size="20" value="" maxlength="20" type="text">
		</font>
	</td>
</tr>

<tr>
	<td align="center">
		<font color="BLACK" face="Verdana" size="3">Distancia</font>
	</td>
	<td align="center">
		<font color="BLACK" face="Verdana" size="2">
			<input name="distancia" size="20" value="" maxlength="8" type="text">
		</font>
	</td>
</tr>

</table>

<br><br>

<center>
<input name="Alta" onclick="javascript:verif_entradas(document.arm)" value="Agregar" type="button" class="boton"></center>
</form>

</body></html>

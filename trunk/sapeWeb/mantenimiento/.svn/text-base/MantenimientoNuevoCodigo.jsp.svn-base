							<!-- MantenimientoNuevoCodigo.jsp -->
<html>
<head><title>Alta CodigosVer - SAPE</title>
  
<script language="JavaScript">

    
  function verif_entradas(forma) {

  if(forma.codigover.value.length == 0) {
      window.alert("Codigo Ver No Valido");
      forma.codigover.focus();
      return;
   }
  
  if(forma.descripcion.value.length == 0) {
      window.alert("Descripcion No Valida");
      forma.descripcion.focus();
      return;
   }

	var comentario = forma.textArea.value;
	confirmacion=window.confirm("Estos son los datos \npara el nuevo codigo:\n\nCodigo Ver = "+forma.codigover.value+"\nDescripcion="+forma.descripcion.value+"\nComentario="+comentario);
	if (confirmacion) insertar(forma.codigover.value,forma.descripcion.value,forma.textArea.value);

}

function insertar(codigover,descripcion,comentario) {
location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=ejecutarAgregarCodigoVer&codigover="+codigover+"&descripcion="+descripcion+"&comentario="+comentario;
}

</script></head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body topmargin="0" leftmargin="0" alink="olive" bgcolor="white" link="#330099">
<form name="altacodigosver">
<br>
<table align="center" bgcolor="BLACK" width="80%">
<tbody><tr>
<td>
<font color="white" face="Arial" size="4"></font><center><font color="white" face="Arial" size="4"><b>
Alta Codigos Ver
</b></font>
</center></td>
</tr>
</tbody></table>

<br>

<table align="center" border="1" cols="2" width="80%">

<tbody><tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Codigo Ver
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input name="codigover" size="15" value="" type="text">
</font>
</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Descripcion
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input name="descripcion" size="50" value="" maxlength="100" type="text">
</font>
</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Comentario
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<textarea name="textArea" cols="58" rows="5"></textarea>
<!--input name="comentario" size="100" value="" maxlength="100" type="text"-->
</font>
</td>
</tr>

</tbody></table><table align="center" border="0" cols="1" width="80%">
<tbody><tr>
</tr>
<tr>
<td align="center">
	<input name="Alta" onclick="javascript:verif_entradas(document.altacodigosver)" value=" Alta CodigoVer " type="button">
</td>
</tr>
</tbody></table>

</form>

</body></html>

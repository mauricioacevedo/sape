							<!-- MantenimientoModificarCodigoVer.jsp -->
<jsp:useBean id="codigo" class="com.osp.sape.maestros.CodigoVer" scope="request" />
<html><head><title>Editar CodigosVer - SAPE</title>

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


confirmacion = window.confirm("Estos son los nuevos datos \npara el codigo:\n\nCodigo Ver = "+forma.codigover.value+"\nDescripcion="+forma.descripcion.value+"\nComentario="+forma.comentario.value);
if (confirmacion) modificar(forma.codigover.value,forma.descripcion.value,forma.comentario.value);

}

function modificar(codigover,descripcion,comentario) {
location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=realizarModificacionCodigoVer&codigoAnterior=${codigo.codigoVer}&codigover="+codigover+"&descripcion="+descripcion+"&comentario="+comentario;
}

</script></head>
<jsp:include page="../encabezado.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body topmargin="0" leftmargin="0" alink="olive" bgcolor="white" link="#330099">
<form name="altacodigosver">
<br>
<table align="center" bgcolor="BLACK" width="80%">
<tbody><tr>
<td>
<font color="white" face="Arial" size="4"></font><center><font color="white" face="Arial" size="4"><b>
Modificar Codigos Ver
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
<%--25-05-2006: Se coloca el campo codv disabled, debido a que es el identificador del 
	objeto en la tabla!!!!!! --%>
<input class="texto" name="codigover" size="20" value="${codigo.codigoVer}" maxlength="100" type="text" disabled="disabled">
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
<input class="texto" name="descripcion" size="50" value="${codigo.clasificacion}" type="text">
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
<input class="texto" name="comentario" size="100" value="${codigo.comentarios}" type="text" >
</font>
</td>
</tr>

</tbody></table><table align="center" border="0" cols="1" width="80%">
<tbody><tr>
</tr>

<tr>
<td align="center">
<input class="boton" name="Alta" onclick="javascript:verif_entradas(document.altacodigosver)" value=" Modificar CodigoVer " type="button"></td>

</tr>
</tbody></table>
</form>
</body></html>

					<!-- MatenimientoModificarSerie.jsp-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listaCodigosCentral" value="${requestScope.listaCodigosCentral}" />
<jsp:useBean id="serie" class="com.osp.sape.maestros.Serie" scope="request" />
<html>
<head>
<title>SAPE - Modificar Serie</title>
<script language="JavaScript">

function verif_entradasNodosPrueba(formaNodo) {

	var inicial = formaNodo.inicial.value;
  if(inicial.length == 0 || isNaN(inicial)) {
      window.alert("Serie Inicial No Valida");
      formaNodo.inicial.focus();
      return;
   }
 
	var sFinal = formaNodo.FINAL.value;
  if(sFinal.length == 0 || isNaN(sFinal)) {
      window.alert("Serie Final No Valida");
      formaNodo.FINAL.focus();
      return;
   }
   
   if (inicial > sFinal) {
  		alert("Rango de Series Invalido");
  		formaNodo.inicial.focus();
      	return;
   }

  if(formaNodo.central.value.length == 0) {
      window.alert("Central No Valida");
      formaNodo.central.focus();
      return;
   }

  if(formaNodo.tipoCentral.value.length == 0) {
      window.alert("Tipo de Central No Valido");
      formaNodo.tipocentral.focus();
      return;
   }

   if (formaNodo.cabezaid.options[formaNodo.cabezaid.selectedIndex].value==-1) {
    window.alert("SELECCIONE Id Cabeza");
    formaNodo.cabezaid.focus();
    return;
    }
	
	confirmacion=window.confirm("Estos son los nuevos datos \npara la Serie:\n\nSerie Inicial="+formaNodo.inicial.value+"\nSerie Final="+formaNodo.FINAL.value+"\nID Cabeza="+formaNodo.cabezaid.options[formaNodo.cabezaid.selectedIndex].value+"\nCentral="+formaNodo.central.value+"\nTipo Central="+formaNodo.tipoCentral.value+"\nCodigo de Central="+formaNodo.codCentral.value);

	if (confirmacion) modificar(formaNodo.inicial.value,formaNodo.FINAL.value,formaNodo.cabezaid.options[formaNodo.cabezaid.selectedIndex].value,formaNodo.central.value,formaNodo.tipoCentral.value,formaNodo.codCentral.value);

}

function modificar(inicial,FINAL,cabezaid,central,tipoCentral,codCentral) {
	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=realizarModificacionSerie&id=${serie.id}&inicial="+inicial+"&FINAL="+FINAL+"&cabezaid="+cabezaid+"&central="+central+"&tipocentral="+tipoCentral+"&codCentral="+codCentral;

}

</script>
<jsp:include page="../encabezado.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
</head>
<body bgcolor="white">
<form name="frmSerie">
<br>
<table align="center" bgcolor="BLACK" width="40%">
<tr>
<td>
<font color="white" face="Arial" size="4"></font><center><font color="white" face="Arial" size="4"><b>
Actualizar SERIE
</b></font>
</center></td>
</tr>
</table>

<p>

</p><table align="center" border="1" cols="2" width="40%">

<tbody><tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Serie Inicial
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input class="texto" name="inicial" size="20" value="${serie.serieInicial}" maxlength="20" type="text">
</font>
</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Serie Final
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input class="texto" name="FINAL" size="20" value="${serie.serieFinal}" maxlength="20" type="text">
</font>
</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Central
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input class="texto" name="central" size="20" value="${serie.central}" maxlength="20" type="text">
</font>
</td>
</tr>


<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Tipo Central
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input class="texto" name="tipoCentral" size="20" value="${serie.tipocentral}" maxlength="20" type="text">
</font>
</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
cabeza Id
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="3">
<select name="cabezaid">
<option value="-1">Seleccione</option>
<c:forEach items="${listaCodigosCentral}" var="cods">
	<option value="${cods.id}"${serie.cabezaId == cods.id ? " selected" : ""}>${cods.site}</option>
</c:forEach>
</select>
</font>
</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Codigo de La central
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input class="texto" name="codCentral" size="20" value="${serie.codCentral}" maxlength="20" type="text">
</font>
</td>
</tr>




</tbody></table><table align="center" border="0" cols="1" width="60%">
<tbody><tr>
</tr>

<tr>

<td><font color="RED" face="verdana" size="4"></font><center>
<font color="RED" face="verdana" size="4"><input class="boton" name="AltaNodo" onclick="javascript:verif_entradasNodosPrueba(document.frmSerie)" value="Modificar Serie" type="button"></font></center></td>



</tr>
</tbody></table>

</form>

</body></html>

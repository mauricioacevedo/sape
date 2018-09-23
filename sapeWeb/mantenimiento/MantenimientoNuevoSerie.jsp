					<!-- MantenimientoNuevoSerie.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listaCodigosCentral" value="${requestScope.listaCodigosCentral}" />
<html>
<head>
<title>SAPE - Nueva Serie</title>

<script language="JavaScript">

function verif_entradasSerieNueva(formaNodo) {

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

  if(formaNodo.central.value.length<1) {
      window.alert("Nombre Central NO VALIDO");
      formaNodo.central.focus();
      return;
   }

  if(formaNodo.tipoCentral.value.length<1) {
      window.alert("Nombre Tipo Central NO VALIDO");
      formaNodo.tipocentral.focus();
      return;
   }

   if (formaNodo.cabezaid.options[formaNodo.cabezaid.selectedIndex].value<0) {
    	window.alert("SELECCIONE Id Cabeza");
    	formaNodo.cabezaid.focus();
    	return;
    }
	
	if(formaNodo.codCentral.value.length<1) {
      window.alert("Codigo de Central NO VALIDO");
      formaNodo.codCentral.focus();
      return;
   }

confirmacion=window.confirm("Estos son los nuevos datos para la Serie:\n\nSerie Inicial="+formaNodo.inicial.value+"\nSerie Final="+formaNodo.FINAL.value+"\nID Cabeza="+formaNodo.cabezaid.options[formaNodo.cabezaid.selectedIndex].value+"\nCentral="+formaNodo.central.value+"\nTipo Central="+formaNodo.tipoCentral.value+"\nCodigo de Central="+formaNodo.codCentral.value);

if (confirmacion) insertar(formaNodo.inicial.value,formaNodo.FINAL.value,formaNodo.cabezaid.options[formaNodo.cabezaid.selectedIndex].value,formaNodo.central.value,formaNodo.tipoCentral.value,formaNodo.codCentral.value);


}

function insertar(inicial,FINAL,cabezaid,central,tipoCentral,codCentral) {
	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=ejecutarAgregarSerie&inicial="+inicial+"&FINAL="+FINAL+"&cabezaid="+cabezaid+"&central="+central+"&tipocentral="+tipoCentral+"&codCentral="+codCentral;
}

</script></head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body topmargin="0" leftmargin="0" alink="olive" bgcolor="white" link="#330099">
<form name="altanodos">
<br>
<table align="center" bgcolor="BLACK" width="40%">
<tbody><tr>
<td>
<font color="white" face="Arial" size="4"></font><center><font color="white" face="Arial" size="4"><b>
Ingresar nueva SERIE
</b></font>
</center></td>
</tr>
</tbody></table>

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
<input name="inicial" size="20" value="" maxlength="20" type="text">
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
<input name="FINAL" size="20" value="" maxlength="20" type="text">
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
<input name="central" size="20" value="" maxlength="20" type="text">
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
<input name="tipoCentral" size="20" value="" maxlength="20" type="text">
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
<option value="-1" selected>Seleccione</option>
<c:forEach items="${listaCodigosCentral}" var="cods">
	<option value="${cods.id}">${cods.site}</option>
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
<input name="codCentral" size="20" value="" maxlength="20" type="text">
</font>
</td>
</tr>




</tbody></table><table align="center" border="0" cols="1" width="60%">
<tbody><tr>
</tr>

<tr>
	<td align="center">
	<input name="AltaNodo" onclick="javascript:verif_entradasSerieNueva(document.altanodos)" value="Agregar Serie" type="button" class="boton">	
	</td>
</tr>
</tbody></table>

</form>

</body></html>

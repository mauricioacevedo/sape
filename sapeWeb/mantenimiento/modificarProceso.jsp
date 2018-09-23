<!-- modificarProceso.jsp -->


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
  <title>Modificar Proceso -  SAPE</title>
<script language="JavaScript">

function regresa() {
  if (navigator.appName == "Netscape")
      window.back();
        else
	    window.history.back();
}


	function direcciona() {
		forma=this.document.altausuario;
		forma.submit();
	}

    
  function verif_entradas(forma) {
    
	if(forma.nombre.value.length<1) {
      window.alert("Nombre de Proceso NO VALIDO");
      forma.nombre.focus();
      return;
   }
     
  if(forma.comando.value.length<1) {
      window.alert("Comando NO VALIDO");
      forma.comando.focus();
      return;
   }
   
   if(forma.expresion.value.length<1) {
      window.alert("La Expresion para verificar no es valida");
      forma.expresion.focus();
      return;
   }
   
	var estado = forma.activo.options[forma.activo.selectedIndex].value;
	var comand = forma.comando.value;

  if(forma.host.value.length<1) {
      window.alert("Host no valido");
      forma.host.focus();
      return;
   }

  confirmacion = window.confirm("Estos son los nuevos datos \npara el Proceso:\n\nNombre = "+forma.nombre.value +"\nComando = "+comand + "\nExpresion a Verificar = "+forma.expresion.value+"\nHost = "+forma.host.value+"\nEstado = "+forma.activo.options[forma.activo.selectedIndex].text);
  if (confirmacion) {
		comand = comand.replace(/&/g,'AMPERSANT');
		modificar(forma.nombre.value, comand,forma.expresion.value,forma.host.value,forma.activo.options[forma.activo.selectedIndex].value);
	}

 }

function modificar(nombre,comando,expresion,host,estado) {
	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=realizarModificacionProceso&id=${proceso.id}&nombre="+nombre+"&comando="+comando+"&expresion="+expresion+"&host="+host+"&activo="+estado;
}

</script>
  
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white" link="#330099" alink="olive" topmargin="0" leftmargin="0">
<form name="modifProceso">
<BR>

<BR>
<table align="center" width="70%" BGCOLOR="BLACK">
<tr>
	<td><font color="white" size="4" face="Arial"><CENTER><B>Modificar Proceso</font></td>
</tr>
</table>

<table align="center" cols="2" width="90%" border="1">

<tr>
<td width="40%">
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Nombre de Proceso
</FONT>
</td>
<td align="center">
<FONT face="Verdana" COLOR="BLACK" SIZE=2>
<INPUT class="texto" TYPE="text" NAME="nombre" size="25" VALUE="${proceso.nombre}">
</FONT>
</td>
</TR>

<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Comando
</FONT>
</td>
<td align="center">
<FONT face="Verdana" COLOR="BLACK" SIZE=2>
<input class="texto" type="text" name="comando" size="35" value="${proceso.comando}" >
</FONT>
</td>
</TR>

<tr>
	<td><font color="BLACK" face="Verdana" size="3">Expresion Para Verificar</font></td>
	<td align="center">
		<font color="BLACK" face="Verdana" size="2">
		<input class="texto" type="text" name="expresion" size="35" value="${proceso.expresion_verificar}">
		</font>
	</td>
</tr>

<tr>
	<td><font color="BLACK" face="Verdana" size="3">Host</font></td>
	<td align="center">
		<font color="BLACK" face="Verdana" size="2">
		<input class="texto" type="text" name="host" size="12" value="${proceso.host}">
		</font>
	</td>
</tr>


<!--tr>
	<td><font color="BLACK" face="Verdana" size="3">Archivo Log</font></td>
	<td>
		<font color="BLACK" face="Verdana" size="2">
		<input name="log" size="20" value="${proceso.logfile}">
		</font>
	</td>
</tr-->



<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Activo
</FONT>
</td>
<td align="center">
<FONT face="Verdana" COLOR="BLACK" SIZE=3>

<select name="activo">
	<option value="1"${proceso.activo == 1 ? " selected" : ""}>Activo</option>
	<option value="0"${proceso.activo == 0 ? " selected" : ""}>Inactivo</option>
</select>

</FONT>
</td>
</tr>

<p>

<table width="60%" border=0 cols="1" align=CENTER>
<tr>
</tr>

<tr>

<TD><FONT FACE="verdana" SIZE=4 COLOR=RED><CENTER>
<input type="hidden" name="MAX_FILE_SIZE" value="10000000">
<INPUT class="boton" name='modificar' ONCLICK='javascript:verif_entradas(document.modifProceso);' type='button' value='Modificar Proceso'></FONT></TD>

</TR>
</table>
</center>
</FORM>

</body>
</html>

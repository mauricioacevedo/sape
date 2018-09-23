<html>

<head>
<title>SAPE - Agregar Alarma</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
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

    
function agregarAl(forma) {	
    if(forma.nombreCola.value.length < 1 ) {
      window.alert("Cola NO VALIDA");
      forma.nombreCola.focus();
      return;
   }
   
  if(forma.limite.value.length<1) {
      window.alert("Especifique un valor maximo de desborde de cola");
      forma.limite.focus();
      return;
   }
 
   if(forma.avisar.value.length< 1 ) {
     window.alert("Destinatario no es valido");
     forma.avisar.focus(); 
     return;   
  	}
     
	if(forma.mensaje.value.length< 1 ) {
	   window.alert("Debe incluir algun mensaje.");
	   forma.mensaje.focus();  
	   return;     
	}
	  
  
   if (forma.medio.options[forma.medio.selectedIndex].value==0) {
    	window.alert("SELECCIONE un medio de envio.");
    	return;
    }
	
	
  if(forma.valorMedio.value.length<1) {
      window.alert("Debe especificar un correo o codigo de beeper.");
      forma.valorMedio.focus();
      return;
   }                                                                     

 
 	
  confirmacion = window.confirm("Esta es la configuracion\n de la nueva alarma:\n\nCola = "+forma.nombreCola.value +"\nLimite de desborde = "+forma.limite.value + "\nAvisar a = "+forma.avisar.value+"\nMensaje = "+forma.mensaje.value+"\nMedio de envio = "+forma.medio.options[forma.medio.selectedIndex].value+"\ncorreo, codigo o telefono Celular OLA = "+forma.valorMedio.value);
	if (confirmacion) {
		enviarAlarma(forma.nombreCola.value,forma.limite.value,forma.avisar.value,forma.mensaje.value,forma.medio.options[forma.medio.selectedIndex].value,forma.valorMedio.value);
	}
}
 
	function enviarAlarma(nombreCola,limite,avisar,mensaje,medio,valorMedio) {
		location.href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=ejecuteAgregarAlarma&nombreCola="+nombreCola+"&limite="+limite+"&avisar="+avisar+"&mensaje="+mensaje+"&medio="+medio+"&valorMedio="+valorMedio;
	}

 

</script>
  
</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body bgcolor="white" topmargin="0" leftmargin="0">
<form name="agregarAlarma">
<BR>
<table align="center" width="40%">
<tr>
<td class="mensajeCentral">
Nueva Alarma
</td>
</tr>
</table>

<table align="center" cols="2" width="40%" border="1">

<tr>
<td>
Cola
</td>
<td>
<INPUT TYPE="text" NAME="nombreCola" size="10" VALUE="${param.nombreCola}" MAXLENGTH="50" class="texto">
</td>
</TR>

<tr>
<td>
Limite de capacidad
</td>
<td>
<INPUT TYPE="text" NAME="limite" size="10" VALUE="" MAXLENGTH="10" class="texto">
</td>
</TR>

<tr>
<td>
Avisar a
</td>
<td>
<INPUT TYPE="text" NAME="avisar" size="20" VALUE="" MAXLENGTH="40" class="texto">
</td>
</tr>

<tr>
<td>
Mensaje
</td>
<td>
<INPUT TYPE="text" NAME="mensaje" size="20" VALUE="" MAXLENGTH="50" class="texto">
</td>
</TR>

<tr>
<td>
Medio de envio
</td>
<td>
<select name="medio" onchange="if(medio.value !=0)agregarAlarma.valorMedio.value=prompt('Ingrese el '+medio.value+' de la persona a contactar.');">
<option value="0">Seleccione</option>
<option value="correo">Correo electronico</option>
<option value="beeper">Beeper</option>
<option value="smsOla">Sms a telefono Ola</option>
</select>
</td>
</tr>

<tr>
<td>
Direcci&oacute;n de correo o 
c&oacute;digo de beeper
</td>
<td>
<INPUT TYPE="text" NAME="valorMedio" size="20" VALUE="" MAXLENGTH="50" class="texto">
</td>
</tr>
</table>
<br>
<center>
<input name="agregar" onclick="javascript:agregarAl(document.agregarAlarma)" type="button" value="Agregar alarma" class="boton">
<input name="cancelar" onclick="javascript:regresa();" type="button" value="cancelar" class="boton">
</center>
</FORM>

</body>
</html>

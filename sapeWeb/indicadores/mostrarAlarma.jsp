<!-- mostrarAlarma.jsp -->

<jsp:useBean id="alarma" class="java.lang.Object" scope="request" />
<jsp:useBean id="medio" class="java.lang.String" scope="request" />
<jsp:useBean id="valorMedio" class="java.lang.String" scope="request" />

<html>
<!-- warning: ojo con el bean alarma es de la clase: com.osp.sape.servicios.Alarma -->
<head>
<title>Alarma -  SAPE</title>
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


function modificarAl(forma) {
	
    if(forma.nombreCola.value.length< 1 ) {
      window.alert("Cola NO VALIDA");
      forma.nombreCola.focus();
      return;
   }
   
  if(forma.limite.value.length < 1) {
      window.alert("Especifique un valor maximo de desborde de cola");
      forma.limite.focus();
      return;
   }
 
   if(forma.avisar.value.length < 1 ) {
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

   


  confirmacion = window.confirm("Esta es la nueva configuracion\n de la  alarma:\n\nCola = "+forma.nombreCola.value +"\nLimite de desborde = "+forma.limite.value + "\nAvisar a = "+forma.avisar.value+"\nMensaje = "+forma.mensaje.value+"\nMedio de envio = "+forma.medio.options[forma.medio.selectedIndex].value+"\nCorreo, codigo de Beeper o telefono Celular OLA = "+forma.valorMedio.value);
	if (confirmacion) {
		enviarAlarma(forma.nombreCola.value,forma.limite.value,forma.avisar.value,forma.mensaje.value,forma.medio.options[forma.medio.selectedIndex].value,forma.valorMedio.value);
	}
}
 
	function enviarAlarma(nombreCola,limite,avisar,mensaje,medio,valorMedio) {
		location.href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=ejecuteModificarAlarma&nombreCola="+nombreCola+"&limite="+limite+"&avisar="+avisar+"&mensaje="+mensaje+"&medio="+medio+"&valorMedio="+valorMedio;
		
	}

</script>
  
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white" link="#330099" alink="olive" topmargin="0" leftmargin="0">
<form name="mostrarAlarma">
<BR>
<table align="center" width="40%" BGCOLOR="BLACK">
<tr>
<td>
<font color="white" size="4" face="Arial"><CENTER><B>
Alarma de ${alarma.nombreCola}
</font>
</td>
</tr>
</table>

<table align="center" cols="2" width="40%" border="1">

<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Cola
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=2>
<INPUT class="texto" disabled="true" TYPE="text" NAME="nombreCola" size="10" VALUE="${alarma.nombreCola}" MAXLENGTH="50"  >
</FONT>
</td>
</TR>

<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Limite de capacidad
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=2>
<INPUT class="texto" TYPE="text" NAME="limite" size="10" VALUE="${alarma.limite}" MAXLENGTH="10">
</FONT>
</td>
</TR>

<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Avisar a
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=2>
<INPUT class="texto" TYPE="text" NAME="avisar" size="20" VALUE="${alarma.avisar}" MAXLENGTH="40">
</FONT>
</td>
</tr>

<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Mensaje
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=2>
<INPUT class="texto" TYPE="text" NAME="mensaje" size="20" VALUE="${alarma.mensaje}" MAXLENGTH="50">
</FONT>
</td>
</TR>

<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Medio de envio
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
<!--<INPUT TYPE="text" NAME="medio" size="20" VALUE="${medio}" MAXLENGTH="50">-->
<select name="medio" onchange="if(medio.value !=0)mostarAlarma.valorMedio.value=prompt('Ingrese el '+medio.value+' de la persona a contactar.');">
<!--<option value="0">Seleccione</option>-->
<option value="correo""${medio == "correo" ? " selected" : ""}>Correo electronico</option>
<option value="beeper""${medio == "beeper" ? " selected" : ""}>Beeper</option>
<option value="smsOla""${medio == "smsOla" ? " selected" : ""}>Sms a telefono Ola</option>
</select>
</FONT>
</td>
</tr>

<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Direcci&oacute;n de correo o
c&oacute;digo de beeper</span>
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
<INPUT class="texto" TYPE="text" NAME="valorMedio" size="20" VALUE="${valorMedio}" MAXLENGTH="50">
</FONT>
</td>
</tr>
<!--
<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Recordar alarma en
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
<INPUT disabled="true" TYPE="text" NAME="recordar" size="10" VALUE="${alarma.recordar}" MAXLENGTH="50">
&nbsp;&nbsp;
minutos.
</FONT>
</td>
</tr>-->
</table>
<br>
<center>
<input class="boton" name="modificar" onclick="javascript:modificarAl(mostrarAlarma);" type="button" value="modificar">
<input class="boton" name="cancelar" onclick="javascript:window.close();" type="button" value="cerrar">
</center>
</FORM>

</body>
</html>

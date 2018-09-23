<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html><head><title>Nuevo Usuario - SAPE</title>
  
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

  if(forma.nick.value.length<1) {
      window.alert("Nombre nick NO VALIDO");
      forma.nick.focus();
      return;
   }
   
   if(forma.nick.value.length > 8) {
      window.alert("El nick debe ser de maximo de 8 caracteres.");
      forma.nick.focus();
      return;
   }
   
  if(forma.contrasena.value.length<1) {
      window.alert("Contrasena NO VALIDA");
      forma.contrasena.focus();
      return;
   }
   
  if(forma.contrasena2.value.length<1) {
      window.alert("Confirme su contrasena");
      forma.contrasena2.focus();
      return;
   }
   
   if(forma.contrasena.value != forma.contrasena2.value) {
        window.alert("Confirme su contrasena");
		forma.contrasena.value = '';
		forma.contrasena2.value = '';
		forma.contrasena.focus();
   		return;
   }
   

   if (forma.nivel.options[forma.nivel.selectedIndex].value==0) {
    window.alert("SELECCIONE nivel");
    forma.nivel.focus();
    return;
    }

  if(forma.nombre.value.length<1) {
      window.alert("Nombre nombre NO VALIDO");
      forma.nombre.focus();
      return;
   }

    if (forma.lenguaje.options[forma.lenguaje.selectedIndex].value==0) {
    window.alert("SELECCIONE lenguaje");
    forma.lenguaje.focus();
    return;
    }
	

   /* if (forma.grupo.options[forma.grupo.selectedIndex].value==0) {
    window.alert("SELECCIONE grupo");
    forma.grupo.focus();
    return;
    }*/

confirmacion=window.confirm("Esta es la informacion \ndel nuevo usuario:\nNick="+forma.nick.value+"\nContrasena="+forma.contrasena.value+"\nNivel="+forma.nivel.options[forma.nivel.selectedIndex].value+"\nLenguaje="+forma.lenguaje.options[forma.lenguaje.selectedIndex].value+"\nNombre="+forma.nombre.value+"\nContacto="+forma.contacto.value);

if (confirmacion) Alta(forma.nick.value,forma.contrasena.value,forma.nivel.options[forma.nivel.selectedIndex].value,forma.lenguaje.options[forma.lenguaje.selectedIndex].value,'2',forma.nombre.value,forma.contacto.value);
}

function Alta(nick,contrasena,nivel,lenguaje,grupo,nombre,contacto) {
	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=ejecutarAgregarUsuario&nick="+nick+"&contrasena="+contrasena+"&nivel="+nivel+"&lenguaje="+lenguaje+"&grupo="+grupo+"&nombre="+nombre+"&contacto="+contacto;
}

</script></head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body topmargin="0" leftmargin="0" alink="olive" link="#330099" bgcolor="white">
<form name="altausuario">
<br>
<table align="center" bgcolor="BLACK" width="40%">
<tbody><tr>
<td>
<font color="white" face="Arial" size="4"></font><center><font color="white" face="Arial" size="4"><b>
Alta de Usuario
</b></font>
</center></td>
</tr>
</tbody></table>

<p>

</p><table align="center" border="1" cols="2" width="40%">

<tbody><tr>
<td width="40%">
<font color="BLACK" face="Verdana" size="3">
Nombre de Usuario
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input name="nick" size="10" value="" maxlength="10" type="text">
</font>
</td>
</tr>

<tr>
	<td><font color="BLACK" face="Verdana" size="3">Password</font></td>
	<td><font color="BLACK" face="Verdana" size="2"><input name="contrasena" size="10" value="" maxlength="10" type="password"></font></td>
</tr>


<tr>
	<td><font color="BLACK" face="Verdana" size="3">Confirme su Password</font></td>
	<td>
		<font color="BLACK" face="Verdana" size="2">
		<input name="contrasena2" size="10" value="" maxlength="10" type="password">
		</font>
	</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Nivel
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="3">
<c:choose>
<c:when test="${sessionScope.usuario.nivel == '3'}">
<select name="nivel">
	<option value="0">Seleccione</option>
	<option value="1">Operador</option>
	<option value="2">Supervisor</option>
	<option value="3">Administrador</option>
</select>
</c:when>
<c:otherwise>
<select name="nivel">
	<option value="0">Seleccione</option>
	<option value="1">Operador</option>
</select>
</c:otherwise>
</c:choose>
</font>
</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Nombre del usuario
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input name="nombre" size="35" value="" maxlength="50" type="text">
</font>
</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Lenguaje
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="3">
<select name="lenguaje"><option value="0">Seleccione</option><option value="Ingles">Ingles</option><option value="Espanol">Espanol</option></select>
</font>
</td>
</tr>

<tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Contacto
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="2">
<input name="contacto" size="35" value="" maxlength="50" type="text">
</font>
</td>
</tr>
<!--tr>
<td>
<font color="BLACK" face="Verdana" size="3">
Grupo
</font>
</td>
<td>
<font color="BLACK" face="Verdana" size="3">
<select name="grupo"><option value="0">Seleccione</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option></select>
</font>
</td>
</tr-->


</tbody></table><table align="center" border="0" cols="1" width="60%">
<tbody><tr>
</tr>

<tr>

<td><font color="RED" face="verdana" size="4"></font><center>
<font color="RED" face="verdana" size="4"><input name="MAX_FILE_SIZE" value="10000000" type="hidden">
<input name="Alta" onclick="javascript:verif_entradas(document.altausuario)" value=" Alta Usuario " type="button"></font></center></td>



</tr>
</tbody></table>

</form>

</body></html>

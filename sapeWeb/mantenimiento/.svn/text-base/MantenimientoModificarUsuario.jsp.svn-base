<!-- MantenimientoModificarUsuario.jsp -->


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="com.osp.sape.maestros.UserSipe" scope="request" />
<html>

<head>
  <title>Modificar Usuario -  SAPE</title>
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
      window.alert("Nombre contrasena NO VALIDO");
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
   
   if (forma.activo.options[forma.activo.selectedIndex].value==0) {
    window.alert("SELECCIONE estado");
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
	
  if(forma.contacto.value.length<1) {
      window.alert("Ingrese CONTACTO.");
      forma.contacto.focus();
      return;
   }

   /*if (forma.grupo.options[forma.grupo.selectedIndex].value==0) {
    window.alert("SELECCIONE grupo");
    forma.grupo.focus();
    return;
    }*/
  confirmacion = window.confirm("Estos son los nuevos datos \npara el usuario:\n\nNick = "+forma.nick.value +"\nContrasena = "+forma.contrasena.value + "\nNivel = "+forma.nivel.options[forma.nivel.selectedIndex].value+"\nLenguaje = "+forma.lenguaje.options[forma.lenguaje.selectedIndex].value+"\nNombre = "+forma.nombre.value+"\nEstado = "+forma.activo.options[forma.activo.selectedIndex].value+"\nContacto="+forma.contacto.value);
  if (confirmacion) {
	modifica(forma.nick.value,forma.contrasena.value,forma.nivel.options[forma.nivel.selectedIndex].value,forma.lenguaje.options[forma.lenguaje.selectedIndex].value,'2',forma.nombre.value,forma.activo.options[forma.activo.selectedIndex].value,forma.contacto.value);
}
 
function modifica(nick,contrasena,nivel,lenguaje,grupo,nombre,estado,contacto) {
	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=realizarModificacionUsuario&id=${user.id}&nick="+nick+"&contrasena="+contrasena+"&nivel="+nivel+"&lenguaje="+lenguaje+"&grupo="+grupo+"&nombre="+nombre+"&estado="+estado+"&contacto="+contacto;
}


}
</script>

</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body link="#330099" alink="olive" topmargin="0" leftmargin="0" bgcolor="white">
<form name="modificarUsuario">
<BR>
<table align="center" width="40%" BGCOLOR="BLACK">
<tr>
<td>
<font color="white" size="4" face="Arial"><CENTER><B>
Modificar Usuario
</font>
</td>
</tr>
</table>

<table align="center" cols="2" width="40%" border="1">

<tr>
<td width="40%">
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Nombre de Usuario
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=2>
<INPUT class="texto" TYPE="text" NAME="nick" size="10" VALUE="${user.nick}" MAXLENGTH="10">
</FONT>
</td>
</TR>

<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Password
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=2>
<input class="texto" name="contrasena" size="10" value="" maxlength="10" type="password">
</FONT>
</td>
</TR>

<tr>
	<td><font color="BLACK" face="Verdana" size="3">Confirme su Password</font></td>
	<td>
		<font color="BLACK" face="Verdana" size="2">
		<input class="texto" name="contrasena2" size="10" value="" maxlength="10" type="password">
		</font>
	</td>
</tr>

<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Nivel
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
<c:choose>
<c:when test="${sessionScope.usuario.nivel == '3'}">
<select name="nivel">
	<option value="0">Seleccione</option>
	<option value="1"${user.nivel == 1 ? " selected" : ""}>Operador</option>
	<option value="2"${user.nivel == 2 ? " selected" : ""}>Supervisor</option>
	<option value="3"${user.nivel == 3 ? " selected" : ""}>Administrador</option>
</select>
</c:when>
<c:otherwise>
<select name="nivel">
<option value="${user.nivel}">${user.nivel == '1'?'Operador':'Supervisor'}</option>
</select>
</c:otherwise>
</c:choose>


</FONT>
</td>
</tr>

<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Nombre del usuario
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=2>
<INPUT class="texto" TYPE="text" NAME="nombre" size="35" VALUE="${user.nombre}" MAXLENGTH="50">
</FONT>
</td>
</TR>

<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Lenguaje
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
<select name="lenguaje">
<option value="0">Seleccione</option>
<option value="Ingles"${user.lenguaje == "Ingles" ? " selected" : ""}>Ingles</option>
<option value="Espanol"${user.lenguaje == "Espanol" ? " selected" : ""}>Espanol</option>

</select>
</FONT>
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
<input class="texto" name="contacto" size="35" value="${user.contacto}" maxlength="50" type="text">
</font>
</td>
</tr>

<!--tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Grupo
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
<select name="grupo">
<option value="0">Seleccione</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>

</select>
</FONT>
</td>
</tr-->


<tr>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
Estado
</FONT>
</td>
<td>
<FONT face="Verdana" COLOR="BLACK" SIZE=3>
<select name="activo">
<option value="0">Seleccione</option>
<option value="Activo"${user.activo == "S" ? " selected" : ""}>Activo</option>
<option value="Inactivo"${user.activo == "N" ? " selected" : ""}>Inactivo</option>
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
<INPUT class="boton" name='modificar' ONCLICK='javascript:verif_entradas(document.modificarUsuario)' type='button' value=' Modificar Usuario '></FONT></TD>

</TR>
</table>
</center>
</FORM>

</body>
</html>

					<!-- index.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="msg" class="java.lang.String" scope="request" />
<html>
<head>
<title>Sistema Administrativo de Pruebas Extendidas - SAPE</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="/sape/sape.css">
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">
<!--
	function validarUsuario() {
		var usuario = document.frmUsuario.login;
		var password = document.frmUsuario.password;
		//alert(usuario.value + " " + password.value);
		document.frmUsuario.submit();
	}
	
	function getKey(e) {
		if (window.event)
			return window.event.keyCode;
		else 
			return e.keyCode;

	}
	
	function validarInfo(e,id){
		var tecla = getKey(e);


		if(id == '1'){

			if(tecla == 13)
				document.frmUsuario.password.focus();

			return true;
		} else {
			if(tecla == 13 )
				validarUsuario();
		}

		return true;
	}

//-->
</script>
</head>
<body bgcolor="WHITE" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

<br><br><br>

<table width="663" align="center" border="0" cellpadding="0" cellspacing="0" >

<tr>
	<td>
		<img src="imagenes_sape/pantallaInicial/esquina_superior_izquierda.gif">
	</td>
	<td background="imagenes_sape/pantallaInicial/sombra_marco_superior.gif"></td>
	<td>
		<img src="imagenes_sape/pantallaInicial/esquina_superior_derecha.gif">
	</td>
</tr>

<tr>
<td background="imagenes_sape/pantallaInicial/sombra_marco_izquierda.gif"></td>
<td width="*">

<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
 	<tr>
    <td colspan="2" height="70"><img src="imagenes_sape/pantallaInicial/splashInicial.gif"></td>
  	</tr>
    <tr valign="middle">
    <td align="center" bgcolor="white" width="50%">
		<form name="frmUsuario" method="post" action="actionSape">
		<input type="hidden" name="accion" value="inicio">
      	<table align="center" border="0" cellpadding="0" cellspacing="0" width="50%">
        <tr align="center">
          <td width="54%">
			<br><strong>Usuario:</strong>
          </td>
          <td width="46%">
			<br>
      		<input class="texto" name="login" onkeypress="return validarInfo(event, '1');" maxlength="15" size="15" type="text">
          </td>
        </tr>
        <tr align="center">
          <td width="54%">
		  	<br>
    		<strong>Contrase&ntilde;a:</strong>
          </td>
          <td width="46%">
			<br>
              <input class="texto" name="password" onkeypress="return validarInfo(event, '2');" size="15" maxlength="15" type="password">

          </td>
        </tr>
      </table>
	  <br>
		<input class="boton" type="button" onClick="validarUsuario();" value="Ingresar">
	  </form>
    </td>
    <td align="center" bgcolor="white" width="50%">
      <p><font size="2"><b><font color="#000000" face="Arial"><i>

<script language="JavaScript">
<!--
function greeting() {
var today = new Date();
var hrs = today.getHours();
document.writeln("<CENTER>");
if ((hrs >=6) && (hrs <=18))
{
}
else
document.writeln("<BR>");
document.write("<H3>");
if (hrs < 6)
document.write("Buenos Dias");
else if (hrs < 12)
document.write("Buenos Dias");
else if (hrs <= 18)
document.write("Buenas Tardes");
else
document.write("Buenas Noches");
document.writeln("!</H3>");
dayStr = today.toLocaleString();
document.write(dayStr);
document.writeln("</CENTER>");
}
greeting();
//-->
</script>

<p></p>
    </td>
  </tr>
  <tr height="60" valign="top">
	<td align="center"><font size="+1">${msg}</font></td>
    <td align="center">
	    <a href="download/FirefoxGoogleToolbarSetup.exe" title="Bajar Mozilla Firefox para Windows"><img src="imagenes_sape/firefox-white.jpeg" width="50" height="50" border="0"></a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="download/jre-1_5_0_06-windows-i586-p.exe" title="Bajar Java Virtual Machine para Windows"><img src="imagenes_sape/java_logo.gif" width="50" height="50" border="0"></a>
    </td>
  </tr>
</table>


</td>


<td background="imagenes_sape/pantallaInicial/sombra_marco_derecha.gif"></td>

</tr>


<tr>
	<td>
	<img src="imagenes_sape/pantallaInicial/esquina_inferior_izquierda.gif" name="borde_inferior_izquierdo" border="0">
	</td>
	<td background="imagenes_sape/pantallaInicial/sombra_marco_inferior.gif"></td>
	<td>
	<img src="imagenes_sape/pantallaInicial/esquina_inferior_derecha.gif" name="borde_superior_izquierdo" border="0">
	</td>
</tr>


</table>

</body>

</html>

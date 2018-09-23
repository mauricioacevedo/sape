		<!-- AgregarCola.jsp -->
<html>
<head>
<title>SAPE - Agregar Cola</title>
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

    
function agregarCola(forma) {
	
    if(forma.nombreCola.value.length< 1 ) {
      window.alert("Nombre de Cola NO VALIDO.");
      forma.nombreCola.focus();
      return;
   }   

  confirmacion = window.confirm("Esta es la informacion\n de la nueva Cola:\n\nNombre = "+forma.nombreCola.value +"\nDescripcion = "+forma.descripcion.value);
	if (confirmacion) {
		enviarCola(forma.nombreCola.value,forma.descripcion.value);
	}
}
 
	function enviarCola(nombreCola,descripcion) {
		location.href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=ejecuteAgregarCola&nombreCola="+nombreCola+"&descripcion="+descripcion;
	}

</script>
  
</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body bgcolor="white" link="#330099" alink="olive" topmargin="0" leftmargin="0">
<form name="agregarCol">
<BR>
<table align="center" width="40%" BGCOLOR="BLACK">
<tr>
<td class="mensajeCentral">
Nueva Cola
</td>
</tr>
</table>

<table align="center" cols="2" width="40%" border="1">

<tr>
<td>
Nombre de Cola
</td>
<td>
<INPUT TYPE="text" NAME="nombreCola" size="20" VALUE="" MAXLENGTH="50" class="texto">
</td>
</TR>

<tr>
<td>
Descripcion de la cola
</td>
<td>
<INPUT TYPE="text" NAME="descripcion" size="30" VALUE="" MAXLENGTH="50" class="texto">
</td>
</TR>
</table>
<br>
<center>
<input name="agregar" onclick="javascript:agregarCola(document.agregarCol)" type="button" value="Agregar cola" class="boton">
<input name="cancelar" onclick="javascript:regresa();" type="button" value="cancelar" class="boton">
</center>
</FORM>
</body>
</html>

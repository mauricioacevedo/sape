<!-- importeMasivos.jsp -->

<html>
<head><title>SAPE - Importar Clientes Masivo</title></head>

<script language="JavaScript">

	function procesar(forma1) {
	
		if(forma1.textArea.value.length < 1 ){
			alert('Pegue informacion directamente de la plantilla de Excel.');
			return;
		}
		//var datos = forma1.textArea.value.replace(/\n/g,"ENTER");
		
		var datos ="";
		var dat = forma1.textArea.value.split('\n');
		
		for (i=0;i<dat.length;i++)
			datos += dat[i]+",";
		
		dat = datos.split('\t');
		datos = "";
		for (i=0;i<dat.length;i++)
			datos += dat[i]+"*";
		
		//alert(datos);
		alert("A continuacion se procesara la informacion ingresada.");
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=procesarInformacionImporteMasivos&data="+datos; 
	}

</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body topmargin="0" leftmargin="0" bgcolor="white">
<br>
<center><h2>Importe Masivo</h2></center>
<br>
<center>Ingrese la informacion directamente de la plantilla de Excel.</center>

<form name="forma">
<br><br>
<center><textarea name="textArea" cols="40" rows="20"></textarea></center>

</form>
<br>
<center>
<input name="Procesar" onclick="javascript:procesar(document.forma)" value="Procesar" type="button" class="boton">
&nbsp;&nbsp;
<input name="Regresar" onclick="javascript:window.back();" value="Regresar" type="button" class="boton">
</center>
</body>

</html>

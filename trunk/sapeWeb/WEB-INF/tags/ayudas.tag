<%@ attribute name="pagina" %>
<!-- INICIO TAG DE AYUDA  -->
<script languaje="Javascript">
	function mostrarAyuda(destino) {
		var ancho = 400;
		var left = screen.width - ancho - 10;
		var alto = screen.height - 120;
		//alert("pantalla: " + screen.width + " left: " + left + " alto: " + alto);
		var url = "${pageContext.request.contextPath}/ayudas/" + destino + ".html";
		window.open(url, 'ventanaAyuda','scrollbars=yes,resizable=yes,hotkeys=yes,height=' + alto + ',width=' + ancho + ',left=' + left + ',top=0,menubar=no,toolbar=no');
	}
</script>
<a href="javascript:mostrarAyuda('${pagina}');"><img src="imagenes_sape/ayuda.gif" border="0" whidth="18" height="15"></a>
<!--  FIN TAG DE AYUDA  -->

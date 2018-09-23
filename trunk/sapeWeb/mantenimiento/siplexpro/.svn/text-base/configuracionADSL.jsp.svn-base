					<!-- configuracionADSL.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:useBean id="listaCentrales" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="central" class="java.lang.String" scope="request"/>
<jsp:useBean id="site" class="java.lang.String" scope="request"/>
<jsp:useBean id="mostrarConfiguracion" class="java.lang.String" scope="request"/>
<jsp:useBean id="msg" class="java.lang.String" scope="request"/>
<html>
<head>
<title>SAPE - Configuracion ADSL</title>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">

	function updateCabeza() {
		var centrales = document.getElementById("listaCentrales");
		var nuevaCentral = centrales.options[centrales.selectedIndex].value;

		var funcion = recuperarListaCabezas;
		cargarHttpRequest("${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono&operacion=buscarSite&central=" + nuevaCentral, "GET", funcion);
		return true;
	}

	function recuperarListaCabezas() {
		if (req.readyState == 4) {
        	if (req.status == 200) {
				var respuesta = req.responseXML;

				//lleno el combo de los tipos de las cabezas
				var XMLTipoPruebas = req.responseXML.getElementsByTagName("cabezas")[0];

				var cabezas =  document.getElementById("cabezas");
				cabezas.options.length = 0;
				for (i = 0; i < XMLTipoPruebas.childNodes.length; i++) {
					var opcion = XMLTipoPruebas.childNodes[i].childNodes[0].nodeValue;
					var data = opcion.split(",");
					addOpcion(cabezas,data[0],data[1]);
				}

				for(i = 0;i<cabezas.length;i++){

					var option = cabezas.options[i];

					if(option.text == '${site}'){
						option.selected = "true";
						break;
					}
				}

        	}
    	}
	}


	function configurarCabeza(){
		var cabezas = document.getElementById("cabezas");

		var centrales = document.getElementById("listaCentrales");
		var nuevaCentral = centrales.options[centrales.selectedIndex].value;

		if(nuevaCentral != "todas"){
			if(cabezas.length <= 0){return};
		}

		var cabeza = "";
		if(nuevaCentral == "todas")
			cabeza = nuevaCentral.toUpperCase();
		else
			cabeza = cabezas.options[cabezas.selectedIndex].value;

		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=configurarCabeza&id="+cabeza+"&central="+nuevaCentral;
		return;
	}
</script>
<jsp:include page="../../encabezado.jsp" flush="true" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white">
<br>
<center><font color="red">${msg}</font></center>
<br>
<table align="center" width="60%" >
<tr bgcolor="black">
	<td colspan="5" align="center"><font color="white" size="+1"><b> Configuracion ADSL </b></td>
</tr>
</table>

<table width="60%" align="center">
<tr>
<td width="150" align="center">Seleccione una cabeza :</td>
<td width="300" align="center">
	<select name="listaCentrales" id="listaCentrales" onChange="javascript:updateCabeza();">
	<c:forEach items="${listaCentrales}" var="cen">
	<option value="${cen}" ${cen == central ?'selected':''}>${cen}</option>
	</c:forEach>
	</select>
&nbsp;&nbsp;
<select name="cabezas" id="cabezas"></select>
</td>
<td><input class="boton" type="button" name="go" value="Aceptar" onClick="javascript:configurarCabeza();"></td>
</tr>
</table>


<c:if test="${not empty mostrarConfiguracion}">
<jsp:include page="contenidoConfiguracionADSL.jsp" flush="true" />
</c:if>

</body>
<script language="JavaScript">updateCabeza();</script>
</html>

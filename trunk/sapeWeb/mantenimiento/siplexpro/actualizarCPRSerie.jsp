					<!-- actualizarCPRSerie.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="listaCentrales" class="java.util.ArrayList" scope="request"/>
<html>
<title>Actualizar CPR Series - SAPE </title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">

	function updateSeries(){

		var selec = document.getElementById("selectCentrales");

		var central = selec.options[selec.selectedIndex].value;

		//alert('buscar la central : '+central);

		if(central == "-1" || central == -1){
			return;
		}

		var telIni = document.getElementById("telIni");
		var telFin = document.getElementById("telFin");

		telIni.value = "";
		telFin.value = "";

		var funcion = recibirSeries;	cargarHttpRequest("${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=actualizarCPRSerie&central="+central,"GET",funcion);
	}

	function recibirSeries(){

    if (req.readyState == 4) {
        if (req.status == 200) {

			var selectSeries = document.getElementById("selectSeries");

				//lleno el combo de los tipos de pruebas.
			var XMLTipoPruebas = req.responseXML.getElementsByTagName("pruebas")[0];
			selectSeries.options.length = 0;
			addOpcion(selectSeries,"-1","Seleccione: ");

			for (i = 0; i < XMLTipoPruebas.childNodes.length; i++) {
				var opcion = XMLTipoPruebas.childNodes[i];
				var opcion2 = opcion.childNodes[0];
				var opcion3 = opcion.childNodes[1];

				//alert(opcion2.childNodes[0].nodeValue+"--"+opcion3.childNodes[0].nodeValue);

				addOpcion(selectSeries,opcion3.childNodes[0].nodeValue,opcion2.childNodes[0].nodeValue);
			}
        }
    }

}

	function seleccionarSerie(){

		var selec = document.getElementById("selectSeries");
		var telIni = document.getElementById("telIni");
		var telFin = document.getElementById("telFin");

		var valor = selec.options[selec.selectedIndex].value;
		var label = selec.options[selec.selectedIndex].text;

		var data = label.split(" - ");


		telIni.value = data[0];
		telFin.value = data[1];

	}

	function enviarSerie(){

		var selec = document.getElementById("selectSeries");
		var chk = document.getElementById("chk");
		var id = selec.options[selec.selectedIndex].value;

		if(id == -1){
			alert("Seleccione una serie!!");
			return;
		}



		location.href = "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=guardarSeriePrueba&id="+id+"&enabled="+chk.checked;
	}

</script>
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
<jsp:include page="../../encabezado.jsp" flush="true" />
<br>
<br>

<table align="center" width="60%" >
<tr bgcolor="black">
	<td colspan="5" align="center"><font color="white" size="+1"><b> Actualizar CPR Por Serie </b><tags:ayudas pagina="Actualizar"/></font></td>
</tr>
<tr><td height="20"> </td></tr>
<tr>
	<td colspan="5" align="center">
		1. Seleccione una central
		<select id="selectCentrales" onChange="javascript:updateSeries();">
			<option value="-1">Seleccione: </option>
			<c:forEach items="${listaCentrales}" var="central">
			<option value="${central}"> ${central} </option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr><td height="15"> </td></tr>
<tr>
	<td colspan="5" align="center">
		2. Seleccione una Serie <select id="selectSeries" onChange="javascript:seleccionarSerie();">
			<option value="-1">Seleccione: </option>
		</select>
	</td>
</tr>
<tr><td height="15"> </td></tr>
<tr>
	<td colspan="5" align="center">
		Telefono Inicial <input id="telIni" type="text" disabled="" value="" size="7" class="texto"> Telefono Final <input  id="telFin" type="text" disabled="" value="" size="7" class="texto">
	</td>
</tr>
</table>
<br>
<center><input id="chk" type="checkbox" name="chk"> Habilitado</center>


<br>
<center><input type="button" value="aceptar" onClick="javascript:enviarSerie();" class="boton"></center>
<br>
<br>
<div align="center"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoSiplexPRO">Regresar</a></div>
</body>
</html>



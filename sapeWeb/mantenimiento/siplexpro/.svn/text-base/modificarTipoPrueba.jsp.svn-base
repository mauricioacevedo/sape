<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% java.util.List listaTipoPrueba = (java.util.List) request.getAttribute("listaTipoPrueba"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modificar Tipo de Prueba - SAPE</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">

	function updateDesc(){

		var selec = document.getElementById("selectTipos");

		var prueba = selec.options[selec.selectedIndex].value;

		var inp = document.getElementById("btnDesc");

		if(prueba == -1 || prueba == "-1"){
			inp.value = "";
			return;
		}
		
		var idTipoPrueba = document.getElementById("idTipoPrueba");
		var prob = prueba.split("*");
		
		idTipoPrueba.value = prob[0];
		
		inp.value = prob[1];
	}
	
	function modificarDesc(){
	
		var selec = document.getElementById("selectTipos");

		var prueba = selec.options[selec.selectedIndex].text;
		var descAntigua = selec.options[selec.selectedIndex].value;
		
		if(descAntigua == -1 || descAntigua == "-1"){
			return;
		}
		
		var inp = document.getElementById("btnDesc");
		
		var nuevaDesc = inp.value;
		
		if(nuevaDesc == ""){
			alert("Ingrese una descripcion valida.");
			return;
		}
		var idTipoPrueba = document.getElementById("idTipoPrueba");
		
		if(idTipoPrueba == ""){
			alert("No se puede hacer la modificacion.Faltan parametros. ");
			return;
		}
						
		location.href = "${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=doModificarDescTipoPrueba&id="+idTipoPrueba.value+"&nuevaDesc="+nuevaDesc;
		
	}

</script>
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<jsp:include page="../../encabezado.jsp" flush="true" />
<br>
<br>

<table align="center" width="60%" >
<tr bgcolor="black">
	<td colspan="5" align="center"><font color="white" size="+1"><b> Modificar Descripcion de Pruebas </b></font></td>
</tr>
<tr><td height="20" colspan="2"> </td></tr>
<tr>
	<td align="center">
		Seleccione un tipo de prueba:
		<select id="selectTipos" onChange="javascript:updateDesc();">
			<option value="-1">Seleccione: </option>
			<c:forEach items="${listaTipoPrueba}" var="prueba">
			<option value="${prueba.id}*${prueba.descripcion}"> ${prueba.tipo}</option>
			</c:forEach>
		</select>
		<input type="hidden" id="idTipoPrueba">
	</td>
	<td align="center"><input size="25" maxlength="22" type="text" id="btnDesc" value="" class="texto"></td>
</tr>
<tr><td height="20" colspan="2"> </td></tr>
<tr>
<td colspan="2" align="center"><input type="button" name="modificar" value="modificar" onclick="javascript:modificarDesc();" class="boton"></td>
</tr>
</table>

</body>
</html>
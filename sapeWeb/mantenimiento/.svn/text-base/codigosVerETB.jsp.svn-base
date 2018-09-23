					<!--  codigosVerETB.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listaCodvSAPE" value="${requestScope.listaCodvSAPE}" />
<c:set var="listaCodvETB" value="${requestScope.listaCodvETB}" />
<c:set var="msg" value="${requestScope.msg}" />

<html><head></head>
<a name="arriba"></a>
<jsp:include page="../encabezado.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white">

<script language="JavaScript">
<!--
	function informeGen(format){

		var val = format.formatos.options[format.formatos.selectedIndex].value;
		document.location = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=exportarInforme&pantalla=codigosVer&formato="+val;

}

 	function eliminarCodigoVer(codvSAPE,codvETB) {
 	 	if(confirm("A continuacion se eliminara el Codigo, SAPE = \""+codvSAPE+"\" - - ETB = \""+codvETB+"\". \nEsta seguro? "))
     		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=eliminarCodvETB&codvSAPE="+codvSAPE;
}

	function almacenarCodigo(){
		
		var comboSAPE = document.getElementById("codvSAPE");
		var codvSAPE = comboSAPE.options[comboSAPE.selectedIndex].text;
		
		var input = document.getElementById("codvETB");
		var codvETB = input.value;
		
		if(codvETB == '' || isNaN(codvETB)){
			alert('Ingrese un dato valido para el campo de Codigo ETB.');
			input.focus();
			return;
		}
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarCodigoETB&codvETB="+codvETB+"&codvSAPE="+codvSAPE;
	}
	
	function updateCampos(){
		var comboSAPE = document.getElementById("codvSAPE");
		var input = document.getElementById("codvETB");
		var codvSAPE = comboSAPE.options[comboSAPE.selectedIndex].value;
		
		input.value=codvSAPE;
	}
	
	function modificar(codvSAPE,codvETB){
		var comboSAPE = document.getElementById("codvSAPE");
		var input = document.getElementById("codvETB");
		var i=0;
		for(i=0;i<comboSAPE.length;i++){
			if(comboSAPE.options[i].text == codvSAPE){
				comboSAPE.options[i].selected='true';
				updateCampos();
				input.focus();
				location.href="#arriba";
				return;
			}
		}
		//var codvSAPE = comboSAPE.options[comboSAPE.selectedIndex].value;
		
	}

-->
</script>
<!--style type="text/css">

 td { color: black; font-family: "Arial, Helvetica, sans-serif"; font-size: 10pt }

</style-->


<!-- div align="center"><a name="agregarCodigoVer" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarCodigoVer">Agregar nuevo Codigo</a></div-->
<br>
<br>

<center><font color="red" >${msg}</font></center>

<table align="center" width="60%">

<tr bgcolor="black">
<td colspan="4" align="center" class="mensajeCentral">Agregar Relacion de Codigo Ver</td>
</tr>
<tr><td height="15" colspan="4"></td></tr>
<tr>
<td>Codigo Ver de sape:
<select name="codvSAPE" id="codvSAPE" onchange="javascript:updateCampos();" class="texto">
	<option value="-1">Seleccione</option>
	<c:forEach items="${listaCodvSAPE}" var="codvSape"><option value="">${codvSape.codigoVer}</option></c:forEach>
	<c:forEach items="${listaCodvETB}" var="codvEtb"><option value="${codvEtb.codvETB}">${codvEtb.codvSAPE}</option></c:forEach>
</select></td>
<td>Codigo Ver ETB: <input type="text" name="codvETB" id="codvETB" size="10" class="texto"></td>
<td align="center"><input type="button" value="Guardar" onclick="javascript:almacenarCodigo()" class="boton"></td>

</tr>

<tr><td height="15" colspan="4"></td></tr>
<!-- tr>
	<td align="right" bgcolor="white" colspan="6">
		<form name="informes">
		<font color="Black">Exportar a &nbsp;</font>
		<select name="formatos">
			<option value="pdf">PDF</option>
			<option value="csv">CSV</option>
			<option value="xls">XLS</option>
		</select>

		<input class="boton" name='informe' ONCLICK='javascript:informeGen(document.informes);' type='button' value='Aceptar'>
		</form>
	</td>
</tr-->

<tr bgcolor="Black">
	<td align="center" class="mensajeCentral" colspan="4"> Codigos ETB <%--tags:ayudas pagina="CodigoVer"/--%></td>
</tr>

<tr bgcolor="black">
	<td align="center" width="35%" class="header-reporte">Codigo Ver SAPE</td>
	<td align="center" width="30%" class="header-reporte">Codigo ETB</td>
	<!-- td align="center" width="40%" class="header-reporte">Comentario</td-->
	<td align="center" width="25%" class="header-reporte">Opcion</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaCodvETB}" var="codvetb">
	<c:set var="row" value="${i%2 == 0? 0: 1}"/>
	<tr class="row${row}">
		<td align="center" width="10%">&nbsp;${codvetb.codvSAPE}</td>
		<td align="center" width="35%">&nbsp;${codvetb.codvETB}</td>
		<%-- td align="center" width="40%">&nbsp;${codvetb.comentarios}</td--%>
		<td align="center" width="15%"><a name="modificarCodigoVer" href="javascript:modificar('${codvetb.codvSAPE}','${codvetb.codvETB}')">Modificar</a> - <a name="eliminarCodigoVer" href=javascript:eliminarCodigoVer("${codvetb.codvSAPE}","${codvetb.codvETB}");>Eliminar</a></font></td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>

</table>

</body></html>

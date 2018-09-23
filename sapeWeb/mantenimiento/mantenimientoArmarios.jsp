<!--mantenimientoArmarios.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% java.util.List listaEventos = (java.util.List) request.getAttribute("listaEventos"); %>
<jsp:useBean id="msgCentral" class="java.lang.String" scope="request"/>

<jsp:useBean id="offset" class="java.lang.String" scope="request"/>
<jsp:useBean id="regPorPagina" class="java.lang.String" scope="request"/>
<jsp:useBean id="pagActual" class="java.lang.String" scope="request"/>
<jsp:useBean id="totalPaginas" class="java.lang.String" scope="request"/>
<jsp:useBean id="orderBy" class="java.lang.String" scope="request"/>
<jsp:useBean id="cantidadTotalRegistros" class="java.lang.String" scope="request"/>
<jsp:useBean id="query" class="java.lang.String" scope="request"/>

<jsp:include page="../encabezado.jsp" flush="true" />

<html>
	<head>

	<script language="JavaScript">

	function irAPagina(pagina, cantidadRegistros, order){

	if(isNaN(cantidadRegistros)){
		alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
		document.formaPaginacion.regXPag.focus();
		return;
	}

		var query = '${query}';

		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoArmarios&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;

	}

	function eliminarArmario(){
		var armario = document.getElementById("armarioEliminar").value;
		
		if(armario == ''  || isNaN(armario)){
			alert("Ingrese un armario correcto, solo numeros.");
			return;
		}
		if(confirm("Esta seguro que desea eliminar el armario "+armario+" ?")){	
			location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=eliminarArmario&armario="+armario;
		}
	}

	function buscarArmario(forma) {

		var armario = forma.armarioBuscar.value;

		location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoBuscarArmario&armario="+armario;

		return false;
	}


	</script>

	</head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">

	<body bgcolor="white">
	<br>
	
	<center><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarArmario">Agregar Nuevo Armario</a></center>
	
	<br>

		<table width="55%" align="center">

			<tr><td>
			<form name="formaArmario" onSubmit="return buscarArmario(document.formaArmario);">
				<b>Buscar armario:</b> <input class="texto" type="text" name="armarioBuscar" size="7">&nbsp;
				<input type="submit" name="ir" value="ir" class="boton">
			</form>
			</td>
			<td align="center">
			<form name="formaArmarioEliminar">
			<b>Eliminar armario:</b> <input class="texto" type="text" name="armarioEliminar" id="armarioEliminar" size="7">&nbsp;
				<input type="button" name="borrar" value="Borrar" class="boton" onclick="javascript:eliminarArmario();"></td>
			</form>
			</tr>

			<tr bgcolor="black">
				<td align="center" class="mensajeCentral"  height="25" colspan="2">Mantenimiento Armarios <tags:ayudas pagina="Armario"/></td>
			</tr>

			<tr>
				<td colspan="2">
					<jsp:include page="../reportes/paginacion.jsp" flush="true" />
				</td>
			</tr>

			<tr align="center" bgcolor="black">
				<td class="header-reporte">Armario</td>
				<td class="header-reporte">Distancia</td>
			</tr>

			<c:forEach items="${listaEventos}" var="detalle">
			<c:set var="row" value="${i%2 == 0? 0: 1}"/>
				<tr align="center" class="row${row}">
					<td>${detalle.armario}</td>
					<td width="50%">${detalle.distancia} m.</td>
				</tr>
				<c:set var="i" value="${i +1}"/>
			</c:forEach>
		</table>
	</body>
</html>

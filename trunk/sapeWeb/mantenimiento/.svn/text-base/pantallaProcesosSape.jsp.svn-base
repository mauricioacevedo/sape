<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% java.util.List listaProcesos = (java.util.List) request.getAttribute("listaProcesos"); %>
<jsp:useBean id="msg" class="java.lang.String" scope="request"/>
<jsp:useBean id="orderBy" class="java.lang.String" scope="request"/>
<jsp:useBean id="filtro" class="java.lang.String" scope="request"/>
<jsp:useBean id="valorFiltro" class="java.lang.String" scope="request"/>
<jsp:useBean id="query" class="java.lang.String" scope="request"/>
<!-- pantallaProcesosSape.jsp -->

<html>
	<head><jsp:include page="../encabezado.jsp" flush="true" /></head>
	<style type="text/css">
		td { color: black; font-family: "Arial, Helvetica, sans-serif"; font-size: 10pt }
		.header-reporte a:link, a:visited { font-weight: bold; font-size: 12; color: black }
		.mensajeCentral { font-weight: bold; font-size: 14; text-decoration: none; color: white}

		.row0 {background-color: #e7c366;}
		.row1 {background-color: #e7d29e;}
		
		.arrancar {background: royalBlue;}
		.parar {background: orangeRed;}
		.restart {background: gold;}
		.verificar {background: #e7c366;}
		.disabled {background: #e7d29e;}

		.color_192-168-0-128 {background-color: #c1c1c1;}
 		.color_192-168-0-105 {background-color: #c1a788;}
 		.color_192-168-0-129 {background-color: #b9c185;}
 		.color_192-168-0-124 {background-color: #84c18b;}
 		.color_192-168-0-125 {background-color: #818ac1;}

	</style>



	<script language="JavaScript">

		function enviarComando(id,accion){
			location.href = "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=ejecutarAccionProcesosSape&id="+id+"&query="+accion+"&filtro=${param.filtro}&valorFiltro=${param.valorFiltro}";
			return;
		}

		function filtrar(orderBy) {

			var query = '${query}';
			location.href = "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoProcesos&orderBy="+orderBy+query;
			return;
		}

		function buscar(forma) {

			var orderBy = '${orderBy}';
			var filtro = forma.filtrar.options[forma.filtrar.selectedIndex].value;
			var valorFiltro = forma.txtBuscar.value;

			location.href = "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoProcesos&orderBy="+orderBy+"&filtro="+filtro+"&valorFiltro="+valorFiltro;
			return;
			
		}
		
		function modificarProceso(idProceso){
		
			var wind=window.open("${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=modificarProceso&idProceso="+idProceso, 'window900','scrollbars=yes,resizable=yes,hotkeys=yes,height=370,width=500,left=100,top=50,menubar=yes,toolbar=no');
			wind.focus();

		}

	</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
	<body text="#ffffff" bgcolor="white">


		<br>
		<center><font color="red" size="+1">${msg}</font></center>
		<br>
		<table align="center"width="90%">
			<tr bgcolor="black">
				<td align="center" colspan="8">
					<font color="WHITE" class="mensajeCentral">	Procesos Sape<tags:ayudas pagina="Procesosautomaticos"/> </font>
				</td>
			</tr>
		</table>

	<form name="forma1" action="javascript:buscar(document.forma1);">
		<table width="90%" cellspacing=0 cellpadding=0 border=0 align="center">
			<tr>
				<td align="center" class="header-filtro">
					BUSCAR POR:
				</td>
				<td align="center" ></td>
				<td align="center" ></td>
			</tr>
			<tr>
				<td align="center" >
					<select name="filtrar">
						<option value="ninguno" ${empty filtro ?"selected":""}>Todos</option>
						<option value="nombre" ${filtro == 'nombre'?"selected":""}>Nombre</option>
						<option value="host"  ${filtro == 'host'?"selected":""}>Host</option>
					</select>
				<td align="center" ><input class="texto" type="text" name="txtBuscar" size="10" value="${valorFiltro}"></td>
				<td align="center" ><input class="boton" type="button" name="find" onClick="buscar(document.forma1)" value="Aceptar"></td>
			</tr>
		</table>
	</form>

		<table align="center"width="90%">
			<tr bgcolor="black">
				<td align="center" width="5%" class="header-reporte">
					<c:choose>
						<c:when test="${orderBy == 'id DESC'}">
							<a href="javascript:filtrar('id ASC');">ID</a>

						</c:when>
						<c:otherwise>
							<a href="javascript:filtrar('id DESC');">ID</a>
						</c:otherwise>
					</c:choose>
				</td>
				<td align="center" width="15%" class="header-reporte">
					<c:choose>
						<c:when test="${orderBy == 'nombre DESC'}">
							<a href="javascript:filtrar('nombre ASC');">Nombre</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:filtrar('nombre DESC');">Nombre</a>
						</c:otherwise>
					</c:choose>

				</td>
				<td align="center" width="5%" class="header-reporte">
					<c:choose>
						<c:when test="${orderBy == 'host DESC'}">
							<a href="javascript:filtrar('host ASC');">Host</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:filtrar('host DESC');">Host</a>
						</c:otherwise>
					</c:choose>
				</td>
				<td align="center" width="5%" class="header-reporte">
					<c:choose>
						<c:when test="${orderBy == 'activo DESC'}">
							<a href="javascript:filtrar('activo ASC');">Status</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:filtrar('activo DESC');">Status</a>
						</c:otherwise>
					</c:choose>
				</td>
				<td align="center" width="15%" class="barraTitulos">Acciones</td>
			</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaProcesos}" var="proc">

	<c:set var="row" value="${ i % 2 == 0? 0 : 1}"/>
			<tr class="row${row}">
				<td align="center" width="5%">${proc.id}</td>
				<td align="center" width="20%"><a href="javascript:modificarProceso('${proc.id}');">${proc.nombre}</a></td>
				<td align="center" width="10%" class="color_${fn:replace(proc.host, '.', '-')}">${proc.host}</td>
				
				
				<c:choose>
					<c:when test="${proc.activo == '1'}">
					<td align="center" width="10%">
						Activo
					</td>
					<td align="center" width="25%">
						<input class="disabled" type="button" value="Arrancar" disabled="">
						&nbsp;<input class="parar" type="button" value="Parar" onClick="javascript:enviarComando('${proc.id}','stop');" ${proc.nombre == "Servidor Tomcat"?"disabled":""}>
						&nbsp;<input class="restart" type="button" value="Reiniciar" onClick="javascript:enviarComando('${proc.id}','restart');">						
						&nbsp;<input class="verificar" type="button" value="Verificar" onClick="javascript:enviarComando('${proc.id}','status');">
					</td>
					</c:when>
					<c:otherwise>
					
					<td align="center" width="10%">
						Inactivo
					</td>
					<td align="center" width="25%">
						<input class="arrancar" type="button" value="Arrancar" onClick="javascript:enviarComando('${proc.id}','start');">
						&nbsp;<input class="disabled" type="button" value="Parar" disabled="">
						&nbsp;<input class="disabled" type="button" value="Reiniciar" disabled="">
						&nbsp;<input class="verificar" type="button" value="Verificar" onClick="javascript:enviarComando('${proc.id}','status');">
					</td>
					</c:otherwise>
				</c:choose>
			</tr>
	<c:set var="i" value="${i + 1}"/>
</c:forEach>
</table>
	</body>
</html>

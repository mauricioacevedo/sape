<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_ES" scope="session" />
<% java.util.List listaUsuarios = (java.util.List) request.getAttribute("listaUsuarios"); %>
<jsp:useBean id="pagActual" class="java.lang.String" scope="request"/>
<jsp:useBean id="orderBy" class="java.lang.String" scope="request"/>
<jsp:useBean id="cantidadTotalRegistros" class="java.lang.String" scope="request"/>
<jsp:useBean id="query" class="java.lang.String" scope="request"/>

<!-- MantenimientoUsuarios.jsp -->
<html><head>
<jsp:include page="../encabezado.jsp" flush="true" />
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white">

<script language="JavaScript">
<!--
function eliminarUsuario(id) {
  if(confirm("A continuacion se eliminara el usuario con id: \"" + id+"\". \nEsta seguro? "))
     location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=eliminarUsuario&id="+id;
}

function informeGen(format){
	var val = format.formatos.options[format.formatos.selectedIndex].value;
	
	document.location = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=exportarInforme&pantalla=usuariosSape&formato="+val;
	
}

function modificarUser(level,id){
	
	var actLevel = ${sessionScope.usuario.nivel};
	if(actLevel <= level){
		alert('Imposible modificar este usuario.\nComuniquese con el administrador del sistema SAPE.');
		return;
	}
	
	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=modificarUsuario&id="+id;
}

function irAPagina(pagina, cantidadRegistros, order){

	if(isNaN(cantidadRegistros)){
		alert('Ingrese una cantidad numerica \npara el numero de registros por pagina.');
		document.formaPaginacion.regXPag.focus();
		return;
	}

	var query = '${query}';

	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoUsuarios&orderBy="+order+"&pagActual="+pagina+"&regPorPagina="+cantidadRegistros+query;
}

function buscarRegistros(orderBy,regXPag,pagActual){
	
	//TENER CUIDADO CON ESTA FORMA DE OBTENER VALORES DE UN COMBO
	var opcion = document.getElementById('selectOpciones').value;
	var valorOpcion = document.getElementById('valor').value;
	
	if(opcion == 'nivel'){
		if(valorOpcion == 'Administrador')
			valorOpcion = 3;
		else if(valorOpcion == 'Supervisor')
			valorOpcion = 2;
		else if(valorOpcion == 'Operador')
			valorOpcion = 1;
	}
	
	location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoUsuarios&opcion="+opcion+"&valorOpcion="+valorOpcion+"&orderBy="+orderBy+"&regPorPagina="+regXPag+"&pagActual="+pagActual;
} 


//-->
</script>

<style type="text/css">
  
  td { color: black; font-family: "Arial, Helvetica, sans-serif"; font-size: 10pt }

 //.header-reporte { font-weight: bold; font-size: 12; color: black }
 //.mensajeCentral { font-weight: bold; font-size: 14; text-decoration: none; color: white}

//.row0 {background-color: #e7c366;}
//.row1 {background-color: #e7d29e;}
</style>



<div align="center"><a name="agregarUsuario" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarUsuario">Agregar nuevo Usuario</a></div>
<br>


<table width="100%" cellspacing=0 cellpadding=0 border=0 align="center">
	<tr>
		<td class="header-filtro">Buscar por:
		&nbsp;&nbsp;
		
		<c:if test="${opcion == 'nivel'}">
			<c:choose>
				<c:when test="${valorOpcion == '3'}">
					<c:set var="valorOpcion" value="Administrador" />
				</c:when>
				<c:when test="${valorOpcion == '2'}">
					<c:set var="valorOpcion" value="Supervisor" />
				</c:when>
				<c:when test="${valorOpcion == '1'}">
					<c:set var="valorOpcion" value="Operador" />
				</c:when>			
			</c:choose>
		</c:if>
		
		<select name="opciones" id="selectOpciones">
			<option value="ninguno"${opcion == 'ninguno'? 'selected' : ''}>Ninguno</option>
			<option value="id"${opcion == 'id' ? 'selected' : ''}>Id</option>
			<option value="nombre"${opcion == 'nombre' ? 'selected' : ''}>Nombre</option>
			<option value="nivel"${opcion == 'nivel'? 'selected' : ''}>Nivel</option>
			<%-- option value="fechaAlta"${opcion == 'fechaAlta'? 'selected' : ''}>Fecha Ingreso</option--%>
			<option value="nick"${opcion == 'nick'? 'selected' : ''}>Nombre de usuario</option>
			<%--option value="contacto"${opcion == 'contacto'? 'selected' : ''}>Contacto</option--%>
		</select>&nbsp;&nbsp;&nbsp;
		<input class="texto" type="text" name="valor" id="valor" value="${valorOpcion}" size="10" maxlength="40">
		&nbsp;&nbsp;
		<input type="button" class="boton" name="buscar" onclick="buscarRegistros('${orderBy}',document.formaPaginacion.regXPag.value,'${pagActual}')" value="Aceptar">
		</td>

		<td>
			&nbsp;
		</td>
  </tr>
<tr bgcolor="black">
	<td align="center" colspan="8">
		<font color="WHITE" class="mensajeCentral">	Usuarios SAPE, total de usuarios: ${cantidadTotalRegistros} </font>
	<tags:ayudas pagina="Usuario"/></td>
</tr>
  <tr align="right"><td width="100%" align="center"><jsp:include page="../reportes/paginacion.jsp" flush="true" /></td></tr>
</table>


<table align="center"width="100%">

<tr bgcolor="black">
	<td align="center" width="5%" class="header-reporte">
	
	<c:choose>
		<c:when test="${orderBy == 'id DESC'}">
			<span title="Filtrar por ID Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'id ASC')">Id</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por ID Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'id DESC')">Id</a></span>
		</c:otherwise>
	</c:choose>
	
	
	
	</td>
	<td align="center" width="15%" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'nick DESC'}">
			<span title="Filtrar por Nombre de Usuario Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'nick ASC')">Nombre de Usuario</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Nombre de Usuario Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'nick DESC')">Nombre de Usuario</a></span>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center" width="5%" class="header-reporte">
	
	<c:choose>
		<c:when test="${orderBy == 'activo DESC'}">
			<span title="Filtrar por Activo Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'activo ASC')">Activo</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Activo Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'activo DESC')">Activo</a></span>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center" width="10%" class="header-reporte">
	
	<c:choose>
		<c:when test="${orderBy == 'nivel DESC'}">
			<span title="Filtrar por Nivel Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'nivel ASC')">Nivel</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Nivel Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'nivel DESC')">Nivel</a></span>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center" width="20%" class="header-reporte">
		<c:choose>
		<c:when test="${orderBy == 'fechaAlta DESC'}">
			<span title="Filtrar por Fecha de Ingreso Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fechaAlta ASC')">Fecha de Ingreso</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Fecha de Ingreso Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fechaAlta DESC')">Fecha de Ingreso</a></span>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center" width="15%" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'nombre DESC'}">
			<span title="Filtrar por Nombre Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'nombre ASC')">Nombre</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Nombre Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'nombre DESC')">Nombre</a></span>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center" width="15%" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'contacto DESC'}">
			<span title="Filtrar por Contacto Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'contacto ASC')">Contacto</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por Contacto Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'contacto DESC')">Contacto</a></span>
		</c:otherwise>
	</c:choose>
	</td>
	<td align="center" width="15%" class="header-reporte">Opcion</td>
</tr>

<c:set var="i" value="0" />
<c:forEach items="${listaUsuarios}" var="user">

	<c:set var="row" value="${ i % 2 == 0? 0 : 1}"/>
	<tr class="row${row}">
		<td align="center" width="5%">${user.id}</td>
		<td align="center" width="15%">${user.nick}</td>
		<td align="center" width="5%">${user.activo}</td>
		<td align="center" width="10%">
		<c:choose>
			<c:when test="${user.nivel == '1'}">Operador</c:when>
			<c:when test="${user.nivel == '2'}">Supervisor</c:when>
			<c:when test="${user.nivel == '3'}">Administrador</c:when>
		</c:choose>
		</td>
		<td align="center" width="20%"><fmt:formatDate value= "${user.fechaAlta}" timeStyle="long" dateStyle="long" /></td>
		<td align="center" width="15%">${user.nombre}</td>
		<td align="center" width="15%">${user.contacto}</td>
		<td align="center" width="15%">
		<c:choose>
			<c:when test="${sessionScope.usuario.nivel == '3'}">
				<a name="modificarUsuario" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=modificarUsuario&id=${user.id}">modificar</a><br><a name="eliminarUsuario" href=javascript:eliminarUsuario(${user.id});>eliminar</a>
			</c:when>
			<c:when test="${sessionScope.usuario.nivel == '2'}">
				<c:if test="${user.nivel == '1'}">
					<a name="modificarUsuario" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=modificarUsuario&id=${user.id}">modificar</a><br><a name="eliminarUsuario" href=javascript:eliminarUsuario(${user.id});>eliminar</a>
				</c:if>
				
				<c:if test="${sessionScope.usuario.id == user.id}">
					<a name="modificarUsuario" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=modificarUsuario&id=${user.id}">modificar</a>
				</c:if>
			</c:when>
			
			<c:otherwise>
			</c:otherwise>
		</c:choose>
			
		</td>
	</tr>
	<c:set var="i" value="${i + 1}"/>
</c:forEach>
<tr bgcolor="black">
	<td align="left" colspan="8">
		<font color="WHITE" class="mensajeCentral">	Fin Reporte </font>
	</td>
</tr>
<tr>
	<td align="right" colspan="8">
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
</tr>
</table>
<br>
<div align="center"><a name="agregarUsuario" href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=agregarUsuario">Agregar nuevo Usuario</a></div>
</body>
</html>

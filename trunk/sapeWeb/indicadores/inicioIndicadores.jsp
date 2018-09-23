<!-- inicioIndiadores.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sapeTaglib" uri="/WEB-INF/tags/sape-taglib.tld" %>
<html>
<head>
<title>SAPE - Indicadores</title>
</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<div align="center" class="tituloPagina"><img src="imagenes_sape/tituloIndicadores.gif" name="indicadores" border="0"></div>
<br>
<br>
<table width="60%" border="0" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td width="48"><img src="imagenes_sape/imagenesMantenimiento/borde_superior_izquierdo.gif" name="borde_superior_izquierdo" border="0"></td>
		<td width="*" background="imagenes_sape/imagenesMantenimiento/borde_superior_horizontal.gif"></td>
		<td width="48"><img src="imagenes_sape/imagenesMantenimiento/borde_superior_derecho.gif" name="borde_superior_izquierdo" border="0"></td>
	</tr>
	<tr>
	<td width="48" background="imagenes_sape/imagenesMantenimiento/borde_derecho_vertical.gif"></td>
	<td width="*">
		<!-------- Aqui empieza El cuerpo de los indicadores -------->
		<table width="500" border="0" cellspacing="0" cellpadding="0" align="center">


<c:if test="${sapeTaglib:isVisible('GESTIONCOLAS')}">
		  	<tr align="center" valign="top">
		   		<td height="10" colspan="2">
		   			<span class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=indicadoresColas"><strong>Gestion de Colas</strong></a></span>
		   			<br>
		   			<br>
		   		</td>
		  	</tr>
</c:if>
			<tr align="center" >
		   		<td height="10" colspan="2">
		   		<span class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=estadisticaUsuarios">Estadistica Usuarios</a></span>
				   	<br>
		   			<br>
				</td>

		  	</tr>

			<tr align="center" >
		   		<td height="10" colspan="2">
		   		<span class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=estadisticaTelefonos">Estadistica Telefonos</a></span>
				   	<br>
		   			<br>
				</td>

		  	</tr>

			<tr align="center" >
				<td height="10" colspan="2">
				<span class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=estadisticaHoras">Estadisticas Por Horas</a></span>
					<br>
					<br>
				</td>

			</tr>
			<tr align="center" >
				<td height="10" colspan="2" class="menu">
				<a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=estadisticaCodigosVer">Estadisticas Por Codigo Ver</a>
					<br>
					<br>
				</td>
			</tr>
			<tr align="center" >
				<td height="10" colspan="2" class="menu">
					<a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=indicadoresCabezales">Indicadores de Cabezales</a>
					<br>
					<br>
				</td>
			</tr>
			
			<tr align="center" >
				<td height="10" colspan="2" class="menu">
					<a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=indicadoresTecnologia">Indicadores por Tecnologia</a>
					<br>
					<br>
				</td>
			</tr>

			<tr align="center" >
				<td height="10" colspan="2" class="menu">
					<a href="${pageContext.request.contextPath}/actionSape?accion=indicadoresPorCentral">Estadisticas por Central</a>
					<br>
					<br>
				</td>
			</tr>
			<c:if test="${sapeTaglib:isVisible('INDICADORES_ESTADO_CYCLADES')}">
						
			<tr align="center" >
				<td height="10" colspan="2" class="menu">
					<a href="${pageContext.request.contextPath}/actionSape?accion=estadoCyclades">Estado Cyclades</a>
					<br>
					<br>
				</td>
			</tr>
			</c:if>
			
			<tr align="center">
				<td height="10" colspan="2" class="menu">
					<a href="${pageContext.request.contextPath}/actionSape?accion=indicadoresPrimeraPrueba">Primera Prueba Tel&eacute;fono</a>
					<br>
					<br>
				</td>
			</tr>

<c:if test="${sapeTaglib:isVisible('INDICADORESTSTLI')}">
			<tr>
			  	<td colspan="2" align="center"><b><font color="black" size="4">Indicadores TSTLI <tags:ayudas pagina="Indicadoreststli"/> </font></b></td>
			</tr>
			<tr>
				<td width="200">&nbsp;</td>
				<td width="*" height="10" align="left"><span class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=graficaTSTLI&tipo=cable">Cable</a></span></td>
			</tr>
			<tr>
				<td width="200">&nbsp;</td>
				<td width="*" height="10" align="left"><span class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=graficaTSTLI&tipo=tipo_nodo">Tipo de Nodo</a></span></td>
			</tr>
			<tr>
				<td width="200">&nbsp;</td>
				<td width="*" height="10" align="left"><span class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=graficaTSTLI&tipo=area_trabajo_id">Area de Trabajo</a></span></td>
			</tr>
			<tr>
				<td width="200">&nbsp;</td>
				<td width="*" height="10" align="left"><span class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=graficaTSTLI&tipo=armario_id">Armario</a></span></td>
			</tr>
			<tr>
				<td width="200">&nbsp;</td>
				<td width="*" height="10" align="left"><span class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=graficaTSTLI&tipo=subzona_id">Subzona</a></span>
					<br>
					<br>
				</td>
			</tr>
</c:if>
			
		</table>
		<!-------- Aqui Termina El cuerpo de los indicadores -------->
	</td>
<td width="48" background="imagenes_sape/imagenesMantenimiento/borde_izquiedo_vertical.gif"></td>

</tr>


<td width="48"><img src="imagenes_sape/imagenesMantenimiento/borde_inferior_derecho.gif" name="borde_superior_izquierdo" border="0"></td>
		<td width="*" background="imagenes_sape/imagenesMantenimiento/borde_inferior_horizontal.gif"></td>
		<td width="48"><img src="imagenes_sape/imagenesMantenimiento/borde_inferior_izquierdo.gif" name="borde_superior_izquierdo" border="0"></td>
		

</body>
</html>

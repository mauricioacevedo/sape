					<!--  inicioReportes.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sapeTaglib" uri="/WEB-INF/tags/sape-taglib.tld" %>
<html>
<head>
<title>SAPE - Reportes</title>
</head>

<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0" bgcolor="white">
<jsp:include page="../encabezado.jsp" flush="true" />


<div align="center" class="tituloPagina"><img src="imagenes_sape/tituloReportes.gif" name="titulo"  border="0"></div>
<br>
<br>



<table width="60%" border="0" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td width="48"><img src="imagenes_sape/imagenesMantenimiento/borde_superior_izquierdo.gif" name="borde_superior_izquierdo" border="0"></td>
		<td width="*" background="imagenes_sape/imagenesMantenimiento/borde_superior_horizontal.gif"></td>
		<td width="48"><img src="imagenes_sape/imagenesMantenimiento/borde_superior_derecho.gif" name="borde_superior_izquierdo" border="0"></td>
	</tr>
<tr><td width="48" background="imagenes_sape/imagenesMantenimiento/borde_derecho_vertical.gif"></td><td width="*">

<!-- ---------------------------------------------------------------- -->




<table border="0" cellspacing="0" cellpadding="0" align="center" height="150">
<c:if test="${sapeTaglib:isVisible('REPORTETSTLI')}">
  <tr>
  	<td align="center" width="70" align="right"><img src="imagenes_sape/iconoReporte.gif"></td>
    <td height="40" class="menu" align="left"><a href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=inicioConsultaTelefonosTSTLI">Consulta de Telefonos en Colas</a></td>
  </tr>
</c:if>
  <tr>
  	<td align="center" width="70" align="right"><img src="imagenes_sape/iconoReporte.gif"></td>
    <td height="40" class="menu" align="left"><a href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=inicioConsultaPruebas">Consulta de pruebas</a></td>
  </tr>
  <tr>
  	<td align="center" width="70" align="right"><img src="imagenes_sape/iconoReporte.gif"></td>
    <td height="40" class="menu" align="left"><a href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=inicioEfectividadPrueba">Efectividad de Prueba</a></td>
  </tr>
  <tr>
  	<td align="center" width="70" align="right"><img src="imagenes_sape/iconoReporte.gif"></td>
    <td height="50" class="menu" align="left"><a href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=inicioConsultaRutinaPruebas">Rutina de pruebas por Evento</a></td>
  </tr>
  <tr>
  	<td align="center" width="70" align="right"><img src="imagenes_sape/iconoReporte.gif"></td>
    <td height="50" class="menu" align="left"><a href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=inicioPruebasProgramadas">Pruebas Programadas</a></td>
  </tr>

<c:if test="${sapeTaglib:isVisible('REPORTEADOR')}">  
  <tr>
  	<td align="center" width="70" align="right"><img src="imagenes_sape/iconoReporte.gif"></td>
    <td height="50" class="menu" align="left"><a href="${pageContext.request.contextPath}/actionSape?accion=reporteador">Reporteador SAPE</a></td>
  </tr>
</c:if>

<c:if test="${sapeTaglib:isVisible('CALIFICACION_RUTINAS')}">  
  <tr>
  	<td align="center" width="70" align="right"><img src="imagenes_sape/iconoReporte.gif"></td>
    <td height="50" class="menu" align="left"><a href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=calificacionRutinas">Calificacion Rutinas</a></td>
  </tr>
</c:if>

  
</table>



<!-- ---------------------------------------------------------------- -->


</td>
<td width="48" background="imagenes_sape/imagenesMantenimiento/borde_izquiedo_vertical.gif"></td>

</tr>


<td width="48"><img src="imagenes_sape/imagenesMantenimiento/borde_inferior_derecho.gif" name="borde_superior_izquierdo" border="0"></td>
		<td width="*" background="imagenes_sape/imagenesMantenimiento/borde_inferior_horizontal.gif"></td>
		<td width="48"><img src="imagenes_sape/imagenesMantenimiento/borde_inferior_izquierdo.gif" name="borde_superior_izquierdo" border="0"></td>



	<tr>
		<td></td>
		<td></td>
		<td></td>
	</tr>


</table>



</body>
</html>

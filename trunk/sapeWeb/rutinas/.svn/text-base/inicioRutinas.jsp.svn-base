<!--	inicioRutinas.jsp 	-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sapeTaglib" uri="/WEB-INF/tags/sape-taglib.tld" %>

<html>
<head>
<title>SAPE - Rutinas de Prueba</title>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript" src="../javascript/common.js"> </script>
</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">


<div align="center" class="tituloPagina"><img src="imagenes_sape/tituloRutinas.gif" name="titulo"  border="0"></div>
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

<!-- ---------------------------------------------------------------- -->

		<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center" height="150">
		  <tr align="center">
		    <td height="40" class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=inicioArmarios">Por Armario</a></td>
		    </tr>
		  	<tr align="center">
		    <td height="40" class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=inicioCables">Por Cable</a></td>
		  </tr>

		<c:if test="${sapeTaglib:isVisible('RUTINA_SERIE')}">
		  <tr align="center">
		    <td height="40" class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=inicioSeries">Por Series</a></td>
		  </tr>
		</c:if>
			
		  <tr align="center">
		    <td height="40" class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=inicioClientes">Por Clientes</a></td>
		  </tr>
		  
		  <c:if test="${sapeTaglib:isVisible('COLAS')}">
		  <tr align="center">
		    <td height="40" class="menu"><a href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=inicioColas">Por Cola</a></td>
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

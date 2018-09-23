					<!--  mantenimientoSiplexPRO.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sapeTaglib" uri="/WEB-INF/tags/sape-taglib.tld" %>
<html>
<head>
<title>SAPE - Mantenimiento SiplexPRO</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
</head>
<body bgcolor="white">
<jsp:include page="../../encabezado.jsp" flush="true" />


<div align="center" class="tituloPagina">Mantenimiento SiplexPRO <tags:ayudas pagina="Mantenimiento"/></div>
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


		<table width="270" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr><td height="5%" width="50%">&nbsp;</td><td height="5%" width="50%">&nbsp;</td></tr>
		
		
		<c:set var="linea" value="0" /> <%-- Con esta vble pretendo controlar la vista de cada uno
									 de los iconos para que se vean alineados --%>
		
		
		
		<c:if test="${sapeTaglib:isVisible('CPRSIPLEXPRO')}">
			${sapeTaglib:getMenuTag(linea)}
			<c:if test="${linea == 2}">
				<c:set var="linea" value="0"/>
			</c:if>
			<c:set var="linea" value="${linea +1}"/>
      	<td align="center" height="126" width="50%" background="imagenes_sape/imagenCPRS.gif" valign="bottom">
	  		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoCPRS">CPRS SiplexPRO</a>
      	</td>
		</c:if>
		
		<c:if test="${sapeTaglib:isVisible('DESBLOQUEOCABEZAS')}">
			${sapeTaglib:getMenuTag(linea)}
			<c:if test="${linea == 2}">
				<c:set var="linea" value="0"/>
			</c:if>
			<c:set var="linea" value="${linea +1}"/>
      	<td align="center" height="126" width="50%" background="imagenes_sape/siplex.gif" valign="bottom">
	  		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=desbloqueoSiplexPRO">Desbloqueo Cabezas</a>
      	</td>
		</c:if>
		
		<c:if test="${sapeTaglib:isVisible('ACTUALIZARCPRSERIE')}">
			${sapeTaglib:getMenuTag(linea)}
			<c:if test="${linea == 2}">
				<c:set var="linea" value="0"/>
			</c:if>
			<c:set var="linea" value="${linea +1}"/>
      	<td align="center" height="126" width="50%" background="imagenes_sape/siplex.gif" valign="bottom">
	  		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=actualizarCPRSerie">Actualizar CPR Serie</a>
      	</td>
		</c:if>
		
		<c:if test="${sapeTaglib:isVisible('CONTINGENCIA')}">
			${sapeTaglib:getMenuTag(linea)}
			<c:if test="${linea == 2}">
				<c:set var="linea" value="0"/>
			</c:if>
			<c:set var="linea" value="${linea + 1}"/>
      	<td align="center" height="126" width="50%" background="imagenes_sape/siplex.gif" valign="bottom">
	  		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=contingenciaSiplexPRO">Contingencia</a>
      	</td>
		</c:if>
		
		<c:if test="${sapeTaglib:isVisible('MATRIZ')}">
			${sapeTaglib:getMenuTag(linea)}
			<c:if test="${linea == 2}">
				<c:set var="linea" value="0"/>
			</c:if>
			<c:set var="linea" value="${linea + 1}"/>
      	<td align="center" height="126" width="50%" background="imagenes_sape/siplex.gif" valign="bottom">
	  		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=matrizSiplexPro">Matriz</a>
      	</td>
		</c:if>
		
		<c:if test="${sapeTaglib:isVisible('CONFIGADSL')}">
			${sapeTaglib:getMenuTag(linea)}
			<c:if test="${linea == 2}">
				<c:set var="linea" value="0"/>
			</c:if>
			<c:set var="linea" value="${linea +1}"/>
      	<td align="center" height="126" width="50%" background="imagenes_sape/siplex.gif" valign="bottom">
	  		<a  href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=configADSL">Configuracion ADSL</a>
      	</td>
		</c:if>
		
		<c:if test="${sapeTaglib:isVisible('MODIFIC_TIPOPRUEBA')}">
			${sapeTaglib:getMenuTag(linea)}
			<c:if test="${linea == 2}">
				<c:set var="linea" value="0"/>
			</c:if>
			<c:set var="linea" value="${linea +1}"/>
      	<td align="center" height="126" width="50%" background="imagenes_sape/siplex.gif" valign="bottom">
	  		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=modificarDescTipoPrueba">Modificar TipoPrueba</a>
      	</td>
		</c:if>

		<c:if test="${sapeTaglib:isVisible('CARGAR_FIRMWARE')}">
			${sapeTaglib:getMenuTag(linea)}
			<c:if test="${linea == 2}">
				<c:set var="linea" value="0"/>
			</c:if>
			<c:set var="linea" value="${linea +1}"/>
      	<td align="center" height="126" width="50%" background="imagenes_sape/siplex.gif" valign="bottom">
	  		<a href="${pageContext.request.contextPath}/actionSape?accion=firmware">Cargar Firmware.</a>
      	</td>
		</c:if>
		
		<c:if test="${sapeTaglib:isVisible('MANTENIMIENTO_DLU')}">
			${sapeTaglib:getMenuTag(linea)}
			<c:if test="${linea == 2}">
				<c:set var="linea" value="0"/>
			</c:if>
			<c:set var="linea" value="${linea +1}"/>
      	<!-- td align="center" height="126" width="90" background="imagenes_sape/siplex.gif" valign="bottom"-->
      	<td align="center" height="126" width="50%" background="imagenes_sape/siplex.gif" valign="bottom">
			<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=mantenimientoDLU">DLU's SiplexPRO</a>
      	</td>
		</c:if>
		
		<c:if test="${sapeTaglib:isVisible('MANTENIMIENTO_LI')}">
			${sapeTaglib:getMenuTag(linea)}
			<c:if test="${linea == 2}">
				<c:set var="linea" value="0"/>
			</c:if>
			<c:set var="linea" value="${linea +1}"/>
      	<!-- td align="center" height="126" width="90" background="imagenes_sape/siplex.gif" valign="bottom"-->
      	<td align="center" height="126" width="50%" background="imagenes_sape/siplex.gif" valign="bottom">
			<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=mantenimientoLI">LI's SiplexPRO</a>
      	</td>
		</c:if>
		
		
		  <!-- tr align="center">
		    <td height="126" width="135" background="imagenes_sape/imagenCPRS.gif" valign="bottom"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoCPRS">CPRS SiplexPRO</a></td>
			<td width="55"></td>
		    <td height="126" width="135" background="imagenes_sape/siplex.gif" valign="bottom"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=desbloqueoSiplexPRO">Desbloqueo Cabezas</a></td>
		  </tr>
		  <tr>
		  	<td height="20"></td>
		  </tr->
		  <tr align="center">
		    <td height="126" width="135" background="imagenes_sape/siplex.gif" valign="bottom"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=actualizarCPRSerie">Actualizar CPR Serie</a></td>
		  	<td width="55"></td>
		  	<c:if test="${sapeTaglib:isVisible('CONTINGENCIA')}">
		    <td height="126" width="135" background="imagenes_sape/siplex.gif" valign="bottom">
				<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=contingenciaSiplexPRO">Contingencia</a></td>
			</c:if>
		  </tr>
		  <!-- tr>
		  	<td height="20"></td>
		  </tr>
		  <tr align="center">
		  <c:if test="${sapeTaglib:isVisible('MATRIZ')}">
		    <td height="126" width="135" background="imagenes_sape/siplex.gif" valign="bottom">
		    	<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=matrizSiplexPro">Matriz</a>
		    </td>
			</c:if>
		    <td width="55"></td>
		    <c:if test="${sapeTaglib:isVisible('CONFIGADSL')}">
		    <td height="126" width="135" background="imagenes_sape/siplex.gif" valign="bottom">
				<a  href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=configADSL">Configuracion ADSL</a>
		    </td>
			</c:if>
		  </tr>
		  <tr>
		  	<td height="20"></td>
		  </tr>
		   <tr align="center">
		  <c:if test="${sapeTaglib:isVisible('MODIFIC_TIPOPRUEBA')}">
		    <td height="126" width="135" background="imagenes_sape/siplex.gif" valign="bottom">
		    	<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=modificarDescTipoPrueba">Modificar TipoPrueba</a>
		    </td>
			</c:if>
			<td></td>
			<c:if test="${sapeTaglib:isVisible('CARGAR_FIRMWARE')}">
		    <td height="126" width="135" background="imagenes_sape/siplex.gif" valign="bottom">
		    	<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=inicioCargarFirmware">Cargar Firmware.</a>
		    </td>
			</c:if>
		  </tr>
		  
		  <tr align="center">
		  <c:if test="${sapeTaglib:isVisible('MANTENIMIENTO_DLU')}">
		    <td height="126" width="135" background="imagenes_sape/siplex.gif" valign="bottom">
		    	<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=mantenimientoDLU">DLU's SiplexPRO</a>
		    </td>
			</c:if>
			<td></td>
		    <td></td-->
		    
		    
		    
		  </tr>
		</table>




<!-- ---------------------------------------------------------------- -->


	</td>
	<td width="48" background="imagenes_sape/imagenesMantenimiento/borde_izquiedo_vertical.gif"></td>
</tr>
<tr>
	<td width="48">
	<img src="imagenes_sape/imagenesMantenimiento/borde_inferior_derecho.gif" name="borde_superior_izquierdo" border="0"></td>
		<td width="*" background="imagenes_sape/imagenesMantenimiento/borde_inferior_horizontal.gif"></td>
		<td width="48"><img src="imagenes_sape/imagenesMantenimiento/borde_inferior_izquierdo.gif" border="0"></td>
</tr>
</table>

<br>
<div align="center"><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento">Regresar</a></div>
</body>
</html>

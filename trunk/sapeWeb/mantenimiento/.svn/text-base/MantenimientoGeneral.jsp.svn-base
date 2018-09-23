					<!-- MantenimentoGeneral.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sapeTaglib" uri="/WEB-INF/tags/sape-taglib.tld" %>
<html>
<head>
	<title>SAPE - Mantenimiento</title>
</head>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" bgcolor="white">
<jsp:include page="../encabezado.jsp" flush="true" />

<div align="center" class="tituloPagina"><img src="imagenes_sape/tituloMantenimiento.gif" name="titulo"  border="0"></div>
<br>

<table width="80%" border="0" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td width="48"><img src="imagenes_sape/imagenesMantenimiento/borde_superior_izquierdo.gif" name="borde_superior_izquierdo" border="0"></td>
		<td width="*" background="imagenes_sape/imagenesMantenimiento/borde_superior_horizontal.gif"></td>
		<td width="48"><img src="imagenes_sape/imagenesMantenimiento/borde_superior_derecho.gif" name="borde_superior_izquierdo" border="0"></td>
	</tr>
<tr><td width="48" background="imagenes_sape/imagenesMantenimiento/borde_derecho_vertical.gif"></td><td width="*">

<!-- ---------------------------------------------------------------- -->



  <table align="center" border="0" height="250" width="100%">
	<tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr>

<c:set var="linea" value="0" /> <%-- Con esta vble pretendo controlar la vista de cada uno
									 de los iconos para que se vean alineados --%>
									 
<c:if test="${sapeTaglib:isVisible('USUARIOS')}">
	
	${sapeTaglib:getMenuTag(linea)}

	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
	
      <td align="center" height="15%" width="50%">
	  	<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoUsuarios"><img src="imagenes_sape/usuarios.gif" name="usuarios" border="0"></a>
      </td>
</c:if>

<c:if test="${sapeTaglib:isVisible('PROCESOS_AUTOM')}">

	${sapeTaglib:getMenuTag(linea)}
	
	<%--c:choose>
		<c:when test="${linea == 0}">
			<tr>
		</c:when>
		<c:when test="${linea == 1}"></c:when>
		<c:when test="${linea == 2}">
			</tr><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr><tr>
		</c:when>
	</c:choose--%>
	
	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
		
	    <td align="center" height="15%" width="50%">
			<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoProcesos"><img src="imagenes_sape/procesosAutomaticos.gif" border="0"></a>
		</td>
</c:if>

    
<c:if test="${sapeTaglib:isVisible('TIPO_NODO')}">

	${sapeTaglib:getMenuTag(linea)}
	<%--c:choose>
		<c:when test="${linea == 0}">
			<tr>
		</c:when>
		<c:when test="${linea == 1}"></c:when>
		<c:when test="${linea == 2}">
			</tr><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr><tr>
		</c:when>
	</c:choose--%>
	
	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
    <td align="center" height="15%" width="50%">
		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoTipoNodo"><img src="imagenes_sape/tiponodos.gif" border="0"></a>
	</td>
</c:if>
    


<c:if test="${sapeTaglib:isVisible('BASE_DATOS')}">
	
	${sapeTaglib:getMenuTag(linea)}
	<%--c:choose>
		<c:when test="${linea == 0}">
			<tr>
		</c:when>
		<c:when test="${linea == 1}"></c:when>
		<c:when test="${linea == 2}">
			</tr><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr><tr>
		</c:when>
	</c:choose--%>
	
	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
    <td align="center" height="15%" width="50%">
		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoBasedeDatos"><img src="imagenes_sape/baseDatos.gif" border="0"></a>
    </td>
</c:if>


<c:if test="${sapeTaglib:isVisible('SERIES')}">

	${sapeTaglib:getMenuTag(linea)}
	<%--c:choose>
		<c:when test="${linea == 0}">
			<tr>
		</c:when>
		<c:when test="${linea == 1}"></c:when>
		<c:when test="${linea == 2}">
			</tr><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr><tr>
		</c:when>
	</c:choose--%>
	
	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
    <td align="center" height="15%" width="50%">
		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoSeries"><img src="imagenes_sape/serienumerica.gif" border="0"></a>
	</td>
</c:if>


<c:if test="${sapeTaglib:isVisible('CABEZA_PRUEBA')}">

	${sapeTaglib:getMenuTag(linea)}
	<%--c:choose>
		<c:when test="${linea == 0}">
			<tr>
		</c:when>
		<c:when test="${linea == 1}"></c:when>
		<c:when test="${linea == 2}">
			</tr><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr><tr>
		</c:when>
	</c:choose--%>
	
	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
    <td align="center" height="15%" width="50%">
		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoCabezaPrueba"><img src="imagenes_sape/clasenodos.gif" border="0"></a>
	</td>    
</c:if>
    
    
<c:if test="${sapeTaglib:isVisible('CODIGOS_VER')}">

	${sapeTaglib:getMenuTag(linea)}
	<%--c:choose>
		<c:when test="${linea == 0}">
			<tr>
		</c:when>
		<c:when test="${linea == 1}"></c:when>
		<c:when test="${linea == 2}">
			</tr><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr><tr>
		</c:when>
	</c:choose--%>
	
	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
     <td align="center" height="15%" width="50%">
	    <a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoCodigosVer"><img src="imagenes_sape/codigover.gif" border="0"></a>
     </td>
	</c:if>
    
    
    
    
<c:if test="${sapeTaglib:isVisible('EVALUACION')}">

	${sapeTaglib:getMenuTag(linea)}	
	<%--c:choose>
		<c:when test="${linea == 0}">
			<tr>
		</c:when>
		<c:when test="${linea == 1}"></c:when>
		<c:when test="${linea == 2}">
			</tr><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr><tr>
		</c:when>
	</c:choose--%>
	
	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
    <td align="center" height="15%" width="50%">
		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=evaluacionResultados"><img src="imagenes_sape/evaluacion.gif" border="0"></a>
	</td>
	</c:if>

<c:if test="${sapeTaglib:isVisible('PERMITIDOS')}">

	${sapeTaglib:getMenuTag(linea)}
	<%--c:choose>
		<c:when test="${linea == 0}">
			<tr>
		</c:when>
		<c:when test="${linea == 1}"></c:when>
		<c:when test="${linea == 2}">
			</tr><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr><tr>
		</c:when>
	</c:choose--%>
	
	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
    <td align="center" height="15%" width="50%">
		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoPermitidos"><img src="imagenes_sape/permitidos.gif" border="0"></a>
	</td>
</c:if>


<c:if test="${sapeTaglib:isVisible('MANTENIMIENTO_SIPLEXPRO')}">
	
	${sapeTaglib:getMenuTag(linea)}	
	<%--c:choose>
		<c:when test="${linea == 0}">
			<tr>
		</c:when>
		<c:when test="${linea == 1}"></c:when>
		<c:when test="${linea == 2}">
			</tr><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr><tr>
		</c:when>
	</c:choose--%>
	
	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
    <td align="center" height="15%" width="50%">
		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoSiplexPRO"><img src="imagenes_sape/mantenimientoSiplexpro.gif" border="0"></a>
    </td>
</c:if>



<c:if test="${sapeTaglib:isVisible('ARMARIOS')}">

	${sapeTaglib:getMenuTag(linea)}
	<%--c:choose>
		<c:when test="${linea == 0}">
			<tr>
		</c:when>
		<c:when test="${linea == 1}"></c:when>
		<c:when test="${linea == 2}">
			</tr><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr><tr>
		</c:when>
	</c:choose--%>
	
	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
    	<td align="center" height="15%" width="50%">
			<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoArmarios"><img src="imagenes_sape/armario.gif" border="0"></a>
    	</td>
	</c:if>

<c:if test="${sapeTaglib:isVisible('MANTENIMIENTO_INDIGOFAST_EPM')}">
	${sapeTaglib:getMenuTag(linea)}
	<%--c:choose>
		<c:when test="${linea == 0}">
			<tr>
		</c:when>
		<c:when test="${linea == 1}"></c:when>
		<c:when test="${linea == 2}">
			</tr><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr><tr>
		</c:when>
	</c:choose--%>
	
	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
    <td align="center">
		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast"><img src="imagenes_sape/indigofast.gif" border="0"></a>
	</td>
	</c:if>

    
    
<c:if test="${sapeTaglib:isVisible('UMBRALES')}">
	${sapeTaglib:getMenuTag(linea)}
	<%--c:choose>
		<c:when test="${linea == 0}">
			<tr>
		</c:when>
		<c:when test="${linea == 1}"></c:when>
		<c:when test="${linea == 2}">
			</tr><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr><tr>
		</c:when>
	</c:choose--%>
	
	<c:if test="${linea == 2}">
		<c:set var="linea" value="0"/>
	</c:if>
	<c:set var="linea" value="${linea +1}"/>
    <td align="center">
		<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=umbralesSAPE"><img src="imagenes_sape/umbrales.gif" border="0"></a>
	</td>
	</c:if>
    
    </tr>
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

				<!-- mantenimientoColas.jsp -->
<jsp:useBean id="listaColas" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="listaAlarmas" class="java.util.ArrayList" scope="request"/>
<%@ taglib prefix="jsp2" uri="http://java.sun.com/jstl/core_rt" %>
<html><head><title>SAPE Mantenimiento de Colas</title>

<script language="JavaScript">

	function eliminarCola(cola){
		if(confirm("Esta seguro de borrar la cola: "+cola+"\nCon el borrado de la cola se pierde la alarma!.")){
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=eliminarCola&nombreCola="+cola;
		
		}
		
	}
	
	function eliminarAlarma(cola){
		if(confirm("Esta seguro de eliminar la alarma asignada a la cola: "+cola+"?")){
		
		location.href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=eliminarAlarma&nombreCola="+cola;
		
		};
		
	}	
	
	function mostrarAlarma(nombreCola){
		var win = window.open("${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=mostrarAlarma&cola="+nombreCola,"servicio","width=400, height=400, left=100, rigth=100,scrollbars=no,resizable=yes, menubar=no, location=no");
		win.focus();
	}

</script></head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body bgcolor="white">
<br>
<table width="70%" align="center" class="mensajeCentral">
<tr>
<td align="center"> MANTENIMIENTO DE COLAS </td>
</tr>
</table>
<br>
<center>
<a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=agregarCola">Agregar cola nueva</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/actionSape?accion=indicadores">Indicadores</a>
</center>
<br>
<table  border="0" width="600" align="center" class="header-reporte">
<tr align="center"><b>
<td align="center" width="30%">Nombre</td>
<td align="center" width="30%">Descripci&oacute;n</td>
<td align="center" width="10%">Ver alarma</td>
<td align="center" width="30%">Opci&oacute;n</td>
</tr>
</table>
<table  border="1" width="600" align="center" bgcolor="white">
<jsp2:forEach items="${listaColas}" var="cola">
	<%-- Aqui se valida si la cola tiene alarma asociada --%>
	<jsp2:set var="tieneAl" value="false"/>
	<jsp2:forEach items="${listaAlarmas}" var="alarma">
		
		<jsp2:if test="${alarma.nombreCola == cola.nombre}" var="tieneAlarma">
			
			<jsp2:if test="${tieneAlarma == 'true'}" var="si">
			
				<jsp2:set var="tieneAl" value="${tieneAlarma}"/>
				
			</jsp2:if>
		</jsp2:if>
	</jsp2:forEach>
	
<tr align="center">
<td align="center" width="30%">${cola.nombre}</td>
<td align="center" width="30%">${empty cola.descripcion ? "&nbsp;" : cola.descripcion}</td>
<td align="center" width="10%" class="menu">
	<jsp2:choose>
		<jsp2:when test="${tieneAl}">
			<a href="javascript:mostrarAlarma('${cola.nombre}');">Alarma</a>
		</jsp2:when>
		<jsp2:otherwise>&nbsp;</jsp2:otherwise>
	</jsp2:choose>
</td>
<td align="center" width="30%" class="menu">
<a href="javascript:eliminarCola('${cola.nombre}')">Eliminar Cola</a>
<br>
	<jsp2:choose>
		<jsp2:when test="${tieneAl}">
			<a href="javascript:eliminarAlarma('${cola.nombre}')">Eliminar alarma</a>
		</jsp2:when>
		<jsp2:otherwise>
			<a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=agregarAlarma&nombreCola=${cola.nombre}">Agregar alarma</a>
		</jsp2:otherwise>
	</jsp2:choose>
</td>
</tr>
</jsp2:forEach>

</table>


<br>
<center>
<a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=agregarCola">Agregar cola nueva</a>&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/actionSape?accion=indicadores">Indicadores</a>
</center>

</body></html>
    

<jsp:useBean id="listaColas" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="listaAlarmas" class="java.util.ArrayList" scope="request"/>

<%@ taglib prefix="jsp2" uri="http://java.sun.com/jstl/core_rt" %>

<html><head><title>Sistema Administrativo de Pruebas Extendidas - SAPE</title>

<script language="JavaScript">

  function limpia_forma(forma) {
    forma.reset();
    return;
    }

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
		var win = window.open("${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=mostrarAlarma&cola="+nombreCola,"servicio","width=400, height=400, left=100, rigth=100,scrollbars=no, menubar=no, location=no,resizable=yes");
		win.focus();
	}

</script></head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body bgcolor="white">
<h1 align="center">MANTENIMIENTO DE COLAS</h1>
<br>
<center><a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=agregarCola">Agregar cola nueva</a>&nbsp;&nbsp;&nbsp;<a href="/sape/indicadores/IndicadoresServlet">Indicadores</a></center>
<br>
<table  border="0" width="600" align="center" bgcolor="black">
<tr align="center"><b>
<td align="center" width="30%"><font color="white" face="Arial" size="4">Nombre</font></td>
<td align="center" width="30%"><font color="white" face="Arial" size="4">Descripci&oacute;n</font></td>
<td align="center" width="10%"><font color="white" face="Arial" size="4">Ver alarma</font></td>
<td align="center" width="30%"><font color="white" face="Arial" size="4">Opci&oacute;n</font></td>
</tr>
</table>
<table  border="1" width="600" align="center" bgcolor="#dad2c1">
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
<td align="center" width="10%">
	<jsp2:choose>
		<jsp2:when test="${tieneAl}">
				<a href="javascript:mostrarAlarma('${cola.nombre}');">alarma</a> 
		</jsp2:when>
		<jsp2:otherwise>&nbsp;</jsp2:otherwise>
	</jsp2:choose>
</td>
<td align="center" width="30%">
<a href="javascript:eliminarCola('${cola.nombre}')">Eliminar Cola</a>
<!--<a href="/sape/indicadores/IndicadoresServlet?operacion=eliminarCola&nombreCola=${cola.nombre}">Eliminar Cola</a>-->
<br>
	<jsp2:choose>
		<jsp2:when test="${tieneAl}">
			<a href="javascript:eliminarAlarma('${cola.nombre}')">Eliminar alarma</a>
			<!--<a href="/sape/indicadores/IndicadoresServlet?operacion=eliminarAlarma&nombreCola=${cola.nombre}">Eliminar alarma</a>-->
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
<center><a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=agregarCola">Agregar cola nueva</a>&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/actionSape?accion=indicadores">Indicadores</a></center>

</body></html>

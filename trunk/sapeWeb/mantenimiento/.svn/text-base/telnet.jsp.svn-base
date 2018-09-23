<!-- telnet.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="port" class="java.lang.String" scope="request" />
<jsp:useBean id="ip" class="java.lang.String" scope="request" />
<jsp:useBean id="lastState" class="java.lang.String" scope="request" />
<jsp:useBean id="id" class="java.lang.String" scope="request" />
<jsp:useBean id="tipoPrueba" class="java.lang.String" scope="request" />
<jsp:useBean id="cabezaPrueba" class="java.lang.String" scope="request" />
<jsp:useBean id="telCliente" class="java.lang.String" scope="request" />
<jsp:useBean id="telOperador" class="java.lang.String" scope="request" />
<jsp:useBean id="central" class="java.lang.String" scope="request" />
<jsp:useBean id="from" class="java.lang.String" scope="request" />

<html>
	<head>
		<title>Telnet Sape - ${tipoPrueba}</title>
		
	
		<script language="JavaScript">
		
			function updateOnExit(){
				estado = '${lastState}';
				if(estado.length > 0 || estado != ""){
					location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=actualizarEstadoTipoNodo&id=${id}&lastState="+estado;
					delay(1000);
				}
				<c:if test="${empty from}">
				window.opener.location.href= "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoTipoNodo";
				</c:if>
				//window.opener.location.reload();
			}
		
			function updtateOnEnter(){
			<c:if test="${empty from}">
				window.opener.location.href= "${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoTipoNodo&estadoActual=${tipoPrueba}";
			</c:if>
				//window.opener.location.reload();
			}
			
			function delay(gap){/*gap esta en milisegundos!!!*/
				var then,now;
				then=new Date().getTime();
				now=then;
				while((now-then)<gap)
				{now=new Date().getTime();}
			
			}
		</script>
	
	
	</head>
<body onunload="javascript:updateOnExit();" onload="javascript:updtateOnEnter();" topmargin="0" leftmargin="0" alink="olive" bgcolor="white" link="#330099"><center>

<br>

<table align="center" bgcolor="ORANGE" width="100%" border="2">
<tr>
	<td align="center">
		<font color="BLACK" face="Arial" size="4">
			<b>Terminal Telnet Host--&gt;${ip} Puerto--&gt; ${port}</b>
		</font>
	</td>
</tr>

<tr>
	<td align="center" width="100%">

	<applet archive="imagenes_sape/telnetClient.jar" code="com.osp.telVT100.Telnet" height="450" width="560">

		<param name="host" value="${ip}">		
		<param name="port" value="${port}">
	    <param name="emulation" value="VT100">
	    <param name="modo" value="${tipoPrueba}">
	    <param name="central" value="${central}">
   		<param name="nombreCabeza" value="${cabezaPrueba}">
		<c:if test="${tipoPrueba == 'Interactiva'}">
		<param name="telCliente" value="${telCliente}">
		<param name="telOperador" value="${telOperador}">
		</c:if>
	</applet>
	</td>
</tr>

</table>
</body>
</html>

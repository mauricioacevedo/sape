<!-- telnet.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="ipInd" class="java.lang.String" scope="request" />
<jsp:useBean id="puertoInd" class="java.lang.String" scope="request" />
<jsp:useBean id="tipoPrueba" class="java.lang.String" scope="request" />
<jsp:useBean id="port" class="java.lang.String" scope="request" />
<jsp:useBean id="ip" class="java.lang.String" scope="request" />
<jsp:useBean id="tecnologia" class="java.lang.String" scope="request" />
<jsp:useBean id="telCliente" class="java.lang.String" scope="request" />
<jsp:useBean id="telOperador" class="java.lang.String" scope="request" />

<html>
	<head>
		<title>SAPE Interactivas - ${tecnologia}</title>
		
	
		<script language="JavaScript">
		
			function updateOnExit(){
				//if(estado.length > 0 || estado != ""){
					//location.href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=actualizarEstadoTipoNodo&id=${id}&lastState="+estado;
					//delay(1000);
				//}

				//window.opener.location.reload();
			}
		
			function updtateOnEnter(){

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
<body onunload="javascript:updateOnExit();" onload="javascript:updtateOnEnter();" topmargin="10" leftmargin="10" alink="olive" bgcolor="WHITE" link="#330099">
 
 
	<c:set var="clase" value="${tecnologia}"/>
	<c:if test = "${clase == 'SIPLEXPRO-CPR' || clase == 'SIPLEXPRO-MAT'}">
	<c:set var="clase" value="SIPLEXPRO"/>
	</c:if>
		
	<center>
		
		<applet archive="interactivas.jar" code="com.osp.tecnologias.Telnet${clase}" codebase="${pageContext.request.contextPath}/jars" width="425" height="435">
		<!-- El certificado del applet se debe actualizar cada seis meses. Utima firma 21/04/2006 -->
		
				<param name="ip" value="${ip}">		
				<param name="port" value="${port}">
				<param name="ipInd" value="${ipInd}">		
				<param name="puertoInd" value="${puertoInd}">
				<param name="telCliente" value="${telCliente}">
				<param name="telOperador" value="${telOperador}">
				<param name="tipoPrueba" value="${tipoPrueba}">
		
		</applet>
	</center>


</body>
</html>

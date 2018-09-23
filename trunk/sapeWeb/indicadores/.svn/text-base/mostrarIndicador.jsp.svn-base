<jsp:useBean id="parametros" class="java.lang.String" scope="request" />

<html>
<head>
<title>Grafica Barras - Sape</title>
<script language="JavaScript">
<!--
function salir() {
parent.window.close();
}
-->
</script>
</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body>
<center>

<param name="datos" value="${parametros}"></applet-->

<table>
	<tr>
		<td>
			<applet archive="jcommon-0.8.8.jar,jfreechart-0.9.8.jar,GraphBarras.jar" code="grafico.GraphBarras" codebase="${pageContext.request.contextPath}/jars" width="780" height="500">
				<param name="datos" value="${datos}">
			</applet>
		</td>
		
	</tr>
	<tr>
		<td>
			<font color="#ff5900" size="3px">Click derecho sobre la gr&aacute;fica para mas opciones</font>
		</td>
	</tr>
</table>
</center>
<p>
</p>
</body></html>

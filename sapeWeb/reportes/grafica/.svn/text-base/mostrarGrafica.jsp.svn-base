				<!-- reportes/grafica/motrarGrafica.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:useBean id="datos" class="java.lang.String" scope="request" />
<jsp:useBean id="titulo" class="java.lang.String" scope="request" />
<html>
<head>
<title>Sape - Gr&aacute;fica</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript" src="javascript/varios.js"> </script>
</head>
<body  bgcolor="WHITE">
<table width="100%">
<tr>
<td align="center">
<applet code="com.osp.SiplexProApplet" width="550" height="450" codebase="${pageContext.request.contextPath}/jars" Archive="osp.jar,jcommon-0.8.0.jar,jfreechart-0.9.8.jar">
	<param name="titulo" value="SAPE/OSP">
	<param name="idioma" value="espanol">
	<param name="datos" value="${datos}">
</applet>
</td>
</tr>
</table>
<br>
<br>
<div align="center" class="graficaLink"><a href="javascript:window.close();">Cerrar</a></div>
</body>
</html>

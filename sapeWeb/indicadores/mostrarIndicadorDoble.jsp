				<!-- mostrarIndicadorDoble.jsp -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<fmt:setLocale value="es_ES" scope="session" />
<jsp:useBean id="parametrosC" class="java.lang.String" scope="request" />
<jsp:useBean id="parametrosP" class="java.lang.String" scope="request" />
<jsp:useBean id="graficasMostrar" class="java.lang.String" scope="request" />
<jsp:useBean id="limite" class="java.lang.String" scope="request" />
<jsp:useBean id="fechaUltimaCarga" class="java.util.Date" scope="request" />
<html>
<meta http-equiv="refresh" content="60;${pageContext.request.requestURL}?<% out.print(request.getQueryString()); %>">
<head>
<title>SAPE - Estado de las colas</title>
<jsp:include page="../encabezado.jsp" flush="true" />
</head>
<body  bgcolor="white">
<center>
<table border="1">

<tr>
<td colspan="3" height="50">

<!--img src="imagenes_sape/eeppm.gif" width="60" align="left" height="75"-->

<p align="center">Ultima fecha de carga: <fmt:formatDate type="both" value="${fechaUltimaCarga}" timeStyle="short" dateStyle="long" /></p>
</td>
</tr>
<tr>
<td colspan="3">
<applet  code="GraficaBarras" codebase="${pageContext.request.contextPath}/jars" align="center" archive="jfreechart-1.0.0-pre2.jar,jcommon-1.0.0-pre2.jar,LabelGenerator.class" height="350" width="800">
	<param name="PENDI" value="${parametrosP}">
	<param name="CUMPL" value="${parametrosC}">
	<param name="limite" value="${limite}">
	<param name="color1" value="#FFFFFF">
	<param name="color2" value="#EEEEEE">
</applet>
</td>
</tr>
<tr>
<td width="27%"><font size="-1">Powered by: </font><br>
<img src="imagenes_sape/osp_n.gif" width="100%" height="55"></td>
<td>
<center>

<a href=javascript:window.history.back();>Regresar</a>
&nbsp;&nbsp;
<a href=javascript:location.reload();>Refrescar</a>

</center>
</td>
<td width="27%" height="30">
<img src="imagenes_sape/sape.gif" width="160" height="55">
</td>
</tr><br>

</table>
</center>

</body></html>

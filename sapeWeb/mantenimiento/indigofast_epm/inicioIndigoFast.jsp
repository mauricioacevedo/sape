<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- inicioIndigoFast.jsp -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mantenimiento de Indigos y Fast</title>
</head>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" bgcolor="white">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<jsp:include page="../../encabezado.jsp" flush="true" />

<br><br>
<table align="center" width="300">
<tr bgcolor="black" align="center">
	<td align="center" colspan="8">
		<font color="WHITE" class="mensajeCentral">Mantenimiento de Indigos y Fast</font>
</tr>
</table>
<br><br>
<!-- center><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=inicioIndigos">Mantenimiento Indigos</a></center-->
<br>
<center><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=inicioFast">Mantenimiento Fast</a></center>
<br>
<center><a href="${pageContext.request.contextPath}/actionSape?accion=mantenimientoIndigoFast&operacion=operacionesBasicas">Mantenimiento Indigos</a></center>
</body>
</html>
		<!-- programacionCables.jsp -->

<%@ taglib prefix="jsp2" uri="http://java.sun.com/jstl/core_rt" %>
<% java.util.List listaHoraCable = (java.util.List) request.getAttribute("listaHoraCable"); %>
<jsp:useBean id="cable" class="java.lang.String" scope="request"/>


<html>
<title>Programacion de Cables - SAPE</title>
<script language="JavaScript" src="javascript/common.js"> </script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="WHITE" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0" onLoad="MM_preloadImages('imagenes_sape/cerrar_u.gif')">

<BR>
<BR>
<table width=50% BGCOLOR="BLACK" align=CENTER>
<tr>
<td  BGCOLOR="" align=center><font color="WHITE" face="Arial, Helvetica, sans-serif" size="3"><B>Programacion de Cables </B>
<br> Cable: ${cable}

</font></td>
</tr>
</table>

<table width=50% align=CENTER  bgcolor="#ff6600">
<tr>
<td width="40%"><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">Hora</I></font></td>
<td width="60%" align=center><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">Estado</I></font></td>
</tr>
</table>

<jsp2:forEach var="hor" items="${listaHoraCable}">
	<table width=50% align=CENTER border=1>
	<tr>
	<td width="40%"><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">Hora ${hor.hora} - ${hor.valor} </I></font></td>
	<td width="60%" align=center><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">
	<img src="imagenes_sape/${hor.valor == '1'? 'check.gif' : 'nocheck.gif'}" width="10%" border="0">
	</I></font></td>
	</tr>
	</table>
</jsp2:forEach>
<BR>
<table width=50% align=CENTER>
<TR><TD>
<center>
	<input type="button" class="boton" value="Cerrar" onClick="window.close();">
</center>
</TD></TR>
</table>

</body>
</html>

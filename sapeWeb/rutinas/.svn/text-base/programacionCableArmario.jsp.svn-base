		<!-- programacionCableArmario.jsp -->

<%@ taglib prefix="jsp2" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% java.util.List listaHoraCableArmario = (java.util.List) request.getAttribute("listaHoraCableArmario"); %>
<jsp:useBean id="tecnologia" class="java.lang.String" scope="request"/>
<jsp:useBean id="armario" class="java.lang.String" scope="request"/>
<jsp:useBean id="cable" class="java.lang.String" scope="request"/>
<jsp:useBean id="central" class="java.lang.String" scope="request"/>

<!-- programacionCableArmario.jsp -->
<html>
<title>Programacion de Cables - SAPE</title>
<script language="JavaScript" src="javascript/common.js"> </script>

<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0" onLoad="MM_preloadImages('imagenes_sape/cerrar_u.gif')">

<BR>
<BR>
<table width=70% BGCOLOR="BLACK" align=CENTER>
<tr>
<td  BGCOLOR="" align=center><font color="WHITE" face="Arial, Helvetica, sans-serif" size="3"><B>Programacion Tecnologia <font color="WHITE" face="Arial" size="+1">${tecnologia}</font></B>
<br> 
Central: ${central} - Cable: ${cable} - Armario: ${armario}
</font>
</td>
</tr>
</table>

<table width=70% align=CENTER  bgcolor="#ff6600">
<tr>
<td width="30%"><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">Hora</I></font></td>
<td width="35%" align=center><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">Cable</I></font></td>
<td width="35%" align=center><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">Armario</I></font></td>
</tr>
</table>

<jsp2:forEach var="hor" items="${listaHoraCableArmario}">
	<table width=70% align=CENTER border=1>
	<tr>
	
		<td width="30%"><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">Hora: ${hor.hora} </I></font></td>
	
		<c:choose>
			 <c:when test="${hor.valor == '0'}">
			 
				<td width="35%" align=center><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">
					<img src="imagenes_sape/nocheck.jpg" width="13%" border="0">
					</I></font>
				</td>


				<td width="35%" align=center><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">
					<img src="imagenes_sape/nocheck.jpg" width="13%" border="0">
					</I></font>
				</td>
			 
			 </c:when>
			 <c:when test="${hor.valor == 'ARMARIO'}">
			 
				<td width="35%" align=center><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">
					<img src="imagenes_sape/nocheck.jpg" width="13%" border="0">
					</I></font>
				</td>
				<td width="35%" align=center><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">
					<img src="imagenes_sape/check.jpg" width="13%" border="0">
					</I></font>
				</td>
			 
			 </c:when>
			 <c:when test="${fn:containsIgnoreCase('CABLE', hor.valor)}">
			  
				<td width="35%" align=center><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">
					<img src="imagenes_sape/check.jpg" width="13%" border="0">
					</I></font>
				</td>

<!--fn:containsIgnoreCase('james stratchan', username)}" 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   -->
				<td width="35%" align=center><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">
					<img src="imagenes_sape/nocheck.jpg" width="13%" border="0">
					</I></font>
				</td>
			 
			 </c:when>
			<c:otherwise>
			
				<td width="35%" align=center><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">
					<img src="imagenes_sape/nocheck.jpg" width="13%" border="0">
					</I></font>
				</td>


				<td width="35%" align=center><font color="BLACK" face="Arial, Helvetica, sans-serif" size="2">
					<img src="imagenes_sape/nocheck.jpg" width="13%" border="0">
					</I></font>
				</td>	
			
			</c:otherwise>
		</c:choose>
		
	</tr>
	</table>
</jsp2:forEach>
<BR>
<table width=50% align=CENTER>
<TR><TD>
<center>
	<a href="javascript:parent.window.close()"><IMG onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('cerrar','','imagenes_sape/cerrar_u.gif',1)" name="cerrar" src="imagenes_sape/cerrar_d.gif" alt="Cerrar" border="0"></a>
</center>
</TD></TR>
</table>

</body>
</html>

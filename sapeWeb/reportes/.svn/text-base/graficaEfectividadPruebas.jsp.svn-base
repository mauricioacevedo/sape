<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="datos" class="java.lang.String" scope="request"/>
<jsp:useBean id="noBoton" class="java.lang.String" scope="request"/>
<jsp:useBean id="tipo" class="java.lang.String" scope="request"/>
<jsp:useBean id="modoGrafica" class="java.lang.String" scope="request"/>
<jsp:useBean id="queryString" class="java.lang.String" scope="request"/>
<jsp:useBean id="contador" class="java.lang.String" scope="request"/>
<jsp:useBean id="cola" class="java.lang.String" scope="request"/>
<!-- graficaEfectividadPruebas.jsp -->
<HTML>
<HEAD>
<TITLE>Grafica - SAPE</TITLE>
<script language="JavaScript" src="javascript/varios.js"> </script>
<script language="JavaScript">
function regresa() {

  if (navigator.appName == "Netscape")
      window.back();
        else
	    window.history.back();

}

function cerrar(){

	window.close();
}

function enviar(top){

location.href = "${pageContext.request.contextPath}/${queryString}&contador="+top;
}

</script>

</HEAD>
<body bgcolor="white" topmargin="0">
<center>
<c:if test="${modoGrafica == '3'}">
	<form name="filtr" action="javascript:enviar(filtr.tope.value);">

		<br>
		Mostrar Informacion con registros mayores o iguales a: <input type="text" name="tope" size="3" value="${contador}" maxlength="3">
		<!--input type="button" onclick="javascript:filtro(filtr.filt.value);" value="ir"-->
		<input type="button" value="ir" onclick="javascript:enviar(filtr.tope.value);">
	</form>
</c:if>
<table>
	<tr>
		<td>
			<applet archive="jcommon-0.8.8.jar,jfreechart-0.9.8.jar,GraphBarras.jar" code="grafico.GraphBarras" codebase="${pageContext.request.contextPath}/jars" width="700" height="495">
				<param name="datos" value="${datos}">
			</applet>
		</td>
	</tr>
	<tr>
		<td align="center">
			<font color="#ff5900" size="3px">Click derecho sobre la gr&aacute;fica para mas opciones</font>
		</td>
	</tr>
</table>	
<br>

<c:if test="${empty noBoton}">
<a name="salir" href="javascript:parent.window.close();">cerrar
<!--img onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('cerrar','','imagenes_sape/cerrar_u.gif',1)" name="cerrar" src="imagenes_sape/cerrar_d.gif" alt="Cerrar" border="0"--></a>
</c:if>
</center>
</body>
</HTML>

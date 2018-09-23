<!-- graficaTSTLI.jsp -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="datos" class="java.lang.String" scope="request"/>
<jsp:useBean id="tope" class="java.lang.String" scope="request"/>
<jsp:useBean id="tipe" class="java.lang.String" scope="request"/>
<jsp:useBean id="opcionGraficaArmarios" class="java.lang.String" scope="request"/>
<jsp:useBean id="listaLinks" class="java.util.ArrayList" scope="request"/>

<HTML>
<meta http-equiv="refresh" content="60;${pageContext.request.requestURL}?<% out.print(request.getQueryString()); %>">
<HEAD>
<TITLE>Grafica Barras - SAPE</TITLE>
<script language="JavaScript">
function regresa() {

  if (navigator.appName == "Netscape")
      window.back();
        else
	    window.history.back();
}

function filtro(valor){

	if(valor.length == 0){
		alert("Campo no valido.\nIngrese un valor numerico.");
		return false;
	}

	for (var i = 0; i < valor.length; i++) {
      oneChar = valor.charAt(i);
      if (oneChar < "0" || oneChar > "9") {
        window.alert("CAMPO Telefono NO VALIDO (solo numeros)");
        return false;
       }
     }

	 //location.href="${pageContext.request.contextPath}/indicadores/IndicadoresServlet?operacion=graficaTSTLI&tipo=armario_id&tope="+valor;
	 return true;

}


</script>

</HEAD>
<jsp:include page="../encabezado.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body bgcolor="white" marginwidth="0" marginheight="0">
<center>
	<c:if test="${opcionGraficaArmarios == 'true'}">
	<form name="filtr" action="${pageContext.request.contextPath}/actionSape?accion=indicadores" onSubmit="return filtro(filtr.filt.value);">
		<input type="hidden" name="operacion" value="graficaTSTLI">
		<input type="hidden" name="accion" value="indicadores">
		<input type="hidden" name="tipo" value="${tipo}">
		<br>
		Mostrar Informacion con registros mayores o iguales a: <input class="texto" type="text" name="tope" size="3" value="${tope}" maxlength="3">
		<!--input type="button" onclick="javascript:filtro(filtr.filt.value);" value="ir"-->
		<input class="boton" type="submit" value="ir">
	</form>
</c:if>
<br><br>
<table>
	<tr>
		<td>
			<!--applet archive="jcommon-0.8.8.jar,jfreechart-0.9.8.jar,GraphBarras.jar" code="grafico.GraphBarras" codebase="${pageContext.request.contextPath}/sape/jars" width="780" height="500">
				<param name="datos" value="${datos}">
			</applet-->
			<applet archive="jcommon-0.8.8.jar,jfreechart-0.9.8.jar,GraphBarras.jar" code="grafico.GraphBarras" codebase="${pageContext.request.contextPath}/jars" width="780" height="500">
				<param name="datos" value="${datos}">

				<c:set var="i" value="0"/>
				<c:forEach items="${listaLinks}" var="link">
					<param name="link${i}" value="${pageContext.request.requestURL}?accion=reportes&operacion=realizarConsultaTelefonosTSTLI&filtro=${tipo}&valorFiltro=${link}*_PARENT">
				<c:set var="i" value="${i+1}"/></c:forEach>
			</applet>
		</td>

	</tr>
	<tr>
		<td>
			<font color="#ff5900" size="3px">Click derecho sobre la gr&aacute;fica para mas opciones</font>
		</td>
	</tr>
</table>
<br>
<input class="boton" type="button" onclick="javascript:regresa();" value="regresar">
</center>

</body>
</HTML>

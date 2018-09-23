			<!-- pruebaProgramada.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% java.util.List listaDetalles = (java.util.List) request.getAttribute("listaDetalles"); %>
<%-- java.util.List listaTipos = (java.util.List) request.getAttribute("listaDetalles"); --%>
<% java.util.List estadistica = (java.util.List) request.getAttribute("estadistica"); %>
<jsp:useBean id="idPrueba" class="java.lang.String" scope="request"/>
<jsp:useBean id="tipo" class="java.lang.String" scope="request"/>
<jsp:useBean id="msgCentral" class="java.lang.String" scope="request"/>
<jsp:useBean id="estaProgramado" class="java.lang.String" scope="request"/>
<jsp:useBean id="labelTipoRutina" class="java.lang.String" scope="request"/>

<html>
<title>SAPE - Reporte de Pruebas Programadas</title>
<head>
<script language="JavaScript" src="javascript/common.js"> </script>
<script language="JavaScript">
<!--//

var req;

function Abre_ventanaDetallePrueba(idprueba) {
	this.window.focus();
	v600=window.open("${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono&operacion=mostrarResultados&ticket="+idprueba, 'window600','scrollbars=yes,resizable=yes,hotkeys=no,height=600,width=790,left=0,top=0,menubar=no,toolbar=no');
	v600.focus();
}

function find(forma) {

	var op = forma.opciones.options[forma.opciones.selectedIndex].value;

	if(op == '-1' || op == -1){
		return;
	}

	location.href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=consultaPruebasProgramadas&prueba="+op;

}


function enviarCalificacion(){	
	
	winCalificacion=window.open("${pageContext.request.contextPath}/actionSape?accion=rutinasArmario&operacion=enviarCalificacion&idRutina=${idPrueba}",'Calificacion SAPE','scrollbars=yes,resizable=yes,hotkeys=yes,height=570,width=600,left=0,top=0,menubar=yes,toolbar=no');
  	winCalificacion.focus();
	
}

//-->
</script>
</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body bgcolor="white" marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">
<br>
<%-- form name="forma1">
<table width="100%" cellspacing=0 cellpadding=0 border=0 align="center">
	<tr align="center">
		<td class="header-filtro" align="center">Prueba Programada: 
		<select name="opciones">
			<option value="-1">Seleccione</option>
			<c:forEach  items="${listaTipos}" var="tipoPrueba">
				<option value="${tipoPrueba}"${tipoPrueba == idPrueba ? 'selected' : ''}>${tipoPrueba}</option>
			</c:forEach>
		</select>
		&nbsp;&nbsp;&nbsp;
		<input type="button" class="boton" name="buscar" onClick="find(document.forma1)" value="Aceptar">
		&nbsp;&nbsp;&nbsp;
		<tags:ayudas pagina="Pruebaprogramada"/>
  	</td>
   </tr>
</table>
</form--%>


<table width="100%" border="0" BGCOLOR="black" align="center">
<tr>
	<td align="center" class="mensajeCentral">&nbsp;${msgCentral}&nbsp;</td>
</tr>
</table>

<c:if test="${tipo == 'detalles'}">
	<jsp:include page="ventanaDetallesRutinaPruebas.jsp" flush="true" />
</c:if>
<c:if test="${tipo == 'inicial'}">
	<jsp:include page="consultaInicialPruebaProgramada.jsp" flush="true" />
</c:if>
<br>
<c:if test="${labelTipoRutina == 'Armario'}">
<center><a href="javascript:enviarCalificacion();">Enviar Calificacion al OSS</a></center>
</c:if>
</body>
</html>

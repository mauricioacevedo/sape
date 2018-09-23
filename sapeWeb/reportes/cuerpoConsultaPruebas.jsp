					<!-- cuerpoConsultaPruebas.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="listaEventos" value="${requestScope.listaEventos}" />
<jsp:useBean id="msgCentral" class="java.lang.String" scope="request"/>
<jsp:useBean id="pagActual" class="java.lang.String" scope="request"/>
<jsp:useBean id="orderBy" class="java.lang.String" scope="request"/>

<P></P>

<table width="100%" border="0" BGCOLOR="black" align="center">
<tr>
	<td align="center" class="mensajeCentral"> ${msgCentral} <tags:ayudas pagina="Consultaprueba"/> </td>
</tr>
</table>

<jsp:include page="paginacion.jsp" flush="true" />

<script language="javascript" src="javascript/tooltip/domLib.js"></script>
<script language="javascript" src="javascript/tooltip/domTT.js"></script>
<style>@import url(javascript/tooltip/example.css);</style>
<script language="Javascript">
	var domTT_classPrefix = 'domTTOverlib';
 
	function toolTip(padreEvento, evento,codv,desc) {
		var titulo = "";
	    var contenido = " Codigo Ver: "+codv+"<br> Descripcion: "+desc;
	    return mostrarDivAyuda(padreEvento, evento,'',contenido);
	}
//-->
</script>

<table width="100%" border="0" align="center">
<tr>
	<td width="10%" height="30" align="center" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'ticket DESC'}">
			<span title="Filtrar por PRUEBA Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'ticket ASC')">Prueba</a></span>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por PRUEBA Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'ticket DESC')">Prueba</a></span>
		</c:otherwise>
	</c:choose>
	
	</td>
	<td width="10%" height="30" align="center" class="header-reporte">
		
	<c:choose>
		<c:when test="${orderBy == 'telefono DESC'}">
			<a class="header-filtro" title="Ordenar por TELEFONO Ascendentemente" href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'telefono ASC')">Tel&eacute;fono</a>
	 	</c:when>
	 	<c:otherwise>
			<a title="Ordenar por Telefono Descendentemente" class="header-filtro" href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'telefono DESC')">Tel&eacute;fono</a>
		</c:otherwise>
	</c:choose>
		
	</td>
	<td width="15%" height="30" align="center" class="header-reporte">
		
	<c:choose>
		<c:when test="${orderBy == 'codv DESC'}">
	 		<font color=white>
			<span title="Filtrar por CODIGO VER Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'codv ASC')">Codigo Ver</a></span></font>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por CODIGO VER Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'codv DESC')">Codigo Ver</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
	<td width="10%" height="30" align="center" class="header-reporte">
		
	<c:choose>
		<c:when test="${orderBy == 'tipoPrueba DESC'}">
			<font color=white>
			<span title="Filtrar por TIPO PRUEBA Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipoPrueba ASC')">Tipo Prueba</a></span></font>
		</c:when>
		<c:otherwise>
			<span title="Filtrar por TIPO PRUEBA Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'tipoPrueba DESC')">Tipo Prueba</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
	<td width="10%" height="30" align="center" class="header-reporte">
	
	<c:choose>
		<c:when test="${orderBy == 'central DESC'}">
	 		<font color=white>
			<span title="Filtrar por CENTRAL Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'central ASC')">Central</a></span></font>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por CENTRAL Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'central DESC')">Central</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
	<td width="10%" height="30" align="center" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'usuario DESC'}">
	 		<font color=white>
			<span title="Filtrar por USUARIO Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'usuario ASC')">Usuario</a></span></font>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por USUARIO Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'usuario DESC')">Usuario</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
	
	<td width="20%" height="30" align="center" class="header-reporte">
	<c:choose>
		<c:when test="${orderBy == 'fecha DESC'}">
	 		<font color=white>
			<span title="Filtrar por FECHA Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fecha ASC')">Fecha</a></span></font>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por FECHA Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'fecha DESC')">Fecha</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
	
	<td width="10%" height="30" align="center" class="header-reporte">
	
	<c:choose>
		<c:when test="${orderBy == 'duracion DESC'}">
	 		<font color=white>
			<span title="Filtrar por DURACION Ascendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'duracion ASC')">Duraci&oacute;n</a></span></font>
	 	</c:when>
	 	<c:otherwise>
			<span title="Filtrar por DURACION Descendentemente" class="header-filtro"><a href="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'duracion DESC')">Duraci&oacute;n</a></span>
		</c:otherwise>
	</c:choose>
		
	</td>
</tr>
<c:set var="i" value="0" />
<c:forEach items="${listaEventos}" var="evento">
	<tr class="row${i%2 == 0? 0 : 1}">
		<TD height="20" align="CENTER"><a name="Idprueba" href=javascript:Abre_ventanaDetallePrueba('${evento.ticket}'); title="Consultar Evento">${evento.ticket}</a></td>
		<TD height="20" align="CENTER">${evento.telefono}</td>
		<c:set var="codv" value="${evento.codigoVer}" />
		<c:if test="${fn:startsWith(evento.codigoVer, 'C.')}"><c:set var="codv" value="${fn:split(codv, '.')[1]}" /></c:if>
		<TD height="20" align="CENTER" onmouseover="return toolTip(this, event,'${evento.codigoVer}','${evento.descripcionCodv}');"><!-- span title="Codigo de prueba: ${evento.codigoVer}.">${codv}</span-->${codv}</td>
		<TD height="20" align="CENTER">${evento.tipoPrueba}</td>
		<TD height="20" align="CENTER">${evento.central}</td>
		<TD height="20" align="CENTER">${evento.usuario}</td>
		<TD height="20" align="CENTER"><fmt:formatDate value= "${evento.fecha}" type="both" pattern="dd-MM-yy HH:mm:ss" /></td>
		<TD height="20" align="CENTER"><fmt:formatNumber value="${evento.duracion}" maxFractionDigits="2" minFractionDigits="2" /> seg</td>
	</tr>
	<c:set var="i" value="${i +1}"/>
</c:forEach>
</table>

<table width="100%" border="0" BGCOLOR="black" align="center">
<tr>
	<td class="fin-reporte">Fin de reportes</td>
</tr>
</table>
<br>
<center>
	<form name="informes">
		 &nbsp;Exportar a: &nbsp;
		<select name="formatos" >
		<option value="pdf">PDF</option>
		<option value="csv">CSV</option>
		<option value="xls">XLS</option>
		</select>&nbsp;
		<input name="informe" class="boton" onClick="javascript:informeGen(document.informes,'${pagActual}',document.formaPaginacion.regXPag.value,'${orderBy}');" type="button" value="Aceptar">
	</form>	
</center>
<br>

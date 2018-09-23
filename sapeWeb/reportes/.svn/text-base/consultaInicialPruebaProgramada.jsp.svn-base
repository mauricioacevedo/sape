			<!-- consultaInicialPruebaProgramada.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% java.util.List listaDetalles = (java.util.List) request.getAttribute("listaDetalles"); %>

<%-- OJO, asumo que la rutina es por Serie cuando el parametro estadistica venga vacio --%>
<% java.util.List estadistica = (java.util.List) request.getAttribute("estadistica"); %>

<jsp:useBean id="idPrueba" class="java.lang.String" scope="request"/>
<jsp:useBean id="tipo" class="java.lang.String" scope="request"/>
<jsp:useBean id="estaProgramado" class="java.lang.String" scope="request"/>
<jsp:useBean id="labelRutina" class="java.lang.String" scope="request"/>
<jsp:useBean id="labelTipoRutina" class="java.lang.String" scope="request"/>

<c:set var="datos" value="Estadistica para rutina ${labelTipoRutina} ${labelRutina}*CodigoVer*Valor"/>



<c:forEach items="${listaDetalles}" var="detalle">
	<c:set var="datos" value="${datos}* ${detalle[1]}*${detalle[0]}"/>
</c:forEach>


<script language="JavaScript">
<!--//
	function Abre_ventanaDetalleCodv(codv) {
		this.window.focus();
		v600=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=estadisticoPruebasProgramadas&prueba=${idPrueba}&codv="+codv+"&modo=consultaPorCodigoVer", 'window600','scrollbars=yes,resizable=yes,hotkeys=no,height=600,width=650,left=0,top=0,menubar=no,toolbar=no');
		v600.focus();
	}

	function mostrarGrafica(){
		v900=window.open("${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=mostrarGraficaEfectividad&datos=${datos}", 'window900','scrollbars=yes,hotkeys=yes,height=600,width=800,left=100,resizable=yes,top=50,menubar=yes,toolbar=no');
		v900.focus();

	}

	function actualizarEstados(formas){
		
		
		<c:if test="${estaProgramado == 'no'}">
		if(confirm("Desea habilitar la rutina de nuevo ? Recuerde:"+
				"\n1. La informacion del actual reporte se eliminara."+
				"\n2. Debe cambiar la fecha de la rutina a una futura.")){
			location.href = "${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=habilitarRutina&idRutina=${idPrueba}";
			return;			
		}
		</c:if>
		
		var forma = document.getElementById("forma2");

		var cantTel = forma.chkTel.length;
		var codv = "";

		var i = -1;

		for (j = 0; j < cantTel; j++ ) {
			if(forma.chkTel[j].checked){
				codv = forma.chkTel[j].value;
				i = 2;
			}
		}
		if(i  == -1){
			// OJO ke puede ke solo halla un estado
			if(forma.chkTel.checked){
				codv = forma.chkTel.value;
			} else {
				alert("Debe seleccionar almenos un registro para realizar la Actualizacion.");
				return;
			}
		}

	if(confirm('A continuacion se actualizaran todos los registros'+
		'\nde la prueba ${idPrueba} con Codigo Ver \''+codv+'\'\npara que sean probados nuevamente.'+
		'\nEsta seguro?')){

			location.href = "${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=estadisticoPruebasProgramadas&prueba=${idPrueba}&modo=actualizarEstadoPruebasProgramadas&codv="+codv;
		}
	}


//-->
</script>
<br>
<center><h2>Prueba Programada ${labelTipoRutina}: <font color="Red" size="+1">${labelRutina}</font></h2></center>


<c:choose>
<c:when test="${estaProgramado == 'no'}">
<center>Nota: La rutina no esta programada, para habilitarla nuevamente de click en 'Habilitar Rutina'</center>
</c:when>
<c:otherwise>

<%--table align="center" border="0" cols="10" width="${fn:length(estadistica)*10 +25}%" cellspacing="0">

<tr>

<c:set var="todos" value="0"/>

<c:if test="${not empty estadistica}">
<c:forEach items="${estadistica}" var="linea" >
	<td align="center">
		${linea[1] == 'II'?"Por Realizar":""}${linea[1] == 'ET'?"Realizados":""}${linea[1] == 'PI'?"Prueba Iniciada":""} ${linea[0]}
		<c:set var="todos" value="${todos + linea[0]}"/>
	</td>
</c:forEach>
	<td align="center">Total ${todos}</a>&nbsp;</td>
</c:if>

</tr>
</table--%>
</c:otherwise>
</c:choose>
<br>

<form name="forma2" id="forma2">

<table width="60%" align="center">

	<tr>
		<td align="center" class="graficaLink"><a href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=estadisticoPruebasProgramadas&prueba=${idPrueba}&modo=detallesPruebasProgramadas">Ver detalles</a></td>
		<td align="center" class="graficaLink">
		<%--c:if test="${labelTipoRutina != 'Serie'}"--%><%-- las series no se dejan inicializar --%>
		<span title="Actualizar los registros para la Prueba Programada ${idPrueba}"><a href="javascript:actualizarEstados(document.forma2);">${estaProgramado == 'no'?'Habilitar Rutina':''}${estaProgramado != 'no' && labelTipoRutina != 'Serie'?'Inicializar Estados':''}</a></span>
		<%--/c:if--%>
		</td>
		<td align="center" class="graficaLink"><a href="javascript:mostrarGrafica();">Grafica</a></td>
	</tr>

</table>

<table width="60%" align="center">

<tr class="header-reporte">

	<td align="center">
		Codigover
	</td>
	<td align="center">
		Cantidad
	</td>
	<td align="center">
		Porcentaje
	</td>
	<c:if test="${estaProgramado == 'yes' && labelTipoRutina != 'Serie'}">
	<td align="center">
		Opcion
	</td>
	</c:if>
</tr>

<c:set var="total" value="0"/>
<set var="totalPorciento" value="0" />
<c:forEach items="${listaDetalles}" var="detalle">
	<c:set var="total" value="${total + detalle[0]}"/>
</c:forEach>

<c:set var="i" value="0" />
<c:forEach items="${listaDetalles}" var="detalle">
<tr class="row${i%2 == 0? 0 : 1}">
	<td align="center"><a href="javascript:Abre_ventanaDetalleCodv('${detalle[1]}');">${detalle[1]}</a></td>
	<td align="center">${detalle[0]}</td>
	<td align="center"><fmt:formatNumber value="${detalle[0]/total}" type="percent" pattern="##.###%"/></td>

	<c:if test="${estaProgramado == 'yes' && labelTipoRutina != 'Serie'}">
	<td align="center"><input name="chkTel" type="radio" value="${detalle[1]}"></td>
	</c:if>
	<c:set var="totalPorciento" value="${totalPorciento + detalle[0]/total}"/>
</tr>
<c:set var="i" value="${i + 1}" />
</c:forEach>
<tr class="fin-reporte"
	<td align="center">TOTAL</td>
	<td align="center">${total}</td>
	<td align="center"><fmt:formatNumber value="${totalPorciento}" type="percent" pattern="##.###%"/></td>
	<c:if test="${estaProgramado == 'yes' && labelTipoRutina != 'Serie'}">
	<td>&nbsp;</td>
	</c:if>
</tr>
</table>
</form>
<br>

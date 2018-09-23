				<!--inicioIndicadoresColas.jsp-->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="jsp2" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:useBean id="listaColas" class="java.util.ArrayList" scope="request"/>
<html>
<head><title>Sistema Administrativo de Pruebas Extendidas - SAPE</title>
</head>
<script language="JavaScript">

function grafica(forma){
var valor2= "";

	var form = document.forma;
	var cantColas = form.chkCola.length;

	for (j = 0; j < cantColas; j++) {
		if(form.chkCola[j].checked){
		valor2 = form.chkCola[j].value;
		}
	}

if(valor2 == ""){
window.alert("Debe seleccionar almenos una COLA para realizar la grafica.");
return false;
}

//location.href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=indicadorColas&colas="+colas+"&estado="+valor;
return true;
}


function seleccionarColas(checkBox) {
	var form = document.forma;
	var estado = form.chkTodas.checked;
	var cantColas = form.chkCola.length;
	for (i = 0; i < cantColas; i++) {
		form.chkCola[i].checked = estado;
	}
}

</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<jsp:include page="../encabezado.jsp" flush="true" />
<body bgcolor="WHITE">
<br>
<br>
<table width="70%" class="mensajeCentral" align="center">
<tr>
	<td align="center">INDICADORES DE GESTION DE COLAS <tags:ayudas pagina="Gestioncolas"/></td></tr>
</table>
<br>
<form name="forma" action="${pageContext.request.contextPath}/actionSape?accion=indicadores">
<input type="hidden" name="operacion" value="graficaIndicadoresColas">
<input type="hidden" name="accion" value="indicadores">
<table align="center">
  <tbody>
    <tr>
      <td colspan="3" align="center"><font size="+1"><b>Seleccione estado</b></font></td>
    </tr>
    <tr>
      <td>Cumplido.
<input type="radio" name="radioEstado" value="CUMPL">
	 </td>
      <td>Pendiente.
<input type="radio" name="radioEstado" value="PENDI">
	  </td>
      <td>Ambos
	  	<input type="radio" name="radioEstado" value="AMBAS" checked="true">  
	  </td>

    </tr>
  </tbody>
</table>

<br><br><center>
<font size="+1"><b>Seleccione la cola</b></font>
</center>
<center>
<br>
<table class="header-reporte">
  <tbody>
    <tr>
      <td align="center" width="150">Nombre    </td>
      <td align="center" width="200">Descripcion </td>
	  <td align="center" width="200">Seleccionar (<b>Todas</b> <input type="checkbox" name="chkTodas" onClick="seleccionarColas(this);">)</td>
    </tr>
	</table>
	<table border="1">
<jsp2:forEach items="${listaColas}" var="cola">    
	<tr>
      <td align="center" width="150">&nbsp; ${cola.nombre} :   </td>
	  <td align="center" width="200">&nbsp;${cola.descripcion}</td>
	  <td align="center" width="200"><center><input type="checkbox" name="chkCola" value="${cola.nombre}" align="center"></center></td>
    </tr>
</jsp2:forEach>
  </tbody>
</table>


</center>
<br>

<center>
<input class="boton" type="submit" onClick="return grafica(document.forma);" name="mostrarIndicador" value="mostrar" >
<br>
<br>
<br>
<a href="${pageContext.request.contextPath}/actionSape?accion=indicadores&operacion=mantenimiento">Mantenimiento de Colas</a>
</center>
</form>



</body></html>

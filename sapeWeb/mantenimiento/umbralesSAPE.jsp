<!-- umbrealesSAPE.jsp -->


<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="nombresPropiedades" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="propiedades" class="java.util.Properties" scope="request"/>

<html>
<head>
<title>SAPE - Umbrales del Sistema</title>
<script language="JavaScript">
<!--

function cerrar() {
		parent.window.close();
		die;
	}


	function enviarUpdate(comentario,numeroGrupo){
		var query = "";
		var contar = 0;
		var nombres = "";
		<c:forEach items="${nombresPropiedades}" var="nombre">

			var tmp = document.getElementById("${nombre}"+numeroGrupo);

			if ( tmp != null ) {

				contar = contar + 1;
				query += "&${nombre}="+tmp.value;
				nombres += "${nombre},";
			}

			//query += "&${nombre}="+tmp.value;

		</c:forEach>

		if(confirm("Se actualizara el grupo de datos \n \""+comentario+"\"\n Esta Seguro?")){
location.href='${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=guardarPlantillaGudeConf'+query+"&nombres="+nombres+"&comentario="+comentario;
		}

	}



-->
</script>

<!-- ${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=umbralesSAPE -->
<style type="text/css">

 td { color: black; font-family: "Arial, Helvetica, sans-serif"; font-size: 10pt }

 .header-reporte { font-weight: bold; font-size: 12; color: black }
 .mensajeCentral { font-weight: bold; font-size: 14; text-decoration: none; color: white}

 .row0 {background-color: #e7c366;}
 .row1 {background-color: #e7d29e;}

</style>

<style type="text/css">
 .linkAyuda a:link { font-weight: bold; font-size: 14; text-decoration: none; color: orange }
 .linkAyuda a:visited { font-weight: bold; font-size: 14; text-decoration: none; color: orange }
 .linkAyuda a:hover {color: red}
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body bgcolor="white">
<br><br>

<form name="datos">
<table align="center" width="60%" >
<tr bgcolor="black">
	<td colspan="5" align="center"><font class="mensajeCentral" color="WHITE"> Umbrales del Sistema <tags:ayudas pagina="Umbrales"/></font></td>
</tr>
<tr>
<td colspan="3" height="20"></td>
</tr>

<tr bgcolor="black">
	<td width="60%" height="20" align="center" class="header-reporte">
		<font color="white">Propiedad</font>
	</td>
	<td width="40%" height="20" align="center" class="header-reporte" colspan="2">
		<font color="white">Valor</font>
	</td>
</tr>

<c:set var="i" value="0" />
<c:set var="j" value="0" />
<c:forEach items="${nombresPropiedades}" var="prop">
<tr class="row${i % 2 == 0?'0':'1'}">
	<c:choose>
		<c:when test="${fn:startsWith(prop, 'comentario')}">
		<td colspan="2" bgcolor="black" align="center"><font color="white"><b>${propiedades[prop]}</b></font></td>
		<c:set var="j" value="${j+1}" />
		<c:set var="i" value="${i-1}"/>
		<td align="right" bgcolor="black"><input class="boton" type="button" value="actualizar" onclick="javascript:enviarUpdate('${propiedades[prop]}','${j}');"></td>

		<!--input id="${prop}" type="hidden" value="${propiedades[prop]}"-->

		</c:when>
		<c:otherwise>
		<td align="center">${prop}</td>
		<td align="center" colspan="2"><input class="texto" id="${prop}${j}" type="text" name="${prop}" value="${propiedades[prop]}" size="25">
		</c:otherwise>
	</c:choose>
	</td>
	<c:set var="i" value="${i+1}"/>
</tr>
</c:forEach>

</table>
<br>
<!--center><input type="button" value="aceptar" onClick="javascript:validarDatos();"></center-->

</body>
</html>

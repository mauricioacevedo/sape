<!-- resultadoImporteMasivos.jsp -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% java.util.List listaBuenos = (java.util.List) request.getAttribute("listaBuenos"); %>
<% java.util.List listaNegados = (java.util.List) request.getAttribute("listaNegados"); %>

<jsp:useBean id="totalClientes" class="java.lang.String" scope="request"/>
<jsp:useBean id="msg" class="java.lang.String" scope="request"/>
<jsp:useBean id="estado" class="java.lang.String" scope="request"/>


<html>
<head><title>SAPE - Importar Clientes Masivo</title></head>

<script language="JavaScript">

	function guardar() {
		// en la session esta guardada la lista de los registros buenos!!!!
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=guardarInformacionImporteMasivos"; 
	}

</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<body topmargin="0" leftmargin="0">
<br>
<center><h2>Importe Masivo</h2></center>
<br>
<center>Resultados.</center>

<c:set var="procesables" value="" scope="request"/>
<table width="100%">
	<tr bgcolor="black">
	<td width="50%" align="center"><font color="White" size="4"><b>Aceptados</font></td>
	<td width="50%" align="center"><font color="White" size="4"><b>Rechazados</font></td>
	</tr>
	<tr>
	<td width="50%" align="center">
	<select id="listaTelefonos" size="20" multiple="multiple">
		<c:forEach items="${listaBuenos}" var="bueno">
			<option>${bueno[0]}&nbsp;&nbsp;${bueno[1]}</option>
		</c:forEach>
	</select>
	</td>
	<td width="50%" align="center">
		<select id="listaTelefonos" size="20" multiple="multiple">
			<c:forEach items="${listaNegados}" var="negado">
				<option>${negado[0]}&nbsp;&nbsp;${negado[1]}</option>
			</c:forEach>
		</select>
	</td>
	</tr>
</table>

<br>
<center>
<input name="acepta" onclick="javascript:guardar()" value="aceptar" type="button" class="boton">
&nbsp;&nbsp;
<input name="Cerrar" onclick="javascript:window.close();" value="cancelar" type="button" class="boton">
</center>
</body>

</html>

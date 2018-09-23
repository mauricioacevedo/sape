<!--			COMIENZA ENCABEZADO		-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<br>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<script language="JavaScript">
	function operacionRutinas() {
		var level = '${sessionScope.usuario.nivel}';

/*		if (level == '1') {
			var winUser=window.open("${pageContext.request.contextPath}/actionSape?accion=rutinas", 'window900','scrollbars=yes,resizable=yes,hotkeys=yes,height=600,width=600,left=100,top=50,menubar=yes,toolbar=no');
			winUser.focus();
		} else {*/
			location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas"
		//}
	}
</script>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="10">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="410" colspan="3" align="center">
						<c:if test="${not empty applicationScope.mantenimientoSistema}">
						<font color="red"><strong>SISTEMA EN MANTENIMIENTO</strong></font>
						</c:if>
						</td>
						<td width="6"><img src="imagenes_sape/encabezado/borde_inicial_usuario.gif"></td>
						<td bgcolor="#ff6600" width="70">Usuario:</td>
						<td width="100" align="center" background="imagenes_sape/encabezado/borde_interno_usuario.gif">${sessionScope.usuario.nick}</td>
						<td bgcolor="#ff6600" width="70">&nbsp;&nbsp;Ingreso:</td>
						<td width="80" align="center" background="imagenes_sape/encabezado/borde_interno_usuario.gif"><fmt:formatDate value= "${sessionScope.visita.fechaIngreso}" type="both" pattern="HH:mm:ss"/></td>
						<td width="50" bgcolor="black" align="right" class="encabezado"><font color="white"><a href="${pageContext.request.contextPath}/actionSape?accion=salir">Salir</a>&nbsp;&nbsp;</font></td>
						<td><img src="imagenes_sape/encabezado/borde_fin_usuario.gif"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="150" background="imagenes_sape/encabezado/borde_izquierdo.gif"></td>
			<td colspan="2"><a href="#"><img src="imagenes_sape/encabezado/encabezado_sape.gif" border="0"></a></td>
			<td width="100" align="center" background="imagenes_sape/encabezado/borde_central.gif" class="menu">
			<a href="${pageContext.request.contextPath}/actionSape?accion=pruebaTelefono">Pruebas</a>
			</td>
			<td width="100" align="center" background="imagenes_sape/encabezado/borde_central.gif" class="menu">
			<a href="javascript:operacionRutinas();">Rutinas</a>
			</td>
			<td width="100" align="center" background="imagenes_sape/encabezado/borde_central.gif" class="menu">
			<a href="${pageContext.request.contextPath}/actionSape?accion=mantenimiento">Mantenimiento</a>
			</td>
			<td width="100" align="center" background="imagenes_sape/encabezado/borde_central.gif" class="menu">
			<a href="${pageContext.request.contextPath}/actionSape?accion=reportes&operacion=inicio">Reportes</a>
			</td>
			<td width="100" align="center" background="imagenes_sape/encabezado/borde_central.gif" class="menu">
			<a href="${pageContext.request.contextPath}/actionSape?accion=indicadores">Indicadores</a>
			</td>
			<td width="19" align="center"><img src="imagenes_sape/encabezado/cierre_borde_1.gif" border="0"></td>
			<td width="*" background="imagenes_sape/encabezado/cierre_marco.gif">&nbsp;</td>
		</tr>
		<tr>
			<td width="150">&nbsp;</td>
			<td width="65"><img src="imagenes_sape/encabezado/encabezado_logo_osp.gif" border="0"></td>
			<td width="66"><img src="imagenes_sape/encabezado/encabezado_logo_xplora.gif" border="0"></td>
			<td width="*" colspan="7">&nbsp;</td>
		</tr>
</table>

<!--			FIN ENCABEZADO		-->

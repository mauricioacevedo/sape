		<!-- paginacion.jsp -->
<%--
	Este jsp provee la funcionalidad de realizar la paginacion de un reporte cualquiera.
	Se debe sobreescribir un metodo  irAPagina(paginaDestino, cantidadRegistros, order)
	en el jsp que incluya este.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="regPorPagina" class="java.lang.String" scope="request"/>
<jsp:useBean id="pagActual" class="java.lang.String" scope="request"/>
<jsp:useBean id="totalPaginas" class="java.lang.String" scope="request"/>
<jsp:useBean id="orderBy" class="java.lang.String" scope="request"/>


<!-- DATOS:  regPorPagina=${regPorPagina},pagActual=${pagActual},totalPaginas=${totalPaginas},orderBy=${orderBy} -->

<form name="formaPaginacion" action="javascript:irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'${orderBy}')">
<input type="hidden" name="paginaActual" value="1">
<table width="100%" >
  <tbody>
    <tr>
      <td> <input type="button" class="boton" name="cmdComando" value="<<" onclick="irAPagina('1',document.formaPaginacion.regXPag.value,'${orderBy}')"  ${pagActual == '1' || empty pagActual?'disabled':''}> </td>
      <td> <input type="button" class="boton" name="cmdComando" value="<" onclick="irAPagina('${pagActual -1}',document.formaPaginacion.regXPag.value,'${orderBy}')" ${pagActual == '1' || empty pagActual?'disabled':''}> </td>
      <td> <input type="button" class="boton" name="cmdComando" value=">" onclick="irAPagina('${pagActual +1}',document.formaPaginacion.regXPag.value,'${orderBy}')" ${pagActual == totalPaginas ? 'disabled' : ''}${totalPaginas == '0'?'disabled':''} > </td>
      <td> 
		<input type="button" class="boton" name="cmdComando" value=">>" onclick="irAPagina('${totalPaginas}',document.formaPaginacion.regXPag.value,'${orderBy}')" ${pagActual == totalPaginas ? 'disabled':''}${totalPaginas == '0'?'disabled':''}> </td>
      <td>
	  	Registros/P&aacute;gina:
			<input type="text" class="texto" name="regXPag" size="3" value="${regPorPagina}" >
			<input type="button" class="boton" name="cmdRegistrosPagina" value="Aceptar" onclick="irAPagina('${pagActual}',document.formaPaginacion.regXPag.value,'${orderBy}')" >
	  </td>
	  <td> Ir a la P&aacute;gina: 
	  	<select name="irPagina" onchange="javascript:irAPagina(document.formaPaginacion.irPagina.options[document.formaPaginacion.irPagina.selectedIndex].value,document.formaPaginacion.regXPag.value,'${orderBy}')">
			<c:forEach begin="1" end="${totalPaginas}" step="1" var="num" varStatus="status">
				<option value="${num}" ${status.count == pagActual?'selected':''}>${num}</option>
			</c:forEach>
		</select> 
	  </td>
    </tr>
  </tbody>
</table>
</form>

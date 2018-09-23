<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="categoria" scope="request" class="com.osp.sape.maestros.Categorias" />

<html>

<style type="text/css">

 .header-reporte { font-weight: bold; font-size: 12; color: black }
 .mensajeCentral { font-weight: bold; font-size: 14; text-decoration: none; color:
 
 .row0 {background-color: #e7c366;}
 .row1 {background-color: #e7d29e;}
 
</style>

<head><title>SAPE - Categorias</title></head>
<body bgcolor="white">
	<br>
	<table width="60%" align="center" border="1">
		<tr bgcolor="Black">
		<td colspan="2" align="center">
			<font color="white" size="+1"><b>Categorias</b></font>
		</td>
				
		</tr>
		<tr>
			<td width="50%" align="center"class=".header-reporte">Prueba</td>
			<td align="center">${categoria.id}</td>
		</tr>
		<tr>
			<td align="center" class=".header-reporte">Codigo Ver</td>
			<td align="center">${categoria.codigoVer}</td>
		</tr>
		<tr>
			<td align="center" class=".header-reporte">Estado</td>
			<td align="center">${categoria.estado}</td>
		</tr>
		
		<tr>
			<td valign="top" align="center" class=".header-reporte">Categorias</td>
			<td align="center">
			<table width="100%" height="100%">
			<c:set var="i" value="0" />
			<c:forEach items="${categoria.categoriasList}" var="cat">
				<c:set var="row" value="${i%2 == 0? 0: 1}"/>
				<tr class="row${row}">
					<td align="center">${cat}</td>
				</tr>
				<c:set var="i" value="${i +1}"/>
			</c:forEach>
			</table>

			</td>
		</tr>
	</table>
</body>
</html>
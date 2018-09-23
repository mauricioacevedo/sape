<!-- contenidoConfiguracionADSL.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="listaConfiguraciones" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="central" class="java.lang.String" scope="request"/>
<jsp:useBean id="cabezaId" class="java.lang.String" scope="request"/>


<%--
La plantilla tiene un manejo algo 'dinamico' con respecto a la informacion que maneja.
la manera de tomar la plantilla es la siguiente:

1.	Dentro de listaConfiguraciones viene definida la informacion para cada tipo de configuracion
	(PPPoE, PPPoA, Bridge y IPoA).
2.	Por cada una de estas configuraciones se traen parametros, informacion que el bean maneja, y que
	con cierta logica se despliegan en el jsp.
3.	Por ejemplo en una iteracion tenemos:
	* 	configuracion.values : es una lista con los datos para validacion e identificacion de cada
		configuracion. Esta lista trae en cada posicion un vector con datos que se utilizan para desplegar
		el nombre, desplegar la informacion contenida y validar la informacion que se va a enviar al servidor.

 --%>

<c:forEach items="${listaConfiguraciones}" var="configuracion">

<table width="60%" align="center">
<tr bgcolor="Black">
	<td colspan="2" align="center"><font color="White">Configuracion  ${configuracion.nombreConfiguracion}</font></td>
</tr>
</table>
<table width="60%" align="center" border="1" cellspacing="0" cellpadding="0">
	<c:forEach items="${configuracion.values}" var="datos">
		<tr>
		<td align="center" width="50%">${datos[3]}</td>
		<td align="center" width="50%">
		<c:choose>
		<c:when test="${not empty datos[2] and fn:startsWith(datos[2], '[COMBO]')}">
			<%-- Este campo tiene pocos valores y se debe manejar como un combobox --%>
			<c:set var="combo" value="${fn:substringAfter(datos[2], '[COMBO]')}"/>
			<select name="${datos[0]}" id="${datos[0]}">
				<option value="seleccione" >Seleccione:</option>
			<c:forEach items="${fn:split(combo, ',')}" var="cond">
				<option value="${cond}" ${datos[1] == cond?"selected":""}>${cond}</option>
			</c:forEach>
			</select>
		</c:when>
		<c:when test="${not empty datos[2] and fn:startsWith(datos[2], '[RANGE]')}">
			<%-- Este campo es numerico y tiene que validarse entre un rango de valores --%>

			<input class="texto" id="${datos[0]}" type="text" value="${datos[1]}" size="10">

		</c:when>
		<c:otherwise> 
		<input class="texto" id="${datos[0]}" type="text" value="${datos[1]}" size="10">
		</c:otherwise>
		</c:choose>
		</td>
		</tr>
	</c:forEach>
	<tr>

		<td align="center">
			<input class="boton" type="button" value="Guardar" onclick="javascript:procesar${configuracion.nombreConfiguracion}('guardar');">
		</td>
		<td align="right">

			<input class="boton" type="button" value="Leer de Cabeza" onclick="javascript:procesar${configuracion.nombreConfiguracion}('loadInfoCabeza');">
			&nbsp;&nbsp;			
			<input class="boton" type="button" value="Configurar Cabeza" onclick="javascript:procesar${configuracion.nombreConfiguracion}('saveInfoCabeza');">
		</td>
	</tr>
</table>
<br>
	<script language="JavaScript">
	
	<%--Este javascript procesa la informacion de la pantalla y realiza operaciones de
		guardar configuracion(En la db) (accion=guardar), cargar configuracion desde la cabeza (accion=loadInfoCabeza) y enviar
		informacion a la cabeza (accion=saveInfoCabeza) --%>
	
		function procesar${configuracion.nombreConfiguracion}( accion ){
			var query = "";
			
			if (accion != 'loadInfoCabeza'){
			
				<c:choose>
					<c:when test="${configuracion.nombreConfiguracion == 'PING'}">
						var ti = document.getElementById('tipo');
						var tipo = ti.options[ti.selectedIndex].value;
						var vci = document.getElementById('vci').value;
						if(vci == ""){
							alert('Ingrese un valor para el campo vci');
							document.getElementById('vci').focus();return;
						}
						var vpi = document.getElementById('vpi').value;
						if(vpi == ""){
							alert('Ingrese un valor para el campo vpi');
							document.getElementById('vpi').focus();return;
						}
						var ip = document.getElementById('ip').value;
						query += "&tipo="+tipo+"&vci="+vci+"&vpi="+vpi+"&ip="+ip;
						
						
						if(tipo == 'PPPoE' || tipo == 'PPPoA'){
							var localIpp = document.getElementById('localIp');
							var locIp = localIpp.options[localIpp.selectedIndex].value;
							
							if(locIp == "seleccione"){
								alert("Seleccione un valor para el campo localIp");
								localIpp.focus();
								return;
							}

							var login = document.getElementById('login').value;
							var domain = document.getElementById('domain').value;
							var password = document.getElementById('password').value;
							
							query += "&localIp="+locIp+"&login="+login+"&password="+password;
							
							if(tipo == 'PPPoA'){
								var encapType = document.getElementById('encapType');
								var da = encapType.options[encapType.selectedIndex].value;
	
								if(da == "seleccione"){
									alert("Seleccione un valor para el campo encapType");
									encapType.focus();
									return;
								}
								
								query += "&encapType="+da;
							}
							
						}else{
							
							var gateway = document.getElementById('gateway');
							query += "&gateway="+gateway.value;
	
							var subMask = document.getElementById('subMask');
							query += "&subMask="+subMask.value;
							
							if(tipo == 'Bridge'){
							
								var encapType = document.getElementById('encapType');
								var da = encapType.options[encapType.selectedIndex].value;
	
								if(da == "seleccione"){
									alert("Seleccione un valor para el campo encapType");
									encapType.focus();
									return;
								}
								query += "&encapType="+da;
							

								var lanType = document.getElementById('lanType');
								var da = lanType.options[lanType.selectedIndex].value;
	
								if(da == "seleccione"){
									alert("Seleccione un valor para el campo lanType");
									lanType.focus();
									return;
								}
								query += "&lanType="+da;

								var fcsCont = document.getElementById('fcsCont');	
								var da = fcsCont.options[fcsCont.selectedIndex].value;
								if(da == "seleccione"){
									alert("Seleccione un valor para el campo fcsCont");
									fcsCont.focus();
									return;
								}
	
								query += "&fcsCont="+da;
							}
						}
						
					</c:when>
					<c:otherwise>
						<c:forEach items="${configuracion.values}" var="datos">
						var ${datos[0]} = document.getElementById('${datos[0]}');
		
						<c:choose>
						<c:when test="${not empty datos[2] and fn:startsWith(datos[2], '[COMBO]')}">
							var da = ${datos[0]}.options[${datos[0]}.selectedIndex].value;
		
							if(da == "seleccione"){
								alert("Seleccione un valor para el campo ${fn:replace(datos[0], '_', ' ')}");
								${datos[0]}.focus();
								return;
							}
		
							query += "&${datos[0]}="+da;
						</c:when>
						<c:when test="${not empty datos[2] and fn:startsWith(datos[2], '[RANGE]')}">
							var da = ${datos[0]}.value;
							<c:set var="dat" value="${fn:substringAfter(datos[2], '[RANGE]')}"/>
							<c:set var="rango" value="${fn:split(dat, ',')}"/>
							da = validarInfo(${datos[0]},${rango[0]},${rango[1]})
							if(da == '[null]'){
								alert('Ingrese solo cantidades numericas entre el rango ${rango[0]} y ${rango[1]} para el campo ${datos[3]}!');
								${datos[0]}.focus();
								return
							}
							query += "&${datos[0]}="+da;
						</c:when>
						<c:otherwise>
							query += "&${datos[0]}="+${datos[0]}.value;
						</c:otherwise>
						</c:choose>
					</c:forEach>
		
					</c:otherwise>
				</c:choose>
			}else{
			
				<c:if test="${configuracion.nombreConfiguracion == 'PING'}">
					
					var tipComb = document.getElementById('tipo');
					var nameConff = tipComb.options[tipComb.selectedIndex].value;
					
					if(nameConff == 'seleccione'){
						alert('Seleccione un Tipo de Configuracion a cargar.');
						tipComb.focus();
						return;
					}
					
					query += "&tipo="+nameConff;
				
				</c:if>
			
			}
			
			query+= "&id=${cabezaId}&central=${central}&proceso="+accion;
			
			//alert("QUERY:\n\n"+query);
			
			location.href = "${pageContext.request.contextPath}/actionSape?accion=mantenimientoSiplexPro&operacion=procesarConfigADSL&config=${configuracion.nombreConfiguracion}"+query;
		}

		function validarInfo(input,inicio,fin){

			if(isNaN(input.value)){
				return "[null]";
			}

			if(input.value >= inicio && input.value <= fin){
				return input.value;
			} else{
				return "[null]"
			}
		}
	</script>
</c:forEach>

				<!-- rutinas.jsp -->
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sapeTaglib" uri="/WEB-INF/tags/sape-taglib.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% java.util.List listaArmarios = (java.util.List) request.getAttribute("listaArmarios"); %>

<jsp:useBean id="totalClientes" class="java.lang.String" scope="request"/>


<jsp:useBean id="listaElementos" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="listaPruebasSiplexpro" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="tipoRutina" class="java.lang.String" scope="request"/>
<jsp:useBean id="valorRutina" class="java.lang.String" scope="request"/>
<jsp:useBean id="maxPruebas" class="java.lang.String" scope="request" />
<jsp:useBean id="rutinasActuales" class="java.lang.String" scope="request"/>
<jsp:useBean id="idElementoActual" class="java.lang.String" scope="request"/>
<jsp:useBean id="msg" class="java.lang.String" scope="request"/>
<jsp:useBean id="habilitandoRutina" class="java.lang.String" scope="request"/>
<%-- este bean es para cuando queramos habilitar una rutina --%>

<html>
<head> 
<title>SAPE - Rutinas Por ${fn:toUpperCase(tipoRutina)}</title>
<script language="JavaScript" src="javascript/varios.js"></script>
<script language="JavaScript" src="javascript/calendar.js"></script>
<script language="javascript">


addCalendar("DateIni", "calIni", "fechaIni", "forma1");
addCalendar("DateFin", "calFin", "fechaFin", "forma1");

function verificarRadios(genEvento,anteriorEv){
	var generador = document.getElementById(genEvento);
	var anterior = document.getElementById(anteriorEv);
	 
	//generador.checked=true; 
	anterior.checked=false;	
}

///PUEDE SERVIR EN ALGUN MOMENTO:

function SelectAllList(combo,num){
	
	if(num == 0){
		var CONTROL = document.getElementById(combo);
		for(var i = 0;i < CONTROL.length;i++){
			CONTROL.options[i].selected = true;
		}
		return;
	}
	
	for(var k = 0;k < num;k++){
		var CONTROL = document.getElementById(combo+k);
		for(var i = 0;i < CONTROL.length;i++){
			CONTROL.options[i].selected = true;
		}
	}
}


/*font face="arial" size="1"><a href="javascript:SelectAllList(document.theForm.Christmas_List);" style="color: blue;">select all</a> &nbsp;&nbsp;<a href="javascript:DeselectAllList(document.theForm.Christmas_List);" style="color: blue;">deselect all</a></font-
*/
function cargarHorario(){
	
	var combo = document.getElementById("selectElementos");
	var dato = combo.options[combo.selectedIndex].value;
	if(dato == -1){
		return;
	}
	
	limpiarTodo();
	document.getElementById('iframeOculto').src='${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=cargarHorario&${fn:toLowerCase(tipoRutina)}='+dato+"&tipoRutina=${tipoRutina}";
}

function cargarHorarioInicial(dato){

	document.getElementById('iframeOculto').src='${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=cargarHorario&${fn:toLowerCase(tipoRutina)}='+dato+"&tipoRutina=${tipoRutina}";
}


function limpiarTodo(){
	
	for (i=0;i<24;i++){
		var cleanHora = document.getElementById("check"+(i<=9?'0'+i:i));
		cleanHora.checked=false;
	}
	
	var dias=new Array("Mon","Tue","Wed","Thu","Fri","Sat","Sun");
	
	for(i=0;i<dias.length;i++){
		var cleanDia = document.getElementById(dias[i]);
		cleanDia.checked=false;
	}
	
	/*
	var checkFechaInicial = document.getElementById('checkFechaInicial');
	checkFechaInicial.checked=false;
	var checkFechaFinal = document.getElementById('checkFechaFinal');
	checkFechaFinal.checked=false;	
	*/
	var fechai = document.getElementById('fechaIni');
	fechai.value='';
	
	var fechaf = document.getElementById('fechaFin');
	fechaf.value='';

	resetearCombo('selectHorasInicial');
	resetearCombo('selectHorasFinal');

	resetearCombo('selectMinutosInicial');
	resetearCombo('selectMinutosFinal');
	
	/*
	var checkDiario =document.getElementById('checkDiario');
	checkDiario.checked=false;
	*/
	var usuario =document.getElementById('usuario');
	usuario.value='';
	var fechaProgramado =document.getElementById('fechaProgramado');
	fechaProgramado.value='';
	var idInput = document.getElementById('idInput');
	idInput.value = '';
	//resetearCombo('selectHabilitado');
	
	<%-- Para limpiar los campos si es una serie --%>
	<c:if test="${tipoRutina == 'Serie'}">
	//if('${tipoRutina}' == 'Serie'){
		var telIni = document.getElementById("telIni");
		var telFin = document.getElementById("telFin");
		telIni.value = '';
		telFin.value = '';
		telIni.disabled = true;
		telFin.disabled = true;
	//}
	</c:if>
	
}

function nuevo(){

	var rutinasActuales = ${rutinasActuales};
	var maximoRutinas = ${maxPruebas};
	
	if(rutinasActuales >= maximoRutinas){
		<%-- Con este codigo valido si es una actualizacion o una rutina nueva --%>
			alert('No se pueden insertar mas rutinas, limite alcanzado.\nSe sugiere eliminar alguna rutina antes de insertar una nueva');
			inicializarFormas();
			return;

	}


	limpiarTodo();
	
	var comboelements=document.getElementById('selectElementos');
	comboelements.disabled=true;
	
	resetearCombo('selectElementos');
	resetearCombo('selectTipoPruebas');
	
	var campo = document.getElementById('campo${tipoRutina}');
	var btnGuardar = document.getElementById('botonGuardar');
	var btnEliminar = document.getElementById('botonEliminar');
	var btnEditar = document.getElementById('botonEditar');
	var horaCheck = document.getElementById('horas');
	var fechaCheck = document.getElementById('fechas');
	var usuario =document.getElementById('usuario');
	var fechaProgramado =document.getElementById('fechaProgramado');
	
	
	fechaProgramado.value=formatDate(new Date(),"yyyy-MM-dd hh:mm:ss");
	campo.value='';
	usuario.value='${sessionScope.usuario.nick}';
	campo.disabled=false;
	btnGuardar.disabled=false;
	btnEliminar.disabled=true;
	<c:if test="${tipoRutina != 'Serie'}"><%-- Este if es para que no haga nada con el boton editar cuando sea una rutina por serie --%>
	btnEditar.disabled=true;</c:if>
	horaCheck.checked=false;
	fechaCheck.checked=false;
	campo.focus();
	<c:if test="${tipoRutina == 'Serie'}">
	//if('${tipoRutina}' == 'Serie'){
		var telIni = document.getElementById("telIni");
		var telFin = document.getElementById("telFin");
		telIni.disabled = false;
		telIni.focus();
		telFin.disabled = false;
		campo.disabled = true;
	//}
	</c:if>

}

function inicializarFormas(){

	limpiarTodo();
	resetearCombo('selectElementos');
	resetearCombo('selectTipoPruebas');

	var campo = document.getElementById('campo${tipoRutina}');
	var btnGuardar = document.getElementById('botonGuardar');
	var btnEliminar = document.getElementById('botonEliminar');
	var btnEditar = document.getElementById('botonEditar');
	var horaCheck = document.getElementById('horas');
	var fechaCheck = document.getElementById('fechas');
	//var comboElementos =document.getElementById('selectElementos');
//	var btnNuevo = document.getElementById('botonNuevo');
	campo.value='';
	campo.disabled=true;
	btnGuardar.disabled=true;
	btnEliminar.disabled=true;
	<c:if test="${tipoRutina != 'Serie'}"><%-- Este if es para que no haga nada con el boton editar cuando sea una rutina por serie --%>
	btnEditar.disabled=true;</c:if>
	horaCheck.checked=false;
	fechaCheck.checked=false;
}

function eliminarForma(){
	//var campo = document.getElementById('campo${tipoRutina}');
	var combo = document.getElementById("selectElementos");
	var dato = combo.options[combo.selectedIndex].value;
	
	var campo = combo.options[combo.selectedIndex].text;
	
	if(campo == '' || campo == '-1'){
		alert('Seleccione la rutina a borrar.');
		return;
	}
	
	if(confirm('Esta seguro de borrar la rutina por ${tipoRutina} '+campo+'?')){
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=eliminarHorario${tipoRutina}&id${tipoRutina}="+dato+"&nombre="+campo;
	}
}


function guardarForma(){

	var rutinasActuales = ${rutinasActuales};
	var maximoRutinas = ${maxPruebas};
	var combo = document.getElementById("selectElementos");
	if(rutinasActuales >= maximoRutinas){
		<%-- Con este codigo valido si es una actualizacion o una rutina nueva --%>
		//var combo = document.getElementById("selectElementos");
		var dato = combo.options[combo.selectedIndex].value;
		
		if(dato == '-1'){// es una rutina nueva, no dejo insertar.
			alert('No se pueden insertar mas rutinas, limite alcanzado.\nSe sugiere eliminar alguna rutina antes de insertar una nueva');
			inicializarFormas();
			return;
		}
	}
	var campo = combo.options[combo.selectedIndex].text;
	var dat = combo.options[combo.selectedIndex].value;
	
	if(dat =='-1'){
		campo = document.getElementById('campo${tipoRutina}').value;
		
		<c:if test="${tipoRutina != 'Serie'}">
		if(campo == ''){
			
			<c:if test="${tipoRutina != 'Cliente'}">
			alert('Ingrese un ${tipoRutina} valido');
			</c:if>
			<c:if test="${tipoRutina == 'Cliente'}">
			alert('Ingrese un nombre para la rutina valido');
			</c:if>
			document.getElementById('campo${tipoRutina}').focus();
			return;
		}
		</c:if>
		
		<c:if test="${tipoRutina=='Cable'}"><%--CODIGO HECHO PARA METROTEL --%>
			<c:if test="${sapeTaglib:isVisible('OPCION_CABLE_DIRECTO')}">	
				var directo = document.getElementById('tipo_cable_metrotel');
				var iddd= document.getElementById('idInput');
				if(directo.checked&&iddd.value ==''){
					campo="directo_"+campo;
				}
				
			</c:if>		
		</c:if>
		
	}
	
	
	var tipoPrueba = document.getElementById('selectTipoPruebas').value;
		
	var checkHoras = document.getElementById('horas');
	var checkFechas = document.getElementById('fechas');
	
	if(!checkHoras.checked && !checkFechas.checked){
		alert('Debe seleccionar un tipo de rutina, por horas o por fechas.');
		return;
	}
	
	if(tipoPrueba =='noPrueba'){
		alert('Seleccione un Tipo de prueba para la rutina.');
		document.getElementById('selectTipoPruebas').focus();
		return;
	}
	
	<c:if test="${tipoRutina == 'Serie'}">
	//if('${tipoRutina}' == 'Serie'){
		var telIni = document.getElementById("telIni");
		var telFin = document.getElementById("telFin");
		
		//alert('telini='+telIni+', telfin='+telFin);
		
		if(isNaN(telIni.value) || isNaN(telFin.value)||telIni.value==''||telFin.value==''){
			alert('Ingrese un rango de numeros valido');
			return;
		}
		
		if(telIni.value.length != ${applicationScope.cantDigitosEntorno}){
			alert('el telefono inicial debe tener una longitud de ${applicationScope.cantDigitosEntorno}');
			telIni.focus();
			return;
		}
		
		if(telFin.value.length != ${applicationScope.cantDigitosEntorno}){
			alert('el telefono final debe tener una longitud de ${applicationScope.cantDigitosEntorno}');
			telFin.focus();
			return;
		}
		
		if(telIni.value > telFin.value){
			alert('Rango de numeros invalido.El numero inicial debe ser menor que el final.');
			return;
		}
		
		var tmp = document.getElementById('campo${tipoRutina}').value;
		
		campo = telIni.value+"-"+telFin.value+(tmp == ''?'':"-"+tmp);

	//}
	</c:if>	
	
	if(checkHoras.checked){
		guardarFormaHoras(campo,tipoPrueba,dat);
	}else if(checkFechas.checked){
		guardarFormaFechas(campo,tipoPrueba,dat);
	}else{
		alert('Ocurrio un error.');	
	}
}

function guardarFormaFechas(campoValue,tipoPrueba,idRutina){
	
	var combohoraini = document.getElementById('selectHorasInicial');
	var horaini = combohoraini.options[combohoraini.selectedIndex].value;
	var combominini = document.getElementById('selectMinutosInicial');
	var minini = combominini.options[combominini.selectedIndex].value;
	
	var campoFechaIni = document.getElementById('fechaIni');
	var campoFechaFin = document.getElementById('fechaFin');
	var fechaf = campoFechaFin.value;
	var fechai = campoFechaIni.value;
	
	if(fechaf != '' && fechai != ''){
		if (!validarCamposRangosFechas(campoFechaIni, campoFechaFin)) {
			return;
		}
	}

	if(horaini=="" || horaini=="--"){
		alert('La hora inicial es obligatoria.');
		combohoraini.focus();
		return;
	}
	if(minini=="" || minini=="--"){
		alert('Los minutos iniciales son obligatorios.');
		combohoraini.focus();
		return;
	}
	
	var combohorafin = document.getElementById('selectHorasFinal');
	var horafin = combohorafin.options[combohorafin.selectedIndex].value;
	var combominfin = document.getElementById('selectMinutosFinal');
	var minfin = combominfin.options[combominfin.selectedIndex].value;
	
	
	if(confirm("Se agregara la actual informacion a la Rutina de ${tipoRutina} "+campoValue+". Desea Continuar?")){
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=actualizarRutina${tipoRutina}"+
		"&${fn:toLowerCase(tipoRutina)}="+campoValue+"&tipoRutina=D&fechaIni="+fechai+"&horaIni="+horaini+"&minIni="+minini+	
		"&fechaFin="+fechaf+"&horaFin="+horafin+"&minFin="+minfin+"&tipoPrueba="+tipoPrueba+"&idRutina="+idRutina+"&habilitandoRutina=${habilitandoRutina}";
	}
			
}

function guardarFormaHoras(campoValue,tipoPrueba,idRutina){

	var horas2Send="";
	var dias2Send="";
	var separador = "";
	for (i=0;i<24;i++){
		var hor = document.getElementById("check"+(i<=9?'0'+i:i));
		
		if(hor.checked){
			horas2Send += separador+hor.value;
			separador=",";
		}
	}
	
	var dias=new Array("Mon","Tue","Wed","Thu","Fri","Sat","Sun");
	separador = "";
	
	for(i=0;i<dias.length;i++){
		var day = document.getElementById(dias[i]);
		if(day.checked){
			dias2Send += separador+day.value;
			separador=",";
		}
	}
	
	if(horas2Send==''){
		alert('Seleccione las horas para realizar la rutina.');
		return;
	}
	
	
	if(confirm("Se agregara la actual informacion a la Rutina de ${tipoRutina} "+campoValue+". Desea Continuar?")){
	//var v210=window.open(
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=actualizarRutina${tipoRutina}"+
		"&${fn:toLowerCase(tipoRutina)}="+campoValue+"&tipoRutina=H&horas="+horas2Send+"&dias="+dias2Send+"&tipoPrueba="+tipoPrueba+"&idRutina="+idRutina+"&habilitandoRutina=${habilitandoRutina}";
	}
	
}

function enableForm(forma,habilitar){
	
	if(forma == 'horas'){// habilito o deshabilito todo el formato de horas
		
		if(habilitar == 'true'){
			alert('encotro un true');
			for (i=0;i<24;i++){
				var hor = document.getElementById("check"+(i<=9?'0'+i:i));
				hor.enabled=true;
			}
			
			var dias=new Array("Mon","Tue","Wed","Thu","Fri","Sat","Sun");
			
			for(i=0;i<dias.length;i++){
				var day = document.getElementById(dias[i]);
				day.enabled=true;
			}
		
		}else{
			alert('encotro un false');
			for (i=0;i<24;i++){
				var hor = document.getElementById("check"+(i<=9?'0'+i:i));
				hor.disabled=true;
			}
			
			var dias=new Array("Mon","Tue","Wed","Thu","Fri","Sat","Sun");
			
			for(i=0;i<dias.length;i++){
				var day = document.getElementById(dias[i]);
				day.disabled=true;
			}
			
		}
	}else{// habilito o deshabilito todo el formato de fechas (o definido por el usuario)

		var campoFechaIni = document.getElementById('fechaIni');
		var campoFechaFin = document.getElementById('fechaFin');
		campoFechaIni.disabled=habilitar;
		campoFechaFin.disabled=habilitar;
				
		var combohoraini = document.getElementById('selectHorasInicial');	
		var combohorafin = document.getElementById('selectHorasFinal');
		combohoraini.disabled=habilitar;
		combohorafin.disabled=habilitar;
		
		var combominini = document.getElementById('selectMinutosInicial');
		var combominfin = document.getElementById('selectMinutosFinal');
		//var checkDiario = document.getElementById('checkDiario');
		combominini.disabled=habilitar;
		combominfin.disabled=habilitar;
		//checkDiario.disabled=habilitar;
	}
}

function cargarDatosHorarioPorHoras(valorTipo,tipoPrueba,usuario,fechaProgramado,horasProgramadas,diasProgramados,id){

    var hora1 = document.getElementById("horas");
	var fecha1 = document.getElementById("fechas");
	
	var idInput = document.getElementById('idInput');
	idInput.value = id;
			
	fecha1.checked=false;
    hora1.checked=true;

	var element = document.getElementById("campo${tipoRutina}");
	element.value = valorTipo;
	<%-- Para determinar si una rutina es por serie y llenar adecuadamente sus campos. --%>
	
	<c:if test="${tipoRutina == 'Serie'}">
	//if('${tipoRutina}' == 'Serie'){
		var numeros = valorTipo.split("-");
		var telIni = document.getElementById("telIni");
		var telFin = document.getElementById("telFin");
		telIni.value = numeros[0];
		telFin.value = numeros[1];
		telIni.disabled = true;
		telFin.disabled = true;
		//element.disabled = true;
		element.value= '';
		if(numeros.length == 3)
			element.value=numeros[2];
		
		<%-- VALIDACIONES para cuando se va a habilitar nuevamente una rutina --%>
		<c:if test="${habilitandoRutina == 'true'}" >valorTipo=numeros[0]+"-"+numeros[1]</c:if>
	//}
	</c:if>

	element.disabled = true;
	
	<%-- VALIDACIONES para cuando se va a habilitar nuevamente una rutina --%>
	<c:if test="${habilitandoRutina == 'true'}" >
		var selxx=document.getElementById('selectElementos');
		addOpcion(selxx, id, valorTipo);
	</c:if>
	
	
	seleccionarComboValue('selectTipoPruebas',tipoPrueba);
	seleccionarCombo('selectElementos',valorTipo);
	
	
	var user = document.getElementById("usuario");
	user.value=usuario;
	
//	seleccionarComboValue('selectHabilitado',habilitado);
	
	var fechaProgramado2 = document.getElementById("fechaProgramado");
	fechaProgramado2.value=fechaProgramado;
	
	var sttHoras = horasProgramadas.split(",");
	
	for(i=0;i<sttHoras.length;i++){
		var checkHora = document.getElementById("check"+sttHoras[i]);
		checkHora.checked=true;
	}

	var sttDias = diasProgramados.split(",");
	
	if(diasProgramados != ''){
		for(i=0;i<sttDias.length;i++){
			
 			var diacheck = document.getElementById(sttDias[i]);
	       	diacheck.checked=true;
		}
	}
    
	var botonGuardar = document.getElementById("botonGuardar");
    var botonEliminar =document.getElementById("botonEliminar");
    var botonEditar = document.getElementById('botonEditar');
    botonGuardar.disabled=false;
   	<c:if test="${tipoRutina != 'Serie'}"><%-- Este if es para que no haga nada con el boton editar cuando sea una rutina por serie --%>
	botonEditar.disabled=false;</c:if>
	
	
	    <%-- VALIDACIONES para cuando se va a habilitar nuevamente una rutina --%>
    <c:choose>
    <c:when test="${habilitandoRutina == 'true'}">
    	botonEliminar.disabled=true;
    	botonEditar.disabled=true;
    </c:when>
    <c:otherwise>	
    	botonEliminar.disabled=false;    
    </c:otherwise>
    </c:choose>    
	
	
	
}

function cargarDatosHorarioPorFechas(valorTipo,tipoPrueba,usuario,fechaProgramado,fechaInicial,fechaFinal,horaInicial,minutoInicial,horaFinal,minutoFinal,id){
    var hora1 = document.getElementById("horas");
	var fecha1 = document.getElementById("fechas");


	var idInput = document.getElementById('idInput');
	idInput.value = id;

	fecha1.checked=true;
    hora1.checked=false;
    var element = document.getElementById("campo${tipoRutina}");
	element.disabled = true;
	
	element.value= '';
	element.value = valorTipo;
	<%-- Para determinar si una rutina es por serie y llenar adecuadamente sus campos. --%>
	<c:if test="${tipoRutina == 'Serie'}">
	//if('${tipoRutina}' == 'Serie'){
		var numeros = valorTipo.split("-");
		var telIni = document.getElementById("telIni");
		var telFin = document.getElementById("telFin");
		telIni.value = numeros[0];
		telFin.value = numeros[1];
		telIni.disabled = true;
		telFin.disabled = true;
		element.disabled = true;
		element.value= '';
		if(numeros.length == 3)
			element.value=numeros[2];

		<%-- VALIDACIONES para cuando se va a habilitar nuevamente una rutina --%>
		<c:if test="${habilitandoRutina == 'true'}" >valorTipo=numeros[0]+"-"+numeros[1];</c:if>		
	//}
	</c:if>	
	//element.value = valorTipo;



	
	<%-- VALIDACIONES para cuando se va a habilitar nuevamente una rutina --%>
	<c:if test="${habilitandoRutina == 'true'}" >
		var selxx=document.getElementById('selectElementos');
		addOpcion(selxx, id, valorTipo);
	</c:if>



	
	seleccionarComboValue('selectTipoPruebas',tipoPrueba);
	seleccionarCombo('selectElementos',valorTipo);
	
	var user = document.getElementById("usuario");
	user.value=usuario;
	
//	seleccionarComboValue('selectHabilitado',habilitado);
	
	var fechaProgramado2 = document.getElementById("fechaProgramado");
	fechaProgramado2.value=fechaProgramado;
	
	
	var fechai = document.getElementById("fechaIni");
	fechai.value=fechaInicial;
	
	var fechaf = document.getElementById("fechaFin");
	fechaf.value=fechaFinal;
	
	seleccionarCombo('selectHorasInicial',horaInicial);
   	seleccionarCombo('selectMinutosInicial',minutoInicial);
   	
	seleccionarCombo('selectHorasFinal',horaFinal);
	seleccionarCombo('selectMinutosFinal',minutoFinal);
	
	var botonGuardar = document.getElementById("botonGuardar");
    var botonEliminar =document.getElementById("botonEliminar");
    var botonEditar = document.getElementById('botonEditar');
    botonGuardar.disabled=false;
   	<c:if test="${tipoRutina != 'Serie'}"><%-- Este if es para que no haga nada con el boton editar cuando sea una rutina por serie --%>
	botonEditar.disabled=false;</c:if>
    
        <%-- VALIDACIONES para cuando se va a habilitar nuevamente una rutina --%>
    <c:choose>
    <c:when test="${habilitandoRutina == 'true'}">
    	botonEliminar.disabled=true;
    	botonEditar.disabled=true;
    </c:when>
    <c:otherwise>
    	botonEliminar.disabled=false;    
    </c:otherwise>
    </c:choose>
    
}


function editar() {

	var combo = document.getElementById("selectElementos");
	var element = combo.options[combo.selectedIndex].text;
    var dat = combo.options[combo.selectedIndex].value;
  	if(element.length < 1 || dat == '-1'){
		alert("Seleccione un ${fn:toUpperCase(tipoRutina)}");
		combo.focus();
		return;
 	}
  
  v203=window.open("${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=pantallaRutina${tipoRutina}&${fn:toLowerCase(tipoRutina)}="+element,'window203','scrollbars=yes,resizable=yes,hotkeys=yes,height=570,width=600,left=0,top=0,menubar=yes,toolbar=no');
  v203.focus();
}

 


</script>
</head>

<jsp:include page="../encabezado.jsp" flush="true" />
<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0" bgcolor="WHITE" >
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sape.css">
<BR>
<iframe id="iframeOculto" name="iframeOculto" style="border: 0px none ; width: 400px; height: 0px;" visibility="false"></iframe>

<table align="center" width="100%">
	<tr><td colspan=4 align="center" class="mensajeCentral">RUTINAS DE PRUEBA POR ${fn:toUpperCase(tipoRutina)} </td></tr>
	<c:if test="${not empty msg}">
		<tr><td colspan=4 align="center">${msg}</td></tr>
	</c:if>
	<tr><td colspan="4" height="10"></td></tr>
	<tr>
		<td align="center">
<%-- Inicio de forma de informacion del elemento de rutina --%>
	<table width="600" border="1">

	<c:choose>
	<c:when test="${tipoRutina=='Armario'||tipoRutina=='Cable'||tipoRutina=='Cliente'}">
		<tr>
		<td colspan="3" align="center">${tipoRutina}s activos: 
		
			<select name="selectElementos" id="selectElementos" onChange="javascript:cargarHorario();">
				<option value="-1">Seleccione:</option>
				<c:forEach items="${listaElementos}" var="tipo">
					<option value="${tipo[1]}">${tipo[0]}</option>
				</c:forEach>
			</select>&nbsp;&nbsp;Id: <input type="text" id="idInput" name="idInput" value="" size="4" disabled=true></td>
		</tr>
	</c:when>
	<c:when test="${tipoRutina=='Serie'}">
		
		<tr>
		<td colspan="4" align="center">
			Series Actuales:&nbsp;&nbsp;
			<select name="selectElementos" id="selectElementos" onChange="javascript:cargarHorario();">
				<option value="-1">Seleccione:</option>
				<c:forEach items="${listaElementos}" var="tipo">
					<option value="${tipo[1]}">${tipo[0]}</option>
				</c:forEach>
			</select>&nbsp;&nbsp;Identificador: <input type="text" id="idInput" name="idInput" value="" size="4" disabled=true>
		</td>
		</tr>
		<tr>
		<td colspan="2" align="center">
		Tel Inicial: <input type="text" class="texto" name="telIni" id="telIni" size="8" disabled=true>&nbsp;&nbsp;
		</td>
		<td colspan="2" align="center">
		Tel Final: <input type="text" class="texto" name="telFin" id="telFin" size="8" disabled=true>&nbsp;&nbsp;
		</td>
		</tr>
	
	</c:when>
	</c:choose>
	<tr>
		
		<td align=center> 
		
		<c:choose>
		<c:when test="${tipoRutina == 'Serie'}">Telefono Actual</c:when>
		<c:when test="${tipoRutina == 'Cliente'}">Nombre de rutina</c:when>
		<c:otherwise>${tipoRutina}</c:otherwise>
		</c:choose>
		: &nbsp;&nbsp;<input tipe="text" class="texto" name="campo${tipoRutina}" id="campo${tipoRutina}" size="8" disabled="disabled">
		
		<c:if test="${tipoRutina=='Cable'}">
			<c:if test="${sapeTaglib:isVisible('OPCION_CABLE_DIRECTO')}">
						
				Cable Directo <input type="checkbox" id="tipo_cable_metrotel" >
			</c:if>		
		</c:if>
		
		</td>

		<td align="center" colspan="2">Tipo de Prueba : &nbsp;&nbsp; 
			<select name="selectTipoPruebas" id="selectTipoPruebas">
				<option value="noPrueba">Seleccione:</option>
				<c:forEach items="${listaPruebasSiplexpro[0]}" var="pruebaSiplex">
				<option value="${pruebaSiplex}">${pruebaSiplex}</option>
				</c:forEach>
			</select>
		</td>

	</tr>
	<tr>
		<td colspan="2" align="center">Fecha de Ultimo Cambio: <input type="text" id="fechaProgramado" name="fechaProgramado" disabled="true" class="texto" size="16"></td>
		<td align="center" colspan="2">Usuario: <input type='text' name='usuario' id='usuario' disabled="true" size="10" class="texto"></td>		
	</tr>
	</table>
	<%-- Fin de forma de informacion del elemento de rutina --%>
	
<table width="100%" border="0">

<tr>
<td width="20%" align="left"><b>Por Horas</b> : <input type="radio" name="horas" id="horas" onclick="javascript:verificarRadios('horas','fechas');"></td>
<td width="80%" align=left></td>
</tr>
<tr>
<!-- td width="15%">Por Horas</td>
<td width="5%"><input type="radio" name="horas" id="horas" onclick="javascript:verificarRadios('horas','fechas');"></td-->
<td width="100%" align=left colspan="2">


<!-- horas ----------------------------------------------------------------------------- -->


<table width="100%" align="left" bgcolor="white" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" height="15" width="15%" class="fondo2"><b>Horario</b></td>
		<%-- Ciclo para colocar los horarios --%>
		<c:forEach begin="0" end="23" var="hour"><td align="center" height="15" width="20">${hour}</td></c:forEach>
	</tr>
			
	<tr>

		<td align="center" height="15" width="15%" class="fondo2"><b>Programacion</b></td>

			<c:forEach begin="0" end="23" var="hour">
				<td align="center" height="15" width="20"><input id="check${hour <=9?'0':''}${hour}" name="horasRutina" type="checkbox" value="${hour <=9?'0':''}${hour}"></td>
			</c:forEach>

	</tr>

	<tr>
		<!-- td rowspan="2" align="center" height="25" class="fondo2" width="15%"><b>Dias</b></td-->
		<td align="center" height="25" class="fondo2" width="15%"><b>Dias</b></td>
 
		<!-- td colspan="24" height="5" bgcolor=black></td-->
	<!-- /tr>

	<tr-->
		<td colspan="24">	
			<table width="100%" border="1" cellspacing="0">
			
				<!-- td colspan="3" align="center"><input name="lunes" id="Mon" type="checkbox" value="Mon">lunes</td>
				<td colspan="3" align="center"><input name="martes" id="Tue"  type="checkbox" value="Tue">martes</td>
				<td colspan="3" align="center"><input name="miercoles" id="Wed"  type="checkbox" value="Wed" >miercoles</td>
				<td colspan="3" align="center"><input name="jueves" id="Thu"  type="checkbox" value="Thu">jueves</td>
				<td colspan="3" align="center"><input name="viernes" id="Fri"  type="checkbox" value="Fri">viernes</td>
				<td colspan="3" align="center"><input name="sabado" id="Sat"  type="checkbox" value="Sat">sabado</td>
				<td colspan="3" align="center"><input name="domingo" id="Sun"  type="checkbox" value="Sun">domingo</td-->
			
			
				<tr>
				<td align="center"><input name="lunes" id="Mon" type="checkbox" value="Mon">Lunes</td>
				<td align="center"><input name="martes" id="Tue"  type="checkbox" value="Tue">Martes</td>
				<td align="center"><input name="miercoles" id="Wed"  type="checkbox" value="Wed" >Miercoles</td>
				<td align="center"><input name="jueves" id="Thu"  type="checkbox" value="Thu">Jueves</td>
				<td align="center"><input name="viernes" id="Fri"  type="checkbox" value="Fri">Viernes</td>
				<td align="center"><input name="sabado" id="Sat"  type="checkbox" value="Sat">Sabado</td>
				<td align="center"><input name="domingo" id="Sun"  type="checkbox" value="Sun">Domingo</td>
				</tr>
			</table>
		</td>
	</tr>
	</table>


<!-- horas --------------------------------------------------------------- -->

</td>
</tr>
<tr>

<td colspan="10" height="20"></td>
</tr>
<tr>
<td width="20%" align="left"><b>Por Fechas</b> : <input type="radio" name="fechas" id="fechas" onclick="javascript:verificarRadios('fechas','horas');"></td>
<td width="80%" align=left></td>
</tr>

<tr>
<!-- td width="15%">Por Fechas </td>
<td width="5%"><input type="radio" name="fechas" id="fechas" onclick="javascript:verificarRadios('fechas','horas');"></td-->
<td width="100%" align=left colspan="2">

<table width="100%" align="left" bgcolor="white" border="1" cellpadding="0" cellspacing="0">
<form name="forma1">
<tr>
<td width="15%" class="fondo2" align="center"><b>Fecha Inicial</b> </td>
<!-- td align="center"><input id="checkFechaInicial" name="checkFechaInicial" type="checkbox"></td-->

    <td align="center">
        <input type="text" class="texto" name="fechaIni" id="fechaIni" maxlength="10" size="10" value="${fIni}">
        <span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateIni', 260, -5)"> (aaaa-mm-dd)</a></span>
        <div id="calIni" style="position:relative; visibility: hidden;">
    </td>

 <td align="center">Hora : 
 <select name="selectHorasInicial" id="selectHorasInicial">
   <option value="--">--</option>
 <c:forEach begin="0" end="23" var="hora"><option value="${hora <= 9?'0':''}${hora}">${hora <= 9?'0':''}${hora}</option></c:forEach>
 </select>
 </td>
 <td align="center">Minuto :
 <select name="selectMinutosInicial" id="selectMinutosInicial">
   <option value="--">--</option>
 <option value="00">00</option> <option value="15">15</option> <option value="30">30</option> <option value="45">45</option> 
 </select>
 </td>
 </tr>
 <tr>
 <td width="15%" class="fondo2" align="center"><b>Fecha Final</b></td>
 <!--  td align="center"><input id="checkFechaFinal" name="checkFechaFinal" type="checkbox"></td-->
    <td align="center">
        <input type="text" class="texto" name="fechaFin" id="fechaFin" maxlength="10" size="10" value="${fFin}">
        <span title="Click Para Abrir El Calendario" class="calendario"><a href="javascript:showCal('DateFin', 260, -5)">(aaaa-mm-dd)</a></span>
        <div id="calFin" style="position:relative; visibility: hidden;">
    </td>
     <td align="center">Hora : 
 <select name="selectHorasFinal" id="selectHorasFinal">
 <option value="--">--</option>
 <c:forEach begin="0" end="23" var="hora"><option value="${hora <= 9?'0':''}${hora}">${hora <= 9?'0':''}${hora}</option></c:forEach>
 </select>
 </td> 
 <td align="center">Minuto :
 <select name="selectMinutosFinal" id="selectMinutosFinal">
  <option value="--">--</option>
 <option value="00">00</option> <option value="15">15</option> <option value="30">30</option> <option value="45">45</option>
 </select>
 </td>
</tr>


<!-- tr>
 <td width="15%" class="fondo2" align="center"><b>Frecuencia</b></td>
<td align="center">Diario :  <input id="checkDiario" name="checkDiario" type="checkbox"></td>
<td> </td>
<td> </td>
<td> </td>
</tr-->


<tr></tr>
</form>
</table>

</td>
</tr>

<tr>
<td height="10" colspan='4'></td>
</tr>

<tr>
<td colspan='4' align="center">
<input type="button" class="boton" name="nuev" value="Nuevo" onClick="javascript:nuevo();">
&nbsp;&nbsp;
<input type="button" class="boton" name="guarda" id="botonGuardar" value="Guardar" disabled="disabled" onclick="javascript:guardarForma();">
&nbsp;&nbsp;
<input type="button" class="boton" name="borra" value="Eliminar" id="botonEliminar" onClick="javascript:eliminarForma();" disabled="disabled">
<c:if test="${tipoRutina != 'Serie'}">
&nbsp;&nbsp;
<input type="button" class="boton" name="edit" value="Editar" id="botonEditar" onClick="javascript:editar();" disabled="disabled">
</c:if>
</td>
</tr>
</table>


<br>
	<center>${rutinasActuales} rutinas de un maximo de ${maxPruebas}.</center>
		
<br>
<center><a href="${pageContext.request.contextPath}/actionSape?accion=rutinas">Regresar</a></center>

<c:if test="${not empty idElementoActual}">
<script language="javascript">
	cargarHorarioInicial('${idElementoActual}');
</script>
</c:if>
<input type="hidden" name="requestStatus" id="requestStatus" value='invalid'>
</body>
</html>

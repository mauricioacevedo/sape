					<!-- inicioColas.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% java.util.List listaColas = (java.util.List) request.getAttribute("listaColas"); %>
<% java.util.List listaHorarios = (java.util.List) request.getAttribute("listaHorarios"); %>
<jsp:useBean id="msgColas" class="java.lang.String" scope="request"/>

<head> 
<title>SAPE - Rutinas</title>

<!--style type="text/css">
	a:link { font-weight: bold; font-size: 18; text-decoration: none; color: black }
	a:visited { font-weight: bold; font-size: 18; text-decoration: none; color: black }
	a:hover {color: #e7a500}
</style-->

<script language="JavaScript" src="javascript/common.js"> </script>
<script language="JavaScript">

  function limpia_forma(forma) {
    //forma.reset();

	var x = forma.input;
	var j=0;
	for (var i in forma)
	{
		if(j> 30){
			return;
		}

		var obj = typeof forma[i];
		if(obj == "object"){

			if(forma[i].type == "radio" || forma[i].type == "checkbox")
				forma[i].checked = false;
			//document.write("lo tastaseo<br>");
		}
		//document.write("TIPO: "+(typeof forma[i])+" otro: "+forma[i]+"<br>");
		j++;

	}

    return;
    }

function verif_entradas(forma,cola) {

var horas = "";
var k =0;

if  ( forma.c0.checked ){ s0=1; horas = "00:00,"; k=k+1;}
 else s0=0;

if  ( forma.c1.checked ){ s1=1; horas = horas + " 01:00,"; k=k+1; }
 else s1=0;

if  ( forma.c2.checked ){ s2=1; horas = horas + " 02:00,";  k=k+1;}
 else s2=0;

if  ( forma.c3.checked ){ s3=1; horas = horas + " 03:00,";  k=k+1;}
 else s3=0;

if  ( forma.c4.checked ){ s4=1; horas = horas + " 04:00,";  k=k+1;}
 else s4=0;

if  ( forma.c5.checked ){ s5=1; horas = horas + " 05:00,";  k=k+1;}
 else s5=0;

if  ( forma.c6.checked ){ s6=1; horas = horas + " 06:00,";  k=k+1;}
 else s6=0;


 if  ( forma.c7.checked ){ s7=1; horas = horas + " 07:00,";  k=k+1;}
 else s7=0;

if  ( forma.c8.checked ){ s8=1; horas = horas + " 08:00,";  k=k+1;}
 else s8=0;

if  ( forma.c9.checked ){ s9=1; horas = horas + " 09:00,";  k=k+1;}
 else s9=0;

if  ( forma.c10.checked ){ s10=1; horas = horas + " 10:00,";  k=k+1;}
 else s10=0;

if  ( forma.c11.checked ){ s11=1; horas = horas + " 11:00,"; k=k+1; }
 else s11=0;

if  ( forma.c12.checked ){ s12=1; horas = horas + " 12:00,"; k=k+1; }
 else s12=0;

 if(k >= 12){
 	horas = horas + "\n";
	k= -1
 }
if  ( forma.c13.checked ){ s13=1; horas = horas + " 13:00,";  k=k+1;}
 else s13=0;

 if(k >= 12){
 	horas = horas + "\n";
	k= -1
 }
 if  ( forma.c14.checked ){ s14=1; horas = horas + " 14:00,";  k=k+1;}
 else s14=0;

 if(k >= 12){
 	horas = horas + "\n";
	k= -1
 }
if  ( forma.c15.checked ){ s15=1; horas = horas + " 15:00,";  k=k+1;}
 else s15=0;

 if(k >= 12){
 	horas = horas + "\n";
	k= -1
 }
if  ( forma.c16.checked ){ s16=1; horas = horas + " 16:00,";  k=k+1;}
 else s16=0;

 if(k >= 12){
 	horas = horas + "\n";
	k= -1
 }
if  ( forma.c17.checked ){ s17=1; horas = horas + " 17:00,";  k=k+1;}
 else s17=0;

 if(k >= 12){
 	horas = horas + "\n";
	k= -1
 }
if  ( forma.c18.checked ){ s18=1; horas = horas + " 18:00,";  k=k+1;}
 else s18=0;

 if(k >= 12){
 	horas = horas + "\n";
	k= -1
 }
if  ( forma.c19.checked ){ s19=1; horas = horas + " 19:00,";  k=k+1;}
 else s19=0;

 if(k >= 12){
 	horas = horas + "\n";
	k= -1
 }
if  ( forma.c20.checked ){ s20=1; horas = horas + " 20:00,";  k=k+1;}
 else s20=0;

 if(k >= 12){
 	horas = horas + "\n";
	k= -1
 }
if  ( forma.c21.checked ){ s21=1; horas = horas + " 21:00,";  k=k+1;}
 else s21=0;

 if(k >= 12){
 	horas = horas + "\n";
	k= -1
 }
if  ( forma.c22.checked ){ s22=1; horas = horas + " 22:00,";  k=k+1;}
 else s22=0;

if  ( forma.c23.checked ){ s23=1; horas = horas + " 23:00,";  k=k+1;}
 else s23=0;


 horas = horas.substring(0,horas.length-1);

 var dias ="";
 var lunes = 0,martes=0,miercoles=0,jueves=0,viernes=0,sabado=0,domingo=0;


 if(forma.lunes.checked){
 	lunes=1;
	dias = dias + " Lunes ";
  }

  if(forma.martes.checked){
 	martes=1;
	dias = dias + " Martes ";
  }

    if(forma.miercoles.checked){
 	miercoles=1;
	dias = dias + " Miercoles ";
  }


  if(forma.jueves.checked){
 	jueves=1;
	dias = dias + " Jueves ";
  }

    if(forma.viernes.checked){
 	viernes=1;
	dias = dias + " Viernes ";
  }

    if(forma.sabado.checked){
 	sabado=1;
	dias = dias + " Sabado ";
  }

    if(forma.domingo.checked){
 	domingo=1;
	dias = dias + " Domingo ";
  }


  if(horas == "" && dias != ""){
	 alert("Seleccione una hora para realizar pruebas por la cola "+cola);
	 return;
  }

  if(dias == ""){

	 confirmacion=window.confirm("No se selecciono ninguna configuracion para realizar pruebas sobre la cola "+cola+",\n"+
	 "A continuacion se borrara cualquier horario de prueba sobre esta cola.\n"+
	 "\nEsta seguro?");
	 if(confirmacion)
	 	location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=eliminarProgramacionCola&cola="+cola;
	 return;
  }


	confirmacion=window.confirm("Horario de pruebas por cola ("+cola+"):\n "+horas+"\n Estas pruebas se haran los dias:\n"+dias);


	if (confirmacion) {
		this.window.focus();
		location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=actualizarRutinaCola&h0="+s0+"&h1="+s1+"&h2="+s2+"&h3="+s3+"&h4="+s4+"&h5="+s5+"&h6="+s6+"&h7="+s7+"&h8="+s8+"&h9="+s9+"&h10="+s10+"&h11="+s11+"&h12="+s12+"&h13="+s13+"&h14="+s14+"&h15="+s15+"&h16="+s16+"&h17="+s17+"&h18="+s18+"&h19="+s19+"&h20="+s20+"&h21="+s21+"&h22="+s22+"&h23="+s23+"&lunes="+lunes+"&martes="+martes+"&miercoles="+miercoles+"&jueves="+jueves+"&viernes="+viernes+"&sabado="+sabado+"&domingo="+domingo+"&cola="+cola;
	}
}


	function editarCola(cola){
		v600=window.open("${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=editarColas&cola="+cola, 'window600','scrollbars=yes,resizable=yes,hotkeys=no,height=600,width=650,left=0,top=0,menubar=no,toolbar=no');
		v600.focus();
	}

	function cargarCola(cola){
		if (confirm("Seguro de cargar datos de la Cola " + cola + "?")) {
			location.href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=cargarCola&cola="+cola;
		}
	}

</script>
</head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0" bgcolor="WHITE">


<BR>
<table align="center" bgcolor="BLACK" width="720">
<TR>
	<TD align="center" class="mensajeCentral">
		HORARIOS DE RUTINAS DE PRUEBAS POR COLAS
	</TD>
</TR>
</table>


<font color="red" size="+1"><center>${msgColas}</center></font>
<BR>

<center><a href="${pageContext.request.contextPath}/actionSape?accion=rutinas&operacion=inicioCodigosFalla">Codigos de Falla y Naturalezas</a></center>

<iframe id="iframeOculto" name="iframeOculto" style="border: 0px none ; width: 400px; height: 0px;" visibility="false"></iframe>

<c:forEach var="horario" items="${listaHorarios}">
	<BR>
	<form name="rutina${horario.cola}">

	<table cellpadding="0" cellspacing="0" border="1" align="center" bgcolor="white">
	<TR>
		<TD colspan="25" bgcolor="black" align="center" class="mensajeCentral">
			${horario.titulo}
		</TD>
	</TR>
	<tr>
		<td colspan="25" align="center">
			Usuario: ${horario.usuarioCambio} &nbsp;-&nbsp; Fecha: <fmt:formatDate value= "${horario.fechaCambio}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
		</td>
	</tr>
	<tr>
		<td width="50" height="15" align="center" class="fondo2"><b><font face="Arial" size="1">HORARIO</font></b></td>
		<%-- Ciclo para mostrar las columnas de las horas --%>
		<c:forEach var="hora" begin="0" end="23">
			<td width="20" height="15" align="center" class="fondo2">${hora}</td>
		</c:forEach>
	</tr>
	<tr>
		<td width="50"  height="15" class="fondo2"><font face="Verdana" size="3" COLOR="BLACK"><b><center>Programacion</center></b></font></td>
		<c:forEach var="horas" items="${horario.listaHorarios}">
			<td width="20"  height="15" align="center"><input type="radio" ${horas}></td>
		</c:forEach>
	</tr>

	<tr>
		<td rowspan="2" height="25" class="fondo2"><font face="Verdana" size="3" COLOR="BLACK"><B><center>Dias</center></b></font></td>
		<td height="10" colspan="24" class="fondo2"></td>
	</tr>

	<tr>
		<c:forEach var="dias" items="${horario.listaDias}">
			${dias}
		</c:forEach>
	</tr>
	
	</table>
	<br>
	<br>
		<table width="420" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr> 
				<td align="center">
					<input type="button" class="boton" value="Aceptar" onClick="verif_entradas(document.rutina${horario.cola},'${horario.cola}')">
					&nbsp;&nbsp;
					<input type="button" class="boton" value="Limpiar" onClick="limpia_forma(document.rutina${horario.cola})">
					<c:if test="${fn:contains(horario.titulo, 'ACTIVA') == true}">
					&nbsp;&nbsp;
					<input class="boton" type="button" value="Editar" onclick="javascript:editarCola('${horario.cola}');">
					&nbsp;&nbsp;
					<input class="boton" type="button" value="Cargar Cola" onclick="javascript:cargarCola('${horario.cola}');">
					</c:if>
				</td>
			</tr>
		</table>	
	</form>
	<br>
</c:forEach>
<br>

<center><a href="${pageContext.request.contextPath}/actionSape?accion=rutinas">Regresar</a></center>

</body>
</html>

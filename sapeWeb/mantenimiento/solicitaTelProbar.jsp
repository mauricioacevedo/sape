<html><head><title>Sistema Administrativo de Pruebas Extendidas - SAPE</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript">
<!--
function MM_swapImgRestore() {
  var i,x,a=document.MM_sr; 
  for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) 
  	x.src=x.oSrc;
}

function MM_preloadImages() {
  var d=document; 
  if(d.images){ if(!d.MM_p) 
  	d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; 
    for(i=0; i<a.length; i++)
      if (a[i].indexOf("#")!=0) { 
       d.MM_p[j]=new Image; 
       d.MM_p[j++].src=a[i];
       }
     }
}

function MM_findObj(n, d) {
  var p,i,x;  
  if(!d) d=document; 
  if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; 
    n=n.substring(0,p);
    }
  if(!(x=d[n])&&d.all) x=d.all[n]; 
  for (i=0;!x&&i<d.forms.length;i++) 
   x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) 
   x=MM_findObj(n,d.layers[i].document); 
   return x;
}

function MM_swapImage() {
  var i,j=0,x,a=MM_swapImage.arguments; 
  document.MM_sr=new Array; 
  for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){
    document.MM_sr[j++]=x; 
    if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];
    }
}
//-->
</script></head>
<jsp:include page="../encabezado.jsp" flush="true" />
<body topmargin="0" leftmargin="0" onload="MM_preloadImages('imagenes_sape/tipo_nodo_u.gif','imagenes_sape/nodo_prueba_u.gif','imagenes_sape/usuarios_u.gif')" bgcolor="white" marginheight="0" marginwidth="0">
<form action="" method="post" target="Cuerpo">
  <p align="center"><font color="#333333" face="AriaL" size="4"><b><font size="3">MANTENIMIENTO 
    A PARAMETROS DEL SISTEMA</font></b></font>
  <table align="center" border="0" height="250" width="60%">
<tbody><tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr>
    <tr> 
      <td align="center" height="15%" width="50%"> 
        <input onclick="this.form.action='${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoUsuarios';return" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('usuarios','','imagenes_sape/usuarios_u.gif',1)" target="Cuerpo" name="usuarios" src="imagenes_sape/usuarios_d.gif" type="image">
      </td>
    <td align="center" height="15%" width="50%"> 
    <input onclick="this.form.action='${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=Umbrales';return" target="Cuerpo" name="Umbrales" src="imagenes_sape/umbrales.jpeg" type="image" width="18%">
<font color="BLACK" face="Verdana" size="2"><b>
<br>Umbrales</b>
      </font></td>
    </tr>
<tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr>
    <tr> 

      <td align="center" height="15%" width="50%"> 
        <input onclick="this.form.action='${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoTipoNodo';return" target="Cuerpo" name="tipo_nodo" src="imagenes_sape/SIPLEXpro.jpg" type="image" width="30%">
<font color="BLACK" face="Verdana" size="2">
<br> <b>Tipo de Nodos</b>
      </font></td>

      <td align="center" height="15%" width="50%"> 
        <input onclick="this.form.action='${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoBasedeDatos';return" target="Cuerpo" name="basedatos" src="imagenes_sape/basedatos.jpg" type="image" width="20%">
<font color="BLACK" face="Verdana" size="2">
<br> <b>Base de Datos</b>
      </font></td>
    </tr>

<tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr>
    <tr> 
      <td align="center" height="15%" width="50%"> 
        <input onclick="this.form.action='${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoSeries';return" target="Cuerpo" name="nodo_prueba" src="imagenes_sape/nodo_prueba.gif" type="image" width="15%">
<font color="BLACK" face="Verdana" size="2"><b>
<br> Series Numericas</b>
      </font></td>
      <td align="center" height="15%" width="50%"> 
        <input onclick="this.form.action='${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoCabezaPrueba'" target="Cuerpo" name="tipo_nodo" src="imagenes_sape/SIPLEXpro.jpg" type="image" width="30%">
<font color="BLACK" face="Verdana" size="2">
<br> <b>Clase de Nodos</b>
      </font></td>
    </tr>

<tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr>
    <tr> 
      <td align="center" height="15%" width="50%"> 
        <input onclick="this.form.action='${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoCodigosVer';return" target="Cuerpo" name="codigosver" src="imagenes_sape/codigosver.gif" type="image" width="18%">
<font color="BLACK" face="Verdana" size="2"><b> 
<br> Codigos Ver</b>
      </font></td>

    </tr>
<tr><td height="5%">&nbsp;</td><td height="5%">&nbsp;</td></tr>
    <tr>


    <td align="center" height="15%" width="50%"> 
    <input onclick="this.form.action='${pageContext.request.contextPath}/actionSape?accion=mantenimiento&operacion=mantenimientoPermitidos';return" target="Cuerpo" name="permitidos" src="imagenes_sape/permitidos.gif" type="image" width="18%">
<font color="BLACK" face="Verdana" size="2"><b>
<br>IPs Permitidos</b>
      </font></td>

    </tr>

  </tbody></table>

</p></form></body></html>

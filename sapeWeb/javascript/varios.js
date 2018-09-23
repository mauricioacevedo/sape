var NSX = (navigator.appName == "Netscape");
var IE4 = (document.all) ? true : false;

/**
 * Le adiciona una nueva opcion a los select de los formularios
 * sirve mucho para hacer selects dinamicos y que se acomoden al 
 * navegador
 */
function addOpcion(select, value, text) {
   opcion=window.parent.document.createElement("OPTION");
   opcion.value=value;
   opcion.text=text;
   if (NSX) {
      select.appendChild(opcion);
   } else if (IE4) {
     select.options.add(opcion);
   }
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; 
  for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}


function MM_preloadImages() { //v3.0
  var d=document; 

  if(d.images){ 
	   if(!d.MM_p) d.MM_p=new Array();
	    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; 
	
	   for(i=0; i<a.length; i++)
	    if (a[i].indexOf("#")!=0){ 
	     d.MM_p[j]=new Image; 
	     d.MM_p[j++].src=a[i];
	   }
  }
}


function MM_findObj(n, d) { //v3.0
  var p,i,x;  
  if(!d) d=document; 
  if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; 
    n=n.substring(0,p);
   }
  if(!(x=d[n])&&d.all) x=d.all[n]; 
  for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document); 
  return x;
}



function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; 
  document.MM_sr=new Array; 
  for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){
    document.MM_sr[j++]=x; 
   if(!x.oSrc) 
    x.oSrc=x.src; 
    x.src=a[i+2];
   }
}


function cerrar() {
    parent.window.close();
}


function Abre_ventanaDetallePrueba(idprueba) {
  this.window.focus();
  v500=window.open("/sape/actionSape?accion=pruebaTelefono&operacion=mostrarResultados&ticket="+idprueba, 'detallePrueba','scrollbars=yes,resizable=yes,hotkeys=yes,height=600,width=650,left=0,top=0,menubar=yes,toolbar=no');
  v500.focus();
}


function cargarHttpRequest(url, metodo, funcion) {
	if (window.XMLHttpRequest) {
    	req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
    	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open(metodo, url, true);
	req.onreadystatechange = funcion;
	req.send(null);
}

function abrirGrafica(datos){

	v900=window.open("/sape/actionSape?accion=reportes&operacion=mostrarGraficaEfectividad&datos="+datos, 'window900','scrollbars=yes,resizable=yes,hotkeys=yes,height=600,width=800,left=100,top=50,menubar=yes,toolbar=no');
	v900.focus();
}


/* Se deben incluir los siguientes tags para utilizar la funcion:

<script language="javascript" src="javascript/tooltip/domLib.js"></script>
<script language="javascript" src="javascript/tooltip/domTT.js"></script>

<style>@import url(javascript/tooltip/example.css);</style>

*/
function mostrarDivAyuda(padreEvento,evento,titulo,contenido){
	var domTT_classPrefix = 'domTTOverlib';	
    return makeTrue(domTT_activate(padreEvento, evento, 'caption', titulo, 'content', contenido, 'trail', true));
}


/* Valida que la fecha tenga la forma yyyy-mm-dd */
function fechaValida(fecha) {
	var regexp = /[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}/
	//alert("regexp: " + regexp);
	if (fecha.search(regexp) == -1) return false;
	else return true;
}


/* Validar Campos Rangos Fechas. Los mas utilizados en el sape*/
function validarCamposRangosFechas(txtFechaIni,txtFechaFin) {
	//alert("validarCamposRangosFechas");
	var  fechaIni = txtFechaIni.value;
	if (!fechaValida(fechaIni)) {
		alert("Fecha Inicial Invalida!");
		txtFechaIni.focus();
		return false;
	}
	
	var fechaFin = txtFechaFin.value;
	if (!fechaValida(fechaFin)) {
		alert("Fecha Final Invalida!");
		txtFechaFin.focus();
		return false;
	}

	if (compareDates(fechaIni, fechaFin) == 1) {
		alert("Rango de Fechas Invalido!");
		txtFechaIni.focus();
		return false;
	}
	return true;
}

/*Para seleccionar un dato en un combo por el texto*/
function seleccionarCombo(comb,valorSeleccionado){

	var combo = document.getElementById(comb);
	
    for(var i = 0;i < combo.length;i++){
   		if(combo.options[i].text == valorSeleccionado ) {
   			combo.options[i].selected=true;
   			break;
   		}
	}
}

/*Para seleccionar un dato en un combo por el value*/
function seleccionarComboValue(comb,valorSeleccionado){

	var combo = document.getElementById(comb);
	
    for(var i = 0;i < combo.length;i++){
   		if(combo.options[i].value == valorSeleccionado ) {
   			combo.options[i].selected=true;
   			break;
   		}
	}
}

function resetearCombo(c){

	var combo = document.getElementById(c);
	combo.options[0].selected=true;
}

function getValue(c){
	return c.options[c.selectedIndex].value;
}

function getText(c){
	return c.options[c.selectedIndex].text;
}

function getValueid(c){
	var combo = document.getElementById(c);
	return combo.options[combo.selectedIndex].value;
}

function getTextid(c){
	var combo = document.getElementById(c);
	return combo.options[combo.selectedIndex].text;
}



 function addZero(vNumber){ 
    return ((vNumber < 10) ? "0" : "") + vNumber 
  } 
        
  function formatDate(vDate, vFormat){ 
    var vDay                      = addZero(vDate.getDate()); 
    var vMonth            = addZero(vDate.getMonth()+1); 
    var vYearLong         = addZero(vDate.getFullYear()); 
    var vYearShort        = addZero(vDate.getFullYear().toString().substring(3,4)); 
    var vYear             = (vFormat.indexOf("yyyy")>-1?vYearLong:vYearShort) 
    var vHour             = addZero(vDate.getHours()); 
    var vMinute           = addZero(vDate.getMinutes()); 
    var vSecond           = addZero(vDate.getSeconds()); 
    var vDateString       = vFormat.replace(/dd/g, vDay).replace(/MM/g, vMonth).replace(/y{1,4}/g, vYear) 
    vDateString           = vDateString.replace(/hh/g, vHour).replace(/mm/g, vMinute).replace(/ss/g, vSecond) 
    return vDateString 
  }


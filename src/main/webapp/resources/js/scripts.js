
//funcion que se ejecutara cuando en "cv/content.xhtml" se pulse sobre alguno de los botones para añadir/editar datos
//se encarga de la 'animación' de desplazamiento de derecha a izquierda
function showDlg(idDlg) {
		
	var yBody = $('body').height();
	var xBody = Math.floor( $('body').width() );
	var xDlg = Math.floor( $('body').width() * 0.50);
					
	$( idDlg ).height(yBody).width(0).css('left',xBody);
	$( idDlg ).animate( { width: xDlg.toString(), left: '-=' + xDlg }, 400);
		
}//showDlg()


function preShowDlg(wv) {
	//si el usuario no hay introducido 'datos personales' no se permite introducción de datos de ninguna de las otras 'categorias'
	if ( $("#nombreAlumno").text() === '') {
		PF('wv_dlgFaltanDatos').show();
	} else {
		PF( wv ).show();
	}
}

//funcion que se ejecutara cuando en "cv/dlgXXX.xhtml" se pulse sobre el botón de cancelar o el link superior en forma de x para cerrar el dialogo
//se encarga de la 'animación' de desplazamiento de izquierda a derecha
function hideDlg(idDlg, widgetVar) {

	var xBody = Math.floor( $('body').width() );
					
	$( idDlg ).animate( { 
							left: '+=' + xBody.toString()},
							600, function() {
						 		PF( widgetVar ).hide(); 
						});
			
}//hideDlg()


//funcion para establecer como activado/desactivato los inputs de fecha de finalización en el diálogo para añadir datos de Formacion ("cv/dlgFormacion.xhtml")
function toggleFechaFin() {
	
	if ( PF('wvActualmente').isChecked() ) {
		PF('wvFinMes').disable();	
		PF('wvFinAnno').disable();
		$("#datosFormacion\\:fechaFin").css('color','#CECECE');
	} else {
		PF('wvFinMes').enable();	
		PF('wvFinAnno').enable();
		$("#datosFormacion\\:fechaFin").css('color','#222');
	}
	
}

//TRAS LOS ULTIMOS CAMBIOS ESTA FUNCION YA NO SE USA, PERO NO BORRAR POR SI ACASO
//funcion para establecer como desactivado/activado el input de "identificación", 
//que solo se activará cuando se seleccione una opción de las disponibles para "tipoIdentificacion"
function tipoIdentificacion() {
	$('#datosPersonales\\:identificacion').val('');
	
	if ( PF('wvSelectTipo').getSelectedValue() !== '') {
		//$('#contentForm\\:labelIdentificacion').text( valor );
		PF('wvInputIdentificacion').enable();
	} else {
		//$('#contentForm\\:labelIdentificacion').text( 'Identificación' );
		PF('wvInputIdentificacion').disable();		
	}
}//tipoIdentificacion		

	

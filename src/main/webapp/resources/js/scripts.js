
function confirmacion(args) {
    if (args.loginCorrecto) {
        PF('wv_dlgConfirmacion').show();
    }
}

function limpiarErrorValidacion(cosa) {
	
	var $errorCorrespondiente = $( PrimeFaces.escapeClientId( cosa ) ).parent().next();
	var texto = $errorCorrespondiente.find('.ui-message-error-detail').text();

	if ( texto !== '' && texto !== undefined ) { 
		//console.log('eliminar');
		$errorCorrespondiente.find('.ui-message-error-detail').remove();
	}
	
}


//Función para forzar que si el usuario no hay introducido 'Datos personales', no se permita la introducción de datos de ninguna de las otras 'categorias'.
function preShowDlg(wv) {
	if ( $("#nombreAlumno").text() === '') {
		PF('wv_dlgFaltanDatos').show();
	} else {
		PF( wv ).show();
	}
}

//Función que se ejecutará cuando en la pantalla del Panel del Alumno, se pulse sobre alguno de los botones para añadir/editar datos.
//Y que se encarga de la 'animación' de desplazamiento de derecha a izquierda.
function showDlg(idDlg) {
		
	var yBody = $('body').height();
	var xBody = Math.floor( $('body').width() );
	var xDlg = Math.floor( $('body').width() * 0.50);
					
	$( idDlg ).height(yBody).width(0).css('left',xBody);
	$( idDlg ).animate( { width: xDlg.toString(), left: '-=' + xDlg }, 400);
		
}//showDlg()


//Función que se ejecutara cuando en cualquiera de los diálogos para la introducción de datos, se pulse sobre el botón de cancelar o el link superior en forma
//de x para cerrar el dialogo. Y que se encarga de la 'animación' de desplazamiento de izquierda a derecha.
function hideDlg(idDlg, widgetVar) {

	var xBody = Math.floor( $('body').width() );
					
	$( idDlg ).animate( { 
							left: '+=' + xBody.toString()},
							600, function() {
						 		PF( widgetVar ).hide(); 
						});
			
}//hideDlg()


//Función para establecer como activados/desactivados, los inputs de fecha de finalización en los diálogos para añadir Datos de Formacion o de Experiencia Laboral
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


//funcion que se ejecutara cuando en "cv/content.xhtml" se pulse sobre el boton "<p:commandButton value="Añadir datos ..."
//se encarga de la 'animación' de desplazamiento de derecha a izquierda
function showDlg(idDlg) {

	var yBody = $('body').height();
	var xBody = Math.floor( $('body').width() );
	var xDlg = Math.floor( $('body').width() * 0.66);
					
	$( idDlg ).height(yBody).width(0).css('left',xBody);
	$( idDlg ).animate( { width: xDlg.toString(), left: '-=' + xDlg }, 400);
	
	tipoIdentificacion();
//	$( document).ready( function() {
//		$('.ui-calendar input').width( $('#datosPersonales\\:identificacion').width() );	
//	});
	
}//showDlg()


//funcion que se ejecutara cuando en "cv/dlgDatosPersonales.xhtml" se pulse sobre el botón/link de cerrar el dialogo
//se encarga de la 'animación' de desplazamiento de izquierda a derecha
//TODO : 'limpiar' los valores de los campos
function hideDlg(idDlg, widgetVar) {

	var xBody = Math.floor( $('body').width() );
					
	$( idDlg ).animate( { 
							left: '+=' + xBody.toString()},
							600, function() {
						 		PF( widgetVar ).hide(); 
						});
			
}//hideDlg()


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

	

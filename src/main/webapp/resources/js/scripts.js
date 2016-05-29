
function showDlg(idDlg) {

	var yBody = $('body').height();
	var xBody = Math.floor( $('body').width() );
	var xDlg = Math.floor( $('body').width() * 0.66);
					
	$( idDlg ).height(yBody).width(0).css('left',xBody);
	$( idDlg ).animate( { width: xDlg.toString(), left: '-=' + xDlg }, 400);
	
}//showDlg()


function hideDlg(idDlg, widgetVar) {

	var xBody = Math.floor( $('body').width() );
					
	$( idDlg ).animate( { 
							left: '+=' + xBody.toString()},
							600, function() {
						 		PF( widgetVar ).hide(); 
						});
			
}//hideDlg()

/**
 * Prueba usando "Simple Java Mail" (https://github.com/bbottema/simple-java-mail)
 * Siguiendo estas instrucciones "https://github.com/bbottema/simple-java-mail/issues/23#issuecomment-149468197", y además activando en el panel de control de la
 * cuenta de google correspondiente la sgte opción: 'Inicio de sesion y seguridad' --> 'Permitir el acceso de aplicaciones menos seguras: SÍ'
 */

package es.albarregas.utilidades;

//import javax.mail.internet.MimeMessage.RecipientType;
//
//import org.codemonkey.simplejavamail.Mailer;
//import org.codemonkey.simplejavamail.TransportStrategy;
//import org.codemonkey.simplejavamail.email.Email;
//
//public class EnviarCorreo {
//	
//	private final Email email = new Email();
//	
//	private final static String SERVER = "smtp.gmail.com";
//	private final static int PORT = 25;	//alternativos: 587,465
//	private final static String USERNAME = "befp.iesalbarregas";
//	private final static String PASSWORD = "befpbefp";
//	
//	private String registroSubject = "Registro en BEFP";
//	private String registroContenido = "Se ha procesado su registro en la web 'Bolsa de Empleo FP | BEFP'. A continuación el administrador procederá a verificar los datos que nos ha suministrado, y si son correctos, se le enviará un nuevo mensaje de correo electrónico para avisarle de que ya tiene acceso a la web."; 
//
//	
//	
//	public void configurar(String to, String toDir, String tipoMensaje) {
//		
//		this.email.setFromAddress( "Bolsa de Empleo FP | BEFP", "correo03012010" );
//		this.email.addRecipient( to, toDir, RecipientType.TO);
//		
//		if (tipoMensaje.equals("registroOK")) {
//			this.email.setSubject( this.registroSubject );		
//			this.email.setText( this.registroContenido );			
//		}
//		
//	}//configurar
//	
//	public void enviar() {
//		
//	   	new Mailer( SERVER,  PORT, USERNAME, PASSWORD, TransportStrategy.SMTP_TLS).sendMail(email);
//		
//	}//enviar
//
//		
//}//enviar

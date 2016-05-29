
/**
 * Esto actualmente no vale para nada, fue una prueba de intentar usar primefaces custom client side validators, que al final no fui capar de que hiciese
 * lo que pretendia
 */

package es.albarregas.utilidades;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

@FacesValidator("validarNif")
public class ValidadorNIFDNI implements Validator, ClientValidator {
		
	private static final String NIF_STRING = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	
	public ValidadorNIFDNI() {		
	}
	
	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValidatorId() {		
		return "validarNif";
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		if (value == null) {
			return;
		}
		
        //validar:
        //- debe tener longitud 9
        //- 8 primeros caracters numeros (obligar a poner un 0 al principio si es necesario)
        //- ultimo caracter letra
        //- que la letra sea la correcta
		
		if ( ((String)value).matches("\\d{8}[a-zA-Z]{1}") ) {
			
			//comprobar si la letra es incorrecta
	        String nif = (String) value;                
	        int numeros = Integer.parseInt(nif.substring(0,7));                
	        String letra = nif.substring(7).toUpperCase();

	        if (letra.charAt(0) == NIF_STRING.charAt(numeros % 23)) {
	            throw new ValidatorException(new FacesMessage ( FacesMessage.SEVERITY_ERROR, "Letra incorrecta", null) );
	        }//if
	        						
		} else {
			throw new ValidatorException( new FacesMessage (FacesMessage.SEVERITY_ERROR, "Formato incorrecto", null) );
		}//if..else
				
	}//validate

}//CLASS

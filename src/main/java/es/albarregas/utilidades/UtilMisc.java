package es.albarregas.utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilMisc {

	public static Date strMesAnnoToDate(String strMes, String strAnno) {

		Date date = null;
		String strFecha = String.format("%s %s", strMes, strAnno); 
		DateFormat format = new SimpleDateFormat("MM yyyy");
		
		try {
			date = format.parse(strFecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//System.out.println(date);				
		return date;
		
	}//convertirADate 
	
}//CLASS

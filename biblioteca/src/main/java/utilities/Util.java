package utilities;

import java.util.Calendar;
import java.util.Date;

public class Util {
	
	public static boolean esPalindroma(String palabra){

		String palabraSinEspacios=palabra.replace(" ", "");
		StringBuffer sb = new StringBuffer(palabraSinEspacios);
		sb = sb.reverse();
		
		return palabraSinEspacios.equalsIgnoreCase(sb.toString());
	}

	public static Date getFechaEntregaMaxima(String isbn) {
		
		Date fechaEntregaMaxima=null;
		
		char[] arreglo = isbn.toCharArray();
		int suma=0;
	    for (char caracter : arreglo){
	        if ( Character.isDigit(caracter) ){
	            suma+= Integer.valueOf(caracter);
	        }
	    } 
	    
	    if(suma>30){
	    	
	    	//calcular fechaEntregaMaxima max 15 dias
	    	Calendar c = Calendar.getInstance();	    	
	    	c.add(Calendar.DAY_OF_YEAR, -1);
	    	
	    	int i=1;
	    	while (i <= 15) {
	    		if(c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
	    			i++;
	    		}
	    		c.add(Calendar.DAY_OF_YEAR, 1);
			}
	    	
	    	fechaEntregaMaxima= c.getTime();
	    	
	    }
		
		return fechaEntregaMaxima;
	}

}

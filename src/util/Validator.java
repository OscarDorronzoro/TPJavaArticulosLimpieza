package util;

public class Validator {

	public static boolean isNullField(String[] campos) {
		
		boolean isNullField=false;
		int i=0;

		while(i<campos.length && !isNullField) {	
			if(campos[i]==null || campos[i].equals("")) {
				isNullField=true;
			}
		}
		return isNullField;
	}
}

package entities;

public class InformacionFiscal {

	private static String telefono;
	private static String direccion;
	private static String cuit;
	private static String razonSocial;
	
	public static String getTelefono() {
		return telefono;
	}
	public static void setTelefono(String telefono) {
		InformacionFiscal.telefono = telefono;
	}
	public static String getDireccion() {
		return direccion;
	}
	public static void setDireccion(String direccion) {
		InformacionFiscal.direccion = direccion;
	}
	public static String getCuit() {
		return cuit;
	}
	public static void setCuit(String cuit) {
		InformacionFiscal.cuit = cuit;
	}
	public static String getRazonSocial() {
		return razonSocial;
	}
	public static void setRazonSocial(String razonSocial) {
		InformacionFiscal.razonSocial = razonSocial;
	}
}

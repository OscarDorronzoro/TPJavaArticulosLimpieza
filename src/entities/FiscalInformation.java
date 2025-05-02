package entities;

public class FiscalInformation {

	private static String telefono;
	private static String direccion;
	private static String cuit;
	private static String razonSocial;
	
	public static String getTelefono() {
		return telefono;
	}
	public static void setTelefono(String telefono) {
		FiscalInformation.telefono = telefono;
	}
	public static String getDireccion() {
		return direccion;
	}
	public static void setDireccion(String direccion) {
		FiscalInformation.direccion = direccion;
	}
	public static String getCuit() {
		return cuit;
	}
	public static void setCuit(String cuit) {
		FiscalInformation.cuit = cuit;
	}
	public static String getRazonSocial() {
		return razonSocial;
	}
	public static void setRazonSocial(String razonSocial) {
		FiscalInformation.razonSocial = razonSocial;
	}
}

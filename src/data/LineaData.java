package data;

public abstract class LineaData {
	
	private ArticuloData articuloData;
	private ProveedorData proveedorData;
	
	public LineaData() {
		articuloData = new ArticuloData();
	}

	protected ArticuloData getArticuloData() {
		return articuloData;
	}

	protected void setArticuloData(ArticuloData articuloData) {
		this.articuloData = articuloData;
	}

	protected ProveedorData getProveedorData() {
		return proveedorData;
	}

	protected void setProveedorData(ProveedorData proveedorData) {
		this.proveedorData = proveedorData;
	}
}
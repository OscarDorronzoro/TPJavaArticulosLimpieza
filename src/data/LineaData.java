package data;

public abstract class LineaData {
	
	private ArticuloData articuloData;
	
	public LineaData() {
		articuloData = new ArticuloData();
	}

	protected ArticuloData getArticuloData() {
		return articuloData;
	}

	protected void setArticuloData(ArticuloData articuloData) {
		this.articuloData = articuloData;
	}
}
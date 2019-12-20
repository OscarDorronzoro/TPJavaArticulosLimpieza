package logic;

import java.util.ArrayList;

import data.LineaVentaData;
import entities.Linea;
import entities.Venta;
import util.ArticleException;
import util.CategoryException;
import util.PriceException;
import util.ProviderException;
import util.SaleLineException;

public class ABMCLineaVenta {
	
	private LineaVentaData lineaVentaData;
	private Venta miVenta;
	
	public ABMCLineaVenta(Venta venta) {
		this.setLineaVentaData(new LineaVentaData());
		miVenta=venta;
	}
	
	public void add(Linea linea) throws SaleLineException {
		this.getLineaVentaData().add(linea,miVenta.getNroVenta());
	}
	
	public ArrayList<Linea> getAllByVenta() throws ProviderException, ArticleException, SaleLineException, PriceException, CategoryException{
		return this.getLineaVentaData().getAllByVenta(miVenta.getNroVenta());
	}

	public LineaVentaData getLineaVentaData() {
		return lineaVentaData;
	}

	public void setLineaVentaData(LineaVentaData lineaVentaData) {
		this.lineaVentaData = lineaVentaData;
	}
}

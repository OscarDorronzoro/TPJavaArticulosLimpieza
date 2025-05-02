package logic;

import java.util.ArrayList;

import data.LineaVentaData;
import entities.Line;
import entities.Sale;
import util.ArticleException;
import util.CategoryException;
import util.PriceException;
import util.ProviderException;
import util.SaleLineException;

public class ABMCLineaVenta {
	
	private LineaVentaData lineaVentaData;
	private Sale sale;
	
	public ABMCLineaVenta(Sale sale) {
		this.setLineaVentaData(new LineaVentaData());
		this.sale = sale;
	}
	
	public void add(Line linea) throws SaleLineException {
		this.getLineaVentaData().add(linea, this.sale.getNroVenta());
	}
	
	public ArrayList<Line> getAllBySale() throws ProviderException, ArticleException, SaleLineException, PriceException, CategoryException{
		return this.getLineaVentaData().getAllBySale(this.sale.getNroVenta());
	}

	public LineaVentaData getLineaVentaData() {
		return lineaVentaData;
	}

	public void setLineaVentaData(LineaVentaData lineaVentaData) {
		this.lineaVentaData = lineaVentaData;
	}
}

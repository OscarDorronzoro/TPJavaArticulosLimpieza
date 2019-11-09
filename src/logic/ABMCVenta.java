package logic;


import java.util.Date;

import entities.Venta;
import util.SaleException;

public class ABMCVenta {

	public void registrarVenta(Venta venta) throws SaleException{
		venta.setLineas(venta.getCliente().getMiCarrito().getLineas());
		venta.setfEmision(new Date());
		
	}
}

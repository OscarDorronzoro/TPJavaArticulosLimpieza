package logic;


import java.util.Date;

import entities.Venta;

public class ABMCVenta {

	public void registrarVenta(Venta venta) {
		venta.setLineas(venta.getCliente().getMiCarrito().getLineas());
		venta.setfEmision(new Date());
		
	}
}

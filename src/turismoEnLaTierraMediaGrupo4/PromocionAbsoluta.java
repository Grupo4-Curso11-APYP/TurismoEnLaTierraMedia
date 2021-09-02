package turismoEnLaTierraMediaGrupo4;

import java.util.List;

public class PromocionAbsoluta extends PromocionBase {

	private double monto;

	public PromocionAbsoluta(String nombre,List<Atraccion> packAtracciones, TipoAtraccion tipo, double monto) {
		super(nombre, packAtracciones, tipo);
		this.monto = monto;
	}

	// se realiza el descuento absoluto para esta promocion
	@Override
	public double getCosto() {
		float precioFinal = 0;
		for (Atraccion atraccion : this.packAtracciones) {
			precioFinal += atraccion.getCosto();
		}

		precioFinal -= this.getMonto();
		return precioFinal;
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getMonto() {
		return monto;
	}

}

package turismoEnLaTierraMediaGrupo4;

import java.util.List;
import java.util.Objects;

public class PromocionAbsoluta extends PromocionBase {

	private double monto;

	public PromocionAbsoluta(String nombre,Atraccion []packAtracciones, TipoAtraccion tipo, double monto) {
		super(nombre, packAtracciones, tipo);
		this.monto = monto;
	}

	/*
	 * calcula  el descuento que se le aplica a esta promocion
	 */
	@Override
	public Double getCosto() {
		double precioFinal = 0;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(monto);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromocionAbsoluta other = (PromocionAbsoluta) obj;
		return Double.doubleToLongBits(monto) == Double.doubleToLongBits(other.monto);
	}

	
}

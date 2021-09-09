package turismoEnLaTierraMediaGrupo4;

import java.util.List;
import java.util.Objects;

public class PromocionAbsoluta extends PromocionBase {

	private double monto;

	public PromocionAbsoluta(String nombre,Atraccion []packAtracciones, TipoAtraccion tipo, double monto) {
		super(nombre, packAtracciones, tipo);
		this.monto = monto;
	}

	// se realiza el descuento absoluto para esta promocion
	@Override
	public Double getCosto() {
		return monto;
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return null;
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

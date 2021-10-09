package turismoEnLaTierraMediaGrupo4;

import java.util.Objects;

public class PromocionAbsoluta extends Promocion {

	private double monto;

	public PromocionAbsoluta(String nombre, Atraccion[] packAtracciones, TipoAtraccion tipo, double monto) {
		super(nombre, packAtracciones, tipo);
		this.monto = monto;
	}

	/*
	 * getCosto devuelve el monto final
	 */
	@Override
	public Double getCosto() {
		return monto;
	}

	@Override
	public String toString() {
		var aux = '\n' + getNombre() + ": " + "precio: " + getCosto() + ", duracion: " 
				+ getTiempo() + ", tipo: " + getTipo()
				+ ", atracciones incluidas: \n";
		for (Atraccion atraccion : packAtracciones) {
			aux += atraccion.toString();
		}
		return aux;
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

	@Override
	public int compareTo(Ofertable otro) {
		return -this.getCosto().compareTo(otro.getCosto());
	}

}

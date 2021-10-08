package turismoEnLaTierraMediaGrupo4;

import java.util.Objects;

public class PromocionPorcentual extends PromocionBase {

	private int descuento;

	public PromocionPorcentual(String nombre, Atraccion[] packAtracciones, TipoAtraccion tipo, int descuento) {
		super(nombre, packAtracciones, tipo);
		this.descuento = descuento;
	}

	// realiza el descuento porcentual para un pack de atracciones
	@Override
	public Double getCosto() {
		double precioFinal = 0;
		for (Atraccion atraccion : this.packAtracciones) {
			precioFinal += atraccion.getCosto();
		}

		precioFinal -= precioFinal / 100 * this.getDescuento();

		return precioFinal;
	}


	@Override
	public String toString() {
		var aux = '\n'  + getNombre() + ": " + "descuento: " + descuento + ", precio: " 
		+ getCosto() + ", duracion: " + getTiempo() + ", tipo: " + getTipo()
		+ ", atracciones incluidas: " + '\n';
		
		for (Atraccion atraccion : packAtracciones) {
			aux += atraccion.toString();
		}
		return aux;
	}

	/*
	 * se espera que devuelva el descuento
	 */
	public int getDescuento() {
		return descuento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(descuento);
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
		PromocionPorcentual other = (PromocionPorcentual) obj;
		return Double.doubleToLongBits(descuento) == Double.doubleToLongBits(other.descuento);
	}

	@Override
	public int compareTo(Ofertable otro) {
		return -this.getCosto().compareTo(otro.getCosto());
	}

}

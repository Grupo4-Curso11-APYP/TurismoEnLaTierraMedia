package turismoEnLaTierraMediaGrupo4;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PromocionAxB extends PromocionBase {

	private Atraccion atraccionGratis;

	public PromocionAxB(String nombre, Atraccion[] atraccion, TipoAtraccion tipo, Atraccion atraccionGratis) {
		super(nombre, atraccion, tipo);
		this.atraccionGratis = atraccionGratis;
	}

	
	
	/*
	 * se calcula el descuento de estas promociones y a su vez se le deposita a
	 * usuario una nueva atraccion
	 */

	@Override
	public Double getCosto() {
		double precioFinal = 0;
		for (Atraccion atraccion : this.packAtracciones) {
			precioFinal += atraccion.getCosto();
		}
		return precioFinal;
	}

	/*
	 * calcula el tiempo que necesita el usuario para adiquir una promocion
	 */
	@Override
	public Double getTiempo() {
		double tiempo = 0;
		for (Atraccion atraccion : this.packAtracciones) {
			tiempo += atraccion.getTiempo();
		}

		tiempo += this.atraccionGratis.getTiempo();
		return tiempo;
	}

	/*
	 * se espera que devuelva una atraccion gratis
	 */

	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}

	/*
	 * pregunta si hay cupo disponible tanto en el pack de atracciones como en la
	 * promocion que se otorga gratis
	 */
	public boolean hayCupo() {

		return ((this.packAtracciones[0].getCupoDisponible() > 0) && (this.packAtracciones[1].getCupoDisponible() > 0)
				&& this.atraccionGratis.getCupoDisponible() > 0);
	}

	/*
	 * si hay cupo disponible en la atraccion gratis se resta un cupo
	 */
	@Override
	public void reservarCupo() {
		for (Atraccion atraccion : packAtracciones) {
			atraccion.cupoDisponible -= 1;

		}
		atraccionGratis.cupoDisponible -= 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atraccionGratis);
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
		PromocionAxB other = (PromocionAxB) obj;
		return Objects.equals(atraccionGratis, other.atraccionGratis);
	}

	@Override
	public String toString() {
		String	res =" ";
		for (Atraccion atraccion : packAtracciones) {
			res = "PromocionAxB: nombre=" + getNombre() +" "+  atraccion.toString2() +
					 ", getTipo()=" + getTipo() +" "+"atraccionGratis=" + atraccionGratis;
		}
		return res;
	}

	



    

}

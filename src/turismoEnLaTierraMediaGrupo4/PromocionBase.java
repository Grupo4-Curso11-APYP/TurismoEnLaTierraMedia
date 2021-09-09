package turismoEnLaTierraMediaGrupo4;

import java.util.Arrays;
import java.util.Objects;

public abstract class PromocionBase implements Ofertable {

	private String nombre;
	protected Atraccion[] packAtracciones;// posiblemente se cambie a un array
	private TipoAtraccion tipo;
	private double tiempo;

	public PromocionBase(String nombre, Atraccion[] atraccion, TipoAtraccion tipo) {
		this.packAtracciones = atraccion;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	/*
	 * get costo se encargara de calcular los descuentos en cada promocion
	 */
	public abstract Double getCosto();

	public abstract String ToString();

	public Double getTiempo() {
		this.tiempo = 0;
		for (Atraccion atraccion : this.packAtracciones) {
			tiempo += atraccion.getTiempo();
		}

		return tiempo;
	}

	public TipoAtraccion getTipo() {
		return tipo;
	}

	// indica si la estructura esta llena
	@Override
	public boolean hayCupo() {

		return ((this.packAtracciones[0].getCupoDisponible() > 0) && 
				(this.packAtracciones[1].getCupoDisponible() > 0));
	}

	@Override
	public void  reservarCupo() {
		for (Atraccion atraccion : packAtracciones) {
			atraccion.cupoDisponible -= 1;
			}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(packAtracciones);
		result = prime * result + Objects.hash(nombre, tiempo, tipo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromocionBase other = (PromocionBase) obj;
		return Objects.equals(nombre, other.nombre) && Arrays.equals(packAtracciones, other.packAtracciones)
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo) && tipo == other.tipo;
	}
	
	
	
}

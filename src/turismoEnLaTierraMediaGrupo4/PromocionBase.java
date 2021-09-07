package turismoEnLaTierraMediaGrupo4;

import java.util.ArrayList;
import java.util.List;

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
	public double getCosto() {
		return 0;
	}

	public abstract String ToString();

	public double getTiempo() {
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
	
}

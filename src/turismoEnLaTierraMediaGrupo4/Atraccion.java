package turismoEnLaTierraMediaGrupo4;

import java.util.Objects;

public class Atraccion implements Ofertable {
	protected double costo;
	protected double tiempo;
	protected int cupoDisponible;
	private TipoAtraccion tipoAtraccion;
	private String nombre;

	public Atraccion(String nombre, double costo, double tiempo, int cupoDisponible, TipoAtraccion tipo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupoDisponible = cupoDisponible;
		this.tipoAtraccion = tipo;
	}


	public int getCupoDisponible() {
		return cupoDisponible;
	}

	public String getNombre() {
		return this.nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costo, nombre, tiempo, tipoAtraccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return costo == other.costo && Objects.equals(nombre, other.nombre) && tiempo == other.tiempo
				&& tipoAtraccion == other.tipoAtraccion;
	}

	

	public void reservarCupo() {
		if (this.hayCupo()) {
			this.cupoDisponible -= 1;
		}
// tirar excepcion si cupo <= 0
	}

	@Override
	public double getCosto() {
		
		return this.costo;
	}

	@Override
	public TipoAtraccion getTipo() {
		
		return this.tipoAtraccion;
	}

	@Override
	public boolean hayCupo() {
		return this.cupoDisponible > 0;
	}


	@Override
	public double getTiempo() {
	
		return this.tiempo;
	}
	
	


	
	
	
}

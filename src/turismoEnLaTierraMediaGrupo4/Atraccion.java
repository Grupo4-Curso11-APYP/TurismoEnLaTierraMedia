package turismoEnLaTierraMediaGrupo4;

import java.util.Objects;

public class Atraccion implements Ofertable {
	protected double costo;
	protected double tiempo;
	protected int cupoDisponible;
	private TipoAtraccion tipoAtraccion;
	private String nombre;

	/*
	 * @Param nombre , costo, tiempo , cupoDisponible , tipo se inicializan todos
	 * los atributos de la atraccion
	 */
	public Atraccion(String nombre, double costo, double tiempo, int cupoDisponible, TipoAtraccion tipo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupoDisponible = cupoDisponible;
		this.tipoAtraccion = tipo;
	}
	
	public Atraccion(String nombre ) {
		this.nombre = nombre;
	}

	/*
	 * se espera que devuelva el cupo disponible
	 */
	public int getCupoDisponible() {
		return cupoDisponible;
	}

	/*
	 * se espera que devuelva el nombre
	 */
	@Override
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

	/*
	 * Metodo reservar cupo : si todavia hay cupo de atraccion se restara un cupo
	 * para decir que esta reservado
	 */
	public void reservarCupo() {
		if (this.hayCupo()) {
			this.cupoDisponible -= 1;
		}

	}

	/*
	 * se espera que devuelva el costo
	 */
	@Override
	public Double getCosto() {

		return this.costo;
	}

	/*
	 * se espera que devuelva el tipo
	 */
	@Override
	public TipoAtraccion getTipo() {

		return this.tipoAtraccion;
	}

	/*
	 * Metodo hayCupo : pregunta si todavia hay cupo disponible
	 */
	@Override
	public boolean hayCupo() {
		return this.cupoDisponible > 0;
	}

	/*
	 * se espera que devuelva el tiempo
	 */
	@Override
	public Double getTiempo() {

		return this.tiempo;
	}

	@Override
	public String toString() {
		return getNombre() + ": " + "precio: " + getCosto() + ", duracion: " 
				+ getTiempo() + ", tipo: " + getTipo() + '\n';
	}
	
}

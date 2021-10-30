package turismoEnLaTierraMediaGrupo4;

import java.sql.SQLException;
import java.util.Objects;

import dao.AtraccionDAOImpl;

public class Atraccion implements Ofertable, Comparable<Ofertable> {
	protected double costo;
	protected double tiempo;
	protected int cupoDisponible;
	private TipoAtraccion tipoAtraccion;
	private String nombre;

	/*
	 * @Param nombre , costo, tiempo , cupoDisponible y tipo se inicializan todos
	 * los atributos de la atraccion
	 */
	public Atraccion(String nombre, double costo, double tiempo, int cupoDisponible, TipoAtraccion tipo) throws Exception {
		this.nombre = nombre;
		validandoCosto(costo);
		validandoTiempo(tiempo);
		validandoCupo(cupoDisponible);
		this.tipoAtraccion = tipo;
	}

	/*
	 * validaci�n para evitar costo negativo, si lo es, lanza una excepci�n
	 */
	private void validandoCosto(double costo) throws Exception {
		if (costo < 0.0)
			throw new CostoNegativoExcepcion();
		this.costo = costo;
	}
	/*
	 * validaci�n para evitar tiempo negativo, si lo es, lanza una excepci�n
	 */
	private void validandoTiempo(double tiempo) throws Exception {
		if (tiempo < 0.0)
			throw new SinTiempoDisponible();
		this.tiempo = tiempo;
	}
	/*
	 * validaci�n para evitar cupo negativo, si lo es, lanza una excepci�n
	 */
	private void validandoCupo(int cupoDisponible) throws Exception {
		if (cupoDisponible < 0)
			throw new CupoNegativoException();
		this.cupoDisponible = cupoDisponible;
	}

	/*
	 * @param nombre permite pasar un nombre para inicializar el atributo nombre
	 * Constructor auxiliar usado para comparar con el nombre de la lista de
	 * ofertables.
	 */
	public Atraccion(String nombre) {
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
	public void reservarCupo() throws SQLException {
		AtraccionDAOImpl aDAO = new AtraccionDAOImpl();
		if (this.hayCupo()) {
			this.cupoDisponible -= 1;
			aDAO.update(this);
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

	/*
	 * aplica toString a las atracciones para que se muestren en el formato
	 * requerido. Aplica un salto de l�nea con \n para mayor legibilidad.
	 */
	@Override
	public String toString() {
		return getNombre() + ": " + "precio: " + getCosto() + ", duracion: " + getTiempo() + ", tipo: " + getTipo()
				+ '\n';
	}

	@Override
	public int compareTo(Ofertable otro) {
		return -this.getCosto().compareTo(otro.getCosto());
	}

}

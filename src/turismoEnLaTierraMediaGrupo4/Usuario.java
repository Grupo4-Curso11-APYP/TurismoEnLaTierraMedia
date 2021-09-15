package turismoEnLaTierraMediaGrupo4;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private TipoAtraccion tipoFavorito;
	private List<Ofertable> ofertables; // Las sugerencias que va aceptando.

	public Usuario(String nombre, double presupuesto, double tiempoDisponible, TipoAtraccion tipoFavorito)
			throws Exception {
		this.nombre = nombre;
		validandoPresupuesto(presupuesto);
		validandoTiempoDisponible(tiempoDisponible);
		this.tipoFavorito = tipoFavorito;
		this.ofertables = new ArrayList<>();

	}

	/*
	 * validación para evitar presupuesto negativo, si lo es, lanza una excepción
	 */
	private void validandoPresupuesto(double presupuesto) throws Exception {
		if (presupuesto < 0.0)
			throw new SinMontoDisponible();
		this.presupuesto = presupuesto;
	}

	/*
	 * validación para evitar tiempo negativo, si lo es, lanza una excepción
	 */
	private void validandoTiempoDisponible(double tiempo) throws Exception {
		if (tiempo < 0.0)
			throw new SinTiempoDisponible();
		this.tiempoDisponible = tiempo;
	}

	/*
	 * se espera que devuelva el tiempo disponible
	 */
	public double getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	/*
	 * se espera que devuelva el presupuesto
	 */
	public double getPresupuesto() {
		return this.presupuesto;
	}

	/*
	 * se espera que devuelva el tipo favorito de atraccion
	 */
	public TipoAtraccion getTipoFavorito() {
		return this.tipoFavorito;
	}

	/*
	 * se espera que devuelva el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * se espera que devuelva una lista de ofertables
	 */
	public List<Ofertable> getOfertables() {
		return this.ofertables;
	}

	/*
	 * Muestra todos los datos del usuario mas sus ofertables aceptados en formato
	 * itinerario, calculando el tiempo y costo total para completar su agenda.
	 */
	@Override
	public String toString() {

		double horas = 0;
		double costoFinal = 0;
		for (Ofertable ofertable : ofertables) {
			horas += ofertable.getTiempo();
			costoFinal += ofertable.getCosto();
		}

		var aux = '\n' + "Usuario: " + nombre + ", presupuesto: " + presupuesto + ", tiempo disponible: "
				+ tiempoDisponible + ", tipo favorito: " + tipoFavorito + '\n'
				+ "Su itinerario final le tomará un total " + "de: " + horas + " horas; con un costo final de: "
				+ (int) costoFinal + " monedas." + '\n' + "Sugerencias incluidas:\n";

		for (Ofertable ofertable : ofertables) {
			aux += ofertable.toString();
		}
		return aux;
	}

	/*
	 * @Param tiempo metodo privado para restar un tiempo al atributo
	 * tiempoDisponible
	 */
	double restarTiempo(double tiempo) {
		return this.tiempoDisponible -= tiempo;
	}

	/*
	 * @Param monto metodo privado para restar un presupuesto al atributo
	 * presupuesto
	 */
	double restarPresupuesto(double monto) {
		return this.presupuesto -= monto;
	}

	/*
	 * @Param o pasa el ofertable que el usuario va a comprar al aceptar sugerencia
	 * una vez que el usuario compra un ofertable sugerido se le restara el
	 * presupuesto y el tiempo del ofertable comprado finalmente guarda el ofertable
	 * en su lista de ofertables
	 */
	public void comprarOfertable(Ofertable o) {
		double tiempoO = o.getTiempo();
		double presupuesto = o.getCosto();

		restarTiempo(tiempoO);
		restarPresupuesto(presupuesto);
		ofertables.add(o);

	}

}

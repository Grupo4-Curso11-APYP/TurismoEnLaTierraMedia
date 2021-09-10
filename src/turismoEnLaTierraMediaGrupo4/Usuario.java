package turismoEnLaTierraMediaGrupo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario  {
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private TipoAtraccion tipoFavorito;
	private List<Ofertable> ofertables;

	public Usuario(String nombre,double presupuesto, double tiempoDisponible, TipoAtraccion tipoFavorito) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoFavorito = tipoFavorito;
		this.ofertables = new ArrayList<>();

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
	public List<Ofertable> getOfertables(){
		return this.ofertables;
	}
	
/*
 * se muestran todos los datos del usuario
 */
	@Override
	public String toString() {
		
			double horas = 0;
			double costoFinal = 0;
			for (Ofertable ofertable: ofertables) {
				horas += ofertable.getTiempo();
				costoFinal += ofertable.getCosto();
			}
			return "[Su itinerario final es:" + ofertables + ". Le tomará un total "
					+ "de:" + horas + "horas; con un costo final de:" + costoFinal 
					+ "monedas.]";
		}

	
	/*
	 * @Param tiempo
	 *  metodo privado para calcular el tiempo que gasta un usuario
	 */
	private double restarTiempo(double tiempo) {
		return this.tiempoDisponible  -=tiempo;
	}
	/*
	 *@Param monto
	 *metodo privado para calcular cuanto presupuesto gasto el usuario 
	 */
	private double restarPresupuesto(double monto) {
		return this.presupuesto-= monto;
	}
	
	/*
	 * @Param o
	 * una vez que el usuario compra una oferta sugerida 
	 * se les restara el presupuesto y el tiempo de la oferta comprada 
	 * una vez confirme la compra se guardara la oferta en una lista de ofertas
	 */
	public void comprarOfertable(Ofertable o) {
      	double tiempoO = o.getTiempo();
      	double presupuesto = o.getCosto();
     
      	restarTiempo(tiempoO);
      	restarPresupuesto(presupuesto);
      	ofertables.add(o);
		
	}



}

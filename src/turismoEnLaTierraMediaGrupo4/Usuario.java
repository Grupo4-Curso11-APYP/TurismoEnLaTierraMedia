package turismoEnLaTierraMediaGrupo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private TipoAtraccion tipoFavorito;
	private List<Ofertable> ofertables;

	public Usuario(String nombre, double presupuesto, double tiempoDisponible, 
			TipoAtraccion tipoFavorito) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoFavorito = tipoFavorito;
		this.ofertables = new ArrayList<>();

	}

	public double getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public double getPresupuesto() {
		return this.presupuesto;
	}

	public TipoAtraccion getTipoFavorito() {
		return this.tipoFavorito;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Ofertable> getOfertables() {
		return this.ofertables;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", presupuesto=" + presupuesto + ","
				+ " tiempoDisponible=" + tiempoDisponible
				+ ", tipoFavorito=" + tipoFavorito + ", ofertables=" 
				+ ofertables + "]";
	}

	private double restarTiempo(double tiempo) {
		return this.tiempoDisponible -= tiempo;
	}

	private double restarPresupuesto(double monto) {
		return this.presupuesto -= monto;
	}

	public void comprarOfertable(Ofertable o) {
		double tiempoO = o.getTiempo();
		double presupuesto = o.getCosto();

		restarTiempo(tiempoO);
		restarPresupuesto(presupuesto);
		ofertables.add(o);

	}

}

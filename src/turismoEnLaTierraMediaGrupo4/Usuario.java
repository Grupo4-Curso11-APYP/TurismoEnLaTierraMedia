package turismoEnLaTierraMediaGrupo4;

import java.util.Objects;

public class Usuario {
	private String nombre;
	private float presupuesto;
	private float tiempoDisponible;
	private TipoAtraccion tipoFavorito;
	private Atraccion [] atracciones;
	private PromocionBase [] promociones;
	
	

	public Usuario(String nombre, float presupuesto, float tiempoDisponible, TipoAtraccion tipoFavorito) {
		this.tiempoDisponible = tiempoDisponible;
		this.presupuesto = presupuesto;
		this.tipoFavorito = tipoFavorito;
		this.atracciones = new Atraccion[0];
		this.promociones = new PromocionPorcentual[0];
	}

	public int getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public double getPresupuesto() {
		return this.presupuesto;
	}

	public TipoAtraccion getTipoFavorito() {
		return this.tipoFavorito;
	}
	
	public String Itinerario() {
		
		return "algo";
	}

}

package turismoEnLaTierraMediaGrupo4;

import java.util.Objects;

public class Usuario {
	private String nombre;
	private float presupuesto;
	private float tiempoDisponible;
	private TipoAtraccion  tipoFavorito;
	private Atraccion[]  atracciones;
	private PromocionBase [] promociones;
	



	public Usuario(String nombre, float presupuesto, float tiempoDisponible, TipoAtraccion tipoFavorito) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoFavorito = tipoFavorito;
		this.atracciones = new Atraccion[0];
		this.promociones = new PromocionPorcentual[0];
	}

	public float getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public float getPresupuesto() {
		return this.presupuesto;
	}

	public TipoAtraccion getTipoFavorito() {
		return this.tipoFavorito;
	}
	
	
	public Atraccion[] getAtracciones() {
		return atracciones;
	}



	public PromocionBase [] getPromociones() {
		return promociones;
	}
	
	
	
	
	// se mostrara un resumen del itinerario del usuario , posiblemente se guarde en un archivo  
	public String Itinerario() {
		
		return "algo";
	}
	
	

}

package turismoEnLaTierraMediaGrupo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
	private String nombre;
	private float presupuesto;
	private float tiempoDisponible;
	private TipoAtraccion  tipoFavorito;
	private List<Atraccion>  atracciones;
	private List<PromocionBase> promociones;
	


	

	public Usuario(String nombre, float presupuesto, float tiempoDisponible, TipoAtraccion tipoFavorito) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoFavorito = tipoFavorito;
		this.atracciones = new ArrayList<>();
		this.promociones = new ArrayList<>();
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
	
	
	
	
	
	
	public String getNombre() {
		return nombre;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public List<PromocionBase> getPromociones() {
		return promociones;
	}

	// se mostrara un resumen del itinerario del usuario , posiblemente se guarde en un archivo  
	public String Itinerario() {
		
		return "algo";
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", presupuesto=" + presupuesto + ", tiempoDisponible=" + tiempoDisponible
				+ ", tipoFavorito=" + tipoFavorito + ", atracciones=" + atracciones + ", promociones=" + promociones
				+ "]";
	}
	
	

}

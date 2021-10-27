package turismoEnLaTierraMediaGrupo4;

public class Itinerario {
	private String usuario2; 
	private Double presupuesto; 
	private Double tiempoDisponible; 
	private TipoAtraccion tipoFav;
	private String nombrePromo;
	private int atraccion1;
	private int atraccion2;
	private int atraccionGratis; 
	private TipoAtraccion tipo;
	private String nombreAtraccion; 
	private Double costo;
	private Double tiempo; 
	private TipoAtraccion tipoAtraccion;
	
	
	public Itinerario(String usuario2, Double presupuesto, Double tiempoDisponible, TipoAtraccion tipoFav,
			String nombrePromo, int atraccion1, int atraccion2, int atraccionGratis, TipoAtraccion tipo,
			String nombreAtraccion, Double costo, Double tiempo, TipoAtraccion tipoAtraccion) {
	}


	@Override
	public String toString() {
		return "Itinerario [usuario2=" + usuario2 + ", presupuesto=" + presupuesto + ", tiempoDisponible="
				+ tiempoDisponible + ", tipoFav=" + tipoFav + ", nombrePromo=" + nombrePromo + ", atraccion1="
				+ atraccion1 + ", atraccion2=" + atraccion2 + ", atraccionGratis=" + atraccionGratis + ", tipo=" + tipo
				+ ", nombreAtraccion=" + nombreAtraccion + ", costo=" + costo + ", tiempo=" + tiempo
				+ ", tipoAtraccion=" + tipoAtraccion + "]";
	}

	
}

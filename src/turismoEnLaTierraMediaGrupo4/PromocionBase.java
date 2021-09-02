package turismoEnLaTierraMediaGrupo4;

import java.util.ArrayList;
import java.util.List;

public abstract class PromocionBase {

	private String nombre;
	protected List<Atraccion> packAtracciones;
	private TipoAtraccion tipo;

	
	public PromocionBase(String nombre,List<Atraccion> atraccion, TipoAtraccion tipo) {
	this.packAtracciones = atraccion;
    this.nombre = nombre;
    this.tipo = tipo;
	}

	public PromocionBase() {
		this.packAtracciones = new ArrayList<>();
	}
	
	
// metodo para agregar atracciones al packDeAtracciones  
	
	public void agregar(Atraccion a) {
	 packAtracciones.add(a);
	}

	public String getNombre() {
		return nombre;
	}

	
	/*
	 *  get costo se encargara de calcular los descuentos en cada  promocion 
	 */
	public abstract double getCosto();

	public abstract String ToString();

	public double getTiempo() {
		float tiempo = 0;
		for (Atraccion atraccion : this.packAtracciones) {
			tiempo += atraccion.getTiempo();
		}

		return tiempo;
	}

	public TipoAtraccion getTipo() {
		return tipo;
	}

	// indica si la estructura esta llena
	public boolean llena() {
		boolean atraccion = false;

   for (Atraccion atra : packAtracciones) {
	    	    if(atra.cupoDisponible <= 0){
    	        atraccion = true;
    	    }
    	}
    	if(atraccion == false){
    	    System.out.println("Array is full");
       
    }
      return  atraccion;
	}
}

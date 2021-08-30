package turismoEnLaTierraMediaGrupo4;

public abstract class PromocionBase {

	private String nombre;
	protected Atraccion[] packAtracciones;
	private TipoAtraccion tipo;

	// las clases abstractas no instancian por lo que solo iniciaria el array en el
	// constructor
	public PromocionBase(int numAtraccionesEnPromo) {
		this.packAtracciones = new Atraccion[numAtraccionesEnPromo];

	}

	
	
// metodo para agregar atracciones al packDeAtracciones  
	
	public void agregar(Atraccion a) {
		// Crear nuevoArray con length = array.length + 1
		Atraccion[] arrayAuxiliar = new Atraccion[packAtracciones.length + 1];
		// Copiar los valores de array en otro array nuevoArray
		for (int i = 0; i < packAtracciones.length; i++) {
			arrayAuxiliar[i] = packAtracciones[i];
		}
		// Poner el nuevo valor en nuevoArray
		arrayAuxiliar[arrayAuxiliar.length - 1] = a;
		// Sobreescribir el valor de array
		this.packAtracciones = arrayAuxiliar;
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

    	for(int i = 0; i < packAtracciones.length; i++){
    	    if(packAtracciones[i].cupoDisponible <= 0){
    	        atraccion = true;
    	    }
    	}
    	if(atraccion == false){
    	    System.out.println("Array is full");
       
    }
      return  atraccion;
	}
}

package turismoEnLaTierraMediaGrupo4;

public class PromocionesAxB extends PromocionBase {

	private Atraccion atraccionGratis;

	public PromocionesAxB(String nombre, int numAtraccionesEnPromo, TipoAtraccion tipo, Atraccion atraccionGratis) {
		super(numAtraccionesEnPromo);

		this.atraccionGratis = atraccionGratis;
	}

	
	// se calcula el descuento de estas promociones y a su vez  se le deposita a usuario una nueva atraccion 
	@Override
	public double getCosto() {
		float precioFinal = 0;
		for (Atraccion atraccion : this.packAtracciones) {
			precioFinal += atraccion.getCosto();
		}
		return precioFinal;
	}

	@Override
	public double getTiempo() {
		float tiempo = 0;
		for (Atraccion atraccion : this.packAtracciones) {
			tiempo += atraccion.getTiempo();
		}

		tiempo += this.atraccionGratis.getTiempo();
		return tiempo;
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean llena() {
		boolean atraccion = false;

		for (int i = 0; i < packAtracciones.length; i++) {
			if (packAtracciones[i].cupoDisponible <= 0 && getAtraccionGratis().cupoDisponible <= 0) {
				atraccion = true;
			}
		}
		if (atraccion == false) {
			System.out.println("Array is full");

		}
		return atraccion;
	}

	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}

}

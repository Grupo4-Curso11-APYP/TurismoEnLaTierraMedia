package turismoEnLaTierraMediaGrupo4;

import java.util.List;

public class PromocionAxB extends PromocionBase {

	private Atraccion atraccionGratis;

	

	
	public PromocionAxB(String nombre, Atraccion[] atraccion, TipoAtraccion tipo,Atraccion atraccionGratis) {
		super(nombre, atraccion, tipo);
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


	

	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}


	public boolean hayCupo() {

		return ((this.packAtracciones[0].getCupoDisponible() > 0) && 
				(this.packAtracciones[1].getCupoDisponible() > 0) && this.atraccionGratis.getCupoDisponible() >0);
	}
	
	@Override 
	public void reservarCupo() {
		for (Atraccion atraccion : packAtracciones) {
			atraccion.cupoDisponible -= 1;
			
		}
		atraccionGratis.cupoDisponible -=1;
	}



	

}

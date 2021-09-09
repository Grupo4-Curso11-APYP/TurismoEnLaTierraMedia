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
	public Double getCosto() {
		double precioFinal = 0;
		for (Atraccion atraccion : this.packAtracciones) {
			precioFinal += atraccion.getCosto();
		}
		return precioFinal;
	}

	@Override
	public Double getTiempo() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atraccionGratis);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromocionAxB other = (PromocionAxB) obj;
		return Objects.equals(atraccionGratis, other.atraccionGratis);
	}

	

}

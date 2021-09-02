package turismoEnLaTierraMediaGrupo4;

import java.util.Objects;


public class Atraccion{
	protected int costo;
	protected double tiempo;
	protected int cupoDisponible;
	private TipoAtraccion tipoAtraccion;
	private String nombre;
	

public Atraccion(String nombre,int costo,double tiempo,int cupoDisponible,TipoAtraccion tipo) {
	this.nombre = nombre;
	this.costo = costo;
	this.tiempo = tiempo;
	this.cupoDisponible =   cupoDisponible;
	this.tipoAtraccion = tipo;
}



	public double getTiempo() {
		return tiempo;
	}

	public int getCosto() {
		return costo;
	}

	public int getCupoDisponible() {
		return cupoDisponible;
	}

	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(costo, nombre, tiempo, tipoAtraccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return costo == other.costo && Objects.equals(nombre, other.nombre) && tiempo == other.tiempo
				&& tipoAtraccion == other.tipoAtraccion;
	}

	
	   public boolean llena() {
	        return (this.cupoDisponible <= 0);
	    }

	    public void reservarCupo() {
	        if (!this.llena()) {
	            this.cupoDisponible -= 1;
	        }
	       //Se debe tirar excepción o algo acá
	    }



}

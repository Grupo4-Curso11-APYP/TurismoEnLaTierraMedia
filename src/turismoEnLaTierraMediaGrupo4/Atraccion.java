package turismoEnLaTierraMediaGrupo4;

import java.util.Objects;

public class Atraccion implements Comparable<Atraccion> {

	protected int costoDeVisita;
	protected double promedioDeVisita;
	protected int cupoDePersonas;
	private TipoDeAtraccion tipoAtraccion;
	private String nombreAtraccion;
	private Usuario usuario;

	public Atraccion(int costoDeVisita, double promedioDeVisita, int cupoDePersonas, TipoDeAtraccion tipoAtraccion,
			String nombreAtraccion, Usuario usuario) {
		
		this.costoDeVisita = costoDeVisita;
		this.promedioDeVisita = promedioDeVisita;
		this.cupoDePersonas = cupoDePersonas;
		this.tipoAtraccion = tipoAtraccion;
		this.nombreAtraccion = nombreAtraccion;
		this.usuario = usuario;
	}

	public int getCostoDeVisita() {
		return costoDeVisita;
	}

	public double getPromedioDeVisita() {
		return promedioDeVisita;
	}

	public int getCupoDePersonas() {
		return cupoDePersonas;
	}

	public TipoDeAtraccion getTipoAtraccion() {
		return tipoAtraccion;
	}

	public String getNombreAtraccion() {
		return nombreAtraccion;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(costoDeVisita, cupoDePersonas, nombreAtraccion, promedioDeVisita, usuario);
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
		return costoDeVisita == other.costoDeVisita && Objects.equals(cupoDePersonas, other.cupoDePersonas)
				&& Objects.equals(nombreAtraccion, other.nombreAtraccion)
				&& Double.doubleToLongBits(promedioDeVisita) == Double.doubleToLongBits(other.promedioDeVisita)
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public int compareTo(Atraccion o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "Atraccion [costoDeVisita=" + costoDeVisita + ", promedioDeVida=" + promedioDeVisita + ", cupoDePersonas="
				+ cupoDePersonas + ", tipoAtraccion=" + tipoAtraccion + ", nombreAtraccion=" + nombreAtraccion
				+ ", usuario=" + usuario + "]";
	}

	
	
	
}

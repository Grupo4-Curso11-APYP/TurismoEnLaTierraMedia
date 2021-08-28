package turismoEnLaTierraMediaGrupo4;

import java.util.Objects;

public class Usuario {
	private int tiempoParaVisita;
	private double presupuesto;
	private TipoDeAtraccion atraccionFavorita;
	private  Atraccion [] usuarioAtraccion;
	
	

	public Usuario(int tiempoParaVisita, double presupuesto, TipoDeAtraccion atraccionFavorita,int indice) {
		super();
		this.tiempoParaVisita = tiempoParaVisita;
		this.presupuesto = presupuesto;
		this.atraccionFavorita = atraccionFavorita;
		this.usuarioAtraccion = new Atraccion[indice];
	}

	public int getTiempoParaVisita() {
		return tiempoParaVisita;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public TipoDeAtraccion getAtraccionFavorita() {
		return atraccionFavorita;
	}

	

	public Atraccion[] getUsuarioAtraccion() {
		return usuarioAtraccion;
	}

	public void setUsuarioAtraccion(Atraccion[] usuarioAtraccion) {
		this.usuarioAtraccion = usuarioAtraccion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atraccionFavorita, presupuesto, tiempoParaVisita);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(atraccionFavorita, other.atraccionFavorita)
				&& Double.doubleToLongBits(presupuesto) == Double.doubleToLongBits(other.presupuesto)
				&& tiempoParaVisita == other.tiempoParaVisita;
	}

}

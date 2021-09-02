package turismoEnLaTierraMediaGrupo4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Sistema {
	protected List<Atraccion> atraccion;
	protected List<Usuario> usuarios;
	protected List<PromocionBase> promocion;

	public Sistema() {
		this.atraccion = new ArrayList<>();
		this.promocion = new ArrayList<>();
		this.usuarios = new ArrayList<>();
	}

	/*
	 * @Param se pasa por parametro una lista de usuarios
	 * 
	 * @Variable cant sirve para cuando se cumple la condicion valla contrando todas
	 * las que cumplen
	 * 
	 * @variable indice es para que pueda recorrer el segundo arrayy donde se van a
	 * guardar las sugerencias ahora si funciona xdxd
	 */
	public List<Usuario> sugerir(Usuario usuario) {
		List<Usuario> usuari = new ArrayList<>();

		for (PromocionBase pr : promocion) {
			if ((usuario.getTipoFavorito().equals(pr.getTipo())) && (usuario.getPresupuesto()) >= pr.getCosto()
					&& usuario.getTiempoDisponible() >= pr.getTiempo()) {
				usuari.add(usuario);
			}
		}

		return usuari;

	}

	/*
	 * @Param c se pasa por parametro el criterio por el cual se va a ordenar
	 */
	public void ordenar(Comparator<Atraccion> c) {
		atraccion.sort(c);

	}

	public void nuevoUsuario(Usuario usuario) {
		usuarios.add(usuario);

	}

	public void nuevaAtraccion(Atraccion atrac) {
		atraccion.add(atrac);

	}

	@Override
	public int hashCode() {
		return Objects.hash(atraccion, promocion, usuarios);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sistema other = (Sistema) obj;
		return Objects.equals(atraccion, other.atraccion) && Objects.equals(promocion, other.promocion)
				&& Objects.equals(usuarios, other.usuarios);
	}

	public List<Atraccion> getAtraccion() {
		return atraccion;
	}

	public List<Usuario> getUsuarios() {
//		for (int i = 0; i < usuarios.size(); i++) {
//			
//			
//		}
		return usuarios;
	}

	public List<PromocionBase> getPromocion() {
		return promocion;
	}

	@Override
	public String toString() {
		return "Sistema [atraccion=" + atraccion + ", usuarios=" + usuarios + ", promocion=" + promocion + "]";
	}

	public void nuevaPromocionPorcentual(String string, List<Atraccion> listAtraccion, int descuento,
			TipoAtraccion aventura) {
		promocion.add(new PromocionPorcentual(string, listAtraccion, aventura, descuento));

	}

	public void nuevaPromocionAbsoluta(String string, List<Atraccion> listAtraccion, int monto,
			TipoAtraccion aventura) {
		promocion.add(new PromocionAbsoluta(string, listAtraccion, aventura, monto));

	}

	public void nuevaPromocionAxB(String string, List<Atraccion> listAtraccion, TipoAtraccion aventura,
			Atraccion atraccionGratis) {
		promocion.add(new PromocionAxB(string, listAtraccion, aventura, atraccionGratis));

	}

}

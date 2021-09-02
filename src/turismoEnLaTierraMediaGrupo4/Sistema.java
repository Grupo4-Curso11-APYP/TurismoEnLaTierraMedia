package turismoEnLaTierraMediaGrupo4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Sistema {
	protected List<Atraccion> atraccion;
	protected List<Usuario> usuarios;
	protected List<PromocionBase> promocion;

	/*
	 * Se inicializan las listas en ArrayList<>
	 */
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
	 * se ordena por criterio de Mayor Costo De Atraccion
	 */
	public void ordenarPorCostoDeAtraccion() {
		Collections.sort(atraccion, new MayorCostoDeAtraccion());

	}

	/*
	 * se ordena por criterio de Mayor Tiempo De Visita
	 */
	public void ordenarPorTiempoDeVisita() {
		Collections.sort(atraccion, new MayorTiempoDeVisita());

	}

	/*
	 * @Param usuario se pasa por parametro un usuario el cual se va a agregar a la
	 * lista
	 * 
	 */
	public void nuevoUsuario(Usuario usuario) {
		usuarios.add(usuario);

	}
/*
 * @Param atrac  se pasa por parametro una atraccion la  cual se va a agregar a la
	 * lista
 */
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

	/*
	 *  devuelve la lista de atracciones
	 */
	public List<Atraccion> getAtraccion() {
		return atraccion;
	}

	/*
	 *  devuelve la lista de usuarios
	 */
	public List<Usuario> getUsuarios() {

		return usuarios;
	}

	
	 /*
	  *  
	  */
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

 
	public void obtenerPromocion(String archivo){
		List<PromocionPorcentual> listaPromocion = new ArrayList<>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(archivo));

			while (sc.hasNext()) {
				// leo cada linea del archivo
				String linea = sc.nextLine();
				String datos[] = linea.split(",");
				// creo una Persona a partir de los datos leídos de la línea.
				String nombre= datos[0];
//				Atraccion atracci = datos[1];
			  int descuento = Integer.parseInt(datos[2]);
			  String tipo  = datos[3];
				// agrego esa persona a la lista, siempre y cuando no esté repetida
			  
//			PromocionPorcentual pp = new PromocionPorcentual(nombre, atracci, tipo, descuento);

//				if ( ! listaPromocion.contains(pp)) {
//					 listaPromocion.add(pp);
//			}
		}} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// cierro el archivo
		sc.close();

		
	}
	
	
}

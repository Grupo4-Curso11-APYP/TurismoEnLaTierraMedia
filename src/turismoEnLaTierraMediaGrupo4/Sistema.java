package turismoEnLaTierraMediaGrupo4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Sistema {

	protected List<Usuario> usuarios;
	protected List<Ofertable> ofertableList;

	/*
	 * Se inicializan las listas en ArrayList<>
	 */
	public Sistema() {

		this.ofertableList = new ArrayList<>();
		this.usuarios = new ArrayList<>();
	}

	/*
	 * metodo sugerir filtra las ofertas segun las posibilades de cada usuario una
	 * vez elegida la oferta , el usuario confirmara y se guardara su compra
	 */
	public void sugerir() {
		for (Usuario usu : usuarios) {
			ordenarOfertasSegunPreferencia(usu.getTipoFavorito());
			for (Ofertable ofertable : ofertableList) {

				if (ofertable.getTipo().equals(usu.getTipoFavorito()) && ofertable.hayCupo()
						&& usu.getPresupuesto() >= ofertable.getCosto()
						&& usu.getTiempoDisponible() >= ofertable.getTiempo()
						&& !(usu.getOfertables().contains(ofertable))) {
				}
				Scanner sc = new Scanner(System.in);
				System.out.println("Ingrese S para aceptar");
				char ingreso = sc.next().charAt(0);
				if (ingreso == 's') {
					usu.comprarOfertable(ofertable);
					ofertable.reservarCupo();
					usu.toString();

				}

			}
		}
	}

	/*
	 * @Param t se compara el tipo de atraccion con la atraccion favorita del
	 * usuario se ordena la coleccion segun la preferencia del usuario
	 */
	public void ordenarOfertasSegunPreferencia(TipoAtraccion t) {
		Collections.sort(ofertableList, new OfertaSegunPreferencia(t));
	}

	/*
	 * @Param usuario se pasa por parametro un usuario el cual se va a agregar a la
	 * lista
	 * 
	 */
	public void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	/*
	 * @param promocion se encarga de agregar una promocion a la lista de ofertables
	 */
	public void agregarPromociones(PromocionBase promocion) {
		ofertableList.add(promocion);
	}

	/*
	 * @Param atraccion se encarga de agregar una atraccion a la lista de ofertables
	 */
	public void agregarAtraccion(Atraccion atraccion) {
		ofertableList.add(atraccion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ofertableList, usuarios);
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
		return Objects.equals(ofertableList, other.ofertableList) && Objects.equals(usuarios, other.usuarios);
	}

	/*
	 * devuelve la lista de usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/*
	 * devuelve una lista de ofertables
	 */
	public List<Ofertable> getOfertableList() {
		return ofertableList;
	}

	/*
	 * metodo toString devuelve el contenido de las listas del sistema
	 */
	@Override
	public String toString() {
		return "Sistema [ofertas=" + ofertableList + ", usuarios=" + usuarios + "  ]";
	}

}

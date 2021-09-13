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
	public void sugerir() throws IOException {
		for (Usuario usu : usuarios) {
			ordenarOfertasSegunPreferencia(usu.getTipoFavorito());
			for (Ofertable ofertable : ofertableList) {

				if (ofertable.hayCupo()
						&& usu.getPresupuesto() >= ofertable.getCosto()
						&& usu.getTiempoDisponible() >= ofertable.getTiempo()
						&& !(usu.getOfertables().contains(ofertable))) {
					System.out.println("Sugerencia diaria de " +
						usu.getNombre() + ":");
					System.out.println(ofertable);
					Scanner sc = new Scanner(System.in);
					System.out.println("Pulse S para aceptar la sugerencia o"
							+ "cualquier otra letra para continuar");
					char ingreso = sc.next().charAt(0);
					if (ingreso == 's' || ingreso == 'S') {
						usu.comprarOfertable(ofertable);
						ofertable.reservarCupo();
						
				}
				
				}

			}
			System.out.println(usu.toString());
			EscribirItinerarios.salidaItinerario(usu);
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

	public void agregarUsuariosDesdeArchivo() {
		this.usuarios = ManejadorArchivos.obtenerUsuarioDesdeArchivo();
	}

	public void agregarOfertables() {
		this.ofertableList.addAll(ManejadorArchivos.cargarPromociones(this.ofertableList));
	}

	public void agregarAtraccion() {
		this.ofertableList.addAll(ManejadorArchivos.obtenerAtraccionesPorAchivo());
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
		var aux = "Sistema ofertas: \n";
		for (var ofertable : ofertableList) {
			aux += ofertable.toString();
		}
		return aux;
	}

	public static void main(String[] args) throws IOException {
		Sistema sistema = new Sistema();
		sistema.agregarAtraccion();
		sistema.agregarOfertables();
		sistema.agregarUsuariosDesdeArchivo();
		sistema.sugerir();
	}

}

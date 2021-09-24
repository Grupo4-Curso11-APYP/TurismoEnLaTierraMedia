package turismoEnLaTierraMediaGrupo4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Sistema {

	protected List<Usuario> usuarios;
	protected List<Ofertable> ofertableList; // contiene atracciones y promos.
						// Mejorar con TreeSet con param del
						//comparator, que lo use en la
						// construccion del TreeSet

	/*
	 * Se inicializan las listas en ArrayList<>
	 */
	public Sistema() {

		this.ofertableList = new ArrayList<>();
		this.usuarios = new ArrayList<>();
	}

	/*
	 * metodo sugerir utiliza el comparator para ordenar los ofertables que va a
	 * sugerir a cada usuario del sistema hasta que al mismo no le quede dinero o
	 * tiempo, siempre y cuando las atracciones tengan cupo y sin ofrecer un
	 * ofertable de nuevo que ya haya sido adquirido. El usuario acepta mediante una
	 * entrada de teclado y la sugerencia se guarda en su lista de ofertables Se
	 * muestra itinerario y se genera un archivo de salida para cada usuario.
	 */
	public void sugerir() throws IOException {
		for (Usuario usuario : usuarios) {
			ordenarOfertasSegunPreferencia(usuario.getTipoFavorito());
			for (Ofertable ofertable : ofertableList) {

				if (ofertable.hayCupo() && usuario.getPresupuesto() >= ofertable.getCosto()
						&& usuario.getTiempoDisponible() >= ofertable.getTiempo()
						&& !(usuario.getOfertables().contains(ofertable))) {
					System.out.println("Sugerencia diaria de " + usuario.getNombre() + ":");
					System.out.println(ofertable);
					System.out.println("Pulse S  para aceptar la sugerencia o");
					System.out.println("cualquier otra letra para continuar y luego Enter");
					Scanner sc = new Scanner(System.in);
					char ingreso = sc.next().charAt(0);
					if (ingreso == 's' || ingreso == 'S') {
						usuario.comprarOfertable(ofertable);
						ofertable.reservarCupo();

					}

				}

			}
			System.out.println(usuario.toString());
			EscribirItinerarios.salidaItinerario(usuario);
		}
	}

	/*
	 * @Param favorita permite pasar el tipo de atraccion favorita del usuario.
	 * ordena la lista de ofertables segun preferencia del usuario y otros criterios
	 * del comparator.
	 */
	public void ordenarOfertasSegunPreferencia(TipoAtraccion favorita) {
		Collections.sort(ofertableList, new OfertaSegunPreferencia(favorita));
	}

	/*
	 * Carga los usuarios en sistema
	 */
	public void agregarUsuariosDesdeArchivo() throws Exception {
		this.usuarios = ManejadorArchivos.obtenerUsuarioDesdeArchivo();
	}

	/*
	 * Carga las promociones en sistema
	 */
	public void agregarPromociones() {
		this.ofertableList.addAll(ManejadorArchivos.cargarPromociones(this.ofertableList));
	}

	/*
	 * Carga las atracciones en sistema
	 */
	public void agregarAtraccion() throws Exception {
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
	 * devuelve la lista de ofertables
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

	/*
	 * metodo main ejecuta el programa.
	 */
	public static void main(String[] args) throws Exception {
		Sistema sistema = new Sistema();
		sistema.agregarAtraccion();
		sistema.agregarPromociones();
		sistema.agregarUsuariosDesdeArchivo();
		sistema.sugerir();
	}

}

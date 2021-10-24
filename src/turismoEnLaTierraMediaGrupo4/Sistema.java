package turismoEnLaTierraMediaGrupo4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import dao.AtraccionDAOImpl;
import dao.UsuarioDaoImpl;

public class Sistema {

	protected Set<Usuario> usuarios;
	protected Set<Ofertable> ofertables; // contiene atracciones y promos.
											// Mejorar con TreeSet con param del
											//comparator, que lo use en la
											// construccion del TreeSet

	/*
	 * Se inicializan las listas en ArrayList<>
	 */
	public Sistema() {

		this.ofertables = new TreeSet<Ofertable>();
		this.usuarios = new LinkedHashSet<Usuario>();
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
			this.ofertables = ordenarOfertasSegunPreferencia(usuario.getTipoFavorito());
			for (Ofertable ofertable : ofertables) {

				if (ofertable.hayCupo() && usuario.getPresupuesto() >= ofertable.getCosto()
						&& usuario.getTiempoDisponible() >= ofertable.getTiempo()
						) {
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
	public Set<Ofertable> ordenarOfertasSegunPreferencia(TipoAtraccion favorita) {
		Set<Ofertable> ofertasAOrdenar = new TreeSet<Ofertable>(new OfertaSegunPreferencia(favorita));
		ofertasAOrdenar.addAll(this.ofertables);
		return ofertasAOrdenar;
	}

	/*
	 * Carga los usuarios en sistema
	 */
	public void agregarUsuariosDesdeArchivo() throws Exception {
		this.usuarios.addAll(  ManejadorArchivos.cargarUsuarios());
	}

	/*
	 * Carga las promociones en sistema
	 */
	public void agregarPromociones() {
		this.ofertables.addAll(ManejadorArchivos.cargarPromociones(this.ofertables));
	}

	/*
	 * Carga las atracciones en sistema
	 */
	public void agregarAtraccion() throws Exception {
		this.ofertables.addAll(ManejadorArchivos.cargarAtracciones());
	}

	@Override
	public int hashCode() {
		return Objects.hash(ofertables, usuarios);
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
		return Objects.equals(ofertables, other.ofertables) && Objects.equals(usuarios, other.usuarios);
	}

	/*
	 * devuelve la lista de usuarios
	 */
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	/*
	 * devuelve la lista de ofertables
	 */
	public Set<Ofertable> getOfertableList() {
		return ofertables;
	}

	/*
	 * metodo toString devuelve el contenido de las listas del sistema
	 */
	@Override
	public String toString() {
		var aux = "Sistema ofertas: \n";
		for (var ofertable : ofertables) {
			aux += ofertable.toString();
		}
		return aux;
	}

	/*
	 * metodo main ejecuta el programa.
	 */
	public static void main(String[] args) throws Exception {
		Sistema sistema = new Sistema();
		/*
		sistema.agregarAtraccion();
		sistema.agregarPromociones();
		sistema.agregarUsuariosDesdeArchivo();
		sistema.sugerir();
		*/
		AtraccionDAOImpl aDAO = new AtraccionDAOImpl();
		UsuarioDaoImpl uDAO = new UsuarioDaoImpl();
		System.out.println(uDAO.findAll());
	}

}

package turismoEnLaTierraMediaGrupo4;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import dao.AtraccionDAOImpl;
import dao.ItinerarioDAOImpl;
import dao.PromocionDAOImpl;
import dao.UsuarioDaoImpl;

public class Sistema {

	protected Set<Usuario> usuarios;
	protected Set<Ofertable> ofertables;
	private ItinerarioDAOImpl iDAO;
	private AtraccionDAOImpl aDAO;
	private UsuarioDaoImpl uDAO;
	private PromocionDAOImpl pDAO;

	/*
	 * Se inicializan las listas en ArrayList<>
	 */
	public Sistema() {

		this.ofertables = new TreeSet<Ofertable>();
		this.usuarios = new LinkedHashSet<Usuario>();
		this.iDAO = new ItinerarioDAOImpl();
		this.aDAO = new AtraccionDAOImpl();
		this.pDAO = new PromocionDAOImpl();
		this.uDAO = new UsuarioDaoImpl();

	}

	/*
	 * metodo sugerir utiliza el comparator para ordenar los ofertables que va a
	 * sugerir a cada usuario del sistema hasta que al mismo no le quede dinero o
	 * tiempo, siempre y cuando las atracciones tengan cupo y sin ofrecer un
	 * ofertable de nuevo que ya haya sido adquirido. El usuario acepta mediante una
	 * entrada de teclado y la sugerencia se guarda en su lista de ofertables Se
	 * muestra itinerario y se genera un archivo de salida para cada usuario.
	 */
	public void sugerir() throws IOException, SQLException {

		for (Usuario usuario : usuarios) {
			this.ofertables = ordenarOfertasSegunPreferencia(usuario.getTipoFavorito());
			usuario.getOfertables().addAll(iDAO.findByNombre(usuario.getNombre())); // carga de bd el itinerario del
																																			  // usuario en su lista de ofertable
																																			 // por si no es la primera vez que entro.																																			
			for (Ofertable ofertable : ofertables) { 
				if (ofertable.hayCupo() && usuario.getPresupuesto() >= ofertable.getCosto()
						&& usuario.getTiempoDisponible() >= ofertable.getTiempo()) {
					System.out.println("Sugerencia diaria de " + usuario.getNombre() + ":");
					System.out.println(ofertable);
					System.out.println("Pulse S  para aceptar la sugerencia o");
					System.out.println("cualquier otra letra para continuar y luego Enter");
					Scanner sc = new Scanner(System.in);
					char ingreso = sc.next().charAt(0);

					if (ingreso == 's' || ingreso == 'S') {
						usuario.comprarOfertable(ofertable);
						ofertable.reservarCupo();
						/**
						 * Se guarda itinerario
						 */
						iDAO.insertar(usuario.getNombre(), ofertable.getNombre());

						/**
						 * Se actualiza los cupos de las atracciones
						 */
						if (ofertable instanceof Promocion) {
							pDAO.update((Promocion) ofertable);
						} else {
							aDAO.update((Atraccion) ofertable);
						}
					}
				}

			}
			System.out.println(usuario.toString());

			/**
			 * Se actualiza presupuesto y tiempo disponible del usuario
			 */
			uDAO.update(usuario);

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
		this.usuarios.addAll(ManejadorArchivos.cargarUsuarios());
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

		AtraccionDAOImpl aDAO = new AtraccionDAOImpl();
		UsuarioDaoImpl uDAO = new UsuarioDaoImpl();
		PromocionDAOImpl pDAO = new PromocionDAOImpl();
		ItinerarioDAOImpl iDAO = new ItinerarioDAOImpl();

		sistema.getUsuarios().addAll(uDAO.findAll());
		sistema.getOfertableList().addAll(aDAO.findAll());
		sistema.getOfertableList().addAll(pDAO.findAll());

		// System.out.println(sistema.getOfertableList());

		sistema.sugerir();

	}
}

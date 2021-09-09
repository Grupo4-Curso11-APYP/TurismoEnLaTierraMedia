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

	public void sugerir() {
		for (Usuario usu : usuarios) {
			ordenarOfertasSegunPreferencia(usu.getTipoFavorito());
			for (Ofertable ofertable : ofertableList) {

				if (ofertable.getTipo().equals(usu.getTipoFavorito()) 
						&& ofertable.hayCupo()
						&& usu.getPresupuesto() >= ofertable.getCosto()
						&& usu.getTiempoDisponible() >= ofertable.getTiempo()
						&& !(usu.getOfertables().contains(ofertable))) {

					Scanner sc = new Scanner(System.in);
					System.out.println("Presione S para aceptar o cualquier letra "
									+ "para continuar recibiendo sugerencias");
					char ingreso = sc.next().charAt(0);
					if (ingreso == 's' || ingreso == 'S') {
						usu.comprarOfertable(ofertable);
						ofertable.reservarCupo();
					}

				}

			}
		}
	}

	public void ordenarOfertasSegunPreferencia(TipoAtraccion t) {
		Collections.sort(ofertableList, new OfertaSegunPreferencia(t));
	}

	/*
	 * @Param usuario se pasa por parametro un usuario el cual se va a agregar 
	 * a la lista
	 * 
	 */
	public void nuevoUsuario(Usuario usuario) {
		usuarios.add(usuario);
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
	 * devuelve la lista de atracciones
	 */

	/*
	 * devuelve la lista de usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Ofertable> getOfertableList() {
		return ofertableList;
	}
	/*
	 *  
	 */

	@Override
	public String toString() {
		return "Sistema [ofertas=" + ofertableList + ", usuarios=" + usuarios + "  ]";
	}

	@SuppressWarnings({ "finally", "resource" })
	public List<PromocionAxB> obtenerPromocionAxB(String archivo) {
		FileReader fr = null;
		BufferedReader br = null;
		List<PromocionAxB> promo = new ArrayList<>();

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea = br.readLine();

			while (linea != null) {
				try {
					String[] in = linea.split("|");
					String promoAxb = in[0];
					String atributos = in[1];
					String atracciones = in[2];

					if ((promoAxb).equals("PromoAxB")) {
						String[] datosAxB = atributos.split(",");
						String nombre = datosAxB[0];
						TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class,
								datosAxB[1].trim().toUpperCase());
						Object[] atraccioneAxB = atracciones.split(";");
						Atraccion.class.cast(atraccioneAxB[0]);
						Atraccion.class.cast(atraccioneAxB[1]);
						Atraccion.class.cast(atraccioneAxB[2]);
						Atraccion atraccionGratis = Atraccion.class.cast(atraccioneAxB[3]);

						PromocionAxB promoAxB = new PromocionAxB(nombre, (Atraccion[]) atraccioneAxB, tipo,
								atraccionGratis);

						promo.add(promoAxB);
						linea = br.readLine();

					}
					return promo;
				} catch (ClassCastException e) {
					System.err.println("error de casteo ");
				}
			}

			return promo;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return promo;
		}
	}

	@SuppressWarnings({ "resource", "finally" })
	public List<PromocionPorcentual> obtenerPromocionPorcentual(String archivo) {
		FileReader fr = null;
		BufferedReader br = null;
		List<PromocionPorcentual> promo = new ArrayList<>();

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea = br.readLine();

			while (linea != null) {
				try {
					String[] in = linea.split("|");
					String lineaPromoP = in[0];
					String atributos = in[1];
					String atracciones = in[2];

					if ((lineaPromoP).equals("PromoPorcentual")) {
						String[] datosPorcentual = atributos.split(",");
						String nombre = datosPorcentual[0];
						TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class,
								datosPorcentual[1].trim().toUpperCase());
						Object[] atraccionePorcentual = atracciones.split(";");
						Atraccion.class.cast(atraccionePorcentual[0]);
						Atraccion.class.cast(atraccionePorcentual[1]);
						Atraccion.class.cast(atraccionePorcentual[2]);

						PromocionPorcentual promoP = new PromocionPorcentual(nombre, (Atraccion[]) atraccionePorcentual,
								tipo, 0);

						promo.add(promoP);
						linea = br.readLine();

					}
					return promo;
				} catch (ClassCastException e) {
					System.err.println("los casteos son invalidos");
				}
			}

			return promo;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return promo;
		}
	}

}

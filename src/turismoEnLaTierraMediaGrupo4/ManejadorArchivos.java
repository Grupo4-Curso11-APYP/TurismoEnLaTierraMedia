package turismoEnLaTierraMediaGrupo4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ManejadorArchivos {

	public static List<Usuario> obtenerUsuarioDesdeArchivo() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		List<Usuario> usuarios = null;

		try {
			archivo = new File("entrada/Usuario.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			usuarios = new ArrayList<Usuario>();

			String linea = br.readLine();
			while (linea != null) {
				String[] datosUsuarios = linea.split(",");
				String nombre = datosUsuarios[0];
				double presupuesto = Double.parseDouble(datosUsuarios[1]);
				double tiempoDisponible = Double.parseDouble(datosUsuarios[2]);
				TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class, datosUsuarios[3].trim().toUpperCase());

				usuarios.add(new Usuario(nombre, presupuesto, tiempoDisponible, tipo));
				linea = br.readLine();
			}

			return usuarios;

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
		}

		return usuarios;
	}

	public static List<Ofertable> obtenerAtraccionesPorAchivo() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		List<Ofertable> atraccion = null;

		try {
			archivo = new File("entrada/Atraccion.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			atraccion = new ArrayList<Ofertable>();

			String linea = br.readLine();
			while (linea != null) {
				String[] datosAtraccion = linea.split(",");
				String nombre = datosAtraccion[0];
				double presupuesto = Double.parseDouble(datosAtraccion[1]);
				double tiempoDisponible = Double.parseDouble(datosAtraccion[2]);
				int cupoDisponible = Integer.parseInt(datosAtraccion[3]);
				TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class, datosAtraccion[4].trim().toUpperCase());

				atraccion.add(new Atraccion(nombre, presupuesto, tiempoDisponible, cupoDisponible, tipo));
				linea = br.readLine();
			}

			return atraccion;

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
		}

		return atraccion;
	}

	public static List<Ofertable> obtenerPromocionPorcentual(Sistema sistema) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		List<Ofertable> promoP = null;
		// PromocionPorcentual : nombre ,atraccion [] , tipoAtraccion , descuento
		try {
			archivo = new File("entrada/PromocionPorcentual.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			promoP = new ArrayList<Ofertable>();
			String linea = br.readLine();
			while (linea != null) {
				String[] datosPromosP = linea.split(",");
				String nombre = datosPromosP[0];
				String[] atraccionesString = datosPromosP[1].split(";");
				Atraccion[] atracciones = new Atraccion[atraccionesString.length];
				for (int i = 0; i < atraccionesString.length; i++) {
					atracciones[i] = (Atraccion) sistema.getAtraccionPorNombre(atraccionesString[i]);
				}
				TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class, datosPromosP[3].trim().toUpperCase());
				int descuento = Integer.parseInt(datosPromosP[4]);
				promoP.add(new PromocionPorcentual(nombre, atracciones, tipo, descuento));
				linea = br.readLine();
			}

			return promoP;

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
		}

		return promoP;
	}

	public static List<Ofertable> obtenerPromocionAbsoluta() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		List<Ofertable> promoAbs = null;
		// PromocionPorcentual : nombre ,atraccion [] , tipoAtraccion , descuento
		try {
			archivo = new File("entrada/PromocionAbsoluta.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			promoAbs = new ArrayList<Ofertable>();
			String linea = br.readLine();
			while (linea != null) {
				String[] datosPromosAbs = linea.split(",");
				String nombre = datosPromosAbs[0];
				String[] atraccionString = datosPromosAbs[1].split(";");
				Atraccion[] atraccion = new Atraccion[atraccionString.length];
				TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class, datosPromosAbs[3].trim().toUpperCase());
				int descuento = Integer.parseInt(datosPromosAbs[4]);
				promoAbs.add(new PromocionAbsoluta(nombre, atraccion, tipo, descuento));
				linea = br.readLine();
			}

			return promoAbs;

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
		}

		return promoAbs;
	}

	public static List<Ofertable> obtenerPromocionAxB() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		List<Ofertable> promoAxB = null;
		// PromocionPorcentual : nombre ,atraccion [] , tipoAtraccion , descuento
		try {
			archivo = new File("entrada/PromocionAxB.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			promoAxB = new ArrayList<Ofertable>();
			String linea = br.readLine();
			while (linea != null) {
				String[] datosPromosAxB = linea.split(",");
				String nombre = datosPromosAxB[0];
				String[] atraccionString = datosPromosAxB[1].split(";");
				Atraccion[] atraccion = new Atraccion[atraccionString.length];
				TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class, datosPromosAxB[3].trim().toUpperCase());
				String nombreGratis = datosPromosAxB[4];
				Atraccion atracciongratis = new Atraccion(nombreGratis, 0, 0, 0, tipo);
				promoAxB.add(new PromocionAxB(nombre, atraccion, tipo, atracciongratis));
				linea = br.readLine();
			}

			return promoAxB;

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
		}

		return promoAxB;
	}

	public static void main(String[] args) {
		Sistema sis = new Sistema();

		sis.agregarPromociones((PromocionBase) ManejadorArchivos.obtenerPromocionPorcentual());
		sis.agregarPromociones((PromocionBase) ManejadorArchivos.obtenerPromocionAbsoluta());
  
	}

}

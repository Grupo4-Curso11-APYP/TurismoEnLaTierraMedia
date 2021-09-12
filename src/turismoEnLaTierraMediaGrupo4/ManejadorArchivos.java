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
			archivo = new File("C:\\Users\\Jere\\eclipse-workspace\\turismoEnlaEdadMedia.grupo4.tp1.arg\\entrada\\Usuario.txt");
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
			archivo = new File("C:\\Users\\Jere\\eclipse-workspace\\turismoEnlaEdadMedia.grupo4.tp1.arg\\entrada\\Atraccion.txt");
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

	public static List<Ofertable> obtenerPromocionPorcentual() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		List<Ofertable> promoP = null;
		// PromocionPorcentual : nombre ,atraccion [] , tipoAtraccion , descuento
		try {
			archivo = new File("C:\\Users\\Jere\\eclipse-workspace\\turismoEnlaEdadMedia.grupo4.tp1.arg\\entrada\\PromocionesPorcentuales.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			promoP = new ArrayList<Ofertable>();
			String linea = br.readLine();
			while (linea != null) {
				
				String[] datosPromosP = linea.split(",");
				
				String nombre = datosPromosP[0];

				
				String[] atraccionesString = datosPromosP[1].split(";");
				
				Atraccion[] atracciones = new Atraccion[atraccionesString.length];
				atracciones[0] = new Atraccion(atraccionesString[0]);
				atracciones[1] = new Atraccion(atraccionesString[1]);
//				for (int i = 0; i < atraccionesString.length; i++) {
//					atracciones[i] = (Atraccion) sistema.filtrarAtraccionPorNombre(atraccionesString[i]);
//				}
				TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class, datosPromosP[2].trim().toUpperCase());
				int descuento = Integer.parseInt(datosPromosP[3]);

				

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
			archivo = new File("C:\\Users\\Jere\\eclipse-workspace\\turismoEnlaEdadMedia.grupo4.tp1.arg\\entrada\\PromocionAbsoluta.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			promoAbs = new ArrayList<Ofertable>();
			String linea = br.readLine();
			while (linea != null) {
				String[] datosPromosAbs = linea.split(",");
				String nombre = datosPromosAbs[0];
				String[] atraccionString = datosPromosAbs[1].split(";");
				
				Atraccion[] atraccion = new Atraccion[atraccionString.length];
				atraccion[0] = new Atraccion(atraccionString[0]);
				atraccion[1] = new Atraccion(atraccionString[1]);
				TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class, datosPromosAbs[2].trim().toUpperCase());
				int descuento = Integer.parseInt(datosPromosAbs[3]);
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

		List<Ofertable> promoAxb = null;
        
		try {
			archivo = new File("C:\\Users\\Jere\\eclipse-workspace\\turismoEnlaEdadMedia.grupo4.tp1.arg\\entrada\\PromocionAxB.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

		
			promoAxb = new  ArrayList<Ofertable>();
			

			String linea = br.readLine();
			while (linea != null) {
				String[] datosPromos = linea.split(",");
             	String nombre = datosPromos[0];
		
				String[] atraccionString = datosPromos[1].split(";");
				Atraccion[] atraccion = new Atraccion[atraccionString.length];	
				atraccion[0] = new Atraccion(atraccionString[0]);
				atraccion[1] = new Atraccion(atraccionString[1]);

				TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class, datosPromos[2].trim().toUpperCase());
				String nombreG = datosPromos[3];
                Atraccion atracGratis = new Atraccion(nombreG, 1, 5, 10, tipo);
				promoAxb.add(new PromocionAxB(nombre, atraccion, tipo, atracGratis));
				linea = br.readLine();
			}

			return promoAxb;

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

		return promoAxb;
	}

}

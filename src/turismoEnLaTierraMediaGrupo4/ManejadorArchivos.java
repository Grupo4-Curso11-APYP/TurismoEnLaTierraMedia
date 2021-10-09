package turismoEnLaTierraMediaGrupo4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class ManejadorArchivos {

	/*
	 * Lee los datos del archivo Usuario.txt
	 * Instancia usuarios a partir de esos datos
	 * Devuelve esos usuarios
	 */
	public static List<Usuario> cargarUsuarios() throws Exception {
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
	
	/*
	 * Lee los datos del archivo Atraccion.txt
	 * Instancia atracciones a partir de esos datos
	 * Devuelve esas atracciones
	 */
	public static List<Ofertable> cargarAtracciones() throws Exception {
		String path = new File("entrada/Atraccion.txt").getAbsolutePath();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		List<Ofertable> atracciones = null;

		try {
			archivo = new File(path);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			atracciones = new ArrayList<Ofertable>();

			String linea = br.readLine();
			while (linea != null) {
				String[] datosAtraccion = linea.split(",");
				String nombre = datosAtraccion[0];
				double presupuesto = Double.parseDouble(datosAtraccion[1]);
				double tiempoDisponible = Double.parseDouble(datosAtraccion[2]);
				int cupoDisponible = Integer.parseInt(datosAtraccion[3]);
				TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class, datosAtraccion[4].trim().toUpperCase());

				atracciones.add(new Atraccion(nombre, presupuesto, tiempoDisponible, cupoDisponible, tipo));
				linea = br.readLine();
			}

			return atracciones;

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

		return atracciones;
	}
	
	/*
	 * Lee los datos del archivo Promociones.txt
	 * Instancia las diferentes promociones a partir de esos datos
	 * Devuelve esas promociones
	 */
	public static List<Ofertable> cargarPromociones(Set<Ofertable> ofertableList){

		String path  = new File("entrada/Promociones.txt").getAbsolutePath();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<Ofertable> listaOfertables = null;

		try{
			listaOfertables = new ArrayList<Ofertable>();
			archivo = new File(path);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea = br.readLine();
			while (linea != null) {
				
				//Distingue promociones porcentuales
				if (linea.contains("Porcentual"))
				{
					String[] datosPromosP = linea.split(",");

					String nombre = datosPromosP[1];
					String[] atraccionesString = datosPromosP[2].split(";");

					Atraccion[] atracciones = new Atraccion[atraccionesString.length];
					atracciones[0] = new Atraccion(atraccionesString[0]);
					atracciones[1] = new Atraccion(atraccionesString[1]);
					for (int i = 0; i < atraccionesString.length; i++) {
						for (var ofertable: ofertableList) {

							if(ofertable.getNombre().equals(atracciones[i].getNombre()))
							{
								atracciones[i] = (Atraccion) ofertable;
							}
						}
					}

					TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class, datosPromosP[3].trim().toUpperCase());
					int descuento = Integer.parseInt(datosPromosP[4]);

					listaOfertables.add(new PromocionPorcentual(nombre, atracciones, tipo, descuento));
				}

				// Distingue promociones absolutas
				if(linea.contains("Absoluta"))
				{
					String[] datosPromosAbs = linea.split(",");
					String nombre = datosPromosAbs[1];
					String[] atraccionesString = datosPromosAbs[2].split(";");

					Atraccion[] atracciones = new Atraccion[atraccionesString.length];
					atracciones[0] = new Atraccion(atraccionesString[0]);
					atracciones[1] = new Atraccion(atraccionesString[1]);
					for (int i = 0; i < atraccionesString.length; i++) {
						for (var ofertable: ofertableList) {

							if(ofertable.getNombre().equals(atracciones[i].getNombre()))
							{
								atracciones[i] = (Atraccion) ofertable;
							}
						}
					}

					TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class, datosPromosAbs[3].trim().toUpperCase());
					int descuento = Integer.parseInt(datosPromosAbs[4]);
					listaOfertables.add(new PromocionAbsoluta(nombre, atracciones, tipo, descuento));
				}

				// Distingue promociones AxB
				if(linea.contains("AxB"))
				{
					String[] datosPromos = linea.split(",");
					String nombre = datosPromos[1];

					String[] atraccionesString = datosPromos[2].split(";");
					Atraccion[] atracciones = new Atraccion[atraccionesString.length];
					atracciones[0] = new Atraccion(atraccionesString[0]);
					atracciones[1] = new Atraccion(atraccionesString[1]);

					for (int i = 0; i < atraccionesString.length; i++) {
						for (var ofertable: ofertableList) {

							if(ofertable.getNombre().equals(atracciones[i].getNombre()))
							{
								atracciones[i] = (Atraccion) ofertable;
							}
						}
					}

					TipoAtraccion tipo = TipoAtraccion.valueOf(TipoAtraccion.class, datosPromos[3].trim().toUpperCase());
					String nombreG = datosPromos[4];

					Atraccion atracGratis = new Atraccion(nombreG);

					for (var ofertable: ofertableList) {
						if (ofertable.getNombre().equals(atracGratis.getNombre()))
							atracGratis = (Atraccion) ofertable;
					}

					listaOfertables.add(new PromocionAxB(nombre, atracciones, tipo, atracGratis));
				}
				linea = br.readLine();
			}

			return listaOfertables;
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();

			}
		}
		return  listaOfertables;
	}

}

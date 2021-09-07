package turismoEnLaTierraMediaGrupo4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArchivoPromocion {

	@SuppressWarnings({ "finally", "resource" })
	public static List<PromocionAxB> obtenerLineaAxB(String archivo) {
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
	public static List<PromocionPorcentual> obtenerLineaPorcentual(String archivo) {
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

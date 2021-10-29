package turismoEnLaTierraMediaGrupo4;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import dao.PromocionDAOImpl;

public class PromocionTest {

	Promocion aventuraPorcentual;
	Promocion degustacionAbsoluta;
	Promocion paisajeAxB;
	Atraccion a1;
	Atraccion a2;
	Atraccion a3;
	Atraccion a4;
	Atraccion a5;
	Atraccion a6;
	Atraccion aGratis;

	@Before
	public void setUp() throws Exception {

		a1 = new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA);
		a2 = new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA);
		a3 = new Atraccion("Lothl�rien", 35, 1, 30, TipoAtraccion.DEGUSTACION);
		a4 = new Atraccion("La Comarca", 3, 6.5, 150, TipoAtraccion.DEGUSTACION);
		a5 = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE);
		a6 = new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE);
		aGratis = new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.PAISAJE);

		// Pack de atracciones que contiene cada promoci�n

		Atraccion[] porcentual = new Atraccion[2];
		porcentual[0] = a1;
		porcentual[1] = a2;

		Atraccion[] absoluta = new Atraccion[2];
		absoluta[0] = a3;
		absoluta[1] = a4;

		Atraccion[] aXb = new Atraccion[2];
		aXb[0] = a5;
		aXb[1] = a6;

		// Promociones

		aventuraPorcentual = new PromocionPorcentual("Pack Aventura", porcentual, TipoAtraccion.AVENTURA, 20);

		degustacionAbsoluta = new PromocionAbsoluta("Pack de degustaci�n", absoluta, TipoAtraccion.DEGUSTACION, 36);

		paisajeAxB = new PromocionAxB("Pack paisajes", aXb, TipoAtraccion.PAISAJE, aGratis);
	}

	@Test
	public void queAlCrearCadaTipoDePromoNingunaSeaNull() {
		assertNotNull(aventuraPorcentual);
		assertNotNull(degustacionAbsoluta);
		assertNotNull(paisajeAxB);
	}

	@Test
	public void queConPromoPorcentualDeBosqueNegroYMordorY20DescuentoSale22_4() {
		assertEquals(22.4, aventuraPorcentual.getCosto(), 0.0001);
	}

	@Test
	public void queConPromoPorcentualDeBosqueNegroYMordorElTiempoEs7() {
		assertEquals(7, aventuraPorcentual.getTiempo(), 0);
	}

	@Test
	public void queConPromoAbsolutaDeLothlorienYComarcaSale36() {
		assertEquals(36, degustacionAbsoluta.getCosto(), 0);
	}

	@Test
	public void queConPromoAbsolutaDeLothlorienYComarcaElTiempoEs7_5() {
		assertEquals(7.5, degustacionAbsoluta.getTiempo(), 0);
	}

	@Test
	public void queConPromoAxBDeMinasYAbismoLlevoGratisErebor() {
		assertEquals(10, paisajeAxB.getCosto(), 0);
	}

	@Test
	public void queConPromoAxBDeMinasYAbismoYEreborElTiempoEs7_5() {
		assertEquals(7.5, paisajeAxB.getTiempo(), 0.0001);
	}

	/*
	 * Promo absoluta y porcentual heredan hayCupo y reservarCupo de Promocion Base,
	 * por ende los siguientes 3 test sirven para ambas clases.
	 */

	@Test
	public void laPromocionBaseSinCupoDisponibleSabeQueNoHayCupo() {
		for (int i = 0; i < 180; i++) {
			degustacionAbsoluta.reservarCupo();
		}
		assertFalse(degustacionAbsoluta.hayCupo());
	}

	@Test
	public void laPromoBaseCon170CuposDisponiblesSabeQueTieneCupo() {
		for (int i = 0; i < 10; i++) {
			degustacionAbsoluta.reservarCupo();
		}
		assertTrue(degustacionAbsoluta.hayCupo());
	}

	@Test
	public void queReservarCupoEnPromoBaseRestaCuposDisponiblesAAtracciones() {
		degustacionAbsoluta.reservarCupo();
		assertEquals(29, a3.getCupoDisponible());
		assertEquals(149, a4.getCupoDisponible());
	}

	// Prueba particular para promos AxB debido a que sobreescribe m�todos.

	@Test
	public void laPromocionAxBSinCupoDisponibleSabeQueNoHayCupo() {
		for (int i = 0; i < 32; i++) {
			paisajeAxB.reservarCupo();
		}
		assertFalse(paisajeAxB.hayCupo());
	}

	@Test
	public void laPromoAxBConCuposDisponiblesSabeQueTieneCupo() {
		paisajeAxB.reservarCupo();
		assertTrue(paisajeAxB.hayCupo());
	}

	@Test
	public void queReservarCupoEnPromoAxBRestaCuposDisponiblesAAtracciones() {
		paisajeAxB.reservarCupo();
		assertEquals(24, a5.getCupoDisponible());
		assertEquals(14, a6.getCupoDisponible());
		assertEquals(31, aGratis.getCupoDisponible());
	}
	
	@Test
	public void deberiaContarTodasLasPromociones() throws SQLException {
		PromocionDAOImpl promo = new PromocionDAOImpl();
		assertTrue(promo.countAll() > 0);
	}
	
	
	@Test
	public void deberiaBuscarTodasLasdPromociones() throws SQLException {
		PromocionDAOImpl promo = new PromocionDAOImpl();
		assertTrue(promo.findAll().size() > 0);
		System.out.println(promo.findAll());
	}
	
	@Test
	public void deberiaInsertarUnaPromocionPorcentual()throws SQLException{
		PromocionDAOImpl promo = new PromocionDAOImpl();
//		promo.insert(degustacionAbsoluta);
//		promo.insert(aventuraPorcentual);
		
	}

	@Test
	public void deberiaBuscarUnaPromocionPorID()throws SQLException{
		PromocionDAOImpl promo = new PromocionDAOImpl();
//		promo.consultarID_Promo(1);
		assertEquals(aventuraPorcentual,promo.consultarID_Promo(1));
	}
	
}

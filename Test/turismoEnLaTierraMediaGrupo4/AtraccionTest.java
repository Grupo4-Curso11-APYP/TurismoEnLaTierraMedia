
package turismoEnLaTierraMediaGrupo4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AtraccionTest {

	Atraccion a1;
	Atraccion a2;

	@Before
	public void setUp() throws Exception {
		a1 = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE);
	}

	@Test
	public void queAlCrearAtraccionNoSeaNull() {
		assertNotNull(a1);
	}

	@Test
	public void laAtraccionSinCupoDisponibleSabeQueNoHayCupo() {
		for (int i = 0; i < 25; i++) {
			a1.reservarCupo();
		}
		assertFalse(a1.hayCupo());
	}

	@Test
	public void laAtraccionCon20CuposDisponiblesSabeQueTieneCupo() {
		for (int i = 0; i < 5; i++) {
			a1.reservarCupo();
		}
		assertTrue(a1.hayCupo());
	}

	@Test
	public void queReservarCupoCon25CuposDisponiblesRestaCupoYQueda24() {
		a1.reservarCupo();
		assertEquals(24, a1.getCupoDisponible());
	}

	@Test(expected = CostoNegativoExcepcion.class)
	public void deberiaDeLanzarExcepcionPorCostoNegativo() throws Exception {
		a2 = new Atraccion("Minas Tirith", -5, 2.5, 25, TipoAtraccion.PAISAJE);
	}

	@Test(expected = SinTiempoDisponible.class)
	public void deberiaDeLanzarExcepcionPorTiempoDisponibleNegativo() throws Exception {
		a2 = new Atraccion("Minas Tirith", 5, -2.5, 25, TipoAtraccion.PAISAJE);
	}

	@Test(expected = CupoNegativoException.class)
	public void deberiaDeLanzarExcepcionPorCupoNegativo() throws Exception {
		a2 = new Atraccion("Minas Tirith", 5, 2.5, -25, TipoAtraccion.PAISAJE);
	}

}

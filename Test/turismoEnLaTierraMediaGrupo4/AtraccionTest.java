
package turismoEnLaTierraMediaGrupo4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AtraccionTest {

	Atraccion a1;
// creacion de before
	@Before
	public void setUp() {
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

}

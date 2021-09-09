package turismoEnLaTierraMediaGrupo4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	
	Usuario u1;
	Atraccion a1;

	@Before
	public void setUp() throws Exception {
		u1 = new Usuario("Eowyn", 8, 10, TipoAtraccion.AVENTURA);
		a1 = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE);
	}

	@Test
	public void queUsuarioNuevoCreadoNoEsNull() {
		assertNotNull(u1);
	}
	
	@Test
	public void comprarRestaPresupuestoYTiempoDelUsuarioYSeGuardaOfertable() {
		assertEquals(8, u1.getPresupuesto(), 0);
		assertEquals(10, u1.getTiempoDisponible(), 0);
		u1.comprarOfertable(a1);
		assertEquals(3, u1.getPresupuesto(), 0);
		assertEquals(7.5, u1.getTiempoDisponible(), 0);
		assertEquals(true, a1.equals(u1.getOfertables().get(0)));
	}

}

package turismoEnLaTierraMediaGrupo4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	Usuario u1;
	Atraccion a1;
	Atraccion a5;
	Atraccion a6;
	Atraccion aGratis;
	PromocionBase paisajeAxB;

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
	
	@Test
	public void toStringMuestraLosDatosCorrectamente() {
		a5 = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE);
		a6 = new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE);
		aGratis = new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.PAISAJE);
		
		Atraccion[] aXb = new Atraccion[2];
		aXb[0] = a5;
		aXb[1] = a6;
		
		paisajeAxB = new PromocionAxB("Pack paisajes", aXb, TipoAtraccion.PAISAJE, aGratis);
		
		u1.comprarOfertable(a1);
		u1.comprarOfertable(paisajeAxB);
		System.out.println(u1.toString());
		String esperado =  "\nUsuario: Eowyn, presupuesto: -7.0, tiempo "
				+ "disponible: 0.0, tipo favorito: AVENTURA\n" 
				+ "Su itinerario final le tomará un total "
				+ "de: 10.0 horas; con un costo final de: 15 monedas.\n" 
				+ "Sugerencias incluidas:\n" + "Minas Tirith: precio: 5.0,"
				+ " duracion: 2.5, tipo: PAISAJE\n" + "\nPack paisajes: "
				+ "precio: 10.0, duracion: 7.5, tipo: PAISAJE, atracciones incluidas: \n"
				+ "Minas Tirith: precio: 5.0, duracion: 2.5, tipo: PAISAJE\n"
				+ "Abismo de Helm: precio: 5.0, duracion: 2.0, tipo: PAISAJE\n"
				+ "Atracción gratis: Erebor: precio: 12.0, duracion: 3.0, tipo: PAISAJE\n";
		assertEquals(esperado, u1.toString());
		System.out.println(u1.toString());
		
	}

}

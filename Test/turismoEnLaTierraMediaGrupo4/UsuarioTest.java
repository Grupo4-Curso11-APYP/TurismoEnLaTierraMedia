package turismoEnLaTierraMediaGrupo4;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import dao.AtraccionDAOImpl;
import dao.UsuarioDaoImpl;

public class UsuarioTest {
	

		Usuario u1;
		Usuario usuarioNuevo;
		Atraccion a1;
		Atraccion a5;
		Atraccion a6;
		Atraccion aGratis;
		Promocion paisajeAxB;

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
//			assertEquals(true, a1.equals(u1.getOfertables().get(0)));
			assertEquals(true, u1.getOfertables().contains(a1));
			
		}
		
		@Test
		public void toStringMuestraLosDatosCorrectamente() throws Exception {
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
					+ "Su itinerario final le tomara un total "
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
		
		@Test(expected=SinMontoDisponible.class)
		public void deberiaDeLanzarExcepcionPorMontoNegativo() throws Exception {
			String nombreUsuario = "Pepe";
			int montoDisponible = -8;
			int tiempoDisponible = 10;
			
			usuarioNuevo = new Usuario(nombreUsuario,montoDisponible, tiempoDisponible, TipoAtraccion.AVENTURA);
		}
		
		@Test(expected=SinTiempoDisponible.class)
		public void deberiaDeLanzarExcepcionPorTiempoNegativo() throws Exception {
			String nombreUsuario = "Tita";
			int montoDisponible = 8;
			int tiempoDisponible = -10;
			
			usuarioNuevo = new Usuario(nombreUsuario, montoDisponible, tiempoDisponible, TipoAtraccion.AVENTURA);
		}

		
		/* consultar en clase mañana , tira un error  
		que dice que la base de datos esta bloqueada
	*/
		@Test
		public void deberiaInsertarUnNuevoUsuario() throws Exception {
			
//			Set<Ofertable> ofertable = new LinkedHashSet<Ofertable>();
//			Usuario us1 = new Usuario("jere", 20,15, TipoAtraccion.DEGUSTACION,ofertable);
//			UsuarioDaoImpl  usDao= new UsuarioDaoImpl();
//			usDao.insert(us1);
		}
		
		/*
		 *  no va a dar verde hasta que no este terminado  el metodo sugerir ,	
		 *  ya que sino  nunca va a mostrar las atracciones incluidas como se muestra 
		 *  en el toString
		 */
		@Test 
		public void deberiaBuscarUsuarioPorNombre() throws Exception {
//			UsuarioDaoImpl us1 = new UsuarioDaoImpl();
//			Usuario u2 = new Usuario("Gandalf",100,5,TipoAtraccion.PAISAJE );
//			assertEquals(u2,us1.findByNombre(u2.getNombre()));
		}
		
		
		@Test
		public void deberiaBuscarTodosLosUsuarios() throws SQLException {
			UsuarioDaoImpl us2 = new UsuarioDaoImpl();
			assertTrue(us2.findAll().size() == 5);
		}
	
		
		@Test
		public void deberiaContarusuarios() throws SQLException {
			UsuarioDaoImpl us2 = new UsuarioDaoImpl();
			assertTrue(us2.countAll() == 5);
		}
		
}

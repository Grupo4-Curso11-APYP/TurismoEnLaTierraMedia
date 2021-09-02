package turismoEnlaEdadMedia.grupo4.tp1.arg;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import turismoEnLaTierraMediaGrupo4.Atraccion;
import turismoEnLaTierraMediaGrupo4.Sistema;
import turismoEnLaTierraMediaGrupo4.TipoAtraccion;
import turismoEnLaTierraMediaGrupo4.Usuario;

public class SistemaTest {
	Sistema sistema;

	@Before
	public void preparacion() {
		sistema = new Sistema();

		sistema.nuevoUsuario(new Usuario("Moria", 8, 10, TipoAtraccion.AVENTURA));

		sistema.nuevaAtraccion(new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE));
		sistema.nuevaAtraccion(new Atraccion("Parque De La costa", 4, 3.7, 15, TipoAtraccion.AVENTURA));
		sistema.nuevaAtraccion(new Atraccion(" La Casona", 2, 1.5, 10, TipoAtraccion.DEGUSTACION));
	}



	@Test
	public void agregarNuevoUsuarioAlSistema() {

		Usuario u1 = new Usuario("Eowyn", 8, 10, TipoAtraccion.AVENTURA);

		sistema.nuevoUsuario(u1);

		assertEquals(true, u1.equals(sistema.getUsuarios().get(1)));

	}

	@Test
	public void agregarNuevaAtraccionAlSistema() {

		Atraccion a1 = new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA);
		sistema.nuevaAtraccion(a1);
		assertEquals(true, a1.equals(sistema.getAtraccion().get(3)));
	}

	@Test
	public void agregarNuevaPromocionPorcentualAlSistema() {

		List<Atraccion> listAtraccion = new  ArrayList<>(); 

		sistema.nuevaPromocionPorcentual("Pack aventura", listAtraccion, 20, TipoAtraccion.AVENTURA);

		assertEquals("Pack aventura", sistema.getPromocion().get(0).getNombre());
	}
	
	
	@Test
	public void agregarNuevaPromocionAbsolutaAlSistema() {

		List<Atraccion> listAtraccion = new  ArrayList<>(); 

		sistema.nuevaPromocionAbsoluta("Pack aventura", listAtraccion, 40, TipoAtraccion.AVENTURA);

		assertEquals("Pack aventura", sistema.getPromocion().get(0).getNombre());
	}
	
	
	
	
	@Test
	public void agregarNuevaPromocionAxBAlSistema() {

		List<Atraccion> listAtraccion = new  ArrayList<>(); 
        Atraccion atraccionGratis = new Atraccion("Gratis", 10, 3.5, 8, TipoAtraccion.AVENTURA);
		sistema.nuevaPromocionAxB("Pack aventura", listAtraccion, TipoAtraccion.AVENTURA,atraccionGratis);

		assertEquals("Pack aventura", sistema.getPromocion().get(0).getNombre());
	}
	
	
	@Test
	public void  deberiaOrdenarLasAtraccionesPorMayorCosto() {
	     
		Atraccion a1 = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE);
		Atraccion a2 = new Atraccion(" La Casona", 2, 1.5, 10, TipoAtraccion.DEGUSTACION);
		sistema.ordenarPorCostoDeAtraccion();
		assertEquals(true,a1.equals(sistema.getAtraccion().get(0)) );
		assertEquals(true, a2.equals(sistema.getAtraccion().get(2)));
	}
	

	@Test
	public void  deberiaOrdenarLasAtraccionesPorMayorTiempo() {
	     
		Atraccion a1 = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE);
		Atraccion a2 = new Atraccion(" La Casona", 2, 1.5, 10, TipoAtraccion.DEGUSTACION);
		sistema.ordenarPorTiempoDeVisita();
		assertEquals(true,a1.equals(sistema.getAtraccion().get(1)) );
		assertEquals(true, a2.equals(sistema.getAtraccion().get(0)));
	}
	
}

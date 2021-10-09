package turismoEnLaTierraMediaGrupo4;

import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class SistemaTest {
	Sistema sistema;
	Atraccion a1;
	Atraccion a2;
	Atraccion a3;
	Atraccion a4;
	Atraccion a5;
	Atraccion a6;
	Atraccion atraccionGratis;
	PromocionPorcentual p1;
	PromocionAbsoluta p2;
	PromocionAxB p3;

	@Before
	public void preparacion() throws Exception {
		sistema = new Sistema();

		// usuario
		sistema.usuarios.add(new Usuario("Moria", 8, 10, TipoAtraccion.AVENTURA));
		// atracion
		Atraccion[] atraccion1 = new Atraccion[2];
		Atraccion[] atraccion2 = new Atraccion[2];
		Atraccion[] atraccion3 = new Atraccion[2];
		a1 = new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE);
		a2 = new Atraccion("Parque De La costa", 4, 3.7, 15, TipoAtraccion.AVENTURA);
		a3 = new Atraccion(" La Casona", 2, 1.5, 10, TipoAtraccion.DEGUSTACION);
		a4 = new Atraccion("Moria", 10, 2.4, 6, TipoAtraccion.AVENTURA);
		a5 = new Atraccion("La Comarca", 6, 3.5, 150, TipoAtraccion.DEGUSTACION);
		a6 = new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE);
		// carga de array Para porcentual
		atraccion1[0] = a4;
		atraccion1[1] = a2;
		// cargar de array Para absoluta
		atraccion2[0] = a3;
		atraccion2[1] = a5;
		// carga de array para AxB
		atraccion3[0] = a1;
		atraccion3[1] = a6;
		// creacion de atraccion gratis
		atraccionGratis = new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.PAISAJE);

		// promocion
		p1 = new PromocionPorcentual("packAventura", atraccion1, TipoAtraccion.AVENTURA, 20);
		p2 = new PromocionAbsoluta("packDegusticion", atraccion2, TipoAtraccion.DEGUSTACION, 36);
		p3 = new PromocionAxB("packPaisaje", atraccion3, TipoAtraccion.PAISAJE, atraccionGratis);
	}

	@Test
	public void agregarNuevoUsuarioAlSistema() throws Exception {

		Usuario u1 = new Usuario("Eowyn", 8, 10, TipoAtraccion.AVENTURA);

		sistema.usuarios.add(u1);

		assertEquals(true, sistema.getUsuarios().contains(u1));

	}

	@Test
	public void comparadorOrdenaPorTipoLuegoClaseLuegoCostoLuegoTiempo() {
		sistema.ofertables.add(a1);
		sistema.ofertables.add(a2);
		sistema.ofertables.add(p1);
		sistema.ofertables.add(p2);
		sistema.ofertables.add(p3);

		assertEquals(((TreeSet<Ofertable>) 
				sistema.ordenarOfertasSegunPreferencia(TipoAtraccion.AVENTURA)).first(), p1);

		assertEquals(((TreeSet<Ofertable>) 
				sistema.ordenarOfertasSegunPreferencia(TipoAtraccion.AVENTURA)).last(), a1);

		assertEquals(sistema.ofertables.size(), 5);
	}

	@Test
	public void queOrdenarOfertasSegunPreferenciaOrdenaPorComparator() {
		sistema.ofertables.add(a1);
		sistema.ofertables.add(a2);
		sistema.ofertables.add(p3);
		sistema.ofertables.add(p1);

		assertEquals(((TreeSet<Ofertable>) sistema.ordenarOfertasSegunPreferencia(TipoAtraccion.AVENTURA)).first(), p1);

		assertEquals(((TreeSet<Ofertable>) sistema.ordenarOfertasSegunPreferencia(TipoAtraccion.AVENTURA)).last(), a1);

		assertEquals(sistema.ofertables.size(), 4);
	}

	@Test
	public void queOrdenarOfertasSegunPreferenciaEliminaDosDuplicados() {
		sistema.ofertables.add(a1);
		sistema.ofertables.add(a1);
		sistema.ofertables.add(p3);
		sistema.ofertables.add(p3);

		assertEquals(sistema.ofertables.size(), 2);
	}
}

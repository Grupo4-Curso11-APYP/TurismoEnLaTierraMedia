package turismoEnLaTierraMediaGrupo4;

import static org.junit.Assert.*;

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
       //atracion
		Atraccion [] atraccion1= new Atraccion[2];
		Atraccion [] atraccion2 = new Atraccion[2];
		Atraccion [] atraccion3 = new Atraccion[2];
		 a1 =new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE);
		a2 =new Atraccion("Parque De La costa", 4, 3.7, 15, TipoAtraccion.AVENTURA);
		 a3 =new Atraccion(" La Casona", 2, 1.5, 10, TipoAtraccion.DEGUSTACION);
		 a4 = new Atraccion("Moria", 10, 2.4 , 6, TipoAtraccion.AVENTURA);
		 a5 = new Atraccion("La Comarca", 6, 3.5, 150, TipoAtraccion.DEGUSTACION);
		 a6 = new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE);
		 //carga de array Para porcentual
		 atraccion1 [0] = a4;
		 atraccion1[1] = a2;
		 //cargar de array Para absoluta
		 atraccion2 [0]= a3;
		 atraccion2[1] = a5;
		 // carga de array para AxB
		 atraccion3[0] = a1;
		 atraccion3[1] = a6;
		//creacion de atraccion gratis
		atraccionGratis = new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.PAISAJE);
		
		 
		 //promocion
		 p1  = new PromocionPorcentual("packAventura",atraccion1 , TipoAtraccion.AVENTURA, 20);
		 p2 = new  PromocionAbsoluta("packDegusticion", atraccion2,TipoAtraccion.DEGUSTACION , 36);
		p3 = new PromocionAxB("packPaisaje", atraccion3, TipoAtraccion.PAISAJE, atraccionGratis);
	}

	@Test
	public void agregarNuevoUsuarioAlSistema() throws Exception {

		Usuario u1 = new Usuario("Eowyn", 8, 10, TipoAtraccion.AVENTURA);

		sistema.usuarios.add(u1);

		assertEquals(true, u1.equals(sistema.getUsuarios().get(1)));

	}

	@Test
	public void comparadorOrdenaPorTipoLuegoClaseLuegoCostoLuegoTiempo() {
		sistema.ofertableList.add(a1);
		sistema.ofertableList.add(a2);
		sistema.ofertableList.add(p1);
		sistema.ofertableList.add(p2);
		sistema.ofertableList.add(p3);
		
		sistema.ordenarOfertasSegunPreferencia(TipoAtraccion.AVENTURA);
		
		assertEquals(true, p1.equals(sistema.getOfertableList().get(0))); //primero promo 1
		assertEquals(true, a2.equals(sistema.getOfertableList().get(1))); //2do atracción 2
		assertEquals(true, p2.equals(sistema.getOfertableList().get(2))); //3ro promo 2
		assertEquals(true, p3.equals(sistema.getOfertableList().get(3))); //4to promo 3
		assertEquals(true, a1.equals(sistema.getOfertableList().get(4))); //5to atracción 1
		
	
	}

}

package turismoEnLaTierraMediaGrupo4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import turismoEnLaTierraMediaGrupo4.Atraccion;
import turismoEnLaTierraMediaGrupo4.Ofertable;
import turismoEnLaTierraMediaGrupo4.Sistema;
import turismoEnLaTierraMediaGrupo4.TipoAtraccion;
import turismoEnLaTierraMediaGrupo4.Usuario;

public class SistemaTest {
	Sistema sistema;
	Ofertable a1;
	Ofertable a2;
	Ofertable a3;
	Ofertable a4;
	Ofertable a5;
	Ofertable a6;
	Ofertable atraccionGratis;
	Ofertable p1;
	Ofertable p2;
	Ofertable p3;

	@Before
	public void preparacion() {
		sistema = new Sistema();
        
		// usuario
		sistema.nuevoUsuario(new Usuario("Moria", 8, 10, TipoAtraccion.AVENTURA));
       //atracion
		Atraccion [] atraccion1= new Atraccion[2];
		Atraccion [] atraccion2 = new Atraccion[2];
		Atraccion [] atraccion3 = new Atraccion[3];
		 a1 =new Atraccion("Minas Tirith", 5, 2.5, 25, TipoAtraccion.PAISAJE);
		a2 =new Atraccion("Parque De La costa", 4, 3.7, 15, TipoAtraccion.AVENTURA);
		 a3 =new Atraccion(" La Casona", 2, 1.5, 10, TipoAtraccion.DEGUSTACION);
		 a4 = new Atraccion("Moria", 10, 2.4 , 6, TipoAtraccion.AVENTURA);
		 a5 = new Atraccion("La Comarca", 6, 3.5, 150, TipoAtraccion.DEGUSTACION);
		 a6 = new Atraccion("Avismo de Ghelm", 5, 2, 15, TipoAtraccion.PAISAJE);
		 //carga de array Para porcentual
		 atraccion1 [0] =(Atraccion) a4;
		 atraccion1[1] = (Atraccion) a2;
		 //cargar de array Para absoluta
		 atraccion2 [0]= (Atraccion) a3;
		 atraccion2[1] = (Atraccion) a5;
		 // carga de array para AxB
		 atraccion3[0] = (Atraccion) a1;
		 atraccion3[1] = (Atraccion) a6;
		//creacion de atraccion gratis
		 atraccionGratis = new Atraccion("Eregor", 12, 3, 32, TipoAtraccion.PAISAJE);
		
		 
		 //promocion
		 p1  = new PromocionPorcentual("packAventura",atraccion1 , TipoAtraccion.AVENTURA, 20);
		 p2 = new  PromocionAbsoluta("packDegusticion", atraccion2,TipoAtraccion.DEGUSTACION , 36);
		p3 = new PromocionAxB("packPaisaje", atraccion3, TipoAtraccion.PAISAJE, (Atraccion) atraccionGratis);
	}

	@Test
	public void agregarNuevoUsuarioAlSistema() {

		Usuario u1 = new Usuario("Eowyn", 8, 10, TipoAtraccion.AVENTURA);

		sistema.nuevoUsuario(u1);

		assertEquals(true, u1.equals(sistema.getUsuarios().get(1)));

	}

	@Test
	public  void  deberiaOrdenarPorPreferencia() {
		sistema.ofertableList.add(a1);
		sistema.ofertableList.add(a2);
		sistema.ofertableList.add(p1);
		sistema.ofertableList.add(p2);
		sistema.ofertableList.add(p3);
		
		sistema.ordenarOfertasSegunPreferencia(TipoAtraccion.AVENTURA);
		
		assertEquals(true, p1.equals(sistema.getOfertableList().get(1)));
	
	}

//	@Test
//	public void deberiaPoderLeerElArchivoPromocionPorcentual() {
//		
//		sistema.obtenerPromocion("C:\\Users\\Jere\\eclipse-workspace\\turismoEnlaEdadMedia.grupo4.tp1.arg\\src\\turismoEnLaTierraMediaGrupo4\\promocionesPorcentual");
//		
//		assertEquals(true, sistema.obtenerPromocion("C:\\Users\\Jere\\eclipse-workspace\\turismoEnlaEdadMedia.grupo4.tp1.arg\\src\\turismoEnLaTierraMediaGrupo4\\promocionesPorcentual"));
//	}

}

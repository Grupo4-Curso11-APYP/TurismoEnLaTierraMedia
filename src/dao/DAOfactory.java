package dao;
import turismoEnLaTierraMediaGrupo4.*;
public class DAOfactory {
	public static PromocionDAO getPromocionDAO() {
        return new  PromocionDAOImplt();
	}
	
	public static AtraccionDAO getAtraccionDAO() {
       return new  AtraccionDAOImplt();
	}
	
	
	public static UsuarioDAO getUsuarioDAO() {
	       return new  UsuarioDaoImpl();
		}

	public static ItinerarioDAO getItinerarioDAO() {
	       return new  ItinerarioDAOImpl();
		}

}

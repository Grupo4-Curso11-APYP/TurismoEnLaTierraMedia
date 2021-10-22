package dao;
public class DAOfactory {
	public static PromocionDAO getPromocionDAO() {
        return new  PromocionDAOImpl();
	}
	
	public static AtraccionDAO getAtraccionDAO() {
       return new  AtraccionDAOImpl();
	}
	
	
	public static UsuarioDAO getUsuarioDAO() {
	       return new  UsuarioDaoImpl();
		}

	public static ItinerarioDAO getItinerarioDAO() {
	       return new  ItinerarioDAOImpl();
		}

}

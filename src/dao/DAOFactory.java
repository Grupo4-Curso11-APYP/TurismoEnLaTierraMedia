package dao;

public class DAOFactory {
	public static AtraccionesDAO getAtraccionesDAO() {
		return new AtraccionesDAOImpl();		//falta implementarlo, en proceso de realizarlo
	}
}

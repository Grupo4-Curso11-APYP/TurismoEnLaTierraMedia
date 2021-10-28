package dao;

import java.sql.SQLException;
import turismoEnLaTierraMediaGrupo4.Usuario;


public interface ItinerarioDAO extends GenericDAO<Usuario>{

	public int delete(int idItinerario) throws SQLException;
	
	public int update(int idAtraccion, int idPromocion, int idUsuario, int idItinerario);
}

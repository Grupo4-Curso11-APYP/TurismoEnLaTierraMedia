package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import turismoEnLaTierraMediaGrupo4.Itinerario;
import turismoEnLaTierraMediaGrupo4.Usuario;


public interface ItinerarioDAO extends GenericDAO<Usuario>{

	public List<Itinerario> findByID_Usuario(Long id) throws SQLException;
}

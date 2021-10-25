package dao;

import turismoEnLaTierraMediaGrupo4.Usuario;


public interface ItinerarioDAO extends GenericDAO<Usuario>{
	public abstract Usuario findByID_Usuario() ;

	Usuario findByID_Usuario(Long id);
}

package dao;

import turismoEnLaTierraMediaGrupo4.Usuario;


public interface ItinerarioDAO extends GenericDAO<Usuario>{

	public Usuario findByID_Usuario(Long id);
}

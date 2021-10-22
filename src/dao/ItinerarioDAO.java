package dao;

import turismoEnLaTierraMediaGrupo4.EscribirItinerarios;


public interface ItinerarioDAO extends GenericDAO<EscribirItinerarios>{
	public abstract EscribirItinerarios findByID_Usuario() ;
}

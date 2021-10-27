package dao;

import java.sql.SQLException;
import java.util.List;

import turismoEnLaTierraMediaGrupo4.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {
	public abstract Usuario findByNombre(String nombre) throws SQLException;
	
	public List<Usuario> findAll() throws SQLException;
}

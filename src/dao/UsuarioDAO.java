package dao;

import java.sql.SQLException;

import turismoEnLaTierraMediaGrupo4.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {
	public abstract Usuario findByNombre(String nombre) throws SQLException;
}

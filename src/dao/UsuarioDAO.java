package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import turismoEnLaTierraMediaGrupo4.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {
	public abstract Usuario findByNombre(String nombre) throws SQLException;
	
	public Set<Usuario> findAll() throws SQLException;
	
	public int insert(Usuario usuario) throws SQLException;
	
	public int delete(Usuario usuario) throws SQLException;
	
	public int update(Usuario usuario) throws SQLException;
}

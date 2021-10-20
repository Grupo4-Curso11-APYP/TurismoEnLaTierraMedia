package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import turismoEnLaTierraMediaGrupo4.Usuario;

public class UsuarioDaoImpl implements UsuarioDAO {

	@Override
	public List<Usuario> findAll() throws SQLException {
		String sql = "SELECT * FROM USUARIO";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Usuario> usuarios = new LinkedList<Usuario>();
		while (resultados.next()) {
			usuarios.add(toUser(resultados));
		}
		return usuarios;
	}

	@Override
	public int countAll() throws SQLException {
		String sql = "SELECT COUNT(1) AS TOTAL FROM USUARIO";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("TOTAL");

		return total;
	}

	@Override
	public int insert(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO USUARIO (NOMBRE, PRESUPUESTO,TIEMPODISPONIBLE,"
				+ "TIPOFAVORITO, OFERTABLE) VALUES (?, ?)";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, usuario.getNombre());
		statement.setDouble(2, usuario.getPresupuesto());
		statement.setDouble(3, usuario.getTiempoDisponible());
		statement.setObject(4, usuario.getTipoFavorito());
		statement.setObject(5, usuario.getOfertables());
		
		int rows = statement.executeUpdate();

		return rows;
	}

	@Override
	public int update(Usuario usuario) throws SQLException {
		String sql = "UPDATE USUARIO SET PRESUPUESTO = ? WHERE NOMBRE = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setDouble(1, usuario.getPresupuesto());
		statement.setString(2, usuario.getNombre());
		int rows = statement.executeUpdate();
		//Faltan resto de atributos

		return rows;

	}

	@Override
	public int delete(Usuario usuario) throws SQLException {
		String sql = "DELETE FROM USUARIO WHERE NOMBRE = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, usuario.getNombre());
		int rows = statement.executeUpdate();

		return rows;

	}

	@Override
	public Usuario findByNombre(String nombre) throws SQLException {
		String sql = "SELECT * FROM USUARIO WHERE NOMBRE = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, nombre);
		ResultSet resultados = statement.executeQuery();

		Usuario usuario = null;

		if (resultados.next()) {
			usuario = toUser(resultados);
		}
		return usuario;

	}

	private Usuario toUser(ResultSet resultados) throws SQLException {
		return new Usuario(resultados.getString(1), resultados.getDouble(2),
				resultados.getDouble(3), resultados.getString(4), 
				resultados.getString(5));
	}


}

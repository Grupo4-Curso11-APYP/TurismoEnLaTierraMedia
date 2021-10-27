package dao;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import turismoEnLaTierraMediaGrupo4.*;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	private AtraccionDAOImpl atraccionDAO;
	private PromocionDAOImpl promocionDAO;
	private UsuarioDaoImpl usuarioDAO;

	@Override
	public int insert(Usuario usuario) {
		try {
			String sql = "INSERT INTO Itinerario (ID_Usuario, ID_Promocion, ID_Atraccion) VALUES (?,?,?)";
			Connection conn;
			conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			for (int i = 0; i < usuario.getOfertables().size(); i++) {
				if (usuario.getOfertables() instanceof Promocion) {
					statement.setString(1, usuario.getNombre());
					statement.setObject(2, usuario.getOfertables());
				} else {
					statement.setString(1, usuario.getNombre());
					statement.setObject(3, usuario.getOfertables());
				}
			}

			int rows = statement.executeUpdate();
			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	@Override
	public int update(Usuario itinerario) {
		try {
			String sql = "UPDATE ITINERARIO SET ID = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 0);
			statement.setInt(2, 0);
			statement.setInt(3, 0);
			statement.setInt(4, 0);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Usuario itinerario) {
		try {
			String sql = "DELETE FROM ITINERARIO WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 0);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() throws SQLException {
		String sql = "SELECT COUNT(2) AS TOTAL FROM Itinerario";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("TOTAL");

		return total;
	}

	@Override
	public Usuario findByID_Usuario(Long ID_Usuario) {
		try {
			String sql = "SELECT Usuario.ID_Usuario, Usuario.Nombre, Usuario.Presupuesto, Usuario.TiempoDisponible,"
					+ "Usuario.TipoFavorito"
					+ " FROM Usuario"
					+ " WHERE Usuario.ID_Usuario = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, ID_Usuario);
			ResultSet resultados = statement.executeQuery();

			Usuario itinerario = null;

			if (resultados.next()) {
				itinerario = toIty(resultados);
			}

			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toIty(ResultSet resultados) throws IOException {
		Long ID;
		return null;
	}

}

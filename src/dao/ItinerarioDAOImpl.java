package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import jdbc.ConnectionProvider;
import turismoEnLaTierraMediaGrupo4.*;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	private AtraccionDAOImpl atraccionDao;
	private PromocionDAOImpl promocionDao;

	public List<Ofertable> findByNombre(String nombre) {
		try {
			String sql = "SELECT Itinerario.ID_Promocion, Itinerario.ID_Atraccion\r\n"
					+ "FROM Itinerario\r\n"
					+ "INNER JOIN Usuario \r\n"
					+ "ON usuario.ID_Usuario = Itinerario.ID_Usuario\r\n"
					+ "WHERE Usuario.Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			List<Ofertable> itinerario = new LinkedList<Ofertable>();
			atraccionDao = new AtraccionDAOImpl();
			promocionDao = new PromocionDAOImpl();
			while (resultados.next()) {
				int idPromocion = resultados.getInt(1);
				Long idAtraccion = resultados.getLong(2);
				if (idPromocion > 0) {
					itinerario.add(promocionDao.consultarID_Promo(idPromocion));
				} else {
					itinerario.add(atraccionDao.buscarPorId(idAtraccion));
				}
			}
			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int insertar(String nombreUsuario, String nombreOfertable) {
		try {
			String sql = "INSERT INTO Itinerario (ID_Atraccion, ID_Usuario, ID_Promocion) VALUES ((SELECT ID_Atraccion FROM Atraccion WHERE Nombre = ?)\r\n"
					+ ",(SELECT ID_Usuario FROM Usuario WHERE Nombre = ?),(SELECT ID_Promocion FROM Promocion WHERE Nombre = ?))";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombreOfertable);
			statement.setString(2, nombreUsuario);
			statement.setString(3, nombreOfertable);
			int rows = statement.executeUpdate();
			
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}


	@Override
	public int update(int idAtraccion, int idPromocion, int idUsuario, int idItinerario) {
		try {
			String sql = "UPDATE Itinerario SET ID_Atraccion = ?, ID_Promocion = ?, ID_Usuario = ? \r\n"
					+ "WHERE ID_Itinerario = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idAtraccion);
			statement.setInt(2, idPromocion);
			statement.setInt(3, idUsuario);
			statement.setInt(4, idItinerario);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}


	@Override
	public int delete(int idItinerario) {
		try {
			String sql = "DELETE FROM Itinerario WHERE ID_Itinerario = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idItinerario);
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



}

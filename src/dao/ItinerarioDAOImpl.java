package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
					statement.setLong(1, findByID_Usuario(usuario.getNombre())); //metodo en linea 46
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

	/*busca el usuario por nombre y devuelve el id
	private Long findByID_Usuario(String nombre) {
		try {
			String sql = "SELECT Usuario.ID_Usuario"
					+ " FROM Usuario"
					+ " WHERE Usuario.Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Long ID_Usuario = null;

			return ID_Usuario;
					
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	} */

	//Este update no nos sirve a menos que tengamos una clase Itinerario
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

	//Este delete no nos sirve a menos que tengamos una clase Itinerario
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
	public List<Itinerario> findByID_Usuario(Long ID_Usuario) throws SQLException {
		try {
			String sql = "SELECT \r\n"
					+ "	Usuario.Nombre AS \"Usuario\", Usuario.Presupuesto, Usuario.TiempoDisponible, Usuario.TipoFavorito, \r\n"
					+ "	Promocion.Nombre AS \"Nombre promo\", a1.Nombre AS \"Atraccion 1 \", a2.Nombre AS \"Atraccion 2 \", Promocion.AtraccionGratis, Promocion.Tipo,\r\n"
					+ "	Atraccion.Nombre AS \"Nombre atraccion\", Atraccion.Costo, Atraccion.Tiempo, Atraccion.TipoDeAtraccion\r\n"
					+ "FROM Itinerario\r\n"
					+ "LEFT JOIN Usuario\r\n"
					+ "ON Itinerario.ID_Usuario = Usuario.ID_Usuario\r\n"
					+ "LEFT JOIN Atraccion\r\n"
					+ "ON Itinerario.ID_Atraccion = Atraccion.ID_Atraccion\r\n"
					+ "LEFT JOIN Promocion\r\n"
					+ "ON Itinerario.ID_Promocion = Promocion.ID_Promocion\r\n"
					+ "LEFT JOIN Atraccion AS a1\r\n"
					+ "ON Promocion.ID_Atraccion1 = a1.ID_Atraccion\r\n"
					+ "LEFT JOIN Atraccion AS a2\r\n"
					+ "ON Promocion.ID_Atraccion2 = a2.ID_Atraccion\r\n"
					+ "WHERE Itinerario.ID_Usuario = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, ID_Usuario);
			ResultSet resultados = statement.executeQuery();

			List<Itinerario> itinerario = new LinkedList<Itinerario>();

			while (resultados.next()) {
				itinerario.add(toItinerario(resultados));
			}

			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Itinerario toItinerario(ResultSet resultados) throws SQLException {
		String usuario = resultados.getString(1);
		Double presupuesto = resultados.getDouble(2); //min
		Double tiempoDisponible = resultados.getDouble(3); //min
		TipoAtraccion tipoFav = TipoAtraccion.valueOf(resultados.getString(4));
		String nombrePromo = resultados.getString(5);
		int atraccion1 = resultados.getInt(6);
		int atraccion2 = resultados.getInt(7);
		int atraccionGratis = resultados.getInt(8);
		TipoAtraccion tipo = TipoAtraccion.valueOf(resultados.getString(9));
		String nombreAtraccion = resultados.getString(10);
		Double costo = resultados.getDouble(11);
		Double tiempo = resultados.getDouble(12);
		TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(resultados.getString(13));
		return new Itinerario(usuario, presupuesto, tiempoDisponible, tipoFav,
				nombrePromo, atraccion1,atraccion2,atraccionGratis, tipo,
				nombreAtraccion, costo, tiempo, tipoAtraccion);
	}

}

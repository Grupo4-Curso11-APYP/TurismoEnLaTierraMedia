package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import turismoEnLaTierraMediaGrupo4.EscribirItinerarios;



public class ItinerarioDAOImpl implements ItinerarioDAO {

	private AtraccionDAOImpl atraccionDAO;
	private PromocionDAOImpl promocionDAO;
	private UsuarioDaoImpl usuarioDAO;

	@Override
	public int insert(Usuario usuario, Ofertable o)  {
		try {
			String sql = "INSERT INTO Itinerario (ID_Promocion, ID_Usuario, ID_Atraccion) VALUES (?,?,?,?)";
			Connection conn;
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			if (o instanceof Promocion) {
				
			}
			statement.setInt(1, o.);
			statement.setInt(2, 0);
			statement.setInt(3, 0);

			return 0;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		
	}

	@Override
	public int update(EscribirItinerarios itinerario) throws SQLException {
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
	public int delete(EscribirItinerarios itinerario) {
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
	public List<EscribirItinerarios> findAll() {
		return null;
	}

	@Override
	public int countAll() {
		return 0;
	}

	@Override
	public EscribirItinerarios findByID_Usuario() {
		try {
			String sql = "SELECT * FROM ITINERARIO WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 0);
			ResultSet resultados = statement.executeQuery();

			EscribirItinerarios itinerario = null;

			if (resultados.next()) {
				itinerario = toIty(resultados);
			}

			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private EscribirItinerarios toIty(ResultSet resultados) throws IOException {
		return (EscribirItinerarios) (resultados);
	}

}

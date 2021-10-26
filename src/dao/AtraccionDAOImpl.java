package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import turismoEnLaTierraMediaGrupo4.Atraccion;
import turismoEnLaTierraMediaGrupo4.TipoAtraccion;

public class AtraccionDAOImpl implements AtraccionDAO{

	@Override
	public List<Atraccion> findAll() throws SQLException {
		try {
			String sql = "SELECT Atraccion.ID_Atraccion, Atraccion.Nombre, Atraccion.Cupo_Disponible,"
					+ " Atraccion.Costo, Atraccion.Tiempo,"
					+ " TipoAtraccion.id_tipoAtraccion"
					+ " FROM Atraccion INNER JOIN TipoAtraccion "  
					+ " ON Atraccion.TipoDeAtraccion = TipoAtraccion.id_tipoAtraccion";

			Connection conn = DriverManager.getConnection("jdbc:Sqlite:TurismoTierraMediaBD.db");
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}
		
			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public Atraccion toAtraccion(ResultSet resultados) throws Exception {
		
		String nombre = resultados.getString(2);
		int cupoDisponible = resultados.getInt(3);
		double costo = resultados.getDouble(4);
		double tiempo = resultados.getInt(5);
		TipoAtraccion tipo =  TipoAtraccion.valueOf(resultados.getString(6).toUpperCase());
		
		return new Atraccion(nombre, costo, tiempo, cupoDisponible, tipo);
	
		
	}
	
	@Override
	public int countAll() throws SQLException {
		String sql = "SELECT COUNT(1) AS TOTAL FROM Atraccion";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("TOTAL");

		return total;
	}
	
	public int actualizarCupo(Atraccion atraccion) {
		try {
			String sql = "UPDATE Atraccion SET Cupo_Disponible = ? WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getCupoDisponible());
			statement.setString(2, atraccion.getNombre());
			
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Atraccion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Atraccion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Atraccion t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Atraccion buscarPorId(Long IdAtraccion) {
		try {
			String sql = "SELECT Atraccion.ID_Atraccion, Atraccion.Nombre, Atraccion.Cupo_Disponible,"
					+ " Atraccion.Costo, Atraccion.Tiempo,"
					+ "  Atraccion.TipoDeAtraccion"
					+ " FROM Atraccion"
					+ " WHERE Atraccion.Id_Atraccion = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, IdAtraccion);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	
}

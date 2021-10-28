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
		double costo = resultados.getDouble(3);
		double tiempo = resultados.getInt(4);
		int cupoDisponible = resultados.getInt(5);
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
	public int insertarAtrac(String nombre, int costo, int tiempo, int cupoDisponible, String tipoAtraccion) throws SQLException {
	//	public int insert(Atraccion atraccion) throws SQLException {
		String sql = "INSERT INTO ATRACCION (NOMBRE, COSTO, TIEMPO, CUPO_DISPONIBLE, TIPODEATRACCION) VALUES (?,?,?,?,?)";
		//String nombre, double costo, double tiempo, int cupoDisponible, TipoAtraccion tipo
		
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setString(1, atraccion.getNombre());
//		statement.setDouble(2, atraccion.getCosto());
//		statement.setDouble(3, atraccion.getTiempo());
//		statement.setInt(4, atraccion.getCupoDisponible());
//		statement.setString(5, atraccion.getTipo());//esto no me parece correcto, tengo que revizarlo, estuve dando vueltas, y me parece que no es as�
		statement.setString(1,nombre);
		statement.setDouble(2,costo);
		statement.setDouble(3,tiempo);
		statement.setDouble(4,cupoDisponible);
		statement.setString(5,tipoAtraccion);
		
		int rows = statement.executeUpdate();
		
		return rows;
	}

	@Override
	public int update(Atraccion atraccion) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Atraccion atraccion) throws SQLException {
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

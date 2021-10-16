package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import turismoEnLaTierraMediaGrupo4.Atraccion;
import turismoEnLaTierraMediaGrupo4.TipoAtraccion;

public class AtraccionesDAO {
	public static void atraccionesDAO(String[] args) {
		return;
	}
	
	public int insert(Atraccion atraccion) throws SQLException {
		String sql = "INSERT INTO ATRACCION (NOMBRE, COSTO, TIEMPO, CUPODISPONIBLE, TIPOATRACCION) VALUES (?.?)";
		//String nombre, double costo, double tiempo, int cupoDisponible, TipoAtraccion tipo
		
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, atraccion.getNombre());
		statement.setDouble(2, atraccion.getCosto());
		statement.setDouble(3, atraccion.getTiempo());
		statement.setInt(4, atraccion.getCupoDisponible());
		statement.setLong(5, atraccion.getTipo());//esto no me parece correcto, tengo que revizarlo, estuve dando vueltas, y me parece que no es así
		
		int rows = statement.executeUpdate();
		
		return rows;
	}
	
	public int getCosto() {
		
	}

}

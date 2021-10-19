package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;

import turismoEnLaTierraMediaGrupo4.*;

public class PromocionDAOImplt implements PromocionDAO {

	public int insert(Ofertable promo) throws SQLException {
		String sql = "INSERT INTO Promocion  (ID_Atraccion,Nombre,Tipo,Monto,Tiempo,AtraccionGratis,Descuento) VALUES (?,?,?,?,?,?,?,?)";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(2, ((Promocion) promo).getId_Atraccion());
		statement.setString(3, ((Promocion) promo).getNombre());
		statement.setObject(4, ((Promocion) promo).getTipo());
		statement.setDouble(5, ((PromocionAbsoluta) promo).getCosto());
		statement.setDouble(6, ((Promocion) promo).getTiempo());
		statement.setObject(7, ((PromocionAxB) promo).getAtraccionGratis());
		statement.setDouble(8, ((PromocionPorcentual) promo).getDescuento());
		int rows = statement.executeUpdate();

		return rows;
	}

	public int update(Ofertable promo) throws SQLException {
		String sql = "UPDATE Promocion SET Nombre = ? WHERE Tipo  = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(3, ((Promocion) promo).getNombre());
		statement.setObject(4, ((Promocion) promo).getTipo());
		int rows = statement.executeUpdate();

		return rows;
	}

	public int delete(Ofertable promo) throws SQLException {
		String sql = "DELETE FROM Promocion WHERE Nombre = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(3, ((Promocion) promo).getNombre());
		int rows = statement.executeUpdate();

		return rows;
	}

	public Promocion findByUsername(String username) throws SQLException {
		String sql = "SELECT * FROM Promocion WHERE Nombre = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet resultados = statement.executeQuery();

	Ofertable promo = null;

		if (resultados.next()) {
			promo =  toPromo(resultados);
		}

		return (Promocion) promo;
	}

	public int countAll() throws SQLException {
		String sql = "SELECT COUNT(1) AS TOTAL FROM Promocion";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("TOTAL");

		return total;
	}

	public List<Ofertable> findAll() throws SQLException {
		String sql = "SELECT * FROM USERS";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Ofertable> promocion = new LinkedList<Ofertable>();
		while (resultados.next()) {
			promocion.add(toPromo(resultados));
		}

		return promocion;
	}

	private Promocion toPromo(ResultSet resultados) throws SQLException {
	
		
		return new  PromocionAbsoluta(resultados.getString(3), resultados.getArray(2), resultados.getObject(4),resultados.getDouble(5));
	}

}

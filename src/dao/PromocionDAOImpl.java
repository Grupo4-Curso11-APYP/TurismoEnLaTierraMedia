package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;

import turismoEnLaTierraMediaGrupo4.*;

public class PromocionDAOImpl implements PromocionDAO {

	private AtraccionDAOImpl atraccionDao;
	
	
	public PromocionDAOImpl() {
		this.atraccionDao = new AtraccionDAOImpl();
	}

	@Override
	public int insert(Promocion t) throws SQLException {
		String sql = "INSERT INTO Promocion  (ID_Atraccion,Nombre,Tipo,Monto,Tiempo,AtraccionGratis,Descuento) VALUES (?,?,?,?,?,?,?,?)";
		Connection conn = ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setLong(2, t.getId_Atraccion());
		statement.setString(3, t.getNombre());
		statement.setObject(4, t.getTipo());

		statement.setDouble(5, t.getCosto());
		statement.setDouble(6, t.getTiempo());
		statement.setObject(7, ((PromocionAxB) t).getAtraccionGratis());
		statement.setDouble(8, ((PromocionPorcentual) t).getDescuento());
		int rows = statement.executeUpdate();

		return rows;

	}
	
	@Override
	public int update(Promocion t) throws SQLException {
		String sql = "UPDATE Promocion SET Nombre = ? WHERE ID_Atraccion  = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(3, t.getNombre());
		statement.setInt(2, t.getId_Atraccion());
		int rows = statement.executeUpdate();

		return rows;

	}

	@Override
	public int delete(Promocion t) throws SQLException {
		String sql = "DELETE FROM Promocion WHERE Nombre LIKE ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(3, t.getNombre());
		int rows = statement.executeUpdate();

		return rows;

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

	private Promocion toPromo(ResultSet resultados) throws Exception {

		Long idAtraccion = resultados.getLong(2);
		String nombre = resultados.getString(3);
		TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(resultados.getString(4));
		double monto = resultados.getInt(5);
		double tiempo = resultados.getDouble(6);
		Atraccion gratis = new Atraccion(resultados.getString(7));
		int descuento = resultados.getInt(8);
		String PromocionTipo = resultados.getString(10);
		Atraccion[] packAtracciones = atraccionesDeLaPromocion(resultados);
		Promocion promo = null;
		if (PromocionTipo.equals("AxB")) {
			promo = new PromocionAxB(nombre, packAtracciones, tipoAtraccion, gratis);
		} else if (PromocionTipo.equals("PORCENTUAL")) {
			promo = new PromocionPorcentual(nombre, packAtracciones, tipoAtraccion, descuento);
		} else if (PromocionTipo.equals("ABSOLUTA")) {
			promo = new PromocionAbsoluta(nombre, packAtracciones, tipoAtraccion, monto);
		}

		return promo;

	}

	private Atraccion[] atraccionesDeLaPromocion(ResultSet resultados) throws Exception {
		try {
			// no me convence lo de id_Atraccion , consultar en la clase 
			//quizas una tabla intermedia de atracciones sea buena tambien  
			String sql = "select ID_Atraccion1.Nombre, ID_Atraccion2.Nombre\r\n"
					+ "from Promocion\r\n"
					+ "WHERE Promocion.Nombre = " + resultados.getString(3);
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(2, resultados.getLong(2));
			ResultSet result = statement.executeQuery();

			AtraccionDAO atraccionDAO = DAOfactory.getAtraccionDAO();

			Atraccion[] packs = new Atraccion[2];
			
		
			while (result.next()) {
				
				packs[0] = atraccionDao.buscarPorId(resultados.getLong(0));
				packs[1] = atraccionDao.buscarPorId(resultados.getLong(1));
				
			}

			return packs;
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public List<Promocion> findAll() throws SQLException {
	
		String sql = "SELECT * FROM Promocion";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Promocion> promo = new LinkedList<Promocion>();
		while (resultados.next()) {
			try {
				promo.add(toPromo(resultados));
			} catch (Exception e) {
				
				throw new MissingDataException(e);
			}
		}
		return promo;
	}

}

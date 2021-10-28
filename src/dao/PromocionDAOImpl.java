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

	private int ID_Promocion, ID_Atraccion1, ID_Atraccion2;

	public PromocionDAOImpl() {
		this.atraccionDao = new AtraccionDAOImpl();
	}

	@Override
	public int insert(Promocion promocion) throws SQLException {
		String sql = "INSERT INTO Promocion  ( ID_Atraccion1, ID_Atraccion2, nombre ,Tipo,monto,Tiempo,AtraccionGratis,Descuento) VALUES "
				+ "(?,?,?,?,?,?,?,?)";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setInt(1, consultarID_Promo(1));// hay que ver como los mejoramos a estos metodos
		statement.setObject(2, atraccionDao.buscarPorId((long) 2));// 
		statement.setObject(3,  atraccionDao.buscarPorId((long) 3));// 

		statement.setString(4, promocion.getNombre());
		statement.setObject(5, promocion.getTipo());
		statement.setDouble(6, promocion.getCosto());
		statement.setDouble(7, promocion.getTiempo());
		statement.setObject(8, ((PromocionAxB) promocion).getAtraccionGratis());
		statement.setDouble(9, ((PromocionPorcentual) promocion).getDescuento());

		int rows = statement.executeUpdate();

		return rows;

	}

	public Promocion consultarID_Promo(int promo) throws SQLException {
		try {
		String sql = "SELECT * FROM Promocion WHERE ID_Promocion=?";// el problema con este metodo y el siguiente es que
																	// dependen de valores estaticos como parametros,
																	// tenemos que averiguar como obtenerlos sin tener
																	// que pasarselos, esto nos va a ayudar a eviatr a
																	// que se rompa el insert

		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, promo);
        ResultSet rows = statement.executeQuery();
       Promocion pr = null;
	  
      while(rows.next()) {
    	  pr = toPromo(rows);
      }
		return pr;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	

	

	@Override
	public int update(Promocion promocion) throws SQLException {
		String sql = "UPDATE Promocion SET Nombre = ? WHERE Tipo  = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(4, promocion.getNombre());
		statement.setObject(5, promocion.getTipo());
		int rows = statement.executeUpdate();

		return rows;

	}

	@Override
	public int delete(Promocion promocion) throws SQLException {
		String sql = "DELETE FROM Promocion WHERE Nombre LIKE ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(3, promocion.getNombre());
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

		TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(resultados.getString(5));
		Atraccion[] packAtracciones = atraccionesDeLaPromocion(resultados.getLong(2), resultados.getLong(3));
		String nombre = resultados.getString(4);
		String promocionTipo = resultados.getString(10);
		Promocion promo = null;
		promo = creacionPromo(resultados, tipoAtraccion, packAtracciones, nombre, promocionTipo, promo);
		return promo;

	}

	private Promocion creacionPromo(ResultSet resultados, TipoAtraccion tipoAtraccion, Atraccion[] packAtracciones,
			String nombre, String promocionTipo, Promocion promo) throws SQLException {
		if (promocionTipo.equals("AxB")) {
			Atraccion gratis = atraccionDao.buscarPorId(resultados.getLong(8));
			promo = new PromocionAxB(nombre, packAtracciones, tipoAtraccion, gratis);
		} else if (promocionTipo.equals("PORCENTUAL")) {
			int descuento = resultados.getInt(9);
			promo = new PromocionPorcentual(nombre, packAtracciones, tipoAtraccion, descuento);
		} else if (promocionTipo.equals("ABSOLUTA")) {
			double monto = resultados.getInt(6);
			promo = new PromocionAbsoluta(nombre, packAtracciones, tipoAtraccion, monto);
		}
		return promo;
	}

	private Atraccion[] atraccionesDeLaPromocion(Long atraccion1, Long atraccion2) throws Exception {
		Atraccion[] packs = new Atraccion[2];
		try {

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sqlAtraccion());
			statement.setLong(1, atraccion1);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				packs[0] = atraccionDao.toAtraccion(result);
			}
			statement.setLong(1, atraccion2);
			ResultSet result2 = statement.executeQuery();
			while (result2.next()) {
				packs[1] = atraccionDao.toAtraccion(result2);
			}
			return packs;
		} catch (Exception e) {
			throw new Exception();
		}
	}

	private String sqlAtraccion() {
		String sql = "select *" + "from Atraccion WHERE ID_Atraccion = ?";
		return sql;
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

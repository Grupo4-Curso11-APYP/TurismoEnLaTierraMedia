package dao;

import java.sql.Connection;
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

	/*
	 * Inserta una promocion nueva en la base de datos
	 */
	@Override
	public int insert(Promocion promocion) throws SQLException {
		String sql = "INSERT INTO Promocion  ( ID_Atraccion1, ID_Atraccion2, nombre ,Tipo,monto,Tiempo,AtraccionGratis,Descuento) VALUES "
				+ "(?,?,?,?,?,?,?,?)";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setObject(1, atraccionDao.buscarPorId((long) 1));//
		statement.setObject(2, atraccionDao.buscarPorId((long) 2));//

		statement.setString(3, promocion.getNombre());
		statement.setObject(4, promocion.getTipo());
		statement.setDouble(5, promocion.getCosto());
		statement.setDouble(6, promocion.getTiempo());
		statement.setObject(7, ((PromocionAxB) promocion).getAtraccionGratis());
		statement.setDouble(8, ((PromocionPorcentual) promocion).getDescuento());

		int rows = statement.executeUpdate();

		return rows;

	}

	/*
	 * Busca en la base de datos y devuelve una promocion a partir de su ID
	 */
	public Promocion consultarID_Promo(int promo) throws SQLException {
		try {
			String sql = "SELECT * FROM Promocion WHERE ID_Promocion=?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promo);
			ResultSet rows = statement.executeQuery();
			Promocion pr = null;

			while (rows.next()) {
				pr = toPromo(rows);
			}
			return pr;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	/*
	 * Actualiza el nombre de una promocion
	 */
	@Override
	public int update(Promocion promocion) throws SQLException {
		String sql = "UPDATE Promocion SET Nombre = ?  WHERE Nombre  = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, promocion.getNombre());
		statement.setObject(2, promocion.getNombre());
		int rows = statement.executeUpdate();

		return rows;

	}

	/*
	 * Borra una promocion
	 */
	@Override
	public int delete(Promocion promocion) throws SQLException {
		String sql = "DELETE FROM Promocion WHERE Nombre LIKE ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(3, promocion.getNombre());
		int rows = statement.executeUpdate();

		return rows;

	}

	/*
	 * Cuenta todas las promociones
	 */
	public int countAll() throws SQLException {
		String sql = "SELECT COUNT(1) AS TOTAL FROM Promocion";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("TOTAL");

		return total;
	}

	/*
	 * Pasa los datos para la creacion de una promocion
	 */
	private Promocion toPromo(ResultSet resultados) throws Exception {

		TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(resultados.getString(5));
		Atraccion[] packAtracciones = atraccionesDeLaPromocion(resultados.getLong(2), resultados.getLong(3));
		String nombre = resultados.getString(4);
		String promocionTipo = resultados.getString(10);
		Promocion promo = null;
		promo = creacionPromo(resultados, tipoAtraccion, packAtracciones, nombre, promocionTipo, promo);
		return promo;

	}

	/*
	 * Usando los datos pasados por toPromo, instancia algun tipo de promocion
	 */
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

	/*
	 * Carga las atracciones de la promocion
	 */
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

	/*
	 * sql utilizado en el m�todo atraccionesDeLaPromocion
	 */
	private String sqlAtraccion() {
		String sql = "select *" + "from Atraccion WHERE ID_Atraccion = ?";
		return sql;
	}

	/*
	 * Busca y devuelve todas las promociones de la base de datos
	 */
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

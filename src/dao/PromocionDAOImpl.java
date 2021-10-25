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

		TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(resultados.getString(5));
		Atraccion[] packAtracciones = atraccionesDeLaPromocion(resultados.getLong(2), resultados.getLong(3));
		String nombre = resultados.getString(4);
		String promocionTipo = resultados.getString(10);
		Promocion promo = null;
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
			String sql = "select *"
					+ "from Atraccion WHERE ID_Atraccion = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, atraccion1);
			ResultSet result = statement.executeQuery();

			
		
			while (result.next()) {
				
				packs[0] = atraccionDao.a_Atraccion(result);
						//buscarPorId(result.getLong(1));
				
			}
			
			String sql2 = "select *"
					+ "from Atraccion WHERE ID_Atraccion = ?";
			Connection conn2 = ConnectionProvider.getConnection();
			PreparedStatement statement2 = conn.prepareStatement(sql2);
			statement.setLong(1, atraccion2);
			ResultSet result2 = statement.executeQuery();		
		
			while (result2.next()) {
				
				packs[1] = atraccionDao.a_Atraccion(result2);
						//buscarPorId(result.getLong(1));
				
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
	}}



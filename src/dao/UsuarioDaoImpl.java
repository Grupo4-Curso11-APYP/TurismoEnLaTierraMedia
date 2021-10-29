package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import jdbc.ConnectionProvider;
import turismoEnLaTierraMediaGrupo4.EscribirItinerarios;
import turismoEnLaTierraMediaGrupo4.Ofertable;
import turismoEnLaTierraMediaGrupo4.TipoAtraccion;
import turismoEnLaTierraMediaGrupo4.Usuario;

public class UsuarioDaoImpl implements UsuarioDAO {

	@Override
	public Set<Usuario> findAll() throws SQLException {
		try {
			String sql = "SELECT * FROM USUARIO";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			Set<Usuario> usuarios = new LinkedHashSet<Usuario>();
			while (resultados.next()) {
				usuarios.add(toUser(resultados));
			}
			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() throws SQLException {
		String sql = "SELECT COUNT(1) AS TOTAL FROM USUARIO";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("TOTAL");

		return total;
	}

	@Override
	public int insert(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO USUARIO (NOMBRE, PRESUPUESTO,TIEMPODISPONIBLE,"
				+ "TIPOFAVORITO, OFERTABLE) VALUES (?, ?, ?, ?, ?)";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, usuario.getNombre());
		statement.setDouble(2, usuario.getPresupuesto());
		statement.setDouble(3, usuario.getTiempoDisponible());
		statement.setObject(4, usuario.getTipoFavorito());
		statement.setObject(5, usuario.getOfertables());

		int rows = statement.executeUpdate();

		return rows;
	}

	@Override
	public int update(Usuario usuario) throws SQLException {
		String sql = "UPDATE USUARIO SET PRESUPUESTO = ?, TIEMPODISPONIBLE = ?, " + "OFERTABLE = ? WHERE NOMBRE = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setDouble(1, usuario.getPresupuesto());
		statement.setDouble(2, usuario.getTiempoDisponible());
		statement.setObject(3, usuario.getOfertables());
		statement.setString(4, usuario.getNombre());
		int rows = statement.executeUpdate();

		return rows;

	}

	@Override
	public int delete(Usuario usuario) throws SQLException {
		String sql = "DELETE FROM USUARIO WHERE NOMBRE = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, usuario.getNombre());
		int rows = statement.executeUpdate();

		return rows;

	}

	@Override
	public Usuario findByNombre(String nombre) throws SQLException {
		try {
			String sql = "SELECT * FROM USUARIO WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Usuario usuario = null;

			if (resultados.next()) {
				usuario = toUser(resultados);
			}
			return usuario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUser(ResultSet resultados) throws Exception {
		String nombre = resultados.getString(2);
		Double presupuesto = resultados.getDouble(3);
		Double tiempoDisponible = resultados.getDouble(4);
		TipoAtraccion tipoFavorito = TipoAtraccion.valueOf(resultados.getString(5));
		return new Usuario(nombre, presupuesto, tiempoDisponible, tipoFavorito);
	}

	/*
	 * private void sugerir() throws IOException { for (Usuario usuario : usuarios)
	 * { this.ofertables =
	 * ordenarOfertasSegunPreferencia(usuario.getTipoFavorito()); for (Ofertable
	 * ofertable : ofertables) {
	 * 
	 * if (ofertable.hayCupo() && usuario.getPresupuesto() >= ofertable.getCosto()
	 * && usuario.getTiempoDisponible() >= ofertable.getTiempo()) {
	 * System.out.println("Sugerencia diaria de " + usuario.getNombre() + ":");
	 * System.out.println(ofertable);
	 * System.out.println("Pulse S  para aceptar la sugerencia o");
	 * System.out.println("cualquier otra letra para continuar y luego Enter");
	 * Scanner sc = new Scanner(System.in); char ingreso = sc.next().charAt(0); if
	 * (ingreso == 's' || ingreso == 'S') { usuario.comprarOfertable(ofertable);
	 * ofertable.reservarCupo();
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } System.out.println(usuario.toString());
	 * EscribirItinerarios.salidaItinerario(usuario); } }
	 */

	private AtraccionDAOImpl atraccionDao;
	private PromocionDAOImpl promocionDao;

	public ResultSet buscarAtraccionParaElUsuario(String nombre) throws SQLException {

			String sql = "SELECT * FROM Usuario INNER JOIN Atraccion WHERE Atraccion.Costo <= Usuario.Presupuesto AND\r\n"
					+ "Atraccion.Tiempo <= Usuario.TiempoDisponible AND Atraccion.TipoDeAtraccion = Usuario.TipoFavorito\r\n"
					+ "AND Usuario.Nombre = ?";

			/*
			 * "SELECT Itinerario.ID_Promocion, Itinerario.ID_Atraccion\r\n" +
			 * "FROM Itinerario\r\n" + "INNER JOIN Usuario \r\n" +
			 * "ON usuario.ID_Usuario = Itinerario.ID_Usuario\r\n" +
			 * "WHERE Usuario.Nombre = ?";
			 */
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			// Set<Ofertable> itinerario = new LinkedHashSet<Ofertable>();
			// atraccionDao = new AtraccionDAOImpl();
			// promocionDao = new PromocionDAOImpl();
			// esPromoOesAtraccion(resultados, itinerario);
			// return resultados;
			// ResultSet resultados = statement.executeQuery();
			while(resultados.next())
			{
				resultados.getString(1); //or rs.getString("column name");
			}
			//ResultSet ress = extraer_metadatos_query(resultados);
			//return resultados;
			return resultados;

	}

	private void extraer_metadatos_query(ResultSet resultados) throws SQLException {
		ResultSetMetaData resultados_metadatos = resultados.getMetaData();
		int columnsNumber = resultados_metadatos.getColumnCount();
		while (resultados.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1)
					System.out.print(",  ");
				String columnValue = resultados.getString(i);
				System.out.print(columnValue + " " + resultados_metadatos.getColumnName(i));
			}
			System.out.println("");
		}
	}

}
